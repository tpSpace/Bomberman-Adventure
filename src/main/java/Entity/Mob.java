package Entity;

import Controls.CollisionCheck;
import Variables.Constant;
import GUI.GameScene;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Mob extends Entity {
    private final Random rand = new Random();
    CollisionCheck cCheck = new CollisionCheck();
    String[] dir = {"down", "up", "right", "left"};

    public Mob(int x, int y) {
        this.x = x;
        this.y = y;

        solidArea = new Rectangle();
        solidArea.x = 4;
        solidArea.y = 16;
        solidArea.width = 36;
        solidArea.height = 32;

        setDefault();
        getMobImage();
    }

    public void setDefault() {
        speed = 1;
        this.direction = "down";
    }

    public void update(double dt) {
        collisionOn = false;
        cCheck.checkTile(this);
        cCheck.checkBomb(GameScene.getBombList(), this);

        if (!collisionOn) {
            switch (direction) {
                case "up" -> y -= speed;
                case "down" -> y += speed;
                case "left" -> x -= speed;
                case "right" -> x += speed;
            }

        } else {
            this.direction = dir[rand.nextInt(4)];
        }

        spriteCounter++;
        if (spriteCounter > 8) {
            if (spriteNum != 4) {
                spriteNum++;
            } else
                spriteNum = 1;
            spriteCounter = 0;
        }
    }

    public void getMobImage() {
        try {
            for (int i = 0; i < 4; i++) {
                up[i] = ImageIO.read(Objects.requireNonNull(getClass()
                        .getResourceAsStream("/Mob/MobUpRight" + (i + 1) + ".png")));
                down[i] = ImageIO.read(Objects.requireNonNull(getClass()
                        .getResourceAsStream("/Mob/MobDownLeft" + (i + 1) + ".png")));
                left[i] = ImageIO.read(Objects.requireNonNull(getClass()
                        .getResourceAsStream("/Mob/MobDownLeft" + (i + 1) + ".png")));
                right[i] = ImageIO.read(Objects.requireNonNull(getClass()
                        .getResourceAsStream("/Mob/MobUpRight" + (i + 1) + ".png")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage img = getEntityImage();

        int screenX = x - GameScene.getPlayer().x + Constant.PLAYER_SCREEN_X;
        int screenY = y - GameScene.getPlayer().y + Constant.PLAYER_SCREEN_Y;

        if (x + Constant.TILE_SIZE > GameScene.getPlayer().x - Constant.PLAYER_SCREEN_X &&
                x - Constant.TILE_SIZE < GameScene.getPlayer().x + Constant.PLAYER_SCREEN_X &&
                y + Constant.TILE_SIZE > GameScene.getPlayer().y - Constant.PLAYER_SCREEN_Y &&
                y - Constant.TILE_SIZE < GameScene.getPlayer().y + Constant.PLAYER_SCREEN_Y)
        {
            g2.drawImage(img, screenX, screenY, Constant.ORIGINAL_TILE_SIZE * Constant.SCALE,
                    Constant.ORIGINAL_TILE_SIZE * Constant.SCALE, null);
        }
    }
}