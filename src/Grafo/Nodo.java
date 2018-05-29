/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import View.NodeBS;

/**
 *
 * @author fazd
 */
public class Nodo {
    
    private NodeBS nodo;
    private MyArray nodos;
    private String nombre;
   
    /**
     * Este constructor recibe un NodoBS y lo transforma en un nodo del grafo
     * @param nodo  nodo que será guardado en el grafo
     */
    
    
    public Nodo(NodeBS nodo) {
        this.nodo = nodo;
        nodos = new MyArray();
        nombre = nodo.getNombre();
    }

    /**
     * nodo creado a partir de un nombre (para pruebas)
     * @param nombre nombre del nodo
     */
    
    public Nodo(String nombre) {
        this.nombre = nombre;
        nodos = new MyArray();
    }
    
    /**
     * esta subrutina imprime los nodos adyacentes a este
     */
    
    public void print(){
        MyArray p = nodos;
        while(p!=null){
            Nodo n = (Nodo) p.info;
            if(n != null){
                System.out.println(n.getNombre());
            }
            p = p.link;
        }
    }
    
    /**
     * Esta función agrega un nodo adyacente
     * @param n 
     */
    
    public void addAdy(Nodo n){
        nodos.add(n);
    }

    /**
     * esta funcion retorna la información del nodo
     * @return retorna NodeBS almacenado
     */
    
    public NodeBS getNodo() {
        return nodo;
    }

    /**
     * Esta función retorna la lista de nodos adyacentes
     * @return lista de nodos adyacentes
     */
    
    public MyArray getNodos() {
        return nodos;
    }

    /**
     * esta función retorna el nombre del nodo
     * @return  nombre del nodo
     */
    
    public String getNombre() {
        return nombre;
    }
    
    
    
    
}
