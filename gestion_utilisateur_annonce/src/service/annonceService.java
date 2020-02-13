/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.annonce;
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
public class annonceService implements annonce_service<annonce>{
private Connection cnx;   
    private Statement st; 
    private PreparedStatement pst;
    private ResultSet rs;
    public annonceService(){
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
            Logger.getLogger(annonceService.class.getName()).log(Level.SEVERE, null, ex);
        }
       // list.forEach(System.out::println);
        return list.contains(id);
    }
    
    
@Override
    public void insert(annonce t) {
       String req="insert into annonce(id_annonce,text,image,user_id) values('"+t.getId_annonce()+"','"+t.getText()+"','"+t.getImage()+"','"+t.getUser_id()+"')"; 
     
        try {
            st=cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(userService.class.getName()).log(Level.SEVERE, null, ex);
        }
// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(annonce t, int id) {
        
    try {
        if(chercher(id)) {
            PreparedStatement pre;
            try {
                pre = cnx.prepareStatement("UPDATE huntkingdom.`annonce` SET text = ? , image = ? WHERE id_annonce = ? ;");
                pre.setString(1, t.getText());
                pre.setString(2, t.getImage());
                
                pre.setInt(3,id);
                pre.executeUpdate();
                
            } catch (SQLException ex) {
                Logger.getLogger(annonceService.class.getName()).log(Level.SEVERE, null, ex);
            } } else {System.out.println("id introuvable");}
        
        
        
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    } catch (SQLException ex) {
        Logger.getLogger(annonceService.class.getName()).log(Level.SEVERE, null, ex);
    }
    }  
    
    public List<annonce> trie_userId_decroissant() throws SQLException {
        List<annonce> arr=new ArrayList<>();
    st=cnx.createStatement();
    ResultSet rs=st.executeQuery("select * from annonce ORDER BY user_id DESC");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String text=rs.getString(2);
               String image=rs.getString(3);
               int id_user=rs.getInt(4);
               
               
               annonce p=new annonce(id, text, image, id_user);
     arr.add(p);
     }
    return arr;
    }
    
    

    @Override
    public void delete(int id) { 
    try {
        if(chercher(id)){
            String sql = "DELETE FROM annonce WHERE id_annonce = ?";
            
            try (
                    PreparedStatement pstmt = cnx.prepareStatement(sql)) {
                
                // set the corresponding param
                pstmt.setInt(1, id);
                // execute the delete statement
                pstmt.executeUpdate();
                
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }  else{System.out.println("id introuvable");}
    } catch (SQLException ex) {
        Logger.getLogger(annonceService.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    @Override
    public List<annonce> displayAll() {
      String req="select*from annonce"; 
     List<annonce> list=new ArrayList<>(); 
        try { 
            st = cnx.createStatement();
            rs= st.executeQuery(req);
            while(rs.next()) {
            list.add(new annonce(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4)));
            } } catch (SQLException ex) {
            Logger.getLogger(userService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;        
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
