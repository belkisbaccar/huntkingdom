/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chasse.Service;

import chasse.Entity.Chasse;
import chasse.Entity.Produitentity;
import connection.Datasource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AHMED
 */
public class ProduitService {
    //AHMED
    private Connection cnx;
   private Statement st;
    private PreparedStatement pst;

    private ResultSet rs;
private static ChasseService instance;
    public ProduitService() {
        cnx = Datasource.getInstance().getCnx();

    }
//    int id_produit;
//
//    public int getId_produit() {
//        return id_produit;
//    }
    
     public Produitentity getProduitt(Produitentity p) throws SQLException {   
        Produitentity pe = new Produitentity();
        //String req="select * from product where ID LIKE ? ;";
             pst = cnx.prepareStatement("SELECT * FROM product WHERE nom LIKE  ? AND quantite LIKE  ? AND prix LIKE  ? AND image LIKE  ? ;");
                  System.out.println("jhgfddfghjklmlkjhgvf");


        //pst.setInt(1, p.getId());
        pst.setString(1,p.getNomP());
                pst.setInt(2,p.getQuantite());
                        pst.setInt(3,p.getPrix());
                                pst.setString(4,p.getImage());



         System.out.println(p);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
             int id_produit= rs.getInt(1);


            pe.setId(id_produit);
            System.out.println("hh"+id_produit);
           
        }
        return pe;
        

    }
     
     //AHMED
      public Produitentity getProduit_id(int id) throws SQLException {  //teb3a belkis :)
        Produitentity pe = new Produitentity();
        PreparedStatement pre = cnx.prepareStatement("SELECT * FROM product WHERE ID LIKE ? ;");

        pre.setInt(1,id);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            int id_produit = rs.getInt(1);
            String nom = rs.getString(2);
            int quantite = rs.getInt(3);
            String image = rs.getString(5);
            
            int prix = rs.getInt(4);
            
            pe.setId(id_produit);
            pe.setImage(image);
            pe.setNomP(nom);
            pe.setPrix(prix);
           pe.setQuantite(quantite);
        }
        return pe;
      }
      
      public List<Produitentity> displayProduit(){
          List<Produitentity> data = new ArrayList<>();
              try {
            cnx = Datasource.getInstance().getCnx();
            ResultSet rss=cnx.createStatement().executeQuery("select * from product ORDER BY ID");
            while(rss.next())
            {
                data.add(new Produitentity(rss.getString("nom"), rss.getInt("ID")));


            }
                   
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
              return data;
              
      }
      
}
