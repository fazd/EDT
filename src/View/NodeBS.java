
package View;

import Grafo.Grafo;
import Grafo.MyArray;
import Grafo.Nodo;
import static View.Validations.numTabs;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author jaishirb
 */
public class NodeBS {

    private NodeList children;
    private boolean isParent;
    private String nombre;
    private long budget;
    private int time;
    private String index;
    private long currentBudget;
    private LocalDate fechaInicio;
    private String rootPath;
    private String dep;
    private int x;
    private int y;

    
    public NodeBS (String nombre, int time){
        this.nombre = nombre;
        this.time = time;
        this.dep = "";
    }
    
    /**
     * Este constructor se encarga de crear un nodoBS
     * @param nombre nombre del nodo
     * @param budget presupuesto del nodo
     * @param date fecha de inicio del nodo
     * @param time tiempo de duracion
     * @param user usuario que está agregando
     * @throws Exception si el archivo no existe
     */
    
    
    public NodeBS(String nombre, long budget, LocalDate date, int time, String user) throws Exception {
        this.nombre = nombre;
        this.budget = budget;
        this.index = "1";
        this.isParent = true;
        this.currentBudget = budget;
        this.fechaInicio = date;
        this.time = time;
        this.rootPath = "files/"+user;
        this.dep = "";
        Archivo.createProyectFile(this, user);
    }
    
    
    /**
     * Este constructor  es para agregar un nodo que proviene del archivo y no es raiz
     * @param nombre Nombre del paquete o entregable
     * @param budget Presupuesto del paquete o entregable
     * @param time Tiempo del paquete o entregable
     * @param index indice del paquete o entregable
     * @param Parent True is es paquete, false si es entregable
     */
    
    
    private NodeBS(String nombre, long budget, LocalDate date, int time,String index, boolean Parent,String path) {
        this.nombre = nombre;
        this.budget = budget;
        this.time = time;
        this.index = index;
        this.isParent = Parent;
        this.currentBudget = budget;
        this.fechaInicio = date;
        this.rootPath = path;
        this.dep = "";
        System.out.println("path: "+ this.rootPath);
    }
    
    /**
     * Este constructor es para crear los paquetes y entregables por primera vez 
     * @param parent Padre del paquete o entregablee
     * @param nombre Nombre del paquete o entregable
     * @param budget Presupuesto del paquete o entregable
     * @param time Tiempo del paquete o entregable
     * @param Parent True si es paquete, false si es entregable
     */
    
    
    public NodeBS(NodeBS parent,String nombre, long budget,int time, boolean Parent){
        this.index = generateIndex(parent);
        this.budget = budget;
        this.time = time;
        this.nombre = nombre;
        this.isParent = Parent;
        this.currentBudget = budget;
        this.rootPath = parent.getRootPath();
        parent.addChildren(this);
        this.fechaInicio= parent.getFechaInicio();
        if (!Parent){
            Archivo.createEntregable(this);
        }
        
    }
    
    
    
    /**
     * Esta función estatica genera el indice dado un un nodo padre
     * @param parent Paquete que contendrá el paquete o entregable a generarse
     * @return el indice del paquete o entregable 
     */
    
    private static String generateIndex(NodeBS parent){
        int num = parent.numberChildren() +1;
        return parent.getIndex()+"."+num;
    }
    
    /**
     *  Esta función genera un indice nuevo cuando se modifica el arbol para mantener
     * la armonia
     * @param parent El padre del paquete o entregable
     * @param indexChild Su posición de hijo en el paquete, Por ejemplo: si es 
     * el tercer hijo, 3, si es el segundo hijo 2
     * @return Indice modificado
     */
    
    private static String generateIndex(NodeBS parent, int indexChild){
        return parent.getIndex()+"."+indexChild;
    }
    
    /**
     * Esta subrutina agrega un paquete o entregable al paquete padre 
     * @param parent Paquete al que se le agregará un paquete o nodo
     * @param node  Nodo que será agregado
     */
    
    
    public void add(NodeBS parent, NodeBS node){
        if(parent.equals(this)){
        }
        else if (isParent){
            addNode(parent,node);
        }
    }
    
    /**
     * Esta función permite extraer el indice del padre de un nodo
     * @param index Indice del nodo
     * @return Indice del padre
     */
    
    public static  String parentIndex(String index){
        String parentIndex;
        if(index.equals("1")){
            return null;
        }
        int i = index.length()-1;
        while(i>0){
            if(index.charAt(i)== '.'){
                break;
            }
            i--;
        }
        parentIndex = index.substring(0,i);
        return parentIndex;
    }
    
    /**
     * Esta subrutina se encarga de agregar un nodo a su padre
     * @param parent Padre del nodo
     * @param node Nodo que será agregado
     */
    
    private void addNode(NodeBS parent, NodeBS node){
        if(this.getNombre().equals(parent.getNombre())){
            this.addChildren(node);
        }
        else{
            int i = 1;
            while(i < node.numberChildren()){
                addNode(this.nextChildren(i),node);
                i++;
            }
        }
    }   
    
    /**
     * Esta función valida si se puede agregar o no el paquete o entregable
     * @param time Tiempo del paquete o entregable
     * @param budget Presupuesto del paquete o entregable
     * @return True si se puede agregar, falso de lo contrario
     */
    
    
    public boolean canAdd(int time, long budget){
        if(budget<= this.currentBudget){
            if(time <= this.time){
                return true;
            }
            else{
                JOptionPane.showMessageDialog(null,"Error, el tiempo supera el tiempo del paquete"
                    + " donde se agregará", "Error en el tiempo", JOptionPane.ERROR_MESSAGE);
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"Error, el presupuesto supera el presupuesto del paquete"
                    + " donde se agregará", "Error en el presupuesto", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
    
    /**
     * Esta subrutina se encarga de agregar un hijo a un paquete
     * @param nodo Nodo que quiere ser agregado
     */
    
    private void addChildren(NodeBS nodo){
        this.currentBudget -=nodo.getBudget(); 
        if(children == null){
            children = new NodeList(nodo);
        }
        else{
            children.add(nodo);
        }
    }
    
    /**
     * Esta subrutina se encarga de actualizar los indices cuando un nodo 
     * es eliminado
     * @param k Nodo de la lista de los hijos donde hay que comenzar a actualizar
     */
    
    
    public void fixIndex(NodeList k){
        String antIndex = k.getInfo().getIndex();
        k=k.getLink();
        while(k != null){
            String copia = k.getInfo().getIndex();
            k.getInfo().setIndex(antIndex);
            fixIndexChildren(k.getInfo());
            antIndex = copia;
            k = k.getLink();
        }
    }
    
    /**
     * Esta subrutina se encarga de actualizar los indices de los hijos cuando el 
     * indice del padre es actualizado
     * @param node Nodo al que se le será arreglado el indice
     */
    
    private void fixIndexChildren(NodeBS node){
        for(int i = 1; i <=node.numberChildren(); i++){
            node.nextChildren(i).setIndex(NodeBS.generateIndex(node,i));
            fixIndexChildren(node.nextChildren(i));
        }
    }
    
    /**
     * Esta Subrutina se encarga de eliminar un Paquete o entregable, además valida 
     * los 4 casos 1-paquete-raiz,2-Paquete-vacio,3-Paquete-lleno,4-entregable 
     * @param root Raiz del arbol
     * @param nodo Nodo que se quiere eliminar
     * @param  model Modelo del JTree donde se eliminará tambien el nodo
     * @param ancestor Nodo del Jtree que contiene al nodo que se quiere eliminar  
     */
    
    
    public void deleteNode(NodeBS root,NodeBS nodo, DefaultTreeModel model,DefaultMutableTreeNode ancestor){
        int i =JOptionPane.showConfirmDialog(null,"Esta seguro que quiere eliminar este nodo?",
                "Confirmacion",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
        
        if(i == JOptionPane.YES_OPTION){
            if(nodo.equals(root)){
                JOptionPane.showMessageDialog(null, "No puedes eliminar la raiz",
                        "error",JOptionPane.ERROR_MESSAGE);
            }
            else{
                model.removeNodeFromParent(ancestor);
                NodeBS parent = findParent(null,root,nodo);
                if(parent.numberChildren() == 1){
                    parent.children=null;
                }
                else{
                    NodeList antk = null;
                    NodeList k = parent.children;
                    while( k != null && !k.getInfo().equals(nodo)){
                        antk=k;
                        k=k.getLink();
                        
                    }
                    if(k != null){
                        fixIndex(k);
                        if(antk == null){
                            parent.children=k.getLink();
                            k=null;
                        }
                        else{
                            antk.setLink(k.getLink());
                            k.setLink(null);
                        }
                        JOptionPane.showMessageDialog(null,"Se elimino correctamente",
                                "Eliminacion exitosa",JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "No lo encontro",
                                "Error grave", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }
    
    /**
     * Esta función se encarga de encontrar el padre de un paquete o entregable
     * en el arbol
     * @param parent Padre del nodo actual (al principio comienza en null)
     * @param current Nodo en el cual se está 
     * @param node Nodo que se quiere encontar
     * @return Padre del nodo node
     */
    
    public NodeBS findParent(NodeBS parent, NodeBS current, NodeBS node){
        if(current != null){
            if(current.equals(node)){
                return parent;
            }
            for(int i = 1; i<= current.numberChildren(); i++){
                NodeBS k = findParent(current,current.nextChildren(i),node);
                if(k!= null){
                    return k;
                }
            }
        }
        return null;
    }
    
    /**
     * Esta función retorna el i-esimo hijo de un nodo siempre y cuando este no 
     * exceda su numero de hijos
     * @param num i-esimo hijo que quiere obtener
     * @return Un nodo que es el i-esimo hijo, null si num es mayor que el numero de hijos
     */
    
    
    public NodeBS nextChildren(int num){
        int cont =1;
        if(children == null){
            return null;
        }
        NodeList p = children;
        while(p!= null && cont<num){
            p= p.getLink();
            cont++;
        }
        if(p==null){
            return null;
        }
        return p.getInfo();
    }
    
    /**
     * Esta función calcula la cantidad de hijos de un paquete
     * @return Cantidad de hijos de un paquete
     */
    
    public int numberChildren(){
        if(children == null){
            return 0;
        }
        else{
            return children.size();
        }
        
    }
    
    /**
     * Esta función transforma una linea con un formato especifico a un Nodo
     * @param root Nodo raiz del arbol
     * @param str Linea extraida del archivo que será transformada
     * @return Un nodo con la información de la linea
     */
    
    private static NodeBS stringToNode(NodeBS root,String str, String path) throws FileNotFoundException{
        //System.out.println("la linea es: "+str);
        String [] line = str.split("##");
        long budget = Long.parseLong(line[2]);
        LocalDate date = LocalDate.parse(line[3]);
        int time = Integer.parseInt(line[4]);
        long currentB = Long.parseLong(line[6]);
        if(line[0].equals("1")){
           return new NodeBS(line[1],budget,date,time,line[0],true,path);
        }
        String parentIndex = parentIndex(line[0]);
        NodeBS parent = findByIndex(root, parentIndex);
        if(parent == null){
            //esto no deberia suceder
            return null;
        }
        else{
            boolean par;
            if(line[5].equalsIgnoreCase("true")){
                par = true;
            }
            else{
                par = false;
            }
            NodeBS nodo = new NodeBS(parent,line[1],budget,time,par);
            path = parent.getRootPath();
            if(!par){
                path+="/"+nodo.getNombre()+".txt";
                File f = new File(path);
                Scanner lector = new Scanner(f);
                String linea = lector.nextLine();
                if(lector.hasNext()){
                    linea = lector.nextLine();
                    nodo.setDep(linea);
                    //nodo.setFechaInicio(root);
                }
                else{
                    nodo.setDep("");
                }
            }
            return nodo;
            
        }
    }
    
    /**
     * Esta función se encarga de encontrar un nodo mediante el nombre 
     * @param root Raiz del arbol
     * @param nombre Nombre del nodo que se quiere encontrar
     * @return Nodo con el nombre que se envió a buscar, de lo contrario null
     */
    
    
    public static NodeBS find(NodeBS root,String nombre){
        if(root != null){
            if(root.getNombre().equals(nombre)){
                return root;
            }
            for(int i =1; i <=root.numberChildren(); i++){
                NodeBS k= find(root.nextChildren(i),nombre);
                if(k!=null){
                    return k;
                }
            }
        }
        return null;
    }
    
    /**
     * Esta función se encarga de encontrar un nodo mediante el indice 
     * @param root Raiz del arbol
     * @param index Indice del nodo que se quiere encontrar
     * @return Nodo con el indice que se envió a buscar, de lo contrario null
     */
    
    public static NodeBS findByIndex(NodeBS root,String index){
        if(root != null){
            if(root.getIndex().equals(index)){
                return root;
            }
            for(int i =1; i <=root.numberChildren(); i++){
                NodeBS k= findByIndex(root.nextChildren(i),index);
                if(k!=null){
                    return k;
                }
            }
        }
        
        return null;
    }
    
    /**
     * Esta Función se encarga de transformar un archivo txt a un arbol
     * @param f Archivo que quiere ser transformado a arbol
     * @return Nodo raiz que contiene todo el arbol
     * @throws Exception si la encriptación falla  
     */
    
    public static NodeBS fileToTree(File f) throws Exception{
        NodeBS root = null;
        String name = f.getParent();
        try {
            Scanner lector = new Scanner(f);
            String line = lector.nextLine();
            line = encryption.descifra(line);
            root = stringToNode(null,line, name);
            while(lector.hasNext()){
                line = lector.nextLine();
                line = encryption.descifra(line);
                NodeBS aux = stringToNode(root,line,null);
                NodeBS parent = findByIndex(root,parentIndex(aux.getIndex()));
                root.add(parent, aux);
                
            }
            lector.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(NodeBS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return root;
    }
    
    
    /**
     * subrutina que se encarga de agregar todos los entregables a un grafo
     * @param raiz raiz del arbol
     * @param node nodo que puede ser o no agregado
     * @param g  grafo donde se agregan los nodos
     */
    
    
    public static void findEntregables(NodeBS raiz,NodeBS node, Grafo g){
        if(node != null){
            if(!node.isIsParent()){
                System.out.println("Found it");
                Nodo ng =new Nodo(node);
                g.addNodo(ng);
            }
            for(int i = 1; i <=node.numberChildren(); i++){
                findEntregables(raiz, node.nextChildren(i), g);
            }
        }
    }
    
    /**
     * Funcion que devuelve un grafo y se encarga de volver un arbol a un grafo
     * @param root raiz del arbol
     * @return grafo del arbol
     * @throws FileNotFoundException no se encontró un archivo 
     */
    
    
    public static Grafo toGraph(NodeBS root) throws FileNotFoundException{
        Grafo g = new Grafo();
        findEntregables(root, root, g);
        System.out.println("size = "+g.getNodos().size());
        System.out.println("los nodos serán");
        g.print();
        MyArray p = g.getNodos();
        while(p!= null){
            Nodo n = (Nodo) p.getInfo();
            System.out.println("Inicio");
            System.out.println("nombre del nodo "+n.getNombre());
            g.makeDependencies(n);
            System.out.println("FIN");
            p = p.getLink();
        }
        System.out.println("en tograph");
        g.print();
        return g;
    }
    
    
    
    /**
     * Esta función confirma si el nodo puede tener hijos o no
     * @return True si puede tener hijos, false si no
     */
    
    public boolean isIsParent() {
        return isParent;
    }

    /**
     * Esta Subrutina permite editar si el nodo puede o no tener hijos
     * @param flag True si puede tener hijos, false si no puede tener hijos
     */
    
    public void setParent(boolean flag){
        this.isParent = flag;
    }
    
    /**
     * Esta función devuelve el nombre del nodo
     * @return Devuelve el nombre del nodo
     */
    
    public String getNombre() {
        return nombre;
    }

    /**
     * Esta funcion devuelve el presupuesto del nodo
     * @return Devuelve el presupuesto del nodo
     */
    
    public long getBudget() {
        return budget;
    }

    /**
     * Esta función devuelve el tiempo del nodo
     * @return Devuelve el tiempo del nodo
     */
    
    public int getTime() {
        return time;
    }

    /**
     * Esta Subrutina modifica el indice de un nodo
     * @param index El nuevo valor del indice del nodo
     */
    
    public void setIndex(String index) {
        this.index = index;
    }

    /**
     * Esta función retorna el indice del nodo
     * @return Devuelve el indice del nodo
     */
    
    public String getIndex() {
        return index;
    }

    /**
     * Esta función retorna el Presupuesto actual del proyecto
     * @return Devuelve el presupuesto actual del proyecto
     */
    
    public long getCurrentBudget() {
        return currentBudget;
    }

    
    /**
     * Devuelve la fehca de inicio del proyecto
     * @return fecha de inicio del proyecto
     */
    
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }
    
    /**
     * ruta del archivo donde está guardada 
     * @return ruta del archivo que contiene este paquete o entregable
     */

    public String getRootPath() {
        return rootPath;
    }

    /**
     * Esta subrutina se encarga de asignar dependencias a un nodo
     * @param dep  dependencias que se quieren asignar
     */
    
    public void setDep(String dep) {
        this.dep = dep;
        String folder = rootPath;
        String pathName = folder+"/"+nombre+".txt";
        System.out.println("path:"+ pathName);
        File f = new File(pathName);
        try {
                f.createNewFile();
                FileWriter fstream = new FileWriter(f, true);
                BufferedWriter out = new BufferedWriter(fstream);
                out.newLine();
                out.write(dep);
                out.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * eesta subrutina se encarga de transformar la fecha de inicio del entregable por la correcta
     * @param root raiz del arbol
     */
    
    public void setFechaInicio(NodeBS root){
        String k[] = this.dep.split(",");
        //System.out.println("K length: "+k.length);
        if(k.length == 1 && k[0].equals("")){
            System.out.println("entra aqui el nodo: "+this.nombre);
            this.fechaInicio = root.getFechaInicio().plusDays(this.time);
        }
        else{
            NodeBS maximo = NodeBS.find(root, k[0]);
            System.out.println("maximo:"+k[0]);
            LocalDate max = maximo.getFechaInicio();
            for(int i = 1; i < k.length; i++){
                NodeBS n = NodeBS.find(root, k[i]);
                if(max.isBefore(n.getFechaInicio())){
                    max = n.getFechaInicio();
                }
            }
            this.fechaInicio = max; 
        }
    }
    
    /**
     * devuelve un string de dependencias
     * @return string de dependencias
     */
    
    public String getDep() {
        return dep;
    }

    /**
     * Devuelve el valor dentro del grafo dibujado donde se encuentra 
     * @return valor de X en el plano cartesiano
     */
    
    public int getX() {
        return x;
    }
    
    /**
     * Devuelve el valor dentro del grafo dibujado donde se encuentra
     * @return valor de Y en el plano cartesiano
     */

    public int getY() {
        return y;
    }
    
    /**
     * subrutina para asignar posicion dentro del plano cartesiano al grafo
     * @param x posicion en X
     */

    public void setX(int x) {
        this.x = x;
    }
    
    
    /**
     * subrutina para asignar posicion dentro del plano cartesiano al grafo
     * @param y posicion en Y
     */

    public void setY(int y) {
        this.y = y;
    }
    
    /**
     * Esta subrutina muestra toda la información del nodo en consola
     */
    
    public void show(){
        System.out.println("nombre = "+ nombre);
        System.out.println("index = "+index);
        System.out.println("budget = "+budget);
        System.out.println("time = "+time);
        System.out.println("");
        System.out.println("num hijos = "+this.numberChildren());
        System.out.println("________________________");
        for(int i = 1; i <= this.numberChildren(); i++){
            this.nextChildren(i).show();
        }
    }
    
    /**
     * Esta función transforma la información del nodo en una linea 
     * @return Una linea con la información del nodo
     */
    
    
    public String toInfo(){
        String line = index+"##"+nombre+"##"+budget+"##"+fechaInicio+
                "##"+time+"##"+isParent+"##"+currentBudget;
        return line;
    }
    
    
    /**
     * Esta función agrega tabulaciones al nombre del nodo para escribirlo 
     * posteriormente en un archivo
     * @return Linea con las tabulaciones respectivas del nodo
     */
    
     @Override
    public String toString(){
        int numTabs = numTabs(index);
        String line = "";
        for(int i = 0; i < numTabs; i++){
            line+="\t";
        }
        line+=nombre;
        return line;
    }
    
}
