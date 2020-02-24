/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commerceController;


import commerce.entities.Produitentity;
import commerce.entities.CommandeEntity;
import connection.Datasource;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;


/**
 * FXML Controller class
 *
 * @author azizm
 */
public class ProduitController implements Initializable {

    @FXML
    private TextField nomP;
    @FXML
    private TextField quantite;
    @FXML
    private TextField prix;
    @FXML
    private Button ajout;
    @FXML
    private Button update;
    @FXML
    private Button delete;
     private Connection cnx;
    private Statement st;
    private PreparedStatement pst;
    private ResultSet s;
     Alert a = new Alert(AlertType.NONE);
    @FXML
    private TableView<Produitentity> tableproduit;
    @FXML
    private TableColumn<Produitentity, String> ColumnProduit;
    @FXML
    private TableColumn<Produitentity, Integer> columnQuantite;
    @FXML
    private TableColumn<Produitentity, Integer> columnPrix;
    ObservableList <Produitentity> data =FXCollections.observableArrayList();
    ObservableList <Produitentity> data2 ;
    Produitentity cc=null;

    TableView<Produitentity> prod;
    @FXML
    private BarChart<String, Integer> barchart;
    @FXML
    private AnchorPane parentcommerce;
    @FXML
    private ImageView image;
    private Image image2;
    @FXML
    private Button save;
    @FXML
    private TableColumn<CommandeEntity, Integer> columnClient;
    @FXML
    private TableColumn<CommandeEntity, Integer> columnQuantite2;
    @FXML
    private TableColumn<CommandeEntity, Integer> columnPrixtotl;
    @FXML
    private TableColumn<CommandeEntity, String> columnEtat;
    @FXML
    private TableView<CommandeEntity> tablecommande;
    @FXML
    private Label progress;
    @FXML
    private Label progress2;
    @FXML
    private Label image3;
    String link;
    boolean uploaded;
    List<String> type;
    String imgG="";
    ObservableList <CommandeEntity> data3 =FXCollections.observableArrayList();
   
   
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        type=new ArrayList<>();
            type.add("*.jpg");
            type.add("*.png");
          
        afficher();
        barchar();
         afficher_commande();
        try {
            calculer();
            
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    
    //calculer quantite et les nombres du produits
private void calculer() throws SQLException
{
    Statement stmt = cnx.createStatement();
        String SQL2 = "SELECT SUM(quantite),COUNT(*) FROM product";
          ResultSet rs2 = stmt.executeQuery(SQL2);
         
          
          
          if(rs2.next())
          {
          
             int x = rs2.getInt(1);
              int x2 = rs2.getInt(2);
             
              
            
          progress.setText((String.valueOf(x)));
           progress2.setText((String.valueOf(x2)));
          
     }
          
}


//ajouter le produit dans la base de donnée
    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
        Scanner sc = new Scanner(System.in);
         cnx = Datasource.getInstance().getCnx();
         
        Statement stmt = cnx.createStatement();
          String SQL = "SELECT * FROM product WHERE nom ='" +nomP.getText()+"'";
           ResultSet rs = stmt.executeQuery(SQL);
           if(!rs.next()){
                if ((!quantite.getText().matches("[A-Za-z]") && !quantite.getText().matches("[\\\\!\"#$%&()*+,./:;<=>?@\\[\\]^_{|}~]+")) 
                    && (!prix.getText().matches("[A-Za-z]") && !prix.getText().matches("[\\\\!\"#$%&()*+,./:;<=>?@\\[\\]^_{|}~]+"))
                        && (image.getImage()!=null)){
                   
                    
           String req="insert into product (nom,quantite,prix,image,prix_promo)values('"+nomP.getText()+"','"+quantite.getText()+"','"+prix.getText()+"','"+imgG+"','"+0+"')";
        try {
            st=cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
         Alert a1 = new Alert(AlertType.NONE,  
                              "Produit ajouté",ButtonType.APPLY); 
  
                // show the dialog 
                a1.show(); 
                afficher2();
               barchar2();
    }
                else
                {
                    a.setAlertType(AlertType.WARNING); 
  
                // set content text 
                a.setContentText("verifié vos parametre "); 
  
                // show the dialog 
                a.show(); 
                }
             
           }
                else {
                a.setAlertType(AlertType.WARNING); 
  
                // set content text 
                a.setContentText("Produit déjà existe "); 
  
                // show the dialog 
                a.show(); 
                
           }
                
          calculer();
          generation_code(nomP.getText(),prix.getText());
                
           }
     
    
    //selectionner la ligne pour afficher prix quantite et nom 
    String selectionner()
    {
          data2=tableproduit.getSelectionModel().getSelectedItems();
            String id;
           id=data2.get(0).getNomP();
            //System.out.println(id);
            prix.setText(String.valueOf(data2.get(0).getPrix()));
            quantite.setText(String.valueOf(data2.get(0).getQuantite()));
            nomP.setText(data2.get(0).getNomP());
            return id;
    }
    
    // pour retourner ID du ligne à partir du tableau 
    String getid()
    {
        data2=tableproduit.getSelectionModel().getSelectedItems();
            String id;
          return  id=data2.get(0).getNomP();
    }

    @FXML
    
    //pour mettre nom;quantite et prix dans input textfield  
    private void modifier(ActionEvent event) throws SQLException {
           selectionner();
   
    }

    @FXML
    
    //supprimer selon la ligne qui la selectiooner 
    private void supprimer(ActionEvent event) throws SQLException {
        data2=tableproduit.getSelectionModel().getSelectedItems();
            String id;
            id=data2.get(0).getNomP();
            System.out.println(data2);
             
        try {
            
           String query = "delete from product where nom = ?";
      PreparedStatement preparedStmt = cnx.prepareStatement(query);
      preparedStmt.setString(1, id);

      // execute the preparedstatement
      preparedStmt.execute();
      
     
        } catch (SQLException ex) {
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
         afficher2();
          calculer(); 
    }

    public void afficher(){
        try {
            cnx = Datasource.getInstance().getCnx();
            ResultSet rss=cnx.createStatement().executeQuery("select * from product ORDER BY ID");
            while(rss.next())
            {
                data.add(new Produitentity( rss.getString("nom"),rss.getInt("quantite"),rss.getInt("prix"),rss.getString("image")));


            }
                   
        } catch (SQLException ex) {
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
                    ColumnProduit.setCellValueFactory(new PropertyValueFactory<>("nomP"));
                    columnQuantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
                    columnPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
                    tableproduit.setItems(data);
    }
     public void afficher2(){
         data.removeAll(data);
        try {
            cnx = Datasource.getInstance().getCnx();
            ResultSet rss=cnx.createStatement().executeQuery("select * from product ");
            while(rss.next())
            {
                data.add(new Produitentity( rss.getString("nom"),rss.getInt("quantite"),rss.getInt("prix"),rss.getString("image")));


            }
                   
        } catch (SQLException ex) {
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
                    ColumnProduit.setCellValueFactory(new PropertyValueFactory<>("nomP"));
                    columnQuantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
                    columnPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
                    tableproduit.setItems(data);
    }
    public void barchar()
    {
        String query="select nom,quantite FROM product ORDER BY   quantite";
        XYChart.Series<String,Integer> series = new XYChart.Series<>();
try 
{
cnx = Datasource.getInstance().getCnx();
 ResultSet rss=cnx.createStatement().executeQuery(query);
while (rss.next())
{
series.getData().add(new XYChart.Data<>(rss.getString(1), rss.getInt(2)));
}

barchart.getData().add(series);
}
catch (SQLException ex) {
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void barchar2()
    {
        barchart.getData().clear();
 
        String query="select nom,quantite FROM product ORDER BY   quantite";
        XYChart.Series<String,Integer> series1 = new XYChart.Series<>();
try 
{
   

barchart.getData().add( series1 );
cnx = Datasource.getInstance().getCnx();
 ResultSet rss=cnx.createStatement().executeQuery(query);
while (rss.next())
{
series1.getData().add(new XYChart.Data<>(rss.getString(1), rss.getInt(2)));
}

barchart.getData().add(series1);
}
catch (SQLException ex) {
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void retourner(MouseEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/dashboard/FXML.fxml"));
         parentcommerce.getChildren().setAll(pane);
    }//return to the dashboard

    @FXML
     private void ajouterimage(ActionEvent event) throws IOException {
       
         FileChooser f=new FileChooser();
        f.getExtensionFilters().add(new FileChooser.ExtensionFilter("jpeg,png files", type));
        File fc=f.showOpenDialog(null);
        if(f!= null)
        {
            System.out.println(fc.getName());
            imgG=fc.getAbsoluteFile().toURI().toString();
            Image i = new Image(imgG);
            image.setImage(i);
            
        }
        fc.exists();
    }
    
    

    @FXML
    private void save(ActionEvent event) throws SQLException {
         pst=cnx.prepareStatement("UPDATE product SET nom = ? , quantite = ? , prix = ?   WHERE nom = ?");
    String i =getid();
    System.out.println(i);
    pst.setString(1, nomP.getText());
    pst.setInt(2, Integer.parseInt(quantite.getText()));
    pst.setInt(3, Integer.parseInt(prix.getText()));
    pst.setString(4, i);  
    pst.executeUpdate();
      afficher2();
       calculer();
    }
    void afficher_commande()
    {
        try {
            cnx = Datasource.getInstance().getCnx();
            ResultSet rss=cnx.createStatement().executeQuery("select * from commande ORDER BY idcmd");
            while(rss.next())
            {
                data3.add(new CommandeEntity( rss.getInt("id"),rss.getInt("quantite"),rss.getInt("prix_total"),rss.getString("etat")));


            }
                   
        } catch (SQLException ex) {
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
                  
                    columnClient.setCellValueFactory(new PropertyValueFactory<>("idc"));
                    columnQuantite2.setCellValueFactory(new PropertyValueFactory<>("quantite"));
                    columnPrixtotl.setCellValueFactory(new PropertyValueFactory<>("prix_total"));
                    columnEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
                     addButtonToTable(); 
                    tablecommande.setItems(data3);
    }
    public void generation_code(String q , String d)
    {
         String details ="le nom du produit : "+q+"  prix :"+d;
ByteArrayOutputStream out = QRCode.from(details).to(ImageType.PNG).stream();
File f = new File("D:\\QRCODE\\"+q+".png");
FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(f);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            fos.write(out.toByteArray());
        } catch (IOException ex) {
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            fos.flush();
        } catch (IOException ex) {
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     private void addButtonToTable() {
         TableColumn actionCol = new TableColumn("ajouter au pannier");
        actionCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

        Callback<TableColumn<CommandeEntity, Void>, TableCell<CommandeEntity, Void>> cellFactory = new Callback<TableColumn<CommandeEntity, Void>, TableCell<CommandeEntity, Void>>() {
            @Override
            public TableCell<CommandeEntity, Void> call(final TableColumn<CommandeEntity, Void> param) {
                final TableCell<CommandeEntity, Void> cell = new TableCell<CommandeEntity, Void>() {

                    private final Button btn = new Button("Acheter");
                    

                    {
                        btn.setOnAction((ActionEvent event) -> {
                         
                            try {
                                CommandeEntity cmd = getTableView().getItems().get(getIndex());
                                //System.out.println(cmd.getIdcmd());
                               
                                modifier_etat(cmd.getIdc());
                                afficher_cmd();
                            } catch (SQLException ex) {
                                Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
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

        tablecommande.getColumns().add(actionCol);

    }
     void modifier_etat(int i) throws SQLException
     {
         String s="traitée";
           pst=cnx.prepareStatement("UPDATE commande SET etat = '"+s+"'   WHERE id = '"+i+"' ");
      
    pst.executeUpdate();
      afficher_commande();
      
     }
     void afficher_cmd()
     {
           data3.removeAll(data3);
        try {
            cnx = Datasource.getInstance().getCnx();
            ResultSet rss=cnx.createStatement().executeQuery("select * from commande ");
            while(rss.next())
            {
                  data3.add(new CommandeEntity( rss.getInt("id"),rss.getInt("quantite"),rss.getInt("prix_total"),rss.getString("etat")));


            }
                   
        } catch (SQLException ex) {
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
                    columnClient.setCellValueFactory(new PropertyValueFactory<>("idc"));
                    columnQuantite2.setCellValueFactory(new PropertyValueFactory<>("quantite"));
                    columnPrixtotl.setCellValueFactory(new PropertyValueFactory<>("prix_total"));
                    columnEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
                     //addButtonToTable(); 
                    tablecommande.setItems(data3);
     }
    }
    
  


   
    

