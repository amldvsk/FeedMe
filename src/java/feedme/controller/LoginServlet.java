/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedme.controller;

import feedme.model.DbUsersManagement;
import feedme.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author David Lazarev
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/Login"})
public class LoginServlet extends HttpServlet {
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        response.setStatus(HttpServletResponse.SC_OK);
        String username= request.getParameter("Username"); 
        String Password= request.getParameter("UserPass"); 
        
        
        DbUsersManagement DB =  new DbUsersManagement();
        RequestDispatcher dispatcher = request.getRequestDispatcher("userprofile.jsp");
        User user= DB.getUserByUserName(username); // return the user if he exists
        
        if(user!= null && Password.equals(user.getPw()) ){ // if user exists in database && the password is correct
            switch(user.getRole()){
                case 0 ://user page
                        request.setAttribute("user", user);
                        dispatcher = request.getRequestDispatcher("userprofile.jsp");
                        dispatcher.forward(request, response);
                    break;
                case 1: //restorant page
                        request.setAttribute("user", user);
                        dispatcher = request.getRequestDispatcher("restorantpage.jsp");
                        dispatcher.forward(request, response);
                    break;
                case 2://admin page
                        request.setAttribute("user", user);
                        dispatcher = request.getRequestDispatcher("adminpage.jsp");
                        dispatcher.forward(request, response);
                    break;
                default:
                    return;           
            }
                    
        }else{
            
        }      
  
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
