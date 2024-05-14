package com.Demo.webSockets;

import java.io.*;
import java.net.*;


public class Server {
    private ServerSocket serverSocket;
    private int port;
    private String IP;
   static private String added;
   static private int pos;
    public Server(int port) {
       this.port=port;
    }

    public void startServer() {
        try {
            serverSocket = new ServerSocket(port); //if not avialable incraese it by 77
            port = serverSocket.getLocalPort();
            InetAddress localHost = InetAddress.getLocalHost();
            IP = localHost.getHostAddress();
            System.out.println("Server started port " + port + " IP " + IP);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);

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
        System.out.println("start sending data to the cleint");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        objectOutputStream.writeObject("Hello"); // added string
        objectOutputStream.writeInt(7); // pos
        objectOutputStream.flush();
        System.out.println("Data has been sent to client");
        
        // receive added and pos from client
        ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
        added = (String) objectInputStream.readObject(); // Read string added
        pos = objectInputStream.readInt(); // Read position
        
        System.out.println("Received string from client: " + added);
        System.out.println("Received number from client: " + pos);
    } catch (Exception e) {
        e.printStackTrace();
     }
        }
             }
                }
