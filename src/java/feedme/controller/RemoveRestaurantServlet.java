package feedme.controller;

import feedme.model.DbRestaurantsManagement;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author David Lazarev
 */
public class RemoveRestaurantServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RemoveRestaurantServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RemoveRestaurantServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        // get restaurant pkid(from jsp page)that we want to remove from database 
        String RestaurantId= request.getParameter("id");  // pkid
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("newjsp.jsp");
        DbRestaurantsManagement ob= new DbRestaurantsManagement();
        //@result tell us if  restaurant was deleted successfully
        /*int result = ob.removeRestaurant(RestaurantId); // remove restaurant by pkid 
        //if error ,need to send session message to adi,elseredirect 
        if(result==-1){ 
            //Restaurant was deleted successfully
        }
        else { 
            //Error
       }  */  
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
