/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commerce.entities;

/**
 *
 * @author azizm
 */
public class produit {
  private  String nomP;
     private    int quantite;
      private  int  id;
      private  int prix;

    public produit(int id,String nomP, int quantite, int prix) {
        this.nomP = nomP;
        this.quantite = quantite;
        this.id = id;
        this.prix = prix;
    }
     public produit(String nomP, int quantite, int prix) {
        this.nomP = nomP;
        this.quantite = quantite;
      
        this.prix = prix;
    }

    public produit() {
        
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
        return "produit{" + "ID=" + id + "," + "nomP=" + nomP + ", quantite=" + quantite + ", prix=" + prix + '}';
    }

   
    
    
}
