/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.Service;

import user.Entity.user;
import connection.Datasource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;

/**
 *
 * @author LENOVO
 */
public class UserSession {
    private static UserSession instance;
 
    private String userName; 
    private String photo;

   
   

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoto() {
        return photo;
    }

   

   
    public UserSession(String userName) {
        this.userName = userName;
    }

    public static UserSession getInstance() {
        return instance;
    }

    public String getUserName() {
        return userName;
    }

    public static void setInstance(UserSession instance) {
        UserSession.instance = instance; 
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserSession(String userName, String photo) {
        this.userName = userName;
        this.photo = photo;
        
    }

    @Override
    public String toString() {
        return "UserSession{" + "userName=" + userName + ", photo=" + photo + '}';
    }

    
    
    public static UserSession getInstace(String userName, String photo) {
        if(instance == null) {
         instance = new UserSession(userName,photo);
        }
        return instance;
    }   
    
     public void cleanUserSession() {
        userName = "";
        photo = null;//7 or null
      instance = null;
        
       // or null
    }  
     
     
     
     
    
      
      
      
}
