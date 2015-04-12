/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedme.controller;

import feedme.model.DBRestaurantsManagement;
import feedme.model.FileUpload;
import feedme.model.Restaurant;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author User
 */
@WebServlet(name = "AddRestaurantServlet", urlPatterns = {"/AddRestaurantServlet"})
public class AddRestaurantServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddRestaurant</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddRestaurant at " + request.getContextPath() + "</h1>");
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
        
        response.setStatus(HttpServletResponse.SC_OK);
        RequestDispatcher dispatcher = request.getRequestDispatcher("newjsp.jsp");

        String name= request.getParameter("name"); 
        String phone= request.getParameter("phone"); 
        String category= request.getParameter("category"); 
        String logo= request.getParameter("logo"); 
        String street= request.getParameter("street"); 
        String streetNum= request.getParameter("streetNum"); 
        String city= request.getParameter("city"); 
        String deliveryPrice= request.getParameter("deliveryPrice"); 
        String minOrder= request.getParameter("minOrder"); 
        //---------------------------
        //==========### File Uploading ##===========
        boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
        if(isMultiPart){
            ServletFileUpload upload = new ServletFileUpload();
            try{
                FileItemIterator itr = upload.getItemIterator(request);
                while(itr.hasNext()){
                    FileItemStream item=itr.next();
                    if(item.isFormField()){
                        String fieldName =item.getFieldName();
                        InputStream is = item.openStream();
                        byte[] b = new byte[is.available()];
                        is.read(b);
                        String value = new String(b);
                        response.getWriter().println(fieldName+":"+value+"<br/>");
                        
                        
                    } else {
                        String path= getServletContext().getRealPath("/");
                        if(FileUpload.processFile(path, item))
                            response.getWriter().println("file uploaded seccsesfuly");
                        else response.getWriter().println("file uploading failed");
                        
                    }
                }
                
            }catch(FileUploadException fue){
                fue.printStackTrace();
            }
        }
        //=======================
        
        try{
           Integer.parseInt(minOrder);
            Integer.parseInt(deliveryPrice);
            Integer.parseInt(category);

        }catch(ClassCastException e){
            e.printStackTrace();
        }
        String estimatedTimeDel= request.getParameter("estimatedTimeDel"); 
        DBRestaurantsManagement ob= new DBRestaurantsManagement();
        
        int result = ob.addNewRestaurant(name,Integer.parseInt(category),phone,logo,street,streetNum,city,Integer.parseInt(deliveryPrice),Integer.parseInt(minOrder),estimatedTimeDel);
        //need to send session message to adi if error and do redirect to 
        if(result==-1){ //restorant name || phone exists
            /**request.setAttribute("user", user);
            dispatcher = request.getRequestDispatcher("restorantpage.jsp");
            dispatcher.forward(request, response);**/
        }
        else if(result==1){ // new restaurant added successfully
            
       }
        
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
