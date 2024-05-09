package server;


import client.model.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;

/**
 * A multithreading client handler to manage communication between different clients
 */
public class ServerClientHandler implements Runnable {

    private final Socket socket;
    private final ObjectInputStream inputStream;
    private final ObjectOutputStream outputStream;
    private final Player player;
    private final Map<String, ObjectOutputStream> outputStreams;

    // init input and output
    public ServerClientHandler(Socket socket, Player player,
                               ObjectInputStream inputStream,
                               ObjectOutputStream outputStream,
                               Map<String, ObjectOutputStream> outputStreams) {
        this.socket = socket;
        this.player = player;
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        this.outputStreams = outputStreams;
    }

    public Socket getSocket() {
        return socket;
    }

    public ObjectOutputStream getOutputStream() {
        return outputStream;
    }

    public ObjectInputStream getInputStream() {
        return inputStream;
    }

    public Player getMember() {
        return player;
    }

    // thread run method to start, a starting point
    public void run() {
        while (true) {
            try {
                // receive the message
                Command receivedCommand = (Command) inputStream.readObject();

                if (receivedCommand != null) {

                    System.out.printf("Message Server: (Type: , %s from: %s, Msg: %s\n",
                            receivedCommand.getMessageType(), receivedCommand.getSender(),
                            receivedCommand.getMessage());

                    /*
                     * TODO: HANDLE MESSAGE TYPE
                     */
                    if (receivedCommand.getMessageType().equals(CommandType.NEW_MATCH_REQUEST_HUMAN)) {
                        System.out.println(outputStreams.values().size());
                        if (outputStreams.values().size() >= 2) {
                            try {
                                Command command = new Command(null, player, CommandType.MATCH_START, "");
                                outputStream.writeObject(command);
                                outputStream.flush();

                                System.out.println(command);
                                System.out.println("Start Match Request to player" + player);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }

                } else {
                    System.out.println("error message is null!");
                }

            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Exception while sending message or");
                System.out.println(player.getName() + " disconnected from server.");
                try {
                    socket.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}