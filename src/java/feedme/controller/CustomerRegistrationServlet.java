
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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "CustomerRegistrationServlet", urlPatterns = {"/CustomerRegistrationServlet"})
public class CustomerRegistrationServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        
         User user = null;              
         Date date =  null;
         String street = null;
         String houseNum = null;
         String apartmentNum = null;
         String city = null;
         String firstName = "dfdfddf";//request.getParameter("firstName");
         String lastName = "bidfgfdgton";//request.getParameter("lastName");
         String userName = "fd11221h";//request.getParameter("userName");
         String pw = "123";//request.getParameter("pw");
         String phone = "1111";//request.getParameter("phone");
         String email = "df13dגd3fd1123g@gmail.com";//request.getParameter("email");
         int role = 0;//Integer.parseInt(request.getParameter("role"));
         
         if(role == 0)
         {
            try {
                 date =  (Date) new SimpleDateFormat("dd-MM-yyyy").parse(request.getParameter("bday"));
            } catch (ParseException ex) {
                Logger.getLogger(CustomerRegistrationServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
              street = "a";//request.getParameter("st");
              houseNum = "1";//request.getParameter("hnum");
              apartmentNum = "12";//request.getParameter("aprtnum");
              city = "bs";//request.getParameter("cty");
         }

             

         
         DbUsersManagement dbUserManagment = new DbUsersManagement();
         int result = dbUserManagment.addNewUser(firstName,lastName,userName,pw,phone,email,role,date,street,houseNum,apartmentNum,city);
         if(result == 2)
         {
            
             switch(role)
             {
                 case 0:
                      user  = (Customer)dbUserManagment.getUserByUserName(userName);
                     break;
                 case 1:
                      user  = (Manager)dbUserManagment.getUserByUserName(userName);
                     break;
                 case 2:
                       user  = (Admin)dbUserManagment.getUserByUserName(userName);
                        break;
             }
             request.setAttribute("user", user);
        
             //response.sendRedirect("website/success.jsp");
             RequestDispatcher dispatcher = request.getRequestDispatcher("website/success.jsp");
            dispatcher.forward(request, response);
            
         }
         else
         {
             response.sendRedirect("website/indקרx.html");
         }
        

    }


    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
