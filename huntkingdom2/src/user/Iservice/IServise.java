/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.Iservice;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author belkis
 * @param <T>
 */
public interface IServise<T> {
    void ajouter(T t) throws SQLException;
    boolean delete(int id) throws SQLException;
    boolean update(T t,int id) throws SQLException;
    List<T> readAll() throws SQLException;
}