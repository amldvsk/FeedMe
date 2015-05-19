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
            <c:choose>
                <c:when test="${sessionScope.AuthenticatUser != null}">
                   <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">שלום, ${sessionScope.AuthenticatUser.getUserFirstName()} ${sessionScope.AuthenticatUser.getUserLastName()} <span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                          <li><a href="#">Action</a></li>
                          <li><a href="#">Another action</a></li>
                          <li><a href="#">Something else here</a></li>
                          <li class="divider"></li>
                          <li><a href="#">Separated link</a></li>
                          <li class="divider"></li>
                          <li><a href="#">One more separated link</a></li>
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
