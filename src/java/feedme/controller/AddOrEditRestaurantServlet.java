package feedme.controller;

import feedme.model.DbAdminManagmentTools;
import feedme.model.DbHPOnLoad;
import feedme.model.DbRestaurantsManagement;
import feedme.model.Restaurant;
import feedme.model.User;
import java.io.File;
import java.io.IOException;
//import java.io.InputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
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

        private static final String SAVE_DIR = "/assets/Uploads";
        private String fileName;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        DbAdminManagmentTools dbAdminManage = new DbAdminManagmentTools();
        DbHPOnLoad dbonLoad = new DbHPOnLoad();
        List<User> managers = dbAdminManage.getAllUsersByRole(1);
        
        HashMap<String , Integer > cat = dbonLoad.getCategories();
        request.setAttribute("managers", managers);
        request.setAttribute("categories", cat);
        
        RequestDispatcher  dispatcher = request.getRequestDispatcher("admin/editResturent.jsp");
        dispatcher.forward(request, response);
        
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
        
        request.setCharacterEncoding("UTF-8");
        
        
        response.setStatus(HttpServletResponse.SC_OK);
        String action= request.getParameter("action");  
          
         
        //==========### Get parameters from JSP page ##===========
        String newName= request.getParameter("newName"); 
        String correntName= request.getParameter("correntName"); 
        String phone= request.getParameter("phone"); 
        String category= request.getParameter("category"); 
   
        String street= request.getParameter("street"); 
        String streetNum= request.getParameter("streetNum"); 
        String city= request.getParameter("city"); 
        String deliveryPrice= request.getParameter("deliveryPrice"); 
        String minOrder= request.getParameter("minOrder");    
        int managerId = Integer.parseInt(request.getParameter("managerId"));
         
       //==========### File(logo) uploading to server ##===========
        //Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
       // String fileName = filePart.getSubmittedFileName();
        String appPath = getServletConfig().getServletContext().getRealPath("/");
        //System.out.println("##########"+);
        //==================================
        String RealPath = request.getServletContext().getRealPath("/Uploads");
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar cal = Calendar.getInstance();
        // gets absolute path of the web application
        //request.getServletContext().getRealPath("");
        // constructs path of the directory to save uploaded file
        String SaveLogoPath = appPath + File.separator + SAVE_DIR;
         
        // creates the save directory if it does not exists
        File fileSaveDir = new File(SaveLogoPath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }                 
        for (Part part : request.getParts()) {
             fileName = extractFileName(part);
            part.write(SaveLogoPath + File.separator + fileName);
        }               
        File currentImageName = new File(RealPath+fileName);
        //check File Type
        //String contentType = getServletContext().getMimeType(fileName);
        
        if(getFileExtension(currentImageName).equals("jpg")){
            currentImageName.renameTo(new File(RealPath+dateFormat.format(cal.getTime())+".jpg"));
        
        }else if (getFileExtension(currentImageName).equals("png")){
            currentImageName.renameTo(new File(RealPath+dateFormat.format(cal.getTime())+".png"));
        }
        else{
            request.setAttribute("message", "Error,the file type shuld by .png or .jpg only !! " );
            getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);
            currentImageName.delete();

        }
        
        request.setAttribute("message", "OK");
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
        DbRestaurantsManagement ob= new DbRestaurantsManagement();
        int result=0;
        if(Integer.parseInt(action)== 1){// 1=add , 2= edit
            result = ob.addNewRestaurant(newName,Integer.parseInt(category),phone,fileName,street,streetNum,city,Integer.parseInt(deliveryPrice),Integer.parseInt(minOrder),estimatedTimeDel ,managerId);
        }else if(Integer.parseInt(action)== 2){
            Restaurant res =  new Restaurant(correntName,phone,fileName,street,streetNum,city,Integer.parseInt(deliveryPrice),Integer.parseInt(minOrder),estimatedTimeDel);
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
            RequestDispatcher dispatcher = request.getRequestDispatcher("/resturents");
            dispatcher.forward(request, response);
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
    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
