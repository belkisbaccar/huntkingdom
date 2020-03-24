/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reclamation.Service;

/**
 *
 * @author walid
 */
public class Produit_Sessions {
    private static  Produit_Sessions instance;
    private int id_produit;

    public static Produit_Sessions getInstance() {
        return instance;
    }

    @Override
    public String toString() {
        return "Produit_Sessions{" + "id_produit=" + id_produit + '}';
    }

    public Produit_Sessions(int id_produit) {
        this.id_produit=id_produit;
    }
    

    public int getId_produit() {
        return id_produit;
    }

    public static void setInstance(Produit_Sessions instance) {
        Produit_Sessions.instance = instance;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }
    
     public static Produit_Sessions getInstace(int id) {
        if(instance == null) {
         instance = new Produit_Sessions(id);
        }
        return instance;
    }  
    public void cleanSession() {
        id_produit=0;
      instance = null;
        
       // or null
    }  
    
    
    
}
