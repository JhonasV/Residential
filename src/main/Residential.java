/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
import controllers.AuthController;
import view.auth.LoginForm;


/**
 *
 * @author jhonas
 */
public class Residential {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        LoginForm loginFrm = new LoginForm();
        AuthController authContoller = new AuthController(loginFrm);
        loginFrm.setVisible(true);


    }
    
}
