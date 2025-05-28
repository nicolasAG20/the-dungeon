/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thedungeon.models;

/**
 *
 * @author Nico
 */
public abstract class Mejora {
    private String infoMejora;

    public Mejora(String infoMejora) {
        this.infoMejora = infoMejora;
    }
    
    
    
    public Player aplicarMejora(Player player){
        return player;
    }

    public String getInfoMejora() {
        return infoMejora;
    }
    
    
}
