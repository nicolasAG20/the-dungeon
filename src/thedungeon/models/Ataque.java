/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thedungeon.models;

/**
 *
 * @author Nico
 */
public abstract class Ataque {
    public double atk; // el ataque se lo da inicialmente el jugador/enemigo (osea que toca establecerlo cada que se modifique ese valor)
    public double multiplier; 

    public Ataque(double atk, double multiplier) {
        this.atk = atk;
        this.multiplier = multiplier;
    }
    
    
    
    public double infligirDaño(Enemy enemy){
        double damage= 0; 
        damage= (atk*multiplier)*(100/(enemy.getDefense()+100));
        if(damage<0){
            return 0; 
        }
        return damage; 
    }
    public double infligirDaño(Player enemy){
        double damage= 0; 
        damage= (atk*multiplier)*100/(enemy.getDefense()+400);
        return damage; 
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }
    
}
