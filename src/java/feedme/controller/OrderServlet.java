
package feedme.controller;

import feedme.model.DbOrderManagement;
import feedme.model.HashMapKey;
import feedme.model.Item;
import feedme.model.Order;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author nirk
 */
@WebServlet(name = "OrderServlet", urlPatterns = {"/update-cart"})
public class OrderServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
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
        int action = Integer.parseInt(request.getParameter("action"));
        HashMapKey rest_item = new HashMapKey(itemid, restid);
        HttpSession session = request.getSession(false);
        Order cart =(Order)session.getAttribute("shoppingCart");
        if( action == 1 ) {
             DbOrderManagement dbOrderManagement = new DbOrderManagement();
            Item item = dbOrderManagement.getItemById(itemid);
            item.setRestId(restid);
            if(cart.getRestItemsMap().get(rest_item) == null)
            {
                cart.getRestItemsMap().put(rest_item,item);
            } else {
                cart.getRestItemsMap().get(rest_item).increaseQunatity();
            }
        } else {
            cart.getRestItemsMap().remove(rest_item);
        }
       
        
       
        
       request.getSession().setAttribute("shoppingCart", cart);
        try {
            JSONObject orderObj = new JSONObject();
            orderObj.put("cart", ((Order)session.getAttribute("shoppingCart")).toJson());
            orderObj.put("cart_sum", cart.calcSum());
            response.setContentType("application/json");
            PrintWriter writer = response.getWriter();
            writer.print(orderObj);
            response.getWriter().flush();
        } catch (JSONException ex) {
            Logger.getLogger(OrderServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
            

        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
