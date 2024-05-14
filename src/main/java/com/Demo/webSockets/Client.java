package com.Demo.webSockets;

import java.io.*;
import java.net.*;

public class Client {
    private Socket socket;
    private int port;
    private String IP;

    public Client(String IP, int port) {
        this.IP = IP;
        this.port = port;
    }

    public void startClient() {
        try {
            socket = new Socket(IP, port);
            System.out.println("Connected to server whose IP " + IP + " on port " + port);
    
           
           
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            String message = (String) objectInputStream.readObject(); // Read string object
            int number = objectInputStream.readInt(); // Read integer object
    
            System.out.println("Received string: " + message);
            System.out.println("Received number: " + number);

            
            // Close the socket
            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void runClient() {
        String IP = "127.0.0.1"; // Assuming server is running on localhost
        int port = 8081; // Assuming server is running on port 8080
        Client client = new Client(IP, port);
        client.startClient();
    }
}
