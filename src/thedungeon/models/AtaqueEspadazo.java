/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thedungeon.models;

/**
 *
 * @author Nico
 */
public class AtaqueEspadazo extends Ataque{
    
    public AtaqueEspadazo(int atk, double multiplier) {
        super(atk, multiplier);
    }
    
    @Override
    public double infligirDaño(Enemy enemy){
        double damage= 0; 
        damage= (atk*multiplier)*100/(enemy.getDefense()+400);
        return damage; 
    }
}
