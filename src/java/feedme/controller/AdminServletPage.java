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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AdminServletPage</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminServletPage at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        AuthenticatUser admin = (AuthenticatUser)request.getSession().getAttribute("AuthenticatUser");
        if(admin == null || PasswordEncryptionService.authenticate(Integer.toString(2), admin.getEncrypRole(), "Admin".getBytes()))
            response.sendRedirect(request.getContextPath() + "/index");
        DbRestaurantsManagement dbrm = new DbRestaurantsManagement();
        DbAdminManagmentTools dbamt = new DbAdminManagmentTools();
        //List<Restaurant> restaurants = dbrm.getAllRestaurants();
        List<Customer> customers = new ArrayList<>() ;
        
        List<User> managers = dbamt.getAllUsersByRole(1);
        
        
        
        for(User user : dbamt.getAllUsersByRole(0))
        {
            customers.add((Customer)user);
            
        }
        
        
        
        //request.setAttribute("restaurant", restaurants);
        request.setAttribute("reslist", customers);
        request.setAttribute("orders", managers);
        
        RequestDispatcher  dispatcher = request.getRequestDispatcher("admin/index.jsp");
        dispatcher.forward(request, response);
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
