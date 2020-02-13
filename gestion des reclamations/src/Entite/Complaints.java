/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

import java.util.Date;

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
    String type;
    int user_id;
    Date date;

   
    public Complaints(String etat, String description, String image, int note, String type, int user_id, Date date ) {
        this.etat = etat;
        this.description = description;
        this.image = image;
        this.note = note;
        this.type = type;
        this.user_id = user_id;
        this.date =  date;
    }

    public Complaints(int id_reclamation, String etat, String description, String image, int note, String type, int user_id, Date date) {
        this.id_reclamation = id_reclamation;
        this.etat = etat;
        this.description = description;
        this.image = image;
        this.note = note;
        this.type = type;
        this.user_id = user_id;
        this.date =date;
    }

    public Complaints(String etat,Date date) {
        this.etat = etat;
        this.date = date;
    }

    public Complaints(int id_reclamation, String etat, String description, String image, int note, String type, Date date) {
        this.id_reclamation = id_reclamation;
        this.etat = etat;
        this.description = description;
        this.image = image;
        this.note = note;
        this.type = type;
        this.date = date;
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

    public String getType() {
        return type;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    public void setEtat(String etat) {
        this.etat = etat;
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

    public void setType(String type) {
        this.type = type;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Complaints{" + "id_reclamation=" + id_reclamation + ", etat=" + etat + ", description=" + description + ", image=" + image + ", note=" + note + ", type=" + type + ", user_id=" + user_id + ", date=" + date + '}';
    }

   
    
    
    
}
