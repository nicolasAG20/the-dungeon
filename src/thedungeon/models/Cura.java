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
    
    private double curacion;
    private double dificultadActual;

    public Cura(double curacion, double dificultadActual) {
        super("aumenta la vida actual a +"+curacion*dificultadActual);
        this.curacion = curacion*dificultadActual;
        this.dificultadActual = dificultadActual;
    }
    
    
    
    @Override
    public Player aplicarMejora(Player player ){
        double hpActual = player.getHp();
        if(hpActual+curacion>=player.getHpMax()){
            player.setHp(player.getHpMax());
        }else{
            player.setHp(player.getHp()+curacion);
        }
        
        return player;
    }
}
