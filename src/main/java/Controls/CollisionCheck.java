package Controls;

import Entity.Entity;
import GUI.GameScene;
import GUI.TileManager;
import GUI.Window;
import Variables.Constant;

import java.awt.*;

public class CollisionCheck {
    TileManager tileM = new TileManager();
    public CollisionCheck() {
    }

    public void checkTile(Entity entity) {
        entity.setEntityInteractionBox(entity);
        int entityLeftCol = entity.InteractionBox.get(3) / (Constant.original_tile_size * Constant.scale);
        int entityRightCol = entity.InteractionBox.get(1) / (Constant.original_tile_size * Constant.scale);
        int entityTopRow = entity.InteractionBox.get(0) / (Constant.original_tile_size * Constant.scale);
        int entityBottomRow = entity.InteractionBox.get(2) / (Constant.original_tile_size * Constant.scale);

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up" -> {
                entityTopRow = (entity.InteractionBox.get(0) - entity.speed) / (Constant.original_tile_size * Constant.scale);
                tileNum1 = tileM.mapTileNum[entityTopRow][entityLeftCol];
                tileNum2 = tileM.mapTileNum[entityTopRow][entityRightCol];
                if (tileM.tiles[tileNum1].collision
                        || tileM.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
            }
            case "down" -> {
                entityBottomRow = (entity.InteractionBox.get(2) + entity.speed) / (Constant.original_tile_size * Constant.scale);
                tileNum1 = tileM.mapTileNum[entityBottomRow][entityLeftCol];
                tileNum2 = tileM.mapTileNum[entityBottomRow][entityRightCol];
                if (tileM.tiles[tileNum1].collision
                        || tileM.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
            }
            case "left" -> {
                entityLeftCol = (entity.InteractionBox.get(3) - entity.speed) / (Constant.original_tile_size * Constant.scale);
                tileNum1 = tileM.mapTileNum[entityTopRow][entityLeftCol];
                tileNum2 = tileM.mapTileNum[entityBottomRow][entityLeftCol];
                if (tileM.tiles[tileNum1].collision
                        || tileM.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
            }
            case "right" -> {
                entityRightCol = (entity.InteractionBox.get(1) + entity.speed) / (Constant.original_tile_size * Constant.scale);
                tileNum1 = tileM.mapTileNum[entityTopRow][entityRightCol];
                tileNum2 = tileM.mapTileNum[entityBottomRow][entityRightCol];
                if (Window.getWindow().tileM.tiles[tileNum1].collision
                        || Window.getWindow().tileM.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
            }
        }
    }
    public void checkMob(Entity entity, Entity entity1) {
        entity.setEntityInteractionBox(entity);
        entity1.setEntityInteractionBox(entity1);
        Rectangle entitySolidBox = new Rectangle(entity.InteractionBox.get(3),
                entity.InteractionBox.get(0),
                entity.solidArea.width,
                entity.solidArea.height);
        Rectangle entity1SolidBox = new Rectangle(entity1.InteractionBox.get(3),
                entity1.InteractionBox.get(0),
                entity1.solidArea.width,
                entity1.solidArea.height);
        boolean intersects = entitySolidBox.intersects(entity1SolidBox);
        if (intersects) {
            entity.collisionOn = true;
            entity.state = 0;
        }
    }
    public int checkObject (Entity entity, Boolean player) {
        int index = 999;
        for (int i = 0; i < GameScene.Object.length; i++){
            if (GameScene.Object[i] != null){
                entity.solidArea.x = entity.x + entity.solidArea.x;
                entity.solidArea.y = entity.y + entity.solidArea.y;

                GameScene.Object[i].solidArea.x = GameScene.Object[i].x + GameScene.Object[i].solidArea.x;
                GameScene.Object[i].solidArea.y = GameScene.Object[i].y + GameScene.Object[i].solidArea.y;

                switch (entity.direction){
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(GameScene.Object[i].solidArea)){
                            if (GameScene.Object[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if (player == true){
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(GameScene.Object[i].solidArea)){
                            if (GameScene.Object[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if (player == true){
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(GameScene.Object[i].solidArea)){
                            if (GameScene.Object[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if (player == true){
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(GameScene.Object[i].solidArea)){
                            if (GameScene.Object[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if (player == true){
                                index = i;
                            }
                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                GameScene.Object[i].solidArea.x = GameScene.Object[i].solidAreaDefaultX;
                GameScene.Object[i].solidArea.y = GameScene.Object[i].solidAreaDefaultY;
            }
        }
        return index;
    }
}
