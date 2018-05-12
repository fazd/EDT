/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author fabio
 */
public class Reporte extends javax.swing.JFrame {

    private NodeBS rootNode;
    public static JTree stree;
    private DefaultTreeModel model;
    DefaultMutableTreeNode selectedNode;
    boolean flag;
    private File f;
    private File f2;
    private NodeList pre;
    private NodeList in;
    private NodeList post;
    private NodeList current;
    private NodeList Lista;
    private NodeList nodosT;
    private NodeList nodosP1;
    
    
    public Reporte() {
        initComponents();
    }

    /**
     * Este constructor es el que se encarga de cargar el Jtree con la información
     * del arbol que es recibido
     * @param rootNode Nodo que contiene toda la información de un arbol
     * @param user Usuario que inició sesión
     */
    
    public Reporte(NodeBS rootNode, String user){
        initComponents();
        this.setLocationRelativeTo(null);
        this.rootNode = rootNode;
        String pathName = "files\\" + user + "\\" + user + ".txt";
        String pathName2 = "files\\" + user + "\\" + user + "_info.txt";
        this.f = new File(pathName);
        this.f2 = new File(pathName2);
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(rootNode);
        model = (DefaultTreeModel) tree.getModel();
        model.setRoot(root);
        proyectoL.setText("Proyecto: "+rootNode.getNombre());
        for (int i = 1; i <= rootNode.numberChildren(); i++) {
            fillTree(root, rootNode.nextChildren(i));
        }
        tree.setModel(model);        
        Validations.expandAllNodes(tree,root);
        tree.addTreeSelectionListener(new TreeSelectionListener() {

            @Override
            public void valueChanged(TreeSelectionEvent e) {
                selectedNode
                        = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                flag = true;

            }
        });
        orden.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                changeOption();
            }
        });
        pre = new NodeList();
        in = new NodeList();
        post = new NodeList();
        nodosT = new NodeList();
        nodosP1 = new NodeList();
        preorden(this.rootNode,pre);
        inorden(this.rootNode, in);
        postorden(this.rootNode,post);
        nodosTerminales(this.rootNode,nodosT);
        nodosSolo1Entregable(this.rootNode, nodosP1);
        Lista = pre;
        current = pre;
        setInfo();
        alturaL.setText(""+altura(rootNode,0,-1));
        
    }
    
    /**
     * Subrutina que se encarga de llenar el Jtree con la información del arbol 
     * en cuestión
     * @param parent Nodo del Jtree al cual se le agregará un hijo 
     * @param root  Nodo del arbol que se agregará al Jtree
     */
    
    
    private void fillTree(DefaultMutableTreeNode parent, NodeBS root) {
        if (root != null) {
            DefaultMutableTreeNode nodoTree = new DefaultMutableTreeNode(root);
            model.insertNodeInto(nodoTree, parent, parent.getChildCount());
            int hijos = root.numberChildren();
            for (int i = 1; i <= hijos; i++) {
                fillTree(nodoTree, root.nextChildren(i));
            }
        }
    }
    
    /**
     * Esta subrutina se encarga de actualizar una lista donde se agrega nodos
     * en forma del recorrido de preorden
     * @param node Nodo que podría ser agregado y se mirará sus hijos
     * @param lista Lista donde se agregará
     */
    
    private void preorden(NodeBS node, NodeList lista){
        if(node != null){
            lista.add(node);
            for(int i = 1; i <=node.numberChildren(); i++){
                preorden(node.nextChildren(i),lista);
            }
        }
    }
    
    /**
     * Esta subrutina se encarga de actualizar una lista donde se agrega nodos
     * en forma del recorrido de inorden
     * @param node Nodo que podría ser agregado y se mirará sus hijos
     * @param lista Lista donde se agregará
     */
    
    private void inorden(NodeBS node,NodeList lista){
        if(node != null){
            if(node.numberChildren()>=1){
                inorden(node.nextChildren(1),lista);
                lista.add(node);
                for(int i = 2; i <=node.numberChildren(); i++){
                    inorden(node.nextChildren(i),lista);
                }
            }
            else{
                lista.add(node);
            }
        }
    }
    
    /**
     * Esta subrutina se encarga de actualizar una lista donde se agrega nodos
     * en forma del recorrido de postorden
     * @param node Nodo que podría ser agregado y se mirará sus hijos
     * @param lista Lista donde se agregará
     */
    
    private void postorden(NodeBS node,NodeList lista){
        if(node != null){
            for(int i = 1; i <=node.numberChildren(); i++){
                postorden(node.nextChildren(i),lista);
            }
            lista.add(node);
        }
    }
    
    /**
     * Esta subrutina se encarga de actualizar una lista donde se agrega nodos
     * cuando estos solo cuando no tienen ningún hijo
     * @param node Nodo que podría ser agregado y se mirará sus hijos
     * @param lista Lista donde se agregará
     */
    
    private void nodosTerminales(NodeBS node, NodeList lista){
        if(node != null){
            if(node.numberChildren() == 0){
                lista.add(node);
            }
            else{
                for(int i = 1; i <=node.numberChildren(); i++){
                    nodosTerminales(node.nextChildren(i),lista);
                }
            }
        }
    }
    
    /**
     * Esta subrutina se encarga de actualizar una lista donde se agrega nodos
     * cuando estos solo tienen un solo entregable
     * @param node Nodo que podría ser agregado y se mirará sus hijos
     * @param lista Lista donde se agregará
     */
    
    private void nodosSolo1Entregable(NodeBS node, NodeList lista){
        if(node != null){
            if(node.numberChildren() == 1){
                if(!node.nextChildren(1).isIsParent()){
                    lista.add(node);
                }
            }
            for(int i = 1; i<=node.numberChildren(); i++){
                nodosSolo1Entregable(node.nextChildren(i), lista);
            }
        }
    }
    
    /**
     * Esta función se encarga de hallar el nivel maximo del arbol
     * @param node Nodo donde se está evaluando
     * @param max Maximo parcial del arbol
     * @param current Nivel donde se encuentra
     * @return  Maximo del arbol
     */
    
    private int altura(NodeBS node, int max, int current){
        if(node != null){
            current++;
            if(max < current){
                max = current;
            }
            int cont = max;
            for(int i = 1; i <=node.numberChildren(); i++){
                
                cont = Math.max(cont,altura(node.nextChildren(i),max,current));
            }
            if(cont > max){
                max= cont;
            }
        }
        return max;
    }
    
    
    
    /**
     * Esta subrutina escribe la información del nodo en su respectivo Label
     */
    
    private void setInfo(){
        if(current.getInfo() != null){
            NodeBS node =current.getInfo();
            String nombre = node.getNombre();
            long presupuesto = node.getBudget();
            long currentP = node.getCurrentBudget();
            int time = node.getTime();
            String tipo;
            if(node.isIsParent()){
                tipo = "Paquete";
            }
            else{
                tipo = "Entregable";
            }
            nombreL.setText(nombre);
            presupuestoL.setText(""+presupuesto);
            presuCL.setText(""+currentP);
            tiempoL.setText(""+time);
            tipoL.setText(tipo);
            if(current.next() == null){
                siguienteB.setEnabled(false);
            }
            else{
                siguienteB.setEnabled(true);
            }
            if(current.before(Lista) == null){
                anteriorB.setEnabled(false);
            }
            else{
                anteriorB.setEnabled(true);
            }
            tree.setCellRenderer(new ReportCellRenderer(current));
        }
        else{
            siguienteB.setEnabled(false);
            anteriorB.setEnabled(false);
        }
    }
    
    /**
     * Esta subrutina limpia todos los campos 
     */
    
    private void clear(){
        nombreL.setText("");
        presupuestoL.setText("");
        presuCL.setText("");
        tiempoL.setText("");
        tipoL.setText("");
    }
    
    /**
     * Esta subrutina cambia la opción del ComboBox para cambiar el recorrido
     * que se mostrará
     */
    
    private void changeOption(){
        String opcion = (String) orden.getSelectedItem();
        switch (opcion){
            case "Preorden":
            {
                current = pre;
                Lista = pre;
                break;
            }
            
            case "Inorden": 
            {
                current = in;
                Lista = in;
                break;
            }
            
            case "Postorden":
            {
                current = post;
                Lista = post;
                break;
            }
            
            case "Nodos terminales":
            {
                current = nodosT;
                Lista = nodosT;
                break;
            }
            
            case "Paquetes con solo un entregable":  
            {
                current = nodosP1;
                Lista = nodosP1;
                break;
            }  
        }
        clear();
        setInfo();
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        orden = new javax.swing.JComboBox<>();
        atrasB = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        nombreL = new javax.swing.JLabel();
        presupuestoL = new javax.swing.JLabel();
        tiempoL = new javax.swing.JLabel();
        tipoL = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        presuCL = new javax.swing.JLabel();
        anteriorB = new javax.swing.JButton();
        siguienteB = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        alturaL = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tree = new javax.swing.JTree();
        proyectoL = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(238, 112, 82));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Acciones ");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Seleccione una acción");

        orden.setBackground(new java.awt.Color(255, 255, 255));
        orden.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        orden.setForeground(new java.awt.Color(0, 0, 0));
        orden.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Preorden", "Inorden", "Postorden", "Nodos terminales", "Paquetes con solo un entregable" }));

        atrasB.setBackground(new java.awt.Color(255, 255, 255));
        atrasB.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        atrasB.setForeground(new java.awt.Color(0, 0, 0));
        atrasB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/back32.png"))); // NOI18N
        atrasB.setText("Atrás");
        atrasB.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        atrasB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atrasBActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(238, 112, 82));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nombre");

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Presupuesto");

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Tiempo");

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("tipo");

        nombreL.setForeground(new java.awt.Color(255, 255, 255));
        nombreL.setMaximumSize(new java.awt.Dimension(105, 16));
        nombreL.setMinimumSize(new java.awt.Dimension(105, 16));
        nombreL.setPreferredSize(new java.awt.Dimension(105, 16));

        presupuestoL.setForeground(new java.awt.Color(255, 255, 255));

        tiempoL.setForeground(new java.awt.Color(255, 255, 255));

        tipoL.setForeground(new java.awt.Color(255, 255, 255));

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Presu. restante");

        presuCL.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(presupuestoL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tiempoL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tipoL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nombreL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(presuCL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nombreL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(presupuestoL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(presuCL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tiempoL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tipoL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        anteriorB.setBackground(new java.awt.Color(255, 255, 255));
        anteriorB.setForeground(new java.awt.Color(0, 0, 0));
        anteriorB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/back24.png"))); // NOI18N
        anteriorB.setText("Anterior");
        anteriorB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anteriorBActionPerformed(evt);
            }
        });

        siguienteB.setBackground(new java.awt.Color(255, 255, 255));
        siguienteB.setForeground(new java.awt.Color(0, 0, 0));
        siguienteB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/next24.png"))); // NOI18N
        siguienteB.setText("Siguiente");
        siguienteB.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        siguienteB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siguienteBActionPerformed(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Altura del arbol");

        alturaL.setForeground(new java.awt.Color(255, 255, 255));
        alturaL.setText("0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(anteriorB)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(siguienteB))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(46, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(32, 32, 32)
                        .addComponent(alturaL, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(orden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(atrasB, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(orden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(alturaL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(anteriorB)
                    .addComponent(siguienteB))
                .addGap(30, 30, 30)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(atrasB, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        tree.setBackground(new java.awt.Color(255, 255, 255));
        tree.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tree.setForeground(new java.awt.Color(238, 112, 82));
        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        tree.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jScrollPane1.setViewportView(tree);

        proyectoL.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        proyectoL.setForeground(new java.awt.Color(238, 112, 82));
        proyectoL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        proyectoL.setText("Proyecto: Nombre del proyecto ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(proyectoL, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(proyectoL, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void atrasBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atrasBActionPerformed
        Login.current.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_atrasBActionPerformed

    private void siguienteBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siguienteBActionPerformed
        if(current.next() != null){
            current = current.next();
            setInfo();
        }
    }//GEN-LAST:event_siguienteBActionPerformed

    private void anteriorBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anteriorBActionPerformed
        if(current.before(Lista)!= null){
           current = current.before(Lista);
           setInfo();   
        }
    }//GEN-LAST:event_anteriorBActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Reporte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Reporte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Reporte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Reporte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Reporte().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alturaL;
    private javax.swing.JButton anteriorB;
    private javax.swing.JButton atrasB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel nombreL;
    private javax.swing.JComboBox<String> orden;
    private javax.swing.JLabel presuCL;
    private javax.swing.JLabel presupuestoL;
    private javax.swing.JLabel proyectoL;
    private javax.swing.JButton siguienteB;
    private javax.swing.JLabel tiempoL;
    private javax.swing.JLabel tipoL;
    private javax.swing.JTree tree;
    // End of variables declaration//GEN-END:variables
}
