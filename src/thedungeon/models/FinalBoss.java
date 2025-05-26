package thedungeon.models;

// @author vanes

import baseThedungeon.models.Sprite;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import thedungeon.main.Game;
import static utilz.Constants.FinalBossConstants.*;

import utilz.LoadSave;

public class FinalBoss extends Enemy{

    public FinalBoss(float x, float y, int width, int height,int difficulty) {
        super(x, y, width, height, 300, 20,  15, difficulty);
        AtaqueLatigo latigo = new AtaqueLatigo(damage, 1.5);
        AtaqueRayoLasser rayo = new AtaqueRayoLasser(damage, 1.6);
        ataques.add(latigo);
        ataques.add(rayo);
    }

    

}
