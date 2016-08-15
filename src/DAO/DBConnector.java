/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Abdullah
 */
public class DBConnector {
    private Connection DBConnection;
    public Connection connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connection successful");
        }
        catch(ClassNotFoundException cnfe){
            System.out.println("Connection failed" + cnfe);
        }
        String url = "jdbc:mysql://localhost:3306/bookstore";
        try{
            DBConnection = (Connection) DriverManager.getConnection(url, "root", "");
            System.out.println("Database Connected");
        }
        catch(SQLException se){
            System.out.println("Database Not Found " + se);
        }
        return DBConnection;
    }
}

//EDITING ONLINE
