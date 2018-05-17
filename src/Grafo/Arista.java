/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

/**
 *
 * @author fazd
 */
public class Arista {
    
    private Nodo in;
    private Nodo fin;

    public Arista(Nodo in, Nodo fin) {
        this.in = in;
        in.addAdy(fin);
        this.fin = fin;
    }

    public Nodo getIn() {
        return in;
    }

    public Nodo getFin() {
        return fin;
    }
    
    
    
    
}
