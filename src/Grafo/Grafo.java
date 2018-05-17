/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import View.NodeBS;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Scanner;

/**
 *
 * @author fazd
 */
public class Grafo {
    
    private MyArray nodos;
    private MyArray aristas;

    public Grafo() {
        nodos = new MyArray();
        aristas = new MyArray();
    }
    
    
    public void addNodo(Nodo n){
        nodos.add(n);
    }
    
    public void addArista (Nodo a, Nodo b){
        aristas.add(new Arista(a,b));
    }
    
    
    
    public void print(){
        MyArray p = nodos;
        while(p != null){
            Nodo n = (Nodo) p.info;
            if(n != null){
                System.out.println("el nodo "+ n.getNombre() + " tiene:");
            }
            n.print();
            p= p.link;
        }
    }

    public void makeDependencies(Nodo in) throws FileNotFoundException {
        //MyArray p = nodos;
        //Nodo n = (Nodo) p.info;
        NodeBS nodo = in.getNodo();
        String folder = nodo.getRootPath();
        String pathName = folder+"/"+in.getNombre()+".txt";
        System.out.println("abre el archivo "+pathName);
        File f = new File(pathName);
        Scanner lector = new Scanner(f);
        String line = lector.nextLine();
        if(lector.hasNext()){
            line = lector.nextLine();
            String [] K = line.split(",");

            for(int i = 0; i < K.length; i++){
                Nodo temp = MyArray.find(nodos, K[i]);
                System.out.println("en in:"+ in.getNombre()+" se encontro "+
                        temp.getNombre());
                if(nodo == null){
                    System.out.println("badd");
                }
                else{
                    addArista(in, temp);
                }
            }
        }
    }

    public MyArray getNodos() {
        return nodos;
    }

    public MyArray getAristas() {
        return aristas;
    }
    
    
    public static void main(String[] args) {
        Grafo g = new Grafo();
        NodeBS n1BS = new NodeBS("n1BS", 500);
        NodeBS n2BS = new NodeBS("n2BS", 500);
        NodeBS n3BS = new NodeBS("n3BS", 500);
        NodeBS n4BS = new NodeBS("n4BS", 500);
        NodeBS n5BS = new NodeBS("n5BS", 500);
        NodeBS n6BS = new NodeBS("n6BS", 500);
        NodeBS n7BS = new NodeBS("n7BS", 500);
        
        Nodo n1 = new Nodo(n1BS);
        Nodo n2 = new Nodo(n2BS);
        Nodo n3 = new Nodo(n3BS);
        Nodo n4 = new Nodo(n4BS);
        Nodo n5 = new Nodo(n5BS);
        Nodo n6 = new Nodo(n6BS);
        Nodo n7 = new Nodo(n7BS);
        
        
        
        g.addNodo(n1);
        g.addNodo(n2);
        g.addNodo(n3);
        g.addNodo(n4);
        g.addNodo(n5);
        g.addNodo(n6);
        g.addNodo(n7);
        
        g.addArista(n1, n2);
        g.addArista(n1, n3);
        g.addArista(n1, n4);
        g.addArista(n1, n5);
        g.addArista(n1, n6);
        g.addArista(n1, n7);
        g.addArista(n2, n3);
        g.addArista(n2, n4);
        g.addArista(n2, n5);
        g.addArista(n2, n6);
        g.addArista(n2, n7);
        
        
        
        
        System.out.println("el tamaño es: "+g.nodos.size());
        g.print();
        
        
        
        
        
    }
    
    
    
}
