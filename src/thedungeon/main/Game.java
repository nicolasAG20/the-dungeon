package thedungeon.main;

// @author vanes

import TheDungeon.models.Usu;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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

public class Game  implements Runnable {
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;
    private Player player;
    private ArrayList<Enemy> enemies; 
    private Mejora[] mejoras ;
    private int enemigoAAtacar=0;
    
    private int ronda=0; 
    private int dificultad= 1; 
    private boolean rondaMejora=false;

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
        System.out.println(enemies.size());
        startGameLoop();
    }
    
    private void initClasses() throws IOException{
        player = new Player(40, 200, (int) ( 125 * SCALE), (int) (125 * SCALE));
    }
    
    private void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    public void update() throws IOException{       
        player.update();
        for(int i=0; i<enemies.size(); i++){
            enemies.get(i).update();
        }
        if(enemies.isEmpty() && !rondaMejora){
            pasarDeRonda();
        }
    }
    
    public void render(Graphics g){
        player.render(g);
        for(int i=0; i<enemies.size(); i++){
            enemies.get(i).render(g);
        }
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
                try {
                    update();
                } catch (IOException ex) {}
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
    
    
    public void addNewEnemy(int dificulty, int pos) throws FileNotFoundException, IOException{
        int enemyNum = (int) (Math.random() * (2 - 1 + 1)) + 1;
        Enemy newEnemy;
        int x;
        int y; 
        if(pos==1){
             x= 360;
            y= 250;
        }else{
            x= 400;
            y= 70;
        }
        if(enemyNum==1){
            newEnemy = new Usu(x, y , (int) (125 * SCALE), (int) (125 * SCALE), dificulty); 
            //newEnemy = new Enemy1(100, 120 , (int) (125 * SCALE), (int) (125 * SCALE), dificulty);
            
        }else{
             newEnemy = new Usu(x, y , (int) (125 * SCALE), (int) (125 * SCALE), dificulty);
            
        }
        enemies.add(newEnemy);
    }
     
    public void atacarEnemigo(Ataque ataque) throws IOException  {
        enemies.get(enemigoAAtacar).recibirDaño(ataque);
        Enemy enemigoActual = enemies.get(enemigoAAtacar);
        if(enemies.get(enemigoAAtacar).getHp()<=0){
            enemies.remove(enemigoAAtacar);
            
        }
        
            for(int i=0; i< enemies.size(); i++){
               player= enemies.get(i).atacar(player);
            }
            System.out.println(player.getHp() +" "+ player.getShield() +" " +enemigoActual.getHp());
    }
    public void atacarEnemigo() throws IOException, InterruptedException  {
                    for(int i=0; i< enemies.size(); i++){
               player= enemies.get(i).atacar(player);
            }
            System.out.println(player.getHp() +" "+ player.getShield() );
    }

    public void setEnemigoAAtacar(int num){
        enemigoAAtacar= num;
    }
    
    public int getEnemigosVivos(){
        return enemies.size();
    }

    public boolean isRondaMejora() {
        return rondaMejora;
    }
   
    
    public void pasarDeRonda() throws IOException {
        ronda++;
        if(ronda==1){
            addNewEnemy(1,1);
        }else if(ronda==2){
            addNewEnemy(dificultad, 1);
            addNewEnemy(dificultad, 2);
        }else if(ronda%4==0 ||(ronda%4==0 && ronda%3==0) ){
            int x= 150;
            int y= 0;
            FinalBoss boss = new FinalBoss(x, y , (int) (300 * SCALE), (int) (300 * SCALE), dificultad);
            enemies.add(boss);
            dificultad++;
        }else if(ronda%3==0 ){
            generarRondaMejora();
        }else{
            addNewEnemy(dificultad, 1);
            addNewEnemy(dificultad, 2);
        }
        
    }
    public void generarRondaMejora(){
        rondaMejora= true;
        mejoras = new Mejora[3];
        for(int i=0; i<3; i++){
            int numMejora = (int) (Math.random()* (4-1+1)+1);
            int randomStat;
            switch(numMejora){
                case 1: 
                    randomStat= (int) (Math.random()* (10-6+1)+6);
                    mejoras[i]= new Cura(randomStat, dificultad);
                    break;
                case 2: 
                    randomStat = (int) (Math.random()* (10-5+1)+5);
                    mejoras[i]= new MejoraAtaque(randomStat, dificultad);
                    break;
                case 3: 
                    randomStat = (int) (Math.random()* (9-5+1)+5);
                    mejoras[i]= new MejoraDefensa(randomStat, dificultad);
                    break;
                case 4: 
                    randomStat = (int) (Math.random()* (18-10+1)+10);
                    mejoras[i]= new MejoraVida(randomStat, dificultad);
            }
        }
    }
    
    public void usarMejora(int numMejora) throws IOException{
        player = mejoras[numMejora].aplicarMejora(player);
        pasarDeRonda();
        rondaMejora= false; 
    }
}