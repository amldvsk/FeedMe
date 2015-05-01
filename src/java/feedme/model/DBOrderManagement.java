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
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NadavBismuth
 */
public class DBOrderManagement {
    
    private Connection con = null;
    CallableStatement cstmt = null;
    ResultSet rs = null;

    public DBOrderManagement() {
        
            con = DbConnector.getInstance().getConn();
    }
    
    /***
     * (try to)add new order (can be from diffrent restaurants)
     * @param currOrd - order object , see order class
     * @return 1 if we entered all items in the order , -2 if we catch sql exception
     */
    public int addNewOrder(Order currOrd)
    {
        int result = -1;
        String spuName = "{CALL feedmedb.Spu_AddNewOrder(?, ?, ?, ?, ?, ?, ?, ?)}";
        int orderNum = setLatestOrderNum();
        int customerId = currOrd.getOrderCustomerId();
        String custFullName = currOrd.getCustomerFullName();
        String custAddr = currOrd.getCustomerAdress();
        String custPhone = currOrd.getCustomerPhonenum();
        con = DbConnector.getInstance().getConn();
        Iterator it = currOrd.getRestItemsMap().entrySet().iterator();
        currOrd.setOrderId(orderNum);
        try {
            result = 0;
            while(it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            int restId = (int)pair.getKey();
            Item item = (Item)pair.getValue();
            cstmt = con.prepareCall(spuName);
            cstmt.clearParameters();
            cstmt.setInt(1,currOrd.getOrderId());
            cstmt.setInt(2, item.getItemID());
            cstmt.setInt(3,restId);
            cstmt.setInt(4, customerId);
            cstmt.setString(5,custFullName);
            cstmt.setString(6 , custAddr);
            cstmt.setString(7 , custPhone);
            cstmt.setInt(8, item.getQuantity());
            result += cstmt.executeUpdate();
            
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBOrderManagement.class.getName()).log(Level.SEVERE, null, ex);
            result = -2;
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
        
        
        
        
        
        if(result == currOrd.getRestItemsMap().size())
        {
            result = 1;
        }
        return result;
    }
    
    public int setLatestOrderNum() 
    {
        String spuName = "{CALL feedmedb.Spu_GetLatestOrdernNum(?)}";
        int orderNum = -1;
        con = DbConnector.getInstance().getConn();
        
        try {
         
                cstmt = con.prepareCall(spuName);
                cstmt.clearParameters();
                cstmt.registerOutParameter(1, java.sql.Types.INTEGER);
                cstmt.executeUpdate();
                orderNum = cstmt.getInt(1);
                if(orderNum == 0)
                {
                    orderNum = 1000;
                }
                else
                {
                    orderNum++;
                }
                
        } catch (SQLException ex) {
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
        
        return orderNum;
    }
}
