<%-- 
    Document   : newjsp
    Created on : Apr 12, 2015, 7:42:57 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form method="post" action="http://localhost:8025/FeedMe/AddRestaurantServlet"
              enctype="multipart/form-data" >
        
            select image : <input type="file" name="file"/>
            <input type="submit" value="go!"/>

        </form>
    </body>
</html>
