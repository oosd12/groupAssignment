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
abstract public class User {
    protected String userID, name, email,contactNumber,password,userType;
    
    public int login(String email, String password){
        return 0;
    }
    
    public boolean register(String name, String email, String contactNumber, String password, String userType){
        return false;
    }
}
