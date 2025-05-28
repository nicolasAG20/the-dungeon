package inputs;

// @author vanes

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import thedungeon.main.Game;
import thedungeon.main.GamePanel;
import thedungeon.models.Ataque;

public class KeyboardInputs implements KeyListener {
    private GamePanel gamePanel;
    private boolean ataqueSeleccionado= false; 
    private int numAtk;
    private int shieldCooldown=0;
    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
                
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        if(gamePanel.getGame().getEnemigosVivos()== 1){
            if(e.getKeyChar()=='w' && shieldCooldown<=0){
                shieldCooldown=3;
                gamePanel.setShieldCooldown(shieldCooldown);
                gamePanel.getGame().getPlayer().setDefending(true);
                    gamePanel.getGame().getPlayer().shield();
                    {
                        try {
                            gamePanel.getGame().atacarEnemigo();
                        } catch (IOException ex) {} catch (InterruptedException ex) { }
                    }
        }
            gamePanel.getGame().setEnemigoAAtacar(0);
            switch (e.getKeyCode()) {
            case KeyEvent.VK_Q:
                
                gamePanel.getGame().getPlayer().setAttacking(true);
                Ataque ataque = gamePanel.getGame().getPlayer().getEspadazo();
                {
                    try {
                        gamePanel.getGame().atacarEnemigo(ataque);
                    } catch (IOException ex) {}
                }
                if(shieldCooldown>0){
                    shieldCooldown--;
                }
                
                gamePanel.setShieldCooldown(shieldCooldown);
                break;
                
            case KeyEvent.VK_E:
                gamePanel.getGame().getPlayer().setAmagando(true);
                if(shieldCooldown>0){
                    shieldCooldown--;
                }
                gamePanel.setShieldCooldown(shieldCooldown);
                break;
            }
            System.out.println(shieldCooldown);
        }else if(gamePanel.getGame().getEnemigosVivos()>1){
            if(e.getKeyChar()=='w' && shieldCooldown<=0){
                shieldCooldown=3;
                gamePanel.setShieldCooldown(shieldCooldown);
            gamePanel.getGame().getPlayer().setDefending(true);
                gamePanel.getGame().getPlayer().shield();
                {
                    try {
                        gamePanel.getGame().atacarEnemigo();
                    } catch (IOException ex) {} catch (InterruptedException ex) {}
                }
            }
            if(!ataqueSeleccionado){
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_Q:
                        
                        numAtk=1;
                        break;
                    case KeyEvent.VK_E:
                        numAtk=2;
                        break;
                }
                
                ataqueSeleccionado=true;
            }else{
                
                switch (e.getKeyCode()) {
                        
                    case KeyEvent.VK_1:
                        if(shieldCooldown>0){
                            shieldCooldown--;
                        }
                        gamePanel.setShieldCooldown(shieldCooldown);
                        gamePanel.getGame().getPlayer().setAttacking(true);
                        Ataque ataque = gamePanel.getGame().getPlayer().getEspadazo();
                        gamePanel.getGame().setEnemigoAAtacar(0);
                        ataqueSeleccionado=false;
                    {
                        try {
                            gamePanel.getGame().atacarEnemigo(ataque);
                        } catch (IOException ex) {}
                    }
                        break;

                    case KeyEvent.VK_2:
                        if(shieldCooldown>0){
                            shieldCooldown--;
                        }
                        gamePanel.setShieldCooldown(shieldCooldown);
                        gamePanel.getGame().getPlayer().setAttacking(true);
                        ataque = gamePanel.getGame().getPlayer().getEspadazo();
                        gamePanel.getGame().setEnemigoAAtacar(1);
                        ataqueSeleccionado=false;
                    {
                        try {
                            gamePanel.getGame().atacarEnemigo(ataque);
                        } catch (IOException ex) {}
                    }
                        break;

                }
                
                System.out.println(shieldCooldown);
            }
            
        }else if(gamePanel.getGame().isRondaMejora()){
            switch(e.getKeyCode()){
                case KeyEvent.VK_1:
                
                    try {
                        gamePanel.getGame().usarMejora(0);
                    } catch (IOException ex) {}
                
                    break;

                case KeyEvent.VK_2:
                    try {
                        gamePanel.getGame().usarMejora(1);
                    } catch (IOException ex) {}
                    break;
                case KeyEvent.VK_3:
                    try {
                        gamePanel.getGame().usarMejora(2);
                    } catch (IOException ex) {}
                    break;
            }
        }
    
    }
    
    public void atacarEnemigo() throws IOException{
        switch(numAtk){
            case 1:
                Ataque ataque = gamePanel.getGame().getPlayer().getEspadazo();
                gamePanel.getGame().atacarEnemigo(ataque);
                break;
            case 2:
                
                break;
        }
    }

    public int getShieldCooldown() {
        return shieldCooldown;
    }
    
}