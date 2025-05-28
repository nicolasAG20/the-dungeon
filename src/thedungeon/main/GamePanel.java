package thedungeon.main;

// @author vanes

import baseThedungeon.models.Lector;
import inputs.KeyboardInputs;
import inputs.MouseInputs;
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

public class GamePanel extends JPanel {
    private Game game;
    private JLabel vidaPlayer;
    private JLabel atkPlayer;
    private JLabel defPlayer;
    private JLabel escudoPlayer;
    private JLabel contadorEscudo;
    private JLabel vidaEnemigos;
    private JLabel[] mejoras= new JLabel[3];
    private JLabel rondaMj; 
    private int entero=0;
    private BufferedImage fondo;
    
    private int shieldCooldown=0;
    
    public GamePanel(Game game) {
       
        this.game = game;
        setPanelSize();
        setFocusable(true);
        addKeyListener(new KeyboardInputs(this));
        requestFocusInWindow();
        fondo= LoadSave.GetSpriteAtlas("images/backGround.png");
        initLabels();
        
    }

    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setPreferredSize(size);
    }

    public void updateGame() {

    }

    @Override
    public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(fondo, 0, 0,GAME_WIDTH,GAME_HEIGHT, this);
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
            textEnemies();
            vidaEnemigos.setLocation(200,550);
            if(game.isRondaMejora()){
                if(entero==0){
                    initMejoras();
                    entero=1;
                }
                
                locateMejoras();
            }else{
                hideMejoras();
                entero=0;
            }
            game.render(g);
    }

    public Game getGame() {
            return game;
    }

    public void initLabels(){
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
        
        this.add(atkPlayer);
        this.add(defPlayer);
        this.add(vidaEnemigos);
        this.add(contadorEscudo);
        this.add(escudoPlayer);
        this.add(vidaPlayer);
    }
   
    public void initMejoras(){
        rondaMj = new JLabel("ronda Mejora");
         rondaMj.setForeground(Color.WHITE);
         rondaMj.setFont(new Font("Arial", Font.BOLD, 16));
         rondaMj.setBounds(300, 50, 150, 80);
         this.add(rondaMj);
        Mejora[] mejorasJuego= game.getMejoras();
        for(int i=0; i<3; i++){
            if (mejorasJuego[i] != null) {
            mejoras[i] = new JLabel((i+1) + ") " +mejorasJuego[i].getInfoMejora());
            mejoras[i].setForeground(Color.WHITE);
            mejoras[i].setFont(new Font("Arial", Font.BOLD, 16));
            mejoras[i].setBounds(300, 50, 300, 80);
            this.add(mejoras[i]);
            }
        }
    }
     public void locateMejoras(){
        rondaMj.setLocation(250, 5);
        for(int i=0; i<3; i++){
           if(mejoras[i] != null){
                if(i==0){
                mejoras[i].setLocation(20, 100);
                 }else if(i==1){
                mejoras[i].setLocation(20, 150);
                 }else{
                mejoras[i].setLocation(20, 200);
                 }
           }
           
           
        }
    }
     
     public void hideMejoras(){
         rondaMj.setText("");
         for(int i=0; i<3 ; i++){
             mejoras[i].setText("");
         }
     }

    public void setShieldCooldown(int shieldCooldown) {
        this.shieldCooldown = shieldCooldown;
    }
    
    public void textEnemies(){
        if(game.getEnemigosVivos()==0){
            vidaEnemigos.setText("");
        }else{
            String text="" ; 
            for(int i=0; i<game.getEnemies().size(); i++){
                text+= "-vida enemigo "+ (i+1) + ": "+ (int)game.getEnemies().get(i).getHp()+" ";
            }
            vidaEnemigos.setText(text);
        }
    }
     

}
