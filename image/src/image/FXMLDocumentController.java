/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package image;
import image.panierController;
import connection.Datasource;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    @FXML
    private TableColumn<Produitentity, String> quantite;
 panierController k=new panierController();
 
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
                    quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
                    quantite.setCellFactory(TextFieldTableCell.forTableColumn());
                      /* quantite.setOnEditCommit((CellEditEvent<Produitentity, String> t) -> {
                         ((Produitentity) t.getTableView().getItems().get(
                                 t.getTablePosition().getRow())
                                 ).setQuantite(t.getNewValue());
       });*/
                    columnImage.setCellValueFactory(new PropertyValueFactory<>("image"));
                    columnPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
                   
                    addButtonToTable();
                    addButtonToTable1();
                    table.setItems(data);
    }

    private void addButtonToTable() {
         TableColumn actionCol = new TableColumn("ajouter au pannier");
        actionCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

        Callback<TableColumn<Produitentity, Void>, TableCell<Produitentity, Void>> cellFactory = new Callback<TableColumn<Produitentity, Void>, TableCell<Produitentity, Void>>() {
            @Override
            public TableCell<Produitentity, Void> call(final TableColumn<Produitentity, Void> param) {
                final TableCell<Produitentity, Void> cell = new TableCell<Produitentity, Void>() {

                    private final Button btn = new Button("Acheter");
                    

                    {
                        btn.setOnAction((ActionEvent event) -> {
                         
                            try {
                                Produitentity prod = getTableView().getItems().get(getIndex());
                               // System.out.println(prod.getQuantite());
                               
                                k.insert(4,prod.getNomP());
                            } catch (SQLException ex) {
                                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            try {
                                calculer(4);
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


    @FXML
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
                               
                                k.réduire_quantité(4,prod.getNomP());
                            } catch (SQLException ex) {
                                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            try {
                                calculer(4);
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

    @FXML
    private void commander(ActionEvent event) throws SQLException {
        k.commande(4);
        k.pdf(4);
    }
                }