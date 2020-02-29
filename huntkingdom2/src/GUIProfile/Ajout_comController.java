/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIProfile;

import user.Entity.Annonce;
import user.Entity.commentaire;
import user.Entity.user;
import user.Service.ServiceAnnonce;
import user.Service.ServiceCommentaire;
import user.Service.ServiceUser;
import user.Service.UserSession;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class Ajout_comController implements Initializable {
    @FXML
    private TextField comenti;
    @FXML
    private Button ajout_c;
     UserSession n = UserSession.getInstance();
    @FXML
    private AnchorPane parent;
 private Annonce selectedAnnonce; 
    @FXML
    private Text kk;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajout(ActionEvent event) throws SQLException, IOException {
        
        ServiceUser cs = new ServiceUser();
        user uu = new user(); 
            uu.setUsername(n.getUserName());
            user u =cs.getuser(uu); 
        String comment = comenti.getText(); 
         ServiceCommentaire an = new ServiceCommentaire();  
         int i= Integer.parseInt(kk.getText());
         ServiceAnnonce l = new ServiceAnnonce(); 
         
         commentaire a = new commentaire(u,l.getannonce_id(i),comment); 
         an.ajouter(a);    
         JOptionPane.showMessageDialog(null, "commentaire ajouté");   
        FXMLLoader loader = new FXMLLoader();  
        loader.setLocation(getClass().getResource("/GUIProfile/comment.fxml"));
        AnchorPane parent = loader.load(); 
        Scene tableviewscene = new Scene(parent);  
        
         CommentController controller = loader.getController(); 
         int oddd = Integer.parseInt(kk.getText()); 
                  Annonce aaa = new Annonce();
        aaa.setId_annonce(oddd); 
       ServiceAnnonce saa = new ServiceAnnonce();  
       Annonce kkk= new Annonce(); 
                try {
                    kkk =saa.getannonce_id(aaa.getId_annonce());
                } catch (SQLException ex) {
                    Logger.getLogger(WelcomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
        controller.initData(kkk);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow(); 
        window.setScene(tableviewscene); 
        window.show();
    }
     public void initDataa(Annonce aa) { 
                  selectedAnnonce = aa; 
                 int n= selectedAnnonce.getId_annonce();
                 
                String odd=String.valueOf(n);
                kk.setText(odd);
    
    }
}
