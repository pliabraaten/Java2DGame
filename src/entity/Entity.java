package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

// Parent class for player, characters, enemies
public class Entity {

    public int worldX, worldY;  // Position on the world map (not screen)
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    // Collision box
    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;

}
