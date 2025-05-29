
package thedungeon.models;

/**
 * Representa un ataque tipo hoja en el juego
 * Hereda de la clase base Ataque
 * @author Vanessa Toro Sepulveda
 */
public class AtaqueHoja extends Ataque{
    
    /**
     * Crea un nuevo ataque de hoja con los parametros especificados
     * 
     * @param atk Valor base del ataque
     * @param multiplier Multiplicador de daño para este ataque
     */
    public AtaqueHoja(double atk, double multiplier) {
        super(atk, multiplier);
    }
    
}
