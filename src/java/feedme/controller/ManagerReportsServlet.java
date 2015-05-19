/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedme.controller;

import feedme.model.AuthenticatUser;
import feedme.model.DbOrderManagement;
import feedme.model.DbRestaurantsManagement;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ManagerReportsServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManagerReportsServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        // Check if user is :admin\manager\customer
        AuthenticatUser manager = (AuthenticatUser)request.getSession().getAttribute("AuthenticatUser");
        try {
            if(manager == null || !PasswordEncryptionService.authenticate(Integer.toString(1), manager.getEncrypRole(), "Manager".getBytes())|| manager.isLoginResult()== true) {
                response.sendRedirect(request.getContextPath() + "/");
                return;                
            }
            else{
                int totalPrice=0;
                Calendar now = Calendar.getInstance();
                DbRestaurantsManagement restaurant = new DbRestaurantsManagement();
                List<Restaurant> reslist = restaurant.getRestaurantsByManagerId(manager.getUserId());
                List<Order> orders = new DbOrderManagement().getOrdersByRestId(reslist.get(0).getDbid());
                //request.setAttribute("reslist", reslist);
                int counter=0;                
                Map<String, String> dateAndNumOfOrders = new HashMap<String, String>();
                Map<String, String> dateAndPrice = new HashMap<String, String>();
                for(Order ord:orders){
                    counter=0;
                    totalPrice=0;
                    int day=ord.getOrderDateAndTime().getDate();
                    int month= ord.getOrderDateAndTime().getMonth();
                    int year=ord.getOrderDateAndTime().getYear();                  
                    String fullDate= Integer.toString(day)+"/"+Integer.toString(month)+"/"+Integer.toString(year);//dd/MM/yyyy                   
                    if(dateAndNumOfOrders.get(fullDate)== null && month == now.get(Calendar.MONTH) && year == now.get(Calendar.YEAR)){// check if the key is not exists
                       for(Order o:orders){
                            int dayy=o.getOrderDateAndTime().getDate();
                            int monthh= o.getOrderDateAndTime().getMonth();
                            int yearr=o.getOrderDateAndTime().getYear(); 
                            String fullDatee= Integer.toString(dayy)+"/"+Integer.toString(monthh)+"/"+Integer.toString(yearr);//dd/MM/yyyy 
                            if(fullDate.equals(fullDatee)){
                                counter++;
                                HashMap<Integer[],Item> items=o.getRestItemsMap();
                                int tempPrice=0;
                                for (Item it: items.values()) {
                                     tempPrice+=it.getQuantity() * it.getItemPrice();                   
                                }
                                totalPrice+=tempPrice;
                                dateAndNumOfOrders.put(fullDate, Integer.toString(counter));
                                dateAndPrice.put(fullDate, Integer.toString(totalPrice));

                            }
                       } 
                    }

                }
                request.setAttribute("dateAndNumOfOrders", dateAndNumOfOrders);
                request.setAttribute("dateAndPrice", dateAndPrice);
                RequestDispatcher  dispatcher = request.getRequestDispatcher("/manager/order_reports.jsp");
                dispatcher.forward(request, response);
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ManagerReportsServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
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
