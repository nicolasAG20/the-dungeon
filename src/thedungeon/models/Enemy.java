/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thedungeon.models;
import baseThedungeon.models.Sprite;
import java.util.ArrayList;
/**
 *
 * @author Nico
 */
public abstract class Enemy extends Sprite{
    private double hp; 
    private int defense;
    int damage; 
    private double shield=0; 
    public ArrayList<Ataque> ataques = new ArrayList<>(); 
    private boolean attacking = false;
    
    
    public Enemy(float x, float y, int width, int height, int hp , int defense ,  int damage, int difficulty) {
        super(x, y, width, height);
        this.hp= hp*difficulty; 
        this.defense =(int) defense*difficulty; 
        this.damage= damage*difficulty;
    }
            
    public void recibirDaño(Ataque ataque){
        double daño = ataque.infligirDaño(this);
        if(shield>0){
            shield-=daño;
            daño-= shield; 
        }else if (shield<0){
            shield=0;
        }
        if(daño>0){
            this.hp -= daño; 
        }
        
    }
    
    public void atacar(Player player){
        //animacion de ataque
        double shieldP = player.getShield();
        double hpP= player.getHp();
        Ataque ataque; 
        if (ataques.size()==1){
            ataque= ataques.get(0);// lo que dice abajo >_< 
        }else{
            int numAtk = (int)(Math.random() * ((ataques.size()-1) - 0 + 1)) + 0;
            ataque= ataques.get(numAtk);//toca ejecutar la animacion de ese ataque
        }
        double daño = ataque.infligirDaño(player);
        if(shieldP>0){
            shieldP-=daño;
            daño-= shieldP; 
        }else if (shieldP<0){
            shieldP=0;
        }
        if(daño>0){
            hpP -= daño; 
        }
        player.setHp(hpP);
        player.setShield(shieldP);
    }
    
    
    public int getDefense() {
        return defense;
    }

    public int getDamage() {
        return damage;
    }

    public double getShield() {
        return shield;
    }
    
    
    
}
