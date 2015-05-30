/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedme.controller;

import feedme.model.DbAdminManagmentTools;
import feedme.model.DbHPOnLoad;
import feedme.model.DbRestaurantsManagement;
import feedme.model.Order;
import feedme.model.Restaurant;
import feedme.model.RestaurantRanking;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        
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
         HashMap<String,Integer> category = new HashMap<>();
         List<Restaurant> restaurants;
         
         DbHPOnLoad dbPageOnLoad = new DbHPOnLoad();//creating a DbHPOnLoad object
         
         
         
         
         List<Restaurant> allResturent = new DbAdminManagmentTools().getAllRestaurants();
         
         
         int page = 1;
         int recordsPerPage = 6;
         
         if( request.getParameter("page") != null ) {
             page = Integer.parseInt(request.getParameter("page"));
         }
         
         int noOfRecords = allResturent.size();
         int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
         
         
         
         category = dbPageOnLoad.getCategories();//getting a categories
         restaurants = new DbRestaurantsManagement().getNextRecentRestaurantsByCatAndCity((page-1)*recordsPerPage,recordsPerPage,0,"Asd");//get the last 6 new restaurants
         
         if(isAjaxRequest(request)) {
             try {
                    JSONObject restObj = new JSONObject();
                    JSONArray restArray = new JSONArray();
                    for(Restaurant rest : restaurants) {
                        restArray.put(new JSONObject().put("resturent", rest.toJson()));
                    }
                    restObj.put("resturent", restArray);
                    restObj.put("noOfPages", noOfPages);
                    restObj.put("currentPage", page);
                    response.setContentType("application/json");
                    PrintWriter writer = response.getWriter();
                    writer.print(restObj);
                    response.getWriter().flush();
                    return;
                } catch( JSONException e ) {
                    e.printStackTrace();
                }
         }
         
         List<String> cities = dbPageOnLoad.getCities();
         if( request.getSession().getAttribute("shoppingCart") == null ) {
             request.getSession().setAttribute("shoppingCart", new Order());//crete a new shopping cart
         } 
         
         
        List<RestaurantRanking> rankings = dbPageOnLoad.getRestRandomComments(5);//getting a random rankings from DB
         
        for( RestaurantRanking re : rankings ) {//looping over the rankings
            re.setResturent(new DbRestaurantsManagement().getRestaurantById(re.getRestId()));
        }
         
         request.setAttribute("noOfPages", noOfPages);
         request.setAttribute("currentPage", page);
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
    
    public static boolean isAjaxRequest(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
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
