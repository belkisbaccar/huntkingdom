/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIR;

import reclamation.Entity.Complaints;
import reclamation.Service.ServiceComplaint;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import connection.Datasource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import reclamation.Entity.user;
import user.Service.UserSession;

/**
 * FXML Controller class
 *
 * @author belkis
 */
public class Afficher_complaintController implements Initializable {

    @FXML
    private TableView<Complaints> table_id;
    @FXML
    private TableColumn<Complaints, String> etat_id;
    @FXML
    private TableColumn<Complaints, String> desc_id;
    @FXML
    private TableColumn<Complaints, String> image_id;
    @FXML
    private TableColumn<Complaints, String> note_id;
    @FXML
    private TableColumn<Complaints, String> sujet_id;
    @FXML
    private TableColumn<Complaints, String> date_id;
    @FXML
    private ScrollPane scroll;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private VBox vbox;
    @FXML
    private ImageView imgfront;
    @FXML
    private Button menu;
    @FXML
    private Button profile;
    @FXML
    private Button products;
    @FXML
    private Button events;
    @FXML
    private Button annonce;
    @FXML
    private Button complains;
    @FXML
    private Button hunting;
 int user_cc=0;
 private Connection con;

    public Afficher_complaintController() {
        con = Datasource.getInstance().getCnx();
    }
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            user_cc=get();
        } catch (SQLException ex) {
            Logger.getLogger(Afficher_complaintController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
              drawer.setSidePane(vbox);
               
        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
        transition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();

            if (drawer.isOpened()) {
                
                drawer.close();
            } else {
                drawer.open();
                
            }
        });
        
        
        
        try {
            // TODO

            afficher();
            

        } catch (SQLException ex) {
            Logger.getLogger(Afficher_complaintController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void afficher() throws SQLException {

        ServiceComplaint ser = new ServiceComplaint();
        user aa= new user(user_cc);
        List d = ser.readAll_id2(aa);
        ObservableList rob2 = FXCollections.observableArrayList(d);
        //System.out.println(rob2);
        addButtonToTable();
        table_id.setItems(rob2);

        etat_id.setCellValueFactory(new PropertyValueFactory<>("etat"));
        desc_id.setCellValueFactory(new PropertyValueFactory<>("description"));
        image_id.setCellValueFactory(new PropertyValueFactory<>("j"));
        note_id.setCellValueFactory(new PropertyValueFactory<>("note"));
        sujet_id.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        date_id.setCellValueFactory(new PropertyValueFactory<>("date"));

    }
    
      public void update() throws SQLException {

        ServiceComplaint ser = new ServiceComplaint();
        List d = ser.readAll_id();
        ObservableList rob2 = FXCollections.observableArrayList(d);
        //System.out.println(rob2);
        
        table_id.setItems(rob2);

       
        note_id.setCellValueFactory(new PropertyValueFactory<>("note"));
        

    }

    private void addButtonToTable() throws SQLException {
        TableColumn colBtn = new TableColumn("Action");
        colBtn.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
        Callback<TableColumn<Complaints, Void>, TableCell<Complaints, Void>> cellFactory = new Callback<TableColumn<Complaints, Void>, TableCell<Complaints, Void>>() {
            @Override
            public TableCell<Complaints, Void> call(final TableColumn<Complaints, Void> param) {
                final TableCell<Complaints, Void> cell = new TableCell<Complaints, Void>() {

                    private final Button btn = new Button("noter");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Complaints data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data);
                            try {
//                                Stage stage1 = (Stage) btn.getScene().getWindow();
//        stage1.close();
                                FXMLLoader loader = new FXMLLoader();
                                loader.setLocation(getClass().getResource("rating.fxml"));
                                Parent tableview = loader.load();
                                Scene tableviewscene = new Scene(tableview);
                                RatingController controller = loader.getController();
                                controller.initData(data);
                                System.out.println("controller");
                                Stage stage = new Stage();

                                stage.setTitle("évaluation");
                                stage.setScene(tableviewscene);
                                stage.show();
                                Stage stage1 = (Stage) btn.getScene().getWindow();
        stage1.close();
                                
                            } catch (Exception exc) {
                            }

                        });
                       
                    }

                    @Override

                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
//                            try {
//                                update();
//                            } catch (SQLException ex) {
//                                Logger.getLogger(Afficher_complaintController.class.getName()).log(Level.SEVERE, null, ex);
//                            }
                        }
                    }
                };
                return cell;
            } 

        };

        colBtn.setCellFactory(cellFactory);

        table_id.getColumns().add(colBtn);
        
 
    }

   @FXML
    private void home(ActionEvent event) throws IOException {
                       FXMLLoader fxml=new FXMLLoader(getClass().getResource("/MenuGUI/menu2.fxml"));
        
        Parent root=fxml.load();
        menu.getScene().setRoot(root);
        
    }

    @FXML
    private void page_profile(ActionEvent event) throws IOException {
                   FXMLLoader fxml=new FXMLLoader(getClass().getResource("/GUIProfile/Welcome.fxml"));
        
        Parent root=fxml.load();
        menu.getScene().setRoot(root);
    }

    @FXML
    private void page_products(ActionEvent event) throws IOException {
                   FXMLLoader fxml=new FXMLLoader(getClass().getResource("/image/FXMLDocument.fxml"));
        
        Parent root=fxml.load();
        menu.getScene().setRoot(root);
    }

    @FXML
    private void page_events(ActionEvent event) throws IOException {
                   FXMLLoader fxml=new FXMLLoader(getClass().getResource("/MenuGUI/events.fxml"));
        
        Parent root=fxml.load();
        menu.getScene().setRoot(root);
    }

    @FXML
    private void page_annonce(ActionEvent event) throws IOException {
                   FXMLLoader fxml=new FXMLLoader(getClass().getResource("/MenuGUI/annonce.fxml"));
        
        Parent root=fxml.load();
        menu.getScene().setRoot(root);
    }

    @FXML
    private void page_complains(ActionEvent event) throws IOException {
                       FXMLLoader fxml=new FXMLLoader(getClass().getResource("/GUIR/afficher_complaint.fxml"));
        
        Parent root=fxml.load();
        menu.getScene().setRoot(root);
    }

    @FXML
    private void page_hunting(ActionEvent event) throws IOException {
                   FXMLLoader fxml=new FXMLLoader(getClass().getResource("/MenuGUI/hunting.fxml"));
        
        Parent root=fxml.load();
        menu.getScene().setRoot(root);
    }
               public int get() throws SQLException
{
    int i2=0;
      UserSession n = UserSession.getInstance();
                               String s1 = n.getUserName();
                               Statement stmt1 = con.createStatement();
                              String SQL1 = "SELECT * FROM user  WHERE username ='" +s1+"'";
                               ResultSet rs1 = stmt1.executeQuery(SQL1);
                               while(rs1.next())
                                {
                                    i2=rs1.getInt(1);
                                           
                                }
        return i2;
                              
    
}
}
