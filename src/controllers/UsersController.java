/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.HeadlessException;
import java.awt.Rectangle;
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
        this.usersCrud.btnRemover.addActionListener(this);
        this.usersCrud.btnRefresh.addActionListener(this);
        this.usersCrud.btnUpdate.addActionListener(this);
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
                if(createOrUpdate.btnCreate.getText().contains("Actualizar")){
                    this.UpdateUser(createOrUpdate);
                }else{
                    this.CreateUser(createOrUpdate);  
                }
                this.ClearFields(createOrUpdate);
            }
        }
        
        if(e.getSource() == createOrUpdate.btnClear){
            this.ClearFields(createOrUpdate);
        }
        
        if(e.getSource() == createOrUpdate.btnCancel){
           this.CloseCreateOrUpdate(createOrUpdate);
        }
        
        if(e.getSource() == usersCrud.btnNewUser){            
            createOrUpdate = new CreateOrUpdate();
            UsersController c = new UsersController(createOrUpdate);
            createOrUpdate.setVisible(true);
        }
        
        if(e.getSource() == usersCrud.btnRefresh){
            this.ShowUserList(usersCrud);
        }
        
        if(e.getSource() == usersCrud.btnRemover){
            int selectedRow = usersCrud.tblUsers.getSelectedRow();
            if(selectedRow == -1){
                JOptionPane.showMessageDialog(null, "Debe de seleccionar un registro primero!", "Advertencia", JOptionPane.INFORMATION_MESSAGE); 
                return;
            }
            
            int result = JOptionPane.showConfirmDialog(null, "¿Seguro de remover este registro?");
            if(JOptionPane.OK_OPTION == result){
            
               int selectedId = (int)usersCrud.tblUsers.getValueAt(selectedRow, 0);
               this.RemoveUser(selectedId);
            }
        }
        
        if(e.getSource() == usersCrud.btnUpdate){
            int selectedRow = usersCrud.tblUsers.getSelectedRow();
            if(selectedRow == -1){
                JOptionPane.showMessageDialog(null, "Debe de seleccionar un registro primero!", "Advertencia", JOptionPane.INFORMATION_MESSAGE); 
                return;
            }
            
            int selectedId = (int)usersCrud.tblUsers.getValueAt(selectedRow, 0);
            this.LoadUserToUpdate(selectedId);
        }
    }
    
    public void LoadUserToUpdate(int userId){
        try{
            Users user = usersDao.FindOne(userId);
            CreateOrUpdate updateFrm = new CreateOrUpdate();
            updateFrm.txtEmail.setText(user.getEmail());
            updateFrm.txtLastName.setText(user.getLastName());
            updateFrm.txtName.setText(user.getName());
            updateFrm.txtPassword.setText(user.getPassword());
            updateFrm.txtUserName.setText(user.getUserName());
            updateFrm.lblUserFrm.setText("Actualización de Usuarios");
            updateFrm.btnCreate.setText("Actualizar");
            updateFrm.lblHideId.setText(user.getId().toString());
            UsersController c = new UsersController(updateFrm);
            updateFrm.setVisible(true);
            
        }catch(RollbackException ex){
            JOptionPane.showMessageDialog(null, "Error al intentar cargar usuario para actualizar: "+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void RemoveUser(int userId){
         try{
            usersDao.Delete(userId);
            this.ShowUserList(usersCrud);
        }catch(RollbackException ex){
            JOptionPane.showMessageDialog(null, "Error al intentar borrar registro: "+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void CloseCreateOrUpdate(CreateOrUpdate createOrUpdate){
        int result = JOptionPane.showConfirmDialog(null, "¿Estas seguro de esta acción?");
        if(JOptionPane.OK_OPTION == result){
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
            Users existsUserName = usersDao.FindOneByUserName(user.getUserName());
            
            if(existsUserName == null){
              Users existsEmail = usersDao.FindOneByEmail(user.getEmail());    
              if(existsEmail == null){
                  usersDao.Save(user);
                  JOptionPane.showMessageDialog(null, "El Usuario se ha creadio de forma exitosa!", "Exito", JOptionPane.INFORMATION_MESSAGE);
              }else{
                  JOptionPane.showMessageDialog(null, "El email ingresado ya se encuentra en uso. ", "Advertencia", JOptionPane.INFORMATION_MESSAGE);               
              }
              
              
            }else{
               JOptionPane.showMessageDialog(null, "El nombre de usuario ya se encuentra en uso ", "Advertencia", JOptionPane.INFORMATION_MESSAGE);               
            }
            
            
        }catch(RollbackException | HeadlessException ex){
            JOptionPane.showMessageDialog(null, "Error al intentar crear el usuario: "+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void UpdateUser(CreateOrUpdate createOrUpdate){
        Users user = new Users();
        user.setUserName(createOrUpdate.txtUserName.getText());
        user.setEmail(createOrUpdate.txtEmail.getText());
        user.setPassword(createOrUpdate.txtPassword.getText());
        user.setName(createOrUpdate.txtName.getText());
        user.setLastName(createOrUpdate.txtLastName.getText());
        user.setId(Integer.parseInt(createOrUpdate.lblHideId.getText()));
        try{
            Users existsUser = usersDao.FindOne(user.getId());
            if(existsUser != null){
                usersDao.Update(user);
            }else{
                JOptionPane.showMessageDialog(null, "No pudimos completar la actualizacion en estos momentos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
           
            
            
        }catch(RollbackException | HeadlessException ex){
            JOptionPane.showMessageDialog(null, "Error al intentar crear el usuario: "+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void ClearTable(){
        while (usersTable.getRowCount()>0)
          {
             usersTable.removeRow(0);
          }
    }
    
    public void ShowUserList(UsersCrud userCrud){
        this.ClearTable();
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
