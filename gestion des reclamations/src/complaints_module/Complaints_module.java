/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complaints_module;


import Entite.Complaints;
import Service.ServiceComplaint;
import Utils.DataBase;
import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author belkis
 */
public class Complaints_module {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        
        
        ServiceComplaint ser = new ServiceComplaint();
        Date d=new Date();
        Complaints c1= new Complaints("confirme",d); // pour modifier 
        Complaints c = new Complaints("etat", "fghj", "j", 4, "kjhd", 1,d);
        Complaints c2 = new Complaints("etat", "fghj", "j", 4, "kjhd", 1,d);
        Complaints c3 = new Complaints("non traite", "aa", "a", 4, "a.png", 2,d);
        
        
         try {
             
             //ajout
             
             
//         
//             ser.ajouter(c);
//             ser.ajouter(c2);
             //  ser.ajouter(c3);
            List<Complaints> list = ser.readAll();
            
            
            
             //tester la fct delete
            
//             if(ser.delete(5)== false) 
//                 System.out.println("Reclmation introuvable.");
//             else 
//                 ser.delete(5);
             
//             
            
            //tester la fonction update
            
//            if(ser.update(c1,12)==false)  
//                 System.out.println("Reclamtion non modifiée");
//            else{
//                ser.update(c1,12);
//                System.out.println("réclamtion a été modifiée ");
//            }
//             
            
            
            
             //tester la fonction readAll
            
            System.out.println(list); 
            
            
            //trie croissant
            
//            List<Complaints> list_triee_croissant= ser.trie_userId_croissant();
//             System.out.println("les reclamations trie par les utilisateurs croissant"+list_triee_croissant);
//             
//             
            //trie decroissant
            
            
//             List<Complaints> list_triee_decroissant= ser.trie_userId_decroissant();
//             System.out.println("les reclamations trie par les utilisateurs decroissant"+list_triee_decroissant);
            
            
            //recherche par userid
            
//           List<Complaints> recherche_userId = ser.recherche_userID(2);
//             System.out.println("liste des reclamation du user avec l'id 2"+recherche_userId);
//             
             
             
             //recherche par etat
//           List<Complaints> recherche_etat = ser.recherche_etat("confirme");
//             System.out.println("liste des reclamations confirme : "+recherche_etat);
             
             
             
             //recherhce par userid et par etat
//             
//           List<Complaints> recherche_userid_etat = ser.recherche_userid_etat("confirme",1);
//             System.out.println("liste des reclamations confirme du user avec l'id 1: "+recherche_userid_etat);
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        
    }
    
}
