package View;

/**
 *
 * @author fabio
 */
public class NodeList {
    private NodeBS info;
    private NodeList link;
    
    /**
     * Constructor base
     */
    
    public NodeList(){
        this.info = null;
        this.link = null;
    }
    
    /**
     * Constructor cuando se agregará un primer nodo a la lista
     * @param info Información que guardará este nodo
     */
    
    
    public NodeList(NodeBS info){
        this.info = info;
        this.link = null;
    }
        
    /**
     * Esta subrutina indexa un nuevo elemento a esta lista
     * @param node Elemento que se agregará a la lista
     */
    
    public void add(NodeBS node){
        if(this.link == null && this.info == null){
            this.info = node;
        }
        else if(this.link==null){
            this.link = new NodeList(node);
        }
        else{
            NodeList p = this;
            while(p.link!= null){
                p = p.link;
            }
            p.link = new NodeList(node);
        }            
    }   
    
    /**
     * Esta función devuelve el siguiente nodo 
     * @return el siguiente nodo de la lista
     */
    
    public NodeList next(){
        if(this.link == null){
            return null;
        }
        else{
            return this.link;
        }
    }
    /**
     * Esta función se encarga de devolver el nodo anterior al actual
     * @param inicio Inicio de la lista
     * @return nodo anterior en el orden de la lista o nullo si es el primer nodo
     *  de la lista
     */
    
    public NodeList before(NodeList inicio){
        NodeList ant = null;
        NodeList p = inicio;
        while(p!= null && !p.equals(this)){
            ant = p;
            p = p.link;
        }
        if(p == null || ant == null){
            return null;
        }
        else{
            return ant;
        }
    }
    
    
    
    /**
     * Esta función nos dice el tamaño de la lista
     * @return tamaño de la lista
     */
    public int size(){
        int cont = 0;
        NodeList p = this;
        if(p ==null || p.getInfo() == null){
            return 0;
        }
        while(p != null){
            cont++;
            p=p.link;
        }
        return cont;
    }
    
    /**
     * Esta función retorna la info del nodo de la lista
     * @return Nodo que almacenaba la lista
     */
    
    
    public NodeBS getInfo() {
        return info;
    }

    /**
     * Esta función retorna el apuntador que apunta al siquiente nodo de la lista
     * @return Link que apunta al siguiente nodo de la lista, null si es el ultimo nodo
     */
    
    public NodeList getLink() {
        return link;
    }
    
    /**
     * Esta subrutina actualiza la información del nodo de la lista
     * @param info Información que se guardará en el nodo
     */
    
    public void setInfo(NodeBS info) {
        this.info = info;
    }
    
    /**
     * Esta subrutina actualiza la información del link de la lista
     * @param link link que se guardará en el nodo
     */
    
    public void setLink(NodeList link) {
        this.link = link;
    }
    
    
    /**
     * Esta función compara dos nodeList
     * @param node nodo que quiere ser comparado
     * @return true si su información es la misma, false si no lo es
     */
    
    public boolean equals(NodeList node){
        return this.info.equals(node.info);
    }

    
}
