package com.mycompany.client_gomoku.Interfata;

import com.mycompany.client_gomoku.Interfata.Commands.CommandController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainFrame extends JFrame {
    private JTextField nameField;
    private CommandController controller;
    public MainFrame(CommandController controller) {
        setTitle("Gomoku");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setSize(1200, 700);
        setLocationRelativeTo(null);

        // Panel pentru ecranul de start
        JPanel startPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Welcome to Gomoku");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        startPanel.add(titleLabel, BorderLayout.NORTH);

        // Panel pentru introducerea numelui
        JPanel namePanel = new JPanel();
        JLabel nameLabel = new JLabel("Enter your name:");
        nameField = new JTextField(15);
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new SubmitButtonListener());
        namePanel.add(nameLabel);
        namePanel.add(nameField);
        namePanel.add(submitButton);
        startPanel.add(namePanel, BorderLayout.CENTER);

        add(startPanel);
         this.controller = controller;
    }

    public void start(MainFrame mainFrame) {
        SwingUtilities.invokeLater(() -> {
            mainFrame.setVisible(true);
        });
    }

    private class SubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            controller.setCommand("register "+name);
            // Creați o nouă fereastră pentru ecranul de bun venit
            WelcomeFrame welcomeFrame;
            welcomeFrame = new WelcomeFrame(name, controller);
            welcomeFrame.setVisible(true);

            // Închideți fereastra curentă
            dispose();
        }
    }
}

