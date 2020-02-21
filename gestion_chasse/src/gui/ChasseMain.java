/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
//import gui.AjouterchasseController;
        
  
/**
 *
 * @author AHMED
 */
public class ChasseMain extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
         //Parent root=FXMLLoader.load(getClass().getResource("Gestionchasse.fxml"));
        Parent root =FXMLLoader.load(getClass().getResource("Gestionchasse.fxml"));
         Scene scene = new Scene(root);
         
               // deletechass.setVisible(false);
               

       // login.setOnAction(e -> browse.setVisible(true));
        primaryStage.setTitle("Gestion Chasse");
        primaryStage.setScene(scene);
        primaryStage.show();
    
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
