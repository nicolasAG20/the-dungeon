
package thedungeon.models;

/**
 * Clase que representa una mejora de curacion en el juego
 * Hereda de la clase abstracta Mejora
 * @author Nicolas Agudelo Grajales
 */
public class Cura extends Mejora{
    
    private double curacion;
    private double dificultadActual;

    /**
     * Constructor para crear una mejora de curacion
     * 
     * @param curacion Cantidad base de curacion
     * @param dificultadActual Nivel actual de dificultad del juego
     */
    public Cura(double curacion, double dificultadActual) {
        super("Aumenta la vida actual a + " + curacion * dificultadActual);
        this.curacion = curacion * dificultadActual;
        this.dificultadActual = dificultadActual;
    }

    /**
     * Aplica la mejora de curacion al jugador
     * 
     * @param player El jugador que recibe la curacion
     * @return El jugador con la vida actualizada
     */
    @Override
    public Player aplicarMejora(Player player) {
        double hpActual = player.getHp();
        if(hpActual + curacion >= player.getHpMax()) {
            player.setHp(player.getHpMax());
        } else {
            player.setHp(player.getHp() + curacion);
        }
        
        return player;
    }
}
