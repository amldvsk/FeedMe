package feedme.controller;

import feedme.model.DBRestaurantsManagement;
import feedme.model.Restaurant;
import java.io.File;
import java.io.IOException;
//import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
//import org.apache.tomcat.util.http.fileupload.FileItemIterator;
//import org.apache.tomcat.util.http.fileupload.FileItemStream;
//import org.apache.tomcat.util.http.fileupload.FileUploadBase;
//import org.apache.tomcat.util.http.fileupload.FileUploadException;
//import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author David Lazarev
 */
@WebServlet(name = "AddRestaurantServlet", urlPatterns = {"/add-resturent"})
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
                 maxFileSize=1024*1024*10,      // 10MB
                 maxRequestSize=1024*1024*50)   // 50MB
public class AddOrEditRestaurantServlet extends HttpServlet {

    private static final String SAVE_DIR = "Uploads";
    
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }   
    /**
    *
    * @author David Lazarev
    * @action to know what to do, add || edit Restaurant
    */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        response.setStatus(HttpServletResponse.SC_OK);
        String action= request.getParameter("action");  
         RequestDispatcher dispatcher = request.getRequestDispatcher("newjsp.jsp");
         
        //==========### Get parameters from JSP page ##===========
        String newName= request.getParameter("newName"); 
        String correntName= request.getParameter("correntName"); 
        String phone= request.getParameter("phone"); 
        String category= request.getParameter("category"); 
        String logo= request.getParameter("logo"); 
        String street= request.getParameter("street"); 
        String streetNum= request.getParameter("streetNum"); 
        String city= request.getParameter("city"); 
        String deliveryPrice= request.getParameter("deliveryPrice"); 
        String minOrder= request.getParameter("minOrder");    
        int managerId = Integer.parseInt(request.getParameter("managerId"));
         
        //==========### File(logo) uploading to server ##===========
        // gets absolute path of the web application
        String appPath = "C:\\Users\\User\\Documents\\NetBeansProjects\\FeedMe\\web\\assets";//request.getServletContext().getRealPath("");
        // constructs path of the directory to save uploaded file
        String savePath = appPath + File.separator + SAVE_DIR;
         
        // creates the save directory if it does not exists
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
         
        for (Part part : request.getParts()) {
            String fileName = extractFileName(part);
            part.write(savePath + File.separator + fileName);
        }
 
        request.setAttribute("message", "Upload has been done successfully!");
        getServletContext().getRequestDispatcher("/message.jsp").forward(
                request, response);

        try{
           Integer.parseInt(minOrder);
            Integer.parseInt(deliveryPrice);
            Integer.parseInt(category);

        }catch(ClassCastException e){
            e.printStackTrace();
        }
           
        String estimatedTimeDel= request.getParameter("estimatedTimeDel"); 
        DBRestaurantsManagement ob= new DBRestaurantsManagement();
        int result=0;
        if(Integer.parseInt(action)== 1){// 1=add , 2= edit
            result = ob.addNewRestaurant(newName,Integer.parseInt(category),phone,logo,street,streetNum,city,Integer.parseInt(deliveryPrice),Integer.parseInt(minOrder),estimatedTimeDel ,managerId);
        }else if(Integer.parseInt(action)== 2){
            Restaurant res =  new Restaurant(correntName,phone,logo,street,streetNum,city,Integer.parseInt(deliveryPrice),Integer.parseInt(minOrder),estimatedTimeDel);
            if(correntName.equals(newName)==true) 
                result = ob.updateRestaurant(res,0 , managerId);  
            else
                result = ob.updateRestaurant(res,1 , managerId);
         }
        
        //==========### Send successful or failing session message  ##===========
        if(result == -1){ 
            HttpSession session = request.getSession(true);
            session.setAttribute("Status", false);
        }
        else if(result == 1){ 
            // new restaurant added successfully || restaurant successfully changed
            HttpSession session = request.getSession(true);
            session.setAttribute("Status", true);
        }  
        else{//2
            //This name is already exists in the database
            HttpSession session = request.getSession(true);
            session.setAttribute("exists", true);
        }
    }
   
    /**
     * Extracts file name from HTTP header content-disposition
     */
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
