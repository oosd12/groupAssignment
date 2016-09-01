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
             String sql= "SELECT p.product_id, s.supplier_id, op.order_id, o.order_date, s.name, s.contact_number, s.street, s.city, s.post_code, p.name, op.quantity, op.collection_status  " +
                        "FROM `sql6131484`.`Order_Product` op " +
                        "JOIN `sql6131484`.`Product` p on op.product_id = p.product_id "+
                        "JOIN `sql6131484`.`Order` o on op.order_id = o.order_id "+
                        "JOIN `sql6131484`.`Supplier` s on op.supplier_id = s.supplier_id "+
                        "WHERE op.collection_status = 'Pending' ORDER BY s.name, op.order_id ";
            PreparedStatement ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        return rs;
    }

    @Override
    public void updateJobStatus(int orderID, int productId, int supplierID, String status) {
         try{
            String sql= " UPDATE Order_Product " +
                        " SET `collection_status` = ? " +
                        " WHERE order_id = ? AND supplier_id = ? AND product_id = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, orderID);
            ps.setInt(3, supplierID);
            ps.setInt(4, productId);
            ps.executeUpdate();
            
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
}
