package ru.petrov.server;

import javax.swing.*;
import java.awt.*;

public class MessageServerGUI extends JFrame {

    private MessageServer server;

    private JButton btnStart, btnStop;
    private JTextArea textArea;

    private int WIDTH = 600;
    private int HEIGHT = 800;


    public MessageServerGUI(MessageServer server) throws HeadlessException {

        this.server = server;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setTitle("Chat Server");
        setLocationRelativeTo(null);
        setResizable(false);

        add(textAreaPanel());
        add(bottomPanel(), BorderLayout.SOUTH);

        setVisible(true);
    }

    private Component textAreaPanel() {
        JPanel panTextArea = new JPanel(new GridLayout(1, 1));
        textArea = new JTextArea();
//        JScrollPane scrollBar = new JScrollPane(textArea,);
        textArea.setEditable(false);

//        panTextArea.add(scrollBar);
        panTextArea.add(textArea);

        return panTextArea;
    }

    private Component bottomPanel() {
        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");

        JPanel panBottom = new JPanel(new GridLayout(1, 2));
        panBottom.add(btnStart);
        panBottom.add(btnStop);

        btnStart.addActionListener(e -> {
            textArea.setText("");
            textArea.append("Server Started\n");
            server.getHistory().forEach(m -> textArea.append(m + "\n"));
            server.startServer();
        });

        btnStop.addActionListener(e -> {
            textArea.append("Server Stopped\n");
            server.stopServer();
        });

        return panBottom;
    }
}
