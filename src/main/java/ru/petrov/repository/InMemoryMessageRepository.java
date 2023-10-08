package ru.petrov.repository;

import ru.petrov.server.Message;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InMemoryMessageRepository implements MessageRepository {

    List<Message> messageList;
    private static final String LOG_PATH = "chat.txt";
    FileWriter fileWriter;
    FileReader fileReader;

    public InMemoryMessageRepository() throws IOException {

        this.fileWriter = new FileWriter(LOG_PATH, true);
        this.fileReader = new FileReader(LOG_PATH);
    }


    @Override
    public List<Message> getAllMessages() {
        messageList = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(LOG_PATH));
            String line = reader.readLine();

            while (line != null) {
                String[] message = line.split(": ");
                messageList.add(new Message(message[0], message[1]));
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return messageList;
    }

    @Override
    public void saveMessage(Message message) {

        try {
            fileWriter.write(message.toString()+"\n");
            fileWriter.flush();
        } catch (IOException e) {
            System.out.println("error write file");
        }
    }
}
