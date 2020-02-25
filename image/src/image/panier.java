/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package image;

import javafx.scene.control.TextField;

/**
 *
 * @author azizm
 */
public class panier {
   private String nomP;
   private int quantite;
 private int prix;
    private int id;
    private TextField qt;

    public TextField getQt() {
        return qt;
    }

    public void setQt(TextField qt) {
        this.qt = qt;
    }

    public panier(int id,String nomP, TextField qt) {
        this.nomP = nomP;
        this.qt = qt;
        
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
