/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.user;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import test.Datasource;

/**
 *
 * @author LENOVO
 */
public class userService implements user_service<user> {
     private Connection cnx;   
    private Statement st; 
    private PreparedStatement pst;
    private ResultSet rs;
    public userService(){
    cnx=Datasource.getInstance().getCnx();
    } 
    
    public boolean chercher(int id) throws SQLException {
        String req="select * from user  ";
        List<Integer> list = new ArrayList<>();
        
        try {
            st=cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                list.add(rs.getInt(1));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(userService.class.getName()).log(Level.SEVERE, null, ex);
        }
       // list.forEach(System.out::println);
        return list.contains(id);
    }
    
    
    @Override
    public void insert(user t) {
        if((t.getMot_de_passe().length()>=8)) {
       String req="insert into user(nom,prenom,age,sexe,mot_de_passe,photo,role) values('"+t.getNom()+"','"+t.getPrenom()+"','"+t.getAge()+"','"+t.getSexe()+"','"+t.getMot_de_passe()+"','"+t.getPhoto()+"','"+t.getRole()+"')"; 
     
        try {
            st=cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(userService.class.getName()).log(Level.SEVERE, null, ex);
        } } else { 
            System.out.println("le mot de passe doit etre de 8 ou plus caractere");
        }
        }   
    
    public List<user> recherche_usernom_prenom(String nom,String prenom) throws SQLException{
            List<user> arr=new ArrayList<>();
            PreparedStatement pre=cnx.prepareStatement("SELECT * FROM user WHERE nom LIKE ? AND prenom LIKE ? ;");
        
        pre.setString(1,nom);
        pre.setString(2,prenom);
        ResultSet rs=pre.executeQuery();
        while (rs.next()) {                
               int id=rs.getInt(1);
               String nomm=rs.getString(2);
               String prenomm=rs.getString(3);
               int age=rs.getInt(4);
               String sexe=rs.getString(5);
               String mot= rs.getString(6);
               String photo = rs.getString(7);
               String role = rs.getString(8);
               
               
               user p=new user(id, nomm, prenomm, age, sexe, mot ,photo,role);
     arr.add(p);
     }
    return arr;
              
    }

// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    

    @Override
    public void update(user t, int id) {  
      try {
        if(chercher(id)) {
            PreparedStatement pre;
            try {
                pre = cnx.prepareStatement("UPDATE huntkingdom.`user` SET photo = ? , mot_de_passe= ? WHERE id_user = ? ;");
                pre.setString(1, t.getPhoto());
                pre.setString(2, t.getMot_de_passe());
                pre.setInt(3,id);
                pre.executeUpdate();
                
            } catch (SQLException ex) {
                Logger.getLogger(annonceService.class.getName()).log(Level.SEVERE, null, ex);
            } } else {System.out.println("id introuvable");}
        
        
        
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    } catch (SQLException ex) {
        Logger.getLogger(userService.class.getName()).log(Level.SEVERE, null, ex);
    }
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
      

    @Override
    public void delete(int id) { 
         try {
             if(chercher(id)){
                 String sql = "DELETE FROM user WHERE id_user = ?";
                 
                 try (
                         PreparedStatement pstmt = cnx.prepareStatement(sql)) {
                     
                     // set the corresponding param
                     pstmt.setInt(1, id);
                     // execute the delete statement
                     pstmt.executeUpdate();
                     
                 } catch (SQLException e) {
                     System.out.println(e.getMessage());
                 }} else { System.out.println("id introuvable");}
             // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         } catch (SQLException ex) {
             Logger.getLogger(userService.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @Override
    public List<user> displayAll() {
         String req="select*from user ORDER BY id_user DESC"; 
     List<user> list=new ArrayList<>(); 
        try { 
            st = cnx.createStatement();
            rs= st.executeQuery(req);
            while(rs.next()) {
            list.add(new user(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));
            } } catch (SQLException ex) {
            Logger.getLogger(userService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
