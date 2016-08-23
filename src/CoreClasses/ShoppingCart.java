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
public class ShoppingCart {
    Customer c  = new Customer();
    Product p = new Product();
    
    public void addToCart(int productID, int supplierID, int quantity){
        ResultSet rs = null;
        java.sql.Connection conn = new DBConnector().connect();
        try{
            String sql= "INSERT INTO ShoppingCartItem (user_id, supplier_id, product_id, quantity) VALUES (?, ?, ?, ?) ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, c.getCurrentCustomerID());
            ps.setInt(2, supplierID);
            ps.setInt(3, productID);
            ps.setInt(4, quantity);
            
            ps.executeUpdate();

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        p.adjustQuantity("Decrease", quantity, supplierID, productID);
    }
    
    public ResultSet getCartItems(){
        ResultSet rs = null;
        java.sql.Connection conn = new DBConnector().connect();
        try{
            String sql= "SELECT sci.product_id, sci.supplier_id, p.name,s.name, sci.quantity, sp.production_date, sp.price, (sci.quantity * sp.price) AS NewColumn " +
                        "FROM ShoppingCartItem sci " +
                        "JOIN Supplier s on sci.supplier_id = s.supplier_id "+
                        "JOIN Product p on sci.product_id = p.product_id "+
                        "JOIN Supplier_Product sp on sci.product_id = sp.product_id AND sci.supplier_id = sp.supplier_id "+
                        "WHERE sci.user_id = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, c.getCurrentCustomerID());
            rs = ps.executeQuery();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        return rs;
    }
}
