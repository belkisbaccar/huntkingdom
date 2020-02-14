/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produitController;

import commerce.entities.produit;
import datasource.Datasource;
import java.sql.Connection;
import java.sql.Date;
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
 * @author azizm
 */

public class produitcontroller {
    private Connection cnx;
    private Statement st;
    private PreparedStatement pst;
    private ResultSet s;
     public produitcontroller() {
        cnx = Datasource.getInstance().getCnx();
    }
     public void insert(produit p) throws SQLException
    {
         Statement stmt = cnx.createStatement();
        String SQL = "SELECT * FROM product WHERE nom ='" +p.getNomP()+"'";
          ResultSet rs = stmt.executeQuery(SQL);
            if(rs.next())
                System.out.println("produit existe dja");
            else {
            
        String req="insert into product (nom,quantite,prix)values('"+p.getNomP()+"','"+p.getQuantite()+"','"+p.getPrix()+"')";
        try {
            st=cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(produitcontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
            }
    }
   
    public void delete(int id)throws SQLException
    {
         
            
        pst=cnx.prepareStatement("delete from product where ID  = ?;");
        pst.setInt(1,id);
        pst.execute();
       
        
    
    }
    public  List<produit> displayAll()
    {
        String req="select * from product";
                List<produit> list = new ArrayList<>();
                try{
                    st=cnx.createStatement();
                    s=st.executeQuery(req);
                    while(s.next())
                    {
                        list.add(new produit(s.getInt(1),s.getString(2),s.getInt(3),s.getInt(4)));
                    }
                    
                }
        catch (SQLException ex)
     {
         Logger.getLogger(produitcontroller.class.getName()).log(Level.SEVERE, null, ex);
     }
                return list;
    } 
    public void chercher(String nom) throws SQLException {
        String req="select * from product  where nom='" +nom+"' ";
        
        
        try {
            st=cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            if(rs.next()){
                System.out.println("existe");
                
            }
            else
                System.out.println("n'existe pas");
        } catch (SQLException ex) {
            Logger.getLogger(produitcontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
    }
     public void update(produit p,int id) throws SQLException {
        
        
        pst=cnx.prepareStatement("UPDATE product SET nom = ? , quantite = ? , prix = ?   WHERE ID = ?");
       
    pst.setString(1, p.getNomP());
    pst.setInt(2, p.getQuantite());
    pst.setInt(3, p.getPrix());
    pst.setInt(4, id);  
    pst.executeUpdate();
    
        
    }
}
