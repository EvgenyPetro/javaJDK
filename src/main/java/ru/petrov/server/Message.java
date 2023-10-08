package ru.petrov.server;

public record Message(String username, String messageText) {

    public Message {
        if (messageText == null || messageText.isEmpty()){
            throw new RuntimeException();
        }
    }

    @Override
    public String toString() {
        return String.format("%s: %s", username, messageText);
    }
}
