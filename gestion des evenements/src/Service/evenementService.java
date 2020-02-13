/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import entities.evenement;
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
import projet.DataSource;
import projet.Projet;

/**
 *
 * @author hazem
 */
public class evenementService implements Iservice <evenement> {
     private Connection cnx;
    private Statement st;
    private PreparedStatement pre;
   
    public evenementService(){
    cnx=DataSource.getInstance().getCnx();
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
        String req="INSERT INTO `projet`.`evenement` (`titre_event`, `date_debut_event`, `date_fin_event`, `nb_place_event`) VALUES ('"+e.getTitre_event()+"','"+dd+"','"+df+"','"+e.getNb_place_event()+"');";
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
             pre=cnx.prepareStatement("UPDATE evenement SET titre_event = ? ,date_debut_event =? ,date_fin_event =?,nb_place_event =? WHERE id_event=?");
             pre.setString(1,t.getTitre_event());
             pre.setDate(2,dd);
             pre.setDate(3, df);
             pre.setInt(4, t.getNb_place_event());
             pre.setInt(5,id);
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
               {       java.util.Date dd=new java.util.Date(rs.getDate(3).getTime());
        java.util.Date  df=new java.util.Date(rs.getDate(4).getTime()); 
        list.add(new evenement(rs.getInt(1),rs.getString(2), dd, df,rs.getInt(5)));
               
               }
         } catch (SQLException ex) {
             Logger.getLogger(evenementService.class.getName()).log(Level.SEVERE, null, ex);
         }
        return list;
    }
    public List<evenement> trieParID() {
        String req ="select * from evenement ORDER BY id_event";
        List<evenement> list =new ArrayList<>();
         try {
             st=cnx.createStatement();
             ResultSet rs =st.executeQuery(req);
               while(rs.next())
               {       java.util.Date dd=new java.util.Date(rs.getDate(3).getTime());
        java.util.Date  df=new java.util.Date(rs.getDate(4).getTime()); 
        list.add(new evenement(rs.getInt(1),rs.getString(2), dd, df,rs.getInt(5)));
               
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
               {       java.util.Date dd=new java.util.Date(rs.getDate(3).getTime());
        java.util.Date  df=new java.util.Date(rs.getDate(4).getTime()); 
        list.add(new evenement(rs.getInt(1),rs.getString(2), dd, df,rs.getInt(5)));
               
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
               {       java.util.Date dd=new java.util.Date(rs.getDate(3).getTime());
        java.util.Date  df=new java.util.Date(rs.getDate(4).getTime()); 
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
                java.util.Date dd=new java.util.Date(rs.getDate(3).getTime());
        java.util.Date  df=new java.util.Date(rs.getDate(4).getTime()); 
        arr.add(new evenement(rs.getInt(1),rs.getString(2), dd, df,rs.getInt(5)));
    
     }
         } catch (SQLException ex) {
             Logger.getLogger(evenementService.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        
       
    return arr;
              
    }  
}
