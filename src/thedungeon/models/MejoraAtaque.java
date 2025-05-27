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
    
    private int estatAtk; 

    public MejoraAtaque(int estatAtk, int dificultadActual) {
        this.estatAtk = estatAtk*dificultadActual;
    }
    
    
    @Override
    public Player aplicarMejora(Player player){
        player.setAtk(player.getAtk()+estatAtk);
        return player;
    }
}
