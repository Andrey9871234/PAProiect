/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

/**
 *
 * @author 37362
 */
import java.util.Timer;
import java.util.TimerTask;

public class GameTimer {
    private Timer timer;
    private Player currentPlayer;

    public void startTimer(Player player, int seconds) {
        stopTimer(); // Stop any existing timer
        currentPlayer = player;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handleTimeExpired();
            }
        }, seconds * 1000);
    }

    public void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
            currentPlayer = null;
        }
    }

    private void handleTimeExpired() {
            }
}
