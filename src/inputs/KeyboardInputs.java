package inputs;

// @author vanes

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import thedungeon.main.GamePanel;

public class KeyboardInputs implements KeyListener {
    private GamePanel gamePanel;

    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_Q:
                gamePanel.getGame().getPlayer().setAttacking(true);
                break;
            case KeyEvent.VK_A:
                gamePanel.getGame().getPlayer().setDefending(true);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_Q:
                gamePanel.getGame().getPlayer().setAttacking(true);
                break;
            case KeyEvent.VK_A:
                gamePanel.getGame().getPlayer().setDefending(true);
                break;
            case KeyEvent.VK_Z:
                gamePanel.getGame().getPlayer().setAmagando(true);
                break;
        }
    }
}
