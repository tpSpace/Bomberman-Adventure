package GUI;

import Entity.Mob;
import Objects.OBJ_ExtraBomb;
import Objects.OBJ_SpeedIncrease;
import Variables.Constant;

public class AssetSetter {
    GameScene gameScene;

    public AssetSetter(GameScene gameScene) {
        this.gameScene = gameScene;
    }

    public void setMob() {
        GameScene.mobList.removeAll(GameScene.mobList);//reset the creation of mobs
        GameScene.mobList.add(0, new Mob(18 * Constant.TILE_SIZE,19 * Constant.TILE_SIZE));
        GameScene.mobList.add(1, new Mob(19 * Constant.TILE_SIZE, 22 * Constant.TILE_SIZE));
    }

    public void setItems() {
        GameScene.Object[0] = new OBJ_ExtraBomb();
        GameScene.Object[0].x = 19 * Constant.TILE_SIZE;
        GameScene.Object[0].y = 20 * Constant.TILE_SIZE;

        GameScene.Object[1] = new OBJ_SpeedIncrease();
        GameScene.Object[1].x = 22 * Constant.TILE_SIZE;
        GameScene.Object[1].y = 30 * Constant.TILE_SIZE;
    }
}
