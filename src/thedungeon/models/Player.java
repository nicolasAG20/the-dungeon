package thedungeon.models;

// @author vanes

import baseThedungeon.models.Sprite;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import utilz.Constants.PlayerConstants.*;
import utilz.LoadSave;
import thedungeon.main.Game;
import static utilz.Constants.PlayerConstants.*;

public class Player extends Sprite {
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 25;
    private int playerAction = IDLE;
    private boolean attacking = false;
    private float xDrawOffset = 21 * Game.SCALE;
    private float yDrawOffset = 4 * Game.SCALE;

    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnimations();
        initHitbox(x, y, 20 * Game.SCALE, 27 * Game.SCALE);
    }

    public void update() {
        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics g) {
        g.drawImage(animations[playerAction][aniIndex], (int) (hitbox.x - xDrawOffset), (int) (hitbox.y - yDrawOffset), width, height, null);
    }

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(playerAction)) {
                aniIndex = 0;
                attacking = false;
            }
        }
    }

    private void setAnimation() {
        int startAni = playerAction;

        if (attacking) {
            playerAction = ATTACK;
        } else {
            playerAction = IDLE;
        }

        if (startAni != playerAction) {
            resetAniTick();
        }
    }

    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    private void loadAnimations() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER);
        int totalActions = 4; // Número total de acciones
        animations = new BufferedImage[totalActions][];
        int spriteWidth = 80;
        int spriteHeight = 75;
        
        for (int action = 0; action < totalActions; action++) {
            int spriteAmount = GetSpriteAmount(action);
            animations[action] = new BufferedImage[spriteAmount];
            for (int i = 0; i < spriteAmount; i++) {
                int x = i * spriteWidth;
                int y = action * spriteHeight;
                
                if (x + spriteWidth <= img.getWidth() && y + spriteHeight <= img.getHeight()) {
                    animations[action][i] = img.getSubimage(x, y, spriteWidth, spriteHeight);
                } else {
                    System.out.println("Subimage out of bounds for action: " + action + ", index: " + i);
                }
            }
        }  
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }
}

