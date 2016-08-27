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
    java.sql.Connection conn = new DBConnector().connect();
    
    public ArrayList getReviews(){
        ArrayList<Review> reviews = new ArrayList<>();
        
        ResultSet rs = null;
        try{
            String sql= "SELECT u.name, r.message, r.product_rating, r.date " +
                        "FROM Review r " +
                        "JOIN User u on r.user_id = u.user_id ";
                        

            PreparedStatement ps = conn.prepareStatement(sql);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                Review r = new Review(rs.getString(1), rs.getString(2),rs.getInt(3), rs.getDate(4));

                reviews.add(r);
                
            }
            

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        return reviews;
    }
}
