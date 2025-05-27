/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baseThedungeon.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/**
 * Clase para leer y procesar archivos de texto plano
 * @author Nicolas Agudelo Grajales
 */
public class Lector {
    File archivo; 
    FileReader lector; 
    BufferedReader buffer; 
    /**
     * Constrcutor que incializa el lector con un archivo especifico
     * @param archivo Archivo de configuracion a leer
     * @throws FileNotFoundException Si el archivo no existe o no puede leerse
     */
    public Lector(String archivo) throws FileNotFoundException {
        this.archivo = new File(archivo);
        this.lector= new FileReader(archivo);
        this.buffer = new BufferedReader(lector);
    }
    /**
     * Lee el archvio y devuelve un array con los valores de configuracion
     * @return Array de Strin con los valores en el formato
     * @throws IOException Si ocurre un error de lectura del archivo
     */
    public ArrayList<String> devolverTexto() throws IOException{
        ArrayList<String> texto= new ArrayList<>();
        String linea; 
        int i=0; 
        while((linea=this.buffer.readLine())!=null){
            texto.add(linea);
           
        }
        
        return texto; 
    }
  
    /**
     * Separa una linea de texto en dospartes usando el primer espacio como delimitador
     * @param texto de texto a separar
     * @return Array con dos elementos
     */
    public String[] separarEspacios(String texto){
        String[] textoSeparado = new String[2];
        String textoObtenido="";
        texto+=" ";
        int j=0; 
        for(int i=0; i<texto.length(); i++){
            if(texto.charAt(i)!=' '){
                textoObtenido +=texto.charAt(i);
                
            }else{
                textoSeparado[j]= textoObtenido;
                textoObtenido="";
                j++;
            }
        }
        return textoSeparado; 
    }
}
