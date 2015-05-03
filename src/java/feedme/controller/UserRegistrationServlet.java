
package feedme.controller;

import feedme.model.Admin;
import feedme.model.Customer;
import feedme.model.DbUsersManagement;
import feedme.model.Manager;
import feedme.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
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


@WebServlet(name = "UserRegistrationServlet", urlPatterns = {"/registration"})
public class UserRegistrationServlet extends HttpServlet {

    
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
        
        request.setCharacterEncoding("UTF-8");
         User user = null;
         Date d = null;
         String street = null;
         String houseNum = null;
         String apartmentNum = null;
         String city = null;
         String firstName = request.getParameter("firstName");
         String lastName = request.getParameter("lastName");
         String userName = request.getParameter("userName");
         String pw = request.getParameter("pw");
         String phone = request.getParameter("phone");
         String email = request.getParameter("email");
         int role = Integer.parseInt(request.getParameter("role"));
         
         if(role == 0)
         {
            try {

                   
                   String birthday = request.getParameter("bday");
                    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyyy");
                    java.util.Date date = sdf1.parse(birthday);
                    d = new Date(date.getTime()); 
              
            
            } catch (ParseException ex) {
                Logger.getLogger(UserRegistrationServlet.class.getName()).log(Level.SEVERE, null, ex);
          }
              street = request.getParameter("address");
              houseNum = request.getParameter("street_num");
              apartmentNum = request.getParameter("home_num");
              city = request.getParameter("cty");
         }

             

         
         DbUsersManagement dbUserManagment = new DbUsersManagement();
         int result = dbUserManagment.addNewUser(firstName,lastName,userName,pw,phone,email,role,d,street,houseNum,apartmentNum,city);
         RequestDispatcher dispatcher = null;
         if(result == 2)
         {
            
             switch(role)
             {
                 case 0:
                      user  = (Customer)dbUserManagment.getUserByUserName(userName);
                      dispatcher = request.getRequestDispatcher("website/profile.jsp");
                     break;
                 case 1:
                      user  = (Manager)dbUserManagment.getUserByUserName(userName);
                      dispatcher = request.getRequestDispatcher("manager/index.jsp");
                     break;
                 case 2:
                       user  = (Admin)dbUserManagment.getUserByUserName(userName);
                       dispatcher = request.getRequestDispatcher("admin/index.jsp");
                        break;
             }
             request.setAttribute("user", user);
        
             //response.sendRedirect("website/success.jsp");
             
            dispatcher.forward(request, response);
            
         }
         else
         {
             response.sendRedirect("website/index.jsp");
         }
        

    }


    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
