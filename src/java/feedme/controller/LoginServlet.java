/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedme.controller;

import feedme.model.AuthenticatUser;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author David Lazarev
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/Login"})
public class LoginServlet extends HttpServlet {
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
               request.setCharacterEncoding("UTF-8");

        response.setStatus(HttpServletResponse.SC_OK);
        String username= request.getParameter("Username"); 
        String Password= request.getParameter("UserPass"); 
        int check;
        boolean authent;
        AuthenticatUser au;
        String encryRoleName;
        byte[] encRole;
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
                    User user = DB.getUserByUserName(username); // return the user if he exists
                    
                    if(user!= null  ){ // if user exists in database && the password is correct
                        switch(user.getRole()){
                            case 0 ://user page
                                
                                encryRoleName = Integer.toString(user.getRole());
                                encRole = PasswordEncryptionService.getEncryptedPassword(encryRoleName, "Customer".getBytes());
                                au = new AuthenticatUser(user.getDbId(),user.getFirstName(),user.getLastName(), encRole, true);
                                request.getSession(true).setAttribute("AuthenticatUser", au);
                                response.sendRedirect(request.getContextPath()+"/profile");
                                break;
                            case 1: //restorant page
                                
                                encryRoleName = Integer.toString(user.getRole());
                                encRole = PasswordEncryptionService.getEncryptedPassword(encryRoleName, "Manager".getBytes());
                                au = new AuthenticatUser(user.getDbId(),user.getFirstName(),user.getLastName(), encRole, true);
                                au.setManagerRestId(-1);
                                request.getSession(true).setAttribute("AuthenticatUser", au);
                                response.sendRedirect(request.getContextPath()+"/manager");
                                return;
                            case 2://admin page
                                
                                encryRoleName = Integer.toString(user.getRole());
                                encRole = PasswordEncryptionService.getEncryptedPassword(encryRoleName, "Admin".getBytes());
                                au = new AuthenticatUser(user.getDbId(),user.getFirstName(),user.getLastName(), encRole, true);
                                request.getSession(true).setAttribute("AuthenticatUser", au);
                                response.sendRedirect(request.getContextPath()+"/admin");
                                return;
                            default:
                                return;
                        }
                        
                    }else{
                        //if for some reason the user is mother fucking null
                        HttpSession sess = request.getSession();
                        sess.setAttribute("loginError", "אין משתמש כזה");
                        response.sendRedirect(request.getContextPath()+"/");
                        return;
                    }
                }
                else{
                    //wrong password do what ever you want dick head
                    HttpSession sess = request.getSession();
                    sess.setAttribute("loginError", "סיסמה לא נכונה");
                    response.sendRedirect(request.getContextPath()+"/");
                    return;
                }
            } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        {
            //if there is no such user name you little dirty russian
            HttpSession sess = request.getSession();
            sess.setAttribute("loginError", "אין משתמש כזה");
            response.sendRedirect(request.getContextPath()+"/");
            return;
        }
        
             
  
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
