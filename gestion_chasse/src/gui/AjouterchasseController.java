/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import entity.Chasse;
import entity.Animal;
import entity.Type_animal;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
//import static javafx.application.ConditionalFeature.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import service.ChasseService;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import entity.Animal;
import huntkingdom.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
/**
 * FXML Controller class
 *
 * @author AHMED
 */
public class AjouterchasseController implements Initializable {

    /**
     * Initializes the controller class.
     */
   @FXML
    private Button btn_valider;
    @FXML
    private BarChart<String, Double> barChart;
        @FXML
    private Button btn_load;

    @FXML
    private TableView<Chasse> TableChasse;

    @FXML
    private TableColumn<?, ?> animal_tab;

    @FXML
    private TableColumn<?, ?> type_animal_tab;
        @FXML
    private Button deletechass;

    @FXML
    private TableColumn<?, ?> region_tab;

    @FXML
    private TableColumn<?, ?> date_db_tab;

    @FXML
    private TableColumn<?, ?> date_fn_tab;

    @FXML
    private JFXDatePicker date_db;

    @FXML
    private JFXDatePicker date_fn;
        @FXML
    private Button updatee;

    @FXML
    private JFXTextField region;
    @FXML
    private JFXComboBox<Type_animal> typechoice;
    

    @FXML
    private TextField search;
    
        @FXML
    private JFXComboBox<Animal> animalchoice;

//    @FXML
//    void valider(ActionEvent event) {
//
//    }
        //ObservableList<Animal> listanimal = FXCollections.observableArrayList();
        
    
    Connection connection;
    private Connection cnx;
    private Statement st;
    private PreparedStatement pst;
    Chasse c = null;
  
        public AjouterchasseController(){
        cnx = DataSource.getInstance().getCnx();
        try {
            st = cnx.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(AjouterchasseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       deletechass.setDisable(true);
       updatee.setDisable(true);
        
        // TODO
        //animalchoice.setmo(Animal.values());
//        for(Animal env : Animal.values())
 
//            String url1 = env.getUrl();
List<Animal> animalValues = Arrays.asList(Animal.values());
animalchoice.setItems(FXCollections.observableArrayList(animalValues));
List<Type_animal> typeValues = Arrays.asList(Type_animal.values());
typechoice.setItems(FXCollections.observableArrayList(typeValues));
        displayAllChasse();

    
TableChasse.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
    @Override
    public void handle(MouseEvent event) {
       c=(Chasse)TableChasse.getSelectionModel().getSelectedItem();
       animalchoice.setValue(c.getAnimal());
       typechoice.setValue((c.getType()));
       region.setText(c.getRegion());
       //LocalDate d1 = c.getDate_debut().to

              date_db.setValue(c.getDate_debut().toLocalDate());
       date_fn.setValue(c.getDate_fin().toLocalDate());
            deletechass.setDisable(false);
       updatee.setDisable(false);
       
    }
            
        }


);

       
    
//ComboBox<Animal> cbxAnimal = new ComboBox<>();
//cbxAnimal.getItems().setAll(Animal.values());
//List<String> list = new ArrayList();
//list.addAll(Animal.values());
//ArrayList<Animal> all = new ArrayList<Animal>(EnumSet.allOf(Animal.class));
//cbxStatus.getItems().setAll(Status.values());  
//ObservableList<Animal> ob = FXCollections.observableArrayList();
 //ob.addAll(list);

    }
        @FXML
    private void valider(ActionEvent event) {
        ChasseService cs = new ChasseService();
        Chasse c = new Chasse();
        c.setAnimal(animalchoice.getValue());
        //animal_tab.setCellValueFactory(new );
        c.setRegion(region.getText());
        c.setId_user(13);
        LocalDate date_debut =  date_db.getValue();
        LocalDate date_fin =  date_fn.getValue();
c.setDate_debut(java.sql.Date.valueOf(date_debut));
c.setDate_fin(java.sql.Date.valueOf(date_fin));
c.setType((Type_animal) typechoice.getValue());

//            if (carnivore.isSelected()) {
//                c.setType(Type_animal.carnivore);
//            } else if (oiseaux.isSelected()) {
//                c.setType(Type_animal.oiseaux);}
//                else if(herbivore.isSelected()){
//                c.setType(Type_animal.herbivore);}
            
            if (c.getDate_debut().equals(c.getDate_fin())) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("saisi à nouveau vos données");
            a.show();
            System.out.println("invalid");
        } else if (date_fn.getValue().isBefore(date_db.getValue())) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("saisi à nouveau vos données");
            a.show();
        } else {
            
            
            cs.insertPST(c);
                    try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/gui/Gestionchasse.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterchasseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
            //TableChasse.refresh();

            //TableChasse.getColumns().get(0).setVisible(false);
//TableChasse.getColumns().get(0).setVisible(true);

    }
    public void displayAllChasse()  {

        ChasseService cs = new ChasseService();
        ArrayList listcs = (ArrayList) cs.triByDate();
        
        ObservableList rob2 = FXCollections.observableArrayList(listcs);
        TableChasse.setItems(rob2);
        animal_tab.setCellValueFactory(new PropertyValueFactory<>("animal"));
        type_animal_tab.setCellValueFactory(new PropertyValueFactory<>("type"));
        region_tab.setCellValueFactory(new PropertyValueFactory<>("region"));
        date_db_tab.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        date_fn_tab.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        

    
    }
        @FXML    
        public void deleteChasseAction(ActionEvent event) throws SQLException {
        ChasseService cs = new ChasseService();
         Chasse cc = (Chasse)TableChasse.getSelectionModel().getSelectedItem();
        System.out.println(cc);
        if(cc== null){
            System.out.println("Choisir chasse");
                   
        }else{
            ChasseService.getInstance().delete(cc.getAnimal());
            System.out.println("SUCCESS");
    }
         try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/gui/Gestionchasse.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterchasseController.class.getName()).log(Level.SEVERE, null, ex);
        }}


     @FXML
    void SearchChasse(ActionEvent event) {
        ChasseService cs = new ChasseService();
        ArrayList AL = (ArrayList) cs.displayAll();
        ObservableList OReservation = FXCollections.observableArrayList(AL);
        FilteredList<Chasse> filteredData = new FilteredList<>(OReservation, p -> true);
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(myObject -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(myObject.getAnimal()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;

                }
                return false;
            });
        });
        SortedList<Chasse> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(TableChasse.comparatorProperty());
        TableChasse.setItems(sortedData);
    }

    @FXML
    private void updateaction(MouseEvent event) {
    }

    @FXML
    private void update(ActionEvent event) {
        ChasseService cs= new ChasseService();
        if (c==null) {
            JOptionPane.showMessageDialog(null, "chasse misssing");
            
        }
        else {
            LocalDate dd=date_db.getValue();
            LocalDate df=date_fn.getValue();
            Date d1=Date.valueOf(dd);
            Date d2 =Date.valueOf(df);
            cs.update(c.getId(), region.getText(),animalchoice.getValue(),typechoice.getValue(), d1,d2);
            displayAllChasse();
            deletechass.setDisable(true);
       updatee.setDisable(true);
            
        }
 
    }
     HashMap<String, Integer> occ = new HashMap<String, Integer>();
        @FXML
    void loadchart(ActionEvent event) {
         XYChart.Series<String,Double> serie = new XYChart.Series<>();
                 
              String region = "";
        try {
            
            st = cnx.createStatement();

            String req = "SELECT   region FROM  chasse ";

            ResultSet rs = st.executeQuery(req);
            Map<String, Integer> occ = new HashMap<String, Integer>();
            double cnt = 0;
            while (rs.next()) {
                
                region = rs.getString(1);
                
                 
                 
                //animal = rs.getString(1);
                int count = 0;

                // int count = occ.containsKey(region) ? occ.get(region):0;  // if key contains region get it else don't 
                if (occ.containsKey(region)) {
                    count = occ.get(region);
                } else {
                    count = 0;
                }

                occ.put(region, count + 1);  //increments value 

                cnt++;
            }

            for (Map.Entry m : occ.entrySet()) {
                double percentage = (int) m.getValue() / cnt * 100;
                System.out.println(m.getKey().toString() + " : " + percentage + "%");
            serie.getData().add(new XYChart.Data<>(m.getKey().toString(),percentage));}
           barChart.getData().add(serie);
            
            
//    

        } catch (SQLException ex) {
            Logger.getLogger(ChasseService.class.getName()).log(Level.SEVERE, null, ex);
        }
           
    }


}

      
    

