/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thedungeon.models;

/**
 *
 * @author Nico
 */
public class GolpeOscuro extends Ataque{
    
    public GolpeOscuro(int atk, double multiplier) {
        super(atk, multiplier);
    }
    @Override
    public double infligirDaño(Enemy enemy){
        double damage= 0; 
        damage= 6+(atk*multiplier - (int)(0.5*enemy.getDefense()));
        if(damage<0){
            return 0; 
        }
        return damage; 
    } 
}
