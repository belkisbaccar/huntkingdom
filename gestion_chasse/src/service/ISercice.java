/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Animal;
import entity.Chasse;
import entity.Type_animal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AHMED
 */
public interface ISercice<T> {

    //void insert(T t);

    void update(int id,String region,Animal animal,Type_animal type,Date date_debut,Date date_fin);

    void delete(Animal animal);

    List<T> displayAll();
// public T triParId(int id);

    T recherche(String animal);

     List<T> triByDate();
    
    void Stat();

}
