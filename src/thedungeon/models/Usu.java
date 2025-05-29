
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
 * Clase que representa al enemigo Usu
 * Extiende de Enemy e incluye animaciones y logica de ataque
 * @author Vanessa Toro Sepulveda,Nicolas Agudelo Grajales
 */
public class Usu extends Enemy{

    private BufferedImage[] idle;
    private BufferedImage[] attack;
    private int aniTick;
    private int aniIndex;
    private int aniSpeed = 25;
    private int usuAction = IDLE;
    private float xDrawOffset = 21 * Game.SCALE;
    private float yDrawOffset = 4 * Game.SCALE;
    Lector lector;

    /**
     * Constructor de la clase Usu
     * 
     * @param x posicion x inicial
     * @param y posicion y inicial
     * @param width ancho del sprite
     * @param height alto del sprite
     * @param difficulty nivel de dificultad del enemigo
     * @throws FileNotFoundException si no se encuentra el archivo de sprites
     * @throws IOException si ocurre un error al leer los sprites
     */
    public Usu(float x, float y, int width, int height, int difficulty) throws FileNotFoundException, IOException {
        super(x, y, width, height, 12, 4, 7, difficulty, 25);
        AtaqueHoja hoja;
        hoja = new AtaqueHoja(getDamage() , 1.8);
        initHitbox(x, y, 20 * Game.SCALE, 27 * Game.SCALE);
        ataques.add(hoja);
        lector = new Lector("usu.txt");
        loadAnimations();
    }

    /**
     * Actualiza el estado del enemigo y su animacion
     */
    @Override
    public void update() {
        updateAnimationTick();
        setAnimation();
    }

    /**
     * Dibuja el sprite del enemigo en pantalla
     * 
     * @param g contexto grafico donde se dibuja
     */
    @Override
    public void render(Graphics g) {
        if (attacking){
            g.drawImage(attack[aniIndex], (int) (hitbox.x - xDrawOffset), (int) (hitbox.y - yDrawOffset), width, height, null);
        }else{
             g.drawImage(idle[aniIndex], (int) (hitbox.x - xDrawOffset), (int) (hitbox.y - yDrawOffset), width, height, null);
        }
    }

    /**
     * Actualiza el contador de ticks y el indice de la animacion
     */
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

    /**
     * Establece la animacion actual segun el estado del enemigo
     */
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

    /**
     * Reinicia los contadores de animacion
     */
    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    /**
     * Carga las imagenes de las animaciones desde las rutas indicadas en el archivo usu.txt
     * 
     * @throws IOException si ocurre un error al cargar los sprites
     */
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

    /**
     * Indica si el enemigo esta actualmente atacando
     * 
     * @return true si esta atacando, false en caso contrario
     */
    public boolean isAttacking() {
        return attacking;
    }

}
