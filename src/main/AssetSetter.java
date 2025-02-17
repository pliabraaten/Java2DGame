package main;

import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter {

    GamePanel gp;

    // Constructor
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    // Set objects and place on the map
    public void setObject() {

        gp.obj[0] = new OBJ_Key();  // Set key as first object
        gp.obj[0].worldX = 23 * gp.tileSize;  // Set coordinates for key
        gp.obj[0].worldY = 7 * gp.tileSize;

        gp.obj[1] = new OBJ_Key();  // Set another key as second object
        gp.obj[1].worldX = 23 * gp.tileSize;  // Set coordinates for key
        gp.obj[1].worldY = 40 * gp.tileSize;

        gp.obj[2] = new OBJ_Key();  // Set another key as object
        gp.obj[2].worldX = 38 * gp.tileSize;  // Set coordinates for key
        gp.obj[2].worldY = 8 * gp.tileSize;

        gp.obj[3] = new OBJ_Door();  // Set Door as object
        gp.obj[3].worldX = 10 * gp.tileSize;  // Set coordinates for key
        gp.obj[3].worldY = 11 * gp.tileSize;

        gp.obj[4] = new OBJ_Door();  // Set Door as object
        gp.obj[4].worldX = 8 * gp.tileSize;  // Set coordinates for key
        gp.obj[4].worldY = 28 * gp.tileSize;

        gp.obj[5] = new OBJ_Door();  // Set Door as object
        gp.obj[5].worldX = 12 * gp.tileSize;  // Set coordinates for key
        gp.obj[5].worldY = 22 * gp.tileSize;

        gp.obj[6] = new OBJ_Chest();  // Set Chest as object
        gp.obj[6].worldX = 10 * gp.tileSize;  // Set coordinates for key
        gp.obj[6].worldY = 7 * gp.tileSize;

    }
}
