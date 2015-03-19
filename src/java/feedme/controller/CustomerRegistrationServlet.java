
package feedme.controller;

import feedme.model.Customer;
import feedme.model.DbUsersManagement;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
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
        processRequest(request, response);

       /*  String firstName = request.getParameter("firstName");
         String lastName = request.getParameter("lastName");
         String userName = request.getParameter("userName");
         String pw = request.getParameter("pw");
         String phone = request.getParameter("phone");
         String email = request.getParameter("email");
         String role = request.getParameter("role");
         String dbId = request.getParameter("dbId");
         String bDay = request.getParameter("bDay");
         String address = request.getParameter("address");
         
         DbUsersManagement dbUserManagment = new DbUsersManagement();
         //boolean result = dbUserManagment.authenticate(userId,email);
         if(result)
         {
            //Customer customer = new Customer(firstName, lastName,userName,pw,phone,email,role,dbId,bDay);
            request.setAttribute("customer", customer);
        {
             response.sendRedirect("registration.jsp");
         }          RequestDispatcher dispatcher = request.getRequestDispatcher("success.jsp");
            dispatcher.forward(request, response);
            return;
         }
         else
         {
             response.sendRedirect("registration.jsp");
         }*/
        

    }


    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
