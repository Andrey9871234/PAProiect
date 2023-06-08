/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.client_gomoku;

import java.io.IOException;

/**
 *
 * @author 37362
 */
public class Client_Gomoku {

   public static void main(String[] args) {
        String serverHostname = "localhost";
        int serverPort = 12345;
        GameClient gameClient = new GameClient(serverHostname, serverPort);
        try {
            gameClient.start();
        } catch (IOException e) {
            System.err.println("Error during start client: " + e.getMessage());
        }
    }
}
