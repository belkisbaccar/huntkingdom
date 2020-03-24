/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reclamation.Service;

import reclamation.Entity.Produitentity;
import connection.Datasource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author belkis
 */
public class ServiceProduit {
    
    
    private Connection con;
    private Statement ste;

    public ServiceProduit() {
        con = Datasource.getInstance().getCnx();
    }
    
    public Produitentity getuprod(Produitentity p) throws SQLException {   // teb3a belkis :)
        Produitentity us = new Produitentity();
        PreparedStatement pre = con.prepareStatement("SELECT * FROM product WHERE nom LIKE ?;");
        
        pre.setString(1, p.getNomP());
//        pre.setInt(2,p.getQuantite());
//        pre.setInt(3,p.getPrix());
//        pre.setString(4, p.getImage());
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            int id_produit = rs.getInt(1);
//            String nom = rs.getString(2);
//            int quantite = rs.getInt(3);
//            int prix= rs.getInt(4);
//            String image= rs.getString(5);
           

           us.setId(id_produit);
//            us.setNomP(nom);
//            us.setQuantite(quantite);
//            us.setPrix(prix);
//            us.setImage(image);
            
           
           //System.out.println("id prod"+id_produit);
        }
        return us;

    }
    
     public Produitentity getprod_id(int id) throws SQLException {  //teb3a belkis :)
        Produitentity us = new Produitentity();
        PreparedStatement pre = con.prepareStatement("SELECT * FROM product WHERE ID LIKE ? ;");

        pre.setInt(1,id);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            
             int id_produit = rs.getInt(1);
            String nom = rs.getString(2);
           

            us.setId(id_produit);
            us.setNomP(nom);
           
        }
        return us;

    }
}
