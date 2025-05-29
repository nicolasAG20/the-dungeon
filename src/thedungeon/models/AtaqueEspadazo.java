
package thedungeon.models;

/**
 * Clase que representa un ataque de espadazo en el juego
 * Hereda de la clase abstracta Ataque e implementa su propio calculo de daño
 * @author Vanessa Toro Sepulveda
 */
public class AtaqueEspadazo extends Ataque {
    
    /**
     * Constructor para crear un ataque de espadazo
     * 
     * @param atk Valor base del ataque
     * @param multiplier Multiplicador del daño para este tipo de ataque
     */
    public AtaqueEspadazo(int atk, double multiplier) {
        super(atk, multiplier);
    }
    
    /**
     * Calcula el daño infligido a un enemigo con un espadazo
     * Este ataque es particularmente efectivo contra enemigos (defensa +400)
     * 
     * @param enemy El enemigo que recibe el daño
     * @return El valor del daño calculado
     */
    @Override
    public double infligirDaño(Enemy enemy) {
        return (atk * multiplier) * 100 / (enemy.getDefense() + 400);
    }
}