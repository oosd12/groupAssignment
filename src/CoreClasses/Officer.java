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
public abstract class Officer extends User{
    public abstract ResultSet viewRoute();
    
    public abstract void updateJobStatus(int orderID, int productId, int supplierID, String status);
}
