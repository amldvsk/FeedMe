<jsp:directive.page contentType="text/html;charset=UTF-8"/> 
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="http://maps.googleapis.com/maps/api/js?sensor=false&amp;libraries=places"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=request.getScheme().toString() %>://<%=request.getServerName().toString() %>:<%=request.getServerPort() %><%=request.getContextPath() %>/assets/js/bootstrap.min.js"></script>
    <script src="<%=request.getScheme().toString() %>://<%=request.getServerName().toString() %>:<%=request.getServerPort() %><%=request.getContextPath() %>/assets/js/bootstrap-select.min.js"></script>
    <script src="<%=request.getScheme().toString() %>://<%=request.getServerName().toString() %>:<%=request.getServerPort() %><%=request.getContextPath() %>/assets/js/jquery.geocomplete.min.js"></script>
    <script src="<%=request.getScheme().toString() %>://<%=request.getServerName().toString() %>:<%=request.getServerPort() %><%=request.getContextPath() %>/assets/js/modernizr.js"></script>
    <script src="<%=request.getScheme().toString() %>://<%=request.getServerName().toString() %>:<%=request.getServerPort() %><%=request.getContextPath() %>/assets/js/website_main.js"></script>
    
    <c:if  test="${loginError != null }" >
        <script>
            
            $(document).ready(function() {
                confirm("${loginError}");
            });
            
        </script>
    </c:if>
    
  </body>
</html>