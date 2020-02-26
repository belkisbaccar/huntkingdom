/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUILogin;

//import Service.UserSession;
import user.Entity.user;
import user.Service.ServiceUser;
import user.Service.UserSession;
import connection.Datasource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class Sign_inController implements Initializable {
    @FXML
    private AnchorPane parent;
    @FXML
    private Button registrer;
    @FXML
    private TextField username; 
     @FXML
    private PasswordField mot_de_passe;
    @FXML
    private Button sign;
     private Connection cnx;
     private Statement st;
     private PreparedStatement stmt;
     private ResultSet s;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          
        // TODO
    }    

    @FXML
    private void registrer(ActionEvent event) throws IOException {
        
        AnchorPane pane = FXMLLoader.load(getClass().getResource("signup.fxml"));
         parent.getChildren().setAll(pane);
      
        
    }
    @FXML
    private void sign(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
      
        cnx=  Datasource.getInstance().getCnx();
        ResultSet rs=cnx.createStatement().executeQuery("select * from user WHERE username='"+username.getText()+"'and mot_de_passe='"+mot_de_passe.getText()+"' ");
        if(rs.next())
        { 
            if(rs.getString("role").equals("administrateur"))
            { 
                ServiceUser user_ser= new ServiceUser();
                 user uu = new user(username.getText());
                user u2 =user_ser.getuser(uu); 
              int s= rs.getInt("id_user");
              
                UserSession.getInstace(u2.getUsername(),u2.getPhoto());  
              
                AnchorPane pane = FXMLLoader.load(getClass().getResource("/dashboard/FXML.fxml"));
         parent.getChildren().setAll(pane);
               
            }
            else { 
              
                ServiceUser user_ser= new ServiceUser();
                 user u = new user(username.getText());
                user u1 =user_ser.getuser(u);
                
                UserSession.getInstace(u1.getUsername(),u1.getPhoto());  
          
               // UserSession k = UserSession.getInstance();
               // System.out.println(k);
                ScrollPane pane = FXMLLoader.load(getClass().getResource("/MenuGUI/menu2.fxml"));
         parent.getChildren().setAll(pane);
        }
    } else { JOptionPane.showMessageDialog(null, "Verifier Login / Mot de passe", "Message d'erreur:", JOptionPane.ERROR_MESSAGE);
        username.clear(); 
        mot_de_passe.clear();
        }
    }
    }
        
        
        
        
        
        
        
         
       //  AnchorPane pane = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
        // parent.getChildren().setAll(pane);
      
        
    
    

