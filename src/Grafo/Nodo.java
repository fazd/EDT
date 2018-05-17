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
    
    public Nodo(NodeBS nodo) {
        this.nodo = nodo;
        nodos = new MyArray();
        nombre = nodo.getNombre();
    }

    public Nodo(String nombre) {
        this.nombre = nombre;
        nodos = new MyArray();
    }
    
    
    
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
    
    
    public void addAdy(Nodo n){
        nodos.add(n);
    }

    public NodeBS getNodo() {
        return nodo;
    }

    public MyArray getNodos() {
        return nodos;
    }

    public String getNombre() {
        return nombre;
    }
    
    
    
    
}
