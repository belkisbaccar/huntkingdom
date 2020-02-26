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
public class Annonce {
    private int id_annonce;
    private String text;
    private String image;
    private user user;
    private int aime; 
    private String a;
  private ImageView img; 
  private int etat;

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getEtat() {
        return etat;
    }

    public Annonce(int id_annonce, user user, int etat) {
        this.id_annonce = id_annonce;
        this.user = user;
        this.etat = etat;
    }

    public Annonce(int id_annonce, user user, int aime, int etat) {
        this.id_annonce = id_annonce;
        this.user = user;
        this.aime = aime;
        this.etat = etat;
    }

    public Annonce(int id_annonce, String text, String image, String a, int aime, ImageView img) {
        this.id_annonce = id_annonce;
        this.text = text;
        this.image = image;
         this.a = a;
        this.aime = aime;
       
        this.img = img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

    public ImageView getImg() {
        return img;
    }

    
  
    public Annonce(int id_annonce, String text, String image, int aime, String a) {
        this.id_annonce = id_annonce;
        this.text = text;
        this.image = image;
        this.aime = aime;
        this.a = a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getA() {
        return a;
    }

    public Annonce() {
    }

    public Annonce(int id_annonce, int aime) {
        this.id_annonce = id_annonce;
        this.aime = aime;
    }

    public Annonce(String text, String image) {
        this.text = text;
        this.image = image;
    }

    public Annonce(String text, String image, user user, int aime) {
        this.text = text;
        this.image = image;
        this.user = user;
        this.aime = aime;
    }

    public Annonce(int id_annonce, String text, String image, user user, int aime) {
        this.id_annonce = id_annonce;
        this.text = text;
        this.image = image;
        this.user = user;
        this.aime = aime;
    }

    public void setAime(int aime) {
        this.aime = aime;
    }

    public int getAime() {
        return aime;
    }

    public Annonce(int id_annonce, String text, String image, user user) {
        this.id_annonce = id_annonce;
        this.text = text;
        this.image = image;
        this.user = user;
    }

    public Annonce(String text, String image, user user) {
        this.text = text;
        this.image = image;
        this.user = user;
    }

    public int getId_annonce() {
        return id_annonce;
    }

    public String getText() {
        return text;
    }

    public String getImage() {
        return image;
    }

    public user getUser() {
        return user;
    }

    public void setId_annonce(int id_annonce) {
        this.id_annonce = id_annonce;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setUser(user user) {
        this.user = user;
    }

    public Annonce(int id_annonce, String text, String image, user user, int aime, String a, int etat) {
        this.id_annonce = id_annonce;
        this.text = text;
        this.image = image;
        this.user = user;
        this.aime = aime;
        this.a = a;
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Annonce{" + "id_annonce=" + id_annonce + ", text=" + text + ", image=" + image + ", user=" + user + ", aime=" + aime + ", a=" + a + ", img=" + img + ", etat=" + etat + '}';
    }

  
    
    
    
    
    
    

   
}
