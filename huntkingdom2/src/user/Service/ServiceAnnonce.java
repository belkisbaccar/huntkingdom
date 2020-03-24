/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.Service;

import user.Entity.Annonce;
import user.Entity.user;
import user.Iservice.IServise;
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
import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
  PreparedStatement pst=cnx.prepareStatement("INSERT INTO annonce ( `id_annonce`, `text`, `image` , `user_id` , `etat`) VALUES ( ?, ?, ?, ?, ?);");    
        pst.setInt(1, t.getId_annonce());
        pst.setString(2, t.getText());
        pst.setString(3, t.getImage());
        ServiceUser user_ser= new ServiceUser();
        pst.setInt(4,user_ser.getuser(t.getUser()).getId_user()); 
        pst.setInt(5,0);
        pst.executeUpdate(); 
        
    }
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
  public boolean addaime(Annonce t, int id) throws SQLException{ 
      PreparedStatement pre;
      
        pre = cnx.prepareStatement("UPDATE huntkingdom.`annonce` SET aime = ? , etat = ? WHERE id_annonce = ? ;"); 
       if(t.getEtat()==0) {
        int somme = t.getAime(); 
        somme = somme +1; 
        int k = 1 ;
        pre.setInt(1, somme);   
        pre.setInt(2,k);
          pre.setInt(3,id);
         pre.executeUpdate(); 
         if (pre.executeUpdate() != 0) {
            return true;
        }
        return false; } else { if (t.getEtat()==1)
        { 
        int somme = t.getAime(); 
        somme = somme-1;  
         pre.setInt(1, somme);  
          pre.setInt(2,0);
          pre.setInt(3,id);
         pre.executeUpdate(); 
         if (pre.executeUpdate() != 0) {
            return true;
        }
        return false;
        
        } } 
    return false;
  }   
  public boolean removeaime(Annonce t, int id) throws SQLException{ 
      PreparedStatement pre;

        pre = cnx.prepareStatement("UPDATE huntkingdom.`annonce` SET aime = ? WHERE id_annonce = ? ;"); 
        int somme = t.getAime(); 
        somme = somme-1;  
        pre.setInt(1, somme);  
         pre.setInt(2,id);
         pre.executeUpdate(); 
         if (pre.executeUpdate() != 0) {
            return true;
        }
        return false;
      
  }   
    @Override
    public boolean update(Annonce t, int id) throws SQLException {
PreparedStatement pre;

        pre = cnx.prepareStatement("UPDATE huntkingdom.`annonce` SET text = ? , image = ? WHERE id_annonce = ? ;");
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
    public List<Annonce> readAll() throws SQLException {
 String req="select*from annonce ORDER BY id_annonce DESC"; 
     List<Annonce> list=new ArrayList<>(); 
        
            st = cnx.createStatement();
            rs= st.executeQuery(req);
            while(rs.next()) {
                ImageView l=new  ImageView();
                l.setImage(new Image(rs.getString(3)));
                l.setFitHeight(50);
                l.setFitWidth(50);
            int id = rs.getInt(1); 
             String text=rs.getString(2);
               String image=rs.getString(3);
               int user_id = rs.getInt(4);
                ServiceUser user_ser= new ServiceUser();
                user u =user_ser.getuser_id(user_id); 
                int aime = rs.getInt(5);
                String a = u.getUsername();
    Annonce p=new Annonce(id, text, image,a,aime,null);
     p.setImg(l);
     list.add(p);
            } 
        return list;   
    }
    
    
     public List<Annonce> trie_annonceid_decroissant() throws SQLException {
        List<Annonce> arr=new ArrayList<>();
    st=cnx.createStatement();
    ResultSet rs=st.executeQuery("select * from annonce ORDER BY id_annonce DESC");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String text=rs.getString(2);
               String image=rs.getString(3);
               int user_id=rs.getInt(4);
                 ServiceUser user_ser= new ServiceUser();
               user u =user_ser.getuser_id(user_id);
               
               Annonce p=new Annonce(id, text, image,u);
     arr.add(p);
     }
    return arr;
    }
     
     
      public Annonce getAnnonce(Annonce a) throws SQLException {   // teb3a belkis :)
        Annonce an = new Annonce();
        PreparedStatement pre = cnx.prepareStatement("SELECT * FROM annonce WHERE  image LIKE ? AND text LIKE ? AND user_id LIKE ? ;");

        
        pre.setString(1, a.getImage());
        pre.setString(2, a.getText());
        pre.setInt(3, a.getUser().getId_user());
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            int id_annonce = rs.getInt(1);
            String image= rs.getString(2);
            String text = rs.getString(3);
            int id_user = rs.getInt(4);
             int aime =rs.getInt(5);
            ServiceUser user_ser= new ServiceUser();
            user u =user_ser.getuser_id(id_user);
            
            an.setId_annonce(id_annonce);
            an.setImage(image);
            an.setText(text);
            an.setUser(u);
           an.setAime(aime);
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
            int aime =rs.getInt(5);
            int etat =rs.getInt(6);
             ServiceUser user_ser= new ServiceUser();
            user u =user_ser.getuser_id(id_user);
            us.setId_annonce(id_annonce);
            us.setImage(image);
            us.setText(text);
            us.setUser(u);
            us.setAime(aime);
            us.setEtat(etat);
        }
        return us;

    }
      
      
      
     
      
}
