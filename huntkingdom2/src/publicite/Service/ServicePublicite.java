/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publicite.Service;

import publicite.Entity.Publicite;
import publicite.Iservice.IService;
import connection.Datasource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author walid
 */
public class ServicePublicite implements IService<Publicite> {
      private Connection con;
    private Statement ste;
    private PreparedStatement pre;
   
    
    public ServicePublicite(){
    con = Datasource.getInstance().getCnx();
    }

    @Override
    public void ajouter(Publicite t) throws SQLException {
     if(controlede_saisie(t)&&(chercher_ajout(t)||!chercher(t.getId_publicite())))
     {
        pre=con.prepareStatement("INSERT INTO `huntkingdom`.`publicite` ( `nom`, `image`, `date_debut`, `date_fin`, `nom_proprietaire`, `prix`) VALUES ( ?, ?, ?, ?, ?, ?);");
    pre.setString(1, t.getNom());
    pre.setString(2, t.getImage());
    Date sDate = new java.sql.Date(t.getDate_debut().getTime());
    Date sDate1 = new java.sql.Date(t.getDate_fin().getTime());
    System.out.println(sDate);
    pre.setDate(3, sDate);
    pre.setDate(4, sDate1);
    pre.setString(5, t.getNom_proprietaire());
    pre.setDouble(6, t.getPrix());
    pre.executeUpdate();}
     else System.out.println("erreur ajout");
    }

    @Override
    public boolean delete(int id) throws SQLException {
        
        if(chercher(id)){
            System.out.println("exist");
        pre=con.prepareStatement("delete from `huntkingdom`.`publicite` where id_publicite  = (?);");
        pre.setInt(1,id);
            System.out.println(pre.execute());
       return true;}
        else 
        {System.out.println("nexiste pas");
            return false;}
        
    }

    @Override
    public int update(Publicite t,int id) throws SQLException {
        if(chercher(id)){
        Date sDate = new java.sql.Date(t.getDate_debut().getTime());
    Date sDate1 = new java.sql.Date(t.getDate_fin().getTime());
        pre=con.prepareStatement("UPDATE publicite SET nom=?, image = ? , date_debut = ? , date_fin = ? , nom_proprietaire = ? , prix=? WHERE id_publicite = ?");
         
    pre.setString(1, t.getNom());
    
    
    pre.setString(2,t.getImage());
    pre.setDate(3,sDate);
    pre.setDate(4,sDate1);
    pre.setString(5, t.getNom_proprietaire());
    pre.setDouble(6, t.getPrix());
    pre.setInt(7,id);
    pre.executeUpdate();
    return 1;}
        return 0;
    }

    @Override
    public List<Publicite> readAll() throws SQLException {
        String req="select * from publicite  ";
        List<Publicite> list = new ArrayList<>();
        
        try {
            ste=con.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                
               java.util.Date d1 = new java.util.Date(rs.getDate(4).getTime());
               java.util.Date d2 = new java.util.Date(rs.getDate(5).getTime());
                list.add(new Publicite(rs.getInt(1),rs.getString(2),rs.getString(3),d1, rs.getString(6), d2,rs.getDouble(7)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicePublicite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list  ;
    }
        public List<Publicite> readAll_withImage() throws SQLException {
        String req="select * from publicite  ";
        List<Publicite> list = new ArrayList<>();
        
        try {
            ste=con.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                
               java.util.Date d1 = new java.util.Date(rs.getDate(4).getTime());
               java.util.Date d2 = new java.util.Date(rs.getDate(5).getTime());
               
               
                ImageView v = new ImageView();
                
                v.setImage(new Image(rs.getString(3)));
                v.setFitWidth(100);
                v.setFitHeight(100);
               
                //ystem.out.println(v.getImage().toString());
                Publicite p2=new Publicite(rs.getInt(1),rs.getString(2),rs.getString(3),null, d1, d2, rs.getString(6), rs.getDouble(7));
                Publicite p=new Publicite(rs.getInt(1),rs.getString(2),null, d1, d2, rs.getString(6), rs.getDouble(7));
                p2.setPhoto(v);
                list.add(p2);
              // list.add(new Publicite(rs.getInt(1),rs.getString(2),rs.getString(3),d1, rs.getString(6), d2,rs.getDouble(7)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicePublicite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list  ;
    }
    
    public List<Publicite>trieParId() throws SQLException{
            String req="select * from publicite  ORDER BY id_publicite ";
        List<Publicite> list = new ArrayList<>();
        
        try {
            ste=con.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                
               java.util.Date d1 = new java.util.Date(rs.getDate(4).getTime());
               java.util.Date d2 = new java.util.Date(rs.getDate(5).getTime());
                list.add(new Publicite(rs.getInt(1),rs.getString(2),rs.getString(3),d1, rs.getString(6), d2,rs.getDouble(7)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicePublicite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list  ;
    
    }
     public List<Publicite>trieParNom() throws SQLException{
            String req="select * from publicite  ORDER BY nom ";
        List<Publicite> list = new ArrayList<>();
        
        try {
            ste=con.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                
               java.util.Date d1 = new java.util.Date(rs.getDate(4).getTime());
               java.util.Date d2 = new java.util.Date(rs.getDate(5).getTime());
                list.add(new Publicite(rs.getInt(1),rs.getString(2),rs.getString(3),d1, rs.getString(6), d2,rs.getDouble(7)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicePublicite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list  ;
    
    }
        public List<Publicite>trieParPrix() throws SQLException{
            String req="select * from publicite  ORDER BY prix ";
        List<Publicite> list = new ArrayList<>();
        
        try {
            ste=con.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                
               java.util.Date d1 = new java.util.Date(rs.getDate(4).getTime());
               java.util.Date d2 = new java.util.Date(rs.getDate(5).getTime());
                list.add(new Publicite(rs.getInt(1),rs.getString(2),rs.getString(3),d1, rs.getString(6), d2,rs.getDouble(7)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicePublicite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list  ;
    
    }
        public List<Publicite>trieParDate_D() throws SQLException{
            String req="select * from publicite  ORDER BY date_debut ";
        List<Publicite> list = new ArrayList<>();
        
        try {
            ste=con.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                
               java.util.Date d1 = new java.util.Date(rs.getDate(4).getTime());
               java.util.Date d2 = new java.util.Date(rs.getDate(5).getTime());
                list.add(new Publicite(rs.getInt(1),rs.getString(2),rs.getString(3),d1, rs.getString(6), d2,rs.getDouble(7)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicePublicite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list  ;
    
    }
                public List<Publicite>trieParDate_F() throws SQLException{
            String req="select * from publicite  ORDER BY date_fin ";
        List<Publicite> list = new ArrayList<>();
        
        try {
            ste=con.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                
               java.util.Date d1 = new java.util.Date(rs.getDate(4).getTime());
               java.util.Date d2 = new java.util.Date(rs.getDate(5).getTime());
                list.add(new Publicite(rs.getInt(1),rs.getString(2),rs.getString(3),d1, rs.getString(6), d2,rs.getDouble(7)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicePublicite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list  ;
    
    }

    @Override
    public boolean chercher(int id) throws SQLException {
        String req="select * from publicite";
        List<Integer> list = new ArrayList<>();
        
        try {
            ste=con.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                list.add(rs.getInt(1));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicePublicite.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        return list.contains(id);
    }

    @Override
    public boolean chercher_ajout(Publicite t) throws SQLException {
        System.out.println(t.getId_publicite());
        return (t.getId_publicite()==0);
    }
    
    public boolean controlede_saisie(Publicite t) throws SQLException{
    
    if(t.getNom().length()==0 || t.getImage().length()==0 || t.getNom_proprietaire().length()==0 || t.getPrix()==0 || t.getDate_debut()==null || t.getDate_fin()==null)
        return false;
    else if(t.getNom().length()>255 && t.getImage().length()>255 && t.getNom_proprietaire().length()>255) return false;
        System.out.println("valide");
    return true;
    
    }
}
