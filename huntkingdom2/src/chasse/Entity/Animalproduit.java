/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chasse.Entity;

import java.sql.Date;

/**
 *
 * @author AHMED
 */
public class Animalproduit {
    private Animal animal;
    //private String saison;
    private String region;
    private Date date_debut;
     private Date date_fin;
     private Type_animal type;
         private  String nomP;

    public Animalproduit() {
    }

    public Animalproduit(Animal animal, String region, Date date_debut, Date date_fin, Type_animal type, String nomP) {
        this.animal = animal;
        this.region = region;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.type = type;
        this.nomP = nomP;
    }
    

    public Animal getAnimal() {
        return animal;
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

    public Type_animal getType() {
        return type;
    }

    public String getNomP() {
        return nomP;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
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

    public void setType(Type_animal type) {
        this.type = type;
    }

    public void setNomP(String nomP) {
        this.nomP = nomP;
    }

    @Override
    public String toString() {
        return "Animalproduit{" + "animal=" + animal + ", region=" + region + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", type=" + type + ", nomP=" + nomP + '}';
    }

}
