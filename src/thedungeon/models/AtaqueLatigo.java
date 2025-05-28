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
    
    public AtaqueLatigo(double atk, double multiplier) {
        super(atk, multiplier);
    }
    
    @Override
    public double infligirDaño(Player enemy){
        double damage; 
        damage= (atk*multiplier)*100/(enemy.getDefense()+400);
        
        return damage; 
    }
}
