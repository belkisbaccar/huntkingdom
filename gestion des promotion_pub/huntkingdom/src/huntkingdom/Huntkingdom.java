/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huntkingdom;

import huntkingdom.Entite.Publicite;
import huntkingdom.Service.ServicePublicite;
import huntkingdom.Utils.DataBase;
import java.sql.SQLException;
import java.util.Date;
import huntkingdom.Entite.PubliciteAimer;
import huntkingdom.Entite.Promotion;
import huntkingdom.Service.ServicePubliciteAimer;
import huntkingdom.Service.ServicePromotion;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author walid
 */
public class Huntkingdom {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServicePublicite pr=new ServicePublicite();
        ServicePubliciteAimer pre=new ServicePubliciteAimer();
        ServicePromotion pre1=new ServicePromotion();
        
        Date d= new Date(2012-1900, 11, 20);
        Date d1= new Date(2014-1900,2,3);
        Date d3= new Date(2018-1900,2,15);
      
        Publicite p=new Publicite("deca","tt",d,d1,"amir",15);
        Publicite p2 =new Publicite(0,"delta", "t", d1, "t", d1, 5);
       
        Promotion pp1=new Promotion(1,80, d, d);
        Promotion pp2=new Promotion(1,90, d, d); 
        Promotion pp3=new Promotion(1,50, d, d); 
        
         PubliciteAimer pp= new PubliciteAimer(32,1,new Date());
        try {
           
            ////*********crud pub ***************
            //pr.ajouter(p);
            //pr.update(p2,32);
            // pr.delete(33);
            // pr.readAll().forEach(System.out::println);
            // pr.trieParDate_F().forEach(System.out::println);
             
            ////******** aimer pub************///
             //pre.ajouter(pp);
             //pre.delete(6);
            ///////********* crud promo*************
            //pre1.ajouter(pp3);
            //pre1.delete(10);
            pre1.update(pp2,12);
             
             
             
        
        } catch (SQLException ex) {
            Logger.getLogger(Huntkingdom.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
