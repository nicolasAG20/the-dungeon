package thedungeon.main;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import javax.swing.JFrame;

/**
 * Clase que representa la ventana principal del juego
 * Hereda de JFrame para crear la ventana de la aplicacion
 * @author Vanessa Toro Sepulveda
 */
public class GameWindow extends JFrame {
    private JFrame jframe;
    
    /**
     * Constructor que crea y configura la ventana del juego
     * 
     * @param gamePanel Panel del juego que contiene los elementos visuales
     */
    public GameWindow(GamePanel gamePanel) {
        jframe = new JFrame();

        // Configuracion basica de la ventana
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Cierra la aplicacion al salir
        jframe.add(gamePanel);  // Añade el panel del juego
        jframe.setResizable(false);  // Impide redimensionar la ventana
        jframe.pack();  // Ajusta el tamaño al contenido
        jframe.setLocationRelativeTo(null);  // Centra la ventana en pantalla
        jframe.setVisible(true);  // Hace visible la ventana
    }
}