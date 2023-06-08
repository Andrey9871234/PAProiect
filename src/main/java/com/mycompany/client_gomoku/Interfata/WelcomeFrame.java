package com.mycompany.client_gomoku.Interfata;

import com.mycompany.client_gomoku.Interfata.Commands.CommandController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WelcomeFrame extends JFrame {

    private String playerName;
    private CommandController controller;
    private JTextField nameField = new JTextField(15);

    public WelcomeFrame(String name, CommandController controller) {
        playerName = name;
        this.controller = controller;
        setTitle("Gomoku");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Panel pentru ecranul de bun venit
        JPanel welcomePanel = new JPanel(new BorderLayout());
        JLabel welcomeLabel = new JLabel("Welcome, " + playerName + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomePanel.add(welcomeLabel, BorderLayout.NORTH);

        // Panel pentru meniul de butoane
        JPanel buttonPanel = new JPanel(new GridLayout(3, 2));
        JButton createGameButton = new JButton("Create Game");
        JButton joinGameButton = new JButton("Join Game");
        JButton createContestButton = new JButton("Create Contest");
        JButton joinContestButton = new JButton("Join Contest");
        JButton matchHistoryButton = new JButton("Match History");
        JButton quitGameButton = new JButton("Quit Game");
        quitGameButton.addActionListener((ActionEvent e) -> {
            // Închideți complet aplicația când se apasă butonul "Quit Game"
            System.exit(0);
        });
        createGameButton.addActionListener((ActionEvent e) -> {
            // Deschideți o nouă fereastră pentru jocul Gomoku
            controller.setCommand("create ");
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(WelcomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            GomokuFrame gomokuFrame = new GomokuFrame(playerName, controller);
            gomokuFrame.setVisible(true);
            dispose();
             Thread thread;
            thread = new Thread(() -> {
                try {
                    gomokuFrame.start();
                } catch (InterruptedException ex) {
                    Logger.getLogger(WelcomeFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

        thread.start();
        });
        joinGameButton.addActionListener((ActionEvent e) -> {
            // Deschideți o nouă fereastră pentru jocul Gomoku
            JPanel JoinPanel = new JPanel(new BorderLayout());

            JPanel namePanel = new JPanel();
            JLabel nameLabel = new JLabel("which game do you want to join ?");

            JButton joinButton = new JButton("Join Game");
            joinButton.addActionListener(new JoinButtonListener());
            namePanel.add(nameLabel);
            namePanel.add(nameField);
            namePanel.add(joinButton,  BorderLayout.CENTER);
            add(namePanel);
            welcomePanel.setVisible(false);
             
        });
        buttonPanel.add(createGameButton);
        buttonPanel.add(joinGameButton);
        buttonPanel.add(createContestButton);
        buttonPanel.add(joinContestButton);
        buttonPanel.add(matchHistoryButton);
        buttonPanel.add(quitGameButton);
        welcomePanel.add(buttonPanel, BorderLayout.CENTER);

        add(welcomePanel);
    }

    private class JoinButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            dispose();
            controller.setCommand("join "+ name);
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(WelcomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            GomokuFrame gomokuFrame = new GomokuFrame(playerName, controller);
            gomokuFrame.isPlayer1Turn=false;
            gomokuFrame.setVisible(true);
             Thread thread;
            thread = new Thread(() -> {
                try {
                    gomokuFrame.start();
                } catch (InterruptedException ex) {
                    Logger.getLogger(WelcomeFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

        thread.start();
                      
        }
    }
}
