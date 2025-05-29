
package thedungeon.models;

/**
 * Clase abstracta que representa un ataque en el juego
 * Contiene la logica base para calcular el daño infligido
 * @author Vanessa Toro Sepulveda
 */
public abstract class Ataque {
    /**
     * Valor base del ataque
     * Este valor es proporcionado por el jugador o enemigo que realiza el ataque
     */
    public double atk;
    
    /**
     * Multiplicador que afecta la fuerza del ataque
     */
    public double multiplier; 

    /**
     * Constructor para crear un ataque
     * 
     * @param atk Valor base del ataque
     * @param multiplier Multiplicador del daño
     */
    public Ataque(double atk, double multiplier) {
        this.atk = atk;
        this.multiplier = multiplier;
    }
    
    /**
     * Calcula el daño infligido a un enemigo
     * 
     * @param enemy El enemigo que recibe el daño
     * @return El valor del daño calculado (no puede ser negativo)
     */
    public double infligirDaño(Enemy enemy) {
        double damage = (atk * multiplier) * (100 / (enemy.getDefense() + 100));
        return Math.max(damage, 0); // Asegura que el daño no sea negativo
    }
    
    /**
     * Calcula el daño infligido a un jugador
     * 
     * @param enemy El jugador que recibe el daño
     * @return El valor del daño calculado
     */
    public double infligirDaño(Player enemy) {
        return (atk * multiplier) * 100 / (enemy.getDefense() + 400);
    }

    /**
     * Establece el valor base del ataque
     * 
     * @param atk Nuevo valor para el ataque base
     */
    public void setAtk(int atk) {
        this.atk = atk;
    }
}