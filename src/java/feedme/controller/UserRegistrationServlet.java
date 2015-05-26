
package feedme.controller;

import feedme.model.Admin;
import feedme.model.AuthenticatUser;
import feedme.model.Customer;
import feedme.model.DbUsersManagement;
import feedme.model.EmailUtility;
import feedme.model.Manager;
import feedme.model.PasswordEncryptionService;
import feedme.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
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
import org.json.JSONException;


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
        
        boolean authent;
        AuthenticatUser au;
        String encryRoleName;
        byte[] encRole = null;
        
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
                    SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyyy");
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
                      encryRoleName = Integer.toString(user.getRole());
                        {
                            try {
                                encRole = PasswordEncryptionService.getEncryptedPassword(encryRoleName, "Customer".getBytes());
                            } catch (NoSuchAlgorithmException ex) {
                                Logger.getLogger(UserRegistrationServlet.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (InvalidKeySpecException ex) {
                                Logger.getLogger(UserRegistrationServlet.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        au = new AuthenticatUser(user.getDbId(),user.getFirstName(),user.getLastName(), encRole, true);
                        request.getSession(true).setAttribute("AuthenticatUser", au);
                        sendEmail( (Customer)user );
                        response.sendRedirect(request.getContextPath()+"/profile");
                        return;
                 case 1:
                      user  = (Manager)dbUserManagment.getUserByUserName(userName);
                      
                        response.sendRedirect(request.getContextPath()+"/admin/managers");
                        return;
                 case 2:
                       user  = (Admin)dbUserManagment.getUserByUserName(userName);
                       encryRoleName = Integer.toString(user.getRole());
                        {
                            try {
                                encRole = PasswordEncryptionService.getEncryptedPassword(encryRoleName, "Admin".getBytes());
                            } catch (NoSuchAlgorithmException ex) {
                                Logger.getLogger(UserRegistrationServlet.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (InvalidKeySpecException ex) {
                                Logger.getLogger(UserRegistrationServlet.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        au = new AuthenticatUser(user.getDbId(),user.getFirstName(),user.getLastName(), encRole, true);
                        request.getSession(true).setAttribute("AuthenticatUser", au);
                        response.sendRedirect(request.getContextPath()+"/admin");
                        return;
                        
             }

            
         }else if( result == 0 ) {
             request.getSession().setAttribute("registerError", "שם משתמש כבר קיים");
             switch(role) {
                 case 0:
                     response.sendRedirect(request.getContextPath()+"/");
                     return;
                 case 1:
                     response.sendRedirect(request.getContextPath()+"/admin/editManager.jsp");
                     return;
             }
             
         } else if( result == 1 ) {
             request.getSession().setAttribute("registerError", "אימייל כבר קיים");
             switch(role) {
                 case 0:
                     response.sendRedirect(request.getContextPath()+"/");
                     return;
                 case 1:
                     response.sendRedirect(request.getContextPath()+"/admin/editManager.jsp");
                     return;
             }
         }
         else
         {
             response.sendRedirect("website/index.jsp");
         }
        

    }


    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void sendEmail(Customer customer) {
        
        
        String recipient = customer.getEmail();
        String subject = "Wellcome To FeedMe";
        String content = "<h2>Hello," + customer.getFullName() +"</h2> <br/> Thank You For Your Registration at FeedMe";
        String host = getServletContext().getInitParameter("host");
        String port = getServletContext().getInitParameter("port");
        String user = getServletContext().getInitParameter("user");
        String pass = getServletContext().getInitParameter("pass");
        String resultMessage = "";
        
        try {
            EmailUtility.sendEmail(host, port, user, pass, recipient, subject,
                    content);
            resultMessage = "The e-mail was sent successfully";
        } catch (Exception ex) {
            ex.printStackTrace();
            resultMessage = "There were an error: " + ex.getMessage();
        } finally {
            
        }
    }

}
