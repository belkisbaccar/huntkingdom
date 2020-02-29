/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIR;

import reclamation.Entity.Complaints;
import reclamation.Service.ServiceComplaint;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.controlsfx.control.Rating;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author belkis
 */
public class RatingController implements Initializable {

    @FXML
    private Button valider;
    @FXML
    private Rating note;
    private Complaints c; //= new Complaints(54,"traitée", "kljkhjgfhsdfs", "file:/C:/Users/belkis/Desktop/carte%20etudiant/Ateba%20Ateba%20yves%20Constant.jpg", 0, , null, null)

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        note.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                System.out.println("notuyyyyyyyy heeeeeeeeeeey");
                ServiceComplaint cs = new ServiceComplaint();
                c.setNote((int) note.getRating());
               
                try {
                    cs.noter(c, c.getId_reclamation());
                    
                    //a.update();
//                    a.initialize(null, null);

                } catch (SQLException ex) {
                    Logger.getLogger(RatingController.class.getName()).log(Level.SEVERE, null, ex);
                } 
                //JOptionPane.showMessageDialog(null, "vous avez notez avec succés"); 
                //JOptionPane.showMessageDialog(null, "vous avez notez avec succés");

            }

        }
        );

    }

    public void initData(Complaints ca) {

//    System.out.println("tesssssssst :"+ca);
        c = ca;
//    System.out.println("updatfvaysgajhdsbja"+c);
    }

    @FXML
    private void noter(ActionEvent event) throws IOException {
        
Stage stage = (Stage) valider.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader();
                                loader.setLocation(getClass().getResource("afficher_complaint.fxml"));
                                Parent tableview = loader.load();
                                Scene tableviewscene = new Scene(tableview);
                               
                                Stage stage1 = new Stage();

                                
                                stage1.setScene(tableviewscene);
                                stage1.show();
//        Parent root = FXMLLoader.load(getClass().getResource("afficher_complaint.fxml"));
//        Scene scene = new Scene(root);
//        Stage s = new Stage();
//        s.setScene(scene);
//        s.show();
        
        
    }

    public void setCompalint(int id_reclamation) {
        this.c.setId_reclamation(id_reclamation);
    }

}
