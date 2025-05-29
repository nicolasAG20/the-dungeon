
package thedungeon.models;

/**
 * Ataque de rayo laser que hereda de la clase base Ataque
 * Implementa su propia formula de calculo de daño contra jugadores
 * @author Vanessa Toro Sepulveda
 */
public class AtaqueRayoLasser extends Ataque {
    
    /**
     * Constructor que crea un ataque de rayo laser
     * 
     * @param atk Valor base del ataque
     * @param multiplier Multiplicador de daño (10 para daño normal)
     */
    public AtaqueRayoLasser(double atk, double multiplier) {
        super(atk, multiplier);
    }

    /**
     * Calcula el daño infligido a un jugador con el rayo laser
     * Usa la formula: (ataque * multiplicador) * [100 / (defensa + 400)]
     * 
     * @param enemy El jugador que recibe el daño
     * @return Cantidad de daño calculada (siempre no negativo)
     */
    @Override
    public double infligirDaño(Player enemy) {
        double damage = (atk * multiplier) * 100 / (enemy.getDefense() + 400);
        return damage; 
    }
}
