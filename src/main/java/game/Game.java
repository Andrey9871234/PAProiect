/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author 37362
 */
public class Game {
    private final int id;
    private List<Player> players;
    private Board board;
    private int currentTurn;
    private String gameStatus;
    private int winner;
    private List<Tuple<Integer, Integer>> moveHistory;
    private int maxPlayers;
    private int winningStreak;
    private int timeLimit = 100000;
    private long moveEndTime ;
    private long moveStartTime ;
    
    
    public Game(int maxPlayers, int winningStreak, int timeLimit, int id) {
    this.players = new ArrayList<>();
    this.board = new Board();
    this.currentTurn = 0;
    this.gameStatus = "Waiting for players";
    this.winner = -1;
    this.moveHistory = new ArrayList<>();
    this.maxPlayers = maxPlayers;
    this.winningStreak = winningStreak;
    this.id=id;
    
    
    
}

    public Game(Player player) {
    this.players = new ArrayList<>();
    this.board = new Board();
    this.currentTurn = 0;
    this.gameStatus = "Waiting for players";
    this.winner = -1;
    this.moveHistory = new ArrayList<>();
    this.maxPlayers = 2;
    this.winningStreak = 5;
    this.id=1;
    addPlayer(player);
   
    }

    public int getId() {
        return id;
    }

    public void addPlayer(Player player) {
    if (players.size() >= maxPlayers) {
        throw new IllegalStateException("Maximum number of players reached");
    }
    players.add(player);
    if (players.size() == maxPlayers) {
              startGame();
        
    }
}
public void startGame() {
    currentTurn = 0;
    gameStatus = "ongoing";
    winner = -1;
    moveHistory.clear();
    board = new Board();
    for (Player player : players) {
        player.setRemainingTime(timeLimit);
    }
    moveStartTime = System.currentTimeMillis();
}
public String playMove(int x, int y) {
    Player currentPlayer = players.get(currentTurn);
      moveEndTime = System.currentTimeMillis();
      long moveTime = moveEndTime - moveStartTime;
     moveStartTime = System.currentTimeMillis();
     currentPlayer.setRemainingTime((int) (currentPlayer.getRemainingTime() - moveTime));
     
    if (currentPlayer.getRemainingTime() <= 0) {
        
        gameStatus = "Player " + currentPlayer.getName() + " has lost due to time expiration.";
        winner = currentPlayer.getId() == 1 ? 2 : 1;
        return gameStatus;
    }
        
    if (board.getCell(x, y) == 0) {
        board.setCell(currentPlayer.getId(), x, y);
        moveHistory.add(new Tuple<>(x, y));
        if (checkWin(currentPlayer, x, y)) {
            gameStatus = "Game over: " + currentPlayer.getName() + " wins!";
            winner = currentPlayer.getId();
        } else {
            // Check for a draw
            if (moveHistory.size() == board.getSize() * board.getSize()) {
                gameStatus = "Game over: Draw!";
            } else {
                // Move to the next player's turn
                currentTurn = (currentTurn + 1) % players.size();
                gameStatus = " Current player : " + players.get(currentTurn).getName();
            }
        }
    } else {
        gameStatus = "Invalid move: Cell already occupied!";
    }
    return gameStatus;
    
   
}

public boolean checkWin(Player player, int x, int y) {
    // orizontal 
    int count = 0;
    for (int j = Math.max(0, y - winningStreak + 1); j <= Math.min(board.getSize() - 1, y + winningStreak - 1); j++) {
        if (Objects.equals(board.getCell(x, j), player.getId())) {
            count++;
        } else {
            count = 0;
        }
        if (count == winningStreak) {
            return true;
        }
    }

    // vertical 
    count = 0;
    for (int i = Math.max(0, x - winningStreak + 1); i <= Math.min(board.getSize() - 1, x + winningStreak - 1); i++) {
        if (Objects.equals(board.getCell(i, y), player.getId())) {
            count++;
        } else {
            count = 0;
        }
        if (count == winningStreak) {
            return true;
        }
    }

    // diagonal
    count = 0;
    for (int i = Math.max(0, x - winningStreak + 1), j = Math.max(0, y - winningStreak + 1); i <= Math.min(board.getSize() - 1, x + winningStreak - 1) && j <= Math.min(board.getSize() - 1, y + winningStreak - 1); i++, j++) {
        if (Objects.equals(board.getCell(i, j), player.getId())) {
            count++;
        } else {
            count = 0;
        }
        if (count == winningStreak) {
            return true;
        }
    }

    //  diagonal 
    count = 0;
    for (int i = Math.max(0, x - winningStreak + 1), j = Math.min(board.getSize() - 1, y + winningStreak - 1); i <= Math.min(board.getSize() - 1, x + winningStreak - 1) && j >= Math.max(0, y - winningStreak + 1); i++, j--) {
        if (Objects.equals(board.getCell(i, j), player.getId())) {
            count++;
        } else {
            count = 0;
        }
        if (count == winningStreak) {
            return true;
        }
    }

    return false;
}
public boolean checkDraw() {
    int numEmptyCells = 0;
    Integer[][] table = board.getTable();
    for (int i = 0; i < board.getSize(); i++) {
        for (int j = 0; j < board.getSize(); j++) {
            if (table[i][j] == 0) {
                numEmptyCells++;
            }
        }
    }
    return numEmptyCells == 0;
}
public void endGame(int winner) {
    this.gameStatus = "Game Over";
    this.winner = winner;
    System.out.println("Game Over!");
    if (winner == 0) {
        System.out.println("The game ended in a draw.");
    } else {
        System.out.println("The winner is player " + winner + "!");
    }
}

public List<Tuple<Integer, Integer>> getAvailableMoves() {
    List<Tuple<Integer, Integer>> availableMoves = new ArrayList<>();
    for (int i = 0; i < board.getSize(); i++) {
        for (int j = 0; j < board.getSize(); j++) {
            if (board.getCell(i, j) == 0) {
                availableMoves.add(new Tuple<>(i, j));
            }
        }
    }
    return availableMoves;
}

public int getCurrentTurn() {
    return currentTurn;
}

public int getWinner() {
    return winner;
}

public String getGameStatus() {
    return gameStatus;
}

public List<Tuple<Integer, Integer>> getMoveHistory() {
    return moveHistory;
}
public List<Player> getPlayers() {
    return players;
}


}
