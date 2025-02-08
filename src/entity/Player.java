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

    public final int screenX;  // Player character on the screen (centered)
    public final int screenY;  // Final variables so doesn't change

    // Constructor
    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);  // Screen position is centered in the screen
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);  // Adjustment since pos is based on top LH corner of player

        solidArea = new Rectangle();  // Creates collision box
        solidArea.x = 8;  // Collision box parameters -> smaller than image
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();  // Set player's default position
        getPlayerImage(); // Load player images
    }

    // Set player's default position
    public void setDefaultValues() {
        worldX = gp.tileSize * 23;  // Position on world map (not screen)
        worldY = gp.tileSize * 21;  // Start 22 tiles down on world map
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
    public void update() {  // This method gets called from Game Panel > Game Loop 60 times per second

        // Only updates movement and animation counter while key is pressed
        if (keyH.upPressed == true || keyH.downPressed == true ||
                keyH.leftPressed == true || keyH.rightPressed == true) {

            // TODO: FIX DIAGONAL MOVEMENT BY NORMALIZING THE VECTOR
            // Update direction based on keyboard input
            if (keyH.upPressed == true) {
                direction = "up";  // Set direction
            }
            if (keyH.downPressed == true) {
                direction = "down";
            }
            if (keyH.leftPressed == true) {
                direction = "left";
            }
            if (keyH.rightPressed == true) {
                direction = "right";
            }

            // CHECK FILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);  // Pass in player entity to collision checker

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if(collisionOn == false) {

                switch(direction) {
                    case "up": worldY -= speed;
                        break;
                    case "down": worldY += speed;
                        break;
                    case "left": worldX -= speed;
                        break;
                    case "right": worldX += speed;
                        break;
                }
            }

            // Player animation counter
            spriteCounter++;  // Counter is increased every frame (60 times per second)
            if (spriteCounter > 12) {  // When counter hits x frames, it changes the spriteNum which changes image in draw()
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    // Draw new player position/info
    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        // Pick player image based on direction, from already loaded images from getPlayerImage
        switch (direction) {
            case "up":
                if (spriteNum == 1) {  // Animation is based on spriteNum changing
                    image = up1;
                }
                if (spriteNum ==2) {  // If sprite counter is 2, then use second image for animation
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum ==2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum ==2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum ==2) {
                    image = right2;
                }
                break;
        }

        // Draw player image at coordinates and at scaled size
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }

}
