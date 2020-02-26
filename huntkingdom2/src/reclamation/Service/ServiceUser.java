/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reclamation.Service;

import reclamation.Iservice.IServise;
import reclamation.Entity.user;
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
 * @author belkis
 */
public class ServiceUser implements IServise<user> {

    private Connection cnx;
    private Statement st;
    private PreparedStatement pst;
    private ResultSet rs;

    public ServiceUser() {
        cnx = Datasource.getInstance().getCnx();
    }

    @Override
    public boolean delete(int id) throws SQLException {
        if (chercher(id)) {
            PreparedStatement pstmt = cnx.prepareStatement("DELETE FROM user  WHERE id_user = (?) ;");

            // set the corresponding param
            pstmt.setInt(1, id);
            // execute the delete statement
            if (pstmt.executeUpdate() != 0) {
                return true;
            }
        }
        return false;

    }

    @Override
    public void ajouter(user t) {

        if ((t.getMot_de_passe().length() >= 8)) {
            String req = "insert into user(nom,prenom,age,sexe,mot_de_passe,photo,role,username) values('" + t.getNom() + "','" + t.getPrenom() + "','" + t.getAge() + "','" + t.getSexe() + "','" + t.getMot_de_passe() + "','" + t.getPhoto() + "','" + t.getRole() + "','" + t.getUsername() + "')";
            try {
                st = cnx.createStatement();
                st.executeUpdate(req);
            } catch (SQLException ex) {
                Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("le mot de passe doit etre de 8 ou plus caractere");
        }
    }

    @Override
    public boolean update(user t, int id) throws SQLException {

        PreparedStatement pre;

        pre = cnx.prepareStatement("UPDATE `user` SET photo = ? , mot_de_passe= ? WHERE id_user = ? ;");
        pre.setString(1, t.getPhoto());
        pre.setString(2, t.getMot_de_passe());
        pre.setInt(3, id);
        pre.executeUpdate();

        if (pre.executeUpdate() != 0) {
            return true;
        }
        return false;

        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<user> readAll() throws SQLException {
        String req = "select*from user ORDER BY id_user DESC";
        List<user> list = new ArrayList<>();
        try {
            st = cnx.createStatement();
            rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new user(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8) , rs.getString(9)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public boolean chercher(int id) throws SQLException {
        String req = "select * from user  ";
        List<Integer> list = new ArrayList<>();

        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(rs.getInt(1));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        // list.forEach(System.out::println);
        return list.contains(id);
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
    
     public user getuser_id(int id) throws SQLException {  //teb3a belkis :)
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
     
     
}
