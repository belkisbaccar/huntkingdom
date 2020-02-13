/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huntkingdom.Service;
import huntkingdom.IService.IService;
import huntkingdom.Entite.Promotion;
import huntkingdom.Entite.Publicite;
import huntkingdom.Utils.DataBase;
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
/**
 *
 * @author walid
 */
public class ServicePromotion implements IService<Promotion>{
      private Connection con;
    private Statement ste;
    private PreparedStatement pre;
   
    
    public ServicePromotion(){
    con = DataBase.getInstance().getConnection();
    }
    @Override
    public void ajouter(Promotion t) throws SQLException {
     
       if(!chercher(t.getId_promotion())&&!chercherID_PRODUIT(t.getId_produit())){
         pre=con.prepareStatement("INSERT INTO `huntkingdom`.`promotion` ( `id_produit`, `taux`, `date_debut`,`date_fin`) VALUES ( ?, ?, ?,?);");
    pre.setInt(1, t.getId_produit());
    pre.setDouble(2, t.getTaux());
    Date sDate = new java.sql.Date(t.getDate_debut().getTime());
    Date sDate1 = new java.sql.Date(t.getDate_fin().getTime());
    pre.setDate(3, sDate);
    pre.setDate(4, sDate1);
    pre.executeUpdate();
            System.out.println("ajout valide");
    }
        else System.out.println("ajout invalide");  
    }

    @Override
    public boolean delete(int id) throws SQLException {
        if(chercher(id)){
            pre=con.prepareStatement("delete from `huntkingdom`.`promotion` where id_promotion  = (?);");
        pre.setInt(1,id);
       
                pre.execute();
                System.out.println("valide");
                 return true;
        }
        System.out.println("promo nexiste pas");
        return false;
    }

    @Override
    public boolean chercher(int id) throws SQLException {
                       String req="select * from promotion";
        List<Integer> list = new ArrayList<>();
        
        try {
            ste=con.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                list.add(rs.getInt(1));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicePromotion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list.contains(id);
    }
        public boolean chercherID_PRODUIT(int id) throws SQLException {
                       String req="select * from promotion";
        List<Integer> list = new ArrayList<>();
        
        try {
            ste=con.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                list.add(rs.getInt(2));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicePromotion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list.contains(id);
    }

    @Override
    public boolean chercher_ajout(Promotion t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Promotion t, int id) throws SQLException {
        if(chercher(id)){
                Date sDate = new java.sql.Date(t.getDate_debut().getTime());
    Date sDate1 = new java.sql.Date(t.getDate_fin().getTime());
        pre=con.prepareStatement("UPDATE promotion SET id_produit=?, taux = ? , date_debut = ? , date_fin = ?  WHERE id_promotion = ?");
         
    pre.setInt(1, t.getId_produit());
    
    
    pre.setDouble(2,t.getTaux());
    pre.setDate(3,sDate);
    pre.setDate(4,sDate1);
    pre.setInt(5,id);
    pre.executeUpdate();
    return 1;
        
        
        }
        System.out.println("promo nexiste pas ");
        return 0;
    }

    @Override
    public List<Promotion> readAll() throws SQLException {
        String req="select * from promotion  ";
        List<Promotion> list = new ArrayList<>();
        
        try {
            ste=con.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                
               java.util.Date d1 = new java.util.Date(rs.getDate(4).getTime());
               java.util.Date d2 = new java.util.Date(rs.getDate(5).getTime());
                list.add(new Promotion(rs.getInt(1),rs.getInt(2),rs.getDouble(3), d1, d2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicePromotion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list  ;
    }
    public List<Promotion> triePromoParID() throws SQLException {
        String req="select * from promotion ORDER BY id_promotion  ";
        List<Promotion> list = new ArrayList<>();
        
        try {
            ste=con.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                
               java.util.Date d1 = new java.util.Date(rs.getDate(4).getTime());
               java.util.Date d2 = new java.util.Date(rs.getDate(5).getTime());
                list.add(new Promotion(rs.getInt(1),rs.getInt(2),rs.getDouble(3), d1, d2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicePromotion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list  ;
    }

    public List<Promotion> triePromoParID_produit() throws SQLException {
        String req="select * from promotion ORDER BY id_produit  ";
        List<Promotion> list = new ArrayList<>();
        
        try {
            ste=con.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                
               java.util.Date d1 = new java.util.Date(rs.getDate(4).getTime());
               java.util.Date d2 = new java.util.Date(rs.getDate(5).getTime());
                list.add(new Promotion(rs.getInt(1),rs.getInt(2),rs.getDouble(3), d1, d2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicePromotion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list  ;
    } 
    
        public List<Promotion> triePromoParTaux() throws SQLException {
        String req="select * from promotion ORDER BY taux  ";
        List<Promotion> list = new ArrayList<>();
        
        try {
            ste=con.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                
               java.util.Date d1 = new java.util.Date(rs.getDate(4).getTime());
               java.util.Date d2 = new java.util.Date(rs.getDate(5).getTime());
                list.add(new Promotion(rs.getInt(1),rs.getInt(2),rs.getDouble(3), d1, d2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicePromotion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list  ;
    }
}
   

    

