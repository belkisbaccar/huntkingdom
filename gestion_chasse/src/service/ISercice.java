/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Chasse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AHMED
 */
public interface ISercice<T> {

    void insert(T t);

    void update(Chasse t);

    void delete(String animal);

    List<T> displayAll();
//    public T triParId(int id);

    T recherche(String animal);

     List<T> triByDate();
    
    void Stat();

}
