/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chasse.Entity;

/**
 *
 * @author LENOVO
 */
public class user {
     private int id_user;
   private String nom ;
   private String prenom ;
      private int age;
     private String sexe;
       private String mot_de_passe ;
        private String photo; 
      private String role; 

    public user(int id_user, String nom, String prenom, int age, String sexe, String mot_de_passe, String photo, String role) {
        this.id_user = id_user;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.sexe = sexe;
        this.mot_de_passe = mot_de_passe;
        this.photo = photo;
        this.role = role;
    }

    public user(String mot_de_passe, String photo) {
        this.mot_de_passe = mot_de_passe;
        this.photo = photo;
    }

    public user() {
    }

    public user(String nom, String prenom, int age, String sexe, String mot_de_passe, String photo, String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.sexe = sexe;
        this.mot_de_passe = mot_de_passe;
        this.photo = photo;
        this.role = role;
    }

    public int getId_user() {
        return id_user;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getAge() {
        return age;
    }

    public String getSexe() {
        return sexe;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public String getPhoto() {
        return photo;
    }

    public String getRole() {
        return role;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "user{" + "id_user=" + id_user + ", nom=" + nom + ", prenom=" + prenom + ", age=" + age + ", sexe=" + sexe + ", mot_de_passe=" + mot_de_passe + ", photo=" + photo + ", role=" + role + '}';
    }
}
