/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thedungeon.models;

/**
 *
 * @author Nico
 */
public class MejoraAtaque extends Mejora{
    
    @Override
    public Player aplicarMejora(Player player, int dificultad){
        player.setAtk(player.getAtk()+(5*dificultad));
        return player;
    }
}
