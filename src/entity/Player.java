package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    // Constructor
    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();  // Set player's default position
        getPlayerImage(); // Load player images
    }

    // Set player's default position
    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction ="down";
    }

    // Load player images
    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    // Updates player position and direction based on keyboard inputs
    public void update() {
        if (keyH.upPressed == true) {  // Move up if W is pressed
            direction = "up";  // Set direction
            y -= speed;
        }
        if (keyH.downPressed == true) {
            direction = "down";
            y += speed;
        }
        if (keyH.leftPressed == true) {
            direction = "left";
            x -= speed;
        }
        if (keyH.rightPressed == true) {
            direction = "right";
            x += speed;
        }
    }

    // Draw new player position/info
    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        // Pick player image based on direction, from already loaded images from getPlayerImage
        switch (direction) {
            case "up":
                image = up1;
                break;
            case "down":
                image = down1;
                break;
            case "left":
                image = left1;
                break;
            case "right":
                image = right1;
                break;
        }

        // Draw player image at coordinates and at scaled size
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }

}
