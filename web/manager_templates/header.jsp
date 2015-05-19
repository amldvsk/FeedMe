 <jsp:directive.page contentType="text/html;charset=UTF-8"/>
<!-- Header Bar -->
        <div class="row header">
          <div class="col-xs-12">
            <div class="user pull-left">
              <div class="item dropdown" >
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                  <img src="${pageContext.request.contextPath}/assets/Uploads/${requestScope.restaurant.getLogo()}" class="img-circle pull-right">
                  <span class="rest-name">${requestScope.restaurant.getName()}</span>
                </a>
                <ul class="dropdown-menu dropdown-menu-left" role="menu">
                  <li class="dropdown-header">
                    תפריט
                  </li>
                  <li class="divider"></li>
                  <li class="link">
                    <a href="#">
                      משהו
                    </a>
                  </li>
                  <li class="link">
                    <a href="#">
                      משהו
                    </a>
                  </li>
                  <li class="link">
                    <a href="#">
                      משהו
                    </a>
                  </li>
                  <li class="divider"></li>
                  <li class="link">
                    <a href="#">
                      התנתק
                    </a>
                  </li>
                </ul>
              </div>
              <div class="item dropdown">
               <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                  <i class="fa fa-bell"></i>
                </a>
                <ul class="dropdown-menu dropdown-menu-left" role="menu">
                  <li class="dropdown-header">
                    התראות
                  </li>
                  <li class="divider"></li>
                  <li class="link">
                    <a href="#">משהו</a>
                  </li>
                </ul>
              </div>
              <div class="item dropdown">
               <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                  <i class="fa fa-envelope"></i>
                </a>
                <ul class="dropdown-menu dropdown-menu-left" role="menu">
                  <li class="dropdown-header">
                    הודעות
                  </li>
                  <li class="divider"></li>
                  <li class="link">
                    <a href="#">משהו</a>
                  </li>
                </ul>
              </div>
              <div class="item"></div>
            </div>
            <div class="meta">
              <div class="page">
                ${sessionScope.AuthenticatUser.getUserFirstName()} ${sessionScope.AuthenticatUser.getUserLastName()} -
                ניהול מסעדה
                
              </div>
              <div class="breadcrumb-links hidden">
                בית / דשבורד
              </div>
            </div>
          </div>
        </div>
        <!-- End Header Bar -->