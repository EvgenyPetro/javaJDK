package ru.petrov;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Controller {

    FileWriter fileWriter;
    FileReader fileReader;
    Scanner scanner;

    public Controller() throws IOException {
        this.fileWriter = new FileWriter("chat.txt", true);
        this.fileReader = new FileReader("chat.txt");
        scanner = new Scanner(fileReader);
    }

    public void writeMessage(String message) throws IOException {
        fileWriter.write(message);
        fileWriter.flush();
//        fileWriter.close();
    }

    public String readFIle() {
        StringBuilder sb = new StringBuilder();
        while (scanner.hasNextLine()) {
            sb.append(scanner.nextLine()).append("\n");
        }
        return sb.toString();
    }
}
