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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
        //insertUserTest();
        //insertRestaurantTest();
       //addNewCatTest();
        //deleteUserTest();
       // updateUserTest();
        //updateRestTest();
        //addNewMenuTest();
        //addNewMenuCatTest();
        //updateMenuCatTest();
        //addNewItemToMenuTest();
         getRestaurantOrdersTest(5);
        //deleteItemFromMenuTest();
        //updateMenuItemTest();
        //getLatestOrderNumTest();
        //deleteRestTest();
        //updateMenuTest();
        //getResturantsCitiesTest();
        //getRecentRestaurantsByCat();
        //getNextRestaurantsByCat();
        //getItemMenu();
        //getUsersByRoleTest(0);   

     
    
    }
    public static void insertUserTest()
    {
          User user = null;              
         Date date =  null;
         String street = null;//"alya";
         String houseNum = null;//"38";
         String apartmentNum = null;
         String city = null;
         String firstName = "שמעון";//request.getParameter("firstName");
         String lastName = "bidf3gfdgton";//request.getParameter("lastName");
         String userName = "דיןצםמ";//request.getParameter("userName");
         String pw = "123";//request.getParameter("pw");
         String phone = "1111";//request.getParameter("phone");
         String email = "d12gf45gmail.com";//request.getParameter("email");
         int role = 1;//Integer.parseInt(request.getParameter("role"));
         
         
         DbUsersManagement dbUserManagment = new DbUsersManagement();
         int result = dbUserManagment.addNewUser(firstName,lastName,userName,pw,phone,email,role,date,street,houseNum,apartmentNum,city );
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
        String name ="הכרם";
        String phone = "0546325288";
        String logo = "jdse43s.jpg";
        String street = "reht";
        String streetNum = "1";
        int catid = 2;
        String city = "Beer-Sheva";
        int deliveryPrice = 15;
        int minOrder =50;
        String estimatedTimeDel = "שעתיים";
    
        DbRestaurantsManagement rm = new DbRestaurantsManagement();
        int res =  rm.addNewRestaurant(name,catid, phone, logo, street, streetNum, city, deliveryPrice, minOrder, estimatedTimeDel ,1);
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
        int managerId = 1;
        Restaurant rest = new Restaurant("Kampai", phone, logo, street, streetNum, city, deliveryPrice, minOrder, estimatedTimeDel);
        rest.setDbid(8);
        DbRestaurantsManagement rm = new DbRestaurantsManagement();
        int result = rm.updateRestaurant(rest, 1 , managerId);
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
        DbRestaurantsManagement rm = new DbRestaurantsManagement();
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
        DbMenuManagment dbM = new DbMenuManagment();
        int result = dbM.addNewMenu(menuName, restId);
        System.out.println(result);
    }
    
    
    public static void updateMenuTest()
    {
        int menuId = 2 ;
        String menuName = "BallsMenu";
        DbMenuManagment DBM = new DbMenuManagment();
        int result = DBM.updateMenuName(menuName, menuId);
        System.out.println(result);
    }
    public static void addNewMenuCatTest()
    {
        String catName = "קינוחים";
        DbMenuManagment dbM = new DbMenuManagment();
        int result = dbM.addNewMenuCat(catName);
        System.out.println(result);
    }
    
    
    public static void updateMenuCatTest()
    {
        String newCatName = "קינוחים";
        int catId  = 2;
        DbMenuManagment dbM = new DbMenuManagment();
        int result = dbM.updateMenuCat(newCatName, catId);
        System.out.println(result);
    }
    
    public static void addNewItemToMenuTest()
    {
        String itemName = "פפסי";
        double itemPrice = 9.5;
        String itemDic = "סתם שתייה מסריחה";
        String imagePath = null;
        int itemCatId = 2 ;
        int itemRestId = 6;
        int itemMenuId = 2 ;
        
        DbMenuManagment dbM = new DbMenuManagment();
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
        
        DbMenuManagment dbM = new DbMenuManagment();
        int result = dbM.updateMenuItem(itemId, itemName, itemPrice, itemDic, imagePath);
        System.out.println(result);
    }
    
    
    public static void getLatestOrderNumTest()
    {
         DbOrderManagement DBO = new DbOrderManagement();
         int orderNum = DBO.setLatestOrderNum();
         System.out.println(orderNum);
    }
    
    public static void deleteRestTest()
    {
        int restId = 7 ;
        DbRestaurantsManagement DBR = new DbRestaurantsManagement();
        int result = DBR.deleteRestaurant(restId);
        System.out.println(result);
    }
    
    public static void deleteItemFromMenuTest()
    {
        int menuId = 2;
        int itemId = 2;
        DbMenuManagment dBM = new DbMenuManagment();
        int result = dBM.deleteItemFromMenu(menuId, itemId);
        System.err.println(result);
    }
    
    public static void getResturantsCitiesTest()
    {
        List<String> cities ;
        DbHPOnLoad dbph = new DbHPOnLoad();
        cities = dbph.getCities();
        System.out.println(cities);
        
    }
    
    public static void addNewOrderTest()
    {
        int restId1 ;
    }
    
    public static void getRecentRestaurantsByCat()
    {
        int catid = 2 ;
        DbRestaurantsManagement DBRM = new DbRestaurantsManagement();
        
        List<Restaurant> lRest = DBRM.getLatestRestaurantsByCat(3, catid);
        for(Restaurant rest : lRest  )
        {
            System.out.println(rest.getName());
        }
    }
    
    public static void getNextRestaurantsByCat()
    {
         int catid = 2 ;
        DbRestaurantsManagement DBRM = new DbRestaurantsManagement();
        
        List<Restaurant> lRest = DBRM.getNextRecentRestaurantsByCat(0,6, catid);
        for(Restaurant rest : lRest  )
        {
            System.out.println(rest.getName());
        }
    }
    
    public static void getItemMenu()
    {
       int  itemId =2 ;
       DbOrderManagement dbo = new DbOrderManagement();
       Item item = dbo.getItemById(itemId);
       if(item != null){
       System.out.println(item.getItemName());}
       else
            System.err.println("Biga Bu Gu La");
    }
    
    
    public static void getUsersByRoleTest( int role)
    {
        
        DbAdminManagmentTools dbam = new DbAdminManagmentTools();
        List<User> users = dbam.getAllUsersByRole(role);
        switch(role)
        {
            case 0:
                
                for(User user : users)
                {
                    Customer cust = (Customer)user;
                    System.out.println(cust.getbDay());
                }
                break;
            case 1:
                
                 for(User user : users)
                {
                    Manager mang = (Manager)user;
                    System.out.println(mang.getFirstName());
                }
                break;
            case 2:
                 for(User user : users)
                {
                    Admin admin = (Admin)user;
                    System.out.println(admin.getFirstName());
                }
                
                break;
        }
    }
    
    
    public static void getRestRank()
    {
        
    }
      public static void getRestaurantOrdersTest(int restId)
    {
        DbOrderManagement dbor = new DbOrderManagement();
        List<Order> orders = dbor.getOrdersByRestId(restId);
        for(Order order : orders)
        {
            System.out.println("customer id :" + order.getOrderCustomerId() + " customer full name : " + order.getCustomerFullName() + "Customer addres : " + order.getCustomerAdress() + "orders date : " + order.getOrderDateAndTime());
            Iterator it = order.getRestItemsMap().entrySet().iterator();
            while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry)it.next();
                    Item item= (Item)pair.getValue();
                    System.out.println("item  name is : " + item.getItemName() + " item price is : " + item.getItemPrice());
                    it.remove(); // avoids a ConcurrentModificationException
    }
        }
        
    }
      
      
    public static void getRestaurantsByManagerIdTest(int mangerId)
    {
        DbRestaurantsManagement dbr = new DbRestaurantsManagement();
        List<Restaurant> rest = dbr.getRestaurantsByManagerId(mangerId);
        for(Restaurant restaurant : rest)
        {
            System.out.println(restaurant.toString());
        }
    }
    
    
    
    public static void  getAllRestaurantsTest()
    {
        List<Restaurant> rest ;
        DbAdminManagmentTools dba = new DbAdminManagmentTools();
        rest = dba.getAllRestaurants();
        for(Restaurant re : rest)
        {
            System.out.println(re.toString());
        }
    }
}
