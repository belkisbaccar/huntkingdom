/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;
//import java.util.Date;

/**
 *
 * @author AHMED
 */
public class Chasse {
    private int id;
    private String animal;
    private String saison;
    private String region;
    private Date date_debut;
     private Date date_fin;

    public Chasse(int id, String animal, String saison, String region, Date date_debut, Date date_fin) {
        this.id = id;
        this.animal= animal;
        this.saison = saison;
        this.region = region;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public Chasse(String animal, String saison, String region, Date date_debut, Date date_fin) {
        this.animal = animal;
        this.saison = saison;
        this.region = region;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public Chasse() {
        
    }



    public int getId() {
        return id;
    }

    public String getAnimal() {
        return animal;
    }

    public String getSaison() {
        return saison;
    }

    public String getRegion() {
        return region;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAnimal(String animal) {
        this.animal= animal;
    }

    public void setSaison(String saison) {
        this.saison = saison;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    @Override
    public String toString() {
        return "Chasse{" + "id=" + id + ", animal=" + animal + ", saison=" + saison + ", region=" + region + ", date_debut=" + date_debut + ", date_fin=" + date_fin + '}';
    }
    

}