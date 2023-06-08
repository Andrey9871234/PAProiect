/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.client_gomoku.Interfata;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JButton;

/**
 *
 * @author 37362
 */
 class CircleButton extends JButton {
        private Color circleColor;

        public CircleButton() {
            setPreferredSize(new Dimension(40, 40));
            setFocusPainted(false);
            setFont(new Font("Arial", Font.BOLD, 40));
            circleColor = new Color(244, 219, 185); // Culoare bej (RGB: 244, 219, 185)
        }

        public void setCircleColor(Color color) {
            circleColor = color;
            repaint(); // Repaint pentru a actualiza culoarea
        }

        public Color getCircleColor() {
            return circleColor;
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(circleColor);
            g.fillOval(5, 5, getWidth() - 10, getHeight() - 10);
        }
 }