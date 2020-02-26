/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIP;

import publicite.Entity.Promotion;
import publicite.Entity.Publicite;
import publicite.Entity.PubliciteAimer;
import publicite.Service.ServicePromotion;
import publicite.Service.ServicePublicite;
import publicite.Service.ServicePubliciteAimer;
import javax.mail.*;  
import javax.mail.internet.*;  
import javax.activation.*;  
import connection.Datasource;
 import javafx.application.Application;
 import javafx.geometry.Rectangle2D;
 import javafx.scene.Group;
 import javafx.scene.Scene; 
 import javafx.scene.image.Image;
 import javafx.scene.image.ImageView;
 import javafx.scene.layout.HBox;
 import javafx.scene.paint.Color;
 import javafx.stage.Stage;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import user.Service.UserSession;

/**
 * FXML Controller class
 *
 * @author walid
 */
public class PubliciteController implements Initializable {
 private Connection con;
    @FXML
    private Button close;
    public PubliciteController() {
       con = Datasource.getInstance().getCnx();
    }
    
    
    List<String> type;
    String imgG="";
    int verif=0;
    Publicite cc=null;
    Promotion promo=null;
      private final double IMG_WIDTH = 1264;
    private final double IMG_HEIGHT = 350;
 
    private int NUM_OF_IMGS ;
    private final int SLIDE_FREQ = 4; // in secs
         
    private Statement ste;
    private PreparedStatement pre;


    @FXML
    private TextField nomP;
    @FXML
    private Button image;
    @FXML
    private DatePicker date_d;
    @FXML
    private DatePicker date_f;
    @FXML
    private TextField nom_P;
    @FXML
    private TextField prix_P;
    @FXML
    private Button ajout;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    private ImageView imageviw;
    @FXML
    private ImageView imageviw1;
    @FXML
    private TableView<Publicite> tab_pub;
    @FXML
    private TableColumn<Publicite,String> id;
    @FXML
    private TableColumn<Publicite,String> nom;
    @FXML
    private TableColumn<Publicite,String> img_P;
    @FXML
    private TableColumn<Publicite,String> dateD;
    @FXML
    private TableColumn<Publicite,String> dateF;
    @FXML
    private TableColumn<Publicite,String> nom_PP;
    @FXML
    private TableColumn<Publicite,String> prix;
ObservableList <Publicite> data =FXCollections.observableArrayList();
    @FXML
    private ComboBox<String> id_produit;
    @FXML
    private ComboBox<String> taux;
    @FXML
    private DatePicker date_D_P;
    @FXML
    private DatePicker date_F_P;
    @FXML
    private Button ajout2;
    @FXML
    private Button update2;
    @FXML
    private Button delete2;
    @FXML
    private TableView<Promotion> tab_promo;
    @FXML
    private TableColumn<Promotion,String> id_promo;
    @FXML
    private TableColumn<Promotion,String> id_ppp;
    @FXML
    private TableColumn<Promotion,String> Taux;
    @FXML
    private TableColumn<Promotion,String> date_debut_P;
    @FXML
    private TableColumn<Promotion,String> date_fin_P;
    @FXML
    private Label produit_label;
    @FXML
    private TextField rechercher;
    @FXML
    private Label error_prix;
    @FXML
    private Label error_name;
    @FXML
    private Label error_image;
    @FXML
    private Label error_date_d;
    @FXML
    private Label error_date_f;
    @FXML
    private Label error_nom_p;
    @FXML
    private HBox imgslider;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    UserSession n = UserSession.getInstance();
    
        System.out.println(n);
        try {
         start1();
     } catch (Exception ex) {
         Logger.getLogger(PubliciteController.class.getName()).log(Level.SEVERE, null, ex);
     }
        
        try {
            
            type=new ArrayList<>();
            type.add("*.jpg");
            type.add("*.png");
            
            displayAll_pub();
            displayAll_promo();
            
            prix_P.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    prix_P.setText(oldValue);
                   
                }
              
                  
            }
            

              
        });
            prix_P.textProperty().addListener(new ChangeListener<String>()
            {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                   if(newValue.isEmpty())
                       error_prix.setText("remplir champ prix");
                   else
                error_prix.setText("");
                }
                
                
            });
            prix_P.setOnMouseClicked(new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent event) {
                    if(prix_P.getText().length()==0)
                     error_prix.setText("remplir champ prix");    
                    
                }
                
            });
              nomP.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\sa-zA-Z*")) {
            nomP.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
        }
    });
             nomP.textProperty().addListener(new ChangeListener<String>()
            {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                   if(newValue.isEmpty())
                       error_name.setText("remplir champ nom");
                   else if(newValue.length()>25)
                       error_name.setText("Max champ nom 25");
                   else
                error_name.setText("");
                }
                
                
            });
            nomP.setOnMouseClicked(new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent event) {
                    if(nomP.getText().length()==0)
                     error_name.setText("remplir champ nom");    
                    
                }
                
            });
                nom_P.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\sa-zA-Z*")) {
            nom_P.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
        }
    });
                
             nom_P.textProperty().addListener(new ChangeListener<String>()
            {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                   if(newValue.isEmpty())
                       error_nom_p.setText("remplir champ nom");
                   else if(newValue.length()>25)
                       error_nom_p.setText("Max champ nom 25");
                   else
                error_nom_p.setText("");
                }
                
                
            });
            nom_P.setOnMouseClicked(new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent event) {
                    if(nom_P.getText().length()==0)
                     error_nom_p.setText("remplir champ nom");    
                    
                }
                
            });
            date_d.setOnMouseClicked(new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent event) {
                    if(date_d.getValue()==null)
                     error_date_d.setText("remplir champ date debut");    
                   
                    
                    
                }
                
            });
            date_d.valueProperty().addListener((ov, oldValue, newValue) -> {
                 if(newValue==null)
                       error_date_d.setText("remplir champ date debut");
                 
                   else
                error_date_d.setText("");
                
            
        });
            date_f.setOnMouseClicked(new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent event) {
                   
                   
                    if(date_f.getValue()==null)
                    {error_date_f.setText("remplir champ date fin");    
                    }
                    if(date_d.getValue()==null)
                      error_date_d.setText("remplir champ date debut");
                    
              
                    
                     
                  
                         
                }
                
            });
            date_f.valueProperty().addListener((ov, oldValue, newValue) -> {
                 if(newValue==null)
                 {
                     error_date_f.setText("remplir champ date fin");
                }
                 
                
                  
                 else
                 error_date_f.setText("");
                
            
        });
            delete2.setDisable(true);
            update2.setDisable(true);
            ServicePromotion pr=new ServicePromotion();
            List ids=pr.readAll_ID_produit();
            ids.forEach(System.out::println);
            if(!ids.isEmpty())
            id_produit.getItems().addAll(ids);
            else
            {  id_produit.setDisable(true);
               ajout2.setDisable(true);
              
             
            }
            taux.getItems().addAll("10","20","30","40","50","60","70","80","90");
            
            tab_pub.setOnMouseClicked(new EventHandler<MouseEvent>()
            
            {
                @Override
                public void handle(MouseEvent event) {
                    cc = (Publicite)tab_pub.getSelectionModel().getSelectedItem();
                   error_prix.setText(""); 
        error_name.setText("");
        error_nom_p.setText("");
        error_image.setText("");
        error_date_d.setText("");
          error_date_d.setText("");
                                nomP.setText(cc.getNom());
            
            //System.out.println(imageviw1);
            LocalDate d1=cc.getDate_debut().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
             LocalDate d2=cc.getDate_fin().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            date_d.setValue(d1);
            date_f.setValue(d2);
            
            imageviw1.setImage(new Image(cc.getImage()));
            nom_P.setText(cc.getNom_proprietaire());
            double cprix=cc.getPrix();
            String prix_PPP=String.valueOf(cprix);
            prix_P.setText(prix_PPP);
            
                }
            
            
            }
            
            
            
            );
            tab_promo.setOnMouseClicked(new EventHandler<MouseEvent>()
            
            {
                @Override
                public void handle(MouseEvent event) {
                    promo=(Promotion)tab_promo.getSelectionModel().getSelectedItem();
                    
                    System.out.println(promo);
                     LocalDate d1=promo.getDate_debut().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
             LocalDate d2=promo.getDate_fin().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
             date_D_P.setValue(d1);
             date_F_P.setValue(d2);
            String s=String.valueOf(promo.getTaux());
                taux.setValue(s);
                 
               
            id_produit.setDisable(true);
             delete2.setDisable(false);
            update2.setDisable(false);
            ajout2.setDisable(true);
            
                
                
                }
                
               
                
            }
            
            
            
            );
            
           
            
        } catch (SQLException ex) {
            Logger.getLogger(PubliciteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        

      
    }    

    @FXML
    private void import_image(ActionEvent event) {
        FileChooser f=new FileChooser();
        f.getExtensionFilters().add(new FileChooser.ExtensionFilter("jpeg,png files", type));
        File fc=f.showOpenDialog(null);
        
        if(fc != null)
        {   
            System.out.println(fc.getName());
            imgG=fc.getAbsoluteFile().toURI().toString(); 
            Image i = new Image(imgG);
            imageviw1.setImage(i);
            error_image.setText("");
            verif=1;
        }
       
        else 
            error_image.setText("choisir image");
       
            
        
    }

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
        
        if(nomP.getText().isEmpty() ||(imgG.isEmpty()&&cc.getImage().isEmpty()) || date_d.getValue()==null ||date_f.getValue()==null||nom_P.getText().isEmpty()||prix_P.getText().isEmpty() )
        
        {
           
             
         JOptionPane.showMessageDialog(null, "verifer les champs");   
        }
        else if(date_d.getValue().equals(date_f.getValue()) ||date_d.getValue().isAfter(date_f.getValue()))
        {
         JOptionPane.showMessageDialog(null, "Date debut doit etre < date fin");
        }
        else
        { ServicePublicite ps =new ServicePublicite();
        LocalDate dd=date_d.getValue();
        LocalDate df=date_f.getValue();
        java.util.Date d1=java.sql.Date.valueOf(dd);
        java.util.Date d2=java.sql.Date.valueOf(df);
        double s = Double.parseDouble(prix_P.getText());
          if(imgG.length()==0)
          {ps.ajouter(new Publicite(nomP.getText(),cc.getImage(), d1,d2,nom_P.getText(),s));}
          ps.ajouter(new Publicite(nomP.getText(),imgG, d1,d2,nom_P.getText(),s));
        displayAll_pub();
        EmailService mail= new  EmailService("smtp.mailtrap.io", 25, "walid171798@gmail.com", "azerty&psn1234");
        mail.sendMail();
        JOptionPane.showMessageDialog(null, "Ajout effectué");
        nomP.clear();
        imageviw1.setImage(null);
        date_d.setValue(null);
        date_f.setValue(null);
        nom_P.clear();
        prix_P.clear();
        error_prix.setText(""); 
        error_name.setText("");
        error_nom_p.setText("");
        error_image.setText("");
        error_date_d.setText("");
        error_date_f.setText("");
        } 
    }

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
         ServicePublicite cs = new ServicePublicite();
        
       
        if(cc== null){
            JOptionPane.showMessageDialog(null, "choisir pub");
                   
        }else{

                    LocalDate dd=date_d.getValue();
        LocalDate df=date_f.getValue();
        java.util.Date d1=java.sql.Date.valueOf(dd);
        java.util.Date d2=java.sql.Date.valueOf(df);
        double s = Double.parseDouble(prix_P.getText());
            //System.out.println("import"+imgG);
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
        cc=null;
        
    }
    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
        
     
        ServicePublicite cs = new ServicePublicite();
        ServicePubliciteAimer ccs =new ServicePubliciteAimer();
         
        
        if(cc== null){
            JOptionPane.showMessageDialog(null, "choisir pub");
                   
        }else{
            ccs.delete1(cc.getId_publicite());
            cs.delete(cc.getId_publicite());
                               nomP.clear();
        imageviw1.setImage(null);
        date_d.setValue(null);
        date_f.setValue(null);
        nom_P.clear();
        prix_P.clear();
           displayAll_pub();
           JOptionPane.showMessageDialog(null, "pub supprimer");
           cc=null;
            
    }
    }
   
  public void displayAll_pub() throws SQLException  {

        ServicePublicite cs = new ServicePublicite();
        List listcs =  cs.readAll_withImage();
        
        //listcs.forEach(System.out::println);
        
        ObservableList rob2 = FXCollections.observableArrayList(listcs);
        
        tab_pub.setItems(rob2);
       
        
        id.setCellValueFactory(new PropertyValueFactory<>("id_publicite"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        img_P.setCellValueFactory(new PropertyValueFactory<>("photo"));
        dateD.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        dateF.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        nom_PP.setCellValueFactory(new PropertyValueFactory<>("nom_proprietaire"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
  }

    @FXML
    private void ajouter2(ActionEvent event) throws SQLException {
            if(date_D_P.getValue()==null || date_F_P.getValue()==null || taux.getValue()==null) 
            {
                JOptionPane.showMessageDialog(null, "remplir les champs");
            }
            else if(date_D_P.getValue().equals(date_F_P.getValue()) ||date_D_P.getValue().isAfter(date_F_P.getValue()))
        {
         JOptionPane.showMessageDialog(null, "Date debut doit etre < date fin");
        }
            else{
                
            
        ServicePromotion pss=new ServicePromotion();
        LocalDate dd=date_D_P.getValue();
        LocalDate df=date_F_P.getValue();
        java.util.Date d1=java.sql.Date.valueOf(dd);
        java.util.Date d2=java.sql.Date.valueOf(df);
        Double t=Double.parseDouble(taux.getValue());
        int id=Integer.parseInt(id_produit.getValue());
        pss.ajouter(new Promotion(id,t, d1, d2));
        displayAll_promo();
  id_produit.getItems().clear();      
List ids=pss.readAll_ID_produit();
            if(!ids.isEmpty())
            id_produit.getItems().addAll(ids);
            else
            {  id_produit.setDisable(true);
              ajout2.setDisable(true);
            
            }
    }
}
    @FXML
    private void modifier2(ActionEvent event) throws SQLException {
        ServicePromotion cs = new ServicePromotion();
        
       
        if(promo== null){
            JOptionPane.showMessageDialog(null, "choisir promo");
                   
        }else{
                if(date_D_P.getValue().equals(date_F_P.getValue()) ||date_D_P.getValue().isAfter(date_F_P.getValue()))
        {
         JOptionPane.showMessageDialog(null, "Date debut doit etre < date fin");
        }
                else
                { LocalDate dd=date_D_P.getValue();
        LocalDate df=date_F_P.getValue();
        java.util.Date d1=java.sql.Date.valueOf(dd);
        java.util.Date d2=java.sql.Date.valueOf(df);
        double s = Double.parseDouble(taux.getValue());
            //System.out.println("import"+imgG);
           
          cs.update(new Promotion(promo.getId_produit(), s, d1, d2),promo.getId_promotion());
       displayAll_promo();
           
           JOptionPane.showMessageDialog(null, "promo modifier");
           id_produit.getItems().clear();
            List ids=cs.readAll_ID_produit();
            if(ids.size()!=0)
            {id_produit.getItems().addAll(ids);
            id_produit.setDisable(false);
            ajout2.setDisable(false);
            }
            
            else
            {  id_produit.setDisable(true);
             
              ajout2.setDisable(true);
            }
      
                
                date_D_P.setValue(null);
                date_F_P.setValue(null);
                tab_promo.getSelectionModel().clearSelection();
               
                taux.getItems().clear();
                taux.getItems().addAll("10","20","30","40","50","60","70","80","90");
                  delete2.setDisable(true);
            update2.setDisable(true);
        
    }
                }
    }

    @FXML
    private void supprimer2(ActionEvent event) throws SQLException {
        if(promo==null)
        {
          JOptionPane.showMessageDialog(null, "choisir promo");  
        }
        else
        { ServicePromotion cs = new ServicePromotion();
        cs.D_promotion(promo);
            displayAll_promo();
           
           JOptionPane.showMessageDialog(null, "promo supprimer");
            id_produit.getItems().clear();
      List ids=cs.readAll_ID_produit();
            if(!ids.isEmpty())
            {id_produit.getItems().addAll(ids);
            id_produit.setDisable(false);
            ajout2.setDisable(false);
            }
            else
            {  id_produit.setDisable(true);
            
            ajout2.setDisable(true);
            }
                
                date_D_P.setValue(null);
                date_F_P.setValue(null);
                tab_promo.getSelectionModel().clearSelection();
                
                taux.getItems().clear();
                taux.getItems().addAll("10","20","30","40","50","60","70","80","90"); 
                  delete2.setDisable(true);
            update2.setDisable(true);
            
        }
        
    }
      
    
    public void displayAll_promo() throws SQLException  {

        ServicePromotion cs = new ServicePromotion();
        List listcs =  cs.readAll();
        
        //listcs.forEach(System.out::println);
        
        ObservableList rob2 = FXCollections.observableArrayList(listcs);
        
        tab_promo.setItems(rob2);
       
        
        id_promo.setCellValueFactory(new PropertyValueFactory<>("id_promotion"));
        id_ppp.setCellValueFactory(new PropertyValueFactory<>("id_produit"));
        Taux.setCellValueFactory(new PropertyValueFactory<>("taux"));
        date_debut_P.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        date_fin_P.setCellValueFactory(new PropertyValueFactory<>("date_fin"));

  }
    @FXML
      void SearchChasse(ActionEvent event) throws SQLException {
        ServicePublicite cs = new ServicePublicite();
        ArrayList listcs = (ArrayList) cs.readAll_withImage();
        ObservableList OReservation = FXCollections.observableArrayList(listcs);
        FilteredList<Publicite> filteredData = new FilteredList<>(OReservation, p -> true);
        rechercher.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(myObject -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(myObject.getNom()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;

                }
                return false;
            });
        });
        SortedList<Publicite> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tab_pub.comparatorProperty());
        tab_pub.setItems(sortedData);
    }
      public void start1() throws Exception {
          
   
        
       ServicePubliciteAimer pa=new ServicePubliciteAimer();
        String req="select * from publicite  ";
        List<VBox> list = new ArrayList<>();
        
        try {
            ste=con.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                java.util.Date d1 = new java.util.Date(rs.getDate(4).getTime());
               java.util.Date d2 = new java.util.Date(rs.getDate(5).getTime());
                Publicite p= new Publicite(rs.getInt(1),rs.getString(2),rs.getString(3),d1, rs.getString(6), d2,rs.getDouble(7));
              ImageView v=new ImageView(new Image(rs.getString(3)));
                 
        v.setFitHeight(300);
        v.setFitWidth(1278);
              Button bt1=new Button("like");
                System.out.println(pa.chercher_ajout(new PubliciteAimer(p.getId_publicite(),3, d2)));
                      
                      
          if (pa.chercher_ajout(new PubliciteAimer(p.getId_publicite(),3, d2))) {
                   bt1.setDisable(true);
              }

               VBox v1=new VBox();
               v1.setAlignment(Pos.CENTER);
               v1.setSpacing(10);
               v1.getChildren().addAll(v,bt1);
               list.add(v1);
               NUM_OF_IMGS=list.size();
               bt1.setOnAction(new EventHandler<ActionEvent>() {
    @Override public void handle(ActionEvent e) {
        
        System.out.println(p.getId_publicite());
        try {
            if(!pa.chercher_ajout(new PubliciteAimer(p.getId_publicite(),3, d2)))
            {
                pa.ajouter(new PubliciteAimer(p.getId_publicite(),3,new Date()));
                bt1.setDisable(true);
            }
            
           else System.out.println("PUB DEJA AIMER");
        } catch (SQLException ex) {
            Logger.getLogger(PubliciteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
});
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicePublicite.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        
       
        imgslider.getChildren().addAll(list);
        
     
        startAnimation(imgslider);
       
      }
       private void startAnimation(final HBox hbox) {
        //error occured on (ActionEvent t) line
        //slide action
        EventHandler<ActionEvent> slideAction = (ActionEvent t) -> {
            TranslateTransition trans = new TranslateTransition(Duration.seconds(1.5), hbox);
            trans.setByX(-IMG_WIDTH);
            trans.setInterpolator(Interpolator.EASE_BOTH);
            trans.play();
        };
        //eventHandler
        EventHandler<ActionEvent> resetAction = (ActionEvent t) -> {
            TranslateTransition trans = new TranslateTransition(Duration.seconds(1), hbox);
            trans.setByX((NUM_OF_IMGS - 1) * IMG_WIDTH);
            trans.setInterpolator(Interpolator.EASE_BOTH);
            trans.play();
        };
 
        List<KeyFrame> keyFrames = new ArrayList<>();
        for (int i = 1; i <= NUM_OF_IMGS; i++) {
            if (i == NUM_OF_IMGS) {
                keyFrames.add(new KeyFrame(Duration.seconds(i * SLIDE_FREQ), resetAction));
            } else {
                keyFrames.add(new KeyFrame(Duration.seconds(i * SLIDE_FREQ), slideAction));
            }
        }
//add timeLine
        Timeline anim = new Timeline(keyFrames.toArray(new KeyFrame[NUM_OF_IMGS]));
 
        anim.setCycleCount(Timeline.INDEFINITE);
        anim.playFromStart();
    }

    @FXML
    private void close(ActionEvent event) {
        imgslider.setVisible(false);
        close.setVisible(false);
       
    }
}
