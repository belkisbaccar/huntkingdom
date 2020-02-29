/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */  
package GUIProfile;
   
import user.Entity.Annonce;
import user.Entity.commentaire;
import user.Service.ServiceAnnonce;
import user.Service.ServiceCommentaire;
import user.Service.UserSession;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class CommentController implements Initializable {
    @FXML
    private Label annonce;
    @FXML
    private Label userr;
    @FXML
    private ImageView image;
     private Annonce selectedAnnonce; 
    @FXML
    private AnchorPane parent;
    @FXML
    private TableView<?> table;
    @FXML
    private TableColumn<?, ?> idddd;
    @FXML
    private TableColumn<?, ?> comentaireee;
    @FXML
    private TableColumn<?, ?> writer;
    @FXML
    private Button ajou;
    @FXML
    private Button suppp;
    @FXML
    private Button upda;
    @FXML
    private TextField commmmmm;
    @FXML
    private Text writerrrr; 
    private commentaire cc=null;
    @FXML
    private Text iddddddddddddddd;
    
    UserSession n = UserSession.getInstance();
    @FXML
    private Text kop;
    @FXML
    private Button retourrr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        
        
        
        table.setOnMouseClicked(new EventHandler<MouseEvent>()
                
        { 
            @Override
            public void handle(MouseEvent event) { 
                 
                cc = (commentaire)table.getSelectionModel().getSelectedItem(); 
                commmmmm.setText(cc.getComentaire());
                writerrrr.setText(cc.getA());
                int od=cc.getId_commentaire();   
                String odd=String.valueOf(od);
                iddddddddddddddd.setText(odd);   
                
                
                
                
            }});
        try {
            afficher();
            
            
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(CommentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }       
     private void afficher() throws SQLException { 
        
    ServiceCommentaire sp = new ServiceCommentaire(); 
    List commentaire = sp.readAll(); 
    ObservableList et=FXCollections.observableArrayList(commentaire);
       table.setItems(et); 
       idddd.setCellValueFactory(new PropertyValueFactory<>("id_commentaire"));
       comentaireee.setCellValueFactory(new PropertyValueFactory<>("comentaire"));
       writer.setCellValueFactory(new PropertyValueFactory<>("a")); 
     
       
    } 
    
    public void initData(Annonce aa) { 
    selectedAnnonce = aa; 
    annonce.setText(selectedAnnonce.getText());
    Image i = new Image(selectedAnnonce.getImage());
    image.setImage(i);  
    String n= selectedAnnonce.getUser().getUsername();
    userr.setText(n);  
    int od=selectedAnnonce.getId_annonce();
                String odd=String.valueOf(od);
                kop.setText(odd);   
    
    }

    @FXML
    private void ajou(ActionEvent event) throws IOException {  
          FXMLLoader loader = new FXMLLoader();  
        loader.setLocation(getClass().getResource("/GUIProfile/ajout_com.fxml"));
        AnchorPane parent = loader.load(); 
        Scene tableviewscene = new Scene(parent);  
         Ajout_comController controller = loader.getController(); 
         int oddd = Integer.parseInt(kop.getText()); 
                  Annonce aaa = new Annonce();
        aaa.setId_annonce(oddd); 
       ServiceAnnonce saa = new ServiceAnnonce();  
       Annonce kkk= new Annonce(); 
                try {
                    kkk =saa.getannonce_id(aaa.getId_annonce());
                } catch (SQLException ex) {
                    Logger.getLogger(WelcomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
        controller.initDataa(kkk);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow(); 
        window.setScene(tableviewscene); 
        window.show();
    }

    @FXML
    private void suppp(ActionEvent event) throws SQLException {
        ServiceCommentaire cs = new ServiceCommentaire(); 
         cc = (commentaire)table.getSelectionModel().getSelectedItem();
         if(writerrrr.getText().equals(n.getUserName())) {
        commentaire f = new commentaire(cc.getId_commentaire());
        int pm = f.getId_commentaire();
        cs.delete(pm);
            afficher();
        JOptionPane.showMessageDialog(null, "commentaire supprimer");
        cc=null; 
        commmmmm.clear();
        writerrrr.setText(null); } else {JOptionPane.showMessageDialog(null, "Le commentaire ne vous appartient pas, vou ne pouvez pas le supprimer"); 
            cc=null; 
         commmmmm.clear();
        writerrrr.setText(null);
         }
    }

    @FXML
    private void updat(ActionEvent event) throws SQLException {
        ServiceCommentaire cs = new ServiceCommentaire(); 
         if(writerrrr.getText().equals(n.getUserName())) {
        String commme = commmmmm.getText();    
         cs.update(new commentaire(commme),cc.getId_commentaire());
            afficher();
        JOptionPane.showMessageDialog(null, "commentaire modifier");
        cc=null; 
        commmmmm.clear();
        writerrrr.setText(null); } else {JOptionPane.showMessageDialog(null, "Le commentaire ne vous appartient pas, vou ne pouvez pas le modifier"); 
            cc=null; 
         commmmmm.clear();
        writerrrr.setText(null);
         }
        
    }

    @FXML
    private void retourrr(ActionEvent event) throws IOException {
        
           FXMLLoader fxml=new FXMLLoader(getClass().getResource("/GUIProfile/Welcome.fxml"));
        
        Parent root=fxml.load();
        parent.getScene().setRoot(root);
    }
    
}
