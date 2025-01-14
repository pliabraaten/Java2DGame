package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gp;
    Tile[] tile;

    int mapTileNum[][];  // Define tile map matrix

    // Process tiles
    public TileManager(GamePanel gp) {

        this.gp = gp;

        tile = new Tile[10];  // Create array of tiles
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];  // Instantiate mapTileNum object

        getTileImage();  // Load tile images into tile type array
        loadMap("/maps/world01.txt");  // Load tile map into mapTileNum matrix
    }

    // Load tile images
    public void getTileImage() {

        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass01.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water01.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));

        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    // Create Tile Map using txt map file
    public void loadMap(String filePath) {  // Passes txt map file into method
        // Import Tile Map text file
        try {
            InputStream is = getClass().getResourceAsStream(filePath);  // Import text file with InputStream
            BufferedReader br = new BufferedReader(new InputStreamReader(is));  // Read content with BufferedReader

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {  // Limited by world map boundary
                // BufferedReader br will read line and put it in line var
                String line = br.readLine();

                while (col < gp.maxWorldCol) {  // col acts as tile counter for number of tiles in world map width
                    // Split up line by space delimiter and put into numbers array
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);  // Convert string to int

                    mapTileNum[col][row] = num;  // Set the split and newly converted int into the tile map matrix
                    col++;  // Move to next tile
                }
                if (col == gp.maxWorldCol) {  // When end of world map is reached in tile count

                    col = 0;  // Reset
                    row ++;  // Move row counter for next line in text map file
                }
            }
            br.close();  // Close BufferedReader
        }catch(Exception e){

        }
    }

    // Draw tiles
    public void draw(Graphics2D g2) {

       int worldCol = 0;  // To compare with maxWorldCol: width of world map by tile size
       int worldRow = 0;


       while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {  // While col and row are within world map

           int tileNum = mapTileNum[worldCol][worldRow];  // Extract the tile type number stored at mapTileNum[0][0]

           // Tracks where tiles are on the world map (fixed)
           int worldX = worldCol * gp.tileSize;
           int worldY = worldRow * gp.tileSize;

           // Tracks where on the screen the tiles are drawn (changing based on player position)
           int screenX = worldX - gp.player.worldX + gp.player.screenX;  // Offset player position since it is always screen center
           // Tile's world position - player's world position + player's screen position
           int screenY = worldY - gp.player.worldY + gp.player.screenY;

           // Only draws/renders tiles that are in the screenview with these boundaries
           if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&  // Tile position greater than world position - screen position (left)
               worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&  // world -/+ tile size to expand boundary a bit more than screen
               worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&  // Up boundary
               worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {  // Down boundary

               // Draws tile if in boundary
               g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
           }

           worldCol++;  // Increment map matrix width value to draw next tile

           if (worldCol == gp.maxWorldCol) {  // When col reaches far right of world map
               worldCol = 0;  // Reset
               worldRow++;  // Increment map matrix y value
           }
       }
    }
}
