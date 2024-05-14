package com.Demo.model;

import java.net.Socket;

public class ClientModel {
  public  int userID;
  public  Socket clientSocket;

    public ClientModel(int id, Socket s){
        this.userID=id;
        this.clientSocket=s;
    }
}
