/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MenuGUI;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import connection.Datasource;
import evenement.Entity.evenement;
import evenement.Entity.participation;
import evenement.Entity.user;
import evenement.Service.participationService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author walid
 */
public class EventsController implements Initializable {

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
    private Button annonce;
    @FXML
    private Button complains;
    @FXML
    private Button hunting;
    @FXML
    private ScrollPane scroll;
    @FXML
    private VBox eventcontainer;
 private Connection con;
    public EventsController() {
        con = Datasource.getInstance().getCnx();
    }
        private Statement ste;
    private PreparedStatement pre;
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            eventcontainer.setSpacing(5);
            display_events();
        } catch (SQLException ex) {
            Logger.getLogger(EventsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        vbox.setSpacing(5);
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
    }    

      @FXML
    private void home(ActionEvent event) throws IOException {
                       FXMLLoader fxml=new FXMLLoader(getClass().getResource("menu2.fxml"));
        
        Parent root=fxml.load();
        menu.getScene().setRoot(root);
        
    }

    @FXML
    private void page_profile(ActionEvent event) throws IOException {
                   FXMLLoader fxml=new FXMLLoader(getClass().getResource("Profile.fxml"));
        
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
                   FXMLLoader fxml=new FXMLLoader(getClass().getResource("events.fxml"));
        
        Parent root=fxml.load();
        menu.getScene().setRoot(root);
    }

    @FXML
    private void page_annonce(ActionEvent event) throws IOException {
                   FXMLLoader fxml=new FXMLLoader(getClass().getResource("annonce.fxml"));
        
        Parent root=fxml.load();
        menu.getScene().setRoot(root);
    }

    @FXML
    private void page_complains(ActionEvent event) throws IOException {
                   FXMLLoader fxml=new FXMLLoader(getClass().getResource("Complains.fxml"));
        
        Parent root=fxml.load();
        menu.getScene().setRoot(root);
    }

    @FXML
    private void page_hunting(ActionEvent event) throws IOException {
                   FXMLLoader fxml=new FXMLLoader(getClass().getResource("hunting.fxml"));
        
        Parent root=fxml.load();
        menu.getScene().setRoot(root);
    }
     private void display_events() throws SQLException{
          participationService pa = new participationService();
          String req="select * from evenement  ";
          List<VBox> list = new ArrayList<>();
          ste=con.createStatement();
          ResultSet rs = ste.executeQuery(req);
          while(rs.next()){
               java.util.Date d1 = new java.util.Date(rs.getDate(4).getTime());
               java.util.Date d2 = new java.util.Date(rs.getDate(5).getTime());
              evenement e= new evenement(rs.getInt(1), rs.getString(2), rs.getString(3),d1, d2, rs.getInt(6));
              ImageView va=new ImageView(new Image(rs.getString(3)));
               va.setFitHeight(200);
        va.setFitWidth(743);
              Button bt1=new Button("participer");
              Button bt2=new Button("reclamer");
              participation pp= new participation(new user("user"),e.getId_event());
              if(pa.chercher_ajout_participation(pp)){
                  bt1.setDisable(true);
              }
              HBox h= new HBox();
              h.setSpacing(10);
              h.setAlignment(Pos.CENTER);
              h.getChildren().addAll(bt1,bt2);
               VBox v1=new VBox();
               v1.setAlignment(Pos.CENTER);
               v1.setSpacing(10);
               v1.getChildren().addAll(va,h);
               list.add(v1);
               bt1.setOnAction(new EventHandler<ActionEvent>(){
                   @Override
                   public void handle(ActionEvent event) {
                       try {
                           if(!pa.chercher_ajout_participation(pp))
                           {  pp.setDate_reservation(new Date());
                               pa.insert(pp);
                               bt1.setDisable(true);
                               
                           }          
                           else System.out.println("deja participe");
                       } catch (SQLException ex) {
                           Logger.getLogger(EventsController.class.getName()).log(Level.SEVERE, null, ex);
                       }
                   }
                   
               });
          }
         eventcontainer.getChildren().addAll(list);
     }
}
