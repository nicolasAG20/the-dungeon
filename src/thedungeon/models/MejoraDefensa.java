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
    
    @Override
    public Player aplicarMejora(Player player, int dificultad){
        player.setDefense(player.getDefense()+(3*dificultad));
        return player;
    }
}
