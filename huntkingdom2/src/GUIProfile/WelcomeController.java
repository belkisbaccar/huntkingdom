/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIProfile;

       
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import user.Entity.Annonce;
import user.Entity.user;
//import Entite.user;
import user.Service.ServiceAnnonce;
//import Service.ServiceUser;
//import Service.ServiceUser;
import user.Service.UserSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
//import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
//import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class WelcomeController implements Initializable {
    @FXML
    private Button sign_out;
    private AnchorPane parent;
    @FXML
    private ImageView imageview;
    @FXML
    private TextField nom;
    String imgg;
   
            UserSession n = UserSession.getInstance();
    @FXML
    private Button modifier;
    @FXML
    private Button annonce;
    @FXML
    private TableView<?> table;
    @FXML
    private TableColumn<?, ?> textt;
    @FXML
    private TableColumn<?, ?> photoo;
    @FXML
    private TableColumn<?, ?> Userr;
    @FXML
    private TableColumn<?, ?> aimee;
    @FXML
    private Button ai;
    @FXML
    private Button modifierr;
    @FXML
    private TableColumn<?, ?> idd;
          private Annonce cc=null;
          private Annonce c1=null;    
    @FXML
    private TextField ann;
    @FXML
    private ImageView im;
    @FXML
    private Button supprimerr;
    @FXML
    private Text uuu;
   
    String imggg="";
      List<String>type;
      @FXML
    private Button p; 
    @FXML
    private Text cxx;
    @FXML
    private TextField neer;
    @FXML
    private Button com;
    @FXML
    private ScrollPane scroll;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private VBox vbox;
    @FXML
    private ImageView imgfront;
    @FXML
    private Button menu;
    @FXML
    private Button profile;
    @FXML
    private Button products;
    @FXML
    private Button events;
    @FXML
    private Button complains;
    @FXML
    private Button hunting;
    @FXML
    private Button annonce1;
   
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
              drawer.setSidePane(vbox);
               
        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
        transition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();

            if (drawer.isOpened()) {
                
                drawer.close();
            } else {
                drawer.open();
                
            }
        });
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
            Logger.getLogger(WelcomeController.class.getName()).log(Level.SEVERE, null, ex);
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
                    Logger.getLogger(WelcomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
        if(kkk.getEtat()==1)  {
               cxx.setText("dislike");
           ai.setGraphic(cxx); } 
           else {
                   cxx.setText("like");
                   ai.setGraphic(cxx); }
                
                
                }  }); 
        
         
    
    }  
     
       /*
        
        public Annonce addddd(){ 
          table.setOnMouseClicked(new EventHandler<MouseEvent>()
                
        { 
            @Override
            public void handle(MouseEvent event) { 
              
                c1 = (Annonce)table.getSelectionModel().getSelectedItem(); 
                }  });  
       return c1;} */
        

    @FXML
    private void sign_out(ActionEvent event) throws IOException { 
     
       n.cleanUserSession(); 
       
       
           FXMLLoader fxml=new FXMLLoader(getClass().getResource("/GUILogin/sign_in.fxml"));
        
        Parent root=fxml.load();
        menu.getScene().setRoot(root);
    }

    @FXML
    private void modifier(ActionEvent event) throws IOException {
           FXMLLoader fxml=new FXMLLoader(getClass().getResource("/GUIProfile/modifier_compte.fxml"));
        
        Parent root=fxml.load();
        menu.getScene().setRoot(root);
    }

    @FXML
    private void annonce(ActionEvent event) throws IOException {
       
          FXMLLoader fxml=new FXMLLoader(getClass().getResource("/GUIProfile/ajouter_annonce.fxml"));
        
        Parent root=fxml.load();
        menu.getScene().setRoot(root);
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

    @FXML
    private void supprimerr(ActionEvent event) throws SQLException {
        ServiceAnnonce cs = new ServiceAnnonce();
        cc = (Annonce)table.getSelectionModel().getSelectedItem();
         if(cc== null){
            JOptionPane.showMessageDialog(null, "choisir annonce");
                   
        }else{   
                
              if(uuu.getText().equals(n.getUserName())) {
            
                cs.delete(cc.getId_annonce());
                afficher();
            
           JOptionPane.showMessageDialog(null, "annonce supprimer");
          
        cc=null; 
              cc=null;
            ann.clear();
        im.setImage(null);
        uuu.setText(null);
              
              }  else {  JOptionPane.showMessageDialog(null, "L'annonce ne vous appartient pas, vou ne pouvez pas le supprimer"); 
            cc=null;
            ann.clear();
        im.setImage(null);
        uuu.setText(null);
        
    } } }

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
    private void com(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();  
        loader.setLocation(getClass().getResource("/GUIProfile/comment.fxml"));
        AnchorPane parent = loader.load(); 
        Scene tableviewscene = new Scene(parent);  
        
         CommentController controller = loader.getController(); 
         int oddd = Integer.parseInt(neer.getText()); 
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
/*
    @FXML
    private void ch(ActionEvent event) throws SQLException {
         ServiceAnnonce cs = new ServiceAnnonce();
        ArrayList AL = (ArrayList) cs.readAll();
        ObservableList OReservation = FXCollections.observableArrayList(AL);
        FilteredList<Annonce> filteredData = new FilteredList<>(OReservation, p -> true);
        cherreeee.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(myObject -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(myObject.getUser()).toLowerCase().contains(lowerCaseFilter) ) {
                    return true;

                }
                return false;
            });
        });
        SortedList<Annonce> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);
    }
    */

    @FXML
    private void home(ActionEvent event) throws IOException {
                       FXMLLoader fxml=new FXMLLoader(getClass().getResource("/MenuGUI/menu2.fxml"));
        
        Parent root=fxml.load();
        menu.getScene().setRoot(root);
        
    }

    @FXML
    private void page_profile(ActionEvent event) throws IOException {
                   FXMLLoader fxml=new FXMLLoader(getClass().getResource("/GUIProfile/Welcome.fxml"));
        
        Parent root=fxml.load();
        menu.getScene().setRoot(root);
    }

    @FXML
    private void page_products(ActionEvent event) throws IOException {
                   FXMLLoader fxml=new FXMLLoader(getClass().getResource("/image/FXMLDocument.fxml"));
        
        Parent root=fxml.load();
        menu.getScene().setRoot(root);
    }

    @FXML
    private void page_events(ActionEvent event) throws IOException {
                   FXMLLoader fxml=new FXMLLoader(getClass().getResource("/MenuGUI/events.fxml"));
        
        Parent root=fxml.load();
        menu.getScene().setRoot(root);
    }

    @FXML
    private void page_annonce(ActionEvent event) throws IOException {
                   FXMLLoader fxml=new FXMLLoader(getClass().getResource("/MenuGUI/annonce.fxml"));
        
        Parent root=fxml.load();
        menu.getScene().setRoot(root);
    }

    @FXML
    private void page_complains(ActionEvent event) throws IOException {
                       FXMLLoader fxml=new FXMLLoader(getClass().getResource("/GUIR/afficher_complaint.fxml"));
        
        Parent root=fxml.load();
        menu.getScene().setRoot(root);
    }

    @FXML
    private void page_hunting(ActionEvent event) throws IOException {
                   FXMLLoader fxml=new FXMLLoader(getClass().getResource("/MenuGUI/hunting.fxml"));
        
        Parent root=fxml.load();
        menu.getScene().setRoot(root);
    }

}
