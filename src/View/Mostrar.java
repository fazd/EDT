/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Grafo.DrawGraph;
import Grafo.Grafo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author fabio
 */
public class Mostrar extends javax.swing.JFrame {

    private File f;
    private File f2;
    private NodeBS rootNode;
    private DefaultTreeModel model;
    DefaultMutableTreeNode selectedNode;
    private String user;
    boolean flag;
    
    public Mostrar() {
        initComponents();
    }
    
    /**
     * Constructor que se encarga de llenar el Jtree con respecto a un usuario
     * @param rootNode Nodo que contiene todo el arbol 
     * @param user usuario que inició sesión
     */
    
    public Mostrar(NodeBS rootNode, String user){
        initComponents();
        this.user = user;
        this.setLocationRelativeTo(null);
        String pathName = "files\\" + user + "\\" + user + ".txt";
        String pathName2 = "files\\" + user + "\\" + user + "_info.txt";
        this.f = new File(pathName);
        this.f2 = new File(pathName2);
        this.rootNode = rootNode;
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(rootNode);
        proyectoL.setText("Proyecto : "+ rootNode.getNombre());
        model = (DefaultTreeModel) tree.getModel();
        model.setRoot(root);
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
        tree.setCellRenderer(new MyTreeCellRenderer());
    }
    
    /**
     * Subrutina que se encarga de llenar el Jtree con la información del arbol 
     * en cuestión
     * @param parent Nodo del Jtree al cual se le agregará un hijo 
     * @param root  Nodo del arbol que se agregará al Jtree
     */
    
    
    public void fillTree(DefaultMutableTreeNode parent, NodeBS root) {
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
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tree = new javax.swing.JTree();
        proyectoL = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        atrasB = new javax.swing.JButton();
        eliminarB = new javax.swing.JButton();
        agregarB = new javax.swing.JButton();
        txtB = new javax.swing.JButton();
        reporteB = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tree.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        tree.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jScrollPane1.setViewportView(tree);

        proyectoL.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        proyectoL.setForeground(new java.awt.Color(238, 112, 82));
        proyectoL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        proyectoL.setText("Proyecto : Nombre Proyecto");

        jPanel2.setBackground(new java.awt.Color(238, 112, 82));

        atrasB.setBackground(new java.awt.Color(255, 255, 255));
        atrasB.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        atrasB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/previous.png"))); // NOI18N
        atrasB.setText("Atrás");
        atrasB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atrasBActionPerformed(evt);
            }
        });

        eliminarB.setBackground(new java.awt.Color(255, 255, 255));
        eliminarB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        eliminarB.setText("Eliminar Tarea");
        eliminarB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarBActionPerformed(evt);
            }
        });

        agregarB.setBackground(new java.awt.Color(255, 255, 255));
        agregarB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add.png"))); // NOI18N
        agregarB.setText("Agregar Tarea");
        agregarB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarBActionPerformed(evt);
            }
        });

        txtB.setBackground(new java.awt.Color(255, 255, 255));
        txtB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/txt.png"))); // NOI18N
        txtB.setText("Generar Txt");
        txtB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBActionPerformed(evt);
            }
        });

        reporteB.setBackground(new java.awt.Color(255, 255, 255));
        reporteB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/analytics.png"))); // NOI18N
        reporteB.setText("Reporte");
        reporteB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reporteBActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Acciones");

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/line-chart(1).png"))); // NOI18N
        jButton1.setText("grafo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(eliminarB)
                    .addComponent(txtB, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(atrasB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(agregarB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(reporteB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(55, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel6)
                .addGap(104, 104, 104)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eliminarB, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(agregarB, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtB, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reporteB, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(atrasB)
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(proyectoL, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(proyectoL, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 63, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void atrasBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atrasBActionPerformed
        Login.current.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_atrasBActionPerformed

    private void reporteBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reporteBActionPerformed
        Reporte r = new Reporte(rootNode,user);
        r.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_reporteBActionPerformed

    private void eliminarBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarBActionPerformed
        DefaultMutableTreeNode parent = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        if(parent !=null){
            NodeBS padre = (NodeBS) parent.getUserObject();
            if(padre.isIsParent()){
                if(padre.numberChildren()==0){
                    rootNode.deleteNode(rootNode, padre,model,parent);
                    if(!rootNode.equals(padre)){
                        //model.removeNodeFromParent(parent);
                        tree.setModel(model);
                    }
                    try {
                      Archivo.write(f, f2, rootNode);
                    } catch (IOException ex) {
                        Logger.getLogger(Mostrar.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        Logger.getLogger(Mostrar.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else{
                    int opcion =JOptionPane.showConfirmDialog(this,"Advertencia, este paquete contiene"
                            + " entregables, ¿desea eliminarlo?, se borraran todos "
                            + "los entregables","Advertencia", JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE);
                    
                    if(opcion == JOptionPane.YES_OPTION){
                        rootNode.deleteNode(rootNode, padre,model,parent);
                        if(!rootNode.equals(padre)){
                           // model.removeNodeFromParent(parent);
                            tree.setModel(model);
                        }
                        try {
                            Archivo.write(f, f2, rootNode);
                        } catch (IOException ex) {
                            Logger.getLogger(Mostrar.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (Exception ex) {
                            Logger.getLogger(Mostrar.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                }
            }
            else{
                rootNode.deleteNode(rootNode, padre,model,parent);
                //model.removeNodeFromParent(parent);
                tree.setModel(model);
                try {
                    Archivo.write(f, f2, rootNode);
                } catch (IOException ex) {
                    Logger.getLogger(Mostrar.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(Mostrar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        else{
            JOptionPane.showMessageDialog(this,"Seleccione un Paquete o un Entregable",
                "Seleccione una opcion", JOptionPane.ERROR_MESSAGE);
        }
        
        
    }//GEN-LAST:event_eliminarBActionPerformed

    private void agregarBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarBActionPerformed
        Tareas t = new Tareas(rootNode, user);
        t.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_agregarBActionPerformed

    private void txtBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBActionPerformed
        JFileChooser jf =  new JFileChooser();
        jf.setDialogTitle("Seleccione donde guardará");
        if(jf.showSaveDialog(this) == JFileChooser.APPROVE_OPTION){
            String path = jf.getSelectedFile().getAbsolutePath();     
            path +=".txt";
            File f = new File(path);
            File temp = new File("files\\"+user+"\\"+user+".txt");
            try {
                Archivo.copyFile(temp, f);
            } catch (Exception ex) {
                Logger.getLogger(Mostrar.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(this,"El archivo ha sido guardado en "+path,
                    "Creacion exitosa", JOptionPane.INFORMATION_MESSAGE);

        }
    }//GEN-LAST:event_txtBActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            Grafo g = NodeBS.toGraph(rootNode);
            g.print();
            DrawGraph dw = new DrawGraph(g,rootNode);
            DrawGraph.frame = this;
            dw.setVisible(true);
            this.setVisible(false);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Mostrar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    
    
    
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
            java.util.logging.Logger.getLogger(Mostrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Mostrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Mostrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Mostrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Mostrar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregarB;
    private javax.swing.JButton atrasB;
    private javax.swing.JButton eliminarB;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel proyectoL;
    private javax.swing.JButton reporteB;
    private javax.swing.JTree tree;
    private javax.swing.JButton txtB;
    // End of variables declaration//GEN-END:variables
}
