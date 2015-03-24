<%-- 
    Document   : success
    Created on : 17/03/2015, 20:13:08
    Author     : nirk
--%>
<%@page import="feedme.model.User" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login Success!</h1> <br/>
        <%
            User user = (User) request.getAttribute("user");
        %>
            Hello <%= user.getUserName() %>!
    </body>
</html>
