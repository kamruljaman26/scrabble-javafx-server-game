package server;

import client.model.Command;
import client.model.Player;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class GamePlayServer extends Thread {

    // clients and membership manager
    private volatile static Map<String, Thread> clients;
    private volatile static Map<String, ObjectOutputStream> outputStreams;
    private volatile static GamePlayServer server;
    private volatile static Thread serverThread;

    // static block
    static {
        outputStreams = new HashMap<>();
        clients = new HashMap<>();
    }

    public static GamePlayServer getInstance() {
        if (server == null)
            server = new GamePlayServer();
        return server;
    }

    public static Map<String, Thread> getClients() {
        return clients;
    }

    private GamePlayServer() {
        // start the main server
        serverThread = new Thread(new CommandServer(clients, outputStreams));
        serverThread.start();
    }

    public void close() {
        System.out.println("closed");
        serverThread.stop();
    }
}
