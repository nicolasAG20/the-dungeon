package thedungeon.models;
import baseThedungeon.models.Sprite;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * Clase abstracta que representa un enemigo en el juego
 * Hereda de Sprite y define el comportamiento base de los enemigos
 * @author Nicolas Agudelo Grajales
 */
public abstract class Enemy extends Sprite {
    private double hp; 
    private int defense;
    public double damage; 
    private double shield = 0; 
    public ArrayList<Ataque> ataques = new ArrayList<>(); 
    public boolean attacking = false;
    private int puntos; 
    
    /**
     * Constructor para crear un enemigo con sus atributos base
     * 
     * @param x Posicion en el eje X
     * @param y Posicion en el eje Y
     * @param width Ancho del enemigo
     * @param height Alto del enemigo
     * @param hp Vida base del enemigo
     * @param defense Defensa base del enemigo
     * @param damage Daño base del enemigo
     * @param difficulty Nivel de dificultad que escala los atributos
     * @param puntos Puntos que otorga al ser derrotado
     */
    public Enemy(float x, float y, int width, int height, int hp, int defense, double damage, int difficulty, int puntos) {
        super(x, y, width, height);
        if(difficulty == 2) {
            this.hp = hp * 1.7;
            this.defense = (int)(defense * 1.5); 
            this.damage = damage * 1.7;
        } else if(difficulty > 2) {
            this.hp = hp * difficulty * 1.5; 
            this.defense = (int)(defense * difficulty * 1.5); 
            this.damage = damage * difficulty * 1.5;
            this.puntos = (int)(puntos * difficulty * 0.9);
        } else {
            this.hp = hp * difficulty; 
            this.defense = defense * difficulty; 
            this.damage = damage * difficulty;
            this.puntos = puntos * difficulty;
        }
    }
    
    /**
     * Calcula el daño recibido por un ataque
     * 
     * @param ataque El ataque recibido
     */
    public void recibirDaño(Ataque ataque) {
        double daño = ataque.infligirDaño(this);
        if(shield > 0) {
            shield -= daño;
            daño -= shield; 
        } else if(shield < 0) {
            shield = 0;
        }
        if(daño > 0) {
            this.hp -= daño; 
        }
    }
    
    /**
     * Realiza un ataque contra el jugador
     * 
     * @param player El jugador que recibe el ataque
     * @return El jugador con los stats actualizados
     */
    public Player atacar(Player player) {
        attacking = true;
        double shieldP = player.getShield();
        double hpP = player.getHp();
        Ataque ataque;
        
        if(ataques.size() == 1) {
            ataque = ataques.get(0);
        } else {
            int numAtk = (int)(Math.random() * ((ataques.size()-1) - 0 + 1)) + 0;
            ataque = ataques.get(numAtk);
        }
        
        double daño = ataque.infligirDaño(player);
        if(shieldP > 0) {
            shieldP -= daño;
            daño -= shieldP; 
        } 
        if(shieldP < 0) {
            shieldP = 0;
        }
        if(daño > 0) {
            hpP -= daño; 
        }
        
        player.setHp(hpP);
        player.setShield(shieldP);
        return player;
    }
    
    // Métodos getters
    public int getDefense() {
        return defense;
    }

    public double getDamage() {
        return damage;
    }

    public double getShield() {
        return shield;
    }

    public double getHp() {
        return hp;
    }

    public int getPuntos() {
        return puntos;
    }
    
    // Métodos abstractos
    public void update() {}
    public void render(Graphics g) {}
}