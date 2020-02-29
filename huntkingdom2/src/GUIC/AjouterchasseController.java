/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIC;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import chasse.Entity.Chasse;
import chasse.Entity.Animal;
import chasse.Entity.Type_animal;
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
import chasse.Service.ChasseService;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
//import com.lowagie.text.pdf.PdfPTable;
//import com.lowagie.text.pdf.PdfTable;

import chasse.Entity.Animal;
import chasse.Entity.Produitentity;
import connection.Datasource;
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
import chasse.Service.ProduitService;
import chasse.Entity.Produitentity;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javafx.geometry.Pos;
import javafx.print.PrinterJob;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;
import javafx.util.Duration;
import javax.swing.JFileChooser;
import org.controlsfx.control.Notifications;
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
    private JFXButton btn_valider;
    @FXML
    private BarChart<String, Double> barChart;
        

    @FXML
    private TableView<Chasse> TableChasse;

    @FXML
    private TableColumn<?, ?> animal_tab;

    @FXML
    private TableColumn<?, ?> type_animal_tab;
        @FXML
    private JFXButton deletechass;

    @FXML
    private TableColumn<?, ?> region_tab;

    @FXML
    private TableColumn<?, ?> date_db_tab;

    @FXML
    private TableColumn<?, ?> date_fn_tab;
    @FXML
    private TableColumn<Chasse, String> prod;
    @FXML
    private JFXDatePicker date_db;

    @FXML
    private JFXDatePicker date_fn;
        @FXML
    private JFXButton updatee;
            @FXML
    private JFXButton tri;

    @FXML
    private JFXTextField region;
    @FXML
    private JFXComboBox<Type_animal> typechoice;
        @FXML
    private JFXComboBox<Produitentity> produitchoice;
    @FXML
    private AnchorPane anchorr;
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
    private Chasse c=null ;
 // Image img = new Image("/img/P_yes_green2.png");
        public AjouterchasseController(){
        cnx = Datasource.getInstance().getCnx();
        try {
            st = cnx.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(AjouterchasseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //@FXML
    //void loadchart(ActionEvent event) {
         //XYChart.Series<String,Double> serie = new XYChart.Series<>();
                 
              
        try {
            String  region ;

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
                    //barChart.getData().add(serie);
                } else {
                    count = 0;
                }

                occ.put(region, count + 1);  //increments value 

                cnt++;
               
            
            }
            for (Map.Entry m : occ.entrySet()) {
                                     XYChart.Series<String,Double> serie = new XYChart.Series<>();

                double percentage = (int) m.getValue() / cnt * 100;
                System.out.println(m.getKey() + " : " + percentage + "%");
                
            serie.getData().add(new XYChart.Data<>(m.getKey().toString(),percentage));
           
          barChart.getData().add(serie);}
         //barChart.getData().stream().forEach(System.out::println);
            
            
//    

        } catch (SQLException ex) {
            Logger.getLogger(ChasseService.class.getName()).log(Level.SEVERE, null, ex);
        }
           
    
//       deletechass.setDisable(true);
//       updatee.setDisable(true);
       try {
           //ProduitService pss = new ProduitService();
           AfficherComboProd();
           // TODO
           //animalchoice.set(Animal.values());
//        for(Animal env : Animal.values())
       } catch (SQLException ex) {
           Logger.getLogger(AjouterchasseController.class.getName()).log(Level.SEVERE, null, ex);
       }
 
//            String url1 = env.getUrl();
//List<String> produitValues = Arrays.asList(Produitentity.);

List<Animal> animalValues = Arrays.asList(Animal.values());
animalchoice.setItems(FXCollections.observableArrayList(animalValues));
List<Type_animal> typeValues = Arrays.asList(Type_animal.values());
typechoice.setItems(FXCollections.observableArrayList(typeValues));
        displayAllChasse();

    
TableChasse.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
    @Override
    public void handle(MouseEvent event) {
                 Produitentity p = new Produitentity();
                 ProduitService ps = new ProduitService();

       c=(Chasse)TableChasse.getSelectionModel().getSelectedItem();
       
           animalchoice.setValue(c.getAnimal());
           typechoice.setValue((c.getType()));
       region.setText(c.getRegion());
           date_db.setValue(c.getDate_debut().toLocalDate());
       date_fn.setValue(c.getDate_fin().toLocalDate());
      
            
           
       
       

          
       
//            deletechass.setDisable(false);
//       updatee.setDisable(false);
       

       
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
    private void AfficherComboProd() throws SQLException {
        ProduitService ps = new ProduitService();
        List<Produitentity> listprod = ps.displayProduit();
        produitchoice.getItems().addAll(listprod);

    }
    
    
        @FXML
    private void valider(ActionEvent event) throws SQLException {
        ChasseService cs = new ChasseService();
        Chasse ccc = new Chasse();
        ccc.setAnimal(animalchoice.getValue());
         Produitentity p = new Produitentity();
        //p.setNomP(produitchoice.getValue().toString());
            System.out.println("99");
            ProduitService per = new ProduitService();
      //      System.out.println("reteer"+produitchoice.getValue().getId());
          p=  per.getProduit_id(produitchoice.getValue().getId());
            
         // p=  per.getProduit_id(produitchoice.getValue().getId());
           
        ccc.setProduit(p);
           
                
            
              
        System.out.println("produiiiiiiiiit");
       //cs.insertPST(new Chasse(animalchoice.getValue(), region.getText(), date_db.getValue(), date_fn.getValue(), Type_animal.Autres, p));

//animal_tab.setCellValueFactory(new );
        ccc.setRegion(region.getText());
        ccc.setId_user(13);
        LocalDate date_debut =  date_db.getValue();
        LocalDate date_fin =  date_fn.getValue();
ccc.setDate_debut(java.sql.Date.valueOf(date_debut));
ccc.setDate_fin(java.sql.Date.valueOf(date_fin));
ccc.setType((Type_animal) typechoice.getValue());

//            if (carnivore.isSelected()) {
//                c.setType(Type_animal.carnivore);
//            } else if (oiseaux.isSelected()) {
//                c.setType(Type_animal.oiseaux);}
//                else if(herbivore.isSelected()){
//                c.setType(Type_animal.herbivore);}
            
            if (ccc.getDate_debut().equals(ccc.getDate_fin())) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Vérifier les Dates");
            a.show();
            System.out.println("invalid");
        } else if (date_fn.getValue().isBefore(date_db.getValue())) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Vérifier les Dates");
            a.show();}
            else if(region.getText().isEmpty()){
                     Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Vérifier La region");
            a.show();
                    }
            else if(animalchoice.getSelectionModel().isEmpty()){
                     Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Vérifier animal");
            a.show();
            }
            else if (typechoice.getSelectionModel().isEmpty()){
                     Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Veuiller saisir la race de l'animal");
            a.show();
            }
            else if (produitchoice.getSelectionModel().isEmpty()){
             //Image img = new Image("/img/klwa.jpg");
             Notifications notificationBuilder = Notifications.create()
                .title("Avertissement")
                .text("produit not selected")
                     
                       .hideAfter(Duration.seconds(3))
                     .position(Pos.BOTTOM_RIGHT);
            
        notificationBuilder.showWarning();
                      //  .graphic(new ImageView(img))
            }
        else {
                 
             
            cs.insertPST(ccc);
//          displayAllChasse();
//          barChart.getData().removeAll();
//           try {
//            String  region ;
//
//            st = cnx.createStatement();
//
//            String req = "SELECT   region FROM  chasse ";
//
//            ResultSet rs = st.executeQuery(req);
//            Map<String, Integer> occ = new HashMap<String, Integer>();
//            double cnt = 0;
//            while (rs.next()) {
//                
//                region = rs.getString(1);
//                
//                 
//                 
//                //animal = rs.getString(1);
//                int count = 0;
//
//                // int count = occ.containsKey(region) ? occ.get(region):0;  // if key contains region get it else don't 
//                if (occ.containsKey(region)) {
//                    count = occ.get(region);
//                    //barChart.getData().add(serie);
//                } else {
//                    count = 0;
//                }
//
//                occ.put(region, count + 1);  //increments value 
//
//                cnt++;
//               
//            
//            }
//            for (Map.Entry m : occ.entrySet()) {
//                                     XYChart.Series<String,Double> serie = new XYChart.Series<>();
//
//                double percentage = (int) m.getValue() / cnt * 100;
//                System.out.println(m.getKey() + " : " + percentage + "%");
//                
//            serie.getData().add(new XYChart.Data<>(m.getKey().toString(),percentage));
//           
//          barChart.getData().add(serie);}
//         //barChart.getData().stream().forEach(System.out::println);
//            
//            
////    
//
//        } catch (SQLException ex) {
//            Logger.getLogger(ChasseService.class.getName()).log(Level.SEVERE, null, ex);
//        }
           
                    try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/GUIC/Gestionchasse.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
                 FXMLLoader fxml=new FXMLLoader(getClass().getResource("/GUIC/Gestionchasse.fxml"));
        
        Parent root=fxml.load();
        anchorr.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AjouterchasseController.class.getName()).log(Level.SEVERE, null, ex);
        }
                    Notifications notificationBuilder = Notifications.create()
                .title("Succée")
                .text("Ajout terminer")
                       .hideAfter(Duration.seconds(3))
//                        .graphic(new ImageView(img))
                
                       
                // .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT);
            
        notificationBuilder.showInformation();
        }
            //TableChasse.refresh();

            //TableChasse.getColumns().get(0).setVisible(false);
//TableChasse.getColumns().get(0).setVisible(true);

    }
    public void displayAllChasse()  {
         ProduitService ps = new ProduitService();
        ChasseService cs = new ChasseService();
        Produitentity p = new Produitentity();
        ArrayList listcs = (ArrayList) cs.displayAll();
        System.out.println(listcs);
        ObservableList rob2 = FXCollections.observableArrayList(listcs);
        TableChasse.setItems(rob2);
        animal_tab.setCellValueFactory(new PropertyValueFactory<>("animal"));
        type_animal_tab.setCellValueFactory(new PropertyValueFactory<>("type"));
        region_tab.setCellValueFactory(new PropertyValueFactory<>("region"));
        date_db_tab.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        date_fn_tab.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        prod.setCellValueFactory(new PropertyValueFactory<>("produit"));
System.out.println("sup");
        

    
    }
    
   @FXML
    public void displayAllChasseById()  {
         ProduitService ps = new ProduitService();
        ChasseService cs = new ChasseService();
        Produitentity p = new Produitentity();
        ArrayList listcs = (ArrayList) cs.triByDate();
        System.out.println(listcs);
        ObservableList rob2 = FXCollections.observableArrayList(listcs);
        TableChasse.setItems(rob2);
        animal_tab.setCellValueFactory(new PropertyValueFactory<>("animal"));
        type_animal_tab.setCellValueFactory(new PropertyValueFactory<>("type"));
        region_tab.setCellValueFactory(new PropertyValueFactory<>("region"));
        date_db_tab.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        date_fn_tab.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        prod.setCellValueFactory(new PropertyValueFactory<>("produit"));
System.out.println("sup");
        

    
    }
        @FXML    
        public void deleteChasseAction(ActionEvent event) throws SQLException {
        ChasseService cs = new ChasseService();
         Chasse cc = (Chasse)TableChasse.getSelectionModel().getSelectedItem();
        System.out.println(cc);
        if(cc== null){
            System.out.println("Choisir chasse");
            Alert alert = new Alert(AlertType.WARNING);
alert.setTitle("Warning ");
alert.setHeaderText("Veillez d'abort sélécté une chasse dans le tableau");


alert.showAndWait();
                   
        }else{
            ChasseService.getInstance().delete(cc.getId());
            System.out.println("SUCCESS");
          //  Image img = new Image("/img/P_yes_green2.png");
                    
             Notifications notificationBuilder = Notifications.create()
                     
                .title("Succée de suppression")
                .text(":)")
//                     .graphic(new ImageView(img))
                
                       
                // .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT);
            notificationBuilder.show();
    }
         try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/GUIC/Gestionchasse.fxml"));
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

                if (String.valueOf(myObject.getAnimal()).toLowerCase().contains(lowerCaseFilter) || String.valueOf(myObject.getProduit()).toLowerCase().contains(lowerCaseFilter)|| String.valueOf(myObject.getRegion()).toLowerCase().contains(lowerCaseFilter)) {
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
    private void update(ActionEvent event) throws SQLException {
        ChasseService cs= new ChasseService();
        if (c==null) {
//            Notifications notificationBuilder = Notifications.create()
//                .title("Chasse non trouvée")
//                .text("plz select one")
//                
//                       
//                // .hideAfter(Duration.seconds(5))
//                .position(Pos.BOTTOM_RIGHT);
//            notificationBuilder.showError();
//            

Alert alert = new Alert(AlertType.ERROR);
alert.setTitle("Erreur");
alert.setHeaderText("Vérifier que les champs sont tous remplis");
//alert.setContentText("Ooops, there was an error!");

alert.showAndWait();
        }
        if (c.getDate_debut().equals(c.getDate_fin())) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Vérifier les Dates");
            a.show();
            System.out.println("invalid");
        } else if (date_fn.getValue().isBefore(date_db.getValue())) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Vérifier les Dates");
            a.show();}
            else if(region.getText().isEmpty()){
                     Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Vérifier La region");
            a.show();
                    }
            else if(animalchoice.getSelectionModel().isEmpty()){
                     Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Vérifier animal");
            a.show();
            }
            else if (typechoice.getSelectionModel().isEmpty()){
                     Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Veuiller saisir la race de l'animal");
            a.show();
            }
            else if (produitchoice.getSelectionModel().isEmpty()){
             //Image img = new Image("/img/klwa.jpg");
             Notifications notificationBuilder = Notifications.create()
                .title("Avertissement")
                .text("produit not selected")
                     
                       .hideAfter(Duration.seconds(3))
                     .position(Pos.BOTTOM_RIGHT);
            
        notificationBuilder.showWarning();
                      //  .graphic(new ImageView(img))
            }
        else {
            LocalDate dd=date_db.getValue();
            LocalDate df=date_fn.getValue();
            Date d1=Date.valueOf(dd);
            Date d2 =Date.valueOf(df);
            ProduitService per = new ProduitService();
            Produitentity p = new Produitentity();
          p=  per.getProduit_id(produitchoice.getValue().getId());
          
            
         
           
        c.setProduit(p);
        //c.getId(), region.getText(),animalchoice.getValue(),typechoice.getValue(), d1,d2   
        cs.update(c.getId(),  region.getText(), animalchoice.getValue(), typechoice.getValue(), d2, d2, p);
        displayAllChasse();
         try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/GUIC/Gestionchasse.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterchasseController.class.getName()).log(Level.SEVERE, null, ex);
        }
                        //Image img = new Image("/img/P_yes_green.png");
//                    Image img = new Image("/img/P_yes_green2.png");
             Notifications notificationBuilder = Notifications.create()
                     
                .title("Succée de Mise à jour")
                .text(":)")
                   
                //.graphic(new ImageView(img))
                       
                // .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT);
            notificationBuilder.showInformation();
    }
        
//            deletechass.setDisable(true);
//       updatee.setDisable(true);
            
        }
 
    
//     HashMap<String, Integer> occ = new HashMap<String, Integer>();
//        @FXML
//    void loadchart(ActionEvent event) {
//         XYChart.Series<String,Double> serie = new XYChart.Series<>();
//                 
//              String  region ;
//        try {
//            
//            st = cnx.createStatement();
//
//            String req = "SELECT   region FROM  chasse ";
//
//            ResultSet rs = st.executeQuery(req);
//            Map<String, Integer> occ = new HashMap<String, Integer>();
//            double cnt = 0;
//            while (rs.next()) {
//                
//                region = rs.getString(1);
//                
//                 
//                 
//                //animal = rs.getString(1);
//                int count = 0;
//
//                // int count = occ.containsKey(region) ? occ.get(region):0;  // if key contains region get it else don't 
//                if (occ.containsKey(region)) {
//                    count = occ.get(region);
//                    barChart.getData().add(serie);
//                } else {
//                    count = 0;
//                }
//
//                occ.put(region, count + 1);  //increments value 
//
//                cnt++;
//                
//            
//            }
//            for (Map.Entry m : occ.entrySet()) {
//                double percentage = (int) m.getValue() / cnt * 100;
//                System.out.println(m.getKey() + " : " + percentage + "%");
//            serie.getData().add(new XYChart.Data<>(m.getKey().toString(),percentage));}
//           
////           barChart.getData().setAll(serie);
//         barChart.getData().stream().forEach(System.out::println);
//            
//            
////    
//
//        } catch (SQLException ex) {
//            Logger.getLogger(ChasseService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//           
//    }
    public void imprimer(ActionEvent event) throws DocumentException {
//  ProduitService ps = new ProduitService();
//        ChasseService cs = new ChasseService();
//        Produitentity p = new Produitentity();
//        ArrayList listcs = (ArrayList) cs.displayAll();
//        
//        ObservableList rob2 = FXCollections.observableArrayList(listcs);
//        TableChasse.setItems(rob2);
//        animal_tab.setCellValueFactory(new PropertyValueFactory<>("animal"));
//        type_animal_tab.setCellValueFactory(new PropertyValueFactory<>("type"));
//        region_tab.setCellValueFactory(new PropertyValueFactory<>("region"));
//        date_db_tab.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
//        date_fn_tab.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
//        prod.setCellValueFactory(new PropertyValueFactory<>("produit"));
// System.out.println("To Printer!");
//         PrinterJob job = PrinterJob.createPrinterJob();
        PdfPTable table = new PdfPTable(8);
        table.addCell(TableChasse.toString());
//         table.addCell(animal_tab.getText());
//         
//           if(job != null){
//    Window primaryStage = null;
//           job.showPrintDialog(primaryStage); 
//            
//    Node root = this.barChart;
//    Node root2 = this.TableChasse;
//           job.printPage(root);
//           job.printPage(root2);
//           job.endJob();
     String path ="";
     JFileChooser j =new JFileChooser();
     j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
     //j.showSaveDialog(j);
        //path = j.getSelectedFile().getPath();
Document doc = new Document();
       try {
           PdfWriter.getInstance(doc, new FileOutputStream("abc123.pdf"));
       } catch (FileNotFoundException ex) {
           Logger.getLogger(AjouterchasseController.class.getName()).log(Level.SEVERE, null, ex);
       }
           doc.open();
                   table.addCell("Animal");
                   for (int i = 0; i < 30; i++) {
            
        
        table.addCell(animal_tab.getTableView().getItems().toString());
                   }
                   doc.add(table);
                   doc.close();
// System.out.println("To Printer!");
//         PrinterJob job = PrinterJob.createPrinterJob();
//           if(job != null){
//    Window primaryStage = null;
//           job.showPrintDialog(primaryStage); 
            
//    Node root = table;
//           job.printPage(root);
//           job.endJob();
//            
       

  }

//Document doc = new Document();
//       try {
//           PdfWriter.getInstance(doc, new FileOutputStream(path+"abc123.pdf"));
//           doc.open();
//           PdfPTable tbl = new PdfPTable(6);
//           tbl.addCell("Animal");
//           tbl.addCell("Race");
//                      tbl.addCell("Region");
//           tbl.addCell("Début");
//           tbl.addCell("Fin");
//           
//           tbl.addCell("Produit");
//           for (int i = 0; i < 30; i++) {
//               String a = TableChasse.getAccessibleText();
//           }
           
        
                   
//                   } catch (FileNotFoundException ex) {
//           Logger.getLogger(AjouterchasseController.class.getName()).log(Level.SEVERE, null, ex);
//       }
   



        
    }
    




      
    

