/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import View.NodeBS;
import View.NodeList;

/**
 *
 * @author fazd
 */
public class MyArray {
    
    MyArray link;
    Object info;

    public MyArray() {
    }

    public MyArray(Object info) {
        this.info = info;
    }
    
    public void add(Nodo node){
        if(this.link == null && this.info == null){
            this.info = node;
        }
        else if(this.link==null){
            this.link = new MyArray(node);
        }
        else{
            MyArray p = this;
            while(p.link!= null){
                p = p.link;
            }
            p.link = new MyArray(node);
        }            
    }   

    
    public void add(Arista a){
        if(info == null){
            this.info = a;
        }
        else{
            MyArray q = link;
            while(q != null){
                q = q.link;
            }
            q = new MyArray(a);
        }
    }
    
    
    public static Nodo find(MyArray lista, String nombre){
        MyArray p = lista;
        while(p!= null){
            Nodo n = (Nodo) p.info;
            if(n.getNombre().equals(nombre)){
                return n;
            }
            p = p.link;
        }
        return null;
    }

    public MyArray getLink() {
        return link;
    }

    public Object getInfo() {
        return info;
    
    }
    
    public Object get(int num){
        MyArray p = this;
        int cont =0;
        while(p!= null && cont != num){
            cont++;
            p = p.link;
        }
        return p.info;
    }
    
    
    
    public int size(){
        int cont = 0;
        if(this.info == null){
            return 0;
        }
        else{
            MyArray p = this;
            while(p!=null){
                cont++;
                p=p.link;
            }
        }
        return cont;
    }
    
}
