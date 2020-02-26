/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.Entity;

/**
 *
 * @author LENOVO
 */
public class Demande {
     private int id_demande;
     private int id;
      private user user_id;
      private String nom; 
      private String prenom;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Demande(int id_demande, int id, String nom, String prenom) {
        this.id_demande = id_demande;
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public Demande(int id_demande, user user_id, String nom, String prenom) {
        this.id_demande = id_demande;
        this.user_id = user_id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public Demande(user user_id, String nom, String prenom) {
        this.user_id = user_id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public Demande(int id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }
      

    public Demande(int id_demande, user user_id) {
        this.id_demande = id_demande;
        this.user_id = user_id;
    }

    public Demande() {
    }

    public Demande(int id_demande) {
        this.id_demande = id_demande;
    }

    public Demande(user user_id) {
        this.user_id = user_id;
    }

    public int getId_demande() {
        return id_demande;
    }

    public user getUser_id() {
        return user_id;
    }

    public void setId_demande(int id_demande) {
        this.id_demande = id_demande;
    }

    public void setUser_id(user user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Demande{" + "id_demande=" + id_demande + ", user_id=" + user_id + ", nom=" + nom + ", prenom=" + prenom + '}';
    }

   

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
      
}
