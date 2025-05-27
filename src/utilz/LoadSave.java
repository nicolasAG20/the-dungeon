package utilz;

// @author vanes

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import thedungeon.main.Game;


public class LoadSave {
  
    public static final String ENEMY_1 = "images/enemigo1P.png";
    public static final String ENEMY_2 = "images/enemigo2P.png";
    public static final String PLAYER = "images/jugadorP.png";    
    public static final String FINAL_BOSS = "images/jefeFinalP.png";               
    
    public static BufferedImage GetSpriteAtlas(String fileName){
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("/"+ fileName);        
        try {
            img = ImageIO.read(is);            

        } catch (IOException e) {
            System.out.println("archivo no encontrado");
        }finally{
            try{
                is.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return img;     
    }
    
}
