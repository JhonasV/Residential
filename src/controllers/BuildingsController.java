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
import models.Buildings;
import models.BuildingsDAO;
import views.Buildings.BuildingsCreateOrUpdate;
import views.Buildings.BuildingsCrud;

/**
 *
 * @author EnriqueCedeno
 */
public class BuildingsController implements ActionListener{
     BuildingsCreateOrUpdate createOrUpdate;
    BuildingsCrud BuildingsCrud;
    BuildingsDAO BuildingsDao;
    DefaultTableModel BuildingsTable;
    
       public BuildingsController(BuildingsCreateOrUpdate r)
    {
        this.BuildingsDao = new BuildingsDAO();

        
        this.createOrUpdate = r;
        this.createOrUpdate.btnCreate.addActionListener(this);
        this.createOrUpdate.btnClear.addActionListener(this);
        this.createOrUpdate.btnCancel.addActionListener(this);

    }
    
    public BuildingsController(BuildingsCrud u, BuildingsCreateOrUpdate r)
    {
        this.BuildingsDao = new BuildingsDAO();
        this.BuildingsCrud = u;      
        this.BuildingsTable = new DefaultTableModel();
        this.createOrUpdate = r;
        this.BuildingsCrud.btnNewBuilding.addActionListener(this);
        this.BuildingsCrud.btnRemover.addActionListener(this);
        this.BuildingsCrud.btnRefresh.addActionListener(this);
        this.BuildingsCrud.btnUpdate.addActionListener(this);
        this.ShowBuildingList(BuildingsCrud);


    }
    
    /**
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == createOrUpdate.btnCreate) {
            if(this.ValidateBuilding(createOrUpdate)){
                if(createOrUpdate.btnCreate.getText().contains("Actualizar")){
                    this.UpdateBuilding(createOrUpdate);
                }else{
                    this.CreateBuilding(createOrUpdate);  
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
        
        if(e.getSource() == BuildingsCrud.btnNewBuilding){            
            createOrUpdate = new BuildingsCreateOrUpdate();
            BuildingsController c = new BuildingsController(createOrUpdate);
            createOrUpdate.setVisible(true);
        }
        
        if(e.getSource() == BuildingsCrud.btnRefresh){
            this.ShowBuildingList(BuildingsCrud);
        }
        
        if(e.getSource() == BuildingsCrud.btnRemover){
            int selectedRow = BuildingsCrud.tblBuildings.getSelectedRow();
            if(selectedRow == -1){
                JOptionPane.showMessageDialog(null, "Debe de seleccionar un registro primero!", "Advertencia", JOptionPane.INFORMATION_MESSAGE); 
                return;
            }
            
            int result = JOptionPane.showConfirmDialog(null, "¿Seguro de remover este registro?");
            if(JOptionPane.OK_OPTION == result){
            
               int selectedId = (int)BuildingsCrud.tblBuildings.getValueAt(selectedRow, 0);
               this.RemoveBuilding(selectedId);
            }
        }
        
        if(e.getSource() == BuildingsCrud.btnUpdate){
            int selectedRow = BuildingsCrud.tblBuildings.getSelectedRow();
            if(selectedRow == -1){
                JOptionPane.showMessageDialog(null, "Debe de seleccionar un registro primero!", "Advertencia", JOptionPane.INFORMATION_MESSAGE); 
                return;
            }
            
            int selectedId = (int)BuildingsCrud.tblBuildings.getValueAt(selectedRow, 0);
            this.LoadBuildingToUpdate(selectedId);
        }
    }
    
    public void LoadBuildingToUpdate(int BuildingId){
        try{
            Buildings Building = BuildingsDao.FindOne(BuildingId);
            BuildingsCreateOrUpdate updateFrm = new BuildingsCreateOrUpdate();
            updateFrm.txtName.setText(Building.getName());
            updateFrm.lblBuildingFrm.setText("Actualización de Usuarios");
            updateFrm.btnCreate.setText("Actualizar");
            updateFrm.lblHideId.setText(Building.getId().toString());
            BuildingsController c = new BuildingsController(updateFrm);
            updateFrm.setVisible(true);
            
        }catch(RollbackException ex){
            JOptionPane.showMessageDialog(null, "Error al intentar cargar usuario para actualizar: "+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void RemoveBuilding(int BuildingId){
         try{
            BuildingsDao.Delete(BuildingId);
            this.ShowBuildingList(BuildingsCrud);
        }catch(RollbackException ex){
            JOptionPane.showMessageDialog(null, "Error al intentar borrar registro: "+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void CloseCreateOrUpdate(BuildingsCreateOrUpdate createOrUpdate){
        int result = JOptionPane.showConfirmDialog(null, "¿Estas seguro de esta acción?");
        if(JOptionPane.OK_OPTION == result){
            createOrUpdate.dispose();
        }
    }
    
    public void ClearFields(BuildingsCreateOrUpdate createOrUpdate){
        createOrUpdate.txtBuildingName.setText("");
        createOrUpdate.txtEmail.setText("");
        createOrUpdate.txtPassword.setText("");
        createOrUpdate.txtName.setText("");
        createOrUpdate.txtLastName.setText("");   
    }
    
    public ArrayList<String> GetEmptyFieldsNames(BuildingsCreateOrUpdate createOrUpdate){
        ArrayList<String> fieldsEmpty = new ArrayList();
        if(createOrUpdate.txtBuildingName.getText().equals("")){
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
    
    public Boolean ValidateBuilding(BuildingsCreateOrUpdate createOrUpdate){
        ArrayList<String> fieldsEmpty = this.GetEmptyFieldsNames(createOrUpdate);
       
        if(!fieldsEmpty.isEmpty()){
            String message = "Los siguientes campos son requeridos: "+String.join(", ", fieldsEmpty);
            JOptionPane.showMessageDialog(null, message, "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
        
        return fieldsEmpty.isEmpty();
    }
    
    public void CreateBuilding(BuildingsCreateOrUpdate createOrUpdate){
        Buildings Building = new Buildings();
        Building.setName(createOrUpdate.txtBuildingName.getText());
        Building.setName(createOrUpdate.txtName.getText());

        try{
            Buildings existsBuildingName = BuildingsDao.FindOneByBuildingName(Building.getName());
            
            if(existsBuildingName != null){
                JOptionPane.showMessageDialog(null, "El nombre de usuario ya se encuentra en uso ", "Advertencia", JOptionPane.INFORMATION_MESSAGE); 
            }
            
            
        }catch(RollbackException | HeadlessException ex){
            JOptionPane.showMessageDialog(null, "Error al intentar crear el usuario: "+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void UpdateBuilding(BuildingsCreateOrUpdate createOrUpdate){
        Buildings Building = new Buildings();
        Building.setName(createOrUpdate.txtBuildingName.getText());
        Building.setId(Integer.parseInt(createOrUpdate.lblHideId.getText()));
        try{
            Buildings existsBuilding = BuildingsDao.FindOne(Building.getId());
            if(existsBuilding != null){
                BuildingsDao.Update(Building);
            }else{
                JOptionPane.showMessageDialog(null, "No pudimos completar la actualizacion en estos momentos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
           
            
            
        }catch(RollbackException | HeadlessException ex){
            JOptionPane.showMessageDialog(null, "Error al intentar crear el usuario: "+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void ClearTable(){
        while (BuildingsTable.getRowCount()>0)
          {
             BuildingsTable.removeRow(0);
          }
    }
    
    public void ShowBuildingList(BuildingsCrud BuildingCrud){
        this.ClearTable();
        List<Buildings> Buildings = this.BuildingsDao.FindAll();
        BuildingsTable = (DefaultTableModel)BuildingCrud.tblBuildings.getModel();
        Object[] object = new Object[5];
        for(int i = 0; i < Buildings.size(); i++){
            object[0] = Buildings.get(i).getId();
            object[1] = Buildings.get(i).getName();
            BuildingsTable.addRow(object);
        }
        BuildingCrud.tblBuildings.setModel(BuildingsTable);
    }
}
