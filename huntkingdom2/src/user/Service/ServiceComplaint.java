/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.Service;
import user.Service.ServiceUser;
import user.Entity.Complaints;
import user.Entity.user;
import user.Entity.Annonce;
import user.Iservice.IServise;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import java.sql.Date;
import connection.Datasource;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author belkis
 */
public class ServiceComplaint implements IServise<Complaints>{

    private Connection con;
    private Statement ste;

    public ServiceComplaint() {
        con = Datasource.getInstance().getCnx();

    }
   
    
    @Override
    public void ajouter(Complaints t) throws SQLException {
        PreparedStatement pre=con.prepareStatement("INSERT INTO reclamation ( `etat`, `description`, `image` , `note` , `id_annonce_reclame` , `id_user`, `date`) VALUES ( ?, ?, ?, ? ,? ,? , ?);");
        pre.setString(1, t.getEtat());
        pre.setString(2, t.getDescription());
        pre.setString(3, t.getImage());
        pre.setInt(4, t.getNote());
       
        
        ServiceAnnonce annonce_ser = new ServiceAnnonce();
        pre.setInt(5, annonce_ser.getAnnonce(t.getA()).getId_annonce());
       // System.out.println(annonce_ser.getAnnonce(t.getA()).getId_annonce());
        if(annonce_ser.getAnnonce(t.getA())!= null){ System.out.println("reclamation d'annonce");}
        ServiceUser user_ser= new ServiceUser();
        pre.setInt(6,user_ser.getuser(t.getU()).getId_user());
        //System.out.println( t.getU());
        Date d = new java.sql.Date(t.getDate().getTime());
        pre.setDate(7,d);
        pre.executeUpdate();    
    }

    @Override
    public boolean delete(int id) throws SQLException {
//        if(chercher(id)){
        PreparedStatement pre=con.prepareStatement("DELETE FROM `huntkingdom`.`reclamation`  WHERE id_reclamamtion = (?) ;");
        pre.setInt(1,id);
        if (pre.executeUpdate() != 0)
            return true;
//        }
        return false;
    }

    @Override
    public boolean update(Complaints t, int id) throws SQLException {
        
        PreparedStatement pre=con.prepareStatement("UPDATE `huntkingdom`.`reclamation` SET `etat` = ? , `date`= ? WHERE `id_reclamamtion` = ? ;");
        pre.setString(1, t.getEtat());
//        pre.setString(2, t.getDescription());
//        pre.setString(3, t.getImage());
//        pre.setInt(4, t.getNote());
//        pre.setString(5, t.getType());
        Date d = new java.sql.Date(t.getDate().getTime());
        pre.setDate(2,d);
        pre.setInt(3,id);
        if (pre.executeUpdate() != 0)
            return true;
        return false;
        
    }

    @Override
    public List<Complaints> readAll() throws SQLException {
        List<Complaints> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from reclamation");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String etat=rs.getString(2);
               String description=rs.getString(3);
               String image=rs.getString(4);
               int note=rs.getInt(5);
               
               ServiceAnnonce annonce_ser= new ServiceAnnonce();
               int annonce_id = rs.getInt(6);
               Annonce a = annonce_ser.getannonce_id(annonce_id);
               ServiceUser user_ser= new ServiceUser();
               int user_id = rs.getInt(7);
               user u =user_ser.getuser_id(user_id);
               Date date=rs.getDate(8);
              
               Complaints p=new Complaints(etat, description, image, note, a, u, date);
     arr.add(p);
     }
    return arr;
    }
    
    
//    public List<Complaints> recherche_userID(int user_id) throws SQLException{
//            List<Complaints> arr=new ArrayList<>();
//            PreparedStatement pre=con.prepareStatement("SELECT * FROM reclamation WHERE user_id LIKE ? ;");
//        
//        pre.setInt(1,user_id);
//        ResultSet rs=pre.executeQuery();
//        while (rs.next()) {                
//               int id=rs.getInt(1);
//               String etat=rs.getString(2);
//               String description=rs.getString(3);
//               String image=rs.getString(4);
//               int note=rs.getInt(5);
//               String type=rs.getString(6);
//               
//              Date date=rs.getDate(8);
//               
//               Complaints p=new Complaints(id, etat, description, image, note, type ,user_id,date);
//     arr.add(p);
//     }
//    return arr;
//              
//    }
//   
//   public List<Complaints> recherche_etat(String etat) throws SQLException{
//            List<Complaints> arr=new ArrayList<>();
//            PreparedStatement pre=con.prepareStatement("SELECT * FROM reclamation WHERE etat LIKE ? ;");
//        
//        pre.setString(1,etat);
//        ResultSet rs=pre.executeQuery();
//        while (rs.next()) {                
//               int id=rs.getInt(1);
//               
//               String description=rs.getString(3);
//               String image=rs.getString(4);
//               int note=rs.getInt(5);
//               
//               String type=rs.getString(6);
//               int user_id=rs.getInt(7);
//              Date date=rs.getDate(8);
//               
//               Complaints p=new Complaints(id, etat, description, image, note, type ,user_id,date);
//     arr.add(p);
//     }
//    return arr;
//              
//    }
//    
//    public List<Complaints> recherche_userid_etat(String etat,int user_id) throws SQLException{
//            List<Complaints> arr=new ArrayList<>();
//            PreparedStatement pre=con.prepareStatement("SELECT * FROM reclamation WHERE etat LIKE ? AND user_id LIKE ? ;");
//        
//        pre.setString(1,etat);
//        pre.setInt(2,user_id);
//        ResultSet rs=pre.executeQuery();
//        while (rs.next()) {                
//               int id=rs.getInt(1);
//               
//               String description=rs.getString(3);
//               String image=rs.getString(4);
//               int note=rs.getInt(5);
//               
//               String type=rs.getString(6);
//               
//              Date date=rs.getDate(8);
//               
//               Complaints p=new Complaints(id, etat, description, image, note, type ,user_id,date);
//     arr.add(p);
//     }
//    return arr;
//              
//    }
//  
//    public boolean chercher(int id) throws SQLException {
//        String req="select * from reclamation  ";
//        List<Integer> list = new ArrayList<>();
//        
//        try {
//            ste=con.createStatement();
//            ResultSet rs = ste.executeQuery(req);
//            while(rs.next()){
//                list.add(rs.getInt(1));
//                
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(ServiceComplaint.class.getName()).log(Level.SEVERE, null, ex);
//        }
//       //list.forEach(System.out::println);
//        return list.contains(id);
//    }
//    
//    public List<Complaints> trie_userId_croissant() throws SQLException {
//        List<Complaints> arr=new ArrayList<>();
//    ste=con.createStatement();
//    ResultSet rs=ste.executeQuery("select * from reclamation ORDER BY user_id");
//     while (rs.next()) {                
//               int id=rs.getInt(1);
//               String etat=rs.getString(2);
//               String description=rs.getString(3);
//               String image=rs.getString(4);
//               int note=rs.getInt(5);
//               String type=rs.getString(6);
//               int user_id=rs.getInt(7);
//               Date date=rs.getDate(8);
//               
//               Complaints p=new Complaints(id, etat, description, image, note, type, user_id,date);
//     arr.add(p);
//     }
//    return arr;
//    }
//    
//    
//    
//     public List<Complaints> trie_userId_decroissant() throws SQLException {
//        List<Complaints> arr=new ArrayList<>();
//    ste=con.createStatement();
//    ResultSet rs=ste.executeQuery("select * from reclamation ORDER BY user_id DESC");
//     while (rs.next()) {                
//               int id=rs.getInt(1);
//               String etat=rs.getString(2);
//               String description=rs.getString(3);
//               String image=rs.getString(4);
//               int note=rs.getInt(5);
//               String type=rs.getString(6);
//               int user_id=rs.getInt(7);
//               Date date=rs.getDate(8);
//               
//               Complaints p=new Complaints(id, etat, description, image, note, type, user_id,date);
//     arr.add(p);
//     }
//    return arr;
//    }
//     
////     public void alerte_delais() throws SQLException{
////         List<Complaints> arr=new ArrayList<>();
////    ste=con.createStatement();
////    ResultSet rs=ste.executeQuery("select * from reclamation");
////     while (rs.next()) {                
////               int id=rs.getInt(1);
////               String etat=rs.getString(2);
////               String description=rs.getString(3);
////               String image=rs.getString(4);
////               int note=rs.getInt(5);
////               String type=rs.getString(6);
//               int user_id=rs.getInt(7);
//               Date date=rs.getDate(8);
//               
//               Complaints p=new Complaints(id, etat, description, image, note, type, user_id,date);
//               Date d = new java.sql.Date(p.getDate().getTime());
//               if(d-p.getDate()>3)
//     arr.add(p);
//     
//     }
//     
//     }
     
}

