/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huntkingdom.Service;

import huntkingdom.Entite.Publicite;
import huntkingdom.IService.IService;
import java.sql.SQLException;
import java.util.List;
import huntkingdom.Entite.PubliciteAimer;
import huntkingdom.Utils.DataBase;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import huntkingdom.Service.ServicePublicite;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author walid
 */
public class ServicePubliciteAimer implements IService<PubliciteAimer> {
      private Connection con;
    private Statement ste;
    private PreparedStatement pre;
   
    
    public ServicePubliciteAimer(){
    con = DataBase.getInstance().getConnection();
    }
    @Override
    public void ajouter(PubliciteAimer t) throws SQLException {
        if(!chercher(t.getId_pub_aimer())){
         pre=con.prepareStatement("INSERT INTO `huntkingdom`.`publicite_aimer` ( `id_publicite`, `id_user`, `date`) VALUES ( ?, ?, ?);");
    pre.setInt(1, t.getId_publicite());
    pre.setInt(2, t.getId_user());
    Date sDate = new java.sql.Date(t.getDate().getTime());

    pre.setDate(3, sDate);
    pre.executeUpdate();
            System.out.println("ajout valide");
    }
        else System.out.println("ajout invalide");
    }
    @Override
    public boolean delete(int id) throws SQLException {
        if(chercher(id))
        {pre=con.prepareStatement("delete from `huntkingdom`.`publicite_aimer` where id_pub_aimer  = (?);");
        pre.setInt(1,id);
       
                pre.execute();
                System.out.println("valide");
                 return true;}
        System.out.println("n'existe pas");
        return false;
    }

    @Override
    public boolean chercher(int id) throws SQLException {
                String req="select * from publicite_aimer";
        List<Integer> list = new ArrayList<>();
        
        try {
            ste=con.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                list.add(rs.getInt(1));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicePubliciteAimer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list.contains(id);
    }

    @Override
    public boolean chercher_ajout(PubliciteAimer t) throws SQLException {
          String req="select * from publicite_aimer";
        List<Integer> list = new ArrayList<>();
        
        try {
            ste=con.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                list.add(rs.getInt(2));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicePubliciteAimer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list.contains(t.getId_publicite());
    }

    @Override
    public int update(PubliciteAimer t, int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PubliciteAimer> readAll() throws SQLException {
               String req="select * from publicite_aimer  ";
        List<PubliciteAimer> list = new ArrayList<>();
        
        try {
            ste=con.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                
               java.util.Date d1 = new java.util.Date(rs.getDate(4).getTime());
                list.add(new PubliciteAimer(rs.getInt(1),rs.getInt(2),rs.getInt(3),d1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicePubliciteAimer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list  ;
    }

  
    
}
