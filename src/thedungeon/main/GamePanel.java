package thedungeon.main;

// @author vanes

import inputs.KeyboardInputs;
import inputs.MouseInputs;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import static thedungeon.main.Game.*;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements KeyListener{
    private Game game;

    public GamePanel(Game game) {

        this.game = game;
        setPanelSize();
        setFocusable(true);
        addKeyListener(new KeyboardInputs(this));
        requestFocusInWindow();

        
    }

    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setPreferredSize(size);
    }

    public void updateGame() {

    }

    public void paintComponent(Graphics g) {
            super.paintComponent(g);
            game.render(g);
    }

    public Game getGame() {
            return game;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_Q -> { 
                System.out.println("puta");
            }
            
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }


}
