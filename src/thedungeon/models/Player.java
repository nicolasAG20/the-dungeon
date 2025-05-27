package thedungeon.models;

// @author vanes

import baseThedungeon.models.Lector;
import baseThedungeon.models.Sprite;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.ImageIcon;
import utilz.Constants.PlayerConstants.*;
import utilz.LoadSave;
import thedungeon.main.Game;
import static utilz.Constants.PlayerConstants.*;


public class Player extends Sprite implements KeyListener{
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 25;
    private int playerAction = IDLE;
    private boolean attacking = false;
    private boolean defending = false;
    private boolean amagando = false; 
    private float xDrawOffset = 21 * Game.SCALE;
    private float yDrawOffset = 4 * Game.SCALE;
    
    private Lector lector=new Lector("ubicaciones.txt"); 
    
    private BufferedImage[] idle;
    private BufferedImage[] attack;
    private BufferedImage[] defend;
    private BufferedImage[] magic;
    private double hp; 
    private int defense; 
    private int atk; 
    private double shield=0; 
    private AtaqueEspadazo espadazo; 
    private Ataque ataqueElectivo; 
    
    
    public Player(float x, float y, int width, int height) throws IOException {
        super(x, y, width, height);
        loadAnimations();
        initHitbox(x, y, 20 * Game.SCALE, 27 * Game.SCALE);
        this.lector= new Lector("ubicaciones.txt");
        hp= 25;
        defense= 6;
        atk= 8;
        espadazo = new AtaqueEspadazo(atk , 1.5);
        
    }

    public void update() {
        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics g) {
        if (attacking){
            g.drawImage(attack[aniIndex], (int) (hitbox.x - xDrawOffset), (int) (hitbox.y - yDrawOffset), width, height, null);
        }else if(defending){
               g.drawImage(defend[aniIndex], (int) (hitbox.x - xDrawOffset), (int) (hitbox.y - yDrawOffset), width, height, null); 
        }else if(amagando){
            g.drawImage(magic[aniIndex], (int) (hitbox.x - xDrawOffset), (int) (hitbox.y - yDrawOffset), width, height, null);
        }else{
             g.drawImage(idle[aniIndex], (int) (hitbox.x - xDrawOffset), (int) (hitbox.y - yDrawOffset), width, height, null);
        }
       
    }

    private void updateAnimationTick() {
        aniTick++;
        if(playerAction== ATTACK){
            if (aniTick >= aniSpeed) {
                aniTick = 0;
                aniIndex++;
                if (aniIndex >= GetSpriteAmount(ATTACK)) {
                    aniIndex = 0;
                    attacking = false;
                }
        }
        }else if(playerAction== SHIELD){
            if (aniTick >= aniSpeed) {
                aniTick = 0;
                aniIndex++;
                if (aniIndex >= GetSpriteAmount(SHIELD)) {
                    aniIndex = 0;
                    defending = false;
                }
            }
        }else if(playerAction== ATTACK_MAGIC){
            if (aniTick >= aniSpeed) {
                aniTick = 0;
                aniIndex++;
                if (aniIndex >= GetSpriteAmount(ATTACK_MAGIC)) {
                    aniIndex = 0;
                    amagando = false;
            }
            }
        }
        else{
            if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= 4) {
                aniIndex = 0;
                attacking = false;
            }
            }
        }
        
    }

    private void setAnimation() {
        int startAni = playerAction;

        if (attacking) {
            playerAction = ATTACK;
        }else if(defending){
            playerAction= SHIELD;
        }else if(amagando){
            playerAction = ATTACK_MAGIC;
        }else{
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

    private void loadAnimations() throws IOException {
        BufferedImage img;
        ArrayList<String> texto = lector.devolverTexto();
       
        idle = new BufferedImage[4];
        attack= new BufferedImage[10];
        defend= new BufferedImage[12];
        magic = new BufferedImage[8];
        
        for (int i= 0; i< 4; i++){
            int pos=0; 
            for(int j=0; j<texto.size(); j++){
                if(i==0){
                    if(j>22 && j<27){
                        idle[pos]= LoadSave.GetSpriteAtlas(texto.get(j));
                        pos++;    
                    }else if(j>=27){
                        break;
                    }
                }else if(i==1){
                    if(j>=0 && j<10){
                        attack[pos]= LoadSave.GetSpriteAtlas(texto.get(j));
                        pos++;    
                    }
                }else if(i==2){
                    if(j>9 && j<22){
                       
                        defend[pos]= LoadSave.GetSpriteAtlas(texto.get(j));
                        
                        pos++;    
                    }
                }else if(i==3){
                    if(j>27){
                        magic[pos]= LoadSave.GetSpriteAtlas(texto.get(j));
                        pos++;    
                    }
                }
            }
           
        }
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_Q -> attacking=true;
        }
    }
    
    
    public void Shield(){
        this.shield+= this.defense*1.5;
        this.defending= true; // para la animacion supongo
    }
    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    public int getDefense() {
        return defense;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public double getShield() {
        return shield;
    }

    public void setShield(double shield) {
        this.shield = shield;
        
    }
    
    public void addAtack(Ataque ataque){
        ataqueElectivo = ataque;
    }
    public void attacking(){
        attacking= true;
    }

    public void setDefending(boolean defending) {
        this.defending = defending;
    }

    public void setAmagando(boolean amagando) {
        this.amagando = amagando;
    }
   

    
    
    
    
    
    
    
    @Override
    public void keyReleased(KeyEvent e) {
    }
     @Override
    public void keyTyped(KeyEvent e) {
    }
    
}

