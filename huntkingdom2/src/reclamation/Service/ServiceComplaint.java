/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reclamation.Service;
import reclamation.Service.ServiceUser;
import reclamation.Entity.Complaints;
import reclamation.Entity.user;
import reclamation.Entity.Annonce;
import reclamation.Entity.Produitentity;
import reclamation.Iservice.IServise;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import java.sql.Date;
import connection.Datasource;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import reclamation.Service.ServiceProduit;
/**
 *
 * @author belkis
 */
public class ServiceComplaint implements reclamation.Iservice.IServise<Complaints>{

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
        int a= annonce_ser.getAnnonce(t.getA()).getId_annonce();
        System.out.println("dqte "+d);
        System.out.println("qnnnn"+a);
        
//        ServiceProduit prod = new ServiceProduit();
////        System.out.println(prod.getprod_id(t.getP().getId());
////       pre.setInt(8, prod.getuprod(t.getP()).getId());
//        pre.setInt(8, 0);
        //System.out.println("hhhhhhhhhhhhhhhhhhhhhhhh"+prod.getuprod(t.getP()).getId());
      //  ProduitController per = new ProduitController();
        //pre.setInt(8, pre.getuprod);
        pre.executeUpdate();   
       // System.out.println("tesssssssst");
    }
    
    
     public void ajouter_reclamation_prod(Complaints t) throws SQLException {
        PreparedStatement pre=con.prepareStatement("INSERT INTO reclamation ( `etat`, `description`, `image` , `note`  , `id_user`, `date`,`id_produit`) VALUES ( ?, ?, ?, ? ,? ,? , ?);");
        pre.setString(1, t.getEtat());
        pre.setString(2, t.getDescription());
        pre.setString(3, t.getImage());
        pre.setInt(4, t.getNote());
       
       
        ServiceUser user_ser= new ServiceUser();
        pre.setInt(5,user_ser.getuser(t.getU()).getId_user());
        Date d = new java.sql.Date(t.getDate().getTime());
        pre.setDate(6,d);
   
         
        ServiceProduit prod = new ServiceProduit();
        
       pre.setInt(7, prod.getuprod(t.getP()).getId());
        if(prod.getuprod(t.getP())!= null){ System.out.println("reclamation de produit");}
        pre.executeUpdate();   
       // System.out.println("tesssssssst");
    }


    @Override
    public boolean delete(int id) throws SQLException {
//        if(chercher(id)){
        PreparedStatement pre=con.prepareStatement("DELETE FROM `huntkingdom`.`reclamation`  WHERE id_reclamation = (?) ;");
        pre.setInt(1,id);
        if (pre.executeUpdate() != 0)
            return true;
//        }
        return false;
    }

    @Override
    public boolean update(Complaints t, int id) throws SQLException {
        
        PreparedStatement pre=con.prepareStatement("UPDATE `huntkingdom`.`reclamation` SET `etat` = ? , `date`= ? WHERE `id_reclamation` = ? ;");
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
              int prod_id = rs.getInt(8);
              ServiceProduit ser_prod = new ServiceProduit();
              Produitentity pr = ser_prod.getprod_id(prod_id);
              Complaints p=new Complaints(etat, description, image, note, a, u, date,pr);
             // System.out.println("ppp"+p);
     arr.add(p);
     }
    return arr;
    }
    
    
    public List<Complaints> readAll_id() throws SQLException {
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
              ImageView v=new ImageView();
              v.setImage(new Image(rs.getString(4)));
              v.setFitHeight(100);
              v.setFitWidth(100);
              int prod_id = rs.getInt(9);
              ServiceProduit ser_prod = new ServiceProduit();
              Produitentity pr = ser_prod.getprod_id(prod_id);
              //System.out.println("id proooooooooood "+prod_id);
               Complaints p1=new Complaints(id,etat, description, image, note, a, u, date,pr);
               p1.setJ(v);
               if(p1.getA().getId_annonce()>0){
            p1.setSujet("annonce");
               p1.setId_obj(annonce_id);
                   System.out.println("annonce");
               }
               else if(p1.getP().getId()>0){
             p1.setSujet("produit");
                       p1.setId_obj(prod_id);
                       System.out.println("produit");
         }
               
     arr.add(p1);
     }
    return arr;
    }
    
    

    public List<Complaints> recherche_userID(user u) throws SQLException{
            List<Complaints> arr=new ArrayList<>();
            PreparedStatement pre=con.prepareStatement("SELECT * FROM reclamation WHERE user_id LIKE ? ;");
        
        pre.setInt(1,u.getId_user());
        ResultSet rs=pre.executeQuery();
//        while (rs.next()) {                
//               int id=rs.getInt(1);
//               String etat=rs.getString(2);
//               String description=rs.getString(3);
//               String image=rs.getString(4);
//               int note=rs.getInt(5);
//               ServiceAnnonce annonce_ser= new ServiceAnnonce();
//               int annonce_id = rs.getInt(6);
//               Annonce a = annonce_ser.getannonce_id(annonce_id);
//               
//              Date date=rs.getDate(8);
//               
//               Complaints p=new Complaints(id, etat, description, image, note, u, date, a);
//     arr.add(p);
//     }
    return arr;
              
    }
   
   public List<Complaints> recherche_etat(String etat) throws SQLException{
            List<Complaints> arr=new ArrayList<>();
            PreparedStatement pre=con.prepareStatement("SELECT * FROM reclamation WHERE etat LIKE ? ;");
        
        pre.setString(1,etat);
        ResultSet rs=pre.executeQuery();
//        while (rs.next()) {                
//               int id=rs.getInt(1);
//               
//               String description=rs.getString(3);
//               String image=rs.getString(4);
//               int note=rs.getInt(5);
//               
//               String type=rs.getString(6);
//                ServiceUser user_ser= new ServiceUser();
//               int user_id = rs.getInt(7);
//               user u =user_ser.getuser_id(user_id);
//             ServiceAnnonce annonce_ser= new ServiceAnnonce();
//               int annonce_id = rs.getInt(6);
//               Annonce a = annonce_ser.getannonce_id(annonce_id);
//               
//              Date date=rs.getDate(8);
//               
//               Complaints p=new Complaints(id, etat, description, image, note, u, date, a);
//     arr.add(p);
//     }
    return arr;
              
    }
    
    public List<Complaints> recherche_userid_etat(String etat,user u) throws SQLException{
            List<Complaints> arr=new ArrayList<>();
            PreparedStatement pre=con.prepareStatement("SELECT * FROM reclamation WHERE etat LIKE ? AND user_id LIKE ? ;");
        
        pre.setString(1,etat);
        pre.setInt(2,u.getId_user());
        ResultSet rs=pre.executeQuery();
//        while (rs.next()) {                
//               int id=rs.getInt(1);
//               
//               String description=rs.getString(3);
//               String image=rs.getString(4);
//               int note=rs.getInt(5);
//               
//               String type=rs.getString(6);
//                ServiceAnnonce annonce_ser= new ServiceAnnonce();
//               int annonce_id = rs.getInt(6);
//               Annonce a = annonce_ser.getannonce_id(annonce_id);
//              Date date=rs.getDate(8);
//               
//              Complaints p=new Complaints(id, etat, description, image, note, u, date, a);
//     arr.add(p);
//     }
    return arr;
              
    }
  
    public boolean chercher(int id) throws SQLException {
        String req="select * from reclamation  ";
        List<Integer> list = new ArrayList<>();
        
        try {
            ste=con.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                list.add(rs.getInt(1));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceComplaint.class.getName()).log(Level.SEVERE, null, ex);
        }
       list.forEach(System.out::println);
        return list.contains(id);
    }
    
    public List<Complaints> trie_userId_croissant() throws SQLException {
        List<Complaints> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from reclamation ORDER BY user_id");
//     while (rs.next()) {                
//               int id=rs.getInt(1);
//               String etat=rs.getString(2);
//               String description=rs.getString(3);
//               String image=rs.getString(4);
//               int note=rs.getInt(5);
//               ServiceAnnonce annonce_ser= new ServiceAnnonce();
//               int annonce_id = rs.getInt(6);
//               Annonce a = annonce_ser.getannonce_id(annonce_id);
//               ServiceUser user_ser= new ServiceUser();
//               int user_id = rs.getInt(7);
//               user u =user_ser.getuser_id(user_id);
//             
//               Date date=rs.getDate(8);
//               
//               Complaints p=new Complaints(id, etat, description, image, note, u, date, a);
//     arr.add(p);
//     }
    return arr;
    }
    
    
    
     public List<Complaints> trie_userId_decroissant() throws SQLException {
        List<Complaints> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from reclamation ORDER BY user_id DESC");
//     while (rs.next()) {                
//               int id=rs.getInt(1);
//               String etat=rs.getString(2);
//               String description=rs.getString(3);
//               String image=rs.getString(4);
//               int note=rs.getInt(5);
//               ServiceAnnonce annonce_ser= new ServiceAnnonce();
//               int annonce_id = rs.getInt(6);
//               Annonce a = annonce_ser.getannonce_id(annonce_id);
//              ServiceUser user_ser= new ServiceUser();
//               int user_id = rs.getInt(7);
//               user u =user_ser.getuser_id(user_id);
//               Date date=rs.getDate(8);
//               
//              Complaints p=new Complaints(id, etat, description, image, note, u, date, a);
//    
//     arr.add(p);
//     }
    return arr;
    }
     
     public void alerte_delais() throws SQLException{
         Complaints c=new Complaints();
        List<Complaints> arr=new ArrayList<>();
        ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from reclamation ");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String etat=rs.getString(2);               
               Date date=rs.getDate(8);
               Date d = new java.sql.Date(c.getDate().getTime());
               if(d.compareTo(date)>3){
                   Complaints p=new Complaints(id, etat ,date);
               arr.add(p);
    
    
     }
    
     
     }
     
     }
//       public int getReclamation_id(com) throws SQLException {  //teb3a belkis :)
//        user us = new user();
//        PreparedStatement pre = con.prepareStatement("SELECT * FROM reclamation WHERE id_reclamation LIKE ? ;");
//
//        pre.setInt(1,id);
//        ResultSet rs = pre.executeQuery();
//        while (rs.next()) {
//            int id_user = rs.getInt(1);
//            String nom = rs.getString(2);
//            String prenom = rs.getString(3);
//
//            us.setId_user(id_user);
//            us.setNom(nom);
//            us.setPrenom(prenom);
//           
//        }
//        return us;
//
//    }
     
     
     public boolean noter(Complaints t, int id) throws SQLException {
        
        PreparedStatement pre=con.prepareStatement("UPDATE `huntkingdom`.`reclamation` SET `note` = ?  WHERE `id_reclamation` = ? ;");
        pre.setInt(1,t.getNote());     
        pre.setInt(2,id);
        if (pre.executeUpdate() != 0)
            return true;
        return false;
        
    }
     
     
     public int stats_non_traite()throws SQLException {
         List<Complaints> list_c=new ArrayList<>();
         int nb_non_traite=0;
         
         list_c = readAll();
         for(Complaints c:list_c){
             if(c.getEtat().equals("Non traitée"))
                 nb_non_traite ++;

         }System.out.println("list "+list_c);
         System.out.println("mnb "+nb_non_traite);
         return nb_non_traite;
     }
     
     
     public int stats_en_cours()throws SQLException {
         List<Complaints> list_c=new ArrayList<>();
         
         int nb_en_cours=0;
        
         list_c = readAll();
         for(Complaints c:list_c){
             if(c.getEtat().equals("En cours"))
                 nb_en_cours ++;
             
         } System.out.println("list "+list_c);
         return nb_en_cours;
     }
     public int stats_traite()throws SQLException {
         List<Complaints> list_c=new ArrayList<>();
         
         int nb_traite=0;
         list_c = readAll();
         //System.out.println("list c "+list_c);
         for(Complaints c:list_c){
          
             if(c.getEtat().equals("Traitée"))
                 nb_traite ++;
         }System.out.println("list "+list_c);
         return nb_traite;
     }
     
     
         public List<Complaints> readAll_id2( user us) throws SQLException {
        List<Complaints> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from reclamation  where id_user  ='"+us.getId_user()+"'");
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
              ImageView v=new ImageView();
              v.setImage(new Image(rs.getString(4)));
              v.setFitHeight(100);
              v.setFitWidth(100);
              int prod_id = rs.getInt(9);
              ServiceProduit ser_prod = new ServiceProduit();
              Produitentity pr = ser_prod.getprod_id(prod_id);
              //System.out.println("id proooooooooood "+prod_id);
               Complaints p1=new Complaints(id,etat, description, image, note, a, u, date,pr);
               p1.setJ(v);
               if(p1.getA().getId_annonce()>0){
            p1.setSujet("annonce");
               p1.setId_obj(annonce_id);
                   System.out.println("annonce");
               }
               else if(p1.getP().getId()>0){
             p1.setSujet("produit");
                       p1.setId_obj(prod_id);
                       System.out.println("produit");
         }
               
     arr.add(p1);
     }
    return arr;
    }
    
         
         
         
         
         public int stats_0()throws SQLException {
         List<Complaints> list_c=new ArrayList<>();
         
         int nb0=0;
         list_c = readAll();
         //System.out.println("list c "+list_c);
         for(Complaints c:list_c){
          
             if(c.getNote()==0)
                 nb0 ++;
         }System.out.println("list "+list_c);
         return nb0;
     }
         
          public int stats_1()throws SQLException {
         List<Complaints> list_c=new ArrayList<>();
         
         int nb1=0;
         list_c = readAll();
         //System.out.println("list c "+list_c);
         for(Complaints c:list_c){
          
             if(c.getNote()==1)
                 nb1 ++;
         }System.out.println("list "+list_c);
         return nb1;
     }
          
           public int stats_2()throws SQLException {
         List<Complaints> list_c=new ArrayList<>();
         
         int nb2=0;
         list_c = readAll();
         //System.out.println("list c "+list_c);
         for(Complaints c:list_c){
          
             if(c.getNote()==2)
                 nb2 ++;
         }System.out.println("list "+list_c);
         return nb2;
     }
           
            public int stats_3()throws SQLException {
         List<Complaints> list_c=new ArrayList<>();
         
         int nb3=0;
         list_c = readAll();
         //System.out.println("list c "+list_c);
         for(Complaints c:list_c){
          
             if(c.getNote()==3)
                 nb3 ++;
         }System.out.println("list "+list_c);
         return nb3;
     }
            
             public int stats_4()throws SQLException {
         List<Complaints> list_c=new ArrayList<>();
         
         int nb4=0;
         list_c = readAll();
         //System.out.println("list c "+list_c);
         for(Complaints c:list_c){
          
             if(c.getNote()==4)
                 nb4 ++;
         }System.out.println("list "+list_c);
         return nb4;
     }
              public int stats_5()throws SQLException {
         List<Complaints> list_c=new ArrayList<>();
         
         int nb5=0;
         list_c = readAll();
         //System.out.println("list c "+list_c);
         for(Complaints c:list_c){
          
             if(c.getNote()==5)
                 nb5 ++;
         }System.out.println("list "+list_c);
         return nb5;
     }

}

