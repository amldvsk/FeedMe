/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedme.model;

import feedme.controller.UserRegistrationServlet;
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
        
        //encryptTest();
        insertUserTest();
        //insertRestaurantTest();
       //addNewCatTest();
        //deleteUserTest();
       // updateUserTest();
        //updateRestTest();
        //addNewMenuTest();
        //addNewMenuCatTest();
        //updateMenuCatTest();
        //addNewItemToMenuTest();
        //updateMenuItemTest();
        
     
    
    }
    public static void insertUserTest()
    {
          User user = null;              
         Date date =  null;
         String street = "alya";
         String houseNum = "38";
         String apartmentNum = null;
         String city = null;
         String firstName = "אש כרוב ר";//request.getParameter("firstName");
         String lastName = "bidf3gfdgton";//request.getParameter("lastName");
         String userName = "n12345adav";//request.getParameter("userName");
         String pw = "123";//request.getParameter("pw");
         String phone = "1111";//request.getParameter("phone");
         String email = "df13fg@gf45gmail.com";//request.getParameter("email");
         int role = 0;//Integer.parseInt(request.getParameter("role"));
         
         
         DbUsersManagement dbUserManagment = new DbUsersManagement();
         int result = dbUserManagment.addNewUser(firstName,lastName,userName,pw,phone,email,role,date,street,houseNum,apartmentNum,city);
         if(result == 2)
         {
            
             switch(role)
             {
                 case 0:
                      user  = (Customer)dbUserManagment.getUserByUserName(userName);
                        break;
                 case 1:
                      user  = (Manager)dbUserManagment.getUserByUserName(userName);
                     break;
                 case 2:
                       user  = (Admin)dbUserManagment.getUserByUserName(userName);
                        break;
             }
         }
    }
    
    public static void updateUser()
    {
        //checks update user with customer
        Customer user ;
        DbUsersManagement dbUserManagment = new DbUsersManagement();
        user  =  (Customer)dbUserManagment.getUserByUserName("nadav");
        user.setFirstName("Nadavi");
        user.setHouseNum("41");
        int result = dbUserManagment.updateUser(user);
        System.out.println(result);
    }
    public static void insertRestaurantTest()
    {
        String name ="davidsan";
        String phone = "0548235258";
        String logo = "jdsejds.jpg";
        String street = "rehavat";
        String streetNum = "13";
        int catid = 1;
        String city = "Beer-Sheva";
        int deliveryPrice = 15;
        int minOrder =50;
        String estimatedTimeDel = "שעתיים";
    
        DBRestaurantsManagement rm = new DBRestaurantsManagement();
        int res =  rm.addNewRestaurant(name,catid, phone, logo, street, streetNum, city, deliveryPrice, minOrder, estimatedTimeDel);
        System.out.println(res);
    }

    
    public static void  updateRestTest()
    {
         String name ="davidsan";
        String phone = "0548235258";
        String logo = "jdsejds.jpg";
        String street = "rehavat";
        String streetNum = "13";
        int catid = 1;
        String city = "Beer-Sheva";
        int deliveryPrice = 15;
        int minOrder =50;
        String estimatedTimeDel = "שעתיים";
        Restaurant rest = new Restaurant("Kampai", phone, logo, street, streetNum, city, deliveryPrice, minOrder, estimatedTimeDel);
        rest.setDbid(8);
        DBRestaurantsManagement rm = new DBRestaurantsManagement();
        int result = rm.updateRestaurant(rest, 1);
        System.out.println(result);
        
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
    
    
    public static  void addNewCatTest()
    {
        String cat = "ביצים";
        DBRestaurantsManagement rm = new DBRestaurantsManagement();
        int result = rm.addNewCategory(cat);
        System.out.println(result);
    }
    
    public static void deleteUserTest()
    {
        DbUsersManagement dbUserManagment = new DbUsersManagement();
        int result = dbUserManagment.deleteUser(2);
        System.out.println(result);
    }

    
    public static void addNewMenuTest()
    {
        String menuName = "zoooo dugmaaaa";
        int restId = 5;
        DBMenuManagment dbM = new DBMenuManagment();
        int result = dbM.addNewMenu(menuName, restId);
        System.out.println(result);
    }
    
    public static void addNewMenuCatTest()
    {
        String catName = "קינוחים";
        DBMenuManagment dbM = new DBMenuManagment();
        int result = dbM.addNewMenuCat(catName);
        System.out.println(result);
    }
    
    
    public static void updateMenuCatTest()
    {
        String newCatName = "קינוחים";
        int catId  = 2;
        DBMenuManagment dbM = new DBMenuManagment();
        int result = dbM.updateMenuCat(newCatName, catId);
        System.out.println(result);
    }
    
    public static void addNewItemToMenuTest()
    {
        String itemName = "פפסי";
        double itemPrice = 9.5;
        String itemDic = "סתם שתייה מסריחה";
        String imagePath = null;
        int itemCatId = 3 ;
        int itemRestId = 5;
        int itemMenuId = 2 ;
        
        DBMenuManagment dbM = new DBMenuManagment();
        int result = dbM.AddNewMenuItem(itemName, itemPrice, itemDic, imagePath,itemCatId, itemRestId, itemMenuId);
        System.out.println(result);
        
        
        
    }
    
    public static void updateMenuItemTest()
    {
        int itemId = 2 ;
        String itemName = "פפסי";
        double itemPrice = 9.5;
        String itemDic = "סתם שתייה מסריחה";
        String imagePath = null;
        
        DBMenuManagment dbM = new DBMenuManagment();
        int result = dbM.updateMenuItem(itemId, itemName, itemPrice, itemDic, imagePath);
        System.out.println(result);
    }
}
