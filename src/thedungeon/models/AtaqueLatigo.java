package thedungeon.models;

/**
 * Clase que representa un ataque tipo látigo en el juego.
 * Hereda de la clase abstracta Ataque y sobrescribe el método de daño contra jugadores
 * @author Vanessa Toro Sepulveda
 */
public class AtaqueLatigo extends Ataque {
    
    /**
     * Constructor para crear un ataque de látigo
     * @param atk Valor base del ataque
     * @param multiplier Multiplicador del daño (1.0 para daño normal)
     */
    public AtaqueLatigo(double atk, double multiplier) {
        super(atk, multiplier);
    }
    
    /**
     * Calcula el daño infligido a un jugador con un ataque de látigo.
     * La fórmula aplica una reducción de daño basada en la defensa del jugador.
     * 
     * @param enemy El jugador que recibe el daño
     * @return El valor del daño calculado (siempre >= 0)
     */
    @Override
    public double infligirDaño(Player enemy) {
        // Fórmula: (Ataque * Multiplicador) * (100 / (Defensa + 400))
        double damage = (atk * multiplier) * 100 / (enemy.getDefense() + 400);
        return Math.max(damage, 0);  // Asegura que el daño no sea negativo
    }
}