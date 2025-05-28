package baseThedungeon.models;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/*
Clase encargada par aescribir en un archivo de texto planoa
@author Nicolas Agudelo
*/
public class EscritorArchivoTextoPlano {
    
    /*
    atributos 
    Set de caracteres que se guardan 
    */
    private final Charset charset = Charset.forName("UTF-8");
    //String que muestra la ruta del archivo
    private String filePath;
    /*
    @param String filePath ruta que se le da a la clase para escribir 
    constructor que define la ruta del archivo
    */
    public EscritorArchivoTextoPlano(String filePath) {
        this.filePath = filePath;
    }
 

    /*
    metodo que escribe en el archivo basado en un arraylist de contenido
    @param ArrayList<String> archivo , variable que contiene el contenido que se le va a agregar al archivo
    */
    
    
    public void escribir(ArrayList<String> archivo) throws IOException {

        File fichero = new File(this.filePath);
        FileWriter writer = new FileWriter(fichero,false);
        PrintWriter pw = new PrintWriter(writer);
        
        for (String linea : archivo) {
            pw.println(linea);
        }
        
        writer.close();
    }
   

}