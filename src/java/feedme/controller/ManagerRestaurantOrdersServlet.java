/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedme.controller;

import feedme.model.AuthenticatUser;
import feedme.model.DbMenuManagment;
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
 * @author nirk
 */
@WebServlet(name = "ManagerRestaurantOrdersServlet", urlPatterns = {"/manager/orders"})
public class ManagerRestaurantOrdersServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                try {
            processRequest(request, response);
            AuthenticatUser manager = (AuthenticatUser)request.getSession().getAttribute("AuthenticatUser");//get the manager from the session
            //check if its a manager acording to the role and password
            if(manager == null || !PasswordEncryptionService.authenticate(Integer.toString(1), manager.getEncrypRole(), "Manager".getBytes())|| !manager.isLoginResult()) {
                response.sendRedirect(request.getContextPath() + "/");
                return;
            }
                
            
            
            DbRestaurantsManagement restaurant = new DbRestaurantsManagement();//creating a DbRestaurantsManagement object
            List<Restaurant> reslist = restaurant.getRestaurantsByManagerId(manager.getUserId());//getting a list of restaurant
            
            
            
            if( manager.getManagerRestId() != 0 ) {//if the manager has restaurants
                
                for( Restaurant re : reslist )//looping over the manager restaurants
                    if( re.getDbid() == manager.getManagerRestId() )
                        request.setAttribute("restaurant", re);//send the current restaurant
                List<Order> orders = new DbOrderManagement().getOrdersByRestId(manager.getManagerRestId());//getting a list of orders by restid
                request.setAttribute("reslist", reslist);//send all the manager restaurants
                request.setAttribute("orders", orders);//send the manager current orders
            }
            
                    
            
            
            RequestDispatcher  dispatcher = request.getRequestDispatcher("orders.jsp");
            dispatcher.forward(request, response);
            return;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            Logger.getLogger(ManagerPageServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
