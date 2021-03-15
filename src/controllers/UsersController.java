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
import javax.persistence.RollbackException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.Users;
import models.UsersDAO;
import views.users.CreateOrUpdate;
import views.users.UsersCrud;

/**
 *
 * @author jhonas
 */
public class UsersController implements ActionListener {
    

    CreateOrUpdate createOrUpdate;
    UsersCrud usersCrud;
    UsersDAO usersDao;
    DefaultTableModel usersTable;
    
       public UsersController(CreateOrUpdate r)
    {
        this.usersDao = new UsersDAO();

        
        this.createOrUpdate = r;
        this.createOrUpdate.btnCreate.addActionListener(this);
        this.createOrUpdate.btnClear.addActionListener(this);
        this.createOrUpdate.btnCancel.addActionListener(this);

    }
    
    public UsersController(UsersCrud u, CreateOrUpdate r)
    {
        this.usersDao = new UsersDAO();
        this.usersCrud = u;      
        this.usersTable = new DefaultTableModel();
        this.createOrUpdate = r;
        this.usersCrud.btnNewUser.addActionListener(this);
        this.ShowUserList(usersCrud);


    }
    
    /**
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == createOrUpdate.btnCreate) {
            if(this.ValidateUser(createOrUpdate)){
                this.CreateUser(createOrUpdate);                
            }
        }
        
        if(e.getSource() == createOrUpdate.btnClear){
            this.ClearFields(createOrUpdate);
        }
        
        if(e.getSource() == createOrUpdate.btnCancel){
           this.CloseCreateOrUpdate(createOrUpdate);
           this.ShowUserList(usersCrud);
        }
        
        if(e.getSource() == usersCrud.btnNewUser){
            createOrUpdate = new CreateOrUpdate();
            UsersController c = new UsersController(createOrUpdate);
            createOrUpdate.setVisible(true);
        }
    }
    
    public void CloseCreateOrUpdate(CreateOrUpdate createOrUpdate){
         ArrayList<String> emptyFieldNames =  this.GetEmptyFieldsNames(createOrUpdate);
         
         if(!emptyFieldNames.isEmpty()){
            int result = JOptionPane.showConfirmDialog(null, "Hay campos con datos, ¿Estas seguro de esta acción?");
            if(JOptionPane.OK_OPTION == result){
                createOrUpdate.dispose();
            }
         }else{
              createOrUpdate.dispose();
         }
    }
    
    public void ClearFields(CreateOrUpdate createOrUpdate){
        createOrUpdate.txtUserName.setText("");
        createOrUpdate.txtEmail.setText("");
        createOrUpdate.txtPassword.setText("");
        createOrUpdate.txtName.setText("");
        createOrUpdate.txtLastName.setText("");   
    }
    
    public ArrayList<String> GetEmptyFieldsNames(CreateOrUpdate createOrUpdate){
        ArrayList<String> fieldsEmpty = new ArrayList();
        if(createOrUpdate.txtUserName.getText().equals("")){
            fieldsEmpty.add("Nombre de Usuario");
        }
        
        if(createOrUpdate.txtEmail.getText().equals("")){
            fieldsEmpty.add("Email");
        }
        
        if(createOrUpdate.txtPassword.getText().equals("")){
            fieldsEmpty.add("Contraseña");
        }
        
        if(createOrUpdate.txtName.getText().equals("")){
            fieldsEmpty.add("Nombre");
        }
 
        if(createOrUpdate.txtLastName.getText().equals("")){
            fieldsEmpty.add("Apellido");
        }
        
        return fieldsEmpty;
    }
    
    public Boolean ValidateUser(CreateOrUpdate createOrUpdate){
        ArrayList<String> fieldsEmpty = this.GetEmptyFieldsNames(createOrUpdate);
       
        if(!fieldsEmpty.isEmpty()){
            String message = "Los siguientes campos son requeridos: "+String.join(", ", fieldsEmpty);
            JOptionPane.showMessageDialog(null, message, "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
        
        return fieldsEmpty.isEmpty();
    }
    
    public void CreateUser(CreateOrUpdate createOrUpdate){
        Users user = new Users();
        user.setUserName(createOrUpdate.txtUserName.getText());
        user.setEmail(createOrUpdate.txtEmail.getText());
        user.setPassword(createOrUpdate.txtPassword.getText());
        user.setName(createOrUpdate.txtName.getText());
        user.setLastName(createOrUpdate.txtLastName.getText());
        
        try{
            usersDao.Save(user);
            JOptionPane.showMessageDialog(null, "El Usuario se ha creadio de forma exitosa!", "Exito", JOptionPane.INFORMATION_MESSAGE);
        }catch(RollbackException ex){
            JOptionPane.showMessageDialog(null, "Error al intentar crear el usuario: "+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void ShowUserList(UsersCrud userCrud){
        List<Users> users = this.usersDao.FindAll();
        usersTable = (DefaultTableModel)userCrud.tblUsers.getModel();
        Object[] object = new Object[5];
        for(int i = 0; i < users.size(); i++){
            object[0] = users.get(i).getId();
            object[1] = users.get(i).getUserName();
            object[2] = users.get(i).getEmail();
            object[3] = users.get(i).getName();
            object[4] = users.get(i).getLastName();
            usersTable.addRow(object);
        }
        userCrud.tblUsers.setModel(usersTable);
    }
}
