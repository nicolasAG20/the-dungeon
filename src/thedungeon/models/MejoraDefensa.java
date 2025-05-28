/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thedungeon.models;

/**
 *
 * @author Nico
 */
public class MejoraDefensa extends Mejora {
    
    private int statDef;

    public MejoraDefensa(int statDef, int dificultadActual) {
        super("aumenta la defensa actual a +"+statDef*dificultadActual);
        this.statDef = statDef*dificultadActual;
    }
    
    
    @Override
    public Player aplicarMejora(Player player){
        player.setDefense(player.getDefense()+statDef);
        return player;
    }
}
