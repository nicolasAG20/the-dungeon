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

/**
 * Representa al jugador del juego, con animaciones, estadisticas y acciones como atacar, defender y usar magia
 * Extiende de la clase Sprite
 * @author Vanessa Toro Sepulveda,Nicolas Agudelo Grajales
 */
public class Player extends Sprite {

    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 25;
    private int playerAction = IDLE;
    private boolean attacking = false;
    private boolean defending = false;
    private boolean amagando = false; 
    private float xDrawOffset = 21 * Game.SCALE;
    private float yDrawOffset = 4 * Game.SCALE;

    private Lector lector = new Lector("ubicaciones.txt"); 
    public boolean haciendoAccion = true; 

    private BufferedImage[] idle;
    private BufferedImage[] attack;
    private BufferedImage[] defend;
    private BufferedImage[] magic;

    private double hpMax;
    private double hp; 
    private int defense; 
    private int atk; 
    private double shield = 0; 

    private AtaqueEspadazo espadazo; 
    private Ataque ataqueElectivo; 

    /**
     * Constructor del jugador
     * Inicializa las estadisticas, animaciones, hitbox y ataques
     * 
     * @param x posicion x inicial
     * @param y posicion y inicial
     * @param width ancho del sprite
     * @param height alto del sprite
     * @throws IOException si falla la carga de sprites
     */
    public Player(float x, float y, int width, int height) throws IOException {
        super(x, y, width, height);
        loadAnimations();
        initHitbox(x, y, 20 * Game.SCALE, 27 * Game.SCALE);
        this.lector = new Lector("ubicaciones.txt");
        hpMax = 25;
        hp = 25;
        defense = 8;
        atk = 9;
        espadazo = new AtaqueEspadazo(atk , 1.5);
    }

    /**
     * Actualiza la animacion y accion del jugador
     */
    public void update() {
        updateAnimationTick();
        setAnimation();
    }

    /**
     * Dibuja la animacion actual del jugador en pantalla
     * 
     * @param g objeto Graphics usado para dibujar
     */
    public void render(Graphics g) {
        if (attacking){
            g.drawImage(attack[aniIndex], (int) (hitbox.x - xDrawOffset), (int) (hitbox.y - yDrawOffset), width, height, null);
        } else if(defending){
            g.drawImage(defend[aniIndex], (int) (hitbox.x - xDrawOffset), (int) (hitbox.y - yDrawOffset), width, height, null); 
        } else if(amagando){
            g.drawImage(magic[aniIndex], (int) (hitbox.x - xDrawOffset), (int) (hitbox.y - yDrawOffset), width, height, null);
        } else {
            g.drawImage(idle[aniIndex], (int) (hitbox.x - xDrawOffset), (int) (hitbox.y - yDrawOffset), width, height, null);
        }
    }

    /**
     * Actualiza el contador y frame de animacion segun la accion actual
     */
    private void updateAnimationTick() {
        aniTick++;
        if (playerAction == ATTACK) {
            if (aniTick >= aniSpeed) {
                aniTick = 0;
                aniIndex++;
                if (aniIndex >= GetSpriteAmount(ATTACK)) {
                    haciendoAccion = false;
                    aniIndex = 0;
                    attacking = false;
                }
            }
        } else if (playerAction == SHIELD) {
            if (aniTick >= aniSpeed) {
                aniTick = 0;
                aniIndex++;
                if (aniIndex >= GetSpriteAmount(SHIELD)) {
                    aniIndex = 0;
                    defending = false;
                }
            }
        } else if (playerAction == ATTACK_MAGIC) {
            if (aniTick >= aniSpeed) {
                aniTick = 0;
                aniIndex++;
                if (aniIndex >= GetSpriteAmount(ATTACK_MAGIC)) {
                    aniIndex = 0;
                    amagando = false;
                }
            }
        } else {
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

    /**
     * Determina que animacion debe reproducirse segun el estado del jugador
     */
    private void setAnimation() {
        int startAni = playerAction;

        if (attacking) {
            playerAction = ATTACK;
        } else if(defending){
            playerAction = SHIELD;
        } else if(amagando){
            playerAction = ATTACK_MAGIC;
        } else {
            playerAction = IDLE;
        }

        if (startAni != playerAction) {
            resetAniTick();
        }
    }

    /**
     * Reinicia el contador y frame de animacion
     */
    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    /**
     * Carga los sprites necesarios para cada animacion desde el archivo ubicaciones.txt
     * 
     * @throws IOException si ocurre un error al cargar las imagenes
     */
    private void loadAnimations() throws IOException {
        BufferedImage img;
        ArrayList<String> texto = lector.devolverTexto();

        idle = new BufferedImage[4];
        attack = new BufferedImage[10];
        defend = new BufferedImage[12];
        magic = new BufferedImage[9];

        for (int i = 0; i < 4; i++) {
            int pos = 0; 
            for (int j = 0; j < texto.size(); j++) {
                if (i == 0) {
                    if (j > 22 && j < 27) {
                        idle[pos] = LoadSave.GetSpriteAtlas(texto.get(j));
                        pos++;    
                    } else if (j >= 27) {
                        break;
                    }
                } else if (i == 1) {
                    if (j >= 0 && j < 10) {
                        attack[pos] = LoadSave.GetSpriteAtlas(texto.get(j));
                        pos++;    
                    }
                } else if (i == 2) {
                    if (j > 9 && j < 22) {
                        defend[pos] = LoadSave.GetSpriteAtlas(texto.get(j));
                        pos++;    
                    }
                } else if (i == 3) {
                    if (j > 27) {
                        magic[pos] = LoadSave.GetSpriteAtlas(texto.get(j));
                        pos++;    
                    }
                }
            }
        }
    }

    /**
     * Restaura vida al jugador segun la dificultad
     * 
     * @param dificultad nivel de dificultad del juego
     */
    public void curar(int dificultad) {
        double hp = this.hp;
        if (hp + 4 * dificultad > this.hp) {
            this.hp = hpMax;
        } else {
            this.hp += 8 * dificultad;
        }
    }

    /**
     * Aplica un escudo al jugador basado en su defensa
     */
    public void shield() {
        this.shield += this.defense * 1.1;
        this.defending = true;
    }

    /**
     * Cambia el estado de ataque del jugador
     * 
     * @param attacking true si esta atacando
     */
    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
        defending = false;
        amagando = false;
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

    /**
     * Establece un ataque adicional para el jugador
     * 
     * @param ataque objeto de tipo Ataque
     */
    public void addAtack(Ataque ataque) {
        ataqueElectivo = ataque;
    }

    /**
     * Activa el estado de ataque
     */
    public void attacking() {
        attacking = true;
    }

    public void setDefending(boolean defending) {
        this.defending = defending;
        amagando = false;
        attacking = false;
    }

    public void setAmagando(boolean amagando) {
        this.amagando = amagando;
        attacking = false;
        defending = false;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public AtaqueEspadazo getEspadazo() {
        return espadazo;
    }

    public void setEspadazo(AtaqueEspadazo espadazo) {
        this.espadazo = espadazo;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public double getHpMax() {
        return hpMax;
    }

    public void setHpMax(double hpMax) {
        this.hpMax = hpMax;
    }

    public boolean isHaciendoAccion() {
        return haciendoAccion;
    }

    public void setHaciendoAccion(boolean haciendoAccion) {
        this.haciendoAccion = haciendoAccion;
    }
}
