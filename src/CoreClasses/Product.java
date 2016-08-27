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
public class Product {
    java.sql.Connection conn = DBConnector.getDBConnection();
    public ResultSet getAvailableProducts(){
        ResultSet rs = null;
        
        try{
            String sql= "SELECT sp.product_id, sp.supplier_id, p.name,s.name, sp.quantity_available, sp.production_date, sp.price, p.category,p.image_link, s.city " +
                        "FROM Supplier_Product sp " +
                        "JOIN Supplier s on sp.supplier_id = s.supplier_id "+
                        "JOIN Product p on sp.product_id = p.product_id WHERE sp.quantity_available > 0";
            PreparedStatement ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        return rs;
    }
    
    public ResultSet searchForProducts(String keyword, String filter, String sort, String location){
        String sortCondition = "";
        if (sort.equals("Price Highest to Lowest")){
           sortCondition = "ORDER BY sp.price DESC";
        }
        else if (sort.equals("Price Lowest to Highest")){
           sortCondition = "ORDER BY sp.price ASC";
        }
        else if (sort.equals("Quantity Highest to Lowest")){
           sortCondition = "ORDER BY sp.quantity_available DESC";
        }
        else if (sort.equals("Quantity Lowest to Highest")){
           sortCondition = "ORDER BY sp.quantity_available ASC";
        }
        else if (sort.equals("Production Date Latest to Oldest")){
           sortCondition = "ORDER BY sp.production_date DESC";
        }
        else if (sort.equals("Production Date Oldest to Latest")){
           sortCondition = "ORDER BY sp.production_date ASC";
        }
        
        if(filter.equals("All Categories")){
            filter="";
        }
        
        if(location.equals("All Suppliers")){
            location = "";
        }
        
        
        
        ResultSet rs = null;
        try{
            String sql= "SELECT sp.product_id, sp.supplier_id, p.name,s.name, sp.quantity_available, sp.production_date, sp.price, p.category,p.image_link, s.city " +
                        "FROM Supplier_Product sp " +
                        " JOIN Supplier s on sp.supplier_id = s.supplier_id  "+
                        " JOIN Product p on sp.product_id = p.product_id WHERE s.name LIKE ? AND p.name LIKE ? AND p.category LIKE ? AND sp.quantity_available > 0 "+
                        sortCondition;

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%"+location+"%");
            ps.setString(2, "%"+keyword+"%");
            ps.setString(3, "%"+filter+"%");

            

            rs = ps.executeQuery();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        return rs;
        
    
    }
    public ResultSet getAllProducts(){
        ResultSet rs = null;
        try{
            String sql= "SELECT * FROM Product";
            PreparedStatement ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        return rs;
        
        
    }
    
    public void adjustQuantity(String operation, int quantity, int supplierID, int productID){
        try{
            String sql = "";
            if(operation.equals("Increase")){
                sql= "UPDATE Supplier_Product SET `quantity_available` = `quantity_available` + ? WHERE supplier_id = ? AND product_id = ? ";
            }
            else if(operation.equals("Decrease")){
                sql= "UPDATE Supplier_Product SET `quantity_available` = `quantity_available` - ? WHERE supplier_id = ? AND product_id = ? ";
            }
            
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, quantity);
            ps.setInt(2, supplierID);
            ps.setInt(3, productID);
            
            ps.executeUpdate();

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    //For use on admin dashboard products table
    public ResultSet getAllSupplies(){
        ResultSet rs = null;
        try{
            String sql= "SELECT sp.product_id, sp.supplier_id, p.name,s.name, sp.quantity_available, sp.production_date, sp.price, p.category " +
                        "FROM Supplier_Product sp " +
                        "JOIN Supplier s on sp.supplier_id = s.supplier_id "+
                        "JOIN Product p on sp.product_id = p.product_id ";
            PreparedStatement ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        return rs;
    }
    
    //for searching products on admin dashboard
    public ResultSet searchAllSupplies(String keyword, String filter, String sort, String location){
        String sortCondition = "";
        if (sort.equals("Price Highest to Lowest")){
           sortCondition = "ORDER BY sp.price DESC";
        }
        else if (sort.equals("Price Lowest to Highest")){
           sortCondition = "ORDER BY sp.price ASC";
        }
        else if (sort.equals("Quantity Highest to Lowest")){
           sortCondition = "ORDER BY sp.quantity_available DESC";
        }
        else if (sort.equals("Quantity Lowest to Highest")){
           sortCondition = "ORDER BY sp.quantity_available ASC";
        }
        else if (sort.equals("Production Date Latest to Oldest")){
           sortCondition = "ORDER BY sp.production_date DESC";
        }
        else if (sort.equals("Production Date Oldest to Latest")){
           sortCondition = "ORDER BY sp.production_date ASC";
        }
        
        if(filter.equals("All Categories")){
            filter="";
        }
        
        if(location.equals("All Suppliers")){
            location = "";
        }
        
        
        
        ResultSet rs = null;
        try{
            String sql= "SELECT sp.product_id, sp.supplier_id, p.name,s.name, sp.quantity_available, sp.production_date, sp.price, p.category " +
                        "FROM Supplier_Product sp " +
                        " JOIN Supplier s on sp.supplier_id = s.supplier_id  "+
                        " JOIN Product p on sp.product_id = p.product_id WHERE s.name LIKE ? AND p.name LIKE ? AND p.category LIKE ? "+
                        sortCondition;

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%"+location+"%");
            ps.setString(2, "%"+keyword+"%");
            ps.setString(3, "%"+filter+"%");


            rs = ps.executeQuery();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        return rs;

    }
    
    
    public ResultSet searchAdminProducts(String keyword){
        ResultSet rs = null;
        try{
            String sql= "SELECT  * FROM Product WHERE name LIKE ? " ;
                        

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%"+keyword+"%");
            
            rs = ps.executeQuery();

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        return rs;
    }
    
    public ArrayList getAllProductID(){
        ResultSet rs = null;
        ArrayList<String> idList = new ArrayList<>();
       
        try{
            String sql= "SELECT  product_id FROM Product " ;
                        

            PreparedStatement ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                rs.getString(1);
                idList.add(rs.getString(1));
            }

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        return idList;
    }
    
    
    public String getProductName(int productID){
        ResultSet rs = null;
        String prodName = "";
        try{
            String sql= "SELECT name FROM Product WHERE product_id = ? " ;

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, productID);
            rs = ps.executeQuery();
            
            while(rs.next()){
               prodName = rs.getString(1);
            }

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        return prodName;
    }
    
    //Add product
    public class addProduct{
    Product p = new Product();
    
    java.sql.Connection conn = DBConnector.getDBConnection();
    
    
    public void addToProduct(int productID, String name, String category, String image_link){
        
        try{
            String sql= "INSERT INTO Product (product_id, name, category, image_link) VALUES (?, ?, ?, ?) ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, productID);
            ps.setString(2, name);
            ps.setString(3, category);
            ps.setString(4, image_link);
            
            ps.executeUpdate();

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
    }
    
    }
}

