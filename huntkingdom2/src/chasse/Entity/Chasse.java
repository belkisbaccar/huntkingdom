/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chasse.Entity;

import java.sql.Date;
import java.time.LocalDate;
//import java.util.Date;

/**
 *
 * @author AHMED
 */
public class Chasse {
    private int id;
    private Animal animal;
    //private String saison;
    private String region;
    private int idproduit;
    private Date date_debut;
     private Date date_fin;
     private Type_animal type;
     private int id_user;
     Produitentity name;
     Produitentity produit;
     

    public Chasse(int id, Animal animal, String region, Date date_debut, Date date_fin, Type_animal type, int id_user,  Produitentity produit) {
        this.id = id;
        this.animal = animal;
        this.region = region;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.type = type;
        this.id_user = id_user;
        
        this.produit = produit;
    }

    public Produitentity getProduit() {
        return produit;
    }

    public void setProduit(Produitentity produit) {
        this.produit = produit;
    }

    public Chasse(int id, Animal animal, String region, Date date_debut, Date date_fin, Type_animal type, int id_user, Produitentity name, Produitentity produit) {
        this.id = id;
        this.animal = animal;
        this.region = region;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.type = type;
        this.id_user = id_user;
        this.name = name;
        this.produit = produit;
    }
     


    public Chasse(int id,  Type_animal type ,Animal animal,  String region, Date date_debut, Date date_fin) {
        this.id = id;
        this.animal= animal;
        //this.saison = saison;
        this.region = region;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.type= type;
    }

    public Chasse(Animal animal,Type_animal type, String region, Date date_debut, Date date_fin) {
        this.animal = animal;
        //this.saison = saison;
        this.region = region;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.type= type;
    }

    public Chasse() {
        
    }

    public Chasse(int id, Animal animal, String region, Date date_debut, Date date_fin, Type_animal type, int id_user) {
        this.id = id;
        this.animal = animal;
        this.region = region;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.type = type;
        this.id_user = id_user;
    }

    public Chasse(Animal animal, String region, Date date_debut, Date date_fin, Type_animal type) {
        this.animal = animal;
        this.region = region;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.type = type;
    }

    public int getIdproduit() {
        return idproduit;
    }

    public void setIdproduit(int idproduit) {
        this.idproduit = idproduit;
    }



    public int getId() {
        return id;
    }

    public Animal getAnimal() {
        return animal;  
    }

//    public String getSaison() {
//        return saison;
//    }

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

    public void setAnimal(Animal animal) {
        this.animal= animal;
    }

//    public void setSaison(String saison) {
//        this.saison = saison;
//    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public Type_animal getType() {

        return type;
    }

    public void setType(Type_animal type) {
        this.type = type;
    }

    public int getId_user() {
        return id_user;
    }

       public void setId_user(int id_user) {
        this.id_user = id_user;
    }
       
    public Chasse( Animal animal, String region, Date date_debut, Date date_fin, Type_animal type,  Produitentity produit) {

        this.animal = animal;
        this.region = region;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.type = type;
       
        
        this.produit = produit;
    }

    @Override
    public String toString() {
        return "Chasse{" + "id=" + id + ", animal=" + animal + ", region=" + region + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", type=" + type + ", id_user=" + id_user + ", produit=" + produit + '}';
    }




    

  


    

}