/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chasse.Service;

import chasse.Entity.Animal;
import chasse.Entity.Chasse;
import chasse.Entity.Produitentity;
import chasse.Entity.Type_animal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AHMED
 */
public interface ISercice<T> {

    //void insert(T t);

    void update(int id,String region,Animal animal,Type_animal type,Date date_debut,Date date_fin,Produitentity idp);

    void delete(int d);

    List<T> displayAll();
// public T triParId(int id);

    T recherche(String animal);

     List<T> triByDate();
    
    void Stat();

}
