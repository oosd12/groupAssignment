/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CoreClasses;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Abdullah
 */
public class ShoppingCart {
    Customer c  = new Customer();
    Product p = new Product();
    
    java.sql.Connection conn = DBConnector.getDBConnection();
    
    
    public void addToCart(int productID, int supplierID, int quantity){
        
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
        
        //Decreasing the available quantity from Supplier_Prduct table
        p.adjustQuantity("Decrease", quantity, supplierID, productID);
    }
    
    public ResultSet getCartItems(){
        ResultSet rs = null;
        
        try{
            String sql= "SELECT sci.product_id, sci.supplier_id, p.name,s.name, sci.quantity, sp.production_date, sp.price, (sci.quantity * sp.price) AS NewColumn " +
                        "FROM ShoppingCartItem sci " +
                        "JOIN Supplier s on sci.supplier_id = s.supplier_id "+
                        "JOIN Product p on sci.product_id = p.product_id "+
                        "JOIN Supplier_Product sp on sci.product_id = sp.product_id AND sci.supplier_id = sp.supplier_id "+
                        "WHERE sci.user_id = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, c.getCurrentCustomerID());
            System.out.println("GET CART ITEMS : "+ps);
            rs = ps.executeQuery();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        return rs;
    }
    
    public void removeFromCart(int productID, int supplierID, int quantity){
        
        try{
            String sql= "DELETE FROM ShoppingCartItem WHERE  user_id = ? AND supplier_id = ? AND product_id = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, c.getCurrentCustomerID());
            ps.setInt(2, supplierID);
            ps.setInt(3, productID);

            ps.executeUpdate();

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        //Increasing the available quantity in Supplier_Prduct table
        p.adjustQuantity("Increase", quantity, supplierID, productID);
    }
    
    public void emptyShoppingCart(){
        ResultSet rs = null;
        int quantity, supplierID, productID;
        
        
        try{
            //Get details for all the products in the customer's cart
            String sql= "SELECT supplier_id, product_id, quantity FROM ShoppingCartItem WHERE  user_id = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, c.getCurrentCustomerID());

            rs = ps.executeQuery();
            //Removing each item from the cart of the customer
            while(rs.next()){

                supplierID = rs.getInt(1);
                productID = rs.getInt(2);
                quantity = rs.getInt(3);
                
                
                removeFromCart(productID, supplierID, quantity);
            }

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        
    }
    
    
    
    public ArrayList getCheckoutList(){
        ResultSet rs = null;
        ArrayList<String> details = new ArrayList<>();
        double total = 0;
        
        try{
            String sql= "SELECT sci.quantity, p.name, sp.price, (sci.quantity * sp.price) AS NewColumn " +
                        "FROM ShoppingCartItem sci " +
                        "JOIN Product p on sci.product_id = p.product_id "+
                        "JOIN Supplier_Product sp on sci.product_id = sp.product_id AND sci.supplier_id = sp.supplier_id "+
                        "WHERE sci.user_id = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, c.getCurrentCustomerID());
            rs = ps.executeQuery();
            
            while(rs.next()){
                details.add(rs.getInt(1)+" X "+rs.getString(2)+" - Rs. "+rs.getDouble(4));
                total += rs.getDouble(4);
            }
            
            //last element in the arraylist is the gross total
            details.add(total+"");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        return details;
    }
    
    public void checkoutCart(){
        
        try{
            String sql= "DELETE FROM ShoppingCartItem WHERE  user_id = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, c.getCurrentCustomerID());
            ps.executeUpdate();

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    public int getNoItemsInCart(){
        
        ResultSet rs = null;
        int count = 0;
        try{
            String sql= "SELECT COUNT(*) FROM ShoppingCartItem WHERE  user_id = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, c.getCurrentCustomerID());
            rs = ps.executeQuery();
            while(rs.next()){
                count = rs.getInt(1);

            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        return count;
    }
    
}
