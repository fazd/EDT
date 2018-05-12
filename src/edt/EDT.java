/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edt;

import View.Login;

/**
 *
 * @author fabio
 */
public class EDT {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Login l = new Login();
        l.setVisible(true);
        l.setLocationRelativeTo(null);
    }
    
}
