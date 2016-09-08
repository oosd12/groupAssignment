/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CoreClasses;

import java.sql.Date;
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
            PreparedStatement ps = DBConnector.getDBConnection().prepareStatement(sql);
            System.out.println("Used connection : " + DBConnector.getDBConnection());
            System.out.println("Conn : " + conn);
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
   
    public void addToProduct(String name, String category, String image_link){
        
        try{
            String sql= "INSERT INTO Product (name, category, image_link) VALUES (?, ?, ?) ";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, name);
            ps.setString(2, category);
            ps.setString(3, image_link);
            
            ps.executeUpdate();

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
    }
    
    public int getNextProductID(){
        ResultSet rs = null;
        try{
            String sql= "SELECT MAX(product_id) FROM Product";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            rs = ps.executeQuery();
            
            if(rs.next()){
                return rs.getInt(1) + 1;
            }

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        return 0;
    }
    
    public void  updateProduct(int productID,String name, String category, String image_link){
        try{
            String sql= " UPDATE Product " +
                        " SET `name` = ?, category = ?, image_link = ? " +
                        " WHERE product_id = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, category);
            ps.setString(3, image_link);
            ps.setInt(4, productID);
            System.out.println(""+ps);
            ps.executeUpdate();
            
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }

    }
    
    
    public void adjustSupply(int supplierID, int productID, int quantity, Date date, double price){
        ResultSet rs = null;
        try{
            String sql= "SELECT supplier_id, product_id FROM Supplier_Product WHERE supplier_id = ? AND product_id = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, supplierID);
            ps.setInt(2, productID);
            rs = ps.executeQuery();
            
            if(rs.next()){
                //update product
                try{
                    String sql2= " UPDATE Supplier_Product " +
                                " SET `quantity_available` = ?, `production_date` = ?, `price` = ? " +
                                " WHERE product_id = ? AND supplier_id = ? ";
                    PreparedStatement ps2 = conn.prepareStatement(sql2);
                    ps2.setInt(1, quantity);
                    ps2.setDate(2, date);
                    ps2.setDouble(3, price);
                    ps2.setInt(4, productID);
                    ps2.setInt(5, supplierID);
                    System.out.println(""+ps2);
                    ps2.executeUpdate();


                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(null,e);
                }
            }
            else{
                //insert
                try{
                    String sql2= " INSERT INTO Supplier_Product (supplier_id, product_id, quantity_available, production_date, price) " +
                                " VALUES(?, ?, ?, ?, ?) " +
                                " ";
                    PreparedStatement ps2 = conn.prepareStatement(sql2);
                    ps2.setInt(1, supplierID);
                    ps2.setInt(2, productID);
                    ps2.setDouble(3, quantity);
                    ps2.setDate(4, date);
                    ps2.setDouble(5, price);
                    
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
    
    public String[] getQuantityDetails(int productID, int supplierID){
        String[] details = new String[3];
        ResultSet rs = null;
        
        try{
            String sql= "SELECT quantity_available, production_date, price FROM Supplier_Product WHERE product_id = ? AND supplier_id = ? " ;

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, productID);
            ps.setInt(2, supplierID);
            rs = ps.executeQuery();
            
            if(rs.next()){
              details[0] = rs.getString(1); //first index for quantity
              details[1] = rs.getString(2); //second index for production date
              details[2] = rs.getString(3); //third index for price
            }
            else{
                details[0] = ""+0;
                details[1] = "0000-00-00";
                details[2] = ""+0;
            }

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        return details;
    }
    
    //getting the price for a given product
    public double getProductPrice(int productID, int supplierID){
 
        ResultSet rs = null;
        double price = 0.0;
        try{
            String sql= "SELECT price FROM Supplier_Product WHERE product_id = ? AND  supplier_id = ? " ;
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, productID);
            ps.setInt(2, supplierID);
            rs = ps.executeQuery();
            
            if(rs.next()){
               price = rs.getDouble(1);
            }

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        return price;
    }
}


