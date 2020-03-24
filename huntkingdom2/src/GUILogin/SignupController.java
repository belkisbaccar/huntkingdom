/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUILogin;

import user.Entity.Demande;
import user.Entity.user;
import user.Service.ServiceDemande;
import user.Service.ServiceUser;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
public class SignupController implements Initializable {
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
     @FXML
    private TextField user_name;
    @FXML
    private PasswordField mot_de_passe;
    @FXML
    private TextField age;
    @FXML
    private RadioButton homme;
    @FXML
    private RadioButton femme;
    @FXML
   
    private Button photo;
    @FXML
    private Button ajouter;
@FXML
    private ComboBox role;
    ObservableList<String> roleList1 = FXCollections.observableArrayList("Client","administrateur");
    ObservableList<String> roleList = FXCollections.observableArrayList("administrateur","Client");
    @FXML
    private AnchorPane parent;
    @FXML
    private ImageView imageview;
    List<String>type;
    String img="";
    @FXML
    private TextField email;
    @FXML
    private TextField telephone;
    @FXML
    private ToggleGroup sexe_EE;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         role.setValue("administrateur");
        role.setItems(roleList); 
        type=new ArrayList<>();
            type.add("*.jpg");
            type.add("*.png");
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) throws IOException, SQLException {
             String nnom = nom.getText();
       String pprenom = prenom.getText(); 
       int aage = Integer.parseInt(age.getText());
      String ssexe = homme.getText();
       if (homme.isSelected()) 
       {ssexe = homme.getText();}
       else {ssexe = femme.getText(); }
       String mmot_de_passe = mot_de_passe.getText(); 
       String pphoto = img;
       String rrole = role.getValue().toString(); 
       String uusername = user_name.getText();
       int ttelephone = Integer.parseInt(telephone.getText());
       String eemail = email.getText();
        ServiceUser sp = new ServiceUser();
        user e = new user(nnom,pprenom,aage,ssexe,mmot_de_passe,pphoto,rrole,uusername,ttelephone,eemail);
        sp.ajouter(e);   
         ServiceDemande spp = new ServiceDemande();
        Demande ee = new Demande(e.getId_user(),nnom,pprenom);
        if(  rrole.equals("administrateur")) {     
        spp.ajouter(ee);   
    JOptionPane.showMessageDialog(null, "demande d'ajout comme administrateur envoyeé");
    AnchorPane pane = FXMLLoader.load(getClass().getResource("sign_in.fxml"));
         parent.getChildren().setAll(pane);
    } else {
         JOptionPane.showMessageDialog(null, "user ajouté avec succès");
         AnchorPane pane = FXMLLoader.load(getClass().getResource("sign_in.fxml"));
         parent.getChildren().setAll(pane); }
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
