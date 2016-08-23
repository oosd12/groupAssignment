/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CoreClasses;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.Statement;
/**
 *
 * @author Abdullah
 */
public class Customer extends User{
    
    Statement stmt;
    private static String currentCustomer;
    
    public boolean isUserExist(String Email){
        boolean flag = false;
        
        java.sql.Connection conn = new DBConnector().connect();
        try{
            String sql= "SELECT * " +
                        "FROM `User` u"+
                        "WHERE email = "+Email+"";
            stmt=conn.createStatement();
            ResultSet Rs = stmt.executeQuery(sql);
            while(Rs.next()){
                flag = true;
            }
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeQuery();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        return flag;
    }
    
    public boolean insertUser(String UserID,String Name,String Email,String Street,String City,String Postcode,String ContactNo,String Password){
        boolean flag = false;
        java.sql.Connection conn = new DBConnector().connect();
        String sql = "Insert Into `User` u values ('"+UserID+"','"+Name+"','"+Email+"','"+Street+"','"+City+"','"+Postcode+"','"+ContactNo+"','"+Password+"')";
        
        try {
            stmt=conn.createStatement();
            if(stmt.executeUpdate(sql)>0){
                flag = true;
            }
        } catch (SQLException ex) {
            
        }
        return flag;
    }
    
    public String getCurrentCustomerName(){
        ResultSet rs = null;
        java.sql.Connection conn = new DBConnector().connect();
        try{
            String sql= "SELECT name FROM User WHERE email = ?  ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ""+getCurrentCustomer());
            rs = ps.executeQuery();
            if(rs.next()){
                return rs.getString(1);
            }
 
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }

        return "CUSTOMER_NAME";
    }
    
    public int getCurrentCustomerID(){
        ResultSet rs = null;
        java.sql.Connection conn = new DBConnector().connect();
        try{
            String sql= "SELECT user_id FROM User WHERE email = ?  ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ""+getCurrentCustomer());
            rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
 
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }

        return 0;
    }
    
    public static String getCurrentCustomer() {
        return currentCustomer;
    }

    public static void setCurrentCustomer(String currentCustomer) {
        Customer.currentCustomer = currentCustomer;
    }
    
    
    
    
}
