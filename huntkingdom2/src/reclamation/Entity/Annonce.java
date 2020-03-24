/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reclamation.Entity;

/**
 *
 * @author belkis
 */
public class Annonce {
    private int id_annonce;
    private String text;
    private String image;
    private int user_id;
    

    public Annonce(String text, String image) {
        this.text = text;
        this.image = image;
    }

    public Annonce(int id_annonce, String text, String image, int user_id) {
        this.id_annonce = id_annonce;
        this.text = text;
        this.image = image;
        this.user_id = user_id;
      

    }

    public Annonce() {
    }

    public Annonce(String text, String image, int user_id) {
        this.text = text;
        this.image = image;
        this.user_id = user_id;

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

    public int getUser_id() {
        return user_id;
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

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

//    @Override
//    public String toString() {
//        return ""+ id_annonce + "" ;
//    }

    @Override
    public String toString() {
        return "" + id_annonce + "" ;
    }
    
    

}
