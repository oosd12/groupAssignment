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
public class CollectionOfficer extends Officer{
        java.sql.Connection conn = new DBConnector().connect();
        public ResultSet displayCollection(){
        ResultSet rs = null;
        
        try{
            String sql= "SELECT *" +
                        "FROM Order_Product op" +
                        "WHERE op.collection_status = Pending";
            PreparedStatement ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        return rs;
    }
        public ResultSet getrecord(){
           ResultSet rs = null;
        
        try{
            String sql= "SELECT *" +
                        "FROM Order_Product op";
            PreparedStatement ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        return rs; 
        }
}
