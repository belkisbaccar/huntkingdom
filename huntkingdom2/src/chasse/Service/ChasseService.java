/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chasse.Service;

import chasse.Entity.Chasse;
import connection.Datasource;
import chasse.Entity.Type_animal;
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
import chasse.Entity.Animal;
//import service.ProduitController;
import chasse.Entity.Animalproduit;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import chasse.Entity.Produitentity;

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
private static ChasseService instance;
    public ChasseService() {
        cnx = Datasource.getInstance().getCnx();

    }
        public static ChasseService getInstance() throws SQLException {
       
         if (instance == null) {
            instance = new ChasseService();
        }
        return instance;}

//    @Override
//    public void insert(Chasse c) {
//        String req = " insert into chasse (animal,region,saison,date_debut,date_fin) values (' " + c.getAnimal() + "  ','  " + c.getRegion() + "   ', ,'  " + c.getSaison() + "   ',,'  " + c.getDate_debut() + "   ',,'  " + c.getDate_fin() + "   ')";
//
//        try {
//            st = cnx.createStatement();
//            System.out.println(req);
//            st.execute(req);
//        } catch (SQLException ex) {
//            Logger.getLogger(ChasseService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }

    public void insertPST(Chasse c) {
        String req = "insert into chasse (animal,region,date_debut,date_fin,type,id_user,idp) values (?,?,?,?,?,?,?)";
        try {
            pst = cnx.prepareStatement(req);
            pst.setString(1, c.getAnimal().name());
            pst.setString(2, c.getRegion());
            //pst.setString(3, c.getSaison());
            //java.util.Date date = new java.util.Date();
            pst.setDate(3, c.getDate_debut());
            pst.setDate(4, c.getDate_fin());
            pst.setString(5, c.getType().name());
            pst.setInt(6, c.getId_user());
           
                  ProduitService pc= new ProduitService();
                 // Produitentity p = new Produitentity();
                  System.out.println("test1");
        pst.setInt(7, pc.getProduitt(c.getProduit()).getId());
                  System.out.println("test2"); 

            // pst.setTimestamp(4,getCurrentTimeStamp());
            //pst.setDate(5,new java.sql.Date(c.getDate_fin().getT()));
            //pst.setString(2,a.getPrenom());
            pst.executeUpdate();
              System.out.println("test2"); 
        } catch (SQLException ex) {
            Logger.getLogger(ChasseService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(int id,String region,Animal animal,Type_animal type,Date date_debut,Date date_fin, Produitentity idp) {
        String sql = "UPDATE chasse SET animal=?  , region=? ,date_debut=? ,date_fin=?,type=? ,idp=? WHERE id=?";
        
//        int id = 0;
//        try {
//            id = pst.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
//            System.out.println(id);
//        } catch (SQLException ex) {
//            Logger.getLogger(ChasseService.class.getName()).log(Level.SEVERE, null, ex);
//        }
ProduitService pc = new ProduitService();
Chasse c = new Chasse();
        try {
            pst = cnx.prepareStatement(sql);
            //if (pst.executeUpdate(sql) <1) {

            // return true;
            //}
            // System.out.println("Animal Date deb == " +  c.getDate_debut());
            //pst.setString(2, c.getSaison());
            pst.setString(1, animal.name());
            pst.setString(2, region);
pst.setInt(6, pc.getProduitt(idp).getId());
            pst.setDate(3, date_debut);
            pst.setDate(4, date_fin);
            pst.setInt(7, id);
            //pst.setInt(6, idp);
            pst.setString(5, type.name());
            //System.out.println(sql);
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("id introuvable");
        }
        //return false;
    }

    @Override
    public void delete(int id) {
        try {
            String sql = "DELETE FROM chasse WHERE id = '" + id + "';";
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
Chasse c = new Chasse();
                c.setId(rs.getInt(1));
                //c.setAnimal(Animal.valueOf(Animal.class, rs.getString("animal")));
                           //c.setAnimal(rs.getString("animal"));
           c.setRegion(rs.getString("region"));
           c.setAnimal(Animal.valueOf(Animal.class, rs.getString("animal")));
          //c.setSaison(rs.getString("saison"));
          c.setDate_debut(rs.getDate("date_debut"));
          c.setDate_fin(rs.getDate("date_fin"));
          c.setId_user(rs.getInt("id_user"));
          ProduitService pe = new ProduitService();
          int prod_id = rs.getInt(8);
          Produitentity p = pe.getProduit_id(prod_id);
                System.out.println(prod_id);
          c.setProduit(p);
                System.out.println(p);
                
          
          //c.setProduit(pe.getProduit_id(rs.getInt("idp")));
          
//String type=rs.getString("type");
//System.out.println(rs.getObject("type", Type_animal.class));

                //System.out.println(type);
                //c.setType(Type_animal.rs.getString("type"));
                c.setType(Type_animal.valueOf(Type_animal.class, rs.getString("type")));
               //c.setType(Type_animal.valueOf(rs.getString("type")));
               //c.setType(Type_animal.oiseaux);
               //c.setType((rs.getObject("type", Type_animal.class)));
               
              list.add(c);
              
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
                //ch.setAnimal(rs.getString(2));
                ch.setAnimal(Animal.valueOf(Animal.class, rs.getString("animal")));
//                ch.setSaison(rs.getString(4));
                ch.setRegion(rs.getString(3));
                ch.setDate_debut(rs.getDate(4));
                ch.setDate_fin(rs.getDate(5));
 ch.setType(Type_animal.valueOf(Type_animal.class, rs.getString("type")));
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
                          ProduitService pe = new ProduitService();

                Chasse c = new Chasse();
                c.setId(rs.getInt(1));
                c.setAnimal(Animal.valueOf(Animal.class, rs.getString("animal")));
//                c.setAnimal(rs.getString("animal"));
                           //c.setAnimal(rs.getString("animal"));
           c.setRegion(rs.getString("region"));
          //c.setSaison(rs.getString("saison"));
          c.setDate_debut(rs.getDate("date_debut"));
          c.setDate_fin(rs.getDate("date_fin"));
          c.setId_user(rs.getInt("id_user"));
 c.setType(Type_animal.valueOf(Type_animal.class, rs.getString("type")));
 int prod_id = rs.getInt(8);
          Produitentity p = pe.getProduit_id(prod_id);
                System.out.println(prod_id);
          c.setProduit(p);
 list.add(c);
                //list.add(new Chasse(rs.getInt(1), rs.getString("animal"), rs.getString("saison"), rs.getString("region"), rs.getDate("date_debut"), rs.getDate("date_fin")));
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

   
    
//return list;
    }

    
}
//    public HashMap<String,int> occurence(){
//        int valu = 0;
//        Map<String, valu> map;
//        map = new HashMap<>();
//    }

