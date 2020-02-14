/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasource;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author azizm
 */
public class Datasource {
    private String url="jdbc:mysql://127.0.0.1/esprit";
    private String userName="root";
    private String password="";
    private Connection cnx;
    private static Datasource instance;
    public Datasource()
    {
        try {
            cnx=DriverManager.getConnection(url, userName, password);
            System.out.println("succée");
        } catch (SQLException ex) {
            Logger.getLogger(Datasource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public static  Datasource getInstance()
    {
        if(instance==null)
            instance =new Datasource();
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }
    
    
}
