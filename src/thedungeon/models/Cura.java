/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thedungeon.models;

/**
 *
 * @author Nico
 */
public class Cura extends Mejora{
    
    @Override
    public Player aplicarMejora(Player player , int dificultad){
        double hpActual = player.getHp();
        if(hpActual+(6*dificultad)>=player.getHpMax()){
            player.setHp(player.getHpMax());
        }else{
            player.setHp(player.getHp()+(6*dificultad));
        }
        
        return player;
    }
}
