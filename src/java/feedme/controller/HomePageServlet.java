/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedme.controller;

import feedme.model.DbHPOnLoad;
import feedme.model.DbRestaurantsManagement;
import feedme.model.Order;
import feedme.model.Restaurant;
import feedme.model.RestaurantRanking;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author adi
 */
@WebServlet(name = "HomePageServlet", urlPatterns = {"/index"})
public class HomePageServlet extends HttpServlet {

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
         HashMap<String,Integer> category = new HashMap<>();
         List<Restaurant> restaurants;
         
         DbHPOnLoad dbPageOnLoad = new DbHPOnLoad();
         
         category = dbPageOnLoad.getCategories();
         restaurants = dbPageOnLoad.getRecentRestaurants(6);
         List<String> cities = dbPageOnLoad.getCities();
         if( request.getSession().getAttribute("shoppingCart") == null ) {
             request.getSession().setAttribute("shoppingCart", new Order());
         } 
         
         
        List<RestaurantRanking> rankings = dbPageOnLoad.getRestRandomComments(5);
         
        for( RestaurantRanking re : rankings ) {
            re.setResturent(new DbRestaurantsManagement().getRestaurantById(re.getRestId()));
        }
        
         request.setAttribute("category", category);
         request.setAttribute("cities", cities);
         request.setAttribute("restaurants", restaurants);
         request.setAttribute("rankings", rankings);
         
        RequestDispatcher  dispatcher = request.getRequestDispatcher("website/index.jsp");
        dispatcher.forward(request, response);
        return;
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
                request.setCharacterEncoding("UTF-8");

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
