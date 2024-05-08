package client.model;

import java.io.Serializable;

// a pojo message object
public class Command implements Serializable {

    private Player sender;
    private Player receiver;
    private CommandType commandType;
    private String message;

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

    @Override
    public String toString() {
        return "Message{" +
                "sender=" + sender +
                ", receiver=" + receiver +
                ", messageType=" + commandType +
                ", message='" + message + '\'' +
                '}';
    }
}
