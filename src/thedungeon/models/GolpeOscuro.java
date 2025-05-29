package thedungeon.models;

/**
 * Ataque especial de tipo golpe oscuro que hereda de la clase Ataque
 * Implementa un calculo de daño unico que ignora parte de la defensa
 * @author Nicolas Agudelo Grajales
 */
public class GolpeOscuro extends Ataque {
    
    /**
     * Constructor para crear un ataque golpe oscuro
     * 
     * @param atk Valor base del ataque
     * @param multiplier Multiplicador del daño
     */
    public GolpeOscuro(int atk, double multiplier) {
        super(atk, multiplier);
    }

    /**
     * Calcula el daño infligido a un enemigo
     * Usa formula: 6 + (ataque * multiplicador - (0.5 * defensa))
     * 
     * @param enemy El enemigo que recibe el daño
     * @return El daño calculado (minimo 0)
     */
    @Override
    public double infligirDaño(Enemy enemy) {
        double damage = 6 + (atk * multiplier - (int)(0.5 * enemy.getDefense()));
        return Math.max(damage, 0); // Asegura que el daño no sea negativo
    }
}