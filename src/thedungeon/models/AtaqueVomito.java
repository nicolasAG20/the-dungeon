
package thedungeon.models;

/**
 * Clase que representa un ataque de vomito en el juego
 * Hereda de la clase abstracta Ataque
 * @author Vanessa Toro Sepulveda
 */
public class AtaqueVomito extends Ataque {
    /**
     * Constructor para crear un ataque de vomito
     * 
     * @param atk Valor base del ataque
     * @param multiplier Multiplicador del daño
     */
    public AtaqueVomito(double atk, double multiplier) {
        super(atk, multiplier);
    }
    
}
