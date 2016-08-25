/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CoreClasses;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Abdullah
 */
abstract public class User {
    protected String userID, name, email,contactNumber,password,userType;
    
    public int login(String email, String password){
        java.sql.Connection conn = DBConnector.getDBConnection();
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

        return -1;
    }
    
    public boolean register(String name, String email, String contactNumber, String password, String userType){
        return false;
    }
}
