/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedme.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NadavBismuth
 */
public class DbHPOnLoad {
    
    private Connection con = null;
    CallableStatement cstmt = null;
    ResultSet rs = null;

    public DbHPOnLoad() {
        
        con = DbConnector.getInstance().getConn();
    }
    
    public HashMap<String , Integer > getCategories() 
    {
        con = DbConnector.getInstance().getConn();
        String spuName = "{ call feedmedb.Spu_GetCategories()}";
        HashMap<String , Integer > cat = new HashMap<>();
        try {
                
                cstmt =con.prepareCall(spuName);
                cstmt.clearParameters();
                rs = cstmt.executeQuery();
                while(rs.next())
                {
                    cat.put(rs.getString("catName"), rs.getInt("pkid"));
                }
            
        } catch (SQLException ex) {
            Logger.getLogger(DbHPOnLoad.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
            if(cstmt != null)
            {
                cstmt.close();
            }
            if( con != null)
            {
                con.close();
            }
            }catch(SQLException ex)
            {
                Logger.getLogger(DbHPOnLoad.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return cat;
        
    }
            
    
    public List<Restaurant> getRecentRestaurants(int numberOfRest)
    {
        con = DbConnector.getInstance().getConn();
        List<Restaurant> restaurants = new ArrayList<>();
        String spuName = "{CALL feedmedb.Spu_GetRecentRestaurant(?,?)}";
        ResultSet rs  = null;
        
        try {
            
            cstmt =con.prepareCall(spuName);
            cstmt.clearParameters();
            cstmt.setInt(1, numberOfRest);
            cstmt.setInt(2, 0);
            rs = cstmt.executeQuery();
            while(rs.next())
            {
                Restaurant res  = new Restaurant(rs.getString("name") , rs.getString("phone") , rs.getString("logo") ,
                rs.getString("street") , rs.getString("street_num") , rs.getString("city") ,rs.getInt("delivery_price"), rs.getInt("min_order"),rs.getString("estimated_time") );
                res.setDbid(rs.getInt("restid"));
                restaurants.add(res);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbHPOnLoad.class.getName()).log(Level.SEVERE, null, ex);
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
