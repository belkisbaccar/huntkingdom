/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboard;
import huntkingdom2.Huntkingdom2;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import user.Service.UserSession;



/**
 * FXML Controller class
 *
 * @author azizm
 */
public class dashboardController implements Initializable {
     private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private AnchorPane parent;
    @FXML
    private JFXButton commerce;
    @FXML
    private JFXButton user;
    @FXML
    private JFXButton complaints;
    @FXML
    private JFXButton hunt;
    @FXML
    private JFXButton events;
    @FXML
    private JFXButton publicity;
    @FXML
    private MediaView video;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
         
        
        UserSession n = UserSession.getInstance();
        
        /* makeStageDrageable();
         Media media = new Media("file:///C:/Users/azizm/Desktop/hunt1.mp4");
         MediaPlayer player = new MediaPlayer(media);
         video.setMediaPlayer(player);
         player.setVolume(0);
         player.play();*/
    }    

    @FXML
    private void close(MouseEvent event) {
         Stage stage=(Stage) parent.getScene().getWindow();
        stage.close();
    }
   
    @FXML
    private void commerce(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/commerceController/produit.fxml"));
         parent.getChildren().setAll(pane);
      
    }
      private void makeStageDrageable() {
        parent.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        parent.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Huntkingdom2.stage.setX(event.getScreenX() - xOffset);
                Huntkingdom2.stage.setY(event.getScreenY() - yOffset);
                Huntkingdom2.stage.setOpacity(0.7f);
            }
        });
        parent.setOnDragDone((e) -> {
            Huntkingdom2.stage.setOpacity(1.0f);
        });
        parent.setOnMouseReleased((e) -> {
            Huntkingdom2.stage.setOpacity(1.0f);
        });

    }

    @FXML
    private void user(ActionEvent event) throws IOException {
              AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUIProfile/back_admin.fxml"));
         parent.getChildren().setAll(pane);
    }

    @FXML
    private void complaints(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUIR/gestion_reclamation_admin.fxml"));
         parent.getChildren().setAll(pane);
    }

    @FXML
    private void hunt(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUIC/Gestionchasse.fxml"));
      parent.getChildren().setAll(pane);
//              FXMLLoader fxml=new FXMLLoader(getClass().getResource("/GUIC/Gestionchasse.fxml"));
//        
//        Parent root=fxml.load();
//        parent.getScene().setRoot(root);
    }

    @FXML
    private void events(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUIE/Addevenement.fxml"));
         parent.getChildren().setAll(pane);
    }

    @FXML
    private void publicity(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUIP/Publicite.fxml"));
        
         parent.getChildren().setAll(pane);
    }
    
}
