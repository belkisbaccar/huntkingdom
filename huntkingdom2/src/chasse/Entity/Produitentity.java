/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chasse.Entity;

/**
 *
 * @author azizm
 */
public class Produitentity {
    private  String nomP;
     private     int quantite;
      private  int  id;
      private  int prix;
      private String image;

    public Produitentity(int id,String nomP, int quantite, int prix) {
        this.nomP = nomP;
        this.quantite = quantite;
        this.id = id;
        this.prix = prix;
    }

    public Produitentity(String nomP, int quantite, int id, int prix, String image) {
        this.nomP = nomP;
        this.quantite = quantite;
        this.id = id;
        this.prix = prix;
        this.image = image;
    }
    
//AHMED
    
    public Produitentity(String nomP, int id) {
        
        this.id = id;
        this.nomP = nomP;
    }

    public Produitentity(String nomP, int quantite, int prix, String image) {
        this.nomP = nomP;
        this.quantite = quantite;
        this.prix = prix;
        this.image = image;
    }

    public Produitentity() {
    }

    public Produitentity(int id) {
        this.id = id;
    }
    //AHMED

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    

    public String getNomP() {
        return nomP;
    }

    public int getQuantite() {
        return quantite;
    }

    public int getId() {
        return id;
    }

    public int getPrix() {
        return prix;
    }

    public void setNomP(String nomP) {
        this.nomP = nomP;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return nomP ;
    }

 

   
    
              
    
}
