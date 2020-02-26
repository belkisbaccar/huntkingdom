/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reclamation.Entity;

import java.util.Date;
;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.ImageView;

/**
 *
 * @author belkis
 */
public class Complaints {
    int id_reclamation;
    String etat;
    String description;
    String image;
    int note;
    user u;  // le createur de la reclamation
    Date date;
    Annonce a;
    Produitentity p;
    ImageView j;
    String sujet;
    int id_obj;
  
    

    public Complaints(int id_reclamation, String etat, Date date) {
        this.id_reclamation = id_reclamation;
        this.etat = etat;
        this.date = date;
    }
 
    

   
 
     public Complaints(String etat, String description, String image, int note,Annonce a,user u, Date date,Produitentity p ) {
        this.etat =  etat;
        this.description = description;
        this.image = image;
        this.note = note;
        this.a = a;
        this.u=u;
        this.date =  date;
        this.p=p;
        
    }

    public Complaints(){
        
    }
    public Complaints(int id_reclamation, String etat, String description, String image, int note,user u, Date date ,Annonce a  ,Produitentity p) {
        this.id_reclamation = id_reclamation;
        this.etat =  etat;
        this.description = description;
        this.image = image;
        this.note = note;
        this.u = u;
        this.date =date;
        this.a = a;
        this.p=p;
           }
 public Complaints(int id_reclamation, String etat, String description, String image, int note,user u, Date date ,Annonce a ) {
        this.id_reclamation = id_reclamation;
        this.etat =  etat;
        this.description = description;
        this.image = image;
        this.note = note;
        this.u = u;
        this.date =date;
        this.a = a;
           }
    public Complaints(String etat,Date date) {
        this.etat =  etat;
        this.date = date;
    }

    public Complaints(int id_reclamation, String etat, String description, String image, int note, Annonce a,user u, Date date,Produitentity p) {
        this.id_reclamation = id_reclamation;
        this.etat =  etat;
        this.description = description;
        this.image = image;
        this.note = note;
      this.a =a;
        this.u=u;
        this.date = date;
        this.p=p;
        
        
    }
    
    public Complaints(int id_reclamation, String etat, String description, String image, int note, Annonce a,user u, Date date) {
        this.id_reclamation = id_reclamation;
        this.etat =  etat;
        this.description = description;
        this.image = image;
        this.note = note;
      this.a =a;
        this.u=u;
        this.date = date;
        
        
    }
    
    public Complaints(int id_reclamation, String etat, String description, String image, int note, Annonce a,user u, Date date,ImageView j){
        this.etat =  etat;
        this.description = description;
        this.image = image;
        this.note = note;
      this.a =a;
        this.u=u;
        this.date = date;
        this.j=j;
        
        
        
    }
    
    public Complaints(int id_reclamation, String etat, String description, String image, int note, Produitentity p,user u, Date date,ImageView j){
        this.etat =  etat;
        this.description = description;
        this.image = image;
        this.note = note;
        this.p=p;
        this.u=u;
        this.date = date;
        this.j=j;
        
        
        
    }
    
    
     
    
    
     

    

    public Date getDate() {
        return date;
    }
    

    public int getId_reclamation() {
        return id_reclamation;
    }

    public String getEtat() {
        return etat;
    }

    

   
    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public int getNote() {
        return note;
    }

    public Annonce getA() {
        return a;
    }



    public user getU() {
        return u;
    }

    public ImageView getJ() {
        return j;
    }

    public String getSujet() {
        return sujet;
    }

    public Produitentity getP() {
        return p;
    }

   

    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public void setA(Annonce a) {
        this.a = a;
    }

    

    public void setU(user u) {
        this.u = u;
    }
   
    public void setDate(Date date) {
        this.date = date;
    }

    public void setJ(ImageView j) {
        this.j = j;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setP(Produitentity p) {
        this.p = p;
    }

    public int getId_obj() {
        return id_obj;
    }

    public void setId_obj(int id_obj) {
        this.id_obj = id_obj;
    }

    @Override
    public String toString() {
        return "Complaints{" + "id_reclamation=" + id_reclamation + ", etat=" + etat + ", description=" + description + ", image=" + image + ", note=" + note + ", u=" + u + ", date=" + date + ", a=" + a + ", p=" + p + ", j=" + j + ", sujet=" + sujet + ", id_obj=" + id_obj + '}';
    }

    

   

    

    

    
    

   
    
   
    
    
    
}
