/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.client_gomoku;

/**
 *
 * @author 37362
 */
import com.mycompany.client_gomoku.Interfata.Commands.CommandController;
import com.mycompany.client_gomoku.Interfata.MainFrame;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class GameClient {

    private final String serverHostname;
    private final int serverPort;

    public GameClient(String serverHostname, int serverPort) {
        this.serverHostname = serverHostname;
        this.serverPort = serverPort;
    }

    public void start() throws IOException {
        try (Socket socket = new Socket(serverHostname, serverPort); BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); PrintWriter out = new PrintWriter(socket.getOutputStream(), true); BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            CommandController comController = new CommandController();
            System.out.println("Connected to server at " + serverHostname + ":" + serverPort);
            String inputLine;
            StringBuilder serverResponse = new StringBuilder();
            MainFrame mainFrame = new MainFrame(comController);
            mainFrame.start(mainFrame);

            while ((inputLine = comController.getCommand()) != null) {
               
                if (!inputLine.isBlank()) { 
                    System.out.print(">> ");
                    System.out.println(inputLine);
                    out.println(inputLine);
                    serverResponse.setLength(0);
                    if ("exit".equals(inputLine)) {
                        break;
                    }
                }
                socket.setSoTimeout(25);

                while (true) {
                    try {
                        inputLine = in.readLine();
                        if (inputLine != null) {
                            System.out.println(inputLine);
                            comController.setResponse(inputLine);
                            if (inputLine.trim().equals("END_OF_RESPONSE")) {
                                break;
                            }
                        } else {

                            break;
                        }
                    } catch (SocketTimeoutException e) {

                        break;
                    }
                
                }
                
                
            }
        } catch (IOException e) {
            System.err.println("Error connecting to server: " + e.getMessage());
        }
    }
}
