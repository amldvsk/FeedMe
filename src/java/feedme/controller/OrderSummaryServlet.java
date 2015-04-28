/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedme.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
public class OrderSummaryServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet OrderSummaryServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderSummaryServlet at " + request.getContextPath() + "</h1>");
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
        String CustomerName= request.getParameter("CustomerName"); 
        String CustomerStreet= request.getParameter("CustomerStreet"); 
        String OrderTime= request.getParameter("OrderTime"); 
        String Status= request.getParameter("Status"); 
        

    }

    

}
