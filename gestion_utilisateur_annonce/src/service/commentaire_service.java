/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;

/**
 *
 * @author LENOVO
 */
public interface commentaire_service <T> {
     void insert(T t); 
    void update(T t, int id); 
    void delete(int id); 
    List<T>displayAll();
}