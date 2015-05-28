/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedme.controller;

import feedme.model.DbRestaurantsManagement;
import feedme.model.RestaurantRanking;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
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
@WebServlet(name = "RestaurantRankingServlet", urlPatterns = {"/rank-order"})
public class RestaurantRankingServlet extends HttpServlet {

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
        //int restid = Integer.parseInt(request.getParameter("restid"));
        String[] restids = request.getParameterValues("restid[]");
        double rank = Double.parseDouble(request.getParameter("rank"));
        String comment = request.getParameter("comment");
        HashSet<String> restIdsSet = new HashSet<String>();

        for( String id : restids ) {
            restIdsSet.add(id);
        }
        
        for( String rest_id : restIdsSet ) {
            RestaurantRanking rr = new RestaurantRanking(Integer.parseInt(rest_id),rank,comment);
            DbRestaurantsManagement restaurantManagment = new DbRestaurantsManagement();
            restaurantManagment.addRestRanking(rr);
        }
        
        
        
        response.sendRedirect(request.getContextPath()+"/");
        return;
        
        
        
        
        
        

        
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
