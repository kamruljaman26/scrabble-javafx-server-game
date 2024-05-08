package client.model;

import java.io.Serializable;
import java.util.Objects;

// a pojo player class
public class Player implements Serializable {

    private String id;
    private String name;
    private String serverIpAddress;
    private int serverPort;
    private int listeningPort;

    public Player(String id, String serverIpAddress, int serverPort, int listeningPort, String name) {
        this.id = id;
        this.name = name;
        this.serverIpAddress = serverIpAddress;
        this.serverPort = serverPort;
        this.listeningPort = listeningPort;
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
