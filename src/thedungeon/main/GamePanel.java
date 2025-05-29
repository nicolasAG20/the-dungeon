package thedungeon.main;

import baseThedungeon.models.Lector;
import inputs.KeyboardInputs;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import static thedungeon.main.Game.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.swing.JLabel;
import thedungeon.models.Mejora;
import utilz.LoadSave;

/**
 * Panel principal del juego que maneja la visualizacion
 * Hereda de JPanel para mostrar los elementos graficos
 * @author Vanessa Toro Sepulveda,Nicolas Agudelo Grajales
 */
public class GamePanel extends JPanel {
    private Game game;
    private JLabel vidaPlayer;
    private JLabel atkPlayer;
    private JLabel defPlayer;
    private JLabel escudoPlayer;
    private JLabel contadorEscudo;
    private JLabel vidaEnemigos;
    private JLabel textoPerder;
    private JLabel rondaActual;
    private JLabel puntajeActual;
    private JLabel puntajeMax;
    private JLabel[] mejoras = new JLabel[3];
    private JLabel rondaMj; 
    private int entero = 0;
    private BufferedImage fondo;
    
    private int shieldCooldown = 0;
    
    /**
     * Constructor del panel del juego
     * 
     * @param game Instancia del juego principal
     */
    public GamePanel(Game game) {
        this.game = game;
        setPanelSize();
        setFocusable(true);
        addKeyListener(new KeyboardInputs(this));
        requestFocusInWindow();
        fondo = LoadSave.GetSpriteAtlas("images/backGround.png");
        initLabels();
    }

    /**
     * Establece el tamaño del panel segun las constantes del juego
     */
    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setPreferredSize(size);
    }

    /**
     * Actualiza el estado del juego (actualmente vacio)
     */
    public void updateGame() {
        // Metodo para actualizaciones del juego
    }

    /**
     * Dibuja todos los componentes del juego
     * 
     * @param g Objeto Graphics para renderizar
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondo, 0, 0, GAME_WIDTH, GAME_HEIGHT, this);
        locateTextos();
            
        if(game.isRondaMejora()) {
            if(entero == 0) {
                initMejoras();
                entero = 1;
            }
            locateMejoras();
        } else {
            hideMejoras();
            entero = 0;
        }
        
        if(!game.isJugadorVivo()) {
            textoPerder.setText("Ha perdido!! presione R para reiniciar");
            textoPerder.setLocation(40, 200);
        } else {
            textoPerder.setText("");
        }
        game.render(g);
    }

    /**
     * Inicializa las etiquetas de informacion del juego
     */
    public void initLabels() {
        vidaPlayer = new JLabel((float)game.getPlayer().getHp() + "/"+ game.getPlayer().getHpMax());
        vidaPlayer.setForeground(Color.RED);
        vidaPlayer.setFont(new Font("Arial", Font.BOLD, 16));
        vidaPlayer.setBounds(300, 50, 150, 80);
        vidaPlayer.setLocation(50, 400);
        
        escudoPlayer = new JLabel((float)game.getPlayer().getShield() + "");
        escudoPlayer.setForeground(Color.CYAN);
        escudoPlayer.setFont(new Font("Arial", Font.BOLD, 16));
        escudoPlayer.setBounds(20, 550, 150, 80);
        escudoPlayer.setLocation(20, 550);
        
        contadorEscudo = new JLabel(""+ shieldCooldown);
        contadorEscudo.setForeground(Color.CYAN);
        contadorEscudo.setFont(new Font("Arial", Font.BOLD, 16));
        contadorEscudo.setBounds(20, 550, 150, 80);
        contadorEscudo.setLocation(20, 550);
        
        vidaEnemigos = new JLabel(""+ shieldCooldown);
        vidaEnemigos.setForeground(Color.ORANGE);
        vidaEnemigos.setFont(new Font("Arial", Font.BOLD, 16));
        vidaEnemigos.setBounds(20, 550, 150, 120);
        vidaEnemigos.setLocation(20, 550);
        
        atkPlayer = new JLabel(""+ shieldCooldown);
        atkPlayer.setForeground(Color.ORANGE);
        atkPlayer.setFont(new Font("Arial", Font.BOLD, 16));
        atkPlayer.setBounds(20, 550, 150, 120);
        atkPlayer.setLocation(20, 550);
        
        defPlayer = new JLabel(""+ shieldCooldown);
        defPlayer.setForeground(Color.BLUE);
        defPlayer.setFont(new Font("Arial", Font.BOLD, 16));
        defPlayer.setBounds(20, 550, 150, 120);
        defPlayer.setLocation(20, 550);
        
        textoPerder = new JLabel();
        textoPerder.setForeground(Color.RED);
        textoPerder.setFont(new Font("Arial", Font.BOLD, 25));
        textoPerder.setBounds(20, 550, 150, 120);
        textoPerder.setLocation(20, 550);
        
        rondaActual = new JLabel();
        rondaActual.setForeground(Color.WHITE);
        rondaActual.setFont(new Font("Arial", Font.BOLD, 16));
        rondaActual.setBounds(20, 550, 150, 120);
        rondaActual.setLocation(20, 550);
        
        puntajeActual = new JLabel();
        puntajeActual.setForeground(Color.WHITE);
        puntajeActual.setFont(new Font("Arial", Font.BOLD, 16));
        puntajeActual.setBounds(20, 550, 150, 120);
        puntajeActual.setLocation(20, 550);
        
        puntajeMax = new JLabel();
        puntajeMax.setForeground(Color.WHITE);
        puntajeMax.setFont(new Font("Arial", Font.BOLD, 16));
        puntajeMax.setBounds(20, 550, 150, 120);
        puntajeMax.setLocation(20, 550);
        
        this.add(puntajeActual);
        this.add(puntajeMax);
        this.add(rondaActual);
        this.add(textoPerder);
        this.add(atkPlayer);
        this.add(defPlayer);
        this.add(vidaEnemigos);
        this.add(contadorEscudo);
        this.add(escudoPlayer);
        this.add(vidaPlayer);
    }
   
    /**
     * Inicializa las etiquetas de mejoras
     */
    public void initMejoras() {
        rondaMj = new JLabel("ronda Mejora");
        rondaMj.setForeground(Color.WHITE);
        rondaMj.setFont(new Font("Arial", Font.BOLD, 16));
        rondaMj.setBounds(300, 50, 150, 80);
        this.add(rondaMj);
        
        Mejora[] mejorasJuego = game.getMejoras();
        for(int i = 0; i < 3; i++) {
            if (mejorasJuego[i] != null) {
                mejoras[i] = new JLabel((i+1) + ") " + mejorasJuego[i].getInfoMejora());
                mejoras[i].setForeground(Color.WHITE);
                mejoras[i].setFont(new Font("Arial", Font.BOLD, 16));
                mejoras[i].setBounds(300, 50, 300, 80);
                this.add(mejoras[i]);
            }
        }
    }
    
    /**
     * Ubica y actualiza los textos informativos
     */
    public void locateTextos() {
        vidaPlayer.setText((int)(game.getPlayer().getHp()) + "/"+ game.getPlayer().getHpMax()+ " vida");
        vidaPlayer.setLocation(20, 480);
        escudoPlayer.setText((int)(game.getPlayer().getShield()) + " escudo");
        escudoPlayer.setLocation(20, 500);
        defPlayer.setText("Def:"+ game.getPlayer().getDefense());
        defPlayer.setLocation(20, 525);
        atkPlayer.setText("ATK:" + game.getPlayer().getAtk());
        atkPlayer.setLocation(20, 550);
        contadorEscudo.setText("escudo coolDown : " + shieldCooldown);
        contadorEscudo.setLocation(150, 480);
        rondaActual.setText("ronda actual: " + game.getRonda());
        rondaActual.setLocation(250, 30);
        puntajeMax.setText("puntaje max: " + game.getPuntajeMax());
        puntajeMax.setLocation(0, 10);
        puntajeActual.setText("puntaje: " + game.getPuntajeActual());
        puntajeActual.setLocation(0, 40);
        
        textEnemies();
        vidaEnemigos.setLocation(200,550);
    }
    
    /**
     * Ubica las etiquetas de mejoras en pantalla
     */
    public void locateMejoras() {
        rondaMj.setLocation(250, 5);
        for(int i = 0; i < 3; i++) {
            if(mejoras[i] != null) {
                if(i == 0) {
                    mejoras[i].setLocation(20, 100);
                } else if(i == 1) {
                    mejoras[i].setLocation(20, 150);
                } else {
                    mejoras[i].setLocation(20, 200);
                }
            }
        }
    }
     
    /**
     * Oculta las etiquetas de mejoras
     */
    public void hideMejoras() {
        rondaMj.setText("");
        for(int i = 0; i < 3; i++) {
            if(mejoras[i] != null) {
                mejoras[i].setText("");
            }
        }
    }

    /**
     * Establece el cooldown del escudo
     * 
     * @param shieldCooldown Tiempo de cooldown restante
     */
    public void setShieldCooldown(int shieldCooldown) {
        this.shieldCooldown = shieldCooldown;
    }
    
    /**
     * Actualiza el texto de vida de los enemigos
     */
    public void textEnemies() {
        if(game.getEnemigosVivos() == 0) {
            vidaEnemigos.setText("");
        } else {
            String text = ""; 
            for(int i = 0; i < game.getEnemies().size(); i++) {
                text += "-vida enemigo "+ (i+1) + ": "+ (int)game.getEnemies().get(i).getHp()+" ";
            }
            vidaEnemigos.setText(text);
        }
    }

    /**
     * Establece una nueva instancia del juego
     * 
     * @param game Nueva instancia del juego
     */
    public void setGame(Game game) {
        this.game = game;
    }
     
    /**
     * Metodo para cerrar el juego (actualmente vacio)
     */
    public void cerrarJuego() {
        // Metodo para cerrar el juego
    }

    /**
     * Obtiene la instancia del juego
     * 
     * @return Instancia del juego
     */
    public Game getGame() {
        return game;
    }
}