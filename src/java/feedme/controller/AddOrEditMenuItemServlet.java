/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedme.controller;

import feedme.model.DbMenuManagment;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author User
 */
public class AddOrEditMenuItemServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AEODMenuItemServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AEODMenuItemServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        response.setStatus(HttpServletResponse.SC_OK);       
        RequestDispatcher dispatcher = request.getRequestDispatcher("newjsp.jsp");
        String itemName= request.getParameter("itemName");
        String itemPrice= request.getParameter("itemPrice");
        String itemDescrip= request.getParameter("itemDescrip");
        String itemImagePath= request.getParameter("itemImagePath");
        
        String action= request.getParameter("action"); // 1=Add , 2=Edit , 3=Delete
        int result=0;
        DbMenuManagment ob = new DbMenuManagment();
        switch(Integer.parseInt(action)){
            case 1:
                String itemMenuCatId= request.getParameter("itemMenuCatId");
                String itemRestId= request.getParameter("itemRestId");
                String itemMenuId= request.getParameter("itemMenuId");
                result = ob.AddNewMenuItem(itemName, Double.parseDouble(itemPrice), itemDescrip, itemImagePath, Integer.parseInt(itemMenuCatId), Integer.parseInt(itemRestId), Integer.parseInt(itemMenuId));                
                break;           
            case 2 :
                 String itemId= request.getParameter("itemId");
                 result= ob.updateMenuItem(Integer.parseInt(itemId), itemName, Double.parseDouble(itemPrice), itemDescrip, itemImagePath);
                break;
            case 3:
                break;
            default:
                break;
        }
        if (result!=0){// ok
                    HttpSession session = request.getSession(true);
                    session.setAttribute("Status", true);
                }else{//err
                    HttpSession session = request.getSession(true);
                    session.setAttribute("Status", false);
                }
        

    }

}
