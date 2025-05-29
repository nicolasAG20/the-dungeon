
package thedungeon.models;

/**
 * Representa una mejora que aumenta la vida maxima del jugador
 * La cantidad de vida aumentada depende del valor base y la dificultad actual
 * Esta clase extiende de Mejora
 * @author Nicolas Agudelo Grajales
 */
public class MejoraVida extends Mejora{
    
    /**
     * Valor de vida maxima que se agregara al jugador
     */
    private int statMHp;

    /**
     * Constructor de la clase MejoraVida
     * Calcula el valor de la vida maxima a aumentar segun el stat base y la dificultad actual
     * 
     * @param statMHp valor base de vida maxima
     * @param dificultadActual valor de la dificultad actual
     */
    public MejoraVida(int statMHp, int dificultadActual) {
        super("Aumenta la vida maxima a + "+ (int)(statMHp*dificultadActual*0.7));
        this.statMHp = (int) (statMHp*dificultadActual*0.7);
    }
    
    /**
     * Aplica la mejora de vida al jugador
     * Incrementa la vida maxima y la vida actual del jugador con el valor de statMHp
     * 
     * @param player jugador al que se le aplicara la mejora
     * @return el jugador con la mejora aplicada
     */
    @Override
    public Player aplicarMejora(Player player){
        player.setHpMax(player.getHpMax()+statMHp);
        player.setHp(player.getHp()+statMHp);
        return player;
    }
}
