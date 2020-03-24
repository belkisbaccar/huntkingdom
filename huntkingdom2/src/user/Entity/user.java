/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.Entity;

import javafx.scene.image.ImageView;

/**
 *
 * @author belkis
 */
public class user {

    int id_user;
    private String nom;
    private String prenom;
    private int age;
    private String sexe;
    private String mot_de_passe;
    private String photo;
    private String role;
    private String username;
    private int telephone;
    private String email;
    private ImageView img;

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    public user(String nom, String prenom, String mot_de_passe, String photo, String username, int telephone, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.mot_de_passe = mot_de_passe;
        this.photo = photo;
        this.username = username;
        this.telephone = telephone;
        this.email = email;
    }

    public user(String nom, String prenom, int age, String sexe, String mot_de_passe, String photo, String role, String username, int telephone, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.sexe = sexe;
        this.mot_de_passe = mot_de_passe;
        this.photo = photo;
        this.role = role;
        this.username = username;
        this.telephone = telephone;
        this.email = email;
    }

    public user(String nom, String prenom, int age, String sexe, String mot_de_passe, String photo, String role, String username, int telephone, String email, ImageView img) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.sexe = sexe;
        this.mot_de_passe = mot_de_passe;
        this.photo = photo;
        this.role = role;
        this.username = username;
        this.telephone = telephone;
        this.email = email;
        this.img = img;
    }

    public user(int id_user, String nom, String prenom, int age, String sexe, String mot_de_passe, String photo, String role, String username, int telephone, String email, ImageView img) {
        this.id_user = id_user;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.sexe = sexe;
        this.mot_de_passe = mot_de_passe;
        this.photo = photo;
        this.role = role;
        this.username = username;
        this.telephone = telephone;
        this.email = email;
        this.img = img;
    }

    public user(String username) {
        this.username = username;
    }

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

    public user(int id_user, String nom, String prenom, int age, String sexe, String mot_de_passe, String photo, String role , String username) {
        this.id_user = id_user;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.sexe = sexe;
        this.mot_de_passe = mot_de_passe;
        this.photo = photo;
        this.role = role;
        this.username = username;
    }
public user(int id_user, String nom, String prenom, int age, String sexe, String mot_de_passe, String photo, String role , String username,ImageView img) {
        this.id_user = id_user;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.sexe = sexe;
        this.mot_de_passe = mot_de_passe;
        this.photo = photo;
        this.role = role;
        this.username = username;
        this.img=img;
    }
    public user(String mot_de_passe, String photo) {
        this.mot_de_passe = mot_de_passe;
        this.photo = photo;
    }

    public user() {
    }

    public user(String nom, String prenom, int age, String sexe, String mot_de_passe, String photo, String role, String username) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.sexe = sexe;
        this.mot_de_passe = mot_de_passe;
        this.photo = photo;
        this.role = role;
        this.username = username;
    }

    public user(String nom, String prenom, int age, String mot_de_passe, String photo) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.mot_de_passe = mot_de_passe;
        this.photo = photo;
    }

    
    public user(int id_user, String nom, String prenom) {
        this.id_user = id_user;
        this.nom = nom;
        this.prenom = prenom;
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

    public String getUsername() {
        return username;
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

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "user{" + "id_user=" + id_user + ", nom=" + nom + ", prenom=" + prenom + ", age=" + age + ", sexe=" + sexe + ", mot_de_passe=" + mot_de_passe + ", photo=" + photo + ", role=" + role + ", username=" + username + ", telephone=" + telephone + ", email=" + email + ", img=" + img + '}';
    }

    
}
