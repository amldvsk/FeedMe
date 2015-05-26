/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedme.controller;

import feedme.model.AuthenticatUser;
import feedme.model.DbMenuManagment;
import feedme.model.DbOrderManagement;
import feedme.model.DbRestaurantsManagement;
import feedme.model.Order;
import feedme.model.PasswordEncryptionService;
import feedme.model.Restaurant;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author User
 */
@WebServlet(name = "AddOrEditMenuItemServlet", urlPatterns = {"/manager/menu-item-management"})
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
                 maxFileSize=1024*1024*10,      // 10MB
                 maxRequestSize=1024*1024*50)   // 50MB
public class AddOrEditMenuItemServlet extends HttpServlet {

    private static final String SAVE_DIR = "/assets/Uploads";
        private String fileName;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        
        AuthenticatUser manager = (AuthenticatUser)request.getSession().getAttribute("AuthenticatUser");
        try {
            if(manager == null || !PasswordEncryptionService.authenticate(Integer.toString(1), manager.getEncrypRole(), "Manager".getBytes())) {
                response.sendRedirect(request.getContextPath() + "/");
                return;
            }   } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AddOrEditRestaurantServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(AddOrEditRestaurantServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }
        
        List<Restaurant> reslist = new DbRestaurantsManagement().getRestaurantsByManagerId(manager.getUserId());
        HashMap<Integer ,String> menuCat = new DbMenuManagment().getMenuCat();
        request.setAttribute("categories", menuCat);
        for( Restaurant re : reslist )
                        if( re.getDbid() == manager.getManagerRestId() )
                            request.setAttribute("restaurant", re);
        
        RequestDispatcher  dispatcher = request.getRequestDispatcher("edit_menu_item.jsp");
        dispatcher.forward(request, response);
        return;
        
    }   

    
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        AuthenticatUser manager = (AuthenticatUser)request.getSession().getAttribute("AuthenticatUser");
        try {
            if(manager == null || !PasswordEncryptionService.authenticate(Integer.toString(1), manager.getEncrypRole(), "Manager".getBytes())) {
                response.sendRedirect(request.getContextPath() + "/");
                return;
            }   
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AddOrEditRestaurantServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(AddOrEditRestaurantServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }
        
        request.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);       
        String itemName= request.getParameter("itemName");
        String itemPrice= request.getParameter("itemPrice");
        String itemDescrip= request.getParameter("itemDescrip");
        String itemImagePath= request.getParameter("itemImagePath");
        
        String action= request.getParameter("action"); // 1=Add , 2=Edit , 3=Delete
        int result=0;
        DbMenuManagment ob = new DbMenuManagment();
        switch(Integer.parseInt(action)){
            case 1:
                uploadLogoToServer(request);//==========### File(logo) uploading to server ##===========   

                String itemMenuCatId= request.getParameter("itemMenuCatId");
                String itemRestId= request.getParameter("itemRestId");
                int itemMenuId= new DbRestaurantsManagement().getRestaurantById(Integer.parseInt(itemRestId)).getMenuId();
                result = ob.AddNewMenuItem(itemName, Double.parseDouble(itemPrice), itemDescrip, fileName, Integer.parseInt(itemMenuCatId), Integer.parseInt(itemRestId), itemMenuId);                
                break;           
            case 2 :
                 String itemId= request.getParameter("itemId");
                 result= ob.updateMenuItem(Integer.parseInt(itemId), itemName, Double.parseDouble(itemPrice), itemDescrip, itemImagePath);
                break;
            case 3:
                break;
            default:
                break;
        }
        if (result!=0){// ok
                    HttpSession session = request.getSession(true);
                    session.setAttribute("Status", true);
                    response.sendRedirect(request.getContextPath() + "/manager/menus");
                }else{//err
                    HttpSession session = request.getSession(true);
                    session.setAttribute("Status", false);
                }
        

    }
    private static String getFileExtension(String fileName) {        
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
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
    private void uploadLogoToServer(HttpServletRequest request) throws IOException, ServletException {
        String appPath = getServletConfig().getServletContext().getRealPath("/");
        
        Calendar cal = Calendar.getInstance();       
        String SaveLogoPath = appPath + File.separator + SAVE_DIR + File.separator;         
        // creates the save directory if it does not exists
        File fileSaveDir = new File(SaveLogoPath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }                 
        Part part = request.getPart("logo");
        String originalFileName = extractFileName(part);
        fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(cal.getTime())+"."+getFileExtension(originalFileName);
        part.write(SaveLogoPath + File.separator + fileName);               
        request.setAttribute("message", "OK");    }
}
