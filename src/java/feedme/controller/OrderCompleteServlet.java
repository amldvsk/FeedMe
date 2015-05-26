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
import feedme.model.Order;
import feedme.model.PasswordEncryptionService;
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
        
        AuthenticatUser customer = (AuthenticatUser)request.getSession().getAttribute("AuthenticatUser");
        if( customer != null ) {
            request.setAttribute("customer", (Customer)new DbUsersManagement().getUserById(customer.getUserId()));
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("website/complete_order.jsp");
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
