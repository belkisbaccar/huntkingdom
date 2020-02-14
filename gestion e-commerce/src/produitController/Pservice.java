/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produitController;

import java.util.List;

/**
 *
 * @author azizm
 */
public interface Pservice <T>{
     void insert(T t);
    void update(T t);
    int delete(int id);
    List<T>displayAll();
   void réduire_quantité(T t);
    
    
}
