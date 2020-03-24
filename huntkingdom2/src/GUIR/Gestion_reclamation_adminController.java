/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIR;


import reclamation.API.Mail;
import reclamation.Entity.Complaints;
import reclamation.Service.ServiceComplaint;
import com.sun.javafx.font.CompositeGlyphMapper;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.F;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author belkis
 */
public class Gestion_reclamation_adminController implements Initializable {
    @FXML
    private TableView<Complaints> table_id;
    @FXML
    private TableColumn<Complaints, String> reclamation_id;
    @FXML
    private TableColumn<Complaints, String> etat_id;
    @FXML
    private TableColumn<Complaints, String> desc_id;
    @FXML
    private TableColumn<Complaints, String> image_id;
    @FXML
    private TableColumn<Complaints, String> note_id;
//    private TableColumn<Complaints, String> id_annonce;
     @FXML
    private TableColumn<Complaints, String> id_obj;
    @FXML
    private TableColumn<Complaints, String> id_user;
    @FXML
    private TableColumn<Complaints, String> date_id;
    @FXML
    private Button supprimer;
    @FXML
    private Button modifier;
    @FXML
    private ComboBox<String>  combo;
    @FXML
    private TextField chercher_id;
 private Complaints c;
 
 private  ObservableList<Complaints> datalist = FXCollections.observableArrayList(); 
         @FXML
    private PieChart Pie_chart;
    @FXML
    private LineChart<?, ?> linechart;
    @FXML
    private NumberAxis Y;
    @FXML
    private CategoryAxis X;
    @FXML
    private Button retour1;
    @FXML
    private Button retour;

   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ServiceComplaint service = new ServiceComplaint();
        try {
            ObservableList<PieChart.Data> piechart = FXCollections.observableArrayList(
                    new PieChart.Data("non traitée", service.stats_non_traite()),
                    new PieChart.Data("en cours", service.stats_en_cours()),
                    new PieChart.Data("traitée", service.stats_traite())
            );
            Pie_chart.setData(piechart);
//            System.out.println("non traité"+service.stats_non_traite());
//            System.out.println("en cours"+service.stats_en_cours());
//            System.out.println("traitée"+service.stats_traite());
            
            XYChart.Series series = new XYChart.Series();
//            series.getData().add(new XYChart.Data("Non traitée",service.stats_non_traite()));
//            series.getData().add(new XYChart.Data("En cours",service.stats_en_cours()));
//            series.getData().add(new XYChart.Data("Traitée",service.stats_traite()));
            series.getData().add(new XYChart.Data("note = 0",service.stats_0()));
            series.getData().add(new XYChart.Data("note = 1",service.stats_1()));
            series.getData().add(new XYChart.Data("note = 2",service.stats_2()));
            series.getData().add(new XYChart.Data("note = 3",service.stats_3()));
            series.getData().add(new XYChart.Data("note = 4",service.stats_4()));
            series.getData().add(new XYChart.Data("note = 5",service.stats_5()));

            linechart.getData().addAll(series);
        } catch (SQLException ex) {
            Logger.getLogger(Gestion_reclamation_adminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         try {
            // TODO
            afficher();
        } catch (SQLException ex) {
            Logger.getLogger(Gestion_reclamation_adminController.class.getName()).log(Level.SEVERE, null, ex);
        }
         combo.getItems().addAll("Non traitée","En cours","Traitée");
         table_id.setOnMouseClicked(new EventHandler<MouseEvent>()
                 {

             @Override
             public void handle(MouseEvent event) {
                c=(Complaints)table_id.getSelectionModel().getSelectedItem();
                 System.out.println(c);
                 
                    
             }
                     
                 }
         
         
         
         );
       //  Image playI=new Image("C://Users//belkis//Desktop//croissant.png");
        
   
    }    

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
        Complaints ccs=(Complaints)table_id.getSelectionModel().getSelectedItem();
        ServiceComplaint cs=new ServiceComplaint();
         if(ccs==null)
            JOptionPane.showMessageDialog(null,"choisir reclamation");
         else{cs.delete(ccs.getId_reclamation());
         afficher();
         JOptionPane.showMessageDialog(null,"reclamation supprimée");
         }
    }

    @FXML
    private void modifier(ActionEvent event) throws SQLException, Exception {
        ServiceComplaint cs=new ServiceComplaint();
        if(c==null)
            JOptionPane.showMessageDialog(null,"choisir reclamation");
        else{ c.setEtat(combo.getValue());
       Date d=new Date();
        c.setDate(d);
        cs.update(c,c.getId_reclamation());
        afficher();
        JOptionPane.showMessageDialog(null,"reclamation modifiée");
        c=null;
        //String mail=c.getU().get
         Mail.sendMail("belkisbaccar29@gmail.com");
    }}
    
    
    private void afficher() throws SQLException{
        ServiceComplaint ser= new ServiceComplaint();
        List<Complaints> d= ser.readAll_id();
        System.out.println("list "+d);
        ObservableList rob2 = FXCollections.observableArrayList(d);
       ObservableList dataList = FXCollections.observableArrayList(d);
        System.out.println(dataList);
        table_id.setItems(rob2);
//        List<Integer> listId=new ArrayList<>();
//        for(Complaints c:d){
//            listId.add(c.getA().getId_annonce());
//        }
       
        reclamation_id.setCellValueFactory(new PropertyValueFactory<>("id_reclamation"));
        etat_id.setCellValueFactory(new PropertyValueFactory<>("etat"));
         desc_id.setCellValueFactory(new PropertyValueFactory<>("description"));
         image_id.setCellValueFactory(new PropertyValueFactory<>("j"));
         note_id.setCellValueFactory(new PropertyValueFactory<>("note"));
       
              
                  id_obj.setCellValueFactory(new PropertyValueFactory<>("id_obj")); 
         
        
         id_user.setCellValueFactory(new PropertyValueFactory<>("u")); 
         date_id.setCellValueFactory(new PropertyValueFactory<>("date"));
            
        
        // SimpleStringProperty etat =c.getEtat();
         //datalist=ser.readAll_id();
        // Wrap the ObservableList in a FilteredList (initially display all data).
//        FilteredList<Complaints> filteredData = new FilteredList<>(datalist, b -> true);
//		
//		// 2. Set the filter Predicate whenever the filter changes.
//		chercher_id.textProperty().addListener((observable, oldValue, newValue) -> {
//			filteredData.setPredicate(a -> {
//				// If filter text is empty, display all persons.
//								
//				if (newValue == null || newValue.isEmpty()) {
//					return true;
//				}
//				
//				// Compare first name and last name of every person with filter text.
//				String lowerCaseFilter = newValue.toLowerCase();
//				
//				if (a.getEtat().toLowerCase().indexOf(lowerCaseFilter) != -1  ) {
//					return true; // Filter matches first name.
//				} //else if (a.getSujet().toLowerCase().contains(lowerCaseFilter)) {
////					return true; // Filter matches last name.
////				}
//				else if (String.valueOf(a.getNote()).contains(lowerCaseFilter))
//				     return true;
//				     else  
//				    	 return false; // Does not match.
//			});
//		});
//                
//		
//		// 3. Wrap the FilteredList in a SortedList. 
//		SortedList<Complaints> sortedData = new SortedList<>(filteredData);
//		
//		// 4. Bind the SortedList comparator to the TableView comparator.
//		// 	  Otherwise, sorting the TableView would have no effect.
//		sortedData.comparatorProperty().bind(table_id.comparatorProperty());
//		
//		// 5. Add sorted (and filtered) data to the table.
//		table_id.setItems(sortedData);
//                System.out.println(sortedData);
//             // System.out.println(dataList);
//              System.out.println(filteredData);
//        
    }

    @FXML
    private void chercher(ActionEvent event) throws SQLException {
        
        ServiceComplaint cs = new ServiceComplaint();
        ArrayList AL = (ArrayList) cs.readAll_id();
        ObservableList OReservation = FXCollections.observableArrayList(AL);
        FilteredList<Complaints> filteredData = new FilteredList<>(OReservation, p -> true);
        chercher_id.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(myObject -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(myObject.getEtat()).toLowerCase().contains(lowerCaseFilter) || String.valueOf(myObject.getU().getId_user()).toLowerCase().contains(lowerCaseFilter)   ||  String.valueOf(myObject.getA().getId_annonce()).toLowerCase().contains(lowerCaseFilter)   || String.valueOf(myObject.getNote()).toLowerCase().contains(lowerCaseFilter)   || String.valueOf(myObject.getDescription()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;

                }
                return false;
            });
        });
        SortedList<Complaints> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table_id.comparatorProperty());
        table_id.setItems(sortedData);
    }

    @FXML
    private void retour1(ActionEvent event) {
    }

    @FXML
    private void retour(ActionEvent event) {
    }
}
