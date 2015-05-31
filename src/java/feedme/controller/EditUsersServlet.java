/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedme.controller;

import feedme.model.AuthenticatUser;
import feedme.model.Customer;
import feedme.model.DbRestaurantsManagement;
import feedme.model.DbUsersManagement;
import feedme.model.PasswordEncryptionService;
import feedme.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */@WebServlet(name = "EditUsersServlet", urlPatterns = {"/update-user"})

public class EditUsersServlet extends HttpServlet {

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
            out.println("<title>Servlet EditUsersServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditUsersServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
         AuthenticatUser userr = (AuthenticatUser)request.getSession().getAttribute("AuthenticatUser");
         
        try {
            if(userr == null || !PasswordEncryptionService.authenticate(Integer.toString(0), userr.getEncrypRole(), "Customer".getBytes())|| userr.isLoginResult()== false) {
                response.sendRedirect(request.getContextPath() + "/");
                return;                
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(EditUsersServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(EditUsersServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
                 
                    
            
        response.setStatus(HttpServletResponse.SC_OK);
        String firstName= request.getParameter("firstName");
        String lastName= request.getParameter("lastName");  
        String userName= request.getParameter("userName");  
        String phone= request.getParameter("phone");  
        String email= request.getParameter("email");  
        String dbId= request.getParameter("dbId");  
        String role= request.getParameter("role");  
        User user = new User(firstName, lastName,  userName, phone, email, Integer.parseInt(role));
        DbUsersManagement ob= new DbUsersManagement();
        user.setDbId(Integer.parseInt(dbId));
        int result=ob.updateUser(user, userName);
        if(result == 1){ 
                     
            userr.setUserFirstName(firstName);
            userr.setUserLastName(lastName);
            request.getSession().setAttribute("AuthenticatUser" , userr);

            response.sendRedirect(request.getContextPath()+"/profile");
        }
        else if(result==0){
            request.getSession().setAttribute("updateError", "שם משתמש כבר קיים");
            response.sendRedirect(request.getContextPath()+"/profile");
        }
        else{
             request.getSession().setAttribute("updateError", "error");
            response.sendRedirect(request.getContextPath()+"/profile");
        }
            
            
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
