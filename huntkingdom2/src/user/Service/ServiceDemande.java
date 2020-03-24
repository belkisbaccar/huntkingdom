/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.Service;

import user.Entity.Demande;
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

/**
 *
 * @author LENOVO
 */
public class ServiceDemande implements IServise<Demande> {
private Connection cnx;   
    private Statement st; 
    private PreparedStatement pst;
    private ResultSet rs;
    public ServiceDemande(){
    cnx=Datasource.getInstance().getCnx();
    }   

    
    
    
    public boolean chercher(int id) throws SQLException {
        String req="select * from demande_admin  ";
        List<Integer> list = new ArrayList<>();
        
        try {
            st=cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                list.add(rs.getInt(1));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDemande.class.getName()).log(Level.SEVERE, null, ex);
        }
       // list.forEach(System.out::println);
        return list.contains(id);
    }
    
    
    @Override
    public void ajouter(Demande t) throws SQLException {
  
 PreparedStatement pst=cnx.prepareStatement("INSERT INTO demande_admin (`id_user`, `nom`, `prenom`) VALUES (?, ?, ?);");    
        pst.setInt(1,t.getId());
        pst.setString(2, t.getNom());
         pst.setString(3, t.getPrenom());
        pst.executeUpdate(); 
// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
public user getuser(user u) throws SQLException {   // teb3a belkis :)
        user us = new user();
        PreparedStatement pre = cnx.prepareStatement("SELECT * FROM user WHERE username LIKE ? ;");

        pre.setString(1, u.getUsername());
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            int id_user = rs.getInt(1);
            String nom = rs.getString(2);
            String prenom = rs.getString(3);

            us.setId_user(id_user);
            us.setNom(nom);
            us.setPrenom(prenom);
           
        }
        return us;

    }
    
     public user getuser_id(int id) throws SQLException {  
        user us = new user();
        PreparedStatement pre = cnx.prepareStatement("SELECT * FROM user WHERE id_user LIKE ? ;");

        pre.setInt(1,id);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            int id_user = rs.getInt(1);
            String nom = rs.getString(2);
            String prenom = rs.getString(3);

            us.setId_user(id_user);
            us.setNom(nom);
            us.setPrenom(prenom);
           
        }
        return us;

    }
    @Override
    public boolean delete(int id) throws SQLException {
       
if (chercher(id)) { 
    
             
            PreparedStatement pstmt = cnx.prepareStatement("DELETE FROM demande_admin WHERE id_demande = ?");

            // set the corresponding param
            pstmt.setInt(1, id);
            // execute the delete statement
            if (pstmt.executeUpdate() != 0) {
                return true;
            }   
            
            
        }   
        return false;
   // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Demande t, int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Demande> readAll() throws SQLException {
         List<Demande> arr=new ArrayList<>();
    st=cnx.createStatement();
    ResultSet rs=st.executeQuery("select * from demande_admin ORDER BY id_demande DESC");
     while (rs.next()) {                
               int id=rs.getInt(1);
               int user_id=rs.getInt(2);     
               String nom =rs.getString(3); 
               String prenom=rs.getString(4);
               Demande p=new Demande(id,user_id,nom,prenom);
     arr.add(p);
     }
    return arr;

    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
    
    
}
