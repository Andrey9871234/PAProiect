/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.client_gomoku.Interfata.Commands;

import com.mycompany.client_gomoku.GameClient;
import com.mycompany.client_gomoku.Interfata.MainFrame;
import java.util.ArrayList;

/**
 *
 * @author 37362
 */
public class CommandController {
    private GameClient gameClient ;
    private MainFrame mainFrame;
    private String command;
    private String response;
    private  int x = 100;
    private int y=100;
    public CommandController() {
        
        this.command = "" ;
        this.response = "" ;
    }

    public void setCommand(String command) {
        this.command = command;
        
    }

    public void setResponse(String response) {
        if(!response.isBlank()){
            if(response.startsWith("Last move: ")){
                extractCoordinates(response);
                
            }
        this.response = response;}
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

   

    public String getCommand() {
        String command1=command;
        command="" ;
        return command1;
        
    }

    public String getResponse() {
         String command1=response;
         
        response="" ;
        return command1;
    }
    
      public void extractCoordinates(String input) {
        String[] parts = input.split(" ");
        x = Integer.parseInt(parts[2]);
       y = Integer.parseInt(parts[4]);
      for (int i = 1; i <= 5; i++) {
          System.out.println(parts[i] + i);
      }
       
    }
    
    
}
