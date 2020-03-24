/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIProfile;

import user.Entity.user;
import user.Service.ServiceUser;
import user.Service.UserSession;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class Modifier_compteController implements Initializable {
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField telephone;
    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private Button modify;
UserSession n = UserSession.getInstance();
    @FXML
    private TextField mot_de_passe;
    @FXML
    private Button photo;
    @FXML
    private ImageView imageview;
     String img="";
      List<String>type;
    @FXML
    private AnchorPane parent;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      
        try {
            // TODO
            type=new ArrayList<>();
            type.add("*.jpg");
            type.add("*.png");  
            
            ServiceUser user_ser= new ServiceUser();
            user uu = new user(); 
            uu.setUsername(n.getUserName());
            user u =user_ser.getuser(uu);
            
            nom.setText(u.getNom());
            prenom.setText(u.getPrenom()); 
            email.setText(u.getEmail());
            mot_de_passe.setText(u.getMot_de_passe());
            int tele=u.getTelephone();
            String telee=String.valueOf(tele);
            telephone.setText(telee);
            username.setText(u.getUsername());
            imageview.setImage(new Image(u.getPhoto()));
        } catch (SQLException ex) {
            Logger.getLogger(Modifier_compteController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
            
            
           
               
               
               
               
            
           
        
            
        
    }    

    @FXML
    private void modify(ActionEvent event) throws SQLException, IOException {  
        ServiceUser cs = new ServiceUser();
        user uu = new user(); 
            uu.setUsername(n.getUserName());
            user u =cs.getuser(uu);
        String pphoto=""; 
         String nnom = nom.getText();
       String pprenom = prenom.getText(); 
        String mmot_de_passe = mot_de_passe.getText(); 
        if(img.length()!=0)
       { pphoto = img;
       }
       else 
       {  pphoto=u.getPhoto();  } 
        String uusername = username.getText();
                    int tele = Integer.parseInt(telephone.getText()); 
                    String eemail = email.getText();
        cs.update(new user(nnom,pprenom,mmot_de_passe,pphoto,uusername,tele,eemail),u.getId_user()); 
     JOptionPane.showMessageDialog(null, "parametre modifié");
    AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUIProfile/Welcome.fxml"));
         parent.getChildren().setAll(pane);   
        
    }

    @FXML
    private void import_image(ActionEvent event) {
    
    
     FileChooser f=new FileChooser();
        f.getExtensionFilters().add(new FileChooser.ExtensionFilter("jpeg,png files", type));
        File fc=f.showOpenDialog(null);
        if(f!= null)
        {
            System.out.println(fc.getName());
            img=fc.getAbsoluteFile().toURI().toString();
            Image i = new Image(img);
            imageview.setImage(i);
        }
    }
    
}
