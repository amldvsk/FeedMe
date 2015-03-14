/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedme.model;

import java.sql.CallableStatement;;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NadavBismuth
 */
public class DbUsersManagement {
    
    private Connection con = null;
    CallableStatement cstmt = null;
    
    public DbUsersManagement()
    {
        con = DbConnector.getInstance().getConn();
    }
    
    public int AddNewUser(User newUser)
    {
        String spuName = "{call call feedmedb.Spu_UserRegistration(?,?,?,?,?,?,?,?,?,?,?,?,?) ";
        int result = -1;
       
        try {
                cstmt =con.prepareCall(spuName);
                cstmt.setString(1, newUser.getFirstName());
                cstmt.setString(2, newUser.getLastName());
                cstmt.setString(3, newUser.getUserName());
                cstmt.setString(4, newUser.getPw());
                cstmt.setString(5, newUser.getPhone());
                cstmt.setString(6, newUser.getEmail());
                cstmt.setInt(7, newUser.getRole());
                if(newUser.getRole() == 0)
                {
                    
                    Customer nc = (Customer)newUser;
                    
                    cstmt.setDate(8,nc.getbDay());
                    cstmt.setString(9, nc.getStreet());
                    cstmt.setString(10, nc.getHouseNum());
                    cstmt.setString(11, nc.getApartNum());
                    cstmt.setString(12, nc.getCity());
                 }
                else
                {
                     cstmt.setDate(8,null);
                    cstmt.setString(9, null);
                    cstmt.setString(10, null);
                    cstmt.setString(11, null);
                    cstmt.setString(12, null);
                }
                cstmt.registerOutParameter(13, java.sql.Types.INTEGER);
                cstmt.executeUpdate();
                result = cstmt.getInt(13);
                
        } catch (SQLException ex) {
            Logger.getLogger(DbUsersManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DbUsersManagement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return result;
    }
    
    public int deleteUser(int dbid , int userRole)
    {
        return 0;
        
    }
    
    
}
