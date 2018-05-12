package View;


import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;





public class MyTreeCellRenderer extends DefaultTreeCellRenderer {

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
       return c; 
    }
        
    

}