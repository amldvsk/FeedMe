/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedme.controller;

import static feedme.controller.HomePageServlet.isAjaxRequest;
import feedme.model.DbRestaurantsManagement;
import feedme.model.Restaurant;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
 * @author adi
 */
@WebServlet(name = "SearchRestServlet", urlPatterns = {"/search-rest"})
public class SearchRestServlet extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        
        String city = request.getParameter("where");//get the city
        int category = Integer.parseInt(request.getParameter("what"));//get the category
        
        
        
        int page = 1;
         int recordsPerPage = 6;
         
         if( request.getParameter("page") != null ) {
             page = Integer.parseInt(request.getParameter("page"));
         }
         
         List<Restaurant> restaurants = new DbRestaurantsManagement().getNextRecentRestaurantsByCatAndCity(0 , recordsPerPage , category , city);//getting a list of restaurants by category and cities
         int noOfRecords = restaurants.size();
         
         int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
         
         
         
         if(isAjaxRequest(request)) {
             try {
                 restaurants = new DbRestaurantsManagement().getNextRecentRestaurantsByCatAndCity((page-1)*recordsPerPage,recordsPerPage , category , city);//getting a list of restaurants by category and cities
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
         request.setAttribute("noOfPages", noOfPages);
         request.setAttribute("currentPage", page);
        request.setAttribute("restaurants", restaurants);//return the restaurants to the client
         
        RequestDispatcher  dispatcher = request.getRequestDispatcher("website/search_rest.jsp");
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
