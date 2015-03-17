/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedme.model;

import java.sql.CallableStatement;;
import java.sql.Connection;
import java.sql.ResultSet;
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
    /***
     * Constructor
     */
    public DbUsersManagement()
    {
        con = DbConnector.getInstance().getConn();
    }
    
    /***
     * add new user to the deta base using store procedure
     * @param newUser User object the new user we want to add 
     * @return int 0 - username is already in use , 1 - email is already in use , 2 - user add successfully 
     */
    public int AddNewUser(User newUser)
    {
        String spuName = "{call call feedmedb.Spu_UserRegistration(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
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
                //check if it's customer and addind the extra parameters
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
    
    /***
     * delete user from the data base by dbid using store procedure 
     * @param dbid the dbid of the user we want to delete
     * @return int 0 for dosn't find this dbid , 1 for success  , -1 dos'nt call to the store procedure
     */
    public int deleteUser(int dbid )
    {
        int result =-1;
        String spuName = "{ call feedmedb.Spu_DeleteUser( ? )}";
        try {
            cstmt.clearParameters();
            cstmt =con.prepareCall(spuName);
            cstmt.setInt(1, dbid);
            result = cstmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(DbUsersManagement.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
        
    }
    
    /***
     * get specific user from the date base by his name
     * @param userName the user name of the users we want to get
     * @return user according the role , manager,customer,admin
     */
    public User getUserByUserName(String userName)
    {
        String spuName = "{ Spu_GetUsersByUserName( ? )}";
        User user = null;
        ResultSet rs ;
        try {
            cstmt.clearParameters();
            cstmt =con.prepareCall(spuName);
            cstmt.setString(1,userName);
            rs = cstmt.executeQuery();
            if(rs.next() != false){
            int role = rs.getInt("role" );
            switch(role)
            {
                case 0 :
                    user = new Customer(rs.getString("username"),rs.getString("lastname"), rs.getString("username") , rs.getString("pw") ,  rs.getString("phone") ,  rs.getString("email") ,
                            rs.getInt("role" ) , rs.getDate("bday") ,rs.getString("street")  ,rs.getString("house_num") , rs.getString("apartment_num") , rs.getString("city") );
                            user.setDbId(rs.getInt("pkid"));
                       
                            
                    break;
                case 1:
                    user = new Manager(rs.getString("username"),rs.getString("lastname"), rs.getString("username") , rs.getString("pw") ,  rs.getString("phone") ,  rs.getString("email") ,
                            rs.getInt("role" ));
                    user.setDbId(rs.getInt("pkid"));
                    
                    break;
                case 2:
                    
                     user = new Admin(rs.getString("username"),rs.getString("lastname"), rs.getString("username") , rs.getString("pw") ,  rs.getString("phone") ,  rs.getString("email") ,
                            rs.getInt("role" ));
                    user.setDbId(rs.getInt("pkid"));
                    break;
            }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbUsersManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
    
    
    
}
