package Controls;

import Entity.*;
import GUI.TileManager;
import Variables.Constant;
import GUI.Window;
public class CollisionCheck {
    TileManager tileM = new TileManager();
    public CollisionCheck() {

    }

    public void checkTile(Entity entity) {
        entity.setEntityInteractionBox(entity);
        int entityLeftCol = entity.InteractionBox.get(3) / (Constant.original_tile_size * Constant.scale);
        int entityRightCol = entity.InteractionBox.get(1)/ (Constant.original_tile_size * Constant.scale);
        int entityTopRow = entity.InteractionBox.get(0)/ (Constant.original_tile_size * Constant.scale);
        int entityBottomRow = entity.InteractionBox.get(2)/ (Constant.original_tile_size * Constant.scale);

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

}