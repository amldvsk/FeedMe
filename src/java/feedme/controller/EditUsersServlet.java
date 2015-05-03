/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedme.controller;

import feedme.model.DbRestaurantsManagement;
import feedme.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
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
        
        response.setStatus(HttpServletResponse.SC_OK);
        String firstName= request.getParameter("firstName");
        String lastName= request.getParameter("lastName");  
        String userName= request.getParameter("userName");  
        String phone= request.getParameter("phone");  
        String email= request.getParameter("email");  
        String dbId= request.getParameter("dbId");  
        String role= request.getParameter("role");  
        User user = new User(firstName, lastName,  userName, phone, email, Integer.parseInt(role));
        DbRestaurantsManagement ob= new DbRestaurantsManagement();
        user.setDbId(Integer.parseInt(dbId));
       // int result=updateUser(user);
       // if(result == 1){ 
           //OK
      //  }
       // else 
            //err
            
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
