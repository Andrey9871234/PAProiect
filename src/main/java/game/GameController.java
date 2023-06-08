/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author 37362
 */
public class GameController {

    private List<Game> games;
    private int gameId;
    private int playerId;
    private int controllerId;

    public GameController(int controllerId) {
        games = new ArrayList<>();
        gameId = 0;
        controllerId = 1;
        this.controllerId = controllerId;
    }

    public int createGame(Player player) {
      
        gameId = gameId + 1;
        Game game = new Game(player);
        games.add(game);
        return gameId;
    }

    public Player registerPlayer(String playerName) {
        playerId++;
        Player player = new Player(playerName, playerId);
        return player;
    }

    public void joinGame(int gameId, Player player) {
        
        Game game;
        game = games.get(gameId - 1);
        game.addPlayer(player);
    }

    public void startingGame(int gameId) {
        
        Game game = games.get(gameId - 1);
        game.startGame();
    }

    public String submitMove(int gameId, int playerId, int x, int y) {
        Game game = games.get(gameId - 1);
        return game.playMove(x, y);
    }

    public String getGameInfo() {

        StringBuilder sb = new StringBuilder();

        for (Game game : games) {
            sb.append("Game ID: ").append(game.getId()).append("\n");

            sb.append("Players: ");
            for (Player player : game.getPlayers()) {
                sb.append(player.getName()).append(", ");
            }
            sb.delete(sb.length() - 2, sb.length()); 
            sb.append("\n");

            sb.append("Number of Moves: ").append(game.getMoveHistory().size()).append("\n");
            sb.append("Remaining Time: ").append(game.getPlayers().stream()
                    .map(player -> "Player " + player.getId() + ": " + player.getRemainingTime() + " seconds")
                    .collect(Collectors.joining(", "))).append("\n");

            sb.append("Current Turn: ").append(game.getCurrentTurn()).append("\n");
            sb.append("Game Status: ").append(game.getGameStatus()).append("\n");
            sb.append("Winner: ").append(game.getWinner()).append("\n");
            sb.append("Move History: ");
            List<Tuple<Integer, Integer>> moveHistory = game.getMoveHistory();
            for (Tuple<Integer, Integer> move : moveHistory) {
                sb.append("(").append(move.getFirst()).append(", ").append(move.getSecond()).append("), ");
            }
            sb.delete(sb.length() - 2, sb.length()); 
            sb.append("\n");

            sb.append("\n");
        }

        return sb.toString();
    }

}
