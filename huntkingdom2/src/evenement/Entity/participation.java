/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evenement.Entity;

import java.util.Date;
import java.util.Objects;


/**
 *
 * @author hazem
 */
public class participation {
    private int id_participation;
    private int id_user;
    private user user; 
    private String nom;
    private int id_event;
    private Date date_reservation;

    public participation(int id_participation, String nom, int id_event, Date date_reservation) {
        this.id_participation = id_participation;
        this.nom = nom;
        this.id_event = id_event;
        this.date_reservation = date_reservation;
    }

      public participation(user user,int id_event) {
       
    this.user = user;
        this.id_event = id_event;
        
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }
    
    public void setUser(user user) {
        this.user = user;
    }


    public user getUser() {
        return user;
    }

    public participation(user user) {
        this.user = user;
    }

    public participation(int id_participation, int id_user, user user, int id_event, Date date_reservation) {
        this.id_participation = id_participation;
        this.id_user = id_user;
        this.user = user;
        this.id_event = id_event;
        this.date_reservation = date_reservation;
    }

  

    public participation(int id_participation, int id_user, int id_event, Date date_reservation) {
        this.id_participation = id_participation;
        this.id_user = id_user;
        this.id_event = id_event;
        this.date_reservation = date_reservation;
    }
    public participation( int id_user, int id_event, Date date_reservation) {
        
        this.id_user = id_user;
        this.id_event = id_event;
        this.date_reservation = date_reservation;
    }

    @Override
    public String toString() {
        return "participation{" + "id_participation=" + id_participation + ", nom=" + nom + ", id_event=" + id_event + ", date_reservation=" + date_reservation + '}';
    }

   
    

    public int getId_participation() {
        return id_participation;
    }

    public int getId_user() {
        return id_user;
    }

    public int getId_event() {
        return id_event;
    }

    public Date getDate_reservation() {
        return date_reservation;
    }

    public void setId_participation(int id_participation) {
        this.id_participation = id_participation;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public void setDate_reservation(Date date_reservation) {
        this.date_reservation = date_reservation;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.id_participation;
        hash = 53 * hash + this.id_user;
        hash = 53 * hash + this.id_event;
        hash = 53 * hash + Objects.hashCode(this.date_reservation);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final participation other = (participation) obj;
        if (this.id_participation != other.id_participation) {
            return false;
        }
        if (this.id_user != other.id_user) {
            return false;
        }
        if (this.id_event != other.id_event) {
            return false;
        }
        if (!Objects.equals(this.date_reservation, other.date_reservation)) {
            return false;
        }
        return true;
    }
    
}
