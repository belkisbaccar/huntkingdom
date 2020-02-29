/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIProfile;

import user.Entity.user;
import user.Service.ServiceUser;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class Gestion_userController implements Initializable {
    @FXML
    private ComboBox role;
    ObservableList<String> roleList1 = FXCollections.observableArrayList("Client","administrateur");
    ObservableList<String> roleList = FXCollections.observableArrayList("administrateur","Client");
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField age;
    @FXML
    private RadioButton homme;
    @FXML
    private RadioButton femme;
    @FXML
    private PasswordField mot_de_passe;
    @FXML
    private Button photo;
    @FXML
    private TextField user_name;
    @FXML
    private Button ajouter;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    private user cc = null;
    @FXML
    private TableView<user> table;
    @FXML
    private TableColumn<user, String> idd;
    @FXML
    private TableColumn<user, String> nomm;
    @FXML
    private TableColumn<user, String> prenomm;
    @FXML
    private TableColumn<user, String> agee;
    @FXML
    private TableColumn<user, String> sexee;
    @FXML
    private TableColumn<user, String> mot_de_passee;
    @FXML
    private TableColumn<user, String> photoo;
    @FXML
    private TableColumn<user, String> rolee;
    @FXML
    private TableColumn<user, String> user_namee;
    @FXML
    private ToggleGroup groupe;
    @FXML
    private ImageView imageview;
        List<String>type;
    String img="";
    @FXML
    private TextField search;
    @FXML
    private Button retu;
    @FXML
    private AnchorPane parent;
    @FXML
    private TextField email;
    @FXML
    private TextField telephone;
    @FXML
    private TableColumn<?, ?> telephonee;
    @FXML
    private TableColumn<?, ?> emaill;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        role.setValue("administrateur");
        role.setItems(roleList); 
             type=new ArrayList<>();
            type.add("*.jpg");
            type.add("*.png");
       try {
          afficher();
        } catch (SQLException ex) {
            Logger.getLogger(Gestion_userController.class.getName()).log(Level.SEVERE, null, ex);
        } 
       table.setOnMouseClicked(new EventHandler<MouseEvent>()
                
        {
            @Override
            public void handle(MouseEvent event) {
                cc = (user)table.getSelectionModel().getSelectedItem();
                System.out.println(cc);
                nom.setText(cc.getNom());
                prenom.setText(cc.getPrenom());
                int cage=cc.getAge();
                String aaage=String.valueOf(cage);
                age.setText(aaage);
                if(cc.getSexe().equals("homme")) 
                {homme.selectedProperty();} 
                else{femme.selectedProperty();} 
                imageview.setImage(new Image(cc.getPhoto()));
                mot_de_passe.setText(cc.getMot_de_passe()); 
                if(cc.getRole().equals("administrateur")) 
                { role.setItems(roleList1);
            }else { role.setItems(roleList); }
             user_name.setText(cc.getUsername()); 
            int tele=cc.getTelephone();
                String telee=String.valueOf(tele);
                telephone.setText(telee);
                email.setText(cc.getEmail()); 
        }
                
                
                
        
       
       
       
       
    }  ); }
    private void afficher() throws SQLException 
   {ServiceUser sp = new ServiceUser();
   List user=sp.readAll();
       ObservableList et=FXCollections.observableArrayList(user);
       table.setItems(et);
       
       idd.setCellValueFactory(new PropertyValueFactory<>("id_user"));
       nomm.setCellValueFactory(new PropertyValueFactory<>("nom"));
       prenomm.setCellValueFactory(new PropertyValueFactory<>("prenom"));
       agee.setCellValueFactory(new PropertyValueFactory<>("age"));
       sexee.setCellValueFactory(new PropertyValueFactory<>("sexe"));
       mot_de_passee.setCellValueFactory(new PropertyValueFactory<>("mot_de_passe"));
       photoo.setCellValueFactory(new PropertyValueFactory<>("img"));
       rolee.setCellValueFactory(new PropertyValueFactory<>("role"));
       user_namee.setCellValueFactory(new PropertyValueFactory<>("username"));
       telephonee.setCellValueFactory(new PropertyValueFactory<>("telephone"));
       emaill.setCellValueFactory(new PropertyValueFactory<>("email"));
   }

    @FXML
    private void supprimer(ActionEvent event) {
        ServiceUser cs = new ServiceUser();
          cc = (user)table.getSelectionModel().getSelectedItem();
        System.out.println(cc);
        if(cc== null){
            JOptionPane.showMessageDialog(null, "choisir user");
                   
        }else{
            try {
                cs.delete(cc.getId_user());
            } catch (SQLException ex) {
                Logger.getLogger(Gestion_userController.class.getName()).log(Level.SEVERE, null, ex);
            }
    
            try {
                afficher();
            } catch (SQLException ex) {
                Logger.getLogger(Gestion_userController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
           JOptionPane.showMessageDialog(null, "user supprimer");
          
        cc=null;
           nom.clear(); 
        prenom.clear(); 
        age.clear(); 
        user_name.clear(); 
        mot_de_passe.clear(); 
        imageview.setImage(null);
        telephone.clear();
        email.clear();
    }
    }

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
     
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
        int tele = Integer.parseInt(telephone.getText());
         String eemail = email.getText();
        ServiceUser sp = new ServiceUser();
        user e = new user(nnom,pprenom,aage,ssexe,mmot_de_passe,pphoto,rrole,uusername,tele,eemail);
        sp.ajouter(e);
         JOptionPane.showMessageDialog(null, "ajout avec succes");
        afficher();
        nom.clear(); 
        prenom.clear(); 
        age.clear(); 
        user_name.clear(); 
        mot_de_passe.clear(); 
        imageview.setImage(null);
        telephone.clear();
        email.clear();
    }

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
        
        ServiceUser cs = new ServiceUser();
        String pphoto="";
        System.out.println(cc);
        if(cc== null){
            JOptionPane.showMessageDialog(null, "choisir user");
                   
        }else{
 String nnom = nom.getText();
       String pprenom = prenom.getText(); 
       int aage = Integer.parseInt(age.getText());
      String ssexe = homme.getText();
       if (homme.isSelected()) 
       {ssexe = homme.getText();}
       else {ssexe = femme.getText(); }
       String mmot_de_passe = mot_de_passe.getText(); 
       if(img.length()!=0)
       { pphoto = img;
       }
       else 
       {  pphoto=cc.getPhoto();  }
       String rrole = role.getValue().toString(); 
       String uusername = user_name.getText();
                    int tele = Integer.parseInt(telephone.getText()); 
                    String eemail = email.getText();
       cs.update(new user(nnom,pprenom,aage,ssexe,mmot_de_passe,pphoto,rrole,uusername,tele,eemail),cc.getId_user());
       afficher();
        JOptionPane.showMessageDialog(null, "user modifier");
       
        cc=null;
            nom.clear(); 
        prenom.clear(); 
        age.clear(); 
        user_name.clear(); 
        mot_de_passe.clear(); 
        imageview.setImage(null);
        telephone.clear();
        email.clear();
          
  
           
          
 
        
    }
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
    private void recherche(ActionEvent event) throws SQLException {
         ServiceUser cs = new ServiceUser();
        ArrayList AL = (ArrayList) cs.readAll();
        ObservableList OReservation = FXCollections.observableArrayList(AL);
        FilteredList<user> filteredData = new FilteredList<>(OReservation, p -> true);
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(myObject -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(myObject.getUsername()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;

                }
                return false;
            });
        });
        SortedList<user> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);
        
    }

    @FXML
    private void retu(ActionEvent event) throws IOException {
              FXMLLoader fxml=new FXMLLoader(getClass().getResource("/GUIProfile/back_admin.fxml"));
        
        Parent root=fxml.load();
        parent.getScene().setRoot(root);
    }
    
    
    
    
}
