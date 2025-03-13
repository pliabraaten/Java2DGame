package main;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final int originalTileSize = 16;  // 16x16 tiles for characters (pixel art)
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;  // 48x48 tile ; Allows for characters to be larger at higher resolutions
    public final int maxScreenCol = 16;  // 4x3 ratio of screen
    public final int maxScreenRow = 12;  // 12 tiles wide
    public final int screenWidth = tileSize * maxScreenCol;  // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow;  // 576 pixels

    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    // FPS
    int FPS = 60;

    // SYSTEM
    TileManager tileM = new TileManager(this);  // Processes and creates tile map matrix when constructed
    KeyHandler keyH = new KeyHandler();  // Keyboard input
    Sound sound = new Sound();  // Sounds
    public CollisionChecker cChecker = new CollisionChecker(this);  // Instantiate collisionChecker and pass in gp
    public AssetSetter aSetter = new AssetSetter(this);  // Instantiate assetsetter passing in gp
    Thread gameThread;  // Game clock

    // ENTITY AND OBJECT
    public Player player = new Player(this, keyH); // Player object (this => the GamePanel class)
    public SuperObject obj[] = new SuperObject[10];  // Create space for 10 objects to be displayed at the same time



    // Game Panel Constructor
    public GamePanel () {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        // Improves rendering performance
        this.setDoubleBuffered(true);  // When true, allows for offscreen painting buffer
        this.addKeyListener(keyH);  //
        this.setFocusable(true);
    }

    // Run method to set and place objects
    public void setupGame() {

        aSetter.setObject();

        playMusic(0);  // Start music

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
                update();  // Calls update 60 times per second

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

        // TILE
        tileM.draw(g2);  // Draws tile map layer before player

        // OBJECT
        for(int i = 0; i < obj.length; i++) {  // Scan object array
            if(obj[i] != null) {  // If object exists, run draw method
                obj[i].draw(g2, this);
            }
        }

        // PLAYER
        player.draw(g2);  // Run Player's draw method to paint the updated info

        g2.dispose();  // Dispose graphics context
    }

    // MUSIC
    public void playMusic(int i) {

        // Methods from sound class
        sound.setFile(i);  // Set music file
        sound.play();  // Play music
        sound.loop();  // Loop music
    }

    public void stopMusic() {

        sound.stop();
    }

    // SOUND EFFECTS
    public void playSE(int i) {

        sound.setFile(i);
        sound.play();
    }
}