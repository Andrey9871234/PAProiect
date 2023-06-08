/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.gomoku;

import java.io.IOException;

/**
 *
 * @author 37362
 */
public class Gomoku {

   public static void main(String[] args) throws IOException {
        int port = 12345;
        GameServer gameServer = new GameServer(port);
        gameServer.start();
    }
}
