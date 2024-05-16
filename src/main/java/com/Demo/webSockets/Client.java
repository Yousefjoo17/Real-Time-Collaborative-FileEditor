package com.Demo.webSockets;

import java.io.*;
import java.net.*;

public class Client {
    private Socket socket;
    private int port;
    private String IP;
    private int userID;
    private String added;
    private int pos;
    
    public Client(String IP, int port, int userID, String added, int pos) {
        this.IP = IP;
        this.port = port;
        this.userID=userID;
        this.added=added;
        this.pos=pos;
    }

    public void startClient() {
        try {
            socket = new Socket(IP, port);
            System.out.println("Connected to server whose IP " + IP + " on port " + port);
            
            
            /*
            System.out.println("data from flutter");
            System.out.println(userID);
            System.out.println(added);
            System.out.println(pos);

            /******************send added and pos to the server******************/
             
            ObjectOutputStream  objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeInt(userID); // Send userID
            objectOutputStream.writeObject(added); // Send string added
            objectOutputStream.writeInt(pos); // Send position
            objectOutputStream.flush();
            System.out.println("Data has been sent to server");
            /************************************************************************/
           
            /************** recieve data from server*****************/
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            String a = (String) objectInputStream.readObject(); // Read string added
            int p = objectInputStream.readInt(); // Read position
            System.out.println("Client Received string: " + a);
            System.out.println("client Received number: " + p);
            
            //notify here Filfel 
            
           // socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
