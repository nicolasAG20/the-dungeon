package thedungeon.models;

/**
 * Mejora que incrementa el ataque del jugador
 * Hereda de la clase base Mejora e implementa su efecto especifico
 * @author Nicolas Agudelo Grajales
 */
public class MejoraAtaque extends Mejora {
    
    private int estatAtk;  // Cantidad de ataque a aumentar

    /**
     * Crea una mejora de ataque escalada por dificultad
     * @param estatAtk Valor base de mejora de ataque
     * @param dificultadActual Nivel de dificultad para escalar la mejora
     */
    public MejoraAtaque(int estatAtk, int dificultadActual) {
        super("Mejora el ataque a + " + ((int)(estatAtk * dificultadActual * 0.7)));
        this.estatAtk = (int)(estatAtk * dificultadActual * 0.7);
    }

    /**
     * Aplica la mejora de ataque al jugador
     * @param player Jugador a mejorar
     * @return Jugador con los stats de ataque actualizados
     */
    @Override
    public Player aplicarMejora(Player player) {
        player.setAtk(player.getAtk() + estatAtk);
        AtaqueEspadazo espadazo = new AtaqueEspadazo(player.getAtk(), 1.5);
        player.setEspadazo(espadazo);
        return player;
    }
}