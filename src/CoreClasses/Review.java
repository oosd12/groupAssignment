/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CoreClasses;

import java.sql.Date;
import javax.swing.JPanel;

/**
 *
 * @author Abdullah
 */
public class Review {
    private String username, comment;
    private Date date;
    private int rating;
    private int productID, supplierID;
    ReviewPanel rp;
    
    public Review(String userName, String comment, int rating, Date date) {
        this.username = userName;
        this.date = date;
        this.rating = rating;
        this.comment =comment;
        
        this.rp = new ReviewPanel(userName, comment, rating, date);
        
        
    }
    
    
    
    public JPanel getPanel(){
       
        return rp.getReviewPanel();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
}
