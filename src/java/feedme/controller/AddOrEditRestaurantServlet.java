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
 * @author David Lazarev
 */
@WebServlet(name = "AddRestaurantServlet", urlPatterns = {"/AddRestaurantServlet"})
public class AddOrEditRestaurantServlet extends HttpServlet {

 
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
         
        //==========### File(logo) uploading to server ##===========
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
            result = ob.addNewRestaurant(newName,Integer.parseInt(category),phone,logo,street,streetNum,city,Integer.parseInt(deliveryPrice),Integer.parseInt(minOrder),estimatedTimeDel);
        }else if(Integer.parseInt(action)== 2){
            Restaurant res =  new Restaurant(correntName,phone,logo,street,streetNum,city,Integer.parseInt(deliveryPrice),Integer.parseInt(minOrder),estimatedTimeDel);
            //if(correntName.equals(newName)==true) 
                //result = ob.EditRestaurant(res,0);  
            //else
               // result = ob.EditRestaurant(res,1);
         }
        
        //==========### Send successful or failing session message  ##===========
        if(result == -1){ 
           //request.setAttribute("user", user);
            dispatcher = request.getRequestDispatcher("restorantpage.jsp");
            dispatcher.forward(request, response);
        }
        else if(result == 1){ // new restaurant added successfully || restaurant successfully changed

        }  
        else{//2
            //This name is already exists in the database
        }
    }
   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
