/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIProfile;
   
import connection.Datasource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;


/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class StatController implements Initializable {
   
private Connection cnx;
    @FXML
    private BarChart<String,Integer> barChart;
    @FXML
    private Button retu;
    @FXML
    private AnchorPane parent;
    @FXML
    private AnchorPane parent2;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 
      load();
    }    

   
    private void load() {
     
        try {
            
            String query="select id_annonce,aime FROM annonce ORDER BY aime";
            XYChart.Series<String,Integer> series = new XYChart.Series<>();
             
            cnx = Datasource.getInstance().getCnx();
            ResultSet rss = null;
            try {
                rss = cnx.createStatement().executeQuery(query);
            } catch (SQLException ex) {
                Logger.getLogger(StatController.class.getName()).log(Level.SEVERE, null, ex);
            }
            while (rss.next())
            {
                try { 
                    int a = rss.getInt(1);
                        String odd=String.valueOf(a);
                    series.getData().add(new XYChart.Data<>(odd,rss.getInt(2)));
                } catch (SQLException ex) {
                    Logger.getLogger(StatController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            barChart.getData().add(series);
        } catch (SQLException ex) {
            Logger.getLogger(StatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void returnn(ActionEvent event) throws IOException {
           FXMLLoader fxml=new FXMLLoader(getClass().getResource("/GUIProfile/back_admin.fxml"));
        
        Parent root=fxml.load();
        parent2.getScene().setRoot(root);
    }
    
}
