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
            String sql= "SELECT sp.product_id, sp.supplier_id, p.name,s.name, sp.quantity_available, sp.production_date, sp.price, p.category,p.image_link " +
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
    
    public ResultSet searchForProducts(String keyword, String category, String filter, String sort, String location){
        ResultSet rs = null;
        return rs;
        
    }
}
