/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CoreClasses;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Abdullah
 */
public class Order {
    int orderID;
    Customer c = new Customer();
    java.sql.Connection conn = DBConnector.getDBConnection();
    
    public void placeOrder(double grossTotal, double netTotal,double tax, String orderType, String deliveryMode, String street, String city, String postCode){
        
        ResultSet rs = null;
        try{
            String sql= "INSERT INTO `sql6131484`.`Order` ( order_date, gross_total, tax, net_total, order_type, deliveryMode, user_id) VALUES ( ?, ?, ?, ?, ?, ?, ? ); ";
            
            PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, ""+getTodayDate());
            ps.setString(2, ""+grossTotal);
            ps.setString(3, ""+tax);
            ps.setString(4, ""+netTotal);
            ps.setString(5, orderType);
            ps.setString(6, deliveryMode);
            ps.setString(7, ""+c.getCurrentCustomerID());
            
            ps.executeUpdate();
            ResultSet keyResultSet = ps.getGeneratedKeys();
            if (keyResultSet.next()) {
                this.orderID = keyResultSet.getInt(1);
            }
            
            
            addToOrderProduct();
            
            if(deliveryMode.equals("Delivery")){
                Delivery d = new Delivery();
                d.addDeliveryAddress(street, city, postCode, this.orderID);
            }
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    public void addToOrderProduct(){
        ResultSet rs = null;
        //Get all items from shopping cart and add each item to order_product table
        try{
            String sql= "SELECT  supplier_id, product_id, quantity " +
                        "FROM ShoppingCartItem " +
                        "WHERE user_id = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, c.getCurrentCustomerID());
            rs = ps.executeQuery();
            
            while(rs.next()){
                try{
                    String sql2= "INSERT INTO Order_Product (order_id, supplier_id, product_id, quantity, deliver_status, collection_status) VALUES (?, ?, ?, ?, ?, ?) ";
                    PreparedStatement ps2 = conn.prepareStatement(sql2);
                    System.out.println("orderID : "+this.orderID);
                    ps2.setInt(1, this.orderID);
                    ps2.setInt(2, rs.getInt(1));
                    ps2.setInt(3, rs.getInt(2));
                    ps2.setInt(4, rs.getInt(3));
                    ps2.setString(5, "Pending");
                    ps2.setString(6, "Pending");

                    ps2.executeUpdate();

                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(null,e);
                }
            }
            

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
    }
    
    
    
    public java.sql.Date getTodayDate() throws ParseException{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String today = dateFormat.format(date);
        Date today2 = dateFormat.parse(today);
        java.sql.Date sqlDate = new java.sql.Date(today2.getTime());
        return sqlDate;
    }
    
    
    public ResultSet getAllOrders(){
        ResultSet rs = null;
        try{
             String sql= "SELECT o.order_id, o.order_date ,u.email, p.name, s.name, op.quantity, op.collection_status, op.deliver_status, o.deliveryMode " +
                        "FROM `sql6131484`.`Order_Product` op " +
                        "JOIN `sql6131484`.`Order` o on op.order_id = o.order_id "+
                        "JOIN `sql6131484`.`Product` p on op.product_id = p.product_id "+
                        "JOIN `sql6131484`.`Supplier` s on op.supplier_id = s.supplier_id "+
                        "JOIN `sql6131484`.`User` u on o.user_id = u.user_id  ORDER BY o.order_date DESC";
            PreparedStatement ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        return rs;
        
        
    }
}
