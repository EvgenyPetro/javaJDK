package ru.petrov.server;

import ru.petrov.client.MessageClient;
import ru.petrov.exception.ServerNotWorkException;
import ru.petrov.repository.MessageRepository;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MessageServer extends JFrame {

    private List<MessageClient> messageClients;
    private boolean isWork;
    private MessageRepository messageRepository;

    public MessageServer(MessageRepository messageRepository) {
        messageClients = new ArrayList<>();
        this.messageRepository = messageRepository;
    }


    public void connectUser(MessageClient messageClient) throws ServerNotWorkException {
        if (isWork) {
            messageClients.add(messageClient);
        } else {
            throw new ServerNotWorkException("Server not work");
        }
    }

    public void startServer() {
        isWork = true;
    }

    public void stopServer() {
        isWork = false;
        disconnectAllClients();
    }

    public List<Message> getHistory() {
        return messageRepository.getAllMessages();
    }

    public void sendMessage(Message message) {
        messageRepository.saveMessage(message);
        messageClients.forEach(MessageClient::update);
    }

    public void disconnectAllClients(){
        messageClients.forEach(MessageClient::disconnect);
    }


}
