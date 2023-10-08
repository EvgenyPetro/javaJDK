package ru.petrov;


import ru.petrov.client.MessageClient;
import ru.petrov.client.MessageClientGUI;
import ru.petrov.repository.InMemoryMessageRepository;
import ru.petrov.server.MessageServer;
import ru.petrov.server.MessageServerGUI;

public class Main {
    public static void main(String[] args) throws Exception {
        MessageServer server = new MessageServer(new InMemoryMessageRepository());
        new MessageServerGUI(server);
        new MessageClient(server, new MessageClientGUI(server));
        new MessageClient(server, new MessageClientGUI(server));

    }
}