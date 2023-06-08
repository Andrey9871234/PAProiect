/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gomoku;

/**
 *
 * @author 37362
 */

import game.GameController;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class GameServer {
    private final int port;
    private final List<ClientThread> clients;
     private GameServer gameServer;
    
   
    private GameController gameController = new GameController(1);
    
    public GameServer(int port) {
        this.port = port;
        this.clients = new ArrayList<>();
       
    }
    
    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("GameServer started at port " + port);
        
        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("New client connected: " + clientSocket.getInetAddress().getHostAddress());
            ClientThread clientThread = new ClientThread(clientSocket, this);
            clients.add(clientThread);
            clientThread.start(gameController, this);
            }
    }
    
    public void broadcast(String message) {
        for (ClientThread client : clients) {
            client.sendMessage(message);
        }
    }
    
    public void removeClient(ClientThread client) {
        clients.remove(client);
    }
    
    
}

