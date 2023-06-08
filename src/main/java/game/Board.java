/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 37362
 */
public class Board {
    private Integer[][] table = new Integer[19][19];
    private Integer lastX;
    private Integer lastY;
    private Integer lastColor;
    private List<Integer[]> winningCells;

    public Board() {
        for (Integer[] table1 : table) {
            for (int j = 0; j < table1.length; j++) {
                table1[j] = 0;
            }
        }
        this.lastX = null;
        this.lastY = null;
        this.lastColor = null;
        this.winningCells = new ArrayList<>();
    }

    public void setCell(Integer color, Integer x, Integer y) {
        table[x][y] = color;
        lastX = x;
        lastY = y;
        lastColor = color;
    }

    public Integer getCell(Integer x, Integer y) {
        return table[x][y];
    }

    public Integer[][] getTable() {
        return table;
    }

    public void setTable(Integer[][] table) {
        this.table = table;
    }

    public Integer getLastX() {
        return lastX;
    }

    public void setLastX(Integer lastX) {
        this.lastX = lastX;
    }

    public Integer getLastY() {
        return lastY;
    }

    public void setLastY(Integer lastY) {
        this.lastY = lastY;
    }

    public Integer getLastColor() {
        return lastColor;
    }

    public void setLastColor(Integer lastColor) {
        this.lastColor = lastColor;
    }

    public List<Integer[]> getWinningCells() {
        return winningCells;
    }

    public void setWinningCells(List<Integer[]> winningCells) {
        this.winningCells = winningCells;
    }

   public  int getSize() {
    return 19;    
    }
}
