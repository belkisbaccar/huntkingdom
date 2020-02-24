/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publicite.Entity;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author walid
 */
public class Promotion {
    private int id_promotion;
    private int id_produit;
    private double taux;
    private Date date_debut;
    private Date date_fin;

    public Promotion(int id_promotion, int id_produit, double taux, Date date_debut, Date date_fin) {
        this.id_promotion = id_promotion;
        this.id_produit = id_produit;
        this.taux = taux;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }
     public Promotion( int id_produit, double taux, Date date_debut, Date date_fin) {
       
        this.id_produit = id_produit;
        this.taux = taux;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public int getId_promotion() {
        return id_promotion;
    }

    public int getId_produit() {
        return id_produit;
    }

    public double getTaux() {
        return taux;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setId_promotion(int id_promotion) {
        this.id_promotion = id_promotion;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.id_promotion;
        hash = 97 * hash + this.id_produit;
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.taux) ^ (Double.doubleToLongBits(this.taux) >>> 32));
        hash = 97 * hash + Objects.hashCode(this.date_debut);
        hash = 97 * hash + Objects.hashCode(this.date_fin);
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
        final Promotion other = (Promotion) obj;
        if (this.id_promotion != other.id_promotion) {
            return false;
        }
        if (this.id_produit != other.id_produit) {
            return false;
        }
        if (Double.doubleToLongBits(this.taux) != Double.doubleToLongBits(other.taux)) {
            return false;
        }
        if (!Objects.equals(this.date_debut, other.date_debut)) {
            return false;
        }
        if (!Objects.equals(this.date_fin, other.date_fin)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Promotion{" + "id_promotion=" + id_promotion + ", id_produit=" + id_produit + ", taux=" + taux + ", date_debut=" + date_debut + ", date_fin=" + date_fin + '}';
    }
    
}
