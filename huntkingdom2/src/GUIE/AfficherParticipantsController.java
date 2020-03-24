/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIE;

import evenement.Service.evenementService;
import evenement.Service.participationService;
//import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import evenement.Entity.evenement;
import evenement.Entity.participation;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import connection.Datasource;

/**
 * FXML Controller class
 *
 * @author hazem
 */
public class AfficherParticipantsController implements Initializable {
    @FXML
    private Button supprimerP;
    @FXML
    private AnchorPane page;
    @FXML
    private TableColumn<participation,String> id;
    @FXML
    private TableColumn<participation,String> id_user;
    @FXML
    private TableColumn<participation,String> id_event;
    @FXML
    private TableColumn<participation,String> date;
    @FXML
    private TableView<participation> table;
    private participation cc=null;
    @FXML
    private BarChart<String, Integer> chart;
    @FXML
    private Button stat;
     private Connection cnx;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficher();
        loadChart();
    }    
 private void afficher()
   {participationService sp = new participationService();
   List participants=sp.displayAll();
       ObservableList et=FXCollections.observableArrayList(participants);
       table.setItems(et);
       
       id.setCellValueFactory(new PropertyValueFactory<>("id_participation"));
       id_user.setCellValueFactory(new PropertyValueFactory<>("nom"));
       id_event.setCellValueFactory(new PropertyValueFactory<>("id_event"));
       date.setCellValueFactory(new PropertyValueFactory<>("date_reservation"));
       
   
   }
    @FXML
    private void suprrimerP(ActionEvent event) {
         participationService cs = new participationService();
         participation cc;
        cc = (participation)table.getSelectionModel().getSelectedItem();
        System.out.println(cc);
        if(cc== null){
            JOptionPane.showMessageDialog(null, "choisir participant");
                   
        }else{
            cs.delete(cc.getId_participation());
    
           afficher();
           
           JOptionPane.showMessageDialog(null, "participant supprimer");
            
        cc=null;
    }
    }

    @FXML
    private void retourner(ActionEvent event) throws IOException {
        AnchorPane pane=FXMLLoader.load(getClass().getResource("Addevenement.fxml"));
        page.getChildren().setAll(pane);
    }

    @FXML
    private void loadChart(ActionEvent event) {
        try {
            String query="select titre_event,nb_place_event FROM evenement ORDER BY   nb_place_event";
            XYChart.Series<String,Integer> series = new XYChart.Series<>();
            cnx = Datasource.getInstance().getCnx();
            ResultSet rss = null;
            try {
                rss = cnx.createStatement().executeQuery(query);
            } catch (SQLException ex) {
                Logger.getLogger(AfficherParticipantsController.class.getName()).log(Level.SEVERE, null, ex);
            }
            while (rss.next())
            {
                try {
                    series.getData().add(new XYChart.Data<>(rss.getString(1), rss.getInt(2)));
                } catch (SQLException ex) {
                    Logger.getLogger(AfficherParticipantsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            chart.getData().add(series);
        } catch (SQLException ex) {
            Logger.getLogger(AfficherParticipantsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadChart() {
       try {
            String query="select titre_event,nb_place_event FROM evenement ORDER BY   nb_place_event";
            XYChart.Series<String,Integer> series = new XYChart.Series<>();
            cnx = Datasource.getInstance().getCnx();
            ResultSet rss = null;
            try {
                rss = cnx.createStatement().executeQuery(query);
            } catch (SQLException ex) {
                Logger.getLogger(AfficherParticipantsController.class.getName()).log(Level.SEVERE, null, ex);
            }
            while (rss.next())
            {
                try {
                    series.getData().add(new XYChart.Data<>(rss.getString(1), rss.getInt(2)));
                } catch (SQLException ex) {
                    Logger.getLogger(AfficherParticipantsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            chart.getData().add(series);
        } catch (SQLException ex) {
            Logger.getLogger(AfficherParticipantsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
