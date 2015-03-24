/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedme.controller;

import feedme.model.Admin;
import feedme.model.Customer;
import feedme.model.DbUsersManagement;
import feedme.model.Manager;
import feedme.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author NadavBismuth
 */
public class UsersRegistrationServlet extends HttpServlet {

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
            out.println("<title>Servlet UsersRegistrationServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UsersRegistrationServlet at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
         User user = null;              
         Date date =  null;
         String street = null;
         String houseNum = null;
         String apartmentNum = null;
         String city = null;
         String firstName = "eli";//request.getParameter("firstName");
         String lastName = "biton";//request.getParameter("lastName");
         String userName = "tamirkr";//request.getParameter("userName");
         String pw = "123";//request.getParameter("pw");
         String phone = "1111";//request.getParameter("phone");
         String email = "tamir@gmail.com";//request.getParameter("email");
         int role = 0;//Integer.parseInt(request.getParameter("role"));
         
         if(role == 0)
         {
           // try {
               // date =  (Date) new SimpleDateFormat("dd-MM-yyyy").parse(request.getParameter("bday"));
                date = new Date(2012-1900,2,4);
          //  } catch (ParseException ex) {
           //     Logger.getLogger(CustomerRegistrationServlet.class.getName()).log(Level.SEVERE, null, ex);
          //  }
              street = "a";//request.getParameter("st");
              houseNum = "1";//request.getParameter("hnum");
              apartmentNum = "12";//request.getParameter("aprtnum");
              city = "bs";//request.getParameter("cty");
         }

             

         
         DbUsersManagement dbUserManagment = new DbUsersManagement();
         int result = dbUserManagment.AddNewUser(firstName,lastName,userName,pw,phone,email,role,date,street,houseNum,apartmentNum,city);
         if(result == 2)
         {
            
             switch(role)
             {
                 case 0:
                      user  = (Customer)dbUserManagment.getUserByUserName(userName);
                     
                 case 1:
                      user  = (Manager)dbUserManagment.getUserByUserName(userName);
                     break;
                 case 2:
                       user  = (Admin)dbUserManagment.getUserByUserName(userName);
                        break;
             }
             request.setAttribute("user", user);
        
             response.sendRedirect("index.html");
             RequestDispatcher dispatcher = request.getRequestDispatcher("success.jsp");
            dispatcher.forward(request, response);
            
         }
         else
         {
             response.sendRedirect("index.html");
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
