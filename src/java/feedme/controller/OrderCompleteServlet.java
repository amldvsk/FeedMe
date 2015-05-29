/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedme.controller;

import feedme.model.AuthenticatUser;
import feedme.model.Customer;
import feedme.model.DbOrderManagement;
import feedme.model.DbUsersManagement;
import feedme.model.EmailUtility;
import feedme.model.Item;
import feedme.model.Order;
import feedme.model.PasswordEncryptionService;
import feedme.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author nirk
 */
@WebServlet(name = "OrderDetailsServlet", urlPatterns = {"/order-complete"})
public class OrderCompleteServlet extends HttpServlet {

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
        response.setCharacterEncoding("UTF-8");
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
        
        AuthenticatUser customer = (AuthenticatUser)request.getSession().getAttribute("AuthenticatUser");//getting the customer from the session
        try {//check if its a customer 
            if( customer != null && PasswordEncryptionService.authenticate(Integer.toString(0), customer.getEncrypRole(), "Customer".getBytes())) {
                request.setAttribute("customer", (Customer)new DbUsersManagement().getUserById(customer.getUserId()));
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(OrderCompleteServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(OrderCompleteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("website/complete_order.jsp");//displays the customer a jsp file
        dispatcher.forward(request, response);
        
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
        String fullName = request.getParameter("fname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        Order cart = (Order)request.getSession().getAttribute("shoppingCart");
        
        cart.setCustomerFullName(fullName);
        cart.setCustomerPhonenum(phone);
        cart.setCustomerAdress(address);
        int userId = 0;
        AuthenticatUser customer = (AuthenticatUser)request.getSession().getAttribute("AuthenticatUser");
        try {
            if( customer != null && PasswordEncryptionService.authenticate(Integer.toString(0), customer.getEncrypRole(), "Customer".getBytes())) {
                userId = ((AuthenticatUser)request.getSession().getAttribute("AuthenticatUser")).getUserId();
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(OrderCompleteServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(OrderCompleteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        cart.setOrderCustomerId(userId);
        
        DbOrderManagement dbom = new DbOrderManagement();
        int result = dbom.addNewOrder(cart);
        JSONObject orderStatus = new JSONObject();
        try {
                if(result == 1)
                {
                    
                    request.getSession().setAttribute("shoppingCart", new Order());
                    orderStatus.put("status", 1);
                    sendEmail(email, cart);

                }
                else
                {
                    orderStatus.put("shoppingCartError", "התרחשה שגיאה אנא נסו שנית");
                    orderStatus.put("status", 0);

                }
                orderStatus.put("order", cart.toJson());
                response.setContentType("application/json");
                PrintWriter writer = response.getWriter();
                writer.print(orderStatus);
                response.getWriter().flush();
            } catch (JSONException ex) {
                Logger.getLogger(OrderCompleteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
  
    }
    
    
    
    private void sendEmail(String email, Order order) {
        
        
        String recipient = email;
        String subject = " FeedMe התקבלה הזמנה חדשה";
        
        StringBuilder sb = new StringBuilder();
        
        sb.append("<center>");
        sb.append("<div style=\"margin-bottom: 10px; border-top: 3px solid #74ad5a; margin-top:0px;\" >&nbsp;</div>");
        sb.append("<table style=\"text-align:center; max-width:700px; direction: rtl; \"  >");
        sb.append("<thead>");
        sb.append("<tr>");
        sb.append("<td colspan=\"4\" ><img src=\"https://ec2-52-25-118-3.us-west-2.compute.amazonaws.com/FeedMe/assets/img/logo_b.png\" height=\"50\" style=\"margin: 0 auto;\"></td>");
        sb.append("</tr>");
        sb.append("<tr>");
        sb.append("<td colspan=\"4\" style=\"padding:20px;\" ><h2>הזמנה חדשה שהתקבלה</h2></td>");
        sb.append("</tr>");
        sb.append("<tr>\n" +
"        <th style=\"color: #384047; border-bottom: 1px solid #edf5ea;\">כמות</th>   \n" +
"        <th style=\"color: #384047; border-bottom: 1px solid #edf5ea;\">מוצר</th>\n" +
"        <th style=\"color: #384047; border-bottom: 1px solid #edf5ea; \">פירוט</th>\n" +
"        <th style=\"color: #384047; border-bottom: 1px solid #edf5ea;\">מחיר</th>\n" +
"         \n" +
"      </tr>");
        
        sb.append("</thead>");
        sb.append("<tbody style=\"color: #384047;\">");
        
        for( Item item : order.getRestItemsMap().values() ) {
            sb.append("<tr  >");
            sb.append("<td style=\"padding: 10px 0;\"> <p style=\"color:#8c989e;\" >" + item.getQuantity() +  "</p> </td>");
            sb.append("<td style=\"padding: 10px 0;\"> <h4 style=\"font-weight:bold;\" >"  + item.getItemName() +   "</h4> </td>");
            sb.append(" <td style=\"padding: 10px 0;\"> <p style=\"color:#8c989e;\" >" +item.getItemDescription()+ "</p> </td>");
            sb.append("<td style=\"padding: 10px 0;\"> <p style=\"color:#8c989e;\" >" +item.getItemPrice()+ " &#8362;</p> </td>");
            sb.append("</tr>");
        }
        
        sb.append("<tr>\n" +
"        <td colspan=\"3\"></td>\n" +
"        <td colspan=\"3\"><p style=\"font-weight:bold;\">סך הכל: "+ order.calcSum() +" &#8362; <p></td>\n" +
"      </tr>");
        
        sb.append("<tr>\n" +
"        <td colspan=\"4\"><div style=\"margin: 10px 0; border-top: 2px dashed #eeeeee;\" >&nbsp;</div></td>\n" +
"      </tr>");
        
        sb.append("<tr  >\n" +
"        <td colspan=\"2\" style=\"color: #384047; padding: 5px \" > <p style=\"font-weight: bold;\" >  שם: </p> </td>\n" +
"        <td colspan=\"2\" style=\"padding: 5px;\"> <p style=\"color:#8c989e;\" >"+ order.getCustomerFullName() +"</p> </td>\n" +
"      </tr>");
        
        sb.append("<tr  >\n" +
"        <td colspan=\"2\" style=\"color: #384047; padding: 5px \" > <p style=\"font-weight: bold;\" >  כתובת: </p> </td>\n" +
"        <td colspan=\"2\" style=\"padding: 5px;\"> <p style=\"color:#8c989e;\" >"+ order.getCustomerAdress() +"</p> </td>\n" +
"      </tr>");
        
        sb.append("</tbody>\n" +
    "\n" +
    "  </table>\n" +
    "\n" +
    "</center>");
        
        String content = sb.toString();
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
