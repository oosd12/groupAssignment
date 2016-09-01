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
    java.sql.Connection conn = DBConnector.getDBConnection();
    
    Statement stmt;
    private static String currentCustomer = "";
    
    public boolean isCustomerExist(String Email){
        boolean flag = false;
        
        try{
            String sql= "SELECT * " +
                        "FROM User "+
                        "WHERE email = '"+Email+"' ";
            stmt=conn.createStatement();
            ResultSet Rs = stmt.executeQuery(sql);
            if(Rs.next()){
                flag = true;
            }
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.executeQuery();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        return flag;
    }

   
   
    public String getCurrentCustomerName(){
        ResultSet rs = null;
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

    public static void setCurrentCustomer(String email) {
        Customer.currentCustomer = email;
    }
    
    public String[] getPersonalDetails(){
        String[] personalDetails = new String[3];
        ResultSet rs = null;
        
        try{
            String sql= "SELECT name,contact_number FROM User WHERE email = ?  ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, getCurrentCustomer());
            rs = ps.executeQuery();
            if(rs.next()){
               personalDetails[0] = rs.getString(1);
               personalDetails[1] = rs.getString(2);
               personalDetails[2] = getCurrentCustomer();
               
               return personalDetails;
            }
 
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        return personalDetails;
    }
    
    
    
    
}
