package com.mycompany.client_gomoku.Interfata;

import com.mycompany.client_gomoku.Interfata.Commands.CommandController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GomokuFrame extends JFrame {

    private String player1Name;
    private String player2Name;
    private CircleButton[][] boardButtons;
    private JButton leavebutton;
    private JButton infobutton;
    public boolean isPlayer1Turn;
    private JLabel nameLabel;
    private JLabel timeLabel;
    private JLabel currentLabel;
    private JLabel winsLabel;
    private CommandController controller;

    public GomokuFrame(String name, CommandController controller) {
        player1Name = name;
        player2Name = "Player 2";
        isPlayer1Turn = true;
        this.controller = controller;
        setTitle("Gomoku");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);

        // Panel pentru tabla de Gomoku
        JPanel gomokuPanel = new JPanel(new GridLayout(15, 15));
        gomokuPanel.setSize(600, 600);
        JPanel panel = new JPanel();
        boardButtons = new CircleButton[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                boardButtons[i][j] = new CircleButton();
                boardButtons[i][j].setBackground(new Color(244, 219, 185)); // Culoare bej (RGB: 244, 219, 185)
                boardButtons[i][j].addActionListener(new BoardButtonListener(i, j));
                gomokuPanel.add(boardButtons[i][j]);
            }
        }
        panel.add(gomokuPanel);
        add(panel);

        // Panel pentru informații
        JPanel infoPanel = new JPanel(new GridLayout(2, 5));
        infoPanel.setSize(60, 300);

        nameLabel = new JLabel("Player: " + player1Name);

        timeLabel = new JLabel(controller.getResponse());
        leavebutton = new JButton("Leave this game");
        leavebutton.setSize(60, 20);
        leavebutton.setBackground(new Color(0, 219, 185));
        leavebutton.addActionListener((ActionEvent e) -> {
            WelcomeFrame welcomeFrame;
            welcomeFrame = new WelcomeFrame(name, controller);
            welcomeFrame.setVisible(true);

            dispose(); // Închideți fereastra curentă
        });
        infobutton = new JButton("More info about this game");
        infobutton.setSize(60, 20);
        infobutton.setBackground(new Color(0, 219, 185));
        infobutton.addActionListener((var e) -> {
            JPanel JoinPanel = new JPanel(new BorderLayout());
            controller.setCommand("show");

            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(WelcomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            JPanel namePanel = new JPanel();
            JLabel infoLabel = new JLabel(controller.getResponse());

            JButton joinButton = new JButton("Back to the Game");
            namePanel.add(infoLabel);
            namePanel.add(joinButton, BorderLayout.SOUTH);

            joinButton.addActionListener((ActionEvent e1) -> {
                panel.setVisible(true);
                namePanel.setVisible(false);
            });
            add(namePanel);
            panel.setVisible(false);

            namePanel.setVisible(true);
        });
        //currentLabel = new JLabel("Current turn: " + "TREBUIE citit din server");

        // winsLabel = new JLabel(" Game_id : " + "TREBUIE citit din server");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        infoPanel.add(nameLabel, BorderLayout.NORTH);
        infoPanel.add(timeLabel);
        infoPanel.add(leavebutton);
        infoPanel.add(infobutton);
        add(infoPanel, BorderLayout.SOUTH);
        infoPanel.setVisible(true);

    }

    public void start() throws InterruptedException {
        while (true) {
            int x = controller.getX();
            int y = controller.getY();
            if ((x < 15) && (y < 15)) {

                CircleButton button = boardButtons[x][y];
                if (new Color(244, 219, 185).equals(button.getCircleColor())) {

                    Color circleColor = isPlayer1Turn ? Color.BLACK : Color.WHITE;
                    button.setCircleColor(circleColor);
                    button.setEnabled(false);
                    timeLabel.setText(controller.getResponse());
                    isPlayer1Turn = !isPlayer1Turn;
                }
            }
            Thread.sleep(500);
        }
    }

    private class BoardButtonListener implements ActionListener {

        private int row;
        private int col;

        public BoardButtonListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (isPlayer1Turn) {
                CircleButton button = (CircleButton) e.getSource();
                if (new Color(244, 219, 185).equals(button.getCircleColor())) {
                    Color circleColor = isPlayer1Turn ? Color.BLACK : Color.WHITE;
                    button.setCircleColor(circleColor);
                    button.setEnabled(false);
                    controller.setCommand("move " + row + " " + col);
                    isPlayer1Turn = !isPlayer1Turn;
                    controller.getResponse();

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GomokuFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    timeLabel.setText(controller.getResponse());

                }
            }
        }
    }

}
