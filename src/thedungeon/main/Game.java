package thedungeon.main;

import baseThedungeon.models.EscritorArchivoTextoPlano;
import baseThedungeon.models.Lector;
import thedungeon.models.Usu;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JPanel;
import thedungeon.models.Ataque;
import thedungeon.models.Cura;
import thedungeon.models.Enemy;
import thedungeon.models.Enemy1;
import thedungeon.models.FinalBoss;
import thedungeon.models.Mejora;
import thedungeon.models.MejoraAtaque;
import thedungeon.models.MejoraDefensa;
import thedungeon.models.MejoraVida;
import thedungeon.models.Player;

/**
 * Clase principal que controla la logica del juego
 * Implementa Runnable para manejar el bucle del juego
 * @author Vanessa Toro Sepulveda,Nicolas Agudelo Grajales
 */
public class Game implements Runnable {
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;
    private Player player;
    private ArrayList<Enemy> enemies; 
    private Mejora[] mejoras;
    private int enemigoAAtacar = 0;
    private int shieldCooldown = 0;
    
    int puntajeMax; 
    int puntajeActual = 0; 
    
    private int ronda = 0; 
    private int dificultad = 1; 
    private boolean rondaMejora = false;
    private boolean jugadorVivo = true;
    
    // Constantes para el tamaño del juego
    public final static int TILES_DEFAULT_SIZE = 25;
    public final static float SCALE = 1.5f;
    public final static int TILES_IN_WIDTH = 16;
    public final static int TILES_IN_HEIGHT = 16;
    public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;   
    
    /**
     * Constructor principal del juego
     * Inicializa los componentes y comienza el bucle del juego
     * 
     * @throws IOException Si hay error al cargar recursos
     */
    public Game() throws IOException {
        initClasses();   
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        enemies = new ArrayList<>();
        System.out.println(enemies.size());
        iniciarPuntajes();
        startGameLoop();
    }
    
    /**
     * Inicializa las clases principales del juego
     * 
     * @throws IOException Si hay error al cargar recursos
     */
    private void initClasses() throws IOException {
        player = new Player(40, 225, (int) (125 * SCALE), (int) (125 * SCALE));
    }
    
    /**
     * Inicia el bucle principal del juego
     */
    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    /**
     * Actualiza el estado del juego
     * 
     * @throws IOException Si hay error durante la actualizacion
     */
    public void update() throws IOException {       
        player.update();
        for(int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update();
        }
        if(enemies.isEmpty() && !rondaMejora) {
            pasarDeRonda();
        }
    }
    
    /**
     * Renderiza todos los elementos del juego
     * 
     * @param g Objeto Graphics para dibujar
     */
    public void render(Graphics g) {
        player.render(g);
        for(int i = 0; i < enemies.size(); i++) {
            enemies.get(i).render(g);
        }
    }
    
    /**
     * Bucle principal del juego que controla FPS y UPS
     */
    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;
             
        long previousTime = System.nanoTime();
        
        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();
        
        double deltaU = 0;
        double deltaF = 0;
        
        while(true) {
            long currentTime = System.nanoTime();
            
            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;           
            previousTime = currentTime;
            
            if(deltaU >= 1) {
                try {
                    update();
                } catch (IOException ex) {}
                updates++;
                deltaU--;
            }
            if(deltaF >= 1) {
                gamePanel.repaint();                
                frames++;            
                deltaF--;
            }
                           
            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                frames = 0;
                updates = 0;
            }
        }
    }

    /**
     * Obtiene el jugador
     * 
     * @return El objeto Player
     */
    public Player getPlayer() {
        return player;
    }
    
    /**
     * Añade un nuevo enemigo al juego
     * 
     * @param dificulty Nivel de dificultad del enemigo
     * @param pos Posicion donde aparecera el enemigo
     * @throws FileNotFoundException Si no encuentra archivos necesarios
     * @throws IOException Si hay error de entrada/salida
     */
    public void addNewEnemy(int dificulty, int pos) throws FileNotFoundException, IOException {
        int enemyNum = (int) (Math.random() * (2 - 1 + 1)) + 1;
        Enemy newEnemy;
        int x;
        int y; 
        if(pos == 1) {
            x = 360;
            y = 250;
        } else {
            x = 400;
            y = 70;
        }
        if(enemyNum == 1) {
            newEnemy = new Enemy1(x, y, (int) (125 * SCALE), (int) (125 * SCALE), dificulty);
        } else {
            newEnemy = new Usu(x, y, (int) (125 * SCALE), (int) (125 * SCALE), dificulty);
        }
        enemies.add(newEnemy);
    }
     
    /**
     * Realiza un ataque contra el enemigo actual
     * 
     * @param ataque El ataque a realizar
     * @throws IOException Si hay error durante el ataque
     */
    public void atacarEnemigo(Ataque ataque) throws IOException {
        shieldCooldown--;
        enemies.get(enemigoAAtacar).recibirDaño(ataque);
        Enemy enemigoActual = enemies.get(enemigoAAtacar);
        if(enemies.get(enemigoAAtacar).getHp() < 1) {
            puntajeActual += enemies.get(enemigoAAtacar).getPuntos();
            enemies.remove(enemigoAAtacar);
        }
        
        for(int i = 0; i < enemies.size(); i++) {
            player = enemies.get(i).atacar(player);
        }
        if(player.getHp() < 1) {
            player.setHp(0);
            jugadorVivo = false;
        }
        System.out.println(player.getHp() + " " + player.getShield() + " " + enemigoActual.getHp());
    }
    
    /**
     * Ataque basico contra enemigos
     * 
     * @throws IOException Si hay error durante el ataque
     * @throws InterruptedException Si se interrumpe el hilo
     */
    public void atacarEnemigo() throws IOException, InterruptedException {
        for(int i = 0; i < enemies.size(); i++) {
            player = enemies.get(i).atacar(player);
        }
        if(player.getHp() < 1) {
            player.setHp(0);
            jugadorVivo = false;
        }
        System.out.println(player.getHp() + " " + player.getShield());
    }

    /**
     * Establece el enemigo a atacar
     * 
     * @param num Indice del enemigo en la lista
     */
    public void setEnemigoAAtacar(int num) {
        enemigoAAtacar = num;
    }
    
    /**
     * Obtiene la cantidad de enemigos vivos
     * 
     * @return Numero de enemigos vivos
     */
    public int getEnemigosVivos() {
        return enemies.size();
    }

    /**
     * Indica si es una ronda de mejora
     * 
     * @return true si es ronda de mejora, false en caso contrario
     */
    public boolean isRondaMejora() {
        return rondaMejora;
    }
   
    /**
     * Avanza a la siguiente ronda del juego
     * 
     * @throws IOException Si hay error al generar la ronda
     */
    public void pasarDeRonda() throws IOException {
        ronda++;
        if(ronda == 5 || ronda == 9) {
            generarRondaMejora();
            player.curar(dificultad);
        } else if(ronda == 1) {
            generarRondaMejora();
        } else if(ronda == 2) {
            addNewEnemy(dificultad, 1);
            addNewEnemy(dificultad, 2);
        } else if(ronda % 4 == 0 || (ronda % 4 == 0 && ronda % 3 == 0)) {
            int x = 150;
            int y = 0;
            FinalBoss boss = new FinalBoss(x, y, (int) (300 * SCALE), (int) (300 * SCALE), dificultad);
            enemies.add(boss);
            dificultad++;
        } else if(ronda % 3 == 0 && ronda != 3) {
            generarRondaMejora();
        } else {
            addNewEnemy(dificultad, 1);
            addNewEnemy(dificultad, 2);
        }
    }
    
    /**
     * Genera una ronda de mejoras para el jugador
     */
    public void generarRondaMejora() {
        rondaMejora = true;
        mejoras = new Mejora[3];
        for(int i = 0; i < 3; i++) {
            int numMejora;
            int randomStat;
            if(ronda != 1) {
                numMejora = (int) (Math.random() * (4) + 1);
                switch(numMejora) {
                    case 1: 
                        randomStat = (int) (Math.random() * (10 - 6 + 1) + 6);
                        mejoras[i] = new Cura(randomStat, dificultad);
                        break;
                    case 2: 
                        randomStat = (int) (Math.random() * (10 - 5 + 1) + 5);
                        mejoras[i] = new MejoraAtaque(randomStat, dificultad);
                        break;
                    case 3: 
                        randomStat = (int) (Math.random() * (10 - 6 + 1) + 6);
                        mejoras[i] = new MejoraDefensa(randomStat, dificultad);
                        break;
                    case 4: 
                        randomStat = (int) (Math.random() * (20 - 13 + 1) + 13);
                        mejoras[i] = new MejoraVida(randomStat, dificultad);
                        break;
                    default:
                        mejoras[i] = new MejoraDefensa(5, dificultad);
                        System.err.println("Numero de mejora inesperado: " + numMejora);
                        break;
                }
            } else {
                numMejora = (int) (Math.random() * (3) + 1);
                switch(numMejora) {
                    case 1: 
                        randomStat = (int) (Math.random() * (10 - 5 + 1) + 5);
                        mejoras[i] = new MejoraAtaque(randomStat, dificultad);
                        break;
                    case 2: 
                        randomStat = (int) (Math.random() * (10 - 6 + 1) + 6);
                        mejoras[i] = new MejoraDefensa(randomStat, dificultad);
                        break;
                    case 3: 
                        randomStat = (int) (Math.random() * (20 - 13 + 1) + 13);
                        mejoras[i] = new MejoraVida(randomStat, dificultad);
                        break;
                    default:
                        mejoras[i] = new MejoraDefensa(5, dificultad);
                        System.err.println("Numero de mejora inesperado: " + numMejora);
                        break;
                }
            }
           
            if (mejoras[i] == null) {
                mejoras[i] = new MejoraVida(7, dificultad); 
            }
        }
    }
    
    /**
     * Aplica una mejora al jugador
     * 
     * @param numMejora Indice de la mejora a aplicar
     * @throws IOException Si hay error al aplicar la mejora
     */
    public void usarMejora(int numMejora) throws IOException {
        player = mejoras[numMejora].aplicarMejora(player);
        pasarDeRonda();
        rondaMejora = false; 
    }

    /**
     * Obtiene las mejoras disponibles
     * 
     * @return Array de mejoras
     */
    public Mejora[] getMejoras() {
        return mejoras;
    }

    /**
     * Obtiene la lista de enemigos
     * 
     * @return Lista de enemigos
     */
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
    
    /**
     * Indica si el jugador esta vivo
     * 
     * @return true si el jugador esta vivo, false en caso contrario
     */
    public boolean isJugadorVivo() {
        return jugadorVivo;
    }

    /**
     * Obtiene el numero de ronda actual
     * 
     * @return Numero de ronda
     */
    public int getRonda() {
        return ronda;
    }
    
    /**
     * Inicializa los puntajes desde archivo
     * 
     * @throws FileNotFoundException Si no encuentra el archivo de puntajes
     * @throws IOException Si hay error al leer el archivo
     */
    public void iniciarPuntajes() throws FileNotFoundException, IOException {
        Lector lector = new Lector("puntaje.txt");
        ArrayList<String> puntaje = lector.devolverTexto();
        puntajeMax = Integer.parseInt(puntaje.get(0));
    }
    
    /**
     * Compara y actualiza los puntajes maximos
     * 
     * @throws IOException Si hay error al guardar los puntajes
     */
    public void compararPuntajes() throws IOException {
        if(puntajeActual > puntajeMax) {
            puntajeMax = puntajeActual;
            ArrayList<String> puntaje = new ArrayList<>();
            puntaje.add("" + puntajeMax);
            EscritorArchivoTextoPlano escritor = new EscritorArchivoTextoPlano("puntaje.txt");
            escritor.escribir(puntaje);
        }
    }

    /**
     * Obtiene el puntaje maximo
     * 
     * @return Puntaje maximo registrado
     */
    public int getPuntajeMax() {
        return puntajeMax;
    }

    /**
     * Obtiene el puntaje actual
     * 
     * @return Puntaje de la partida actual
     */
    public int getPuntajeActual() {
        return puntajeActual;
    }

    /**
     * Obtiene la ventana del juego
     * 
     * @return Objeto GameWindow
     */
    public GameWindow getGameWindow() {
        return gameWindow;
    }
}