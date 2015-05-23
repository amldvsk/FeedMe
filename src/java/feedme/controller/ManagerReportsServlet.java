/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedme.controller;

import feedme.model.AuthenticatUser;
import feedme.model.DbOrderManagement;
import feedme.model.DbRestaurantsManagement;
import feedme.model.HashMapKey;
import feedme.model.Item;
import feedme.model.Order;
import feedme.model.PasswordEncryptionService;
import feedme.model.Restaurant;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author david
 */
@WebServlet(name = "ManagerReportsServlet", urlPatterns = {"/manager/order_reports"})

public class ManagerReportsServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");       
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        // Check if user is :admin\manager\customer
        AuthenticatUser manager = (AuthenticatUser)request.getSession().getAttribute("AuthenticatUser");
        try {
            if(manager == null || !PasswordEncryptionService.authenticate(Integer.toString(1), manager.getEncrypRole(), "Manager".getBytes())|| manager.isLoginResult()== false) {
                response.sendRedirect(request.getContextPath() + "/");
                return;                
            }
            else{
                double totalPrice=0;
                Calendar now = Calendar.getInstance();
                DbRestaurantsManagement restaurant = new DbRestaurantsManagement();
                List<Restaurant> reslist = restaurant.getRestaurantsByManagerId(manager.getUserId());
                List<Order> orders = new DbOrderManagement().getOrdersByRestId(reslist.get(0).getDbid());
                //request.setAttribute("reslist", reslist);
                int counter=0;                
                Map<Date, String> dateAndNumOfOrders = new HashMap<Date, String>();
                Map<Date, String> dateAndPrice = new HashMap<Date, String>();
                for(Order ord:orders){
                    counter=0;
                    totalPrice=0;
                    //-----------------
                    long timestamp = ord.getOrderDateAndTime().getTime();
                    Calendar cal = Calendar.getInstance();                    
                    Date d = new Date(timestamp);
                    cal.setTime(d);
                    //============
                    int day=cal.get(Calendar.DATE);
                    int month= cal.get(Calendar.MONTH)+1;
                    int year=cal.get(Calendar.YEAR);                 
                    String fullDate= Integer.toString(day)+"/"+Integer.toString(month)+"/"+Integer.toString(year);//dd/MM/yyyy                   
                    if(dateAndNumOfOrders.get(fullDate)== null && month == now.get(Calendar.MONTH)+1 && year == now.get(Calendar.YEAR)){// check if the key is not exists
                       for(Order o:orders){
                            long timestampp = o.getOrderDateAndTime().getTime();
                            Calendar call = Calendar.getInstance();                    
                            Date dd = new Date(timestampp);
                            call.setTime(dd);
                            //============
                            int dayy=call.get(Calendar.DATE);
                            int monthh= call.get(Calendar.MONTH)+1;
                            int yearr=call.get(Calendar.YEAR);  
                            String fullDatee= Integer.toString(dayy)+"/"+Integer.toString(monthh)+"/"+Integer.toString(yearr);//dd/MM/yyyy 
                            if(fullDate.equals(fullDatee)){
                                counter++;
                                HashMap<HashMapKey,Item> items=o.getRestItemsMap();
                                double tempPrice=0;
                                for (Item it: items.values()) {
                                     tempPrice+=it.getQuantity() * it.getItemPrice();                   
                                }
                                totalPrice+=tempPrice;
                                //==========
                                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                                java.util.Date date_obj =  sdf.parse(fullDate);
                                java.sql.Date sqlStartDate = new java.sql.Date(date_obj.getTime());

                                //===========
                                dateAndNumOfOrders.put(sqlStartDate, Integer.toString(counter));
                                dateAndPrice.put(sqlStartDate, Double.toString(totalPrice));

                            }
                       } 
                    }

                }
                 //Sort 
                //==========================
                
                
               
                
                 TreeMap<Date, String> Sorted_dateAndNumOfOrders = new TreeMap<Date, String>(dateAndNumOfOrders);
                 Map<Date, String> Sorted_dateAndPrice = new TreeMap<Date, String>(dateAndPrice);
                request.setAttribute("dateAndNumOfOrders", Sorted_dateAndNumOfOrders);
                request.setAttribute("dateAndPrice", Sorted_dateAndPrice);
                request.setAttribute("restaurant", reslist.get(0));
                request.setAttribute("reslist", reslist);
                RequestDispatcher  dispatcher = request.getRequestDispatcher("/manager/order_reports.jsp");
                dispatcher.forward(request, response);
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ManagerReportsServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(ManagerReportsServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ManagerReportsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        processRequest(request, response);    

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
