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

    /**
     * Este constructor crea el grafo e inicializa dos listas, nodos y aristas
     */
    
    public Grafo() {
        nodos = new MyArray();
        aristas = new MyArray();
    }
    
    /**
     * Esta subrutina agrega nodos al grafo
     * @param n nodo que quiere ser agregado
     */
    
    public void addNodo(Nodo n){
        nodos.add(n);
    }
    
    /**
     * Esta subrutina agrega una arista al grafo
     * @param a nodo inicial
     * @param b nodo final 
     */
    
    
    public void addArista (Nodo a, Nodo b){
        aristas.add(new Arista(a,b));
    }
    
    /**
     * Esta subrutina imprime el grafo
     */
    
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
    
    /**
     * Esta subrutina crea las dependencias entre los nodos 
     * @param in nodo que quiere tener dependencias 
     * @throws FileNotFoundException si el archivo no existe
     */

    public void makeDependencies(Nodo in) throws FileNotFoundException {
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
                if(nodo == null){
                    System.out.println("badd");
                }
                else{
                    System.out.println("en in:"+ in.getNombre()+" se encontro "+
                        temp.getNombre());
                    addArista(in, temp);
                }
            }
        }
    }

    /**
     * Función que retorna la lista de nodos
     * @return lista de nodos
     */
    
    public MyArray getNodos() {
        return nodos;
    }

    /**
     * Funcion que retorna la lista de aristas
     * @return lista de aristas
     */
    
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

        //DrawGraph dg = new DrawGraph(g,);
        //dg.setVisible(true);
        
        System.out.println("el tamaño es: "+g.nodos.size());
        g.print();

        
    }
    
    
    
}
