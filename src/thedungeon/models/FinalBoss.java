package thedungeon.models;

// @author vanes

import baseThedungeon.models.Lector;
import baseThedungeon.models.Sprite;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import thedungeon.main.Game;
import static utilz.Constants.FinalBossConstants.*;

import utilz.LoadSave;

public class FinalBoss extends Enemy{

    private BufferedImage[] idle;
    private BufferedImage[] attack;
    private BufferedImage[] rayAttack;
    private int aniTick, aniIndex, aniSpeed = 25;
    private int usuAction = IDLE;
    private float xDrawOffset = 21 * Game.SCALE;
    private float yDrawOffset = 4 * Game.SCALE;
    
    private boolean usingRay=false;
    Lector lector;
    
    
    public FinalBoss(float x, float y, int width, int height,int difficulty) throws IOException {
        super(x, y, width, height, 30, 6,  11, difficulty, 80);
        AtaqueLatigo latigo = new AtaqueLatigo(damage, 1.2);
        AtaqueRayoLasser rayo = new AtaqueRayoLasser(damage, 1.2);
        ataques.add(latigo);
        ataques.add(rayo);
        initHitbox(x, y, 20 * Game.SCALE, 27 * Game.SCALE);
        lector = new Lector("finalBoss.txt");
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
        }else if(usingRay){
            g.drawImage(rayAttack[aniIndex], (int) (hitbox.x - xDrawOffset), (int) (hitbox.y - yDrawOffset), width, height, null);
        }else{
             g.drawImage(idle[aniIndex], (int) (hitbox.x - xDrawOffset), (int) (hitbox.y - yDrawOffset), width, height, null);
        }
       
    }
    
    private void updateAnimationTick() {
        aniTick++;
        if(usuAction== ATTACK_1){
            if (aniTick >= aniSpeed) {
                aniTick = 0;
                aniIndex++;
                if (aniIndex >= GetSpriteAmount(ATTACK_1)) {
                    aniIndex = 0;
                    attacking = false;
                }
            }
        }else if(usuAction== ATTACK_2){
            if (aniTick >= aniSpeed) {
                aniTick = 0;
                aniIndex++;
                if (aniIndex >= GetSpriteAmount(ATTACK_2)) {
                    aniIndex = 0;
                    usingRay = false;
                }
            }
        }else{
            if (aniTick >= aniSpeed) {
                aniTick = 0;
                aniIndex++;
                if (aniIndex >= 4) {
                    aniIndex = 0;
                    
                }
            }
        }
        
    }
    
    private void setAnimation() {
        int startAni = usuAction;

        if (attacking) {
            usuAction = ATTACK_1;
        }else if(usingRay){
            usuAction = ATTACK_2;
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
       
        idle = new BufferedImage[4];
        attack= new BufferedImage[13];
        rayAttack= new BufferedImage[10];
        
        for (int i= 0; i< 3; i++){
            int pos=0; 
            for(int j=0; j<texto.size(); j++){
                if(i==0){
                    if(j>=0 && j<4){
                        idle[pos]= LoadSave.GetSpriteAtlas(texto.get(j));
                        pos++;    
                    }else if(j>=4){
                        break;
                    }
                }else if(i==1){
                    if(j>=4 && j<17){
                        attack[pos]= LoadSave.GetSpriteAtlas(texto.get(j));
                        pos++;    
                    }
                }else if(i==2){
                    if(j>=17){
                        rayAttack[pos]= LoadSave.GetSpriteAtlas(texto.get(j));
                        pos++;    
                    }
                }
            }
        }
    }
    
    
    @Override
    public Player atacar(Player player){
        double shieldP = player.getShield();
        double hpP= player.getHp();
        Ataque ataque; 
        int numAtk = (int)(Math.random() * ((ataques.size()-1) - 0 + 1)) + 0;
        ataque= ataques.get(numAtk);//toca ejecutar la animacion de ese ataque
        if(numAtk==0){
            attacking= true;
        }else{
            usingRay=true;
        }
        double daño = ataque.infligirDaño(player);
        System.out.println(daño);
        if(shieldP>1){
            
            shieldP-=daño;
            daño-= shieldP; 
        } if (shieldP<1){
            shieldP=0;
        }
        if(daño>0){
            hpP -= daño; 
            
        }
        player.setHp(hpP);
        player.setShield(shieldP);
        return player;
    }
    
    public boolean isAttacking() {
        return attacking;
    }
    

}
