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
    
    private int statMHp;

    public MejoraVida(int statMHp, int dificultadActual) {
        super("Aumenta la vida maxima a + "+ (int)(statMHp*dificultadActual*0.7));
        this.statMHp = (int) (statMHp*dificultadActual*0.7);
    }
    
    
    
    @Override
    public Player aplicarMejora(Player player){
        player.setHpMax(player.getHpMax()+statMHp);
        player.setHp(player.getHp()+statMHp);
        return player;
    }
}
