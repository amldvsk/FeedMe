/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedme.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author NadavBismuth
 */
public class DbConnector {
    
    private static DbConnector instance = null;
    private Connection conn = null;
    private static final String driverName = "com.mysql.jdbc.Driver"; 
    private static final String url = "jdbc:mysql://localhost/feedmedb";
    
    private DbConnector()
    {
        try {
             Class.forName(driverName);
             String  userName = "root";
             String password = "123456789";
             conn = DriverManager.getConnection(url, userName, password);
        
        } catch (ClassNotFoundException | SQLException ex) {
            
            Logger.getLogger(DbConnector.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Db Connection Constructor exception  Mother Fucker");
            
        }
        
    }
    
    public static DbConnector getInstance()
    {
        if(instance == null)
        {
            instance = new DbConnector();
        }
        
        return instance;
    }
    
    public Connection getConn()
    {
        return conn;
    }
    
 
    
    
}
