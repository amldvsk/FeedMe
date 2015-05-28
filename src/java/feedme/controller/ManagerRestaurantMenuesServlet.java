/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedme.controller;

import feedme.model.AuthenticatUser;
import feedme.model.DbMenuManagment;
import feedme.model.DbOrderManagement;
import feedme.model.DbRestaurantsManagement;
import feedme.model.Item;
import feedme.model.Order;
import feedme.model.PasswordEncryptionService;
import feedme.model.Restaurant;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nirk
 */
@WebServlet(name = "ManagerRestaurantMenuesServlet", urlPatterns = {"/manager/menus"})
public class ManagerRestaurantMenuesServlet extends HttpServlet {

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
        try {
            processRequest(request, response);
            AuthenticatUser manager = (AuthenticatUser)request.getSession().getAttribute("AuthenticatUser");//getting the manager object from the session
            //check if its a manager by role & password
            if(manager == null || !PasswordEncryptionService.authenticate(Integer.toString(1), manager.getEncrypRole(), "Manager".getBytes())|| !manager.isLoginResult()) {
                response.sendRedirect(request.getContextPath() + "/");//return to the main page
                return;
            }
                
            
            
            DbRestaurantsManagement restaurant = new DbRestaurantsManagement();//creating a DbRestaurantsManagement object
            List<Restaurant> reslist = restaurant.getRestaurantsByManagerId(manager.getUserId());//get a list of restaurants
            
            if( manager.getManagerRestId() != 0 ) {//if the manager has a  restaurants
                HashMap<Map<Integer,String>,List<Item>> menues = new DbMenuManagment().getMenuByRestaurantId(manager.getManagerRestId());//get the menues by the restid
                request.setAttribute("menus", menues);//send the menues
                for( Restaurant re : reslist )//loop over the manager restaurants
                    if( re.getDbid() == manager.getManagerRestId() )
                        request.setAttribute("restaurant", re);//send the current manager restaurant
                request.setAttribute("reslist", reslist);//send all the manager restaurants
            }
            
            

            
            RequestDispatcher  dispatcher = request.getRequestDispatcher("menus.jsp");//send a jsp file
            dispatcher.forward(request, response);
            return;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            Logger.getLogger(ManagerPageServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
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
