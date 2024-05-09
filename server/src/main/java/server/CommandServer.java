package server;

import client.model.Command;
import client.model.CommandType;
import client.model.Player;
import util.Util;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

/**
 * Will create server socket
 */
public class CommandServer implements Runnable {

    private static final PlayerManager manager;
    private final Map<String, Thread> clients;
    private final Map<String, ObjectOutputStream> outputStreams;

    static {
        manager = PlayerManager.getInstance();
    }

    public CommandServer(Map<String, Thread> clients, Map<String, ObjectOutputStream> outputStreams) {
        this.clients = clients;
        this.outputStreams = outputStreams;
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(Util.DEFAULT_PORT, 50, Util.IP_ADDRESS);
            Socket socket;

            // server details
            System.out.println("Server started on port: " + Util.DEFAULT_PORT);
            System.out.println("Server started on IP: " + Util.IP_ADDRESS);

            while (true) {
                // accept a new player client
                socket = serverSocket.accept();
                System.out.println("\n ---- Socket Accepted ---- ");

                // read player and in player list
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

                // read member
                System.out.println("trying to read player");
                Player player = (Player) objectInputStream.readObject();
                if (player != null) {
                    manager.addPlayer(player);

                    // create client handler in separate thread
                    System.out.println(socket);
                    System.out.println(serverSocket);

                    // create client connection handler
                    ServerClientHandler serverClientHandler = new ServerClientHandler(socket, player,
                            objectInputStream, objectOutputStream, outputStreams);

                    Thread thread = new Thread(serverClientHandler);

                    // add in clients and streams
                    clients.put(player.getId(), thread);
                    outputStreams.put(player.getId(), objectOutputStream);

                    // send a success message.
                    objectOutputStream.writeObject(
                            new Command(null, player, CommandType.CONNECTED_WTH_SERVER, "")
                    );

                    System.out.println("New Player Connected In Server: " + player);

                    thread.start();
                } else {
                    System.out.println("IN SERVER: Player is null.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
