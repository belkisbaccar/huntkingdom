/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIProfile;

import user.Entity.Annonce;
import user.Service.ServiceAnnonce;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AannController implements Initializable {
    private AnchorPane parent;
    @FXML
    private Button sign_out;
    @FXML
    private ImageView imageview;
    @FXML
    private TextField nom;
    @FXML
    private TextField ann;
    @FXML
    private Button p;
    @FXML
    private Text uuu;
    @FXML
    private ImageView im;
    @FXML
    private Button ai;
    @FXML
    private Text cxx;
    @FXML
    private Button supprimerr;
    @FXML
    private Button modifierr;
    @FXML
    private Button annonce;
    @FXML
    private TableView<?> table;
    @FXML
    private TableColumn<?, ?> idd;
    @FXML
    private TableColumn<?, ?> textt;
    @FXML
    private TableColumn<?, ?> photoo;
    @FXML
    private TableColumn<?, ?> Userr;
    @FXML
    private TableColumn<?, ?> aimee;
    @FXML
    private TextField neer;
     String imgg;
   
            UserSession n = UserSession.getInstance();
             private Annonce cc=null;
          private Annonce c1=null;    
           String imggg="";
      List<String>type;
    @FXML
    private Button returnnnnn;
    @FXML
    private AnchorPane parent1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        
         type=new ArrayList<>();
            type.add("*.jpg");
            type.add("*.png"); 
    
        // TODO 
            nom.setText(n.getUserName());   
        imgg =n.getPhoto();
        Image i = new Image(imgg);
        imageview.setImage(i);   
        try {
           afficher();
        } catch (SQLException ex) {
            Logger.getLogger(AannController.class.getName()).log(Level.SEVERE, null, ex);
        }    
        
        table.setOnMouseClicked(new EventHandler<MouseEvent>()
                
        { 
            @Override
            public void handle(MouseEvent event) { 
              
                cc = (Annonce)table.getSelectionModel().getSelectedItem(); 
                int od=cc.getId_annonce();
                String odd=String.valueOf(od);
                neer.setText(odd);
                uuu.setText(cc.getA());
                ann.setText(cc.getText());
                im.setImage(new Image(cc.getImage())); 
                 int oddd = Integer.parseInt(neer.getText()); 
                  Annonce aaa = new Annonce();
        aaa.setId_annonce(oddd); 
       ServiceAnnonce saa = new ServiceAnnonce();  
       Annonce kkk= new Annonce(); 
                try {
                    kkk =saa.getannonce_id(aaa.getId_annonce());
                } catch (SQLException ex) {
                    Logger.getLogger(AannController.class.getName()).log(Level.SEVERE, null, ex);
                }
        if(kkk.getEtat()==1)  {
               cxx.setText("dislike");
           ai.setGraphic(cxx); } 
           else {
                   cxx.setText("like");
                   ai.setGraphic(cxx); }
                
                
                }  }); 
    }    

    @FXML
    private void sign_out(ActionEvent event) throws IOException {  
         n.cleanUserSession(); 
       
          FXMLLoader fxml=new FXMLLoader(getClass().getResource("/GUILogin/sign_in.fxml"));
        
        Parent root=fxml.load();
        parent.getScene().setRoot(root);
    }

    @FXML
    private void import_image(ActionEvent event) { 
         FileChooser f=new FileChooser();
         f.getExtensionFilters().add(new FileChooser.ExtensionFilter("jpeg,png files", type));
        File fc=f.showOpenDialog(null);
        if(f!= null)
        {
            System.out.println(fc.getName());
            imggg=fc.getAbsoluteFile().toURI().toString();
            if(imggg!=null){
            Image i = new Image(imggg);
            im.setImage(i); }
            
    }else { im.setImage(new Image(cc.getImage())); }
    } 

    @FXML
    private void addlike(MouseEvent event) throws SQLException { 
        Annonce aa = new Annonce();
        int oddd = Integer.parseInt(neer.getText()); 
        aa.setId_annonce(oddd); 
       ServiceAnnonce sa = new ServiceAnnonce();  
       Annonce kk= new Annonce(); 
       kk =sa.getannonce_id(aa.getId_annonce());
        if(kk.getEtat()==1)  {
               cxx.setText("dislike");
           ai.setGraphic(cxx); } 
           else {
                   cxx.setText("like");
                   ai.setGraphic(cxx); }
       
            sa.addaime(kk, kk.getId_annonce());
            System.out.println(kk);
           
            afficher(); 
    }

    @FXML
    private void supprimerr(ActionEvent event) throws SQLException {
         ServiceAnnonce cs = new ServiceAnnonce();
        cc = (Annonce)table.getSelectionModel().getSelectedItem();
         if(cc== null){
            JOptionPane.showMessageDialog(null, "choisir annonce");
                   
        }else{   
                
             
            
                cs.delete(cc.getId_annonce());
                afficher();
            
           JOptionPane.showMessageDialog(null, "annonce supprimer");
          
        cc=null;  
            cc=null;
            ann.clear();
        im.setImage(null);
        uuu.setText(null);
        
    }  
    }

    @FXML
    private void modifierr(ActionEvent event) throws SQLException {
         ServiceAnnonce cs = new ServiceAnnonce();
        String pphoto="";  
        
        
        if(cc== null){
            JOptionPane.showMessageDialog(null, "choisir annonce");
        }else{ 
            
            if(uuu.getText().equals(n.getUserName())) {
        String annonc = ann.getText();  
        if(imgg.length()!=0)
       { pphoto = imggg;
       }
       else 
       {  pphoto=cc.getImage();  }
            cs.update(new Annonce(annonc,pphoto),cc.getId_annonce());
            afficher();
        JOptionPane.showMessageDialog(null, "annonce modifier");
        cc=null; 
        ann.clear();
        im.setImage(null);
        uuu.setText(null);
        
        } else { JOptionPane.showMessageDialog(null, "L'annonce ne vous appartient pas, vou ne pouvez pas le modifier"); 
            cc=null; 
        ann.clear();
        im.setImage(null);
        uuu.setText(null);
            }
        }
    }

   /* @FXML
    private void annonce(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("Ajouter_annonce.fxml"));
         parent.getChildren().setAll(pane);
    }*/
     private void afficher() throws SQLException { 
        
    ServiceAnnonce sp = new ServiceAnnonce(); 
    List Annonce = sp.readAll(); 
    ObservableList et=FXCollections.observableArrayList(Annonce);
       table.setItems(et); 
       idd.setCellValueFactory(new PropertyValueFactory<>("id_annonce"));
       textt.setCellValueFactory(new PropertyValueFactory<>("text"));
       photoo.setCellValueFactory(new PropertyValueFactory<>("img")); 
       Userr.setCellValueFactory(new PropertyValueFactory<>("a"));
       aimee.setCellValueFactory(new PropertyValueFactory<>("aime"));
       
    } 

    /*@FXML
    private void annonce(ActionEvent event) {
        
    }*/

    @FXML
    private void annonce(ActionEvent event) throws IOException { 
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUIProfile/ajouter_annonce.fxml"));
         parent1.getChildren().setAll(pane);
         
    }

    @FXML
    private void returnnn(ActionEvent event) throws IOException {
      
        
        
        FXMLLoader fxml=new FXMLLoader(getClass().getResource("/GUIProfile/back_admin.fxml"));
        
        Parent root=fxml.load();
        parent1.getScene().setRoot(root);
    }
}
