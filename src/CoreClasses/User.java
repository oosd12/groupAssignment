/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CoreClasses;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Abdullah
 */
abstract public class User {
    protected String userID, name, email,contactNumber,password,userType;
    java.sql.Connection conn = DBConnector.getDBConnection();
    
    public int login(String email, String password){
        
        ResultSet rs = null;
        try{
            String sql= "SELECT user_type FROM User WHERE email = ? AND password = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ""+email);
            ps.setString(2, ""+password);
            rs = ps.executeQuery();
            
            while(rs.next()){
                switch (rs.getString(1)) {
                    case "Customer":
                        return 1;
                    case "Administrator":
                        return 2;
                    case "Delivery Officer":
                        return 3;
                    case "Collection Officer":
                        return 4;
                    default:
                        return 0;
                }
            }
           
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        //if no result is found/invalid credetials return -1
        return -1;
    }
    
    public void register(String name, String email, String contactNumber, String password, String userType){
        ResultSet rs = null;
        
        try {
             String sql = " INSERT INTO sql6131484.`User` (`name`, `email`, `user_type`, `password`, `contact_number`) " +
                          " VALUES (?, ?, ?, ?, ? ) ";
            PreparedStatement ps=conn.prepareStatement(sql);
            System.out.println(""+ps);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, userType);
            ps.setString(4, password);
            ps.setString(5, contactNumber);
            
            ps.executeUpdate();
            

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
        }

    }
    
    //for use when placing a phone order 
    public int getUserType(String email){
        java.sql.Connection conn = DBConnector.getDBConnection();
        ResultSet rs = null;
        try{
            String sql= "SELECT user_type FROM User WHERE email = ?  ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ""+email);

            rs = ps.executeQuery();
            
            while(rs.next()){
                switch (rs.getString(1)) {
                    case "Customer":
                        return 1;
                    case "Administrator":
                        return 2;
                    case "Delivery Officer":
                        return 3;
                    case "Collection Officer":
                        return 4;
                    default:
                        return 0;
                }
            }
           
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }

        return -1;
    }
    
    
    ArrayList<Message> Messages = new ArrayList<>();
    
}
