/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author LENOVO
 */
public class commentaire {
     private int id_commentaire;
      private int user_id;
    private int id_annonce;
      private String comentaire;

    public commentaire(String comentaire) {
        this.comentaire = comentaire;
    }

    public commentaire(int id_commentaire, int user_id, int id_annonce, String comentaire) {
        this.id_commentaire = id_commentaire;
        this.user_id = user_id;
        this.id_annonce = id_annonce;
        this.comentaire = comentaire;
    }

    public commentaire() {
    }

    public int getId_commentaire() {
        return id_commentaire;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getId_annonce() {
        return id_annonce;
    }

    public String getComentaire() {
        return comentaire;
    }

    public void setId_commentaire(int id_commentaire) {
        this.id_commentaire = id_commentaire;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setId_annonce(int id_annonce) {
        this.id_annonce = id_annonce;
    }

    public void setComentaire(String comentaire) {
        this.comentaire = comentaire;
    }

    @Override
    public String toString() {
        return "commentaire{" + "id_commentaire=" + id_commentaire + ", user_id=" + user_id + ", id_annonce=" + id_annonce + ", comentaire=" + comentaire + '}';
    }
      
   
   
}
