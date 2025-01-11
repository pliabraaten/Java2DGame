package main;

import entity.Player;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final int originalTileSize = 16;  // 16x16 tiles for characters (pixel art)
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;  // 48x48 tile ; Allows for characters to be larger at higher resolutions
    final int maxScreenCol = 16;  // 4x3 ratio of screen
    final int maxScreenRow = 12;  // 12 tiles wide
    final int screenWidth = tileSize * maxScreenCol;  // 768 pixels
    final int screenHeight = tileSize * maxScreenRow;  // 576 pixels

    // FPS
    int FPS = 60;

    // Instantiations
    KeyHandler keyH = new KeyHandler();  // Keyboard input
    Thread gameThread;  // Game clock
    Player player = new Player(this, keyH); // Player object (this => the GamePanel class)

    // Moved into PLayer class
//    // Set Player's default position
//    int playerX = 100;
//    int playerY = 100;
//    int playerSpeed = 4;

    // Game Panel Constructor
    public GamePanel () {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        // Improves rendering performance
        this.setDoubleBuffered(true);  // When true, allows for offscreen painting buffer
        this.addKeyListener(keyH);  //
        this.setFocusable(true);
    }

    // Automatically calls run method
    public void startGameThread() {
        gameThread = new Thread(this);  // this = GamePanel object
        gameThread.start();
    }

    // GAME LOOP: DELTA METHOD
    @Override
    public void run() {  // Called automatically by Thread

        double drawInterval = 1000000000/FPS;  // 1 second divided by FPS (60); draws screen every 0.01666 seconds
        double delta = 0;  // Initialize
        long lastTime = System.nanoTime();  // Initialize lastTime as current time
        long currentTime;

        while(gameThread != null) {  // As long as Thread exists

            currentTime = System.nanoTime();  // Find current time

            delta += (currentTime - lastTime) / drawInterval;  // Delta = duration divided by draw interval

            lastTime = currentTime;  // Update lastTime for next duration interval calculation

            if (delta >= 1) {  // If delta is more than 1, then update info and draw new info to screen

                // 1 UPDATE: update information like character positions
                update();

                // 2 DRAW: draw the screen with the updated information
                repaint();  // repaint calls the paintComponent standard Java method

                delta--;  // ?Resets delta?
            }
        }
    }

    // UPDATE
    public void update() {

        player.update();  // Run Player's info update method


    }

    // DRAW
    public void paintComponent(Graphics g) {  // Graphics is a standard method in Java
        // Use Java's Graphics2D class
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        player.draw(g2);  // Run Player's draw method to paint the updated info

        g2.dispose();  // Dispose graphics context

    }
}