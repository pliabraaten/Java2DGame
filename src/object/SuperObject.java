package object;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {

    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;


    // Draw the objects to map
    public void draw(Graphics2D g2, GamePanel gp) {

        // Tracks where on the screen the objects are drawn (changing based on player position)
        int screenX = worldX - gp.player.worldX + gp.player.screenX;  // Offset player position since it is always screen center
        // Objects's world position - player's world position + player's screen position
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        // Only draws/renders objects that are in the screenview with these boundaries
        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&  // Object position greater than world position - screen position (left)
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&  // world -/+ object size to expand boundary a bit more than screen
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&  // Up boundary
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {  // Down boundary

            // Draws object if in boundary
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
}
