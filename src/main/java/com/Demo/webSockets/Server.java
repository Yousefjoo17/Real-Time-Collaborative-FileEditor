package com.Demo.webSockets;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

import com.Demo.model.ClientModel;


public class Server {
    private ServerSocket serverSocket;
    private int port;
    private String IP;
    static private int userId;
    static private String added;
    static private int pos;
    static public List<ClientModel> myClientSockets;
    public Server(int port) {
       this.port=port;
       this.myClientSockets = new ArrayList<>();
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
        final Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
    try {

         /******************** receive usename, added, and pos from client***********************/
         ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
         userId = objectInputStream.readInt();
         added = (String) objectInputStream.readObject(); // Read string added
         pos = objectInputStream.readInt(); // Read position
         System.out.println("Received number from client: " + userId);
         System.out.println("Received string from client: " + added);
         System.out.println("Received number from client: " + pos);
        /**********************************************************************************/


        ClientModel clientModel=new ClientModel(userId,clientSocket);
         boolean found=false;
         for(ClientModel client : myClientSockets){
            if (client.userID==clientModel.userID){
                found = true;
            }
         }
         if (!found) {
            myClientSockets.add(clientModel);
         }


        System.out.println("number of connected users is " + myClientSockets.size());

        /*******************************************Broadcasting**********************************/
        for(int i=0;i<myClientSockets.size();i++){ 
        System.out.println("start sending data to the cleint");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        objectOutputStream.writeObject("Hello"); // added string
        objectOutputStream.writeInt(7); // pos
        objectOutputStream.flush();
        System.out.println("Data has been sent to a client");
        }
        /*******************************************************************************************/
            
} catch (Exception e) {
        e.printStackTrace();
     }
        }
             }
                }
