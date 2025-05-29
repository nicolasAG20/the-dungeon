package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import thedungeon.main.Game;
import thedungeon.main.GamePanel;
import thedungeon.models.Ataque;

/**
 * Clase que maneja las entradas por teclado del juego
 * Implementa KeyListener para detectar pulsaciones de teclas
 * @author Vanessa Toro Sepulveda,Nicolas Agudelo Grajales
 */
public class KeyboardInputs implements KeyListener {
    private GamePanel gamePanel;
    private boolean ataqueSeleccionado = false; 
    private int numAtk;
    private int shieldCooldown = 0;

    /**
     * Constructor que inicializa el manejador de entradas con el panel del juego
     * 
     * @param gamePanel El panel del juego al que pertenecen las entradas
     */
    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {       
    }

    @Override
    public void keyReleased(KeyEvent e) {   
    }

    /**
     * Metodo que se ejecuta al presionar una tecla
     * Maneja todas las acciones del juego segun la tecla presionada
     * 
     * @param e El evento de teclado que contiene informacion sobre la tecla presionada
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if(gamePanel.getGame().getEnemigosVivos() == 1 && gamePanel.getGame().isJugadorVivo()) {
            if(e.getKeyChar() == 'w' && shieldCooldown <= 0) {
                shieldCooldown = 3;
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
                    if(shieldCooldown > 0) {
                        shieldCooldown--;
                    }
                    gamePanel.setShieldCooldown(shieldCooldown);
                    break;
                    
                case KeyEvent.VK_E:
                    gamePanel.getGame().getPlayer().setAmagando(true);
                    if(shieldCooldown > 0) {
                        shieldCooldown--;
                    }
                    gamePanel.setShieldCooldown(shieldCooldown);
                    break;
            }
            System.out.println(shieldCooldown);
        } else if(gamePanel.getGame().getEnemigosVivos() > 1 && gamePanel.getGame().isJugadorVivo()) {
            if(e.getKeyChar() == 'w' && shieldCooldown <= 0) {
                shieldCooldown = 3;
                gamePanel.setShieldCooldown(shieldCooldown);
                gamePanel.getGame().getPlayer().setDefending(true);
                gamePanel.getGame().getPlayer().shield();
                {
                    try {
                        gamePanel.getGame().atacarEnemigo();
                    } catch (IOException ex) {} catch (InterruptedException ex) {}
                }
            }
            if(!ataqueSeleccionado) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_Q:
                        numAtk = 1;
                        break;
                    case KeyEvent.VK_E:
                        numAtk = 2;
                        break;
                }
                ataqueSeleccionado = true;
            } else {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_1:
                        if(shieldCooldown > 0) {
                            shieldCooldown--;
                        }
                        gamePanel.setShieldCooldown(shieldCooldown);
                        gamePanel.getGame().getPlayer().setAttacking(true);
                        Ataque ataque = gamePanel.getGame().getPlayer().getEspadazo();
                        gamePanel.getGame().setEnemigoAAtacar(0);
                        ataqueSeleccionado = false;
                        {
                            try {
                                gamePanel.getGame().atacarEnemigo(ataque);
                            } catch (IOException ex) {}
                        }
                        break;

                    case KeyEvent.VK_2:
                        if(shieldCooldown > 0) {
                            shieldCooldown--;
                        }
                        gamePanel.setShieldCooldown(shieldCooldown);
                        gamePanel.getGame().getPlayer().setAttacking(true);
                        ataque = gamePanel.getGame().getPlayer().getEspadazo();
                        gamePanel.getGame().setEnemigoAAtacar(1);
                        ataqueSeleccionado = false;
                        {
                            try {
                                gamePanel.getGame().atacarEnemigo(ataque);
                            } catch (IOException ex) {}
                        }
                        break;
                }
                System.out.println(shieldCooldown);
            }
        } else if(gamePanel.getGame().isRondaMejora()) {
            switch(e.getKeyCode()) {
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
        } else {
            if(e.getKeyChar() == 'r') {
                try { 
                    Game nuevoJuego = new Game();
                    gamePanel.getGame().compararPuntajes();
                    gamePanel.setGame(nuevoJuego);
                } catch (IOException ex) {}
            }
        }
        if(e.getKeyChar() == 'r') {
            try { 
                Game nuevoJuego = new Game();
                gamePanel.getGame().compararPuntajes();
                System.exit(0);
            } catch (IOException ex) {}
        }
    }
    
    /**
     * Metodo para atacar a un enemigo segun el tipo de ataque seleccionado
     * 
     * @throws IOException Si ocurre un error de entrada/salida durante el ataque
     */
    public void atacarEnemigo() throws IOException {
        switch(numAtk) {
            case 1:
                Ataque ataque = gamePanel.getGame().getPlayer().getEspadazo();
                gamePanel.getGame().atacarEnemigo(ataque);
                break;
            case 2:
                break;
        }
    }

    /**
     * Obtiene el tiempo de espera restante para el escudo
     * 
     * @return El tiempo de espera restante
     */
    public int getShieldCooldown() {
        return shieldCooldown;
    }
}