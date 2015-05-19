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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NadavBismuth
 */
public class DbOrderManagement {
    
    private Connection con = null;
    CallableStatement cstmt = null;
    ResultSet rs = null;

    public DbOrderManagement() {
        
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
            Logger.getLogger(DbOrderManagement.class.getName()).log(Level.SEVERE, null, ex);
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
    
    /**
     * get item by his id 
     * @param itemId - int item id
     * @return item object with 1 qunatity , if we got sql exception item will be null , don't forget to check it
     * see example in test class
     */
    public Item getItemById(int itemId)
    {
        String spuName = "{CALL feedmedb.Spu_GetItemById(?)}";
        con = DbConnector.getInstance().getConn();
        Item item = null;
        ResultSet rs = null;
        try {
            
                cstmt = con.prepareCall(spuName);
                cstmt.clearParameters();
                cstmt.setInt(1, itemId);
                rs = cstmt.executeQuery();
                while(rs.next())
                {
                    item = new Item(rs.getString("item_name"), rs.getDouble("item_price"), rs.getString("item_description"), rs.getString("item_image"));
                    item.setItemID(rs.getInt("itemid"));
                    item.setQuantity(1);
                    
                }
                
        } catch (SQLException ex) {
            Logger.getLogger(DbOrderManagement.class.getName()).log(Level.SEVERE, null, ex);
            item = null;
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
        
        return item;
    }
        private List<Integer> getOrdersNumByRestId(int restId)
    {
        List<Integer> ordersNum = new ArrayList<Integer>();
        String spuName = "{CALL feedmedb.Spu_GetAllOrdersNumByRestId(?)}";
        con = DbConnector.getInstance().getConn();
        ResultSet rs = null;
        try {
            cstmt = con.prepareCall(spuName);
            cstmt.clearParameters();
            cstmt.setInt(1, restId);
            rs = cstmt.executeQuery();
            while(rs.next())
            {
                ordersNum.add(rs.getInt("order_id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbOrderManagement.class.getName()).log(Level.SEVERE, null, ex);
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
        return ordersNum;
    }
    
    private HashMap<HashMapKey , Item> getItemsByOrderIdAndRestId(int restId , int orderId)
    {
        //List<Item> orderItems = new ArrayList<>();
        String spuName = "{CALL feedmedb.Spu_GetItemsByOIdAndRId(?, ?)}";
        con = DbConnector.getInstance().getConn();
        HashMap<HashMapKey , Item> orderItems = new HashMap<>();
        ResultSet rs = null;
        
        try {
            cstmt = con.prepareCall(spuName);
            cstmt.clearParameters();
            cstmt.setInt(1, restId);
            cstmt.setInt(2, orderId);
            rs = cstmt.executeQuery();
            while(rs.next())
            {
                Item newItem = new Item(rs.getInt("item_id"), rs.getString("item_name"), rs.getDouble("item_price"), rs.getInt("item_quant"));
                orderItems.put(new HashMapKey(restId, newItem.getItemID()), newItem);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbOrderManagement.class.getName()).log(Level.SEVERE, null, ex);
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
        
        return orderItems;
    }
    public List<Order> getOrdersByRestId(int restId)
    {
        List<Order> orders = new ArrayList<>();
        String spuName = "{CALL feedmedb.Spu_GetCustomerDetailsByOrderAndRest(?,?)}";
        
        ResultSet rs = null;
        Order currOrd;
        List<Integer> ordersNum = getOrdersNumByRestId(restId);
        try {
        if(ordersNum != null){
        for(Integer oNum : ordersNum){
        
            
            con = DbConnector.getInstance().getConn();
            cstmt = con.prepareCall(spuName);
            cstmt.clearParameters();
            cstmt.setInt(1, restId);
            cstmt.setInt(2, oNum);
            rs = cstmt.executeQuery();
            while(rs.next())
            {
                currOrd = new Order(rs.getInt("user_id") , rs.getString("user_fn") , rs.getString("user_phone") , rs.getString("user_add"));
                currOrd.setOrderDateAndTime(rs.getTimestamp("order_date"));
                currOrd.setOrderId(rs.getInt("order_id"));
                currOrd.setRestItemsMap(getItemsByOrderIdAndRestId(restId , currOrd.getOrderId()));
                orders.add(currOrd);
            }
            currOrd = null;
            rs = null;
        
        
        
        
        
        }
        }

        } catch (SQLException ex) {
            Logger.getLogger(DbOrderManagement.class.getName()).log(Level.SEVERE, null, ex);
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
        return orders;
        
        
    }
}
