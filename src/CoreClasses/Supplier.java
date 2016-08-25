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
    java.sql.Connection conn = DBConnector.getDBConnection();
    
    public ArrayList getAllSupplierLocations(){
        supplierLocations.add("All Locations");
        ResultSet rs = null;
        
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
    
    public ArrayList getAllSupplierID(){
        ResultSet rs = null;
        ArrayList<String> idList = new ArrayList<>();
        try{
            String sql= "SELECT  supplier_id FROM Supplier " ;
                        

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
    
    public String getSupplierName(int supplierID){
        ResultSet rs = null;
        String supName = "";
        try{
            String sql= "SELECT name FROM Supplier WHERE supplier_id = ? " ;

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, supplierID);
            rs = ps.executeQuery();
            
            while(rs.next()){
               supName = rs.getString(1);
            }

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        return supName;
    }
}



