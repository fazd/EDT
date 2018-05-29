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

    /**
     * Este constructor es para crear una arista
     * @param in inicio de la arista
     * @param fin  fin de la arista
     */
    
    public Arista(Nodo in, Nodo fin) {
        this.in = in;
        in.addAdy(fin);
        this.fin = fin;
    }

    /**
     * retorna inicio de la arista
     * @return retorna inicio de la arista
     */
    
    public Nodo getIn() {
        return in;
    }

    /**
     * funcion que devuelve el fin de la arista
     * @return devuelve el fin de la arista
     */
    
    public Nodo getFin() {
        return fin;
    }
    
    
    
    
}
