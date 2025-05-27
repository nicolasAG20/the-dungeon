package thedungeon.main;

// @author vanes

import TheDungeon.models.Usu;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JPanel;
import thedungeon.models.Ataque;
import thedungeon.models.Enemy;
import thedungeon.models.Enemy1;
import thedungeon.models.Player;

public class Game  implements Runnable {
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;
    private Player player;
    private ArrayList<Enemy> enemies; 
    private int enemigoAAtacar=0;
    
    private int ronda=0; 

    public final static int TILES_DEFAULT_SIZE = 25;
    public final static float SCALE = 1.5f;
    public final static int TILES_IN_WIDTH = 16;
    public final static int TILES_IN_HEIGHT = 16;
    public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;   
    
    
    public Game() throws IOException{
        initClasses();   
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        enemies = new ArrayList<>();
        addNewEnemy(1);
        System.out.println(enemies.size());
        startGameLoop();
    }
    
    private void initClasses() throws IOException{
        player = new Player(40, 120, (int) ( 125 * SCALE), (int) (125 * SCALE));
    }
    
    private void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    public void update(){       
        player.update();
    }
    
    public void render(Graphics g){
        player.render(g);
    }
    
    @Override
    public void run() {
        double timePerFrame = 1000000000.0/FPS_SET;
        double timePerUpdate = 1000000000.0/UPS_SET;
             
        long previousTime = System.nanoTime();
        
        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();
        
        double deltaU = 0;
        double deltaF = 0;
        
        while(true){
            long currentTime = System.nanoTime();
            
            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;           
            previousTime = currentTime;
            
            if(deltaU >= 1){
                update();
                updates++;
                deltaU--;
            }
            if(deltaF >= 1){
                gamePanel.repaint();                
                frames++;            
                deltaF--;
            }
                           
            if (System.currentTimeMillis()- lastCheck >= 1000){
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: "+ frames + " UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        
        }
    }

    public Player getPlayer() {
        return player;
    }
    
    
    public void addNewEnemy(int dificulty){
        int enemyNum = (int) (Math.random() * (2 - 1 + 1)) + 1;
        Enemy newEnemy;
        if(enemyNum==1){
             newEnemy = new Enemy1(40, 12 , (int) (125 * SCALE), (int) (125 * SCALE), dificulty);
            
        }else{
             newEnemy = new Usu(40, 12 , (int) (125 * SCALE), (int) (125 * SCALE), dificulty);
            
        }
        enemies.add(newEnemy);
    }
     
    public void atacarEnemigo(Ataque ataque){
        enemies.get(enemigoAAtacar).recibirDaño(ataque);
        Enemy enemigoActual = enemies.get(enemigoAAtacar);
        if(enemies.get(enemigoAAtacar).getHp()<=0){
            enemies.remove(enemigoAAtacar);
        }else{
            player = enemigoActual.atacar(player);
            System.out.println(player.getHp() + " " +enemigoActual.getHp());
        }
    }

    public void setEnemigoAAtacar(int num){
        enemigoAAtacar= num;
    }
    
    public int getEnemigosVivos(){
        return enemies.size();
    }
   
}
