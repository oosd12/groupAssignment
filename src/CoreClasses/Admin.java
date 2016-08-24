/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CoreClasses;

/**
 *
 * @author Abdullah
 */
public class Admin extends User{
    private static String currentAdmin = "";

    public static String getCurrentAdmin() {
        return currentAdmin;
    }

    public static void setCurrentAdmin(String currentAdmin) {
        Admin.currentAdmin = currentAdmin;
    }
    
    
}
