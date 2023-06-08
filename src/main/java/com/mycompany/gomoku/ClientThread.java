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
import game.Player;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {

    private final Socket clientSocket;
    private GameServer gameServer;
    private PrintWriter out;
    private Player player;
    private GameController gameController;
    private int gameId;
    
    public ClientThread(Socket clientSocket, GameServer gameServer) {
        this.clientSocket = clientSocket;
        this.gameServer = gameServer;

    }

    public void start(GameController gameController, GameServer gameServer) {
        this.gameController = gameController;
        start();
        this.gameServer = gameServer;
    }

    @Override
    public void run() {
        try {
            try (clientSocket) {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    String[] parts = inputLine.split("\\s+");
                    String command = parts[0];

                    switch (command) {
                        case "stop" -> {
                            gameServer.broadcast("Server stopped");
                            gameServer.removeClient(this);
                            System.exit(0);
                        }
                        case "create" -> {

                            // String playerName = parts[1];
                            //int maxPlayers = Integer.parseInt(parts[2]);
                            //int winningStreak = Integer.parseInt(parts[3]);
                            //int timeLimit = Integer.parseInt(parts[4]);
                             gameId = gameController.createGame(player);
                            sendMessage("GAME_CREATED, with the id " + gameId + " You are player 1 : "+ player.getName() +". Waiting for the second player to join .. ");

                        }
                        case "register" -> {
                            if (2 <= parts.length) {
                                String playerName = parts[1];

                                player = gameController.registerPlayer(playerName);
                                sendMessage("USER_REGISTERED " + " You are player " + playerName );

                            } else {
                                sendMessage("ERROR Invalid command. Usage: register <playerName> ");
                            }
                        }
                        case "join" -> {
                            if (parts.length == 2) {
                                gameId = Integer.parseInt(parts[1]);
                                gameController.joinGame(gameId, player);
                               gameServer.broadcast("GAME_JOINED, with the id : " + gameId + ". You are player " + player.getName()+ ". with the id : " +player.getId() + ". Game started");

                            } else {
                                sendMessage("ERROR Invalid command. Usage: join <gameId> ");
                            }

                        }
                        case "move" -> {                       
                            if (parts.length ==3) {
                            int x = Integer.parseInt(parts[1]);
                            int y = Integer.parseInt(parts[2]);
                            String message = gameController.submitMove(gameId, 1, x, y);
                            gameServer.broadcast("Last move: " + x + " : " + y + " ; " + message + " seconds left : "+ player.getRemainingTime()/ 1000 );
                                
                            }
                            else
                            {
                                sendMessage("ERROR Invalid command. Usage: move <x> <y> ");
                            }

                        }
                        case "show" -> {
                            
                            String gameInfo = gameController.getGameInfo();                            
                            sendMessage(gameInfo);
                        }

                        default -> {
                            gameServer.broadcast("Server received an invalid request: " + inputLine);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error handling client: " + e.getMessage());
        }
    }

    public void sendMessage(String message) {
        out.println(message);
    }
}
