/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thedungeon.models;

/**
 *
 * @author Nico
 */
public class AtaqueLatigo extends Ataque{
    
    public AtaqueLatigo(int atk, double multiplier) {
        super(atk, multiplier);
    }
    
    @Override
    public double infligirDaño(Player enemy){
        double damage= 0; 
        damage= 5+(atk*multiplier - (1.25*enemy.getDefense()));
        if(damage<0){
            return 0; 
        }
        return damage; 
    }
}
