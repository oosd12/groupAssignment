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
public class Product {
    
    public ResultSet getAvailableProducts(){
        ResultSet rs = null;
        java.sql.Connection conn = new DBConnector().connect();
        try{
            String sql= "SELECT sp.product_id, sp.supplier_id, p.name,s.name, sp.quantity_available, sp.production_date, sp.price, p.category,p.image_link, s.city " +
                        "FROM Supplier_Product sp " +
                        "JOIN Supplier s on sp.supplier_id = s.supplier_id "+
                        "JOIN Product p on sp.product_id = p.product_id";
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
        
        if(location.equals("All Locations")){
            location = "";
        }
        
        
        
        ResultSet rs = null;
        java.sql.Connection conn = new DBConnector().connect();
        try{
            String sql= "SELECT sp.product_id, sp.supplier_id, p.name,s.name, sp.quantity_available, sp.production_date, sp.price, p.category,p.image_link, s.city " +
                        "FROM Supplier_Product sp " +
                        " JOIN Supplier s on sp.supplier_id = s.supplier_id  "+
                        " JOIN Product p on sp.product_id = p.product_id WHERE s.city LIKE ? AND p.name LIKE ? AND p.category LIKE ? "+
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
        java.sql.Connection conn = new DBConnector().connect();
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
}

