/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedme.controller;

import feedme.model.DBMenuManagment;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author User
 */
public class AddOrEditMenuServlet extends HttpServlet {

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
            out.println("<title>Servlet AddOrEditMenuServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddOrEditMenuServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    
     
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        response.setStatus(HttpServletResponse.SC_OK);
         RequestDispatcher dispatcher = request.getRequestDispatcher("newjsp.jsp");
         
        //==========### Get parameters from JSP page ##===========
        String RestaurantId= request.getParameter("RestaurantId"); 
        String MenuName= request.getParameter("MenuName") + "Menu"; 
        String MenuId= request.getParameter("MenuId"); // for edit
        String action= request.getParameter("action");  
        int result=0;
        DBMenuManagment ob = new DBMenuManagment();
        if(Integer.parseInt(action)== 1){// 1=add , 2= edit
             result = ob.addNewMenu(MenuName, Integer.parseInt(RestaurantId));
        }
        //else{
           // result = ob.updateMenue(MenuName, Integer.parseInt(MenuId));
       // }
        //==========### Send successful or failing session message  ##===========

        if(result == 1){ // 1= added successfully , 2= error 
            HttpSession session = request.getSession(true);
            session.setAttribute("MenuStatus", true);
        }else{
            HttpSession session = request.getSession(true);
            session.setAttribute("MenuStatus", false);
        }
        
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
