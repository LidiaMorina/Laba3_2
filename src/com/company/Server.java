package com.company;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static Socket clientSocket;
    private static ServerSocket server;
    private static BufferedReader in;
    private static BufferedWriter out;

    public static void main(String[] args) {
        try {
            try {
                server = new ServerSocket(4004);
                System.out.println("Сервер запущен");
                // accept() будет ждать пока
                //кто-нибудь не захочет подключиться
                clientSocket = server.accept();
                try {
                    //принять и отправить сообщение
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                    String word = in.readLine();
                    System.out.println(word);

                    out.write("Сообщение: " + word + "\n");
                    out.flush(); // выталкиваем все из буфера

                } finally {
                    
                    clientSocket.close();
                    in.close();
                    out.close();
                }
            } finally {
                System.out.println("Сервер закрыт");
                server.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}