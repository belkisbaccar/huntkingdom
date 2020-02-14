/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panierController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import commerce.entities.panier;
import datasource.Datasource;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author azizm
 */
public class panierController {
    
     private Connection cnx;
    private Statement st;
    private PreparedStatement pst;
    private ResultSet s;
      public panierController() {
        cnx = Datasource.getInstance().getCnx();
    }
     public void insert(panier p) throws SQLException
    {
         cnx = Datasource.getInstance().getCnx();
         
        Statement stmt = cnx.createStatement();
        String SQL = "SELECT * FROM product WHERE nom ='" +p.getNomP()+"'";
          ResultSet rs = stmt.executeQuery(SQL);
            if(rs.next()){
            int x = rs.getInt(4);//le prix unitaire du produit choisi 
             int s = rs.getInt(3)-p.getQuantite();// prix total quantite du client * prix unitaire
             int m=rs.getInt(3);//quantite de produit dans le stock
             
            st = cnx.createStatement();
        String SQL2 = "SELECT * FROM panier WHERE id ='" +p.getId()+"' and nomP='" +p.getNomP()+"'";
          ResultSet rs2 = stmt.executeQuery(SQL2);
          if(rs2.next())
          {existe( p,  s,x );}
          else {
            
            if((m!=0)&&(m>=p.getQuantite())){
        String req="insert into panier (id,nomP,quantite,prix)values('"+p.getId()+"','"+p.getNomP()+"','"+p.getQuantite()+"','"+p.getQuantite()*x+"')";
       
      
      
        String rq="UPDATE product SET  quantite = '"+s+"'  WHERE nom ='" +p.getNomP()+"'";//mettre a jour le tableau product 
        
        try {
            st=cnx.createStatement();
            st.executeUpdate(req);
            st.executeUpdate(rq);
        } catch (SQLException ex) {
            Logger.getLogger(panierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
            
            }
            else 
                System.out.println("running out of stock");
            
          }
            }
   }
    public void commande(int id ) throws SQLException
     {
        Statement stmt = cnx.createStatement();
        String SQL2 = "SELECT sum(prix) FROM panier WHERE id ='"+id+"'";
          ResultSet rs2 = stmt.executeQuery(SQL2);
          
          if(rs2.next())
          {
             int x = rs2.getInt(1);
             String s="en cours ";
            // System.out.println(x);
       String SQL = "INSERT INTO commande(id,prix_total,etat) values ('"+id+"','" +x+"','" +s+"')";
          st=cnx.createStatement();
            st.executeUpdate(SQL);
          
     }
     }
     void  existe(panier p, int s ,int x) throws SQLException
{
cnx = Datasource.getInstance().getCnx();
          Statement stt = cnx.createStatement();
        String SQL = "SELECT * FROM panier  WHERE id ='" +p.getId()+"'and nomP='" +p.getNomP()+"'";
          ResultSet rs = stt.executeQuery(SQL);
            if(rs.next()){
                int l=rs.getInt(4)+p.getQuantite();// si le panier existe dja et meme id et meme produit on ajoute la quantité demande 
                int q =x*l;
        String SQL2 = "UPDATE panier SET  quantite = '"+l+"' , prix = '"+q+"'  WHERE id ='" +p.getId()+"'and nomP='" +p.getNomP()+"'";
         st=cnx.createStatement();
            st.executeUpdate(SQL2);
           
             
            
        String SQL3 = "UPDATE product SET  quantite = '"+s+"'  WHERE nom ='" +p.getNomP()+"'";
         try {
            st=cnx.createStatement();
            st.executeUpdate(SQL3);
          
        } catch (SQLException ex) {
            Logger.getLogger(panierController.class.getName()).log(Level.SEVERE, null, ex);
        }
            }
}
       public void réduire_quantité(panier p) throws SQLException
    {
         cnx = Datasource.getInstance().getCnx();
         
        Statement stmt = cnx.createStatement();
        Statement stt = cnx.createStatement();
        String SQL = "SELECT * FROM product WHERE nom ='" +p.getNomP()+"'";
          ResultSet rs = stmt.executeQuery(SQL);
            if(rs.next()){
            int x = rs.getInt(4);//le prix unitaire du produit choisi 
             int s = rs.getInt(3)+p.getQuantite();// le mise a jour de stock de tableau product
             int m=rs.getInt(3);//quantite de produit dans le stock
              String SQL2 = "SELECT * FROM panier  WHERE id ='" +p.getId()+"'and nomP='" +p.getNomP()+"'";
          ResultSet rs2 = stt.executeQuery(SQL2);
            if(rs2.next()){
                if(rs2.getInt(4)>=p.getQuantite()){
             int l=rs2.getInt(4)-p.getQuantite();
              int q =x*l;
          String req = "UPDATE panier SET  quantite = '"+l+"' , prix = '"+q+"'  WHERE id ='" +p.getId()+"'and nomP='" +p.getNomP()+"'";
       
      
      
        String rq="UPDATE product SET  quantite = '"+s+"'  WHERE nom ='" +p.getNomP()+"'";//mettre a jour le tableau product 
        
        try {
            st=cnx.createStatement();
            st.executeUpdate(req);
            st.executeUpdate(rq);
        } catch (SQLException ex) {
            Logger.getLogger(panierController.class.getName()).log(Level.SEVERE, null, ex);
        }
                }
                else
                System.out.println("error");
            
            }
            }
            check();
          }
        void check() throws SQLException
       {
             pst=cnx.prepareStatement("delete from panier where quantite  = '"+0+"'");

        pst.execute();
       
           
           }
       }
            
   


