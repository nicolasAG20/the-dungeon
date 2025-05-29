package utilz;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import thedungeon.main.Game;

/**
 * Clase utilitaria para cargar imagenes desde recursos del proyecto
 * @author Vanessa Toro Sepulveda
 */
public class LoadSave {              
    
    /**
     * Carga y devuelve una imagen desde los recursos del proyecto
     * 
     * @param fileName nombre del archivo de imagen con su ruta relativa
     * @return BufferedImage correspondiente al archivo cargado o null si ocurre un error
     */
    public static BufferedImage GetSpriteAtlas(String fileName){
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("/"+ fileName);        
        try {
            img = ImageIO.read(is);            

        } catch (IOException e) {
            System.out.println("archivo no encontrado ");
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
