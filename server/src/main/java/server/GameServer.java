package server;



import client.model.Command;
import client.model.TileDTO;
import client.view.BoardView;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameServer extends Thread {

    private volatile static Map<String, Thread> clients;
    private volatile static Map<String, ObjectOutputStream> outputStreams;
    private volatile static PlayerManager manager;
    private volatile static GameServer server;
    private volatile static Thread serverThread;

    // static block
    static {
        manager = PlayerManager.getInstance();
        outputStreams = new HashMap<>();
        clients = new HashMap<>();
    }

    public static GameServer getInstance() {
        if (server == null)
            server = new GameServer();
        return server;
    }

    public static Map<String, Thread> getClients() {
        return clients;
    }

    private GameServer() {
        // start main server
        serverThread = new Thread(new CommandServer(clients, outputStreams));
        serverThread.start();
    }

    public void close() {
        System.out.println("closed");
        serverThread.stop();
    }
    
    // send notification to all active members
    public static synchronized void sendBroadcastMessage(Command command) {
        try {
            for (ObjectOutputStream out : outputStreams.values()) {
                out.writeObject(command);
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
