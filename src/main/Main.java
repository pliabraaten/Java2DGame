package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        // Create game window and settings
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Java Game");

        // Instantiate the game screen
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);  // Null -> centers window to screen
        window.setVisible(true);  // Visibility of the window

        // Starts gameThread
        gamePanel.startGameThread();
    }
}