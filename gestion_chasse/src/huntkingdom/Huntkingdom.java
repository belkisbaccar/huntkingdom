/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huntkingdom;

import entity.Chasse;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.ChasseService;

/**
 *
 * @author AHMED
 */
public class Huntkingdom {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        // TODO code application logic here
//        DataSource ds = DataSource.getInstance();
//        System.out.println(ds);
 // Chasse d = new Chasse();    
      
      ChasseService cs = new ChasseService();
      
      
      
                                      /*---------------------------Instance---------------------------*/
//                                        Chasse c11 = new Chasse();    
//                                          Chasse c12 = new Chasse();    
//                                            Chasse c13 = new Chasse();    
//   c11.setAnimal("zebre");
//   c11.setRegion("Forest Du Remel");
//   c11.setSaison("Printemps");
//java.sql.Date dd11 = java.sql.Date.valueOf("2020-3-3");
//java.sql.Date df11 = java.sql.Date.valueOf("2020-4-3");
// c11.setDate_debut(dd11);
// c11.setDate_fin(df11);
//
//   c12.setAnimal("fox");
//   c12.setRegion("Ksar Ghilane");
//   c12.setSaison("Hiver");
//java.sql.Date dd12 = java.sql.Date.valueOf("2020-12-19");
//java.sql.Date df12 = java.sql.Date.valueOf("2021-2-19");
// c12.setDate_debut(dd12);
// c12.setDate_fin(df12);
//
//   c13.setAnimal("boar");
//   c13.setRegion("Chambi");
//   c13.setSaison("Printemps");
//java.sql.Date dd13 = java.sql.Date.valueOf("2020-8-1");
//java.sql.Date df13 = java.sql.Date.valueOf("2020-9-1");
// c13.setDate_debut(dd13);
// c13.setDate_fin(df13);
//                                    
//            Chasse c1 = new Chasse();                           
//   c1.setAnimal("boar");
//   c1.setRegion("Chambi");
//   c1.setSaison("Printemps");
//java.sql.Date dd1 = java.sql.Date.valueOf("2020-3-3");
//java.sql.Date df1 = java.sql.Date.valueOf("2020-4-3");
// c1.setDate_debut(dd1);
// c1.setDate_fin(df1);
//
//   Chasse c2 = new Chasse();
//   c2.setAnimal("deer");
//   c2.setRegion("Forest Du Remel");
//   c2.setSaison("hiver");
//java.sql.Date dd2 = java.sql.Date.valueOf("2020-9-1");
//java.sql.Date df2 = java.sql.Date.valueOf("2020-11-1");
// c2.setDate_debut(dd2);
// c2.setDate_fin(df2);
//
//Chasse c3 = new Chasse();
//   c3.setAnimal("Lion");
//   c3.setRegion("Chambi");
//   c3.setSaison("Printemps");
//java.sql.Date dd3 = java.sql.Date.valueOf("2020-4-15");
//java.sql.Date df3 = java.sql.Date.valueOf("2020-7-15");
// c3.setDate_debut(dd3);
// c3.setDate_fin(df3);
//
//Chasse c4 = new Chasse();
//   c4.setAnimal("Bear");
//   c4.setRegion("Alaska");
//   c4.setSaison("été");
//java.sql.Date dd4 = java.sql.Date.valueOf("2020-4-15");
//java.sql.Date df4 = java.sql.Date.valueOf("2020-7-15");
// c4.setDate_debut(dd4);
// c4.setDate_fin(df4);

                                             /*---------------------------AJOUT---------------------------*/

//             cs.insertPST(c1);
//            cs.insertPST(c2);
//            cs.insertPST(c3);
//            cs.insertPST(c4);
//              cs.insertPST(c11);
//            cs.insertPST(c12);
//            cs.insertPST(c13);

            
            



                                              /*------------------------Update---------------------------*/
//        c3.setId(17);
//        c3.setAnimal("Fox");
//        c3.setSaison("hiver");
//        c3.setRegion("atlanta");
//        java.sql.Date dd = java.sql.Date.valueOf("2020-7-1");
//        java.sql.Date df = java.sql.Date.valueOf("2020-7-31");
//        //System.out.println("Dates: " + dd2);
//        c3.setDate_debut(dd);
//        c3.setDate_fin(df);
//        cs.update(c3);
        

                                             /*------------------------Delete---------------------------*/

                                             
             //cs.delete("bear");
             
             
                                 /*------------------------Affichage---------------------------*/


        //cs.displayAll().forEach(System.out::println);
        
        
                                              /*---------------------------Recherche---------------------------*/
                                      
        //System.out.println(cs.recherche("fox"));
        
        
                                       /*------------------------Affichage trié---------------------------*/
                                       
                         //cs.triByDate().forEach(System.out::println);
                       
                       
                                                              /*------------------------Stats---------------------------*/

                       
                       //cs.Stat();
    }
    
}
