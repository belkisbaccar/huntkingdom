/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reclamation.Service;

import reclamation.Entity.Annonce;
import reclamation.Iservice.IServise;
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
 * @author belkis
 */
public class ServiceAnnonce implements IServise<Annonce>{
private Connection cnx;   
    private Statement st; 
    private PreparedStatement pst;
    private ResultSet rs;
    public ServiceAnnonce(){
    cnx=Datasource.getInstance().getCnx();
    }   
    
    
     public boolean chercher(int id) throws SQLException {
        String req="select * from annonce  ";
        List<Integer> list = new ArrayList<>();
        
        try {
            st=cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                list.add(rs.getInt(1));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAnnonce.class.getName()).log(Level.SEVERE, null, ex);
        }
       // list.forEach(System.out::println);
        return list.contains(id);
    }
    @Override
    public void ajouter(Annonce t) throws SQLException {
 String req="insert into annonce(id_annonce,text,image,user_id) values('"+t.getId_annonce()+"','"+t.getText()+"','"+t.getImage()+"','"+t.getUser_id()+"')"; 
     
        try {
            st=cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAnnonce.class.getName()).log(Level.SEVERE, null, ex);
        }    }

    @Override
    public boolean delete(int id) throws SQLException  {
        if (chercher(id)) {
            PreparedStatement pstmt = cnx.prepareStatement("DELETE FROM annonce WHERE id_annonce = ?");

            // set the corresponding param
            pstmt.setInt(1, id);
            // execute the delete statement
            if (pstmt.executeUpdate() != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean update(Annonce t, int id) throws SQLException {
PreparedStatement pre;

        pre = cnx.prepareStatement("UPDATE annonce SET text = ? , image = ? WHERE id_annonce = ? ;");
        pre.setString(1, t.getText());
                pre.setString(2, t.getImage());
                
                pre.setInt(3,id);
                pre.executeUpdate();

        if (pre.executeUpdate() != 0) {
            return true;
        }
        return false;
        
     }

    @Override
    public List<Annonce> readAll()  {
 String req="select*from annonce"; 
     List<Annonce> list=new ArrayList<>(); 
        try { 
            st = cnx.createStatement();
            rs= st.executeQuery(req);
            while(rs.next()) {
            list.add(new Annonce(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4)));
            } } catch (SQLException ex) {
            Logger.getLogger(ServiceAnnonce.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;   
    }
    
    
     public List<Annonce> trie_annonceid_decroissant() throws SQLException {
        List<Annonce> arr=new ArrayList<>();
    st=cnx.createStatement();
    ResultSet rs=st.executeQuery("select * from annonce ORDER BY user_id DESC");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String text=rs.getString(2);
               String image=rs.getString(3);
               int id_user=rs.getInt(4);
               
               
               Annonce p=new Annonce(id, text, image, id_user);
     arr.add(p);
     }
    return arr;
    }
     
     
      public Annonce getAnnonce(Annonce a) throws SQLException {   // teb3a belkis :)
        Annonce an = new Annonce();
        PreparedStatement pre = cnx.prepareStatement("SELECT * FROM annonce WHERE  image LIKE ? AND text LIKE ? AND user_id LIKE ? ;");

        
        pre.setString(1, a.getImage());
        pre.setString(2, a.getText());
        pre.setInt(3, a.getUser_id());
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            int id_annonce = rs.getInt(1);
            String image= rs.getString(2);
            String text = rs.getString(3);
            int id_user = rs.getInt(4);

            an.setId_annonce(id_annonce);
            an.setImage(image);
            an.setText(text);
            an.setUser_id(id_user);
           
        }
        return an;

    }
      
      public Annonce getannonce_id(int id) throws SQLException {  //teb3a belkis :)
        Annonce us = new Annonce();
        PreparedStatement pre = cnx.prepareStatement("SELECT * FROM annonce WHERE id_annonce LIKE ? ;");

        pre.setInt(1,id);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            int id_annonce = rs.getInt(1);
            String text = rs.getString(2);
            String image = rs.getString(3);
            int id_user = rs.getInt(4);
            
            us.setId_annonce(id_annonce);
            us.setImage(image);
            us.setText(text);
            us.setUser_id(id);
           
        }
        return us;

    }
}
