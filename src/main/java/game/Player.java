/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import java.net.Socket;

/**
 *
 * @author 37362
 */
public class Player {
    private String name;
    private Integer color =0;
    private Integer wins;
    private Integer losses;
    private Integer draws;
    private boolean isHuman;
    private boolean isTurn;
    private Tuple<Integer, Integer> lastMove;
    private int remainingTime;
    
    
    public Player(String name, boolean isHuman) {
        this.name = name;
        this.color = color+1;
        this.wins = 0;
        this.losses = 0;
        this.draws = 0;
        this.isHuman = isHuman;
        this.isTurn = false;
        this.lastMove = null;
        remainingTime = 0;
    }

    public Player(String name, int id) {
        this.name = name;
        this.color = id;
        this.wins = 0;
        this.losses = 0;
        this.draws = 0;
        this.isHuman = true;
        this.isTurn = false;
        this.lastMove = null;
        remainingTime = 0;
    }
    
    public String getName() {
        return name;
    }
    
    public Integer getId() {
        return color;
    }
    
    public Integer getWins() {
        return wins;
    }
    
    public Integer getLosses() {
        return losses;
    }
    
    public Integer getDraws() {
        return draws;
    }
    
    public boolean isHuman() {
        return isHuman;
    }
    
    public boolean isTurn() {
        return isTurn;
    }
    
    public Tuple<Integer, Integer> getLastMove() {
        return lastMove;
    }
    
    public void setTurn(boolean isTurn) {
        this.isTurn = isTurn;
    }
    
    public void setLastMove(Tuple<Integer, Integer> lastMove) {
        this.lastMove = lastMove;
    }
    
    public void incrementWins() {
        this.wins++;
    }
    
    public void incrementLosses() {
        this.losses++;
    }
    
    public void incrementDraws() {
        this.draws++;
    }
    
    public int getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

}
