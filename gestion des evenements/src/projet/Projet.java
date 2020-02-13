/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import Service.evenementService;
import entities.evenement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author hazem
 */
public class Projet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    
       
        
        
       evenement e1 = null;
       evenement e2 =null;
        try {
            e1 = new evenement("event",new SimpleDateFormat("yyyy-MM-dd").parse("2020-03-06"),new SimpleDateFormat("yyyy-MM-dd").parse("2020-03-06"),10);
            e2=  new evenement("event15",new SimpleDateFormat("yyyy-MM-dd").parse("2000-08-06"),new SimpleDateFormat("yyyy-MM-dd").parse("2000-03-06"),10); 
        } catch (ParseException ex) {
            Logger.getLogger(Projet.class.getName()).log(Level.SEVERE, null, ex);
        }
        evenementService ser = new evenementService();
        List<evenement> l = ser.recherche_event(7);
        System.out.println(l);
        //ser.trieParDatedebut().forEach(System.out::println);
    }

   

    
    
}
