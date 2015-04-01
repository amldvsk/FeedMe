/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedme.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NadavBismuth
 */
public class DBRestaurantsManagement {
    
    private Connection con = null;
    CallableStatement cstmt = null;

    public DBRestaurantsManagement() {
        
        con = DbConnector.getInstance().getConn();
    }
    
    public int addNewRestaurant(String name, String phone, String logo, String street, String streetNum, String city, int deliveryPrice, int minOrder, String estimatedTimeDel)
    {
        int result = 0;
        Connection c = DbConnector.getInstance().getConn();
        String spuName = "{CALL `feedmedb`.`Spu_RestarantsRegistration`(?,?, ?, ?, ?, ?, ?,?, ?, ?)}";
        
        try {
           
            cstmt =c.prepareCall(spuName);
            cstmt.clearParameters();
            cstmt.setString(1, name);
            cstmt.setString(2, phone);
            cstmt.setString(3, logo);
            cstmt.setString(4, street);
            cstmt.setString(5, streetNum);
            cstmt.setString(6, city);
            cstmt.setInt(7, deliveryPrice);
            cstmt.setInt(8, minOrder);
            cstmt.setString(9, estimatedTimeDel);
            cstmt.registerOutParameter(10, java.sql.Types.INTEGER);
            cstmt.executeUpdate();
            result = cstmt.getInt(10);
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DBRestaurantsManagement.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        finally
        {
            try {
                if(cstmt != null)
                { cstmt.close();}
                if(c != null)
                {
                    c.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DbUsersManagement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         
        return result;
    }
    
    
}
