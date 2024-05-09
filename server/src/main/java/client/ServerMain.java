package client;

import server.GamePlayServer;

/**
 * The entry point for starting the game server.
 */
public class ServerMain {
    public static void main(String[] args) {
        GamePlayServer.getInstance();
    }
}