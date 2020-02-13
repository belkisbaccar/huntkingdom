/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.commentaire;
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
public class commentaireService implements commentaire_service<commentaire>{
private Connection cnx;   
    private Statement st; 
    private PreparedStatement pst;
    private ResultSet rs;
    public commentaireService(){
    cnx=Datasource.getInstance().getCnx();
    }   
    
    public boolean chercher(int id) throws SQLException {
        String req="select * from commentaire  ";
        List<Integer> list = new ArrayList<>();
        
        try {
            st=cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                list.add(rs.getInt(1));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(commentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }
       // list.forEach(System.out::println);
        return list.contains(id);
    }
    
    @Override
    public void insert(commentaire t) {
    String req="insert into commentaire(id_commentaire,id_user,id_annonce,commentaire) values('"+t.getId_commentaire()+"','"+t.getUser_id()+"','"+t.getId_annonce()+"','"+t.getComentaire()+"')"; 
     
        try {
            st=cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(userService.class.getName()).log(Level.SEVERE, null, ex);
        }   

// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(commentaire t, int id) {
          try {
        if(chercher(id)) {
            PreparedStatement pre;
            try {
                pre = cnx.prepareStatement("UPDATE huntkingdom.`commentaire` SET commentaire = ? WHERE id_commentaire = ? ;");
                pre.setString(1, t.getComentaire());
                
                
                pre.setInt(2,id);
                pre.executeUpdate();
                
            } catch (SQLException ex) {
                Logger.getLogger(commentaireService.class.getName()).log(Level.SEVERE, null, ex);
            } } else {System.out.println("id introuvable");}
        
        
        
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    } catch (SQLException ex) {
        Logger.getLogger(annonceService.class.getName()).log(Level.SEVERE, null, ex);
    }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int id) { 
    try {
        if(chercher(id)) {
            String sql = "DELETE FROM commentaire WHERE id_commentaire = ?";
            
            try (
                    PreparedStatement pstmt = cnx.prepareStatement(sql)) {
                
                // set the corresponding param
                pstmt.setInt(1, id);
                // execute the delete statement
                pstmt.executeUpdate();
                
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            
// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else{System.out.println("id introuvable");}
    } catch (SQLException ex) {
        Logger.getLogger(commentaireService.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    @Override
    public List<commentaire> displayAll() {
   String req="select*from commentaire"; 
     List<commentaire> list=new ArrayList<>(); 
        try { 
            st = cnx.createStatement();
            rs= st.executeQuery(req);
            while(rs.next()) {
            list.add(new commentaire(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4)));
            } } catch (SQLException ex) {
            Logger.getLogger(userService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;        
        
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
