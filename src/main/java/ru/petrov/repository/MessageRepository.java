package ru.petrov.repository;

import ru.petrov.server.Message;

import java.util.List;

public interface MessageRepository {

    List<Message> getAllMessages();

    void saveMessage(Message message);
}
