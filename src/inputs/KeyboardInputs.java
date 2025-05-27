package inputs;

// @author vanes

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import thedungeon.main.Game;
import thedungeon.main.GamePanel;
import thedungeon.models.Ataque;

public class KeyboardInputs implements KeyListener {
    private GamePanel gamePanel;
    private boolean ataqueSeleccionado= false; 
    private int numAtk;
    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(gamePanel.getGame().getEnemigosVivos()==1){
            gamePanel.getGame().setEnemigoAAtacar(0);
            switch (e.getKeyCode()) {
            case KeyEvent.VK_Q:
                gamePanel.getGame().getPlayer().setAttacking(true);
                Ataque ataque = gamePanel.getGame().getPlayer().getEspadazo();
                gamePanel.getGame().atacarEnemigo(ataque);
                break;
            case KeyEvent.VK_A:
                gamePanel.getGame().getPlayer().setDefending(true);
                break;
            }
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(gamePanel.getGame().getEnemigosVivos()== 1){
            
            gamePanel.getGame().setEnemigoAAtacar(0);
            switch (e.getKeyCode()) {
            case KeyEvent.VK_Q:
                gamePanel.getGame().getPlayer().setAttacking(true);
                Ataque ataque = gamePanel.getGame().getPlayer().getEspadazo();
                gamePanel.getGame().atacarEnemigo(ataque);
                break;
            case KeyEvent.VK_A:
                gamePanel.getGame().getPlayer().setDefending(true);
                break;
            case KeyEvent.VK_Z:
                gamePanel.getGame().getPlayer().setAmagando(true);
                break;
            }
            
        }else{
            if(!ataqueSeleccionado){
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_Q:
                        numAtk=1;
                        break;
                    case KeyEvent.VK_Z:
                        numAtk=2;
                        break;
                }
            }else{
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_1:
                        gamePanel.getGame().setEnemigoAAtacar(0);
                        break;
                    case KeyEvent.VK_2:
                        gamePanel.getGame().setEnemigoAAtacar(1 );
                        break;
            }
        }
            
        }
    
    }
}