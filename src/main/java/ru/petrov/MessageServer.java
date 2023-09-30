package ru.petrov;

import javax.swing.*;
import java.awt.*;

public class MessageServer extends JFrame {

    private JButton btnStart, btnStop;

    private int WIDTH = 600;
    private int HEIGHT = 800;


    public MessageServer() throws HeadlessException {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setTitle("Chat Server");
        setLocationRelativeTo(null);
        setResizable(false);

        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");

        JPanel panTextArea = new JPanel(new GridLayout(1,1));
        TextArea textArea = new TextArea();

        panTextArea.add(textArea);

        JPanel panBottom = new JPanel(new GridLayout(1, 2));
        panBottom.add(btnStart);
        panBottom.add(btnStop);

        add(panTextArea);
        add(panBottom, BorderLayout.SOUTH);

        setVisible(true);
    }
}
