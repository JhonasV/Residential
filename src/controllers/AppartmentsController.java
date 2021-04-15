/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.RollbackException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.Appartments;
import models.AppartmentsDAO;
import views.Apartments.AppartmentCreateOrUpdate;
import views.Apartments.AppartmentsCrud;

/**
 *
 * @author EnriqueCedeno
 */
public class AppartmentsController implements ActionListener{
    AppartmentCreateOrUpdate createOrUpdate;
    AppartmentsCrud ApartmentsCrud;
    AppartmentsDAO AppartmentsDao;
    DefaultTableModel AppartmentsTable;
    
       public AppartmentsController(AppartmentCreateOrUpdate r)
    {
        this.AppartmentsDao = new AppartmentsDAO();

        
        this.createOrUpdate = r;
        this.createOrUpdate.btnCreate.addActionListener(this);
        this.createOrUpdate.btnClear.addActionListener(this);
        this.createOrUpdate.btnCancel.addActionListener(this);

    }
    
    public AppartmentsController(AppartmentsCrud u, AppartmentCreateOrUpdate r)
    {
        this.AppartmentsDao = new AppartmentsDAO();
        this.ApartmentsCrud = u;      
        this.AppartmentsTable = new DefaultTableModel();
        this.createOrUpdate = r;
        this.ApartmentsCrud.btnNewAppartment.addActionListener(this);
        this.ApartmentsCrud.btnRemover.addActionListener(this);
        this.ApartmentsCrud.btnRefresh.addActionListener(this);
        this.ApartmentsCrud.btnUpdate.addActionListener(this);
        this.ShowAppartmentList(ApartmentsCrud);


    }
    
    /**
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == createOrUpdate.btnCreate) {
            if(this.ValidateAppartment(createOrUpdate)){
                if(createOrUpdate.btnCreate.getText().contains("Actualizar")){
                    this.UpdateAppartment(createOrUpdate);
                }else{
                    this.CreateAppartment(createOrUpdate);  
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
        
        if(e.getSource() == ApartmentsCrud.btnNewAppartment){            
            createOrUpdate = new AppartmentCreateOrUpdate();
            AppartmentsController c = new AppartmentsController(createOrUpdate);
            createOrUpdate.setVisible(true);
        }
        
        if(e.getSource() == ApartmentsCrud.btnRefresh){
            this.ShowAppartmentList(ApartmentsCrud);
        }
        
        if(e.getSource() == ApartmentsCrud.btnRemover){
            int selectedRow = ApartmentsCrud.tblAppartments.getSelectedRow();
            if(selectedRow == -1){
                JOptionPane.showMessageDialog(null, "Debe de seleccionar un registro primero!", "Advertencia", JOptionPane.INFORMATION_MESSAGE); 
                return;
            }
            
            int result = JOptionPane.showConfirmDialog(null, "¿Seguro de remover este registro?");
            if(JOptionPane.OK_OPTION == result){
            
               int selectedId = (int)ApartmentsCrud.tblAppartments.getValueAt(selectedRow, 0);
               this.RemoveAppartment(selectedId);
            }
        }
        
        if(e.getSource() == ApartmentsCrud.btnUpdate){
            int selectedRow = ApartmentsCrud.tblAppartments.getSelectedRow();
            if(selectedRow == -1){
                JOptionPane.showMessageDialog(null, "Debe de seleccionar un registro primero!", "Advertencia", JOptionPane.INFORMATION_MESSAGE); 
                return;
            }
            
            int selectedId = (int)ApartmentsCrud.tblAppartments.getValueAt(selectedRow, 0);
            this.LoadAppartmentToUpdate(selectedId);
        }
    }
    
    public void LoadAppartmentToUpdate(int AppartmentId){
        try{
            Appartments Appartment = AppartmentsDao.FindOne(AppartmentId);
            AppartmentCreateOrUpdate updateFrm = new AppartmentCreateOrUpdate();
            updateFrm.txtName.setText(Appartment.getName());
            updateFrm.lblAppartmentFrm.setText("Actualización de Usuarios");
            updateFrm.btnCreate.setText("Actualizar");
            updateFrm.lblHideId.setText(Appartment.getId().toString());
            AppartmentsController c = new AppartmentsController(updateFrm);
            updateFrm.setVisible(true);
            
        }catch(RollbackException ex){
            JOptionPane.showMessageDialog(null, "Error al intentar cargar usuario para actualizar: "+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void RemoveAppartment(int AppartmentId){
         try{
            AppartmentsDao.Delete(AppartmentId);
            this.ShowAppartmentList(ApartmentsCrud);
        }catch(RollbackException ex){
            JOptionPane.showMessageDialog(null, "Error al intentar borrar registro: "+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void CloseCreateOrUpdate(AppartmentCreateOrUpdate createOrUpdate){
        int result = JOptionPane.showConfirmDialog(null, "¿Estas seguro de esta acción?");
        if(JOptionPane.OK_OPTION == result){
            createOrUpdate.dispose();
        }
    }
    
    public void ClearFields(AppartmentCreateOrUpdate createOrUpdate){
        createOrUpdate.txtBuildingName.setText("");
        createOrUpdate.txtName.setText("");
        createOrUpdate.txtNumber.setText("");   
    }
    
    public ArrayList<String> GetEmptyFieldsNames(AppartmentCreateOrUpdate createOrUpdate){
        ArrayList<String> fieldsEmpty = new ArrayList();
        
        
        if(createOrUpdate.txtBuildingName.getText().equals("")){
            fieldsEmpty.add("Edificio");
        }
        
        
        if(createOrUpdate.txtName.getText().equals("")){
            fieldsEmpty.add("Nombre");
        }
 
        if(createOrUpdate.txtNumber.getText().equals("")){
            fieldsEmpty.add("Numero");
        }
        
        return fieldsEmpty;
    }
    
    public Boolean ValidateAppartment(AppartmentCreateOrUpdate createOrUpdate){
        ArrayList<String> fieldsEmpty = this.GetEmptyFieldsNames(createOrUpdate);
       
        if(!fieldsEmpty.isEmpty()){
            String message = "Los siguientes campos son requeridos: "+String.join(", ", fieldsEmpty);
            JOptionPane.showMessageDialog(null, message, "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
        
        return fieldsEmpty.isEmpty();
    }
    
    public void CreateAppartment(AppartmentCreateOrUpdate createOrUpdate){
        Appartments Appartment = new Appartments();
        Appartment.setName(createOrUpdate.txtName.getText());

        try{
            Appartments existsAppartmentName = AppartmentsDao.FindOneByAppartmentName(Appartment.getName());
            
            if(existsAppartmentName != null){
                JOptionPane.showMessageDialog(null, "El nombre de usuario ya se encuentra en uso ", "Advertencia", JOptionPane.INFORMATION_MESSAGE); 
            }
            
            
        }catch(RollbackException | HeadlessException ex){
            JOptionPane.showMessageDialog(null, "Error al intentar crear el usuario: "+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void UpdateAppartment(AppartmentCreateOrUpdate createOrUpdate){
        Appartments Appartment = new Appartments();
        Appartment.setName(createOrUpdate.txtName.getText());
        Appartment.setId(Integer.parseInt(createOrUpdate.lblHideId.getText()));
        try{
            Appartments existsAppartment = AppartmentsDao.FindOne(Appartment.getId());
            if(existsAppartment != null){
                AppartmentsDao.Update(Appartment);
            }else{
                JOptionPane.showMessageDialog(null, "No pudimos completar la actualizacion en estos momentos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
           
            
            
        }catch(RollbackException | HeadlessException ex){
            JOptionPane.showMessageDialog(null, "Error al intentar crear el apartamento: "+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void ClearTable(){
        while (AppartmentsTable.getRowCount()>0)
          {
             AppartmentsTable.removeRow(0);
          }
    }
    
    public void ShowAppartmentList(AppartmentsCrud AppartmentCrud){
        this.ClearTable();
        List<Appartments> Appartments = this.AppartmentsDao.FindAll();
        AppartmentsTable = (DefaultTableModel)AppartmentCrud.tblAppartments.getModel();
        Object[] object = new Object[5];
        for(int i = 0; i < Appartments.size(); i++){
            object[0] = Appartments.get(i).getId();
            object[1] = Appartments.get(i).getName();
            object[2] = Appartments.get(i).getNumber();
            object[3] = Appartments.get(i).getBuildingsId();
            AppartmentsTable.addRow(object);
        }
        AppartmentCrud.tblAppartments.setModel(AppartmentsTable);
    }
}
