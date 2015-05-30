<jsp:directive.page contentType="text/html;charset=UTF-8"/>
<!-- Sidebar -->
    <div id="sidebar-wrapper">
      <ul class="sidebar">
        <li class="sidebar-main">
          <a id="toggleSidebar" href="#">
            <img src="${pageContext.request.contextPath}/assets/img/logoW.png" alt="placeholder+image">
            <i class="menu-icon fa fa-bars"></i>
          </a>
        </li>
        <li class="sidebar-title"><span>תפריט</span></li>
        <li class="sidebar-list">
          <a href="${pageContext.request.contextPath}/admin/index.jsp">דשבורד <span class="menu-icon fa fa-tachometer"></span></a>
        </li>
        <li class="sidebar-list">
          <a data-toggle="collapse" data-parent=".sidebar-list" href="#collapseTwo" class="collapsed">מסעדות <span class="menu-icon fa fa-university"></span></a>
            <div class="panel clearfix">
                  <div id="collapseTwo" class="panel-collapse collapse">
                      <div class="panel-body">
                        <div class="sub-links">
                          <a href="${pageContext.request.contextPath}/admin/resturents" class="list-group-item">כל המסעדות</a>
                          <a href="${pageContext.request.contextPath}/admin/add-resturent" class="list-group-item">הוספת מסעדה</a>
                        </div>
                      </div>
                  </div>
              </div>
        </li>
        <li class="sidebar-list">
          <a data-toggle="collapse" data-parent=".sidebar-list" href="#collapseManager" class="collapsed">מנהלי מסעדות <span class="menu-icon fa fa-suitcase"></span></a>
            <div class="panel clearfix">
                  <div id="collapseManager" class="panel-collapse collapse">
                      <div class="panel-body">
                        <div class="sub-links">
                          <a href="${pageContext.request.contextPath}/admin/managers" class="list-group-item">מנהלים</a>
                          <a href="${pageContext.request.contextPath}/admin/editManager.jsp" class="list-group-item">הוספת מנהל</a>
                        </div>
                      </div>
                  </div>
              </div>
        </li>
        <li class="sidebar-list">
          <a data-toggle="collapse" data-parent=".sidebar-list" href="#collapseMenu" class="collapsed">תפריטים <span class="menu-icon fa fa-th-list"></span></a>
            <div class="panel clearfix">
                  <div id="collapseMenu" class="panel-collapse collapse">
                      <div class="panel-body">
                        <div class="sub-links">
                          <!--<a href="${pageContext.request.contextPath}/admin/menus.jsp" class="list-group-item">כל התפריטים</a>-->
                          <a href="${pageContext.request.contextPath}/admin/menu-category" class="list-group-item">קטגוריות תפריטים</a>
                        </div>
                      </div>
                  </div>
              </div>
        </li>
        <li class="sidebar-list hidden">
          <a data-toggle="collapse" data-parent=".sidebar-list" href="#collapseUsers" class="collapsed">לקוחות <span class="menu-icon fa fa-users"></span></a>
            <div class="panel clearfix">
                  <div id="collapseUsers" class="panel-collapse collapse">
                      <div class="panel-body">
                          <div class="sub-links">
                            <a href="#" class="list-group-item">פריט</a>
                            <a href="#" class="list-group-item">פריט</a>
                            <a href="#" class="list-group-item">פריט</a>
                            <a href="#" class="list-group-item">פריט</a>
                          </div>
                      </div>
                  </div>
              </div>
        </li>
        <li class="sidebar-list hidden">
          <a data-toggle="collapse" data-parent=".sidebar-list" href="#collapseReports" class="collapsed">דוחות <span class="menu-icon fa fa-area-chart"></span></a>
            <div class="panel clearfix">
                  <div id="collapseReports" class="panel-collapse collapse">
                      <div class="panel-body">
                        <div class="sub-links">
                          <a href="#" class="list-group-item">פריט</a>
                          <a href="#" class="list-group-item">פריט</a>
                          <a href="#" class="list-group-item">פריט</a>
                          <a href="#" class="list-group-item">פריט</a>
                        </div>
                      </div>
                  </div>
              </div>
        </li>
      </ul>
      <div class="sidebar-footer">
        <div class="col-xs-4">
          <a href="https://github.com/rdash/rdash-angular" target="_blank">
            תפריט
          </a>
        </div>
        <div class="col-xs-4">
          <a href="https://github.com/rdash/rdash-angular/README.md" target="_blank">
            תפריט
          </a>
        </div>
        <div class="col-xs-4">
          <a href="#">
            תפריט
          </a>
        </div>
      </div>
    </div>
    <!-- End Sidebar -->