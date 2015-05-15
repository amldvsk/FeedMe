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
          <a href="#">דשבורד <span class="menu-icon fa fa-tachometer"></span></a>
        </li>
        <li class="sidebar-list">
          <a href="#/tables">משלוחים <span class="menu-icon fa fa-truck"></span></a>
        </li>
        <li class="sidebar-list">
          <a href="#/tables">ניהול מסעדה <span class="menu-icon fa fa-wrench"></span></a>
        </li>
        <li class="sidebar-list">
          <a data-toggle="collapse" data-parent=".sidebar-list" href="#collapseOne" class="collapsed">תפריט <span class="menu-icon fa fa-bars"></span></a>
            <div class="panel clearfix">
                  <div id="collapseOne" class="panel-collapse collapse">
                      <div class="panel-body">
                          <div class="sub-links">
                            <a href="#" class="list-group-item">תפריט</a>
                            <a href="#" class="list-group-item">הוספה פריט</a>
                          </div>
                      </div>
                  </div>
              </div>
        </li>
        <li class="sidebar-list">
          <a data-toggle="collapse" data-parent=".sidebar-list" href="#collapseTwo" class="collapsed">דוחות <span class="menu-icon fa fa-area-chart"></span></a>
            <div class="panel clearfix">
                  <div id="collapseTwo" class="panel-collapse collapse">
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
        <li class="sidebar-list">
          <a data-toggle="collapse" data-parent=".sidebar-list" href="#collapseThree" class="collapsed">לקוחות <span class="menu-icon fa fa-users"></span></a>
            <div class="panel clearfix">
                  <div id="collapseThree" class="panel-collapse collapse">
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