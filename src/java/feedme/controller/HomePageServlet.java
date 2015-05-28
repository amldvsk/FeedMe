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
 * @author nirk
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
         
         DbHPOnLoad dbPageOnLoad = new DbHPOnLoad();//creating a DbHPOnLoad object
         
         category = dbPageOnLoad.getCategories();//getting a categories
         restaurants = dbPageOnLoad.getRecentRestaurants(6);//get the last 6 new restaurants
         List<String> cities = dbPageOnLoad.getCities();
         if( request.getSession().getAttribute("shoppingCart") == null ) {
             request.getSession().setAttribute("shoppingCart", new Order());//crete a new shopping cart
         } 
         
         
        List<RestaurantRanking> rankings = dbPageOnLoad.getRestRandomComments(5);//getting a random rankings from DB
         
        for( RestaurantRanking re : rankings ) {//looping over the rankings
            re.setResturent(new DbRestaurantsManagement().getRestaurantById(re.getRestId()));
        }
        
         request.setAttribute("category", category);//send the categories
         request.setAttribute("cities", cities);//send the cities
         request.setAttribute("restaurants", restaurants);//send the restaurants
         request.setAttribute("rankings", rankings);//send the rankings
         
        RequestDispatcher  dispatcher = request.getRequestDispatcher("website/index.jsp");//send a ajsp file
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
