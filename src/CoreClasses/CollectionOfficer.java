package CoreClasses;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Abdullah
 */
public class CollectionOfficer extends Officer{
        java.sql.Connection conn = DBConnector.getDBConnection();
        
        public ResultSet displayCollection(){
        ResultSet rs = null;
        
        try{
            String sql= "SELECT order_id,supplier_id,product_id,quantity,collection_status From Order_Product Where collection_status = 'Pending'";
            PreparedStatement ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        return rs;
    }
        public ResultSet getRecords(){
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
        
        public void updateCollection(String OrderID,String collection_status){
        
        try{
            String sql= "INSERT INTO Order_Product (collection_status) WHERE order_id = '"+OrderID+"' VALUES ('"+collection_status+"') ";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, collection_status);
       
            ps.executeUpdate();

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
    }
        
    @Override
    public ResultSet viewRoute() {
        ResultSet rs = null;
        
        try{
             String sql= "SELECT op.order_id, s.name, s.contact_number, s.street, s.city, s.post_code, p.name, op.quantity  " +
                        "FROM `sql6131484`.`Order_Product` op " +
                        "JOIN `sql6131484`.`DeliveryAddress` da on op.order_id = da.order_id "+
                        "JOIN `sql6131484`.`Product` p on od.product_id = p.product_id "+
                        "JOIN `sql6131484`.`Order` o on od.order_id = o.order_id "+
                        "JOIN `sql6131484`.`Supplier` s on op.product_id = op.supplier_id "+
                        "WHERE od.deliver_status = 'Pending' ";
            PreparedStatement ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        return rs;
    }
}
