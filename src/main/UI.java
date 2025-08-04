package main;


import object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

// Handle UI for text messages and icons
public class UI {

    GamePanel gp;
    Font arial_40, arial_80B;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");  // Display 2 decimal places

    // Constructor
    public UI(GamePanel gp) {
        this.gp = gp;

        arial_40 = new Font("Arial", Font.PLAIN, 40);  // Create font outside of game loop
        arial_80B = new Font("Arial", Font.BOLD, 80);
        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;
    }

    public void showMessage(String text) {

        message = text;
        messageOn = true;
    }

    // Within game loop
    public void draw(Graphics2D g2) {

        if(gameFinished == true){

            g2.setFont(arial_40);
            g2.setColor(Color.white);

            String text;
            int textLength;
            int x;
            int y;

            text = "You found the treasure!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();  // Returns length of text
            // Find center of screen
            x = gp.screenWidth/2 - textLength/2;  // Aligns text to center
            y = gp.screenHeight/2 - (gp.tileSize*3);
            g2.drawString(text, x, y);  // Display text

            text = "Your Time is :" + dFormat.format(playTime) + "!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();  // Returns length of text
            // Find center of screen
            x = gp.screenWidth/2 - textLength/2;  // Aligns text to center
            y = gp.screenHeight/2 + (gp.tileSize*4);
            g2.drawString(text, x, y);  // Display text
            g2.setFont(arial_80B);
            g2.setColor(Color.yellow);

            text = "Congratulations!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();  // Returns length of text
            x = gp.screenWidth/2 - textLength/2;  // Aligns text to center
            y = gp.screenHeight/2 + (gp.tileSize*2);
            g2.drawString(text, x, y);  // Display text

            // Stop game
            gp.gameThread = null;

        }
        else {
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
            g2.drawString("x = " + gp.player.hasKey, 74, 65);

            // TIME
            playTime +=(double)1/60;  // Draw method is called 60 times per second
            g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize*11, 65);

            // MESSAGE
            if(messageOn == true) {  // Draw if message

                g2.setFont(g2.getFont().deriveFont(30F));  // Change font size
                g2.drawString(message, gp.tileSize/2, gp.tileSize*5);

                messageCounter++;  // Increment message timer

                if(messageCounter > 120) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
    }
}
