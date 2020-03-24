package chasse.Service;

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package service;
//
//import entity.Produitentity;
//import huntkingdom.DataSource;
//import static java.awt.SystemColor.text;
//import java.net.URL;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ResourceBundle;
//import java.util.Scanner;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.chart.BarChart;
//import javafx.scene.chart.XYChart;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Alert.AlertType;
//import javafx.scene.control.Button;
//import javafx.scene.control.ButtonType;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.TextField;
//import javafx.scene.control.cell.PropertyValueFactory;
//
///**
// * FXML Controller class
// *
// * @author azizm
// */
//public class ProduitController implements Initializable {
//
//    @FXML
//    private TextField nomP;
//    @FXML
//    private TextField quantite;
//    @FXML
//    private TextField prix;
//    @FXML
//    private Button ajout;
//    @FXML
//    private Button update;
//    @FXML
//    private Button delete;
//     private Connection cnx;
//    private Statement st;
//    private PreparedStatement pst;
//
//    private ResultSet s;
//    
//    @FXML
//    private TableView<Produitentity> tableproduit;
//    @FXML
//    private TableColumn<Produitentity, String> columnID;
//    @FXML
//    private TableColumn<Produitentity, String> ColumnProduit;
//    @FXML
//    private TableColumn<Produitentity, String> columnQuantite;
//    @FXML
//    private TableColumn<Produitentity, String> columnPrix;
//    @FXML
//    private Button load;
//    ObservableList <Produitentity> data =FXCollections.observableArrayList();
//    ObservableList <Produitentity> data2 ;
//    TableView<Produitentity> prod;
//    @FXML
//    private BarChart<String, Integer> barchart;
//   
//    /**
//     * Initializes the controller class.
//     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        afficher();
//        barchar();
//        
//        // TODO
//    }    
//
//    @FXML
//    private void ajouter(ActionEvent event) throws SQLException {
//        Scanner sc = new Scanner(System.in);
//         cnx = DataSource.getInstance().getCnx();
//         
//        Statement stmt = cnx.createStatement();
//          String SQL = "SELECT * FROM product WHERE nom ='" +nomP.getText()+"'";
//           ResultSet rs = stmt.executeQuery(SQL);
//           if(!rs.next()){
//                if ((!quantite.getText().matches("[A-Za-z]") && !quantite.getText().matches("[\\\\!\"#$%&()*+,./:;<=>?@\\[\\]^_{|}~]+")) 
//                    && (!prix.getText().matches("[A-Za-z]") && !prix.getText().matches("[\\\\!\"#$%&()*+,./:;<=>?@\\[\\]^_{|}~]+"))){
//           String req="insert into product (nom,quantite,prix)values('"+nomP.getText()+"','"+quantite.getText()+"','"+prix.getText()+"')";
//        try {
//            st=cnx.createStatement();
//            st.executeUpdate(req);
//        } catch (SQLException ex) {
//            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//         
//         Alert a1 = new Alert(AlertType.NONE,  
//                              "Produit ajouté",ButtonType.APPLY); 
//  
//                // show the dialog 
//                a1.show(); 
//                afficher2();
//               barchar2();
//    }
//                else
//                {
//                    Alert a = new Alert(AlertType.NONE);
//                    a.setAlertType(AlertType.WARNING); 
//  
//                // set content text 
//                a.setContentText("verifié vos parametre "); 
//  
//                // show the dialog 
//                a.show(); 
//                }
//             
//           }
//                else {
//               Alert a = new Alert(AlertType.NONE);
//                a.setAlertType(AlertType.WARNING); 
//  
//                // set content text 
//                a.setContentText("Produit déjà existe "); 
//  
//                // show the dialog 
//                a.show(); 
//                
//           }
//                
//         
//                
//           }
//           
//           
//    
//
//    @FXML
//    private void modifier(ActionEvent event) {
//    }
//
//    @FXML
//    private void supprimer(ActionEvent event) {
//        data2=tableproduit.getSelectionModel().getSelectedItems();
//            int id;
//            id=data2.get(0).getId();
//            System.out.println(id);
//             
//        try {
//            
//           String query = "delete from product where id = ?";
//      PreparedStatement preparedStmt = cnx.prepareStatement(query);
//      preparedStmt.setInt(1, id);
//
//      // execute the preparedstatement
//      preparedStmt.execute();
//      
//     
//        } catch (SQLException ex) {
//            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//         afficher2();
// 
//    }
//
//    @FXML
//    private void loaddatabase(ActionEvent event) {
//          
//
//       
//     }
//    public void afficher(){
//        try {
//            cnx = DataSource.getInstance().getCnx();
//            ResultSet rss=cnx.createStatement().executeQuery("select * from product ORDER BY ID");
//            while(rss.next())
//            {
//                data.add(new Produitentity(rss.getInt("ID"), rss.getString("nom"),rss.getString("quantite"),rss.getString("prix")));
//
//
//            }
//                   
//        } catch (SQLException ex) {
//            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//         columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
//                    ColumnProduit.setCellValueFactory(new PropertyValueFactory<>("nomP"));
//                    columnQuantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
//                    columnPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
//                    tableproduit.setItems(data);
//    }
//     public void afficher2(){
//         data.removeAll(data);
//        try {
//            cnx = DataSource.getInstance().getCnx();
//            ResultSet rss=cnx.createStatement().executeQuery("select * from product ");
//            while(rss.next())
//            {
//                data.add(new Produitentity(rss.getInt("ID"), rss.getString("nom"),rss.getString("quantite"),rss.getString("prix")));
//
//
//            }
//           
//                   
//        } catch (SQLException ex) {
//            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//         columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
//                    ColumnProduit.setCellValueFactory(new PropertyValueFactory<>("nomP"));
//                    columnQuantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
//                    columnPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
//                    tableproduit.setItems(data);
//    }
//     
//    public void barchar()
//    {
//        String query="select nom,quantite FROM product ORDER BY   quantite";
//        XYChart.Series<String,Integer> series = new XYChart.Series<>();
//try 
//{
//cnx = DataSource.getInstance().getCnx();
// ResultSet rss=cnx.createStatement().executeQuery(query);
//while (rss.next())
//{
//series.getData().add(new XYChart.Data<>(rss.getString(1), rss.getInt(2)));
//}
//
//barchart.getData().add(series);
//}
//catch (SQLException ex) {
//            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//    }
//    public void barchar2()
//    {
//        barchart.getData().clear();
// 
//        String query="select nom,quantite FROM product ORDER BY   quantite";
//        XYChart.Series<String,Integer> series1 = new XYChart.Series<>();
//try 
//{
//   
//
//barchart.getData().add( series1 );
//cnx = DataSource.getInstance().getCnx();
// ResultSet rss=cnx.createStatement().executeQuery(query);
//while (rss.next())
//{
//series1.getData().add(new XYChart.Data<>(rss.getString(1), rss.getInt(2)));
//}
//
//barchart.getData().add(series1);
//}
//catch (SQLException ex) {
//            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//    }
//    //AHMED
//     public Produitentity getProduitt(Produitentity p) throws SQLException {   
//        Produitentity pe = new Produitentity();
//        //String req="select * from product where ID LIKE ? ;";
//             pst = cnx.prepareStatement("SELECT * FROM product WHERE nom LIKE  ? AND quantite LIKE  ? AND prix LIKE  ? AND image LIKE  ? ;");
//                  System.out.println("jhgfddfghjklmlkjhgvf");
//
//
//        //pst.setInt(1, p.getId());
//        pst.setString(1,p.getNomP() );
//                pst.setInt(2,p.getQuantite());
//                        pst.setInt(3,p.getPrix());
//                                pst.setString(4,p.getImage());
//
//
//
//         System.out.println(p);
//        ResultSet rs = pst.executeQuery();
//        while (rs.next()) {
//            int id_produit= rs.getInt(1);
//
//
//            pe.setId(id_produit);
//
//           
//        }
//        return pe;
//        
//
//    }
//     //AHMED
//      public Produitentity getProduit_id(int id) throws SQLException {  //teb3a belkis :)
//        Produitentity pe = new Produitentity();
//        PreparedStatement pre = cnx.prepareStatement("SELECT * FROM annonce WHERE ID LIKE ? ;");
//
//        pre.setInt(1,id);
//        ResultSet rs = pre.executeQuery();
//        while (rs.next()) {
//            int id_produit = rs.getInt(1);
//            String nom = rs.getString(2);
//            int quantite = rs.getInt(3);
//            String image = rs.getString(5);
//            
//            int prix = rs.getInt(4);
//            
//            pe.setId(id_produit);
//            pe.setImage(image);
//            pe.setNomP(nom);
//            //pe.setUser_id(id);
//           
//        }
//        return pe;
//    }
//}
//
