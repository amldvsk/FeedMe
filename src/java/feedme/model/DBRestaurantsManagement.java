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

    
    /**
     * 
     * @param rest - restaurant that we want to update
     * @param action -  0  - same restaurant name , 1 - rest name has changed
     * @return 1 - if update completed , 2 - if the new rest name already exists
     */
    public int updateRestaurant(Restaurant rest , int action)
    {
        String spuName = "{CALL feedmedb.Spu_RestaurantsUpdate(?, ?, ?, ?, ?, ?, ?,?,?,?, ?,?)}";
        int result = -1 ;
        Connection c = DbConnector.getInstance().getConn();
         try {
           
            cstmt =c.prepareCall(spuName);
            cstmt.clearParameters();
            cstmt.setInt(1, action);
            cstmt.setInt(2, rest.getDbid());
            cstmt.setString(3, rest.getName());
            cstmt.setString(4, rest.getPhone());
            cstmt.setString(5, rest.getLogo());
            cstmt.setString(6, rest.getStreet());
            cstmt.setString(7, rest.getStreetNum());
            cstmt.setString(8, rest.getCity());
            cstmt.setInt(9, rest.getDeliveryPrice());
            cstmt.setInt(10,rest.getMinOrder());
            cstmt.setString(11, rest.getEstimatedTimeDel());
            cstmt.registerOutParameter(12, java.sql.Types.INTEGER);
            cstmt.executeUpdate();
            result = cstmt.getInt(12);
            
            
            
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
                cstmt.clearParameters();
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
