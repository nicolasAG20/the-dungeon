/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thedungeon.models;
import baseThedungeon.models.Sprite;
import java.awt.Graphics;
import java.util.ArrayList;
/**
 *
 * @author Nico
 */
public abstract class Enemy extends Sprite {
    private double hp; 
    private int defense;
    public double damage; 
    private double shield=0; 
    public ArrayList<Ataque> ataques = new ArrayList<>(); 
    public boolean attacking = false;
    
    private int puntos; 
    
    
    public Enemy(float x, float y, int width, int height, int hp , int defense ,  double damage, int difficulty, int puntos) {
        super(x, y, width, height);
        if(difficulty==2){
            this.hp= hp*1.7;
            this.defense =(int) ((int) defense*1.5); 
            this.damage= damage*1.7;
            
        }else if(difficulty>2) {
            this.hp= hp*(difficulty)*1.5; 
            this.defense =(int) ((int) defense*(difficulty)*1.5); 
            this.damage= damage*(difficulty)*1.5;
            this.puntos= (int) (puntos*difficulty*0.9);
        }else{
            this.hp= hp*(difficulty); 
            this.defense =(int) defense*(difficulty); 
            this.damage= damage*(difficulty);
            this.puntos= puntos*difficulty;
        }
        
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
    
    public Player atacar(Player player){
        //animacion de ataque
        
        attacking= true;
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
        } if (shieldP<0){
            shieldP=0;
        }
        if(daño>0){
            hpP -= daño; 
        }
        player.setHp(hpP);
        player.setShield(shieldP);
        return player;
    }
    
    
    public int getDefense() {
        return defense;
    }

    public double getDamage() {
        return damage;
    }

    public double getShield() {
        return shield;
    }

    public double getHp() {
        return hp;
    }
    
    public void update() {}
    public void render(Graphics g){}

    public int getPuntos() {
        return puntos;
    }
    
    
}