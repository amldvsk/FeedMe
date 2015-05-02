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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
    
    public int addNewRestaurant(String name,int catId, String phone, String logo, String street, String streetNum, String city, int deliveryPrice, int minOrder, String estimatedTimeDel , int manId)
    {
        int result = 0;
        Connection c = DbConnector.getInstance().getConn();
        String spuName = "{CALL `feedmedb`.`Spu_RestaurantsRegistration`(?,?,?, ?, ?, ?, ?, ?,?, ?,?, ?)}";
        
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
            cstmt.setInt(11, manId);
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

    
    /**
     * 
     * @param rest - restaurant that we want to update
     * @param action -  0  - same restaurant name , 1 - rest name has changed
     * @return 1 - if update completed , 2 - if the new rest name already exists
     */
    public int updateRestaurant(Restaurant rest , int action , int managerId)
    {
        String spuName = "{CALL feedmedb.Spu_RestaurantsUpdate(?, ?, ?, ?, ?, ?, ?,?,?,?,?, ?,?)}";
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
            cstmt.setInt(12, managerId);
            cstmt.registerOutParameter(13, java.sql.Types.INTEGER);
            cstmt.executeUpdate();
            result = cstmt.getInt(13);
            
            
            
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

    public int deleteRestaurant(int restID)
    {
        int result =-1;
        con = DbConnector.getInstance().getConn();
        String spuName = "{CALL feedmedb.Spe_DeleteRestaurant(?)}";
        
        
        try {
            cstmt = con.prepareCall(spuName);
            cstmt.clearParameters();
            cstmt.setInt(1, restID);
            result  = cstmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DBRestaurantsManagement.class.getName()).log(Level.SEVERE, null, ex);
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
    
    
    /**
     * get the recent restaurants in specific category
     * @param numRest - number of rest on page
     * @param catId - category id
     * @return list of resturants , or fake restaruant "BigaBuGule" with rest id 0
     */
    public List<Restaurant> getLatestRestaurantsByCat(int numRest , int catId)
    {
        con = DbConnector.getInstance().getConn();
        List<Restaurant> restaurants = new ArrayList<>();
        String spuName = "{CALL feedmedb.Spu_GetRecentRestaurant(?,?)}";
        ResultSet rs  = null;
        
        try {
                cstmt =con.prepareCall(spuName);
                   cstmt.clearParameters();
            cstmt.setInt(1, numRest);
            cstmt.setInt(2, catId);
            rs = cstmt.executeQuery();
            while(rs.next())
            {
                Restaurant res  = new Restaurant(rs.getString("rest_name") , rs.getString("phone") , rs.getString("logo") ,
                rs.getString("street") , rs.getString("street_num") , rs.getString("city") ,rs.getInt("delivery_price"), rs.getInt("min_order"),rs.getString("estimated_time") );
                res.setDbid(rs.getInt("restid"));
                restaurants.add(res);
            }
                
        } catch (SQLException ex) {
            Logger.getLogger(DBRestaurantsManagement.class.getName()).log(Level.SEVERE, null, ex);
             Restaurant res  = new Restaurant("BigaBuGule" , "0546555257" , null ,
                "הגשר" , "12" ,"פתח תקווה" ,15, 15,"שעה" );
                res.setDbid(0);
                restaurants.add(res);
        }
        
         finally
        {
              try {
            if(cstmt != null)
            {
              
                    cstmt.close();
                } 
            if(con != null)
            {
                con.close();
            }
              }
            catch (SQLException ex) {
                    Logger.getLogger(DbHPOnLoad.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        
        return restaurants;
    }
    
    public List<Restaurant> getNextRecentRestaurantsByCat(int start , int numRests , int catId)
    {
        con = DbConnector.getInstance().getConn();
        List<Restaurant> restaurants = new ArrayList<>();
        String spuName = "{CALL feedmedb.Spu_GetNextRestaurant(?,? , ?)}";
        ResultSet rs  = null;
        
        try {
                cstmt =con.prepareCall(spuName);
                   cstmt.clearParameters();
            cstmt.setInt(1, start);
            cstmt.setInt(2, numRests);
            cstmt.setInt(3, catId);
            rs = cstmt.executeQuery();
            while(rs.next())
            {
                Restaurant res  = new Restaurant(rs.getString("rest_name") , rs.getString("phone") , rs.getString("logo") ,
                rs.getString("street") , rs.getString("street_num") , rs.getString("city") ,rs.getInt("delivery_price"), rs.getInt("min_order"),rs.getString("estimated_time") );
                res.setDbid(rs.getInt("restid"));
                restaurants.add(res);
            }
                
        } catch (SQLException ex) {
            Logger.getLogger(DBRestaurantsManagement.class.getName()).log(Level.SEVERE, null, ex);
             Restaurant res  = new Restaurant("BigaBuGule" , "0546555257" , null ,
                "הגשר" , "12" ,"פתח תקווה" ,15, 15,"שעה" );
                res.setDbid(0);
                restaurants.add(res);
        }
        
         finally
        {
              try {
            if(cstmt != null)
            {
              
                    cstmt.close();
                } 
            if(con != null)
            {
                con.close();
            }
              }
            catch (SQLException ex) {
                    Logger.getLogger(DbHPOnLoad.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        
        return restaurants;
    }
}
