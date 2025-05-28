/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thedungeon.models;

import baseThedungeon.models.Lector;
import baseThedungeon.models.Sprite;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import thedungeon.main.Game;
import thedungeon.models.AtaqueHoja;
import thedungeon.models.Enemy;
import static utilz.Constants.Usu.*;
import utilz.LoadSave;
/**
 *
 * @author Nico
 */
public class Usu extends Enemy{
    
    private BufferedImage[] idle;
    private BufferedImage[] attack;
    private int aniTick, aniIndex, aniSpeed = 25;
    private int usuAction = IDLE;
    private float xDrawOffset = 21 * Game.SCALE;
    private float yDrawOffset = 4 * Game.SCALE;
    
    Lector lector; 
    
    public Usu(float x, float y, int width, int height, int difficulty) throws FileNotFoundException, IOException {
        super(x, y, width, height, 12, 4, 7, difficulty, 25);
        AtaqueHoja hoja;
        hoja = new AtaqueHoja(getDamage() , 1.8);
        initHitbox(x, y, 20 * Game.SCALE, 27 * Game.SCALE);
        ataques.add(hoja);
        lector = new Lector("usu.txt");
        loadAnimations();
    }
    
    @Override
    public void update() {
        updateAnimationTick();
        setAnimation();
    }
    @Override
    public void render(Graphics g) {
        if (attacking){
            g.drawImage(attack[aniIndex], (int) (hitbox.x - xDrawOffset), (int) (hitbox.y - yDrawOffset), width, height, null);
        }else{
             g.drawImage(idle[aniIndex], (int) (hitbox.x - xDrawOffset), (int) (hitbox.y - yDrawOffset), width, height, null);
        }
       
    }
    
    private void updateAnimationTick() {
        aniTick++;
        if(usuAction== ATTACK){
            if (aniTick >= aniSpeed) {
                aniTick = 0;
                aniIndex++;
                if (aniIndex >= GetSpriteAmount(ATTACK)) {
                    aniIndex = 0;
                    attacking = false;
                }
        }
        }else{
            if (aniTick >= aniSpeed) {
                aniTick = 0;
                aniIndex++;
                if (aniIndex >= GetSpriteAmount(IDLE)) {
                    aniIndex = 0;
                    
                }
            }
        }
        
    }
    
    private void setAnimation() {
        int startAni = usuAction;

        if (attacking) {
            usuAction = ATTACK;
        }else{
            usuAction = IDLE;
        }

        if (startAni != usuAction) {
            resetAniTick();
        }
    }
    
    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }
    
    private void loadAnimations() throws IOException {
        
        ArrayList<String> texto = lector.devolverTexto();
       
        idle = new BufferedImage[2];
        attack= new BufferedImage[6];
        
        for (int i= 0; i< 2; i++){
            int pos=0; 
            for(int j=0; j<texto.size(); j++){
                if(i==0){
                    if(j>=0 && j<2){
                        idle[pos]= LoadSave.GetSpriteAtlas(texto.get(j));
                        pos++;    
                    }else if(j>=2){
                        break;
                    }
                }else if(i==1){
                    if(j>=2 && j<8){
                        attack[pos]= LoadSave.GetSpriteAtlas(texto.get(j));
                        pos++;    
                    }
                }
            }
           
        }
    }

    public boolean isAttacking() {
        return attacking;
    }
    
    
}