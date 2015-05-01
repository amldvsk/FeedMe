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
public class DBMenuManagment {
    
    private Connection con = null;
    CallableStatement cstmt = null;

    public DBMenuManagment() {
        
        con = DbConnector.getInstance().getConn();
    }
    
    /**
     * add new menu 
     * @param menuName = new menu name , should be the restname + Menu , for example , KampaiMenu , each time with add new restaurant we should add new menu to the same rest
     * @param restId = restaurant id 
     * @return  int , 0 if something went wrong like connection problem , 1 if the procedure was successful 
     */
    public int addNewMenu(String menuName , int restId)
    {
        int result = 0;
        con  = DbConnector.getInstance().getConn();
        String spuName = "{CALL feedmedb.Spu_AddNewMenu(?, ?)}";
        try {
                cstmt =con.prepareCall(spuName);
                cstmt.clearParameters();
                cstmt.setString(1, menuName);
                cstmt.setInt(2, restId);
                result = cstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBMenuManagment.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
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
    
    /***
     * 
     * @param menuName - new menu name
     * @param menuId - the menu id
     * @return int , -2 if something went wrong like connection problem , 1 if the procedure was successful  
     */
    public int updateMenuName(String menuName , int menuId)
    {
        int result = -1;
        con  = DbConnector.getInstance().getConn();
        String spuName = "{CALL feedmedb.Spu_UpdateMenuName(?,?)}";
        
        try {
            cstmt = con.prepareCall(spuName);
            cstmt.clearParameters();
            cstmt.setString(1, menuName);
            cstmt.setInt(2, menuId);
            result = cstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBMenuManagment.class.getName()).log(Level.SEVERE, null, ex);
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




        return result;
    }
    
    /**
     * add new menu cat , for example Desserts ,Main dishes
     * @param catName  = cat name  , i don't check if the name is already exists , it should be already checked in the servlet or html\jsp\another fucking web page
     * @return  int , 0 if something went wrong  , 1 if the procedure was successful
     */
    public int addNewMenuCat(String catName)
    {
        String spuName = "{CALL feedmedb.Spu_AddNewMenuCategory(?)}";
        int result = 0;
        con  = DbConnector.getInstance().getConn();
        
        try {
                cstmt =con.prepareCall(spuName);
                cstmt.clearParameters();
                cstmt.setString(1, catName);
                result = cstmt.executeUpdate();
                
        } catch (SQLException ex) {
            Logger.getLogger(DBMenuManagment.class.getName()).log(Level.SEVERE, null, ex);
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
     * update existing menu category
     * @param newCatName  = the name we want , i don't check if the name is already exists , it should be already checked in the servlet or html\jsp\another fucking web page
     * @param catId = the category id 
     * @return int , 0 if something went wrong  , 1 if the procedure was successful
     */
    public int updateMenuCat(String newCatName , int catId)
    {
        String spuName = "{CALL feedmedb.Spu_UpdateMenuCat(? , ?)}";
        int result = 0;
        con  = DbConnector.getInstance().getConn();
        
        try {
                cstmt =con.prepareCall(spuName);
                cstmt.clearParameters();
                cstmt.setString(1, newCatName);
                cstmt.setInt(2,catId);
                result = cstmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(DBMenuManagment.class.getName()).log(Level.SEVERE, null, ex);
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
     * add new item to specific menu , if the item is already exists in another menu we just take the fk ( item exists if the item name and the item price is equale)
     * @param itemName  =  String item name ,see example in test class
     * @param itemPrice = Double item price
     * @param itemDescrip = String item discription , see example in test class - can be null
     * @param itemImagePath = String the item image  - can be null
     * @param itemMenuCatId = int the item category , like Drinks - > Pepsi , see example in test class
     * @param itemRestId = int item restaurant id 
     * @param itemMenuId = int item menu id 
     * @return int , 0 if something went wrong , 1 if the procedure was successful
     */
    public int AddNewMenuItem(String itemName , double itemPrice , String itemDescrip , String itemImagePath , int itemMenuCatId , int itemRestId , int itemMenuId)
    {
        
        String spuName = "{CALL feedmedb.Spu_AddNewItem(? ,?, ?,?, ?, ?, ?)}";
        int result = 0;
        con  = DbConnector.getInstance().getConn(); 
        
        try {
                     cstmt =con.prepareCall(spuName);
                     cstmt.clearParameters();
                     cstmt.setString(1, itemName);
                     cstmt.setDouble(2, itemPrice);
                     cstmt.setString(3, itemDescrip);
                     cstmt.setString(4 , itemImagePath);
                     cstmt.setInt(5, itemMenuCatId);
                     cstmt.setInt(6, itemRestId);
                     cstmt.setInt(7, itemMenuId);
                     result = cstmt.executeUpdate();
                     
        } catch (SQLException ex) {
            Logger.getLogger(DBMenuManagment.class.getName()).log(Level.SEVERE, null, ex);
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
     * update specific item from the menu.
     * i don't check if the item is already exists , 
     * it should be already checked in the servlet or html\jsp\another fucking web page.
     * see example in test class , don't ask stupid questions
     * @param itemId =  int item id 
     * @param itemName = String item new name
     * @param itemPrice = double item new price
     * @param itemDescrip = String item new description
     * @param itemImagePath = String item new image
     * @return int , 0 if something went wrong , 1 if the procedure was successful
     */
    public int updateMenuItem(int itemId ,String itemName , double itemPrice , String itemDescrip , String itemImagePath )
    {
        String spuName = "{CALL feedmedb.Spu_UpdateItem(?, ?, ?, ? , ?)}";
        int result = 0;
        con  = DbConnector.getInstance().getConn(); 
        
        try {
                cstmt =con.prepareCall(spuName);
                cstmt.clearParameters();
                cstmt.setInt(1, itemId);
                cstmt.setString(2, itemName);
                cstmt.setDouble(3, itemPrice);
                cstmt.setString(4, itemDescrip);
                cstmt.setString(5 , itemImagePath);
                result = cstmt.executeUpdate();
                
        } catch (SQLException ex) {
            Logger.getLogger(DBMenuManagment.class.getName()).log(Level.SEVERE, null, ex);
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
     * delete item from menu (not from item because maybe this itemrelated to another menu)
     * @param menuId - menuiId
     * @param itemId - item id
     * @return int , -2 if we got sql exception , 1 if the procedure was successful
     */
    public int deleteItemFromMenu( int menuId , int itemId)
    {
        int result = -1 ;
        String spuName = "{CALL feedmedb.Spu_DeleteItemFromMenu(?,?)}";
        con  = DbConnector.getInstance().getConn();
        
        
        try {
            cstmt = con.prepareCall(spuName);
            cstmt.setInt(1,itemId);
            cstmt.setInt(2,menuId);
            result = cstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBMenuManagment.class.getName()).log(Level.SEVERE, null, ex);
            result = -2 ;
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
