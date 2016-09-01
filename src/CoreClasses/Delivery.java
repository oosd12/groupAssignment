/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CoreClasses;

import static CoreClasses.Customer.getCurrentCustomer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Abdullah
 */
public class Delivery {
    Customer c = new Customer();
    java.sql.Connection conn = DBConnector.getDBConnection();
    
    public void addDeliveryAddress(String street, String city, String postCode, int orderID){
        
        try{
            String sql= "INSERT INTO DeliveryAddress (street, city, post_code, order_id) VALUES (?, ?, ?, ?) ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, street);
            ps.setString(2, city);
            ps.setString(3, postCode);
            ps.setInt(4, orderID);
            
            ps.executeUpdate();

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    //Getting the last delivery address used by the current customer
    public String[] getLastDeliveryAddress(){
        String[] address = new String[3];
        ResultSet rs = null;
        try{
            String sql= "SELECT da.street, da.city, da.post_code " +
                        "FROM DeliveryAddress da " +
                        "JOIN `sql6131484`.`Order` o on da.order_id = o.order_id "+
                        "WHERE o.user_id = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, c.getCurrentCustomerID());
            rs = ps.executeQuery();
            if(rs.next()){
               address[0] = rs.getString(1);
               address[1] = rs.getString(2);
               address[2] = rs.getString(3);
               
               return address;
            }
 
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        return address;
    }
}
