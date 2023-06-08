/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import java.util.List;

/**
 *
 * @author 37362
 */
public class CommandHandler {
    private Game game;
    private List<Player> players;

    public CommandHandler(Game game, List<Player> players) {
        this.game = game;
        this.players = players;
    }

}

