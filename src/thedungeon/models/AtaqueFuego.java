/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thedungeon.models;

/**
 *
 * @author Nico
 */
public class AtaqueFuego extends Ataque{
    
    public AtaqueFuego(int atk, double multiplier) {
        super(atk, multiplier);
    }
    
    @Override
    public double infligirDaño(Enemy enemy){
        double damage= 0; 
        damage= 18+(atk*multiplier - (int)(0.7*enemy.getDefense()));
        if(damage<0){
            return 0; 
        }
        return damage; 
    } 
}
