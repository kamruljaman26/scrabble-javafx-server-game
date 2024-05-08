package client.server;

import client.model.Command;
import client.model.Player;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Manage Client Communication
 */
public class ClientConnection {

    private final Socket socket;
    private final ObjectOutputStream outputStream;
    private final ObjectInputStream inputStream;

    public ClientConnection(Socket socket) throws IOException {
        this.socket = socket;
        this.outputStream = new ObjectOutputStream(socket.getOutputStream());
        this.inputStream = new ObjectInputStream(socket.getInputStream());
    }

    public ClientConnection(InetAddress address, int port) throws IOException {
        this.socket = new Socket(address, port);
        this.outputStream = new ObjectOutputStream(socket.getOutputStream());
        this.inputStream = new ObjectInputStream(socket.getInputStream());
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

    public void close() throws IOException {
        socket.close();
    }

    public void sendMessage(Command message) throws IOException {
        outputStream.writeObject(message);
        outputStream.flush();
    }

    public Command readMessage() throws IOException, ClassNotFoundException {
        return (Command) inputStream.readObject();
    }

    public void sendObject(Object message) throws IOException {
        outputStream.writeObject(message);
        outputStream.flush();
    }
}
