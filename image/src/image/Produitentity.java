/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package image;


import javafx.scene.image.ImageView;




/**
 *
 * @author azizm
 */
public class Produitentity {
    private  String nomP;
     private ImageView image ;
      private  int  id;
      private float  prix;
      private String quantite;

    public String getQuantite() {
        return quantite;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }
 
    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }
    

    public Produitentity(String nomP, float prix,int id,ImageView image) {
        this.nomP = nomP;
        this.id=id;
        this.prix=prix;
        this.image=image;
        
       
    }

    public String getNomP() {
        return nomP;
    }

    public void setNomP(String nomP) {
        this.nomP = nomP;
    }


    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   
    
              
    
}
