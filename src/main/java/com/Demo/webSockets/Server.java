package com.Demo.webSockets;

import java.io.*;
import java.net.*;

public class Server {
    private ServerSocket serverSocket;
    private int port;
    private String IP;

    public Server() {
        // Constructor
    }

    public void startServer() {
        try {
            serverSocket = new ServerSocket(8080); // Use any available port
            //port = serverSocket.getLocalPort();
            InetAddress localHost = InetAddress.getLocalHost();
            IP = localHost.getHostAddress();
            System.out.println("Server started port " + port + " IP " + IP);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);

                // Handle client communication in a separate thread
                Thread thread = new Thread(new ClientHandler(clientSocket));
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClientHandler implements Runnable {
        private final Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
                objectOutputStream.writeObject("Hello"); // added string
                objectOutputStream.writeInt(7); // pos
                objectOutputStream.flush();
                
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static void runServer() {
        Server server = new Server();
        server.startServer();
    }
}
