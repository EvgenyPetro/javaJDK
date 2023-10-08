package ru.petrov.client;

import ru.petrov.exception.ServerNotWorkException;
import ru.petrov.server.Message;
import ru.petrov.server.MessageServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.Method;

public class MessageClientGUI extends JFrame {
    private final static int WIDTH = 800;
    private final static int HEIGHT = 600;

    private MessageClient client;

    private JTextField fieldLogin;
    private JTextArea textArea;

    public Component getPanServerAndUser() {
        return panServerAndUser;
    }

    private Component panServerAndUser;


    public MessageClientGUI(MessageServer server) {

        this.client = new MessageClient(server, this);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setTitle("Chat client");
        setLocationRelativeTo(null);
        setResizable(false);

        createPanel();

        setVisible(true);
    }

    private void createPanel() {
        panServerAndUser = panServerAndUser(panServer(), panUserInfo());
        add(panServerAndUser, BorderLayout.NORTH);
        add(panTextArea());
        add(panelMessageAndSendBtn(), BorderLayout.SOUTH);
    }

    private Component panServer() {

        JPanel panServer = new JPanel(new GridLayout(1, 4));
        JTextField fieldHost = new JTextField("192.168.0.256");
        JTextField fieldPort = new JTextField("8080");
        JTextField fieldNull = new JTextField();

        panServer.add(fieldHost);
        panServer.add(fieldPort);
        fieldNull.setVisible(false);
        panServer.add(fieldNull);

        return panServer;
    }

    private Component panUserInfo() {

        JPanel panUserInfo = new JPanel(new GridLayout(1, 3));
        fieldLogin = new JTextField("Evgeny");
        JPasswordField fieldPassword = new JPasswordField("qwerty");
        JButton btnLogin = new JButton("login");

        fieldPassword.setEchoChar('*');

        panUserInfo.add(fieldLogin);
        panUserInfo.add(fieldPassword);
        panUserInfo.add(btnLogin);

        btnLogin.addActionListener(e -> {
            if (!client.isConnected()) {
                try {
                    client.connectToServer(fieldLogin.getText());
                    panServerAndUser.setVisible(false);
                    textArea.setText("");
                    textArea.append("Connected to Server\n");
                    client.getHistory().forEach(message -> textArea.append(message.toString() + "\n"));

                } catch (ServerNotWorkException ex) {
                    textArea.append("Server not Working\n");
                }
            }
        });

        return panUserInfo;
    }

    private Component panTextArea() {

        JPanel panTextArea = new JPanel(new GridLayout(1, 1));
        textArea = new JTextArea();
        textArea.setEditable(false);

        panTextArea.add(textArea);

        return panTextArea;
    }

    private Component panServerAndUser(Component... panel) {

        JPanel panServerAndUser = new JPanel(new GridLayout(2, 3));

        for (Component jPanel : panel) {
            panServerAndUser.add(jPanel);
        }

        return panServerAndUser;
    }

    private Component panelMessageAndSendBtn() {

        JPanel panelMessageAndSendBtn = new JPanel(new BorderLayout());
        JTextField fieldMessage = new JTextField();
        JButton btnSend = new JButton("send");

        panelMessageAndSendBtn.add(fieldMessage, BorderLayout.CENTER);
        panelMessageAndSendBtn.add(btnSend, BorderLayout.EAST);

        btnSend.addActionListener((e) -> {
            Message message = createMessage(fieldMessage);
            client.sendMessage(message);
        });

        fieldMessage.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    Message message = createMessage(fieldMessage);
                    client.sendMessage(message);
                }
            }
        });

        return panelMessageAndSendBtn;
    }

    private Message createMessage(JTextField fieldMessage) {
        String text = fieldMessage.getText();
        Message message = new Message(fieldLogin.getText(), text);
        fieldMessage.setText("");
        return message;
    }

    public void update() {
        textArea.setText("");
        client.getHistory().forEach(m -> textArea.append(m.toString() + "\n"));
    }

}
