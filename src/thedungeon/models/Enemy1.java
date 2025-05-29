package thedungeon.models;

import baseThedungeon.models.Lector;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import thedungeon.main.Game;
import static utilz.Constants.Enemy1.ATTACK;
import static utilz.Constants.Enemy1.GetSpriteAmount;
import static utilz.Constants.Enemy1.IDLE;
import utilz.LoadSave;

/**
 * Implementacion concreta de un enemigo tipo 1 en el juego
 * Maneja las animaciones y comportamiento especifico de este enemigo
 * @author Nicolas Agudelo Grajales
 */
public class Enemy1 extends Enemy {

    private BufferedImage[] idle;
    private BufferedImage[] attack;
    private int aniTick, aniIndex, aniSpeed = 25;
    private int usuAction = IDLE;
    private float xDrawOffset = 21 * Game.SCALE;
    private float yDrawOffset = 4 * Game.SCALE;
    
    private Lector lector; 
    
    /**
     * Constructor para crear un enemigo tipo 1
     * 
     * @param x Posicion en el eje X
     * @param y Posicion en el eje Y
     * @param width Ancho del enemigo
     * @param height Alto del enemigo
     * @param difficulty Nivel de dificultad que escala los atributos
     * @throws FileNotFoundException Si no encuentra el archivo de datos
     * @throws IOException Si hay error al leer el archivo
     */
    public Enemy1(float x, float y, int width, int height, int difficulty) throws FileNotFoundException, IOException {
        super(x, y, width, height, 10, 6, 6, difficulty, 50);
        AtaqueVomito vomito = new AtaqueVomito(damage, 1.2);
        ataques.add(vomito);
        initHitbox(x, y, 20 * Game.SCALE, 27 * Game.SCALE);
        lector = new Lector("usu2.txt");
        loadAnimations();
    }
    
    /**
     * Actualiza el estado del enemigo cada frame
     */
    @Override
    public void update() {
        updateAnimationTick();
        setAnimation();
    }
    
    /**
     * Dibuja el enemigo en pantalla
     * 
     * @param g Objeto Graphics para renderizar
     */
    @Override
    public void render(Graphics g) {
        if (attacking) {
            g.drawImage(attack[aniIndex], (int)(hitbox.x - xDrawOffset), (int)(hitbox.y - yDrawOffset), width, height, null);
        } else {
            g.drawImage(idle[aniIndex], (int)(hitbox.x - xDrawOffset), (int)(hitbox.y - yDrawOffset), width, height, null);
        }
    }
    
    /**
     * Actualiza el contador de animacion
     */
    private void updateAnimationTick() {
        aniTick++;
        if(usuAction == ATTACK) {
            if (aniTick >= aniSpeed) {
                aniTick = 0;
                aniIndex++;
                if (aniIndex >= GetSpriteAmount(ATTACK)) {
                    aniIndex = 0;
                    attacking = false;
                }
            }
        } else {
            if (aniTick >= aniSpeed) {
                aniTick = 0;
                aniIndex++;
                if (aniIndex >= GetSpriteAmount(IDLE)) {
                    aniIndex = 0;
                }
            }
        }
    }
    
    /**
     * Establece la animacion actual segun el estado
     */
    private void setAnimation() {
        int startAni = usuAction;

        if (attacking) {
            usuAction = ATTACK;
        } else {
            usuAction = IDLE;
        }

        if (startAni != usuAction) {
            resetAniTick();
        }
    }
    
    /**
     * Reinicia los contadores de animacion
     */
    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }
    
    /**
     * Carga las animaciones desde archivo
     * 
     * @throws IOException Si hay error al leer las imagenes
     */
    private void loadAnimations() throws IOException {
        ArrayList<String> texto = lector.devolverTexto();
       
        idle = new BufferedImage[3];
        attack = new BufferedImage[5];
        
        for (int i = 0; i < 2; i++) {
            int pos = 0; 
            for(int j = 0; j < texto.size(); j++) {
                if(i == 0) {
                    if(j >= 0 && j < 3) {
                        idle[pos] = LoadSave.GetSpriteAtlas(texto.get(j));
                        pos++;    
                    } else if(j >= 3) {
                        break;
                    }
                } else if(i == 1) {
                    if(j >= 3 && j < 8) {
                        attack[pos] = LoadSave.GetSpriteAtlas(texto.get(j));
                        pos++;    
                    }
                }
            }
        }
    }
    
    /**
     * Indica si el enemigo esta atacando
     * 
     * @return true si esta atacando, false en caso contrario
     */
    public boolean isAttacking() {
        return attacking;
    }
}