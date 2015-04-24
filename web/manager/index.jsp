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
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/manager_main.css">
  <!-- endbuild -->
  <!-- SCRIPTS -->
  <!-- build:js lib/js/main.min.js -->

  <!-- endbuild -->
  <!-- Custom Scripts -->

  <!-- DataTables CSS -->
<link rel="stylesheet" type="text/css" href="http://cdn.datatables.net/1.10.5/css/jquery.dataTables.css">
  
  

  
</head>
<body >
    <div id="page-wrapper">

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
          <a data-toggle="collapse" data-parent=".sidebar-list" href="#collapseOne" class="collapsed">משלוחנים <span class="menu-icon fa fa-motorcycle"></span></a>
            <div class="panel clearfix">
                  <div id="collapseOne" class="panel-collapse collapse">
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

    <div id="content-wrapper">
      <div class="page-content">

        <!-- Header Bar -->
        <div class="row header">
          <div class="col-xs-12">
            <div class="user pull-left">
              <div class="item dropdown" >
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                  <img src="http://www.getrestaur.co.il/upload/companieslogo/1000873.png" class="img-circle pull-right">
                  <span class="rest-name">נאפיס</span>
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
                ניהול מסעדה
              </div>
              <div class="breadcrumb-links hidden">
                בית / דשבורד
              </div>
            </div>
          </div>
        </div>
        <!-- End Header Bar -->

        <!-- Main Content -->
        <div class="main-content">
          <section class="white border hidden">
            
          </section>
          <section>
            <div class="container-fluid">

              <div class="row">
        
          

                <div class="col-lg-3 col-sm-6 col-xs-6 col-xxs-12">
                  <div class="smallstat">
                    <div class="boxchart-overlay blue">
                      <div class="boxchart"><canvas width="64" height="30" style="display: inline-block; vertical-align: top; width: 64px; height: 30px;"></canvas></div>
                    </div>
                    <span class="value">19,999 &#8362;</span>  
                    <span class="title">סך הכל הכנסות</span>
                      
                  </div>
                </div><!--/col-->

                <div class="col-lg-3 col-sm-6 col-xs-6 col-xxs-12">
                  <div class="smallstat">
                    <div class="linechart-overlay red">
                      <div class="linechart"><canvas width="65" height="30" style="display: inline-block; vertical-align: top; width: 65px; height: 30px;"></canvas></div>
                    </div>
                    <span class="value">789</span>  
                    <span class="title">עסקאות</span>            
                  </div>
                </div><!--/col-->

                <div class="col-lg-3 col-sm-6 col-xs-6 col-xxs-12">
                  <div class="smallstat">
                    <i class="green">&#8362;</i>
                    <span class="value">1,999.99 &#8362;</span>
                    <span class="title">הכנסות היום</span> 
                  </div>
                </div><!--/col-->

                <div class="col-lg-3 col-sm-6 col-xs-6 col-xxs-12">
                  <div class="smallstat">
                    <div class="piechart-overlay blue">
                      <div class="piechart easyPieChart" data-percent="55" style="width: 40px; height: 40px; line-height: 40px;"><span>54</span>%<canvas width="40" height="40"></canvas></div>
                    </div>
                    <span class="value">4,859</span> 
                    <span class="title">לקוחות</span>    
                  </div>
                </div><!--/col-->

              </div>

              <div class="row">
                <div class="col-md-6">
                  <div class="panel panel-default">
                    <!-- Default panel contents -->
                    <div class="panel-heading">הזמנות</div>
                    <div class="panel-body">
                      <p>לורם איפסום דולור סיט אמט, קונסקטורר אדיפיסינג אלית לפרומי בלוף קינץ תתיח לרעח. לת צשחמי צש בליא, מנסוטו צמלח לביקו ננבי, צמוקו בלוקריה שיצמה ברורק. נולום ארווס סאפיאן - פוסיליס קוויס.</p>
                      <div class="table-responsive">
                      <!-- Table -->
                        <table class="table sort-table">
                          <thead>
                            <tr>
                              <th>#</th>
                              <th>שם</th>
                              <th>רחוב</th>
                              <th>שעת הזמנה</th>
                              <th>סטטוס</th>
                            </tr>
                          </thead>
                          <tbody>
                            <tr>
                              <th scope="row">1</th>
                              <td>בשה משה</td>
                              <td>מבצע עובדה 51</td>
                              <td>13/03/15 10:08:00</td>
                              <td><span class="label label-danger">סטטוס 1</span></td>
                            </tr>
                            <tr>
                              <th scope="row">2</th>
                              <td>אשה משה</td>
                              <td>מבצע עובדה 51</td>
                              <td>13/03/15 10:08:00</td>
                              <td><span class="label label-success">סטטוס 2</span></td>
                            </tr>
                            <tr>
                              <th scope="row">3</th>
                              <td>דשה משה</td>
                              <td>מבצע עובדה 51</td>
                              <td>13/03/15 10:08:00</td>
                              <td><span class="label label-info">סטטוס 3</span></td>
                            </tr>
                          </tbody>
                        </table>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="col-md-6">
                  <div class="panel panel-default">
                    <!-- Default panel contents -->
                    <div class="panel-heading">במשלוח</div>
                    <div class="panel-body">
                      <p>לורם איפסום דולור סיט אמט, קונסקטורר אדיפיסינג אלית לפרומי בלוף קינץ תתיח לרעח. לת צשחמי צש בליא, מנסוטו צמלח לביקו ננבי, צמוקו בלוקריה שיצמה ברורק. נולום ארווס סאפיאן - פוסיליס קוויס.</p>
                      <div class="table-responsive">
                      <!-- Table -->
                        <table class="table sort-table">
                          <thead>
                            <tr>
                              <th>#</th>
                              <th>שם</th>
                              <th>רחוב</th>
                              <th>שעת הזמנה</th>
                              <th>סטטוס</th>
                            </tr>
                          </thead>
                          <tbody>
                            <tr>
                              <th scope="row">1</th>
                              <td>משה משה</td>
                              <td>מבצע עובדה 51</td>
                              <td>13/03/15 10:08:00</td>
                              <td><span class="label label-danger">סטטוס 1</span></td>
                            </tr>
                            <tr>
                              <th scope="row">2</th>
                              <td>משה משה</td>
                              <td>מבצע עובדה 51</td>
                              <td>13/03/15 10:08:00</td>
                              <td><span class="label label-success">סטטוס 2</span></td>
                            </tr>
                            <tr>
                              <th scope="row">3</th>
                              <td>משה משה</td>
                              <td>מבצע עובדה 51</td>
                              <td>13/03/15 10:08:00</td>
                              <td><span class="label label-info">סטטוס 3</span></td>
                            </tr>
                          </tbody>
                        </table>
                      </div>
                    </div>
                  </div>
                </div>
              </div>

              <div class="row">
                <div class="col-md-6">
                  <div class="panel panel-default">
                    <!-- Default panel contents -->
                    <div class="panel-heading">משלוחנים</div>
                    <div class="panel-body">
                      <p>לורם איפסום דולור סיט אמט, קונסקטורר אדיפיסינג אלית לפרומי בלוף קינץ תתיח לרעח. לת צשחמי צש בליא, מנסוטו צמלח לביקו ננבי, צמוקו בלוקריה שיצמה ברורק. נולום ארווס סאפיאן - פוסיליס קוויס.</p>
                      <div class="table-responsive">
                      <!-- Table -->
                        <table class="table sort-table">
                          <thead>
                            <tr>
                              <th>#</th>
                              <th>שם</th>
                              <th>זמן שפנוי</th>
                              <th>סטטוס</th>
                            </tr>
                          </thead>
                          <tbody>
                            <tr>
                              <th scope="row">1</th>
                              <td>משה משה</td>
                              <td>02:08:00</td>
                              <td><span class="label label-danger">סטטוס 1</span></td>
                            </tr>
                            <tr>
                              <th scope="row">2</th>
                              <td>משה משה</td>
                              <td>01:08:00</td>
                              <td><span class="label label-success">סטטוס 2</span></td>
                            </tr>
                            <tr>
                              <th scope="row">3</th>
                              <td>משה משה</td>
                              <td>05:08:00</td>
                              <td><span class="label label-info">סטטוס 3</span></td>
                            </tr>
                          </tbody>
                        </table>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="col-md-6">
                  <div class="panel panel-default">
                    <!-- Default panel contents -->
                    <div class="panel-heading">משלוחים פעילים</div>
                    <div class="panel-body">
                       <div id="cd-google-map">
                          <div id="google-container"></div>
                          <div id="cd-zoom-in"></div>
                          <div id="cd-zoom-out"></div>
                        </div>
                    </div>
                  </div>
                </div>
              </div>


            </div>
          </section>

         

        </div>

      </div><!-- End Page Content -->
    </div><!-- End Content Wrapper -->
  </div><!-- End Page Wrapper -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
  <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDjy0n0TFNKTZ4S5Hq2w_FVU4E5EglSd6M&language=he"></script>
  <script type="text/javascript" charset="utf8" src="http://cdn.datatables.net/1.10.5/js/jquery.dataTables.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/manager_main.js"></script> 
  <script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
  <script type="text/javascript">
    $('#toggleSidebar').on('click', function(e) {
      e.preventDefault();
      var parent = $('#page-wrapper');
      if(parent.hasClass('open')) {
        parent.removeClass('open');

      } else {
        parent.addClass('open');
      }
    });

    $(document).ready( function () {
        $('.sort-table').DataTable({
          paging: false,
          searching: false,
          "info":     false
        });
    } );
  </script>
</body>
</html>