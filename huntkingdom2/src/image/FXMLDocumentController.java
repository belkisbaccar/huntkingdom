/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package image;
import GUIR.Reclamer_produitController;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import image.panierController;
import connection.Datasource;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect.Type;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javafx.scene.control.TableColumn.CellEditEvent ;
import javafx.scene.control.TablePosition;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import reclamation.Service.Produit_Sessions;
import user.Service.UserSession;


/**
 *
 * @author azizm
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private TableView<Produitentity> table;
    @FXML
    private TableColumn<Produitentity,String> columnNom;
    @FXML
    private TableColumn<Produitentity, ImageView> columnImage;
     ObservableList <Produitentity> data =FXCollections.observableArrayList();
    private Connection cnx;
    private Statement st;
    private PreparedStatement pst;
    private ResultSet s;
    @FXML
    private Label nbr_produit;
    @FXML
    private TableColumn<Produitentity, Integer> columnPrix;
    
 panierController k=new panierController();
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
 
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        vbox.getStyleClass().add("vbox");
        vbox.setSpacing(5);
              drawer.setSidePane(vbox);
               scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
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
          table.setEditable(true);
        
           
            cnx = Datasource.getInstance().getCnx();
            ResultSet rss=cnx.createStatement().executeQuery("select * from product  ");
            while(rss.next())
            {
                 ImageView v = new ImageView();
                  float t=rss.getFloat("prix_promo");
                v.setImage(new Image(rss.getString("image")));
                 v.setFitWidth(100);
                v.setFitHeight(100);
                if(t!=0)
                {
              Produitentity p = new Produitentity (rss.getString("nom")+" (en promo)",rss.getFloat("prix"),rss.getInt("ID"),null);
              
              p.setImage(v);
              data.add(p);
              p.setPrix(t);
                
                }
                else
                {
                     Produitentity p = new Produitentity (rss.getString("nom"),rss.getFloat("prix"),rss.getInt("ID"),null);
              
              p.setImage(v);
              data.add(p);
                }


            }
                   
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

   
                    columnNom.setCellValueFactory(new PropertyValueFactory<>("nomP"));
                                        /* quantite.setOnEditCommit((CellEditEvent<Produitentity, String> t) -> {
                         ((Produitentity) t.getTableView().getItems().get(
                                 t.getTablePosition().getRow())
                                 ).setQuantite(t.getNewValue());
       });*/
                    columnImage.setCellValueFactory(new PropertyValueFactory<>("image"));
                    columnPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
                   
        try {
            addButtonToTable();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            addButtonToTable3();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
                    addButtonToTable1();
                    table.setItems(data);
    }

    private void addButtonToTable() throws SQLException{
         TableColumn actionCol = new TableColumn("ajouter au pannier");
        actionCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

        Callback<TableColumn<Produitentity, Void>, TableCell<Produitentity, Void>> cellFactory = new Callback<TableColumn<Produitentity, Void>, TableCell<Produitentity, Void>>() {
            @Override
            public TableCell<Produitentity, Void> call(final TableColumn<Produitentity, Void> param) {
                final TableCell<Produitentity, Void> cell = new TableCell<Produitentity, Void>() {

                    private final Button btn = new Button("Acheter");
                    

                    {
                        btn.setOnAction((ActionEvent event) -> {
                         
                            Produitentity prod = getTableView().getItems().get(getIndex());
                            try {
                                // System.out.println(prod.getQuantite());
                                int i = get();
                                 k.insert(i,prod.getNomP());
                            calculer(i);
                            } catch (SQLException ex) {
                                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
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
                        }
                    }
                };
                return cell;
            }
        };

        actionCol.setCellFactory(cellFactory);

        table.getColumns().add(actionCol);

    }
public void changenomPCellEvent(CellEditEvent edittedCell)
{
Produitentity  prodSelected = table.getSelectionModel().getSelectedItem();
prodSelected.setNomP(edittedCell.getNewValue().toString());
}
public int get() throws SQLException
{int i2=0;
      UserSession n = UserSession.getInstance();
                               String s1 = n.getUserName();
                               Statement stmt1 = cnx.createStatement();
                              String SQL1 = "SELECT * FROM user  WHERE username ='" +s1+"'";
                               ResultSet rs1 = stmt1.executeQuery(SQL1);
                               while(rs1.next())
                                {
                                    i2=rs1.getInt(1);
                                           
                                }
        return i2;
                              
    
}

    private void getvalue(CellEditEvent<?, ?> event) {
         Object newValue = event.getNewValue();
        
         TablePosition<?,?> position = event.getTablePosition();
    int row = position.getRow();
    
    }
     public void  calculer(int id_user) throws SQLException
    { 
       cnx = Datasource.getInstance().getCnx();
          Statement stt = cnx.createStatement();
        String SQL = "SELECT SUM(quantite) FROM panier  WHERE id ='" +id_user+"'";
          ResultSet rs = stt.executeQuery(SQL);
            while(rs.next()){
                
               nbr_produit.setText((String.valueOf(rs.getInt(1))));
       
            }
    }
        
    
    
      private void addButtonToTable1() {
         TableColumn actionCol1 = new TableColumn("Réduire");
        actionCol1.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

        Callback<TableColumn<Produitentity, Void>, TableCell<Produitentity, Void>> cellFactory = new Callback<TableColumn<Produitentity, Void>, TableCell<Produitentity, Void>>() {
            @Override
            public TableCell<Produitentity, Void> call(final TableColumn<Produitentity, Void> param) {
                final TableCell<Produitentity, Void> cell = new TableCell<Produitentity, Void>() {

                    private final Button btn1 = new Button("Réduire la quantité");
                    

                    {
                        btn1.setOnAction((ActionEvent event) -> {
                         
                            try {
                                Produitentity prod = getTableView().getItems().get(getIndex());
                               // System.out.println(prod.getQuantite());
                               int i = get();
                                k.réduire_quantité(i,prod.getNomP());
                            } catch (SQLException ex) {
                                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            try {
                                int i = get();
                                calculer(i);
                            } catch (SQLException ex) {
                                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                           
                            
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn1);
                        }
                    }
                };
                return cell;
            }
        };
         actionCol1.setCellFactory(cellFactory);

        table.getColumns().add(actionCol1);
      }
          private void addButtonToTable3() throws SQLException {
        TableColumn colBtn = new TableColumn("Action");
        colBtn.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
        Callback<TableColumn<Produitentity, Void>, TableCell<Produitentity, Void>> cellFactory = new Callback<TableColumn<Produitentity, Void>, TableCell<Produitentity, Void>>() {
            @Override
            public TableCell<Produitentity, Void> call(final TableColumn<Produitentity, Void> param) {
                final TableCell<Produitentity, Void> cell = new TableCell<Produitentity, Void>() {

                    private final Button btn = new Button("reclamer");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Produitentity data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data);
                            try {
//                                Stage stage1 = (Stage) btn.getScene().getWindow();
//        stage1.close();
                                
                                Produit_Sessions.getInstace(data.getId());  
              
                FXMLLoader fxml=new FXMLLoader(getClass().getResource("/GUIR/reclamer_produit.fxml"));
        
        Parent root=fxml.load();
        scroll.getScene().setRoot(root);
                  
                                
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

        table.getColumns().add(colBtn);
        
 
    }

    @FXML
    private void commander(ActionEvent event) throws SQLException {
        int i = get();
        k.commande(i);
        k.pdf(i);
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

                }