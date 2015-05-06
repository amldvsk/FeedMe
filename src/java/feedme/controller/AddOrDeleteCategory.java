/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedme.controller;

import feedme.model.DbRestaurantsManagement;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author david
 */
public class AddOrDeleteCategory extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddOrDeleteCategory</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddOrDeleteCategory at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        /*if (result == 1){
            if(isAjax(request) == true){ // Stay in the same page, and sand json message
                response.setContentType("application/json");
               PrintWriter out = response.getWriter();
                JSONObject jsonObj = (JSONObject) JSONValue.parse(request.getParameter("para"));
                System.out.println(jsonObj.get("message"));
                JSONObject obj = new JSONObject();
                obj.put("message", "hello from server");
                out.print(obj);
            }else{ // redirect to othe page
                
            }
        }*/
    }
  
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private boolean isAjax(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }

}
