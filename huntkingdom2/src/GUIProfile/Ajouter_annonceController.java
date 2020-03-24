/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIProfile;

import user.Entity.Annonce;
import user.Entity.user;
import user.Service.ServiceAnnonce;
import user.Service.ServiceUser;
import user.Service.UserSession;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
public class Ajouter_annonceController implements Initializable {
    @FXML
    private TextField annonce;
    @FXML
    private Button photo;
    @FXML
    private ImageView imageview;
    @FXML
    private Button ajouter;
    UserSession n = UserSession.getInstance(); 
     String img="";
      List<String>type;
    @FXML
    private AnchorPane parent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        type=new ArrayList<>();
            type.add("*.jpg");
            type.add("*.png");   
        
        // TODO
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

    @FXML
    private void ajouter(ActionEvent event) throws SQLException, IOException {
         ServiceUser cs = new ServiceUser();
        user uu = new user(); 
            uu.setUsername(n.getUserName());
            user u =cs.getuser(uu); 
        String anoncee = annonce.getText(); 
         String pphoto = img; 
         ServiceAnnonce an = new ServiceAnnonce(); 
         Annonce a = new Annonce(anoncee,pphoto,u);
         an.ajouter(a);  
         JOptionPane.showMessageDialog(null, "L'annonce ajoutée");  
         
         if(u.getRole().equals("administrateur"))
         {
   
         FXMLLoader fxml=new FXMLLoader(getClass().getResource("/GUIProfile/aann.fxml"));
        
        Parent root=fxml.load();
        parent.getScene().setRoot(root);
         
         
         }   else {
              
           FXMLLoader fxml=new FXMLLoader(getClass().getResource("/GUIProfile/Welcome.fxml"));
        
        Parent root=fxml.load();
        parent.getScene().setRoot(root);
         
         }
    }
    
}
