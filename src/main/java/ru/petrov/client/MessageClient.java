package ru.petrov.client;

import ru.petrov.exception.ServerNotWorkException;
import ru.petrov.server.Message;
import ru.petrov.server.MessageServer;

import java.util.List;

public class MessageClient {

    private MessageClientGUI gui;
    private String name;
    private MessageServer messageServer;
    private boolean isConnected;

    public MessageClient(MessageServer messageServer, MessageClientGUI gui) {
        this.messageServer = messageServer;
        this.gui = gui;
    }

    public void connectToServer(String name) throws ServerNotWorkException {
        this.name = name;
        messageServer.connectUser(this);
        isConnected = true;
    }

    public List<Message> getHistory() {
        return messageServer.getHistory();
    }

    public void sendMessage(Message message) {
        if (isConnected) {
            messageServer.sendMessage(message);
        }
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void disconnect(){
        isConnected = false;
        gui.getPanServerAndUser().setVisible(true);
    }

    public void update() {
        gui.update();
    }


}
