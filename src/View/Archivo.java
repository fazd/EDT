/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fabio
 */
public class Archivo {
     /**
     * Esta subrutina escribe dentro del archivo la estructura del arbol
     * @param f Archivo donde se escribirá
     * @param f2 Archivo donde se guarda la info del nodo
     * @param node Nodo
     * @throws IOException  si no se puede escribir 
     */
    
    public static void write(File f,File f2, NodeBS node) throws IOException, Exception {
        String pathName = f.getAbsolutePath();
        String pathName2 = f2.getAbsolutePath();
        f.delete();
        f2.delete();
        f = new File(pathName);
        f2 = new File(pathName2);
        writeNode(f,f2,node);    
        
    }
    
    /**
     * Esta subrutina escribe dentro de los archivos f1 y f2 un nodo donde dentro del 
     * archivo f se escribe solo el nombre y en el f2 se escribe la informacion del nodo
     * @param f Archivo donde se escribirá la estructura del arbol
     * @param f2 Archivo donde se guarda la informacion del nodo
     * @param node Nodo que se escribirá en el archivo
     * @throws IOException  Cuando el archivo no existe
     */
        
    public static void writeNode(File f, File f2, NodeBS node) throws IOException, Exception{
        if(node != null){
            FileWriter fstream = new FileWriter(f, true);
            FileWriter fstream2 = new FileWriter(f2,true);
            BufferedWriter out = new BufferedWriter(fstream);
            BufferedWriter out2 = new BufferedWriter(fstream2);
            out.write(encryption.cifra(node.toString()));
            out.newLine();
            out.close();
            out2.write(encryption.cifra(node.toInfo()));
            out2.newLine();
            out2.close();
            for(int i =1 ; i<=node.numberChildren(); i++){
                writeNode(f,f2,node.nextChildren(i));
            }
        }
    }
        
    /**
     * Esta subrutina se encarga de copiar la informacion de un archivo a otro
     * @param ini Archivo de donde se copiará la información
     * @param dest Archivo donde se escbribirá la información
     * @throws IOException Si alguno de los archivos no existe 
     */
    
    public static void copyFile(File ini, File dest) throws IOException, Exception{
        try {
            Scanner lector = new Scanner(ini);
            FileWriter fstream = new FileWriter(dest, true);
            BufferedWriter out = new BufferedWriter(fstream);
            while(lector.hasNext()){
                String line = lector.nextLine();
                line = encryption.descifra(line);
                out.write(line);
                out.newLine();
            }
            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Validations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
     
    /**
     * Esta subrutina crea un archivo dentro de la carpeta files
     * Si la carpeta files no existe, la crea
     * @param node Nodo el cual será el padre de todo el proyecto  
     * @param name Nombre del que creo el proyecto 
     * @throws Exception si la encriptación falla  
     */
    
    public static void createProyectFile(NodeBS node,String name) throws Exception{
        String folder = "files";
        File temp = new File(folder);
        if(!temp.exists()){
            temp.mkdir();
        }
        folder +="/"+name;
        temp = new File(folder);
        temp.mkdir();
        String pathName = folder+"/"+name+".txt";
        String pathName2 = folder+"/"+name+"_info.txt";
        File f = new File(pathName);
        if(!f.exists()){
            try {
                //f.mkdir();
                f.createNewFile();
                File f2 = new File(pathName2);
                f2.createNewFile();
                write(f,f2,node);

            } catch (IOException ex) {
                Logger.getLogger(Validations.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void createEntregable(NodeBS nodo) {
        String folder = nodo.getRootPath();
        String pathName = folder+"/"+nodo.getNombre()+".txt";
        System.out.println("path:"+ pathName);
        File f = new File(pathName);
        try {
            if(!f.exists()){
                f.createNewFile();
                FileWriter fstream = new FileWriter(f, true);
                BufferedWriter out = new BufferedWriter(fstream);
                out.write(nodo.toInfo());
                out.close();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
