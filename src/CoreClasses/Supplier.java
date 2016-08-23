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
public class Supplier {
    private ArrayList<String> supplierLocations = new ArrayList<String>();
    
    public ArrayList getAllSupplierLocations(){
        supplierLocations.add("All Locations");
        ResultSet rs = null;
        java.sql.Connection conn = new DBConnector().connect();
        try{
            String sql= "SELECT city FROM Supplier";
            PreparedStatement ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            String city;
            while(rs.next()){
                city = rs.getString(1);
                supplierLocations.add(city);
            }
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        return supplierLocations;
    }
}
