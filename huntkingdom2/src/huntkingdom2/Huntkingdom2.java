/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huntkingdom2;

import connection.Datasource;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 *
 * @author azizm
 */
public class Huntkingdom2 extends Application {
     public static Stage stage = null;
    @Override
    public void start(Stage stage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("/GUILogin/sign_in.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
          //stage.initStyle(StageStyle.UNDECORATED);
        this.stage = stage;
        stage.show();
       
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Datasource ds= Datasource.getInstance();
        launch(args);
    }
    
}
