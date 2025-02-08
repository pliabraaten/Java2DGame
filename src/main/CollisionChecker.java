package main;

import entity.Entity;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    // Checks for collision on all entities
    public void checkTile(Entity entity) {

        // Check if entity is hitting solid tile

        // Find collision box borders
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;

        int tileNum1, tileNum2;  // Only need to check two tiles

        switch(entity.direction) {
            case "up":
                // Check tile player is trying to step in by subtracting
                entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;  // Subtract player speed
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];  // Find the world tiles moving into
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                // Check if the attempted tiles are solid (collision)
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;  // Can't move through solid tiles
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;  // Subtract player speed
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];  // Find the world tiles moving into
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                // Check if the attempted tiles are solid (collision)
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;  // Can't move through solid tiles
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;  // Subtract player speed
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];  // Find the world tiles moving into
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                // Check if the attempted tiles are solid (collision)
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;  // Can't move through solid tiles
                }
                break;
            case "right":
                // Check tile player is trying to step in by subtracting
                entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;  // Subtract player speed
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];  // Find the world tiles moving into
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                // Check if the attempted tiles are solid (collision)
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;  // Can't move through solid tiles
                }
                break;
        }


    }
}
