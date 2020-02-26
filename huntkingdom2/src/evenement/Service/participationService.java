/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evenement.Service;

import evenement.Entity.user;
import evenement.Entity.participation;
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
import connection.Datasource;

/**
 *
 * @author hazem
 */
public class participationService implements Iservice<participation>{
    
    private Connection cnx;
    private Statement st;
    private PreparedStatement pre;
   
    public participationService(){
    cnx = Datasource.getInstance().getCnx();
    }
    @Override
    public void insert(participation e) {
        
        Date df=new java.sql.Date(e.getDate_reservation().getTime());
        String req="INSERT INTO `huntkingdom`.`participation` (`username`, `id_event`, `date_reservation`) VALUES ('"+e.getUser().getUsername()+"','"+e.getId_event()+"','"+df+"');";
        try {
            
            st=cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(evenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
public Boolean chercher(int id) {
        String req ="select * from participation";
        List<Integer> list =new ArrayList<>();
         try {
             st=cnx.createStatement();
             ResultSet rs =st.executeQuery(req);
               while(rs.next())
               {       
        java.util.Date  df=new java.util.Date(rs.getDate(4).getTime()); 
        list.add(rs.getInt(1));
               
               }
         } catch (SQLException ex) {
             Logger.getLogger(evenementService.class.getName()).log(Level.SEVERE, null, ex);
         }
        return list.contains(id);
    } 
    @Override
    public boolean update(participation p,int id){
        if(chercher(id)){
              
        Date df=new java.sql.Date(p.getDate_reservation().getTime()); 
         try {
             pre=cnx.prepareStatement("UPDATE participation SET username = ? ,id_event =? ,date_reservation =? WHERE id_participation=?");
             pre.setString(1,p.getNom());
             pre.setInt(2, p.getId_event());
             pre.setDate(3, df);
             
             pre.setInt(4,id);
             pre.executeUpdate();
         } catch (SQLException ex) {
             Logger.getLogger(participationService.class.getName()).log(Level.SEVERE, null, ex);
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
             
             pre=cnx.prepareStatement("delete from participation where id_participation= ?;");
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
    public List<participation> displayAll() {
       String req ="select * from participation";
        List<participation> list =new ArrayList<>();
         try {
             st=cnx.createStatement();
             ResultSet rs =st.executeQuery(req);
               while(rs.next())
               {      
        java.util.Date  df=new java.util.Date(rs.getDate(4).getTime()); 
        
         
               
              
               participation a= new participation(rs.getInt(1),rs.getString(2),rs.getInt(3),df);
               
               list.add(a);
               }
         } catch (SQLException ex) {
             Logger.getLogger(participationService.class.getName()).log(Level.SEVERE, null, ex);
         }
        return list;
    }
    public List<participation> trieParID() {
        String req ="select * from evenement ORDER BY id_participation";
        List<participation> list =new ArrayList<>();
         try {
             st=cnx.createStatement();
             ResultSet rs =st.executeQuery(req);
               while(rs.next())
               {       
        java.util.Date  df=new java.util.Date(rs.getDate(4).getTime()); 
        list.add(new participation(rs.getInt(1),rs.getInt(2),df));
               
               }
         } catch (SQLException ex) {
             Logger.getLogger(evenementService.class.getName()).log(Level.SEVERE, null, ex);
         }
        return list;
    }
    public List<participation> recherche_participe(int id) {
            List<participation> arr=new ArrayList<>();
            PreparedStatement pre;
         try {
             pre = cnx.prepareStatement("SELECT * FROM participation WHERE  id_participation LIKE ? ;");
              pre.setInt(1,id);
        ResultSet rs=pre.executeQuery();
        while (rs.next()) {                
                
        java.util.Date  df=new java.util.Date(rs.getDate(4).getTime()); 
        arr.add(new participation(rs.getInt(1),rs.getInt(2), df));
    
     }
         } catch (SQLException ex) {
             Logger.getLogger(evenementService.class.getName()).log(Level.SEVERE, null, ex);
         }
    return arr;
              
    }
    
        public boolean chercher_ajout_participation(participation t) throws SQLException {
          String req="select * from participation where id_event= '"+t.getId_event()+ "' AND username ='"+t.getUser().getUsername()+ "'";
        List<participation> list = new ArrayList<>();
       
        try {
            st=cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                 java.util.Date d1 = new java.util.Date(rs.getDate(4).getTime());
                 user a= new user(rs.getString(2));
                list.add(new participation(a));
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(participationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return (list.size()!=0);
    }
    
}
