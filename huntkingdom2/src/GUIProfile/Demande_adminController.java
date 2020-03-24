/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIProfile;

import user.Entity.Demande;
//import Entite.user;
import user.Service.ServiceDemande;
import user.Service.ServiceUser;
//import Service.ServiceUser;
import java.io.IOException;
//import Service.ServiceUser;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.image.Image;
//import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class Demande_adminController implements Initializable {
    @FXML
    private AnchorPane parent;
    @FXML
    private TableView<?> table;
    @FXML
    private TableColumn<?, ?> id_demande;
    @FXML
    private TableColumn<?, ?> idd;
    @FXML
    private TableColumn<?, ?> nomm;
    @FXML
    private TableColumn<?, ?> prenomm;
    @FXML
    private Button valider;
    @FXML
    private Button delete;
    private Demande cc = null;
    @FXML
    private Button retu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            afficher();
            
// TODO
        } catch (SQLException ex) {
            Logger.getLogger(Demande_adminController.class.getName()).log(Level.SEVERE, null, ex);
        }   
        
        
        
        
        
    }    

    @FXML
    private void valider(ActionEvent event) throws IOException {
        
        
         ServiceDemande cs = new ServiceDemande();
          cc = (Demande)table.getSelectionModel().getSelectedItem();
       System.out.println(cc);
        if(cc== null){
            JOptionPane.showMessageDialog(null, "choisir admin");
                   
        }else{
            try { 
                cs.delete(cc.getId_demande());
            } catch (SQLException ex) {
                Logger.getLogger(Demande_adminController.class.getName()).log(Level.SEVERE, null, ex);
            }
    
            try {
                afficher();
            } catch (SQLException ex) {
                Logger.getLogger(Demande_adminController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
           JOptionPane.showMessageDialog(null, "deamande accepteé");
    }
         
        
    }

    @FXML
    private void delete(ActionEvent event) throws SQLException {  
      ServiceUser css = new ServiceUser();
       ServiceDemande cs = new ServiceDemande();
          cc = (Demande)table.getSelectionModel().getSelectedItem(); 
          int c = cc.getId();
       System.out.println(cc);
        if(cc== null){
            JOptionPane.showMessageDialog(null, "choisir admin");
                   
        }else{
            try { 
                 
              cs.delete(cc.getId_demande()); 
               css.delete(c);
                 
            } catch (SQLException ex) {
                Logger.getLogger(Demande_adminController.class.getName()).log(Level.SEVERE, null, ex);
            }
    
            try {
                afficher();
            } catch (SQLException ex) {
                Logger.getLogger(Demande_adminController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
           JOptionPane.showMessageDialog(null, "deamande rejeteé");
    } }
     private void afficher() throws SQLException 
   {ServiceDemande sp = new ServiceDemande();
   List Demande=sp.readAll(); 
       ObservableList et=FXCollections.observableArrayList(Demande);
       table.setItems(et);    
       id_demande.setCellValueFactory(new PropertyValueFactory<>("id_demande"));
       idd.setCellValueFactory(new PropertyValueFactory<>("id_user"));
       nomm.setCellValueFactory(new PropertyValueFactory<>("nom"));
       prenomm.setCellValueFactory(new PropertyValueFactory<>("prenom"));
   }

    @FXML
    private void retu(ActionEvent event) throws IOException {
   
     FXMLLoader fxml=new FXMLLoader(getClass().getResource("/GUIProfile/back_admin.fxml"));
        
        Parent root=fxml.load();
        parent.getScene().setRoot(root);
    }
}
