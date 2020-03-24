/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIProfile;
 
import user.Service.UserSession;
import java.io.IOException;
import java.net.URL;
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

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class Back_adminController implements Initializable {
    @FXML
    private Button traiter;
    @FXML
    private Button aff;
    @FXML
    private AnchorPane parent;
    @FXML
    private ImageView imageview;
    @FXML
    private TextField nom;
    String imgg="";
  
    UserSession a = UserSession.getInstance();
    
    @FXML
    private Button sign_out;
    @FXML
    private Button anonce;
    @FXML
    private Button stat;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
           nom.setText(a.getUserName());
        imgg =a.getPhoto();
        Image i = new Image(imgg);
        imageview.setImage(i); } 
       
        // TODO
      

    @FXML
    private void traiter(ActionEvent event) throws IOException {
        
         AnchorPane pane = FXMLLoader.load(getClass().getResource("Demande_admin.fxml"));
         parent.getChildren().setAll(pane);
    }

    @FXML
    private void aff(ActionEvent event) throws IOException {
          FXMLLoader fxml=new FXMLLoader(getClass().getResource("/GUIProfile/Gestion_user.fxml"));
        
        Parent root=fxml.load();
        parent.getScene().setRoot(root);
    }

    @FXML
    private void sign_out(ActionEvent event) throws IOException {
   
        a.cleanUserSession();
   
  
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUILogin/sign_in.fxml"));
         parent.getChildren().setAll(pane);
    }

    @FXML
    private void anonce(ActionEvent event) throws IOException {  
        
        AnchorPane pane = FXMLLoader.load(getClass().getResource("aann.fxml"));
         parent.getChildren().setAll(pane);
        
    }

    @FXML
    private void stat(ActionEvent event) throws IOException {
         
        AnchorPane pane = FXMLLoader.load(getClass().getResource("stat.fxml"));
         parent.getChildren().setAll(pane);
    }
    
}
