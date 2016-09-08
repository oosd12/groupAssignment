/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CoreClasses;

import java.sql.ResultSet;

/**
 *
 * @author Abdullah
 */
public class Collection {
    java.sql.Connection conn = DBConnector.getDBConnection();
    public ResultSet generateCollectionReport(){
        ResultSet rs = null;
        //implementation here
        return rs;
    }
}
