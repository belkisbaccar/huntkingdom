/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evenement.Service;

import evenement.Entity.evenement;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import connection.Datasource;


/**
 *
 * @author hazem
 */
public class evenementService implements Iservice <evenement> {
     private Connection cnx;
    private Statement st;
    private PreparedStatement pre;
   
    public evenementService(){
    cnx = Datasource.getInstance().getCnx();
    }

    /**
     *
     * @param e
     * @throws SQLException
     */
    
     
     @Override
    public void insert(evenement e){
        Date dd=new java.sql.Date(e.getDate_debut_event().getTime());
        Date df=new java.sql.Date(e.getDate_fin_event().getTime());
        String req="INSERT INTO `huntkingdom`.`evenement` (`titre_event`, `image`, `date_debut_event`, `date_fin_event`, `nb_place_event`) VALUES ('"+e.getTitre_event()+"','"+e.getImage()+"','"+dd+"','"+df+"','"+e.getNb_place_event()+"');";
        try {
            
            st=cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(evenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        
    }

    @Override
    public boolean update(evenement t,int id){
        if(chercher(id)){
               Date dd=new java.sql.Date(t.getDate_debut_event().getTime());
        Date df=new java.sql.Date(t.getDate_fin_event().getTime()); 
         try {
             pre=cnx.prepareStatement("UPDATE evenement SET titre_event = ? ,image = ?,date_debut_event =? ,date_fin_event =?,nb_place_event =? WHERE id_event=?");
             pre.setString(1,t.getTitre_event());
             pre.setString(2,t.getImage());
             pre.setDate(3,dd);
             pre.setDate(4, df);
             pre.setInt(5, t.getNb_place_event());
             pre.setInt(6,id);
             pre.executeUpdate();
         } catch (SQLException ex) {
             Logger.getLogger(evenementService.class.getName()).log(Level.SEVERE, null, ex);
         }
            System.out.println("update valide");
         return true;}
        System.out.println("update invalid: event nexiste pas");
        return false;
    }

    @Override
    public boolean delete(int id) {
         if(chercher(id)){
        try {
             
             pre=cnx.prepareStatement("delete from evenement where id_event= ?;");
             pre.setInt(1, id);
             pre.execute();
         } catch (SQLException ex) {
             Logger.getLogger(evenementService.class.getName()).log(Level.SEVERE, null, ex);
         }
             System.out.println("delete valide");
         return true;
         }
         System.out.println("event nexiste pas");
         return false;
    }

    @Override
    public List<evenement> displayAll() {
        String req ="select * from evenement";
        List<evenement> list =new ArrayList<>();
         try {
             st=cnx.createStatement();
             ResultSet rs =st.executeQuery(req);
               while(rs.next())
               {       java.util.Date dd=new java.util.Date(rs.getDate(4).getTime());
        java.util.Date  df=new java.util.Date(rs.getDate(5).getTime()); 
                   ImageView v=new ImageView();
                   v.setImage(new Image(rs.getString(3)));
                   v.setFitHeight(100);
                   v.setFitWidth(100);
                   evenement pp=new evenement(rs.getInt(1),rs.getString(2),rs.getString(3), dd, df,rs.getInt(6),null);
                   pp.setPhoto(v);
        list.add(pp);
               
               }
         } catch (SQLException ex) {
             Logger.getLogger(evenementService.class.getName()).log(Level.SEVERE, null, ex);
         }
        return list;
    }
    public List<evenement> trieParnb() {
        String req ="select * from evenement ORDER BY nb_place_event";
        List<evenement> list =new ArrayList<>();
         try {
             st=cnx.createStatement();
             ResultSet rs =st.executeQuery(req);
               while(rs.next())
               {       java.util.Date dd=new java.util.Date(rs.getDate(4).getTime());
        java.util.Date  df=new java.util.Date(rs.getDate(5).getTime()); 
        list.add(new evenement(rs.getInt(1),rs.getString(2),rs.getString(3), dd, df,rs.getInt(6)));
               
               }
         } catch (SQLException ex) {
             Logger.getLogger(evenementService.class.getName()).log(Level.SEVERE, null, ex);
         }
        return list;
    }
        public List<evenement> trieParDatedebut() {
        String req ="select * from evenement ORDER BY date_debut_event";
        List<evenement> list =new ArrayList<>();
         try {
             st=cnx.createStatement();
             ResultSet rs =st.executeQuery(req);
               while(rs.next())
               {        java.util.Date dd=new java.util.Date(rs.getDate(4).getTime());
        java.util.Date  df=new java.util.Date(rs.getDate(5).getTime()); 
        list.add(new evenement(rs.getInt(1),rs.getString(2),rs.getString(3), dd, df,rs.getInt(6)));
               
               }
         } catch (SQLException ex) {
             Logger.getLogger(evenementService.class.getName()).log(Level.SEVERE, null, ex);
         }
        return list;
    }
       public Boolean chercher(int id) {
        String req ="select * from evenement";
        List<Integer> list =new ArrayList<>();
         try {
             st=cnx.createStatement();
             ResultSet rs =st.executeQuery(req);
               while(rs.next())
               {       java.util.Date dd=new java.util.Date(rs.getDate(4).getTime());
        java.util.Date  df=new java.util.Date(rs.getDate(5).getTime()); 
        list.add(rs.getInt(1));
               
               }
         } catch (SQLException ex) {
             Logger.getLogger(evenementService.class.getName()).log(Level.SEVERE, null, ex);
         }
        return list.contains(id);
    } 
public List<evenement> recherche_event(int id) {
            List<evenement> arr=new ArrayList<>();
            PreparedStatement pre;
         try {
             pre = cnx.prepareStatement("SELECT * FROM evenement WHERE  id_event LIKE ? ;");
              pre.setInt(1,id);
        ResultSet rs=pre.executeQuery();
        while (rs.next()) {                
                 java.util.Date dd=new java.util.Date(rs.getDate(4).getTime());
        java.util.Date  df=new java.util.Date(rs.getDate(5).getTime()); 
        arr.add(new evenement(rs.getInt(1),rs.getString(2),rs.getString(3), dd, df,rs.getInt(6)));
    
     }
         } catch (SQLException ex) {
             Logger.getLogger(evenementService.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        
       
    return arr;
              
    }  
}
