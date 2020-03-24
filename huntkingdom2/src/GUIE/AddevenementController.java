/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIE;

import evenement.Service.evenementService;
import evenement.Entity.evenement;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.F;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import static jdk.nashorn.internal.objects.NativeString.search;

/**
 * FXML Controller class
 *
 * @author hazem
 */
public class AddevenementController implements Initializable {
    @FXML
    private TextField Etitre;
    @FXML
    private DatePicker Edatedebut;
    @FXML
    private DatePicker Edatefin;
    @FXML
    private TextField nb;
    @FXML
    private Button Eajouter;
    @FXML
    private TableView<evenement> table_event;
    @FXML
    private TableColumn<evenement,String> id;
    @FXML
    private TableColumn<evenement,String> titre;
    @FXML
    private TableColumn<evenement,String> date_D;
    @FXML
    private TableColumn<evenement,String> date_F;
    @FXML
    private TableColumn<evenement,String> nb_P;
    @FXML
    private Button supprimer;
    @FXML
    private Button modifier;
    private evenement cc=null;
    @FXML
    private Button gererP;
    @FXML
    private AnchorPane pag;
    @FXML
    private Button trier;
    @FXML
    private Button imagee;
    @FXML
    private TableColumn<evenement,String> timage;
    @FXML
    private ImageView imageview;
    String img="";
    List<String> type;
    @FXML
    private TextField search;
    @FXML
    private Label error_name;
    @FXML
    private Label error_date_d;
    @FXML
    private Label error_date_f;
    @FXML
    private Label error_nb;
    @FXML
    private Label error_img;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficher();
        type =new ArrayList();
        type.add("*.jpg");
         type.add("*.png");
        table_event.setOnMouseClicked(new EventHandler<MouseEvent>()
                
        {
            @Override
            public void handle(MouseEvent event) {
                cc = (evenement)table_event.getSelectionModel().getSelectedItem();
                System.out.println(cc);
                Etitre.setText(cc.getTitre_event());
                imageview.setImage(new Image(cc.getImage()));
                
                
                LocalDate d1=cc.getDate_debut_event().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate d2=cc.getDate_fin_event().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                Edatedebut.setValue(d1);
                Edatefin.setValue(d2);
                
                
                int cprix=cc.getNb_place_event();
                String nb_PPP=String.valueOf(cprix);
                nb.setText(nb_PPP);
                
            }
            
            
        }
                
                
                
        );
       
       
       Etitre.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\sa-zA-Z*")) {
            Etitre.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
        }
    });
       nb.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    nb.setText(oldValue);
                   
                }
              
                  
            }
            

              
        });
       Etitre.textProperty().addListener(new ChangeListener<String>()
            {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                   if(newValue.isEmpty())
                       error_name.setText("remplir champ titre");
                   else if(newValue.length()>25)
                       error_name.setText("Max champ titre 25");
                   else
                error_name.setText("");
                }
                
                
            });
            Etitre.setOnMouseClicked(new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent event) {
                    if(Etitre.getText().length()==0)
                     error_name.setText("remplir champ titre");    
                    
                }
                
            });
                Etitre.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\sa-zA-Z*")) {
            Etitre.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
        }
    });
                
           
            
            Edatedebut.setOnMouseClicked(new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent event) {
                    if(Edatedebut.getValue()==null)
                     error_date_d.setText("remplir champ date debut");    
                   
                    
                    
                }
                
            });
            Edatefin.valueProperty().addListener((ov, oldValue, newValue) -> {
                 if(newValue==null)
                       error_date_d.setText("remplir champ date debut");
                 
                   else
                error_date_d.setText("");
                
            
        });
            Edatefin.setOnMouseClicked(new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent event) {
                   
                   
                    if(Edatefin.getValue()==null)
                    {error_date_f.setText("remplir champ date fin");    
                    }
                    if(Edatedebut.getValue()==null)
                      error_date_d.setText("remplir champ date debut");
                    
              
                    
                     
                  
                         
                }
                
            });
            Edatefin.valueProperty().addListener((ov, oldValue, newValue) -> {
                 if(newValue==null)
                 {
                     error_date_f.setText("remplir champ date fin");
                }
                 
                
                  
                 else
                 error_date_f.setText("");
                
            
        });
            nb.textProperty().addListener(new ChangeListener<String>()
            {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                   if(newValue.isEmpty())
                       error_nb.setText("remplir champ nombre place");
                   else if(newValue.length()>10)
                       error_nb.setText("Max champ nombre place  10 nombre");
                   else
                error_nb.setText("");
                }
                
                
            });
            nb.setOnMouseClicked(new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent event) {
                    if(nb.getText().length()==0)
                     error_nb.setText("remplir champ nombre de place");    
                    
                }
                
            });
            imagee.textProperty().addListener(new ChangeListener<String>()
            {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                   if(newValue.isEmpty())
                       error_img.setText("remplir champ image");
                   
                   else
                error_img.setText("");
                }
                
                
            });
            imagee.setOnMouseClicked(new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent event) {
                    if(imagee.getText().length()==0)
                     error_img.setText("remplir champ image");    
                    
                }
                
            });
            
          
    }    

    @FXML
    private void ajouter(ActionEvent event) {
       
        
        
        
        String titreE = Etitre.getText();
        
        LocalDate dd =Edatedebut.getValue();
        LocalDate df =Edatefin.getValue();
        Date date_debutE = java.sql.Date.valueOf(dd);
        Date date_finE = java.sql.Date.valueOf(df);
        int nb_place= Integer.parseInt(nb.getText());
        evenementService sp = new evenementService();
        evenement e = new evenement(titreE,img, date_debutE,date_finE,nb_place);
        if(Etitre.getText().isEmpty() ||(img.isEmpty()&&cc.getImage().isEmpty()) || dd==null || df==null || Etitre.getText().isEmpty() || nb.getText().isEmpty() )
        
        {
           
             
         JOptionPane.showMessageDialog(null, "verifer les champs");   
        }
        else if(Edatedebut.getValue().equals(Edatefin.getValue()) ||Edatedebut.getValue().isAfter(Edatefin.getValue()))
        {
         JOptionPane.showMessageDialog(null, "Date debut doit etre < date fin");
        }
        else{
        sp.insert(e);
         JOptionPane.showMessageDialog(null, "ajout avec succes");
         Etitre.clear();
         imageview.setImage(null);
                
        Edatedebut.setValue(null);
        Edatefin.setValue(null);
        nb.clear();
        afficher();
       /* FXMLLoader loader = new FXMLLoader
                                        (getClass()
                                                .getResource("AddEvenement.fxml"));
         try {
            Parent root = loader.load();
            AddEvenementController apc = loader.getController();
            apc.sett(Etitre);
            apc.setdd(Edatedebut);
            apc.setdf(Edatefin);
            
            Etitre.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }*/
    }}
     private void afficher()
   {evenementService sp = new evenementService();
   List events=sp.displayAll();
       ObservableList et=FXCollections.observableArrayList(events);
       table_event.setItems(et);
       
       id.setCellValueFactory(new PropertyValueFactory<>("id_event"));
       titre.setCellValueFactory(new PropertyValueFactory<>("titre_event"));
       timage.setCellValueFactory(new PropertyValueFactory<>("photo"));
       date_D.setCellValueFactory(new PropertyValueFactory<>("date_debut_event"));
       date_F.setCellValueFactory(new PropertyValueFactory<>("date_fin_event"));
       nb_P.setCellValueFactory(new PropertyValueFactory<>("nb_place_event"));
   
   }

    @FXML
    private void supprimer_event(ActionEvent event) {
        evenementService cs = new evenementService();
         evenement cc = (evenement)table_event.getSelectionModel().getSelectedItem();
        System.out.println(cc);
        if(cc== null){
            JOptionPane.showMessageDialog(null, "choisir event");
                   
        }else{
            cs.delete(cc.getId_event());
    
           afficher();
           
           JOptionPane.showMessageDialog(null, "event supprimer");
            Etitre.clear();
            imageview.setImage(null);
                
        Edatedebut.setValue(null);
        Edatefin.setValue(null);
        nb.clear();
        cc=null;
    }
    }

    @FXML
    private void modifer_event(ActionEvent event) {
        evenementService cs = new evenementService();
        
        System.out.println(cc);
        if(cc== null){
            JOptionPane.showMessageDialog(null, "choisir event");
                   
        }else{
 if(Edatedebut.getValue().equals(Edatefin.getValue()) ||Edatedebut.getValue().isAfter(Edatefin.getValue()))
        {
         JOptionPane.showMessageDialog(null, "Date debut doit etre < date fin");
        }
                else
                {
                    LocalDate dd=Edatedebut.getValue();
        LocalDate df=Edatefin.getValue();
        java.util.Date d1=java.sql.Date.valueOf(dd);
        java.util.Date d2=java.sql.Date.valueOf(df);
       int nb_place= Integer.parseInt(nb.getText());
       if(img.length()==0)
       cs.update(new evenement(Etitre.getText(),cc.getImage() , d1, d2, nb_place),cc.getId_event());
       else
           cs.update(new evenement(Etitre.getText(),img, d1, d2, nb_place),cc.getId_event());
           
       afficher();
        JOptionPane.showMessageDialog(null, "event modifier");
        Etitre.clear();
         imageview.setImage(null);
                
        Edatedebut.setValue(null);
        Edatefin.setValue(null);
        nb.clear();
        cc=null;
         /* ServicePublicite cs = new ServicePublicite();
        
        System.out.println(cc);
        if(cc== null){
            JOptionPane.showMessageDialog(null, "choisir pub");
                   
        }else{

                    LocalDate dd=date_d.getValue();
        LocalDate df=date_f.getValue();
        java.util.Date d1=java.sql.Date.valueOf(dd);
        java.util.Date d2=java.sql.Date.valueOf(df);
        double s = Double.parseDouble(prix_P.getText());
            System.out.println("import"+imgG);
           if(imgG.length()==0)
           { 
               cs.update(new Publicite(nomP.getText(),cc.getImage(), d1,d2,nom_P.getText(),s),cc.getId_publicite());
           }
           else
           {cs.update(new Publicite(nomP.getText(),imgG, d1,d2,nom_P.getText(),s),cc.getId_publicite());}
        displayAll_pub();
           
           JOptionPane.showMessageDialog(null, "pub modifier");
                   nomP.clear();
        imageviw1.setImage(null);
        date_d.setValue(null);
        date_f.setValue(null);
        nom_P.clear();
        prix_P.clear();
        
    }
        */
          
  
           
          
                }
        
    }
    }

    @FXML
    private void gerer(ActionEvent event) throws IOException {
       AnchorPane pane=FXMLLoader.load(getClass().getResource("AfficherParticipants.fxml"));
        pag.getChildren().setAll(pane);
    }

    @FXML
    private void trier(ActionEvent event) {
       evenementService sp = new evenementService();
       List events=sp.trieParnb();
       ObservableList et=FXCollections.observableArrayList(events);
       table_event.setItems(et);
         
       id.setCellValueFactory(new PropertyValueFactory<>("id_event"));
       titre.setCellValueFactory(new PropertyValueFactory<>("titre_event"));
       timage.setCellValueFactory(new PropertyValueFactory<>("image"));
       date_D.setCellValueFactory(new PropertyValueFactory<>("date_debut_event"));
       date_F.setCellValueFactory(new PropertyValueFactory<>("date_fin_event"));
       nb_P.setCellValueFactory(new PropertyValueFactory<>("nb_place_event"));
           
           
           
        
    
    }

    @FXML
    private void importer(ActionEvent event) {
       FileChooser f=new FileChooser();
        f.getExtensionFilters().add(new FileChooser.ExtensionFilter("jpeg,png",type));
        File fc=f.showOpenDialog(null);
        if(f!= null)
        {
            System.out.println(fc.getName());
            img=fc.getAbsoluteFile().toURI().toString();
            Image i = new Image(img);
            imageview.setImage(i);
        }
    }

    @FXML
    private void recherche(ActionEvent event) {
        evenementService cs = new evenementService();
        ArrayList AL = (ArrayList) cs.displayAll();
        ObservableList OReservation = FXCollections.observableArrayList(AL);
        FilteredList<evenement> filteredData = new FilteredList<>(OReservation, p -> true);
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(myObject -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(myObject.getTitre_event()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;

                }
                return false;
            });
        });
        SortedList<evenement> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table_event.comparatorProperty());
        table_event.setItems(sortedData);
    }
}
