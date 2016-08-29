package CoreClasses;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Abdullah
 */
public class ReviewDAO {
    java.sql.Connection conn = DBConnector.getDBConnection();
    
    public ArrayList getReviews(int productID, int supplierID){
        ArrayList<Review> reviews = new ArrayList<>();
        
        ResultSet rs = null;
        try{
            String sql= "SELECT u.name, r.message, r.product_rating, r.date, r.user_id " +
                        "FROM Review r " +
                        "JOIN User u on r.user_id = u.user_id WHERE r.product_id = ? AND r.supplier_id = ? ";
                        

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, productID);
            ps.setInt(2, supplierID);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                ResultSet rs2 = null;
                
                try{
                    String sql2= "SELECT op.order_id " +
                                 " FROM `sql6131484`.`Order_Product` op " +
                                 " JOIN `sql6131484`.`Order` o on op.order_id = o.order_id WHERE op.product_id = ? AND op.supplier_id = ? AND o.user_id = ? ";
                    PreparedStatement ps2 = conn.prepareStatement(sql2);
                    ps2.setInt(1, productID);
                    ps2.setInt(2, supplierID);
                    ps2.setInt(3, rs.getInt(5));
                    rs2 = ps2.executeQuery();
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(null,e);
                }
                
                
                Review r;
                if(rs2.next()){
                    r = new Review(rs.getString(1)+" (Customer has purchased this product)", rs.getString(2),rs.getInt(3), rs.getDate(4));
                }
                else{
                    r = new Review(rs.getString(1)+" (Customer has not purchased this product)", rs.getString(2),rs.getInt(3), rs.getDate(4));
                }

                reviews.add(r);
                
            }
            

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        return reviews;
    }
    
    public void addReview(Review r){
        ResultSet rs = null;
        try{
            String sql= "INSERT INTO `sql6131484`.`Review` ( date, message, product_rating, product_id, supplier_id, user_id) VALUES ( ?, ?, ?, ?, ?, ? ); ";
            Customer c = new Customer();
            PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setDate(1, r.getDate());
            ps.setString(2, r.getComment());
            ps.setInt(3, r.getRating());
            ps.setInt(4, r.getProductID());
            ps.setInt(5, r.getSupplierID());
            ps.setInt(6, c.getCurrentCustomerID());

            ps.executeUpdate();
            
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
}
