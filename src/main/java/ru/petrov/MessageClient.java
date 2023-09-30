package ru.petrov;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class MessageClient extends JFrame {

    private int WIDTH = 800;
    private int HEIGHT = 600;

    JButton btnLogin, btnSend;
    Controller controller;

    public MessageClient() throws Exception {
        controller = new Controller();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setTitle("Chat client");
        setLocationRelativeTo(null);
        setResizable(false);


        JPanel panServer = new JPanel(new GridLayout(1, 4));
        JPanel panUserInfo = new JPanel(new GridLayout(1, 3));
        JPanel panTextArea = new JPanel(new GridLayout(1, 1));
        JPanel panServerAndUser = new JPanel(new GridLayout(2, 3));
        JPanel panelMessageAndSendBtn = new JPanel(new BorderLayout());

        JTextField fieldHost = new JTextField("192.168.0.256");
        JTextField fieldPort = new JTextField("8080");
        JTextField fieldNull = new JTextField();
        JTextField fieldLogin = new JTextField("Evgeny");
        JTextField fieldMessage = new JTextField();
        JPasswordField fieldPassword = new JPasswordField("qwerty");
        fieldPassword.setEchoChar('*');

        btnLogin = new JButton("login");
        btnSend = new JButton("send");

        JTextArea textArea = new JTextArea();

        panTextArea.add(textArea);
        panServer.add(fieldHost);
        panServer.add(fieldPort);
        fieldNull.setVisible(false);
        panServer.add(fieldNull);

        panUserInfo.add(fieldLogin);
        panUserInfo.add(fieldPassword);
        panUserInfo.add(btnLogin);

        panServerAndUser.add(panServer);
        panServerAndUser.add(panUserInfo);

        panelMessageAndSendBtn.add(fieldMessage, BorderLayout.CENTER);
        panelMessageAndSendBtn.add(btnSend, BorderLayout.EAST);


        add(panServerAndUser, BorderLayout.NORTH);
        add(textArea);
        add(panelMessageAndSendBtn, BorderLayout.SOUTH);
        setVisible(true);


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

        btnLogin.addActionListener((e) -> {
            textArea.setText(controller.readFIle());
        });

        revalidate();
    }

}
