package client.model;

import java.io.Serializable;

// A Plain Old Java Object (POJO) representing a message command
public class Command implements Serializable {

    private Player sender; // The player who sent the command
    private Player receiver; // The player who should receive the command
    private CommandType commandType; // The type of command (e.g., SEND_MESSAGE, START_GAME)
    private String message; // The content of the command

    public Command(Player sender, Player receiver, CommandType commandType, String message) {
        this.sender = sender;
        this.receiver = receiver;
        this.commandType = commandType;
        this.message = message;
    }

    public Player getSender() {
        return sender;
    }

    public void setSender(Player sender) {
        this.sender = sender;
    }

    public Player getReceiver() {
        return receiver;
    }

    public void setReceiver(Player receiver) {
        this.receiver = receiver;
    }

    public CommandType getMessageType() {
        return commandType;
    }

    public void setMessageType(CommandType commandType) {
        this.commandType = commandType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // Overriding toString() method to provide a string representation of the command object
    @Override
    public String toString() {
        return "Command{" +
                "sender=" + sender +
                ", receiver=" + receiver +
                ", commandType=" + commandType +
                ", message='" + message + '\'' +
                '}';
    }
}
