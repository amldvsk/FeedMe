/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedme.controller;

import feedme.model.DbHPOnLoad;
import feedme.model.DbRestaurantsManagement;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @author david
 */
@WebServlet(name = "AddOrDeleteCategory", urlPatterns = {"/add-resturent-category"})
public class AddOrDeleteCategory extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        request.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);  
        String CategoryName= request.getParameter("categoryName");
        DbRestaurantsManagement ob= new DbRestaurantsManagement();
        int result = ob.addNewCategory(CategoryName);
        PrintWriter out = response.getWriter();
        if (result == 1){
            if(isAjax(request) == true){ // Stay in the same page, and sand json message
               
                try {
                    HashMap<String , Integer > cat = new DbHPOnLoad().getCategories();
                    JSONObject catObj = new JSONObject();
                    JSONArray catArray = new JSONArray();
                    for(Entry<String , Integer> entry : cat.entrySet()) {
                        catArray.put(new JSONObject().put(entry.getValue().toString(), entry.getKey()));
                    }
                    catObj.put("categories", catArray);
                    
                    response.setContentType("application/json;charset=UTF-8");
                    response.setCharacterEncoding("UTF-8");
                    PrintWriter writer = response.getWriter();
                    writer.print(catObj);
                } catch( JSONException e ) {
                    e.printStackTrace();
                }
            }else{ // redirect to othe page
                
            }
        }
    }
  
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private boolean isAjax(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }

}
