/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedme.model;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
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
    
    public int addNewRestaurant(String name,int catId, String phone, String logo, String street, String streetNum, String city, int deliveryPrice, int minOrder, String estimatedTimeDel)
    {
        int result = 0;
        Connection c = DbConnector.getInstance().getConn();
        String spuName = "{CALL `feedmedb`.`Spu_RestaurantsRegistration`(?,?,?, ?, ?, ?, ?, ?,?, ?, ?)}";
        
        try {
           
            cstmt =c.prepareCall(spuName);
            cstmt.clearParameters();
            cstmt.setString(1, name);
            cstmt.setInt(2, catId);
            cstmt.setString(3, phone);
            cstmt.setString(4, logo);
            cstmt.setString(5, street);
            cstmt.setString(6, streetNum);
            cstmt.setString(7, city);
            cstmt.setInt(8, deliveryPrice);
            cstmt.setInt(9, minOrder);
            cstmt.setString(10, estimatedTimeDel);
            cstmt.registerOutParameter(11, java.sql.Types.INTEGER);
            cstmt.executeUpdate();
            result = cstmt.getInt(11);
            
            
            
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
    
    public int addNewCategory(String catName)
    {
        int result = -1;
        String spuName = "{CALL feedmedb.Spu_AddNewCategory(?)}";
  
      
        try {
               
                cstmt = con.prepareCall(spuName);
                cstmt.setString(1, catName);
                result = cstmt.executeUpdate();
               
                
        } catch (SQLException ex) {
            Logger.getLogger(DbUsersManagement.class.getName()).log(Level.SEVERE, null, ex);
       Logger.getLogger(DbUsersManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                if(cstmt != null)
                { cstmt.close();}
                if(con != null)
                {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DbUsersManagement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
     return result;
    }

}
