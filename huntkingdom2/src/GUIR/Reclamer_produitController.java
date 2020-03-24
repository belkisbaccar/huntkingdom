/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIR;

import connection.Datasource;
import reclamation.Entity.Produitentity;
import reclamation.Entity.Annonce;
import reclamation.Entity.Complaints;
import reclamation.Entity.user;
import reclamation.Service.ServiceComplaint;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;
import reclamation.Service.Produit_Sessions;
import user.Service.UserSession;

/**
 * FXML Controller class
 *
 * @author belkis
 */
public class Reclamer_produitController implements Initializable {
    @FXML
    private TextArea description;
    @FXML
    private Button import_id;
    @FXML
    private Button valider_id;
    @FXML
    private Button annuler_id;
    @FXML
    private ImageView img_view;
    @FXML
    private Label nom_produit;
    private user u3;
    Produitentity c = new Produitentity();
List<String> type;
    String imgG="";
     private Connection con;
    
             Produitentity p1;
             user us;
 
    public Reclamer_produitController() {
         con = Datasource.getInstance().getCnx();
    }
             
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            
            p1=get_produit_SS();
            
            us=get_user();
            System.out.println(p1.getNomP());
            
            
            
        type=new ArrayList<>();
        
            type.add("*.jpg");
            type.add("*.png");
            
//            user_id.setText(u3.getUsername());
            nom_produit.setText(p1.getNomP());
        } catch (SQLException ex) {
            Logger.getLogger(Reclamer_produitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void importImage(ActionEvent event) {
          FileChooser f=new FileChooser();
        f.getExtensionFilters().add(new FileChooser.ExtensionFilter("jpeg,png files", type));
        File fc=f.showOpenDialog(null);
        if(f!= null)
        {
            System.out.println(fc.getName());
            imgG=fc.getAbsoluteFile().toURI().toString();
            Image i = new Image(imgG);
            img_view.setImage(i);
        }
    }

    @FXML
    private void valider(ActionEvent event) throws SQLException {
          ServiceComplaint ser = new ServiceComplaint();
        Date d=new Date();
        //System.out.println("prodyit t"+p1);
        if(!description.getText().isEmpty() || !imgG.isEmpty())
        {
            
            if(description.getText().matches("pomme") || description.getText().matches("apple") || description.getText().matches("tonton")){
               JOptionPane.showMessageDialog(null, "evitez les gros mots"); 
            }else {
            ser.ajouter_reclamation_prod(new Complaints("non traite", description.getText(), imgG, 0, null, us, d,p1));
        JOptionPane.showMessageDialog(null, "Reclamation effectuée");  
            }
        }
        else
            
        JOptionPane.showMessageDialog(null, "Remplir les champs");
    }

    @FXML
    private void annuler(ActionEvent event) {
    }
      public void initData(Produitentity ca) {

//    System.out.println("tesssssssst :"+ca);
        c = ca;
//    System.out.println("updatfvaysgajhdsbja"+c);
    }
                    public user get_user() throws SQLException
{
   user u=null;
      UserSession n = UserSession.getInstance();
                               String s1 = n.getUserName();
                               Statement stmt1 = con.createStatement();
                              String SQL1 = "SELECT * FROM user  WHERE username ='" +s1+"'";
                               ResultSet rs1 = stmt1.executeQuery(SQL1);
                               while(rs1.next())
                                {
                                   u=new user(rs1.getString(2),rs1.getString(3),0,null,null,null,null,rs1.getString(9));
                                           
                                }
        return u;
                              
    
}
                                        public Produitentity get_produit_SS() throws SQLException
{
    
      Produit_Sessions n = Produit_Sessions.getInstance();
      Produitentity ps=null;
                               int s1 = n.getId_produit();
                               Statement stmt1 = con.createStatement();
                              String SQL1 = "SELECT * FROM product  WHERE ID ='" +s1+"'";
                               ResultSet rs1 = stmt1.executeQuery(SQL1);
                               while(rs1.next())
                                {
                                    
                                             ps=new Produitentity(rs1.getString(2),rs1.getInt(3),rs1.getInt(4),rs1.getString(5));
                                }
        
                       return  ps;
    
}
}
