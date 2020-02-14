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
public class panier {
    String nomP;
    int quantite;
  int prix;
    int id;

    public panier(int id,String nomP, int quantite) {
        this.nomP = nomP;
        this.quantite = quantite;
        
        this.id = id;
    }

    public String getNomP() {
        return nomP;
    }

    public void setNomP(String nomP) {
        this.nomP = nomP;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "panier{" + "nomP=" + nomP + ", quantite=" + quantite + ", prix=" + prix + ", id=" + id + '}';
    }
    
    
    
}
