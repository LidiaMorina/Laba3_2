package com.company;
import java.io.*;
import java.net.Socket;

public class Client {
    //сокет, ридер для чтения, поток записи и чтения
    private static Socket clientSocket;
    private static BufferedReader reader;
    private static BufferedReader in;
    private static BufferedWriter out;

    public static void main(String[] args) {
        try {
            try {

                clientSocket = new Socket("localhost", 4004);
                reader = new BufferedReader(new InputStreamReader(System.in));
                // читать соообщения с сервера
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                // запись
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                System.out.println("Сообщение серверу:");
                String word = reader.readLine();
                out.write(word + "\n"); // отправляем сообщение на сервер
                out.flush();
                String serverWord = in.readLine();
                System.out.println(serverWord);
            } finally {
                System.out.println("Клиент закрыт");
                clientSocket.close();
                in.close();
                out.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }

    }
}
