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
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];  // Instantiate mapTileNum object

        getTileImage();  // Load tile images into tile type array
        loadMap("/maps/map01.txt");  // Load tile map into mapTileNum matrix
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

            while (col < gp.maxScreenCol && row < gp.maxScreenRow) {  // Limited by screen boundary
                // BufferedReader br will read line and put it in line var
                String line = br.readLine();

                while (col < gp.maxScreenCol) {  // col acts as tile counter for number of tiles in screen width
                    // Split up line by space delimiter and put into numbers array
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);  // Convert string to int

                    mapTileNum[col][row] = num;  // Set the split and newly converted int into the tile map matrix
                    col++;  // Move to next tile
                }
                if (col == gp.maxScreenCol) {  // When end of screen is reached in tile count

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

       int col = 0;  // To compare with maxScreenCol: width of screen by tile size
       int row = 0;
       int x = 0;
       int y = 0;

       while (col < gp.maxScreenCol && row < gp.maxScreenRow) {  // While col and row are within screen

           int tileNum = mapTileNum[col][row];  // Extract the tile type number stored at mapTileNum[0][0] to start

           g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);  // Draws first tile at 0, 0
           col++;  // Increment map matrix width value
           x += gp.tileSize;  // Move x to next tile spot

           if (col == gp.maxScreenCol) {  // When col reaches far right screen
               col = 0;  // Reset
               x = 0;  // Reset
               row++;  // Increment map matrix y value
               y += gp.tileSize;  // Move y down one tile spot
           }
       }
    }
}
