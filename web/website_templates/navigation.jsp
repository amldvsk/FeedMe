<%@page import="feedme.model.AuthenticatUser"%>
<%@page import="feedme.model.PasswordEncryptionService"%>
<jsp:directive.page contentType="text/html;charset=UTF-8"/>
<nav class="navbar navbar-default navbar-static-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="${pageContext.request.contextPath}"><img src="${pageContext.request.contextPath}/assets/img/logo_b.png" alt="placeholder+image"></a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
           <!--<li><a href="../navbar/">Default</a></li>-->
            <!--<li class=""><a href="./">Static top <span class="sr-only">(current)</span></a></li>-->
            <!--<li><a href="../navbar-fixed-top/">Fixed top</a></li>-->
            <li><a href="${pageContext.request.contextPath}/website/about.jsp">קצת עלינו</a></li>
            <c:choose>
                <c:when test="${sessionScope.AuthenticatUser != null}">
                   <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">שלום, ${sessionScope.AuthenticatUser.getUserFirstName()} ${sessionScope.AuthenticatUser.getUserLastName()} <span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <%
                                // This scriptlet declares and initializes "date"
                                AuthenticatUser user = (AuthenticatUser)request.getSession().getAttribute("AuthenticatUser");
                                if( PasswordEncryptionService.authenticate(Integer.toString(1), user.getEncrypRole(), "Manager".getBytes() )) {
                                    out.println( "<li><a href='"+ request.getContextPath() +"/manager'>ניהול מסעדה</a></li>" );
                                } else if( PasswordEncryptionService.authenticate(Integer.toString(0), user.getEncrypRole(), "Customer".getBytes() ) ) {
                                    out.println( "<li><a href='"+ request.getContextPath() +"/profile'>פרופיל</a></li>" );
                                } else if( PasswordEncryptionService.authenticate(Integer.toString(2), user.getEncrypRole(), "Admin".getBytes() ) ) {
                                    out.println( "<li><a href='"+ request.getContextPath() +"/admin'>ניהול אתר</a></li>" );
                                }
                            %>
                          <li class="divider"></li>
                          <li><a href="${pageContext.request.contextPath}/logout">התנתק</a></li>
                        </ul>
                      </li>
                </c:when>
                <c:otherwise>
                    <li><a class="feed-signin" href="#0">התחבר</a></li>
                    <li><a class="feed-signup" href="#0">הירשם</a></li>
                </c:otherwise>
            </c:choose>
            <li id="cd-cart-trigger"><a class="cd-img-replace" href="#0"><i class="fa fa-shopping-cart"></i></a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
