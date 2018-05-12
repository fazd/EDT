/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 *
 * @author fabio
 */
public class ReportCellRenderer extends DefaultTreeCellRenderer {
    
    private NodeBS node;

    public ReportCellRenderer(NodeList k) {
        if(k!= null){
            this.node = k.getInfo();
        }
        else{
            this.node = null;
        }
    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value,
       boolean isSelected, boolean expanded, boolean leaf, int row,
       boolean hasFocus) {
       JLabel c;
       c = (JLabel) super.getTreeCellRendererComponent(tree, value, isSelected, expanded, leaf, row, hasFocus);
       DefaultMutableTreeNode node = (DefaultMutableTreeNode) value; 
       NodeBS nodo = (NodeBS) node.getUserObject();   
       if(nodo.isIsParent()){
           c.setIcon(new ImageIcon("src/icons/package16.png"));
       }
       else{
           c.setIcon(new ImageIcon("src/icons/entregable16.png"));
       }
       if(this.node.equals(nodo)){
           c.setForeground(Color.RED);
           c.setOpaque(true);
       }
       
       return c; 
    }
        
    
    
}
