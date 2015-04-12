/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedme.controller;

import feedme.model.DbUsersManagement;
import feedme.model.PasswordEncryptionService;
import feedme.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
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
        int check;
        boolean authent;
        List<byte[]> psal = new ArrayList<>();
        DbUsersManagement DB =  new DbUsersManagement();
        RequestDispatcher dispatcher = request.getRequestDispatcher("userprofile.jsp");
        check = DB.checkIfUserExists(username);
        if(check == 1)
        {
            try {
                psal = DB.getUserEncryptedPassword(username);
                authent = PasswordEncryptionService.authenticate(Password, psal.get(0), psal.get(1));
                if(authent)
                {
                    User user= DB.getUserByUserName(username); // return the user if he exists
                    
                    if(user!= null  ){ // if user exists in database && the password is correct
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
                        //if for some reason the user is mother fucking null
                    }
                }
                else{
                    //wrong password do what ever you want dick head
                }
            } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        {
            //if there is no such user name you little dirty russian
        }
        
             
  
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
