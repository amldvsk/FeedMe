 <jsp:directive.page contentType="text/html;charset=UTF-8"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  
  <title>FeedMe - ניהול</title>
  <!-- STYLES -->
  <!-- build:css lib/css/main.min.css -->
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/admin_main.css">
  <!-- endbuild -->
  <!-- SCRIPTS -->
  <!-- build:js lib/js/main.min.js -->

  <!-- endbuild -->
  <!-- Custom Scripts -->

  <!-- DataTables CSS -->
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.5/css/jquery.dataTables.css">
  
  

  
</head>
<body >
    <div id="page-wrapper">

    <%@include file='../admin_templates/sidebar.jsp'%>

    <div id="content-wrapper">
      <div class="page-content">

        <%@include file='../admin_templates/header.jsp'%>