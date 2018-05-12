/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author jaisi
 */
public class Register extends javax.swing.JFrame {

    /**
     * Creates new form Register
     */
    private String tps;
    private StartProyect sp;
    static JFrame current;

    /**
     * Este constructor es el unico y default de la clase
     */
    
    public Register() {

        initComponents();
        this.setLocationRelativeTo(null);
        jPanel1.requestFocus();
        tps = passField.getText();
    }

    public boolean isValidEmail(String email) {
        boolean result = true;
        email = email.toLowerCase();
        if(email.matches("[a-z0-9@.]+")){
            try {
                String copia = email;
                copia = copia.replace("@uninorte.edu.co", "");
                
                if(!copia.matches("[0-9a-z]+")){
                    result = false;
                    System.out.println("copia="+copia);
                    JOptionPane.showMessageDialog(null,"Email incorrecto, tiene caracteres especiales");
                }
                else{
                    InternetAddress emailAddr = new InternetAddress(email);
                    emailAddr.validate();
                }
            } catch (AddressException ex) {
                result = false;
            }
            if (!email.matches(".*@uninorte.edu.co.*")) {
                JOptionPane.showMessageDialog(null, "Email incorrecto, solo se admiten correos uninorte.");
                result = false;
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Email incorrecto, solo se admiten letras y numeros "
                    + "y no se admiten espacios");
            return false;
        }
        return result;
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
        minimizarButton = new javax.swing.JLabel();
        cerrarButton = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        emailField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        passField = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        registrarseButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        minimizarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Expand_Arrow_32px.png"))); // NOI18N
        minimizarButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        minimizarButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                minimizarButtonMouseClicked(evt);
            }
        });
        jPanel1.add(minimizarButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 10, 30, 30));

        cerrarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Multiply_32px.png"))); // NOI18N
        cerrarButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cerrarButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cerrarButtonMouseClicked(evt);
            }
        });
        jPanel1.add(cerrarButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, 30, 30));

        jPanel2.setBackground(new java.awt.Color(255, 99, 71));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/man.png"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 260, 400));

        jLabel3.setBackground(new java.awt.Color(238, 112, 82));
        jLabel3.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(238, 112, 82));
        jLabel3.setText("Email");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 80, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_customer_32px_1.png"))); // NOI18N
        jLabel2.setToolTipText("");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 110, 30, 30));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 140, 220, 20));

        emailField.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        emailField.setForeground(new java.awt.Color(153, 153, 153));
        emailField.setText("Ingrese email");
        emailField.setToolTipText("Solo se admiten correos uninorte.");
        emailField.setBorder(null);
        emailField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                emailFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                emailFieldFocusLost(evt);
            }
        });
        emailField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                emailFieldMouseClicked(evt);
            }
        });
        jPanel1.add(emailField, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 110, 180, 30));

        jLabel4.setBackground(new java.awt.Color(238, 112, 82));
        jLabel4.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(238, 112, 82));
        jLabel4.setText("Password:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 170, -1, -1));

        passField.setForeground(new java.awt.Color(153, 153, 153));
        passField.setText("jPasswordField1");
        passField.setBorder(null);
        passField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                passFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                passFieldFocusLost(evt);
            }
        });
        jPanel1.add(passField, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 210, 180, 30));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Key_32px.png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 210, 40, 30));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 240, 220, 20));

        registrarseButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Enter_OFF.png"))); // NOI18N
        registrarseButton.setBorderPainted(false);
        registrarseButton.setContentAreaFilled(false);
        registrarseButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        registrarseButton.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Enter_ON.png"))); // NOI18N
        registrarseButton.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Enter_ON.png"))); // NOI18N
        registrarseButton.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Enter_ON.png"))); // NOI18N
        registrarseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarseButtonActionPerformed(evt);
            }
        });
        jPanel1.add(registrarseButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 300, 110, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-4, 0, 540, 400));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void minimizarButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizarButtonMouseClicked
        this.setState(Login.ICONIFIED);
    }//GEN-LAST:event_minimizarButtonMouseClicked

    private void cerrarButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cerrarButtonMouseClicked
        Login.current.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_cerrarButtonMouseClicked

    private void emailFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_emailFieldMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_emailFieldMouseClicked

    private void emailFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailFieldFocusGained
        if (emailField.getText().equals("Ingrese email")) {
            emailField.setText("");
        }
    }//GEN-LAST:event_emailFieldFocusGained

    private void emailFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailFieldFocusLost
        if (emailField.getText().equals("Ingrese email")) {
            emailField.setText("Ingrese email");
        }
    }//GEN-LAST:event_emailFieldFocusLost

    private void passFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passFieldFocusGained
        if (passField.getText().equals(tps)) {
            passField.setText("");
        }
    }//GEN-LAST:event_passFieldFocusGained

    private void passFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passFieldFocusLost
        if (passField.getText().equals(tps)) {
            passField.setText(tps);
        }
    }//GEN-LAST:event_passFieldFocusLost

    private void registrarseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarseButtonActionPerformed
        String email = emailField.getText();
        String pass = new String(passField.getPassword());
        boolean flag = true;
        if (isValidEmail(email) && pass.matches("[a-zA-Z0-9]+")) {
            try {
                if (Validations.validateData(email, null) != 3) {
                    FileWriter fstream = new FileWriter("lib\\data.txt", true);
                    try (BufferedWriter out = new BufferedWriter(fstream)) {
                        out.write(encryption.cifra(email) + "," + encryption.cifra(pass));
                        out.newLine();
                    } catch (Exception ex) {
                        Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"El usuario ya existe");
                    flag= false;
                }

            } catch (IOException e) {
                System.err.println("Error while writing to file: "
                        + e.getMessage());
            } catch (Exception ex) {
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (!pass.matches("[a-zA-Z0-9]+")){
            JOptionPane.showMessageDialog(null,"La contraseña solo puede contener numeros"
                    + " letras mayusculas y minusculas (No espacios)","Error en la contraseña",
                    JOptionPane.ERROR_MESSAGE);
            flag = false;
        }
        else {
            flag = false;
        }
        if (flag) {
            current =this;
            JOptionPane.showMessageDialog(null, "Registro correcto.");
            String[]  name = email.split("@uninorte.edu.co");
            sp = new StartProyect(name[0]);
            current.setVisible(false);
            sp.setVisible(true);  
        }
    }//GEN-LAST:event_registrarseButtonActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Register().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel cerrarButton;
    private javax.swing.JTextField emailField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel minimizarButton;
    private javax.swing.JPasswordField passField;
    private javax.swing.JButton registrarseButton;
    // End of variables declaration//GEN-END:variables
}
