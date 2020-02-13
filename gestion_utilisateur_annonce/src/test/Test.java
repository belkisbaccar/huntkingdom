/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entity.annonce;
import entity.commentaire;
import entity.user;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.annonceService;
import service.commentaireService;
import service.userService;

/**
 *
 * @author LENOVO
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        user p=new user("jazi","Abdelhamid",21,"homme","123456789","ph","user");
        userService ps=new userService(); 
        //user p1 = new user("aze","1555555555");
        //ps.update(p1,10);
        //annonce a=new annonce("salut","ph",9);
        annonceService as=new annonceService();
        //annonce a1 = new annonce("helloooo","aaaaa");
        //as.update(a1, 8);
        //commentaire c=new commentaire(1,8,8,"hello");
        //commentaireService cs=new commentaireService();
        //commentaire c1 = new commentaire("hellloooo"); 
        //cs.update(c1, 1);
       // ps.insert(p); 
        //cs.insert(c);
        //as.insert(a);
        //ps.displayAll().forEach(System.out::println);
        List<user> u;
        try {
            u = ps.recherche_usernom_prenom("ala","Abdelhamid");
            System.out.println(u);
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        List<annonce> l;
        try {
            l = as.trie_userId_decroissant();
            System.out.println(l);
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //as.displayAll().forEach(System.out::println);
        //cs.displayAll().forEach(System.out::println);
        //ps.delete(11);
        //ps.displayAll().forEach(System.out::println);
        //as.displayAll().forEach(System.out::println);
        //as.delete(1);
        //as.displayAll().forEach(System.out::println);
       
    }
    
}
