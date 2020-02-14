/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Chasse;
import huntkingdom.DataSource;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
//import java.util.Date;

import static javax.management.Query.value;

/**
 *
 * @author AHMED
 */
public class ChasseService implements ISercice<Chasse> {

    private Connection cnx;
    private Statement st;
    private PreparedStatement pst;

    private ResultSet rs;

    public ChasseService() {
        cnx = DataSource.getInstance().getCnx();

    }

    @Override
    public void insert(Chasse c) {
        String req = " insert into chasse (animal,region,saison,date_debut,date_fin) values (' " + c.getAnimal() + "  ','  " + c.getRegion() + "   ', ,'  " + c.getSaison() + "   ',,'  " + c.getDate_debut() + "   ',,'  " + c.getDate_fin() + "   ')";

        try {
            st = cnx.createStatement();
            System.out.println(req);
            st.execute(req);
        } catch (SQLException ex) {
            Logger.getLogger(ChasseService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void insertPST(Chasse c) {
        String req = "insert into chasse (animal,region,saison,date_debut,date_fin) values (?,?,?,?,?)";
        try {
            pst = cnx.prepareStatement(req);
            pst.setString(1, c.getAnimal());
            pst.setString(2, c.getRegion());
            pst.setString(3, c.getSaison());
            //java.util.Date date = new java.util.Date();
            pst.setDate(4, c.getDate_debut());
            pst.setDate(5, c.getDate_fin());

            // pst.setTimestamp(4,getCurrentTimeStamp());
            //pst.setDate(5,new java.sql.Date(c.getDate_fin().getT()));
            //pst.setString(2,a.getPrenom());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ChasseService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(Chasse c) {
        String sql = "UPDATE chasse SET animal=? , saison=? , region=? ,date_debut=? ,date_fin=?  WHERE id=?";
//        int id = 0;
//        try {
//            id = pst.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
//            System.out.println(id);
//        } catch (SQLException ex) {
//            Logger.getLogger(ChasseService.class.getName()).log(Level.SEVERE, null, ex);
//        }

        try {
            pst = cnx.prepareStatement(sql);
            //if (pst.executeUpdate(sql) <1) {

            // return true;
            //}
            // System.out.println("Animal Date deb == " +  c.getDate_debut());
            pst.setString(2, c.getSaison());
            pst.setString(1, c.getAnimal());
            pst.setString(3, c.getRegion());

            pst.setDate(4, c.getDate_debut());
            pst.setDate(5, c.getDate_fin());
            pst.setInt(6, c.getId());
            //System.out.println(sql);
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Chasse.class.getName()).log(Level.SEVERE, null, ex);
        }
        //return false;
    }

    @Override
    public void delete(String name) {
        try {
            String sql = "DELETE FROM chasse WHERE animal = '" + name + "';";
            PreparedStatement pst = cnx.prepareStatement(sql);
            pst.executeUpdate();

            try {
                pst = cnx.prepareStatement(sql);

                pst.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(ChasseService.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println("delete successfully");

        } catch (SQLException ex) {
            Logger.getLogger(ChasseService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public List<Chasse> displayAll() {
        String req = "select * from chasse";
        List<Chasse> list = new ArrayList<>();
        try {
            st = cnx.createStatement();
            rs = st.executeQuery(req);
            while (rs.next()) {
                // list.add(new Chasse(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4));
                list.add(new Chasse(rs.getInt(1), rs.getString("animal"), rs.getString("saison"), rs.getString("region"), rs.getDate("date_debut"), rs.getDate("date_fin")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ChasseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Chasse recherche(String animal) {
        Chasse ch = null;
        String req = "select * from chasse where animal LIKE ? ";

        try {

            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, animal);
            rs = pst.executeQuery();
            //  rs.next();

            if (rs.next()) {
                ch = new Chasse();
                ch.setId(rs.getInt(1));
                ch.setAnimal(rs.getString(2));
                ch.setSaison(rs.getString(4));
                ch.setRegion(rs.getString(3));
                ch.setDate_debut(rs.getDate(5));
                ch.setDate_fin(rs.getDate(6));

            } else {
                throw new SQLException("Animal" + animal + " non trouve");
            }
        } catch (SQLException ex) {
            System.out.println("Animal non trouvé");
            Logger.getLogger(ChasseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ch;
    }

//    @Override
//    public Chasse triParId(int id) {
//           String req = "select * from chasse where id =" + id;
//        Chasse c = new Chasse();
//        try {
//            rs = st.executeQuery(req);
//            // while(rs.next()){
//            rs.next();
//            c.setId(rs.getInt("id"));
//            c.setAnimal(rs.getString("animal"));
//            c.setRegion(rs.getString("region"));
//            c.setSaison(rs.getString("saison"));
//            c.setDate_debut(rs.getDate("date_debut"));
//            c.setDate_fin(rs.getDate("date_fin"));
//
//            //}  
//        } catch (SQLException ex) {
//            Logger.getLogger(ChasseService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return c;
//
//    }
    @Override
    public List<Chasse> triByDate() {

        List<Chasse> list = new ArrayList<Chasse>();

        String req = " select * from chasse ORDER BY date_debut";
        try {
            st = cnx.createStatement();
            rs = st.executeQuery(req);

            while (rs.next()) {

                list.add(new Chasse(rs.getInt(1), rs.getString("animal"), rs.getString("saison"), rs.getString("region"), rs.getDate("date_debut"), rs.getDate("date_fin")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChasseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public void Stat() {
        // List<String> list = new ArrayList<>();
        String region = "";
        try {
            st = cnx.createStatement();

            String req = "SELECT   region FROM  chasse ";

            ResultSet rs = st.executeQuery(req);
            Map<String, Integer> occ = new HashMap<String, Integer>();
            double cnt = 0;
            while (rs.next()) {
                region = rs.getString(1);
                //animal = rs.getString(1);
                int count = 0;

                // int count = occ.containsKey(region) ? occ.get(region):0;  // if key contains region get it else don't 
                if (occ.containsKey(region)) {
                    count = occ.get(region);
                } else {
                    count = 0;
                }

                occ.put(region, count + 1);  //increments value 

                cnt++;
            }

            for (Map.Entry m : occ.entrySet()) {
                double percentage = (int) m.getValue() / cnt * 100;
                System.out.println(m.getKey().toString() + " : " + percentage + "%");
            }
//    

        } catch (SQLException ex) {
            Logger.getLogger(ChasseService.class.getName()).log(Level.SEVERE, null, ex);
        }

//return list;
    }

}
//    public HashMap<String,int> occurence(){
//        int valu = 0;
//        Map<String, valu> map;
//        map = new HashMap<>();
//    }

