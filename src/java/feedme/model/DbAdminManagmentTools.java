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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NadavBismuth
 */
public class DbAdminManagmentTools {
    
    private DbMenuManagment dbm;
    private DbUsersManagement dbu;
    private DbRestaurantsManagement dbr;
    private Connection con;
    private CallableStatement cstmt;

    public DbAdminManagmentTools() {
        
        dbm = new DbMenuManagment();
        dbu = new DbUsersManagement();
        dbr = new DbRestaurantsManagement();
        con = DbConnector.getInstance().getConn();
    }

    public DbMenuManagment getDbm() {
        return dbm;
    }

    public void setDbm(DbMenuManagment dbm) {
        this.dbm = dbm;
    }

    public DbUsersManagement getDbu() {
        return dbu;
    }

    public void setDbu(DbUsersManagement dbu) {
        this.dbu = dbu;
    }

    public DbRestaurantsManagement getDbr() {
        return dbr;
    }

    public void setDbr(DbRestaurantsManagement dbr) {
        this.dbr = dbr;
    }
    
    /**
     * get list of users by specific role , 0 -customer , 1 -manager ,2-admin ,3 - yopt
     * @param role int , wanted users role
     * @return list of users
     */
    public List<User> getAllUsersByRole( int role)
    {
        String spuName = "{CALL `feedmedb`.`Spu_GetAllUsersByRole`(?)}";
        con = DbConnector.getInstance().getConn();
        List<User> users = null ;
        ResultSet rs;
        try {
            
            users = new ArrayList<>();
            cstmt = con.prepareCall(spuName);
            cstmt.clearParameters();
            cstmt.setInt(1, role);
            rs = cstmt.executeQuery();
        switch(role)
        {
            case 0:
                
               
                while(rs.next())
                {
                     User user = new Customer(rs.getString("firstname"),rs.getString("lastname"), rs.getString("username") ,   rs.getString("phone") ,  rs.getString("email") ,
                            rs.getInt("role" ) , rs.getDate("bday") ,rs.getString("street")  ,rs.getString("house_num") , rs.getString("apartment_num") , rs.getString("city") );
                            user.setDbId(rs.getInt("userid"));
                    users.add(user);
                }
                break;
            case 1:
                while(rs.next())
                {
                       User user = new Manager(rs.getString("firstname"),rs.getString("lastname"), rs.getString("username") ,   rs.getString("phone") ,  rs.getString("email") ,
                            rs.getInt("role" ));
                    user.setDbId(rs.getInt("userid"));
                    users.add(user);
                }
             
                break;
            case 2 :
                
                while(rs.next())
                {
                    User    user = new Admin(rs.getString("firstname"),rs.getString("lastname"), rs.getString("username") ,  rs.getString("phone") ,  rs.getString("email") ,
                            rs.getInt("role" ));
                    user.setDbId(rs.getInt("userid"));
                    users.add(user);
                }
                break;
        }
        } catch (SQLException ex) {
            Logger.getLogger(DbAdminManagmentTools.class.getName()).log(Level.SEVERE, null, ex);
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
       
        return users;
    }
    
    
    
}
