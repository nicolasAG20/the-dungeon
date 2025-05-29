
package thedungeon.models;

/**
 * Representa una mejora que aumenta la defensa del jugador
 * La cantidad de defensa aumentada depende del valor base y la dificultad actual
 * Esta clase extiende de Mejora
 * @author Nicolas Agudelo Grajales
 */
public class MejoraDefensa extends Mejora {
    
    /**
     * Valor de defensa que se agregara al jugador
     */
    private int statDef;

    /**
     * Constructor de la clase MejoraDefensa
     * Calcula el valor de la defensa a aumentar segun el stat base y la dificultad actual
     * 
     * @param statDef valor base de defensa
     * @param dificultadActual valor de la dificultad actual
     */
    public MejoraDefensa(int statDef, int dificultadActual) {
        super("Aumenta la defensa actual a + "+ (int)(statDef*dificultadActual*0.7));
        this.statDef = (int) (statDef*dificultadActual*0.7);
    }
    
    /**
     * Aplica la mejora de defensa al jugador
     * Incrementa la defensa actual del jugador con el valor de statDef
     * 
     * @param player jugador al que se le aplicara la mejora
     * @return el jugador con la mejora aplicada
     */
    @Override
    public Player aplicarMejora(Player player){
        player.setDefense(player.getDefense()+statDef);
        return player;
    }
}
