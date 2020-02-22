/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboard;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author azizm
 */
public class dashboardController implements Initializable {

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void close(MouseEvent event) {
    }

    @FXML
    private void commerce(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/commerce/produit.fxml"));
         parent.getChildren().setAll(pane);
    }
    
}
