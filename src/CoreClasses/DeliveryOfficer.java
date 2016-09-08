/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CoreClasses;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author Abdullah
 */
public class DeliveryOfficer extends Officer{
    java.sql.Connection conn = DBConnector.getDBConnection();
        Product p = new Product();
        
        public void updateDelivery(String OrderID,String delivery_status){
        
        try{
            String sql= "INSERT INTO Order_Product (delivery_status) WHERE order_id = '"+OrderID+"' VALUES ('"+delivery_status+"') ";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, delivery_status);
       
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
             String sql= "SELECT od.product_id, od.supplier_id, od.order_id, o.order_date,u.name, u.contact_number,da.street, da.city, da.post_code, p.name, s.name, od.quantity, od.deliver_status,o.payment_status  " +
                        "FROM `sql6131484`.`Order_Product` od " +
                        "JOIN `sql6131484`.`DeliveryAddress` da on od.order_id = da.order_id "+
                        "JOIN `sql6131484`.`Product` p on od.product_id = p.product_id "+
                        "JOIN `sql6131484`.`Supplier` s on od.supplier_id = s.supplier_id "+
                        "JOIN `sql6131484`.`Order` o on od.order_id = o.order_id "+
                        "JOIN `sql6131484`.`User` u on o.user_id = u.user_id "+
                        "WHERE o.payment_status = 'Not Paid' AND o.deliveryMode = 'Delivery' "+
                        "ORDER BY o.order_date ASC ";
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
                        " SET `deliver_status` = ? " +
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
    
    //Adjusting the the gross and net total of the order if a customer rejects a product.
    public void rejectItem(int order_id, int product_id, int supplier_id){
        
        double grossTotal, tax,netTotal,productPrice;
        //Selecting the current gross total and tax values
         try{
            String sql= " SELECT `gross_total`, `tax` FROM `sql6131484`.Order " +
                        " WHERE order_id = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, order_id);

            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                grossTotal = rs.getDouble(1);
                tax = rs.getDouble(2);
                
                //getting the price of the item being rejected
                productPrice = p.getProductPrice(product_id, supplier_id);
                
                //calculating the new gross and net total
                grossTotal -= productPrice;
                netTotal = ((100.0+tax)/100.0)*grossTotal;
                //rounding the new value to two decimal places
                DecimalFormat df = new DecimalFormat("#.##");      
                netTotal = Double.valueOf(df.format(netTotal));
                
                //Updating the record with the new values
                try{
                    String sql2= " UPDATE `sql6131484`.Order " +
                                 " SET `gross_total` = ?, net_total = ? " +
                                 " WHERE order_id = ? ";
                    PreparedStatement ps2 = conn.prepareStatement(sql2);
                    ps2.setDouble(1, grossTotal);
                    ps2.setDouble(2, netTotal);
                    ps2.setInt(3, order_id);

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
    
    public void receivePayment(int orderID){
        try{
            String sql= " UPDATE `sql6131484`.Order " +
                        " SET `payment_status` = ? " +
                        " WHERE order_id = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "Paid");
            ps.setInt(2, orderID);
            ps.executeUpdate();
            
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
}
