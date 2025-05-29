package thedungeon.models;

/**
 * Clase abstracta que representa una mejora aplicable al jugador
 * Define la estructura base para todas las mejoras del juego
 * @author Nicolas Agudelo Grajales
 */
public abstract class Mejora {
    private String infoMejora;  // Descripcion del efecto de la mejora

    /**
     * Constructor base para crear mejoras
     * @param infoMejora Descripcion textual del efecto de la mejora
     */
    public Mejora(String infoMejora) {
        this.infoMejora = infoMejora;
    }

    /**
     * Metodo abstracto para aplicar el efecto de la mejora al jugador
     * @param player Jugador al que se aplica la mejora
     * @return Jugador modificado por la mejora
     */
    public abstract Player aplicarMejora(Player player);

    /**
     * Obtiene la descripcion del efecto de la mejora
     * @return Cadena con la informacion de la mejora
     */
    public String getInfoMejora() {
        return infoMejora;
    }
}