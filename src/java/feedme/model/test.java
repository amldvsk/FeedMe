/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedme.model;

import feedme.controller.CustomerRegistrationServlet;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NadavBismuth
 */
public class test {
    
    public static void main(String [ ] args)
    {
        
        encryptTest();
     
    
    }
    public static void insertUserTest()
    {
          User user = null;              
         Date date =  null;
         String street = null;
         String houseNum = null;
         String apartmentNum = null;
         String city = null;
         String firstName = "sdd3dמ3יר";//request.getParameter("firstName");
         String lastName = "bidf3gfdgton";//request.getParameter("lastName");
         String userName = "e3331sd23rweכגfh";//request.getParameter("userName");
         String pw = "123";//request.getParameter("pw");
         String phone = "1111";//request.getParameter("phone");
         String email = "df1312wg345@gf45gmail.com";//request.getParameter("email");
         int role = 2;//Integer.parseInt(request.getParameter("role"));
         
         
         DbUsersManagement dbUserManagment = new DbUsersManagement();
         int result = dbUserManagment.addNewUser(firstName,lastName,userName,pw,phone,email,role,date,street,houseNum,apartmentNum,city);
         if(result == 2)
         {
            
             switch(role)
             {
                 case 0:
                      user  = (Customer)dbUserManagment.getUserByUserName(userName);
                     
                 case 1:
                      user  = (Manager)dbUserManagment.getUserByUserName(userName);
                     break;
                 case 2:
                       user  = (Admin)dbUserManagment.getUserByUserName(userName);
                        break;
             }
         }
    }
    
    public static void insertRestaurantTest()
    {
        String name ="easldsi";
        String phone = "0548555258";
        String logo = "jdsejds.jpg";
        String street = "rehavat";
        String streetNum = "13";
        String city = "Beer-Sheva";
        int deliveryPrice = 15;
        int minOrder =50;
        String estimatedTimeDel = "שעתיים";
    
        DBRestaurantsManagement rm = new DBRestaurantsManagement();
        int res =  rm.addNewRestaurant(name, phone, logo, street, streetNum, city, deliveryPrice, minOrder, estimatedTimeDel);
        System.out.println(res);
    }

    public static void  encryptTest()
    {
        
       
        byte[] autenticPw  = null;
        byte[] autenticSalt = null;
        
         ResultSet rs;
         String driverName = "com.mysql.jdbc.Driver"; 
        String url = "jdbc:mysql://localhost/encrtray";
        String password = "123456789";
         String userName = "root";
        Connection con = null;
        CallableStatement cstmt = null;
        CallableStatement cstmt2 = null;
        String Spu_Name  = "{CALL encrtray.encrypt_test(?,?, ?)}";
        String Spu_Name2 = "{CALL encrtray.Authenticate(?)}";
         try {
             Class.forName(driverName);
  
             
             con = DriverManager.getConnection(url, userName, password);
                 byte[] salt = PasswordEncryptionService.generateSalt();
                String username = "nadav";
                byte[] pass =  PasswordEncryptionService.getEncryptedPassword("Makara" , salt);
             
                /*cstmt = con.prepareCall(Spu_Name);
                cstmt.setString(1, username);
                cstmt.setBytes(2, pass);
                cstmt.setBytes(3, salt);
                cstmt.executeUpdate();*/
                cstmt2 = con.prepareCall(Spu_Name2);
                cstmt2.setString(1, userName);
              rs =  cstmt2.executeQuery("SELECT encpassword , salt FROM userse where username = 'nadav';");
              while(rs.next()){
              autenticPw = rs.getBytes("encpassword");
              autenticSalt = rs.getBytes("salt");
             }
              boolean b = PasswordEncryptionService.authenticate("Masdskara", autenticPw, autenticSalt);
              if(b == true)
              {
                  System.out.println("Hi Hi Mother Fucker");
              }
              else
                  System.out.println("Biatchchchchch");
                  
              
              
                 
             
        
        } catch (ClassNotFoundException | SQLException ex) {
            
            Logger.getLogger(DbConnector.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Db Connection Constructor exception  Mother Fucker");
            ex.printStackTrace();
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
         finally
         {
             if(cstmt != null)
             {
                 try {
                     cstmt.close();
                 } catch (SQLException ex) {
                     Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
             
             if(con != null)
             {
                 try {
                     con.close();
                 } catch (SQLException ex) {
                     Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
         }
         
         
        
        
    }
    
    
      

}