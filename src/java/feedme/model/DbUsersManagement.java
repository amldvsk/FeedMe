/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedme.model;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.CallableStatement;;
import java.sql.Connection;
import java.sql.Date;
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
     * @param all user details 
     * @return int 0 - username is already in use , 1 - email is already in use , 2 - user add successfully 
     */
    public int addNewUser(String firstName, String lastName, String userName, String pw, String phone, String email, int role
    ,Date bDay , String street , String houseNum, String apartNum ,String city)
    {
        String spuName = "{call feedmedb.Spu_UserRegistration(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        int result = -1;
      
        try {
                byte[] salt = PasswordEncryptionService.generateSalt();
                 byte[] userPw = PasswordEncryptionService.getEncryptedPassword(pw, salt);
                cstmt = con.prepareCall(spuName);
                cstmt.setString(1, firstName);
                cstmt.setString(2, lastName);
                cstmt.setString(3, userName);
                cstmt.setBytes(4, userPw);
                cstmt.setBytes(5, salt);
                cstmt.setString(6, phone);
                cstmt.setString(7, email);
                cstmt.setInt(8, role);
                cstmt.setDate(9,bDay);
                cstmt.setString(10, street);
                cstmt.setString(11, houseNum);
                cstmt.setString(12,apartNum);
                cstmt.setString(13, city);
                cstmt.registerOutParameter(14, java.sql.Types.INTEGER);
                cstmt.executeUpdate();
                result = cstmt.getInt(14);
                
        } catch (SQLException ex) {
            Logger.getLogger(DbUsersManagement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(DbUsersManagement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
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
    
    /***
     * delete user from the data base by dbid using store procedure 
     * @param dbid the dbid of the user we want to delete
     * @return int 0 for dosn't find this dbid , 1 for success  , -1 dos'nt call to the store procedure
     */
    public int deleteUser(int dbid )
    {
        int result =-1;
        Connection c = DbConnector.getInstance().getConn();
        String spuName = "{ call feedmedb.Spu_DeleteUser( ? )}";
        try {
            
            cstmt =c.prepareCall(spuName);
            cstmt.clearParameters();
            cstmt.setInt(1, dbid);
            result = cstmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(DbUsersManagement.class.getName()).log(Level.SEVERE, null, ex);
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
     * get specific user from the date base by his name
     * @param userName the user name of the users we want to get
     * @return user according the role , manager,customer,admin
     */
    public User getUserByUserName(String userName)
    {
        String spuName = "{ call feedmedb.Spu_GetUsersByUserName( ? )}";
        User user = null;
        ResultSet rs ;
        Connection c = DbConnector.getInstance().getConn();
        try {
            
            cstmt = c.prepareCall(spuName);
            cstmt.clearParameters();
            cstmt.setString(1,userName);
            rs = cstmt.executeQuery();
            if(rs.next() != false){
            int role = rs.getInt("role" );
            switch(role)
            {
                case 0 :
                    user = new Customer(rs.getString("username"),rs.getString("lastname"), rs.getString("username") ,   rs.getString("phone") ,  rs.getString("email") ,
                            rs.getInt("role" ) , rs.getDate("bday") ,rs.getString("street")  ,rs.getString("house_num") , rs.getString("apartment_num") , rs.getString("city") );
                            user.setDbId(rs.getInt("userid"));
                       
                            
                    break;
                case 1:
                    user = new Manager(rs.getString("username"),rs.getString("lastname"), rs.getString("username") ,   rs.getString("phone") ,  rs.getString("email") ,
                            rs.getInt("role" ));
                    user.setDbId(rs.getInt("userid"));
                    
                    break;
                case 2:
                    
                     user = new Admin(rs.getString("username"),rs.getString("lastname"), rs.getString("username") ,  rs.getString("phone") ,  rs.getString("email") ,
                            rs.getInt("role" ));
                    user.setDbId(rs.getInt("userid"));
                    break;
            }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbUsersManagement.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
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
        return user;
    }
    
    
    /**
     *when the user try to login  checks if the user name exists
     * @param userName - wanted user name
     * @return 1 if user name exists , - 1 if not
     */
    public int checkIfUserExists(String userName)
    {
        int result =0;
        Connection c = DbConnector.getInstance().getConn();
        String spuName = "{CALL feedmedb.Spu_CheckIfExists(?, ?)}";
        
        try {
                cstmt = c.prepareCall(spuName);
                cstmt.clearParameters();
                cstmt.setString(1,userName);
                cstmt.executeUpdate();
                result = cstmt.getInt(2);
                cstmt.registerOutParameter(2, java.sql.Types.INTEGER);
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
         return result;
    }
    
    
    /**
     * part of the user login service  , get the user encrypted password and salt 
     * @param userName - the wanted username
     * @return  list of byte , list[0] - is the user encrypted password , list[1] - user salt 
     */
     public  List<byte[]> getUserEncryptedPassword(String userName)
    {
        List<byte[]> paSalt = new ArrayList<>();
        Connection c = DbConnector.getInstance().getConn();
        ResultSet rs = null;
        String spuName = "{ CALL feedmedb.Spu_UserAuthentication(?)}";
        try {
                cstmt = c.prepareCall(spuName);
                cstmt.clearParameters();
                cstmt.setString(1,userName);
                rs = cstmt.executeQuery();
                while(rs.next())
                {
                    paSalt.add(rs.getBytes("pw"));
                    paSalt.add(rs.getBytes("salt"));
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
       
        
        return paSalt;
    }
    
}
