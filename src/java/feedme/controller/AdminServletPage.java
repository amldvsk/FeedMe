/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedme.controller;

import feedme.model.AuthenticatUser;
import feedme.model.Customer;
import feedme.model.DbAdminManagmentTools;
import feedme.model.DbOrderManagement;
import feedme.model.DbRestaurantsManagement;
import feedme.model.DbUsersManagement;
import feedme.model.Manager;
import feedme.model.PasswordEncryptionService;
import feedme.model.Restaurant;
import feedme.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "AdminServletPage", urlPatterns = {"/admin"})
public class AdminServletPage extends HttpServlet {

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
        try{
        processRequest(request, response);
        AuthenticatUser admin = (AuthenticatUser)request.getSession().getAttribute("AuthenticatUser");//getting the admin from the session
        //check if its admin by role & password
        if(admin == null || !PasswordEncryptionService.authenticate(Integer.toString(2), admin.getEncrypRole(), "Admin".getBytes())|| !admin.isLoginResult()) {
            //response.sendRedirect(request.getContextPath() + "/");
            //return;
        }
            
        DbRestaurantsManagement dbrm = new DbRestaurantsManagement();//creating a DbRestaurantsManagement object
        DbAdminManagmentTools dbamt = new DbAdminManagmentTools();//creating a DbAdminManagmentTools object
        //List<Restaurant> restaurants = dbrm.getAllRestaurants();
        List<Customer> customers = new ArrayList<>() ;//a list of customer
        
        List<User> managers = dbamt.getAllUsersByRole(1);//list of manager by role
        
        
        
        for(User user : dbamt.getAllUsersByRole(0))//loop over all the users website
        {
            customers.add((Customer)user);
            
        }
        
        
        
        //request.setAttribute("restaurant", restaurants);
        request.setAttribute("reslist", customers);//send a list of restaurants
        request.setAttribute("orders", managers);//sent all the orders
        
        RequestDispatcher  dispatcher = request.getRequestDispatcher("admin/index.jsp");//send a jsp file
        dispatcher.forward(request, response);
        return;
        }catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            Logger.getLogger(AdminServletPage.class.getName()).log(Level.SEVERE, null, ex);
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
