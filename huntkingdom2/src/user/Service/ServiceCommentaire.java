/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.Service;

import user.Entity.Annonce;
import user.Entity.commentaire;
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

/**
 *
 * @author LENOVO
 */
public class ServiceCommentaire implements IServise<commentaire>{
     private Connection con;
    private Statement ste;

    public ServiceCommentaire() {
        con = Datasource.getInstance().getCnx();

    }
    
    
     @Override
    public void ajouter(commentaire t) throws SQLException {
        PreparedStatement pre=con.prepareStatement("INSERT INTO commentaire ( `id_user`, `id_annonce`, `commentaire`) VALUES ( ?, ?, ?);");
       ServiceUser user_ser= new ServiceUser();
        pre.setInt(1,user_ser.getuser(t.getUser_id()).getId_user());
        ServiceAnnonce annonce_ser = new ServiceAnnonce();
        pre.setInt(2, annonce_ser.getAnnonce(t.getId_annonce()).getId_annonce());
        //System.out.println( t.getU());
        pre.setString(3, t.getComentaire());
        pre.executeUpdate();    
    }
    
      @Override
    public boolean delete(int id) throws SQLException {
//        if(chercher(id)){
        PreparedStatement pre=con.prepareStatement("DELETE FROM `huntkingdom`.`commentaire`  WHERE id_commentaire = (?) ;");
        pre.setInt(1,id);
        if (pre.executeUpdate() != 0)
            return true;
//        }
        return false;
    }   
    @Override
    public boolean update(commentaire t, int id) throws SQLException {
        
        PreparedStatement pre=con.prepareStatement("UPDATE `huntkingdom`.`commentaire` SET `commentaire` = ?  WHERE `id_commentaire` = ? ;");
        pre.setString(1, t.getComentaire());
//        pre.setString(2, t.getDescription());
//        pre.setString(3, t.getImage());
//        pre.setInt(4, t.getNote());
//        pre.setString(5, t.getType());
        pre.setInt(2,id);
        if (pre.executeUpdate() != 0)
            return true;
        return false;
        
    }
    @Override
    public List<commentaire> readAll() throws SQLException {
        List<commentaire> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from commentaire ORDER BY id_commentaire DESC");
     while (rs.next()) {                
               int id=rs.getInt(1);
               ServiceUser user_ser= new ServiceUser();
               int user_id = rs.getInt(2);
               user u =user_ser.getuser_id(user_id); 
               String a= u.getUsername();
               int annonce_id = rs.getInt(3);
               String commentaire = rs.getString(4);
               commentaire p=new commentaire(id,commentaire,a);
               
     arr.add(p);
     }
    return arr;
    }
    
}
