package client.model;

import util.Util;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

// a pojo player class
public class Player implements Serializable {

    private String id;
    private String name;
    private String serverIpAddress;
    private int serverPort;
    private int listeningPort;

    public Player(String name) {
        id = createUniqueId();
        this.name = name;
        serverPort = Util.DEFAULT_PORT;
        serverIpAddress = Util.IP_ADDRESS.getHostAddress();
        listeningPort = 9001;
    }

    public Player(String id, String serverIpAddress, int serverPort, int listeningPort, String name) {
        this.id = id;
        this.name = name;
        this.serverIpAddress = serverIpAddress;
        this.serverPort = serverPort;
        this.listeningPort = listeningPort;
    }

    private String createUniqueId() {
        // Generate a random UUID
        UUID uuid = UUID.randomUUID();

        // Convert UUID to string and replace hyphens
        String fullId = uuid.toString().replace("-", "");

        // Return a substring of the first 12 characters
        return fullId.substring(0, 12);
    }


    public String getId() {
        return id;
    }

    public String getServerIpAddress() {
        return serverIpAddress;
    }

    public int getServerPort() {
        return serverPort;
    }

    public int getListeningPort() {
        return listeningPort;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setServerIpAddress(String serverIpAddress) {
        this.serverIpAddress = serverIpAddress;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public void setListeningPort(int listeningPort) {
        this.listeningPort = listeningPort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(id, player.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", serverIpAddress='" + serverIpAddress + '\'' +
                ", serverPort=" + serverPort +
                ", listeningPort=" + listeningPort +
                '}';
    }
}
