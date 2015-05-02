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

    <%@include file='../manager_templates/sidebar.jsp'%>

    <div id="content-wrapper">
      <div class="page-content">

        <%@include file='../manager_templates/header.jsp'%>

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
     $('li.sidebar-list a').on('click', function() {
      var parent = $('#page-wrapper');
      if(!parent.hasClass('open')) {
        parent.addClass('open');

      }
    });
  </script>
</body>
</html>