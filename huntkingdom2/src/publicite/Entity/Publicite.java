/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publicite.Entity;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import javafx.scene.image.ImageView;

/**
 *
 * @author walid
 */
public class Publicite {
    private int id_publicite;
    private String nom;
    private String image;
    private ImageView photo;
    private Date date_debut;
    private Date date_fin;
    private String nom_proprietaire;
    private double prix;

    public Publicite(int id_publicite, String nom, String image, ImageView photo, Date date_debut, Date date_fin, String nom_proprietaire, double prix) {
        this.id_publicite = id_publicite;
        this.nom = nom;
        this.image = image;
        this.photo = photo;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.nom_proprietaire = nom_proprietaire;
        this.prix = prix;
    }
 
    public Publicite(int id_publicite, String nom,ImageView photo, Date date_debut, Date date_fin, String nom_proprietaire, double prix) {
        this.id_publicite = id_publicite;
        this.nom = nom;
        this.photo = photo;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.nom_proprietaire = nom_proprietaire;
        this.prix = prix;
    }
   

    public Publicite(int id_publicite, String nom, String image, Date date_debut,String nom_proprietaire, Date date_fin, double prix) {
        this.id_publicite = id_publicite;
        this.nom = nom;
        this.image = image;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.nom_proprietaire=nom_proprietaire;
        this.prix = prix;
        
    }
    public Publicite( String nom, String image, Date date_debut, Date date_fin,String nom_proprietaire ,double prix) {
        
        this.nom = nom;
        this.image = image;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.nom_proprietaire=nom_proprietaire;
        this.prix = prix;
        
    }

    public int getId_publicite() {
        return id_publicite;
    }

    public String getNom() {
        return nom;
    }

    public String getImage() {
        return image;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public String getNom_proprietaire() {
        return nom_proprietaire;
    }

    public double getPrix() {
        return prix;
    }

    public ImageView getPhoto() {
        return photo;
    }

    public void setPhoto(ImageView photo) {
        this.photo = photo;
    }

   

    public void setId_publicite(int id_publicite) {
        this.id_publicite = id_publicite;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public void setNom_proprietaire(String nom_proprietaire) {
        this.nom_proprietaire = nom_proprietaire;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Publicite{" + "id_publicite=" + id_publicite + ", nom=" + nom + ", photo=" + photo +",image="+image+ ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", nom_proprietaire=" + nom_proprietaire + ", prix=" + prix + '}';
    }

  

   

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.id_publicite;
        hash = 47 * hash + Objects.hashCode(this.nom);
        hash = 47 * hash + Objects.hashCode(this.image);
        hash = 47 * hash + Objects.hashCode(this.date_debut);
        hash = 47 * hash + Objects.hashCode(this.date_fin);
        hash = 47 * hash + Objects.hashCode(this.nom_proprietaire);
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.prix) ^ (Double.doubleToLongBits(this.prix) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Publicite other = (Publicite) obj;
        if (this.id_publicite != other.id_publicite) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        if (!Objects.equals(this.date_debut, other.date_debut)) {
            return false;
        }
        if (!Objects.equals(this.date_fin, other.date_fin)) {
            return false;
        }
        if (!Objects.equals(this.nom_proprietaire, other.nom_proprietaire)) {
            return false;
        }
        if (Double.doubleToLongBits(this.prix) != Double.doubleToLongBits(other.prix)) {
            return false;
        }
        return true;
    }

   
    
}
