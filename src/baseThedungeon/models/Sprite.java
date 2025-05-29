/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseThedungeon.models;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public abstract class Sprite {

    protected float x, y;
    protected int width, height;
    protected Rectangle2D.Float hitbox;

    /**
     * Constructor que inicializa un sprite con posicion y dimensiones
     * 
     * @param x Posicion en el eje X
     * @param y Posicion en el eje Y
     * @param width Ancho del sprite
     * @param height Alto del sprite
     */
    public Sprite(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Dibuja el hitbox del sprite en pantalla
     * Utilizado principalmente para depuracion
     * 
     * @param g Objeto Graphics para dibujar
     */
    protected void drawHitbox(Graphics g) {
        g.setColor(Color.PINK);
        g.drawRect((int) hitbox.x, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height);
    }

    /**
     * Inicializa el hitbox del sprite con las dimensiones especificadas
     * 
     * @param x Posicion X del hitbox
     * @param y Posicion Y del hitbox
     * @param width Ancho del hitbox
     * @param height Alto del hitbox
     */
    protected void initHitbox(float x, float y, float width, float height) {
        hitbox = new Rectangle2D.Float(x, y, width, height);
    }

    /**
     * Obtiene el hitbox del sprite
     * 
     * @return El rectangulo que representa el area de colision
     */
    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }
}
