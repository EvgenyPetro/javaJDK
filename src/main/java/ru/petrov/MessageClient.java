package ru.petrov;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class MessageClient extends JFrame {
    private final static int WIDTH = 800;
    private final static int HEIGHT = 600;
    private final Controller controller;

    private JTextField fieldLogin;
    private JTextArea textArea;

    public MessageClient() throws Exception {
        controller = new Controller();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setTitle("Chat client");
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        JPanel panServer = panServer();
        JPanel panUserInfo = panUserInfo();
        JPanel panTextArea = panTextArea();
        JPanel panServerAndUser = panServerAndUser(panServer, panUserInfo);
        JPanel panelMessageAndSendBtn = panelMessageAndSendBtn();

        add(panServerAndUser, BorderLayout.NORTH);
        add(panTextArea);
        add(panelMessageAndSendBtn, BorderLayout.SOUTH);

        revalidate();
    }

    private JPanel panServer() {

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

    private JPanel panUserInfo() {

        JPanel panUserInfo = new JPanel(new GridLayout(1, 3));
        fieldLogin = new JTextField("Evgeny");
        JPasswordField fieldPassword = new JPasswordField("qwerty");
        JButton btnLogin = new JButton("login");

        fieldPassword.setEchoChar('*');

        panUserInfo.add(fieldLogin);
        panUserInfo.add(fieldPassword);
        panUserInfo.add(btnLogin);

        btnLogin.addActionListener((e) -> {
            textArea.setText(controller.readFIle());
        });

        return panUserInfo;
    }


    private JPanel panTextArea() {

        JPanel panTextArea = new JPanel(new GridLayout(1, 1));
        textArea = new JTextArea();

        panTextArea.add(textArea);

        return panTextArea;
    }

    private JPanel panServerAndUser(JPanel... panel) {

        JPanel panServerAndUser = new JPanel(new GridLayout(2, 3));

        for (JPanel jPanel : panel) {
            panServerAndUser.add(jPanel);
        }

        return panServerAndUser;
    }

    private JPanel panelMessageAndSendBtn() {

        JPanel panelMessageAndSendBtn = new JPanel(new BorderLayout());
        JTextField fieldMessage = new JTextField();
        JButton btnSend = new JButton("send");

        panelMessageAndSendBtn.add(fieldMessage, BorderLayout.CENTER);
        panelMessageAndSendBtn.add(btnSend, BorderLayout.EAST);

        btnSend.addActionListener((e) -> {
            String text = fieldMessage.getText();
            String message = String.format("%s: %s\n", fieldLogin.getText(), text);
            textArea.append(message);
            fieldMessage.setText("");
            try {
                controller.writeMessage(message);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        fieldMessage.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String text = fieldMessage.getText();
                    String message = String.format("%s: %s\n", fieldLogin.getText(), text);
                    textArea.append(message);
                    fieldMessage.setText("");
                    try {
                        controller.writeMessage(message);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        return panelMessageAndSendBtn;
    }

}
