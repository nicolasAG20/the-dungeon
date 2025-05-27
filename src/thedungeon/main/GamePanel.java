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

public class GamePanel extends JPanel {
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

   


}
