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

        
    public MejoraAtaque( int estatAtk, int dificultadActual) {
        super("Mejora el ataque a + " + ((int)(estatAtk * dificultadActual*0.7)));
        this.estatAtk = (int) (estatAtk * dificultadActual*0.7);
    }

    
    
    
    @Override
    public Player aplicarMejora(Player player){
        player.setAtk(player.getAtk()+estatAtk);
        AtaqueEspadazo espadazo = new AtaqueEspadazo(player.getAtk() , 1.5);
        player.setEspadazo(espadazo);
        return player;
    }
}
