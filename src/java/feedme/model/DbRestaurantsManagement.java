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
public class DbRestaurantsManagement {
    
    private Connection con = null;
    CallableStatement cstmt = null;
    private DbMenuManagment dbm = null;
    
    public DbRestaurantsManagement() {
        
        con = DbConnector.getInstance().getConn();
        dbm = new DbMenuManagment();
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
            Logger.getLogger(DbRestaurantsManagement.class.getName()).log(Level.SEVERE, null, ex);
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
         
        if (result ==0 || result == -1 )
        {
            return result;
        }
       
        
        result = dbm.addNewMenu("Menu" + name, result);
       
        return result;
    }
    
    
    
    public List<Customer> getCustomersByManagerIdAndRestId(int managerId , int restId)
    {
        List<Customer> customers = new ArrayList<>();
        String spuName = "{CALL feedmedb.Spu_GetCustomersByManagerId(? , ?)}";
        con = DbConnector.getInstance().getConn();
        ResultSet rs = null;
        
        try {
            cstmt = con.prepareCall(spuName);
            cstmt.clearParameters();
            cstmt.setInt(1, managerId);
            cstmt.setInt(2, restId);
            rs= cstmt.executeQuery();
            while(rs.next())
            {
               Customer cust = new Customer(rs.getString("firstname"),rs.getString("lastname"), rs.getString("username") ,   rs.getString("phone") ,  rs.getString("email") ,
                            rs.getInt("role" ) , rs.getDate("bday") ,rs.getString("street")  ,rs.getString("house_num") , rs.getString("apartment_num") , rs.getString("city") );
                           cust.setDbId(rs.getInt("userid"));
                            customers.add(cust);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbRestaurantsManagement.class.getName()).log(Level.SEVERE, null, ex);
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
        
        
        
        return customers;
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
            Logger.getLogger(DbRestaurantsManagement.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DbRestaurantsManagement.class.getName()).log(Level.SEVERE, null, ex);
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
                rs.getString("street") , rs.getString("street_num") , rs.getString("city") ,rs.getInt("delivery_price"), rs.getInt("min_order"),rs.getString("estimated_time")  );
                res.setDbid(rs.getInt("restid"));
                res.setMenuId(rs.getInt("menuid"));
                restaurants.add(res);
            }
                
        } catch (SQLException ex) {
            Logger.getLogger(DbRestaurantsManagement.class.getName()).log(Level.SEVERE, null, ex);
             Restaurant res  = new Restaurant("BigaBuGule" , "0546555257" , null ,
                "הגשר" , "12" ,"פתח תקווה" ,15, 15,"שעה"  );
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
    
    public List<Restaurant> getNextRecentRestaurantsByCatAndCity(int start , int numRests , int catId , String cityName)
    {
        con = DbConnector.getInstance().getConn();
        List<Restaurant> restaurants = new ArrayList<>();
        String spuName = "{CALL feedmedb.Spu_GetNextRestaurant(?,? ,?, ?)}";
        ResultSet rs  = null;
        
        try {
                cstmt =con.prepareCall(spuName);
                   cstmt.clearParameters();
            cstmt.setInt(1, start);
            cstmt.setInt(2, numRests);
            cstmt.setInt(3, catId);
            cstmt.setString(4, cityName);
            rs = cstmt.executeQuery();
            while(rs.next())
            {
                Restaurant res  = new Restaurant(rs.getString("rest_name") , rs.getString("phone") , rs.getString("logo") ,
                rs.getString("street") , rs.getString("street_num") , rs.getString("city") ,rs.getInt("delivery_price"), rs.getInt("min_order"),rs.getString("estimated_time")  );
                res.setDbid(rs.getInt("restid"));
                res.setMenuId(rs.getInt("menuid"));
                restaurants.add(res);
            }
                
        } catch (SQLException ex) {
            Logger.getLogger(DbRestaurantsManagement.class.getName()).log(Level.SEVERE, null, ex);
             Restaurant res  = new Restaurant("BigaBuGule" , "0546555257" , null ,
                "הגשר" , "12" ,"פתח תקווה" ,15, 15,"שעה"  );
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
    
    /**
     * add new ranking to specific restuarant
     * @param restRank -   RestuarantRanking object
     * @return 1 - succsess , -2 sql exception
     */
    public int addRestRanking( RestaurantRanking restRank)
    {
        int result =-1 ;
        con = DbConnector.getInstance().getConn();
        String spuName = "{CALL `feedmedb`.`Spu_AddRestaurantRanking`(?, ?, ?)};";
        
        
        try {
            cstmt = con.prepareCall(spuName);
            cstmt.clearParameters();
            cstmt.setInt(1, restRank.getRestId());
            cstmt.setDouble(2, restRank.getRankValue());
            cstmt.setString(3, restRank.getComment());
            result = cstmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(DbRestaurantsManagement.class.getName()).log(Level.SEVERE, null, ex);
            result = -2;
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
        
        return result;
    }
    
    /**
     * return ranks for specific restuarant
     * @param restId - restuarant id
     * @return list of ranking
     */
    public List<RestaurantRanking> getRestRank(int restId)
    {
        List<RestaurantRanking> restRankingList = new ArrayList<>();
        con = DbConnector.getInstance().getConn();
        String spuName = "{CALL feedmedb.Spu_GetRestRanks(?)}";
        ResultSet rs ;
        
        try {
             cstmt = con.prepareCall(spuName);
             cstmt.clearParameters();
             cstmt.setInt(1, restId);
             rs = cstmt.executeQuery();
             while(rs.next())
             {
                 RestaurantRanking rK = new RestaurantRanking(rs.getInt("restid") , rs.getDouble("rank") , rs.getString("rest_comment"));
                 restRankingList.add(rK);
             }
             
        } catch (SQLException ex) {
            Logger.getLogger(DbRestaurantsManagement.class.getName()).log(Level.SEVERE, null, ex);
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
        
        return restRankingList;
    }
    
    public Restaurant getRestaurantById(int restId)
    {
        con = DbConnector.getInstance().getConn();
        String spuName = "{CALL feedmedb.Spu_GetRestaurantById(?)}";
        ResultSet rs  = null;
        Restaurant rest  = null;
        try {
            cstmt = con.prepareCall(spuName);
            cstmt.clearParameters();
            cstmt.setInt(1, restId);
            rs = cstmt.executeQuery();
            while(rs.next())
            {
               rest  = new Restaurant(rs.getString("rest_name") , rs.getString("phone") , rs.getString("logo") ,
                rs.getString("street") , rs.getString("street_num") , rs.getString("city") ,rs.getInt("delivery_price"), rs.getInt("min_order"),rs.getString("estimated_time")  );
                rest.setDbid(rs.getInt("restid"));
                rest.setMenuId(rs.getInt("menuid"));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbRestaurantsManagement.class.getName()).log(Level.SEVERE, null, ex);
            rest = new Restaurant("BigaBuGule","0546555234", "","dsfsd", "dsfsdfds", "adasd", 12, 12 , "agv");
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
        
        return rest;
        
        
    }
    
    
    
    public List<Restaurant> getRestaurantsByManagerId(int ManagerId)
    {
        con = DbConnector.getInstance().getConn();
        List<Restaurant> restaurants = new ArrayList<>();
        String spuName = "{CALL feedmedb.Spu_GetResturantsByManagerId(?)}";
        ResultSet rs  = null;
        
        try {
            cstmt = con.prepareCall(spuName);
            cstmt.clearParameters();
            cstmt.setInt(1, ManagerId);
            rs = cstmt.executeQuery();
            while(rs.next())
            {
                 Restaurant res  = new Restaurant(rs.getString("rest_name") , rs.getString("phone") , rs.getString("logo") ,
                rs.getString("street") , rs.getString("street_num") , rs.getString("city") ,rs.getInt("delivery_price"), rs.getInt("min_order"),rs.getString("estimated_time")   );
                res.setDbid(rs.getInt("restid"));
                res.setMenuId(rs.getInt("menuid"));
                restaurants.add(res);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbRestaurantsManagement.class.getName()).log(Level.SEVERE, null, ex);
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
    
    
    
    public List<Customer> getCustomersByManagerId(int managerId)
    {
        List<Customer> customers = new ArrayList<>();
        String spuName = "{CALL feedmedb.Spu_GetCustomersByManagerId(?)}";
        con = DbConnector.getInstance().getConn();
        ResultSet rs = null;
        
        try {
            cstmt = con.prepareCall(spuName);
            cstmt.clearParameters();
            cstmt.setInt(1, managerId);
            rs= cstmt.executeQuery();
            while(rs.next())
            {
               Customer cust = new Customer(rs.getString("firstname"),rs.getString("lastname"), rs.getString("username") ,   rs.getString("phone") ,  rs.getString("email") ,
                            rs.getInt("role" ) , rs.getDate("bday") ,rs.getString("street")  ,rs.getString("house_num") , rs.getString("apartment_num") , rs.getString("city") );
                           cust.setDbId(rs.getInt("userid"));
                            customers.add(cust);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbRestaurantsManagement.class.getName()).log(Level.SEVERE, null, ex);
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
        
        
        
        return customers;
    }
}
