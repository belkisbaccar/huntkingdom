/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evenement.Entity;

import java.util.Date;
import javafx.scene.image.ImageView;

/**
 *
 * @author hazem
 */
public class evenement {
    private int id_event;
    private String titre_event;
     private String image;
    private Date date_debut_event;
    private Date date_fin_event;
    private int nb_place_event;
    private ImageView photo;

    public ImageView getPhoto() {
        return photo;
    }

    public void setPhoto(ImageView photo) {
        this.photo = photo;
    }

    public evenement(int id_event, String titre_event,String image, Date date_debut_event, Date date_fin_event, int nb_place_event,ImageView photo) {
        this.id_event = id_event;
        this.titre_event = titre_event;
        this.image = image;
        this.date_debut_event = date_debut_event;
        this.date_fin_event = date_fin_event;
        this.nb_place_event = nb_place_event;
        this.photo=photo;
    }
        public evenement(int id_event, String titre_event,String image, Date date_debut_event, Date date_fin_event, int nb_place_event) {
        this.id_event = id_event;
        this.titre_event = titre_event;
        this.image = image;
        this.date_debut_event = date_debut_event;
        this.date_fin_event = date_fin_event;
        this.nb_place_event = nb_place_event;
       
    }

    public evenement(String titre_event,String image, Date date_debut_event, Date date_fin_event, int nb_place_event) {
        this.titre_event = titre_event;
        this.image = image;
        this.date_debut_event = date_debut_event;
        this.date_fin_event = date_fin_event;
        this.nb_place_event = nb_place_event;
    }

    public int getId_event() {
        return id_event;
    }

    public String getTitre_event() {
        return titre_event;
    }

    public Date getDate_debut_event() {
        return date_debut_event;
    }

    public Date getDate_fin_event() {
        return date_fin_event;
    }

    public int getNb_place_event() {
        return nb_place_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public void setTitre_event(String titre_event) {
        this.titre_event = titre_event;
    }

    public void setDate_debut_event(Date date_debut_event) {
        this.date_debut_event = date_debut_event;
    }

    public void setDate_fin_event(Date date_fin_event) {
        this.date_fin_event = date_fin_event;
    }

    public void setNb_place_event(int nb_place_event) {
        this.nb_place_event = nb_place_event;
    }

    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "evenement{" + "id_event=" + id_event + ", titre_event=" + titre_event + ", image=" + image + ", date_debut_event=" + date_debut_event + ", date_fin_event=" + date_fin_event + ", nb_place_event=" + nb_place_event + ", photo=" + photo + '}';
    }

  

    public void setImage(String image) {
        this.image = image;
    }

    
    
    
    
}
