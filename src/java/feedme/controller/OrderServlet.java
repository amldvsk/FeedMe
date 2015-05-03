
package feedme.controller;

import feedme.model.DbOrderManagement;
import feedme.model.Item;
import feedme.model.Order;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nirk
 */
@WebServlet(name = "OrderServlet", urlPatterns = {"/OrderServlet"})
public class OrderServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet OrderServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        int itemid = Integer.parseInt(request.getParameter("itemid"));
        int restid = Integer.parseInt(request.getParameter("restid"));

        HttpSession session = request.getSession(true);
        Order cart =(Order)session.getAttribute("shoppingCart");
        if (cart == null) // No cart already in session
        {
            cart = new Order();
            session.setAttribute("shoppingCart", cart);
        
            DbOrderManagement dbOrderManagement = new DbOrderManagement();
            Item item = dbOrderManagement.getItemById(itemid);
            if(cart.getRestItemsMap().get(new Integer[]{restid,itemid}) == null)
            {
                cart.getRestItemsMap().get(new Integer[]{restid,itemid}).increaseQunatity();
                cart.getRestItemsMap().put(new Integer[]{restid,itemid},item);
                
            }
            }
            else
            {
                
                cart.getRestItemsMap().get(new Integer[]{restid,itemid}).increaseQunatity();
                
            }
            
           
        request.setAttribute("cart", cart);
        
        RequestDispatcher  dispatcher = request.getRequestDispatcher("website/index.jsp");
        dispatcher.forward(request, response);
        
            

        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
