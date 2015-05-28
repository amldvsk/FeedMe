/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedme.controller;

import feedme.model.AuthenticatUser;
import feedme.model.Customer;
import feedme.model.DbAdminManagmentTools;
import feedme.model.DbRestaurantsManagement;
import feedme.model.Manager;
import feedme.model.PasswordEncryptionService;
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
@WebServlet(name = "AdminManagersServlet", urlPatterns = {"/admin/managers"})
public class AdminManagersServlet extends HttpServlet {

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
        if(admin == null || !PasswordEncryptionService.authenticate(Integer.toString(2), admin.getEncrypRole(), "Admin".getBytes())|| admin.isLoginResult()== true) {
            //response.sendRedirect(request.getContextPath() + "/");
            //return;
        }
            

        DbAdminManagmentTools dbamt = new DbAdminManagmentTools();

        
        
        
        List<Manager> managers = new ArrayList<Manager>();
        
        
        for(User user : dbamt.getAllUsersByRole(1))
        {
            managers.add((Manager)user);
            
        }
        
        
        

        request.setAttribute("managers", managers);
        
        RequestDispatcher  dispatcher = request.getRequestDispatcher("managers.jsp");
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
