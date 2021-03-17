/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import models.Users;
import models.UsersDAO;
import view.admin.AdminMenu;
import view.auth.LoginForm;

/**
 *
 * @author jhonas
 */
public class AuthController implements ActionListener {

    
    LoginForm loginFrm;
    UsersDAO userDao;
    public AuthController(LoginForm loginFrm){
        this.loginFrm = loginFrm;
        this.userDao = new UsersDAO();
        
        this.loginFrm.btnLogin.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.loginFrm.btnLogin){
            if(this.ValidateValues(loginFrm)){
                if(this.LogIn(loginFrm)){                
                    // Hide login
                    this.loginFrm.dispose();
                    // Validate role and show Guest menu or Admin menu
                    AdminMenu menu = new AdminMenu();
                    menu.setVisible(true);
                }
            }
        }
    }
    
    public Boolean ValidateValues(LoginForm loginFrm){
        ArrayList<String> emptyFields = new ArrayList<>();
        
        if(loginFrm.txtUserNameOrEmail.getText().equals("")){
            emptyFields.add("Nombre de Usuario o Email");
        }
        
         if(loginFrm.txtPassword.getText().equals("")){
            emptyFields.add("Contraseña");
        }       
    
        if(emptyFields.isEmpty()){
            return true;
        }else{
            String message = "Los siguientes campos son requeridos: "+String.join(", ", emptyFields);
            JOptionPane.showMessageDialog(null, message, "Advertencia", JOptionPane.WARNING_MESSAGE);
            return false;
        } 

    }
    
    public Boolean LogIn(LoginForm loginFrm){
        Users userByEmail = userDao.FindOneByEmail(loginFrm.txtUserNameOrEmail.getText());
        
        if(userByEmail == null){
          Users userByUserName = userDao.FindOneByUserName(loginFrm.txtUserNameOrEmail.getText());
          if(userByUserName == null){
            JOptionPane.showMessageDialog(null, "Nombre de usuario, email o contraseña invalida", "Advertencia", JOptionPane.WARNING_MESSAGE);
          }else{
              return userByUserName.getPassword().equals(loginFrm.txtPassword.getText()); 
          }              
        }
        return userByEmail.getPassword().equals(loginFrm.txtPassword.getText());  
    }
    
}
