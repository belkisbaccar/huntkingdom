/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commerce;

import commerce.entities.panier;
import commerce.entities.produit;
import datasource.Datasource;
import java.sql.SQLException;
import panierController.panierController;
import produitController.produitcontroller;

/**
 *
 * @author azizm
 */
public class Commerce {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        Datasource ds= Datasource.getInstance();
        produit p = new produit("boisson",25,20);
        produit p2 = new produit("halib",20,20);
        produitcontroller ps= new produitcontroller();
       panierController pn = new panierController();
       panier p3 = new panier(1,"halib",3);
        panier p4 = new panier(2,"boisson",1);
       //ps.insert(p);
     
       // ps.update(p,24);
       //ps.displayAll().forEach(System.out::println);
       //ps.chercher("");
     //  ps.delete(25);
      //pn.commande(2);
    //pn.insert(p3);
   // pn.insert(p3);

     //pn.réduire_quantité(p4);
        
    }
    
}
