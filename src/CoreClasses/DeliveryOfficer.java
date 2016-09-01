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
public class DeliveryOfficer extends Officer{
    java.sql.Connection conn = DBConnector.getDBConnection();
    public ResultSet displayDelivery(){
        ResultSet rs = null;
        
        try{
            String sql= "SELECT Order_id,order_date,gross_total,tax,net_total,deliveryMode FROM `Order` WHERE (deliveryMode = 'Delivery')";
            PreparedStatement ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        return rs;
    }
    
        public void updateDelivery(String OrderID,String delivery_status){
        
        try{
            String sql= "INSERT INTO Order_Product (delivery_status) WHERE order_id = '"+OrderID+"' VALUES ('"+delivery_status+"') ";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, delivery_status);
       
            ps.executeUpdate();

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
    }
        
        
    @Override
    public ResultSet viewRoute() {
        ResultSet rs = null;
        
        try{
             String sql= "SELECT od.order_id, u.name, u.contact_number,da.street, da.city, da.post_code, p.name, od.quantity  " +
                        "FROM `sql6131484`.`Order_Product` od " +
                        "JOIN `sql6131484`.`DeliveryAddress` da on op.order_id = da.order_id "+
                        "JOIN `sql6131484`.`Product` p on od.product_id = p.product_id "+
                        "JOIN `sql6131484`.`Order` o on od.order_id = o.order_id "+
                        "JOIN `sql6131484`.`User` u on od.product_id = p.product_id "+
                        "WHERE od.deliver_status = 'Pending' ";
            PreparedStatement ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        return rs;
    }
}
