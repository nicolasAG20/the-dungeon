/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thedungeon.models;

/**
 *
 * @author Nico
 */
public class MejoraVida extends Mejora{
    @Override
    public Player aplicarMejora(Player player, int dificultad){
        player.setHpMax(player.getHpMax()+(4*dificultad));
        return player;
    }
}
