/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedme.model;

import java.sql.CallableStatement;
import java.sql.Connection;

/**
 *
 * @author NadavBismuth
 */
public class DBOrderManagement {
    
    private Connection con = null;
    CallableStatement cstmt = null;

    public DBOrderManagement() {
        
            con = DbConnector.getInstance().getConn();
    }
    
    public int addNewOrder()
    {
        int result = 0;
        
        return result;
    }
    
    
}
