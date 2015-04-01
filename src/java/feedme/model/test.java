/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedme.model;

import feedme.controller.CustomerRegistrationServlet;
import java.sql.Date;
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
        /* User user = null;              
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
    */
        
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
}
