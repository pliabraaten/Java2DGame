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

    // Check if player is hitting any objects, if yes, return index of the object
    public int checkObject(Entity entity, boolean player) {

        int index = 999;

        // Scan object array
        for (int i = 0; i < gp.obj.length; i++) {

            if (gp.obj[i] != null) {

                // Get entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                // Get the object's solid area position
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                // Simulate entity's movement and check where it will be after it moved
                switch (entity.direction) {
                case "up":
                    entity.solidArea.y -= entity.speed;
                    // Check if entity's movement intersects with the object area
                    if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                        // Check if object is solid, if so turn collision on
                        if(gp.obj[i].collision == true) {
                            entity.collisionOn = true;
                        }
                        // Return index for object array if player, otherwise (NPC, enemy) do nothing
                        if(player == true) {
                            index = i;
                        }
                    }
                    break;
                case "down":
                    entity.solidArea.y += entity.speed;
                    if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                        if(gp.obj[i].collision == true) {
                            entity.collisionOn = true;
                        }
                        if(player == true) {
                            index = i;
                        }
                    }
                    break;
                case "left":
                    entity.solidArea.x -= entity.speed;
                    if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                        if(gp.obj[i].collision == true) {
                            entity.collisionOn = true;
                        }
                        if(player == true) {
                            index = i;
                        }
                    }
                    break;
                case "right":
                    entity.solidArea.x += entity.speed;
                    if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                        if(gp.obj[i].collision == true) {
                            entity.collisionOn = true;
                        }
                        if(player == true) {
                            index = i;
                        }
                    }
                    break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }
        }

        return index;
    }
}
