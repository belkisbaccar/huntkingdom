/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MenuGUI;


import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import publicite.Entity.Promotion;
import publicite.Entity.Publicite;
import publicite.Entity.PubliciteAimer;
import publicite.Service.ServicePublicite;
import publicite.Service.ServicePubliciteAimer;
import connection.Datasource;
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
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
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
import javafx.util.Duration;
import javax.activation.DataSource;

/**
 * FXML Controller class
 *
 * @author walid
 */
public class Menu2Controller implements Initializable {

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
    private HBox imgslider;
     private Connection con;
    @FXML
    private ScrollPane scroll;

    public Menu2Controller() {
        con = Datasource.getInstance().getCnx();
    }
List<String> type;
    String imgG="";
    int verif=0;
    Publicite cc=null;
    Promotion promo=null;
      private final double IMG_WIDTH = 1125;
    private final double IMG_HEIGHT = 135;
 
    private int NUM_OF_IMGS ;
    private final int SLIDE_FREQ = 4; // in secs
         
    private Statement ste;
    private PreparedStatement pre;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // imgfront.setImage(new Image("l.png"));
            start1();
        } catch (Exception ex) {
            Logger.getLogger(Menu2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
              vbox.setSpacing(5);
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
             public void start1() throws Exception {
          
   
        
       ServicePubliciteAimer pa=new ServicePubliciteAimer();
        String req="select * from publicite  ";
        List<VBox> list = new ArrayList<>();
        
        try {
            ste=con.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                java.util.Date d1 = new java.util.Date(rs.getDate(4).getTime());
               java.util.Date d2 = new java.util.Date(rs.getDate(5).getTime());
                Publicite p= new Publicite(rs.getInt(1),rs.getString(2),rs.getString(3),d1, rs.getString(6), d2,rs.getDouble(7));
              ImageView v=new ImageView(new Image(rs.getString(3)));
                 
        v.setFitHeight(129);
        v.setFitWidth(1125);
              Button bt1=new Button("like");
                System.out.println(pa.chercher_ajout(new PubliciteAimer(p.getId_publicite(),3, d2)));
                      
                      
          if (pa.chercher_ajout(new PubliciteAimer(p.getId_publicite(),3, d2))) {
                   bt1.setDisable(true);
              }

               VBox v1=new VBox();
               v1.setAlignment(Pos.CENTER);
               v1.setSpacing(10);
               v1.getChildren().addAll(v,bt1);
               list.add(v1);
               NUM_OF_IMGS=list.size();
               bt1.setOnAction(new EventHandler<ActionEvent>() {
    @Override public void handle(ActionEvent e) {
        
        System.out.println(p.getId_publicite());
        try {
            if(!pa.chercher_ajout(new PubliciteAimer(p.getId_publicite(),3, d2)))
            {
                pa.ajouter(new PubliciteAimer(p.getId_publicite(),3,new Date()));
                bt1.setDisable(true);
            }
            
           else System.out.println("PUB DEJA AIMER");
        } catch (SQLException ex) {
            Logger.getLogger(Menu2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
});
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicePublicite.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        
       
        imgslider.getChildren().addAll(list);
        
     
        startAnimation(imgslider);
       
      }
       private void startAnimation(final HBox hbox) {
        //error occured on (ActionEvent t) line
        //slide action
        EventHandler<ActionEvent> slideAction = (ActionEvent t) -> {
            TranslateTransition trans = new TranslateTransition(Duration.seconds(1.5), hbox);
            trans.setByX(-IMG_WIDTH);
            trans.setInterpolator(Interpolator.EASE_BOTH);
            trans.play();
        };
        //eventHandler
        EventHandler<ActionEvent> resetAction = (ActionEvent t) -> {
            TranslateTransition trans = new TranslateTransition(Duration.seconds(1), hbox);
            trans.setByX((NUM_OF_IMGS - 1) * IMG_WIDTH);
            trans.setInterpolator(Interpolator.EASE_BOTH);
            trans.play();
        };
 
        List<KeyFrame> keyFrames = new ArrayList<>();
        for (int i = 1; i <= NUM_OF_IMGS; i++) {
            if (i == NUM_OF_IMGS) {
                keyFrames.add(new KeyFrame(Duration.seconds(i * SLIDE_FREQ), resetAction));
            } else {
                keyFrames.add(new KeyFrame(Duration.seconds(i * SLIDE_FREQ), slideAction));
            }
        }
//add timeLine
        Timeline anim = new Timeline(keyFrames.toArray(new KeyFrame[NUM_OF_IMGS]));
 
        anim.setCycleCount(Timeline.INDEFINITE);
        anim.playFromStart();
    } 
}
