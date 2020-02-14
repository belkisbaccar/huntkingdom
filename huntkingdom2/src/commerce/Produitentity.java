/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commerce;

/**
 *
 * @author azizm
 */
public class Produitentity {
    private  String nomP;
     private     String quantite;
      private  int  id;
      private  String prix;

    public Produitentity(int id,String nomP, String quantite, String prix) {
        this.nomP = nomP;
        this.quantite = quantite;
        this.id = id;
        this.prix = prix;
    }

    public String getNomP() {
        return nomP;
    }

    public String getQuantite() {
        return quantite;
    }

    public int getId() {
        return id;
    }

    public String getPrix() {
        return prix;
    }

    public void setNomP(String nomP) {
        this.nomP = nomP;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

   
    
              
    
}
