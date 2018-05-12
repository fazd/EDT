/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

/**
 *
 * @author jaisi
 */
public class Validations {
    
    /**
     * Esta función valida si el usuario y contraseña son correctos
     * @param user Usuario
     * @param pass Contraseña
     * @return  3 si la contraseña corresponde con el usuario
     * 2 si el usuario no existe
     * 1 si la contraseña no corresponde con el usuario
     * @throws Exception si la encriptación falla  
     */
    
    
    public static int validateData(String user, String pass) throws Exception{
        if(!user.equals("Ingrese email") && !user.equals("")){
            File users = new File("lib\\data.txt");
            
            if(!users.exists()){
                try {
                    users.createNewFile();
                } catch (IOException ex) {
                    Logger.getLogger(Validations.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                user = encryption.cifra(user);
                pass = encryption.cifra(pass);
                Scanner lector = new Scanner(users);
                while (lector.hasNext()) { 
                    String[] datos = lector.nextLine().split(","); 
                    if(datos[0].equals(user)){
                        if(datos[1].equals(pass))return 1;
                        return 3;
                    }
               }
            } catch (IOException ex) {
                //JOptionPane.showMessageDialog(null, ex.getMessage());
            } catch (Exception ex) {
                //JOptionPane.showMessageDialog(null, ex.getMessage());
            }

        }
        return 2;
    }
    
    /**
     *  Esta subrutina redimensiona una imgagen dentro de un label
     * @param path Ruta de la imagen
     * @param label Label donde se pondrá la imagen 
     */
    
    public static  void setImage(String path, JLabel label){
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(label.getWidth(), label.getHeight(),
        Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        label.setIcon(imageIcon);
    }
        
    /**
     *  Esta función se encarga de validar si se puede crear o no el proyecto
     * @param name Label donde está el nombre del proyecto
     * @param budget Label donde está el presupuesto del proyecto
     * @param time Label donde está el tiempo estimado del proyecto
     * @return 0 si el nombre no es valido (solo letras y numeros)
     * 1 si el presupuesto tiene otros caracteres aparte de numeros
     * 2 si el el presupuesto es menor o igual a 0
     * 3 si es tiempo tiene otros valores aparte de numeros
     * 4 si el tiempo es negativo o 0
     * 5 si todo está bien
     */
    
    public static boolean createProyect(JTextField name, JTextField budget, JTextField time){
        if(name.getText().trim().length() > 0 && name.getText().matches("[A-Z a-z 0-9 ]+")){
            if(budget.getText().matches("[0-9 ]+") && budget.getText().trim().length() > 0){
                fixNumber(budget);
                int presupuesto = Integer.parseInt(budget.getText());
                if(presupuesto <=0){
                    JOptionPane.showMessageDialog(null, "Error, el presupuesto no es valido",
                            "Error en presupuesto", JOptionPane.ERROR_MESSAGE);
                    return false; 
                }
                if(time.getText().matches("[0-9 ]+") && time.getText().trim().length() > 0){
                    fixNumber(time);
                    int tiempo = Integer.parseInt(time.getText());
                    if(tiempo <= 0){
                        JOptionPane.showMessageDialog(null, "Error, el tiempo no es valido",
                            "Error en el tiempo", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                    JOptionPane.showMessageDialog(null, "Creacion exitosa",
                            "Creación exitosa", JOptionPane.INFORMATION_MESSAGE);
                    return true;
                }
                else{
                    JOptionPane.showMessageDialog(null, "Error, el tiempo contiene caracteres no validos"
                            + "(Solo se puede ingresar numeros enteros)",
                            "Error en el tiempo", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Error, el presupuesto contiene caracteres no validos"
                        + "(solo se puede ingresar numeros enteros)",
                            "Error en presupuesto", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        JOptionPane.showMessageDialog(null, "Error, el nombre está vacio o contiene caracteres no validos"
                + " (solo se permiten numeros y letras)",
                            "Error en el nombre", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    /**
     * Esta función se encarga de devolver el numero de tabulaciones que debe tener un nodo
     * lo cual es analogo a decir en que nivel se encuentra
     * @param str Indice del nodo
     * @return Número de tabulaciones que debe tener el nodo (Nivel)
     */
    
    public static int numTabs(String str){
        int tam = str.length();
        int i =0;
        int cont =0;
        while(i < str.length()){
            if (str.charAt(i) == '.'){
                cont++;
            }
            i++;
        }
        return cont;
    }
 
    public static boolean validName(NodeBS node, String name){
        if(node != null){
            if(node.getNombre().equals(name)){
                return false;
            }
            for(int i = 1; i <=node.numberChildren(); i++){
                boolean k = validName(node.nextChildren(i),name);
                if(!k){
                    return false;
                }
            }
        }
        return true;
    }
    

    /**
     * Esta función se encarga de validar los campos para decidir si se puede o no agregar una tarea
     * @param nameTF TextField donde se tiene la información del nombre
     * @param budgetTF TextField donde se tiene la información del presupuesto
     * @param timeTF TextField donde se tiene la información del tiempo
     * @param parent Nodo del Jtree donde se tiene la información de en que lugar se ubicará el nodo
     * @return true si se puede agregar, falso si hay algún error en los campos
     */
    
    public static boolean createTarea(JTextField nameTF, 
        JTextField budgetTF, JTextField timeTF, DefaultMutableTreeNode parent) {
        if(nameTF.getText().matches("[A-Z a-z 0-9]+") && nameTF.getText().trim().length() > 0){
            if(budgetTF.getText().matches("[0-9 ]+") && budgetTF.getText().trim().length() > 0){
                fixNumber(budgetTF);
                int budget = Integer.parseInt(budgetTF.getText());
                if(budget <=0){
                    JOptionPane.showMessageDialog(null,"Error el presupuesto no puede ser 0",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                if(timeTF.getText().matches("[0-9 ]+") && timeTF.getText().trim().length() > 0){
                    fixNumber(timeTF);
                    int time = Integer.parseInt(timeTF.getText());
                    if(time <=0){
                        JOptionPane.showMessageDialog(null,"Error el tiempo no puede ser 0",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                    if(parent != null){
                        return true;
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Error, Seleccione una ubicacion"
                            ,"Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"Error el tiempo no es valido",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Error, el presupuesto no es valido"
                    ,"Error",JOptionPane.ERROR_MESSAGE);
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Error, el nombre no es valido"
                    ,"Error",JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
    
    /**
     * Esta subrutina sirve para corregir espacios en el campo de tiempo y presupuesto
     * @param number TextField donde se quiere corregir el error del TextField
     */
    
    public static void fixNumber(JTextField number){
        String k = number.getText();
        try{    
            int num = Integer.parseInt(k);
        }
        catch (NumberFormatException e){
            String numero = k.replaceAll("\\s+","");
            number.setText(numero);
            
        }
    }
    
    /**
     * Esta subrutina se encarga de expandir el Jtree para que se pueda evidenciar 
     * todos los nodos del Jtree
     * @param tree Arbol que se quiere expandir
     * @param root Raiz del Jtree
     */
    
    public static void expandAllNodes(JTree tree, DefaultMutableTreeNode root) {
        DefaultMutableTreeNode currentNode = root.getNextNode();    
        if(currentNode!= null){
            do {
                if (currentNode.getLevel()==1) 
                    tree.expandPath(new TreePath(currentNode.getPath()));
                    currentNode = currentNode.getNextNode();
                }
            while (currentNode != null);
            int j = tree.getRowCount();
            int i = 0;
            while(i < j) {
                tree.expandRow(i);
                i += 1;
                j = tree.getRowCount();
            }
        }
    }   
    
}
