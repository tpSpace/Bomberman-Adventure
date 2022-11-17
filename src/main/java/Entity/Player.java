package Entity;

import Controls.keyHandler;
import Variables.Constant;
import Controls.collisionCheck;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    keyHandler keyH;
    collisionCheck cCheck = new collisionCheck();

    public Player(keyHandler keyH) {
        this.keyH = keyH;
        solidArea = new Rectangle();
        solidArea.x = 4;
        solidArea.y = 16;
        solidArea.width = 36;
        solidArea.height = 32;
        setDefault();
        getPlayerImage();
    }

    @Override
    public void setDefault() {
        x = 48;
        y = 32;
        speed = 2;
        direction = "down";
        state = 1;
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/Player/player_up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/Player/player_up2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/Player/player_up3.png"));
            up4 = ImageIO.read(getClass().getResourceAsStream("/Player/player_up4.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/Player/player_down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/Player/player_down2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/Player/player_down3.png"));
            down4 = ImageIO.read(getClass().getResourceAsStream("/Player/player_down4.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/Player/player_left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/Player/player_left2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/Player/player_left3.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/Player/player_left4.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/Player/player_right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/Player/player_right2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/Player/player_right3.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/Player/player_right4.png"));
            die1 = ImageIO.read(getClass().getResourceAsStream("/Player/player_die1.png"));
            die2 = ImageIO.read(getClass().getResourceAsStream("/Player/player_die2.png"));
            die3 = ImageIO.read(getClass().getResourceAsStream("/Player/player_die3.png"));
            die4 = ImageIO.read(getClass().getResourceAsStream("/Player/player_die4.png"));
            die5 = ImageIO.read(getClass().getResourceAsStream("/Player/player_die5.png"));
            die6 = ImageIO.read(getClass().getResourceAsStream("/Player/player_die6.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(double dt) {
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            if (keyH.upPressed) {
                direction = "up";
            } else if (keyH.downPressed) {
                direction = "down";
            } else if (keyH.leftPressed) {
                direction = "left";
            } else {
                direction = "right";
            }

            collisionOn = false;
            cCheck.checkTile(this);

            if (!collisionOn) {
                switch (direction) {
                    case "up" -> y -= speed;
                    case "down" -> y += speed;
                    case "left" -> x -= speed;
                    case "right" -> x += speed;
                }
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
    }
    @Override
    public void draw(Graphics2D g2) {

        BufferedImage img = null;
        switch (direction) {
            case "up" -> img = getBufferedImage(up1, up2, up3, up4);
            case "down" -> img = getBufferedImage(down1, down2, down3, down4);
            case "left" -> img = getBufferedImage(left1, left2, left3, left4);
            case "right" -> img = getBufferedImage(right1, right2, right3, right4);
        }
        g2.drawImage(img, x, y, Constant.original_tile_size * Constant.scale,
                Constant.original_tile_size * Constant.scale, null);
    }

    private BufferedImage getBufferedImage(BufferedImage img1,
                                           BufferedImage img2,
                                           BufferedImage img3,
                                           BufferedImage img4) {
        if (spriteNum == 1) {
            return img1;
        }
        if (spriteNum == 2) {
            return img2;
        }
        if (spriteNum == 3) {
            return img3;
        }
        if (spriteNum == 4) {
            return img4;
        }
        return null;
    }
}
