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
import java.util.HashMap;
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
        String spuName = "{ call feedmedb.Spu_GetCategories()}";
        HashMap<String , Integer > cat = new HashMap<>();
        try {
                cstmt.clearParameters();
                cstmt =con.prepareCall(spuName);
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
            
    
    
    
}
