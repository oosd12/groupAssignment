/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Abdullah
 */
public class DBConnector {
     private com.mysql.jdbc.Connection DBConnection;
    public com.mysql.jdbc.Connection connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connection successful");
        }
        catch(ClassNotFoundException cnfe){
            System.out.println("Connection failed" + cnfe);
        }
        String url = "jdbc:mysql://sql6.freesqldatabase.com:3306/sql6131484";
        try{
            DBConnection = (com.mysql.jdbc.Connection) DriverManager.getConnection(url, "sql6131484", "cRzv6DYZaP");
            System.out.println("Database Connected");
        }
        catch(SQLException se){
            System.out.println("Database Not Found " + se);
        }
        return DBConnection;
    }
}

//I like coffee

//I like tea

