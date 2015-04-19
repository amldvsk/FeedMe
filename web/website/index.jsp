<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <jsp:directive.page contentType="text/html;charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>FeedMe</title>

    <!-- Bootstrap -->
    <link href="<%=request.getScheme().toString() %>://<%=request.getServerName().toString() %>:<%=request.getServerPort() %><%=request.getContextPath() %>/assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getScheme().toString() %>://<%=request.getServerName().toString() %>:<%=request.getServerPort() %><%=request.getContextPath() %>/assets/css/website_style.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
  </head>
  <body>
    
    
    <nav class="navbar navbar-default navbar-static-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#"><img src="assets/img/logo_b.png" alt="placeholder+image"></a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
           <!--<li><a href="../navbar/">Default</a></li>-->
            <!--<li class=""><a href="./">Static top <span class="sr-only">(current)</span></a></li>-->
            <!--<li><a href="../navbar-fixed-top/">Fixed top</a></li>-->
            <li><a class="feed-signin" href="#0">התחבר</a></li>
            <li><a class="feed-signup" href="#0">הירשם</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
<c:forEach var="i" begin="1" end="5">
   <!--Item <c:out value="${i}"/><p>-->
</c:forEach>

    <header>

      <div class="header-wrapper">
        <div class="container">
          <div class="header-content">
            <h1>מה בא לך לאכול ?</h1>
            <div class="filters clearfix">
              <ul class="list-inline">
                <li>בחר איזור</li>
                <li class="seporator">&gt;</li>
                <li>בחר מסעדה</li>
                <li class="seporator">&gt;</li>
                <li>הוסף פריטים לסל</li>
                <li class="seporator">&gt;</li>
                <li>האוכל בדרך</li>
              </ul>
              <hr>
              <form class="form-inline">
                <div class="form-group">
                  <select class="selectpicker" data-width="100%">
                    <option>איפה תרצה לאכול ?</option>
                    <option>Ketchup</option>
                    <option>Relish</option>
                  </select>
                </div>
                <div class="form-group">
                  <select class="selectpicker" data-width="100%">
                    <option>מה תרצה לאכול ?</option>
                    <option>Ketchup</option>
                    <option>Relish</option>
                  </select>
                </div>
                <div class="form-group post">
                  <button type="submit" class="btn send btn-default">Feed Me</button>
                </div>
              </form>
            </div>  
          </div>
        </div>
      </div>

    </header>


     <section class="rest-section">
      <div class="container">
        <h2>מסעדות חדשות שנוספו</h2>
        <div class="sub-section">
          <ul class="list-inline rests">
            <li class="rest">
              <div class="rest-logo">
                <img src="http://www.cafe-hillel.co.il/wp-content/themes/hillel/img/logo-white.png" alt="placeholder+image">
              </div>
              <div class="rest-caption">
                <h4>קפה הלל</h4>
                <p><small><i class="fa fa-map-marker"></i> לילינבלום 40, תל אביב יפו</small></p>
                <div class="review">
                  <ul class="list-inline">
                      <li><i class="fa fa-star"></i></li>
                      <li><i class="fa fa-star"></i></li>
                      <li><i class="fa fa-star"></i></li>
                      <li><i class="fa fa-star"></i></li>
                      <li><i class="fa fa-star"></i></li>
                      <li>24 ביקורות</li>
                    </ul>
                </div> 
                <a href="#" class="btn btn-green btn-blcok">הזמן עכשיו</a> 
              </div>
            </li>
            <li class="rest">
              <div class="rest-logo">
                <img src="http://www.arabica-rest.co.il/images/logo.png" alt="placeholder+image">
              </div>
              <div class="rest-caption">
                <h4>ערביקה</h4>
                <p><small><i class="fa fa-map-marker"></i> לילינבלום 40, תל אביב יפו</small></p>
                <div class="review">
                  <ul class="list-inline">
                      <li><i class="fa fa-star"></i></li>
                      <li><i class="fa fa-star"></i></li>
                      <li><i class="fa fa-star"></i></li>
                      <li><i class="fa fa-star"></i></li>
                      <li><i class="fa fa-star"></i></li>
                      <li>24 ביקורות</li>
                    </ul>
                </div>  
                <a href="#" class="btn btn-green btn-blcok">הזמן עכשיו</a>
              </div>
            </li>
            <li class="rest">
              <div class="rest-logo">
                <img src="http://kampaistreetwok.co.il/wp-content/uploads/2014/08/0006_logo_kampai.png" alt="placeholder+image">
              </div>
              <div class="rest-caption">
                <h4>קמפאי</h4>
                <p><small><i class="fa fa-map-marker"></i> לילינבלום 40, תל אביב יפו</small></p>
                <div class="review">
                  <ul class="list-inline">
                      <li><i class="fa fa-star"></i></li>
                      <li><i class="fa fa-star"></i></li>
                      <li><i class="fa fa-star"></i></li>
                      <li><i class="fa fa-star"></i></li>
                      <li><i class="fa fa-star"></i></li>
                      <li>24 ביקורות</li>
                    </ul>
                </div>  
                <a href="#" class="btn btn-green btn-blcok">הזמן עכשיו</a>
              </div>
            </li>
            <li class="rest">
              <div class="rest-logo">
                <img src="http://www.blackbarburger.co.il/warehouse/content/galleries/photos/pic_154_Big.jpg" alt="placeholder+image">
              </div>
              <div class="rest-caption">
                <h4>Black Bar 'n' Burger</h4>
                <p><small><i class="fa fa-map-marker"></i> לילינבלום 40, תל אביב יפו</small></p>
                <div class="review">
                  <ul class="list-inline">
                      <li><i class="fa fa-star"></i></li>
                      <li><i class="fa fa-star"></i></li>
                      <li><i class="fa fa-star"></i></li>
                      <li><i class="fa fa-star"></i></li>
                      <li><i class="fa fa-star"></i></li>
                      <li>24 ביקורות</li>
                    </ul>
                </div>  
                <a href="#" class="btn btn-green btn-blcok">הזמן עכשיו</a>
              </div>
            </li>
            <li class="rest">
              <div class="rest-logo">
                <img src="http://www.arabica-rest.co.il/images/logo.png" alt="placeholder+image">
              </div>
              <div class="rest-caption">
                <h4>ערביקה</h4>
                <p><small><i class="fa fa-map-marker"></i> לילינבלום 40, תל אביב יפו</small></p>
                <div class="review">
                  <ul class="list-inline">
                      <li><i class="fa fa-star"></i></li>
                      <li><i class="fa fa-star"></i></li>
                      <li><i class="fa fa-star"></i></li>
                      <li><i class="fa fa-star"></i></li>
                      <li><i class="fa fa-star"></i></li>
                      <li>24 ביקורות</li>
                    </ul>
                </div>  
                <a href="#" class="btn btn-green btn-blcok">הזמן עכשיו</a>
              </div>
            </li>
            <li class="rest">
              <div class="rest-logo">
                <img src="http://kampaistreetwok.co.il/wp-content/uploads/2014/08/0006_logo_kampai.png" alt="placeholder+image">
              </div>
              <div class="rest-caption">
                <h4>קמפאי</h4>
                <p><small><i class="fa fa-map-marker"></i> לילינבלום 40, תל אביב יפו</small></p>
                <div class="review">
                  <ul class="list-inline">
                      <li><i class="fa fa-star"></i></li>
                      <li><i class="fa fa-star"></i></li>
                      <li><i class="fa fa-star"></i></li>
                      <li><i class="fa fa-star"></i></li>
                      <li><i class="fa fa-star"></i></li>
                      <li>24 ביקורות</li>
                    </ul>
                </div>  
                <a href="#" class="btn btn-green btn-blcok">הזמן עכשיו</a>
              </div>
            </li>
          </ul>
        </div>
      </div>
    </section>


     <section class="gray recommendations">
       <div class="container">
         <div class="sub-section">
            <div class="container text-center">
              <div class="row">
                <div class=" col-xs-8 col-sm-12">
                    <!-- Tab panes -->
                    <div class="tab-content" id="tabs-collapse">            
                        <div role="tabpanel" class="tab-pane fade in active" id="dustin">
                            <div class="tab-inner">                    
                                <p class="lead">לורם איפסום דולור סיט אמט, קונסקטורר אדיפיסינג אלית קולורס מונפרד אדנדום סילקוף, מרגשי ומרגשח. עמחליף ליבם סולגק. בראיט ולחת צורק מונחף, בגורמי מגמש. תרבנך וסתעד לכנו סתשם השמה - לתכי מורגם בורק? לתיג ישבעס.</p>
                                <hr>
                                <p><strong class="text-uppercase">Dustin Lamont</strong></p>
                                <p><em class="text-capitalize"> Senior web developer</em> at <a href="#">Apple</a></p>                 
                            </div>
                        </div>
                        
                        <div role="tabpanel" class="tab-pane fade" id="daksh">
                            <div class="tab-inner">
                                <p class="lead">לורם איפסום דולור סיט אמט, קונסקטורר אדיפיסינג אלית קולורס מונפרד אדנדום סילקוף, מרגשי ומרגשח. עמחליף ליבם סולגק. בראיט ולחת צורק מונחף, בגורמי מגמש. תרבנך וסתעד לכנו סתשם השמה - לתכי מורגם בורק? לתיג ישבעס.</p>
                                <hr>
                                <p><strong class="text-uppercase">Daksh Bhagya</strong></p>
                                <p><em class="text-capitalize"> UX designer</em> at <a href="#">Google</a></p>
                            </div>
                        </div>
                        
                        <div role="tabpanel" class="tab-pane fade" id="anna">
                            <div class="tab-inner">
                                <p class="lead">לורם איפסום דולור סיט אמט, קונסקטורר אדיפיסינג אלית קולורס מונפרד אדנדום סילקוף, מרגשי ומרגשח. עמחליף ליבם סולגק. בראיט ולחת צורק מונחף, בגורמי מגמש. תרבנך וסתעד לכנו סתשם השמה - לתכי מורגם בורק? לתיג ישבעס.</p>
                                <hr>
                                <p><strong class="text-uppercase">Anna Pickard</strong></p>
                                <p><em class="text-capitalize"> Master web developer</em> at <a href="#">Intel</a></p>
                            </div> 
                        </div>
                        
                        <div role="tabpanel" class="tab-pane fade" id="wafer">
                            <div class="tab-inner">
                                <p class="lead"> לורם איפסום דולור סיט אמט, קונסקטורר אדיפיסינג אלית קולורס מונפרד אדנדום סילקוף, מרגשי ומרגשח. עמחליף ליבם סולגק. בראיט ולחת צורק מונחף, בגורמי מגמש. תרבנך וסתעד לכנו סתשם השמה - לתכי מורגם בורק? לתיג ישבעס.</p>
                                <hr>
                                <p><strong class="text-uppercase">Wafer Baby</strong></p>
                                <p><em class="text-capitalize"> Web designer</em> at <a href="#">Microsoft</a></p>
                            </div>
                        </div>
                    </div>
                </div>        
              </div>
            </div>
         </div>
       </div>
     </section>

     <div class="avaters clearfix">
        <div class="container">
        <div class="people" role="tabpanel">
              <div class="">
                  <!-- Nav tabs -->
                  <ul class="[ nav nav-justified ]" id="nav-tabs" role="tablist">
                      <li role="presentation" class="active">
                          <a href="#dustin" aria-controls="dustin" role="tab" data-toggle="tab">
                              <div class="img-wrap"><img class="img-circle" src="https://s3.amazonaws.com/uifaces/faces/twitter/dustinlamont/128.jpg" /></div>
                              <span class="quote"><i class="fa fa-quote-left"></i></span>
                          </a>
                      </li>
                      <li role="presentation" class="">
                          <a href="#daksh" aria-controls="daksh" role="tab" data-toggle="tab">
                              <div class="img-wrap"><img class="img-circle" src="https://s3.amazonaws.com/uifaces/faces/twitter/dakshbhagya/128.jpg" /></div>
                              <span class="quote"><i class="fa fa-quote-left"></i></span>
                          </a>
                      </li>
                      <li role="presentation" class="">
                          <a href="#anna" aria-controls="anna" role="tab" data-toggle="tab">
                              <div class="img-wrap"><img class="img-circle" src="https://s3.amazonaws.com/uifaces/faces/twitter/annapickard/128.jpg" /></div>
                              <span class="quote"><i class="fa fa-quote-left"></i></span>
                          </a>
                      </li>
                      <li role="presentation" class="">
                          <a href="#wafer" aria-controls="wafer" role="tab" data-toggle="tab">
                              <div class="img-wrap"><img class="img-circle" src="https://s3.amazonaws.com/uifaces/faces/twitter/waferbaby/128.jpg" /></div>
                              <span class="quote"><i class="fa fa-quote-left"></i></span>
                          </a>
                      </li>
                      <li role="presentation" class="">
                          <a href="#daksh" aria-controls="daksh" role="tab" data-toggle="tab">
                              <div class="img-wrap"><img class="img-circle" src="https://s3.amazonaws.com/uifaces/faces/twitter/dakshbhagya/128.jpg" /></div>
                              <span class="quote"><i class="fa fa-quote-left"></i></span>
                          </a>
                      </li>
                  </ul>
              </div>
          </div>
        </div>
     </div>

      
    <section></section>

     <div class="feed-user-modal"> <!-- this is the entire modal form, including the background -->
    <div class="feed-user-modal-container"> <!-- this is the container wrapper -->
      <ul class="feed-switcher">
        <li><a href="#0">התחבר</a></li>
        <li><a href="#0">הרשמה</a></li>
      </ul>

      <div id="feed-login"> <!-- log in form -->
        <form class="feed-form" method="POST" action="<%=request.getScheme().toString() %>://<%=request.getServerName().toString() %>:<%=request.getServerPort() %><%=request.getContextPath() %>/Login">
          <p class="fieldset">
            <label class="image-replace feed-email" for="signin-email">E-mail</label>
            <input class="full-width has-padding has-border" id="signin-email" type="text" name="Username" placeholder="שם משתמש">
            <span class="feed-error-message">Error message here!</span>
          </p>

          <p class="fieldset">
            <label class="image-replace feed-password" for="signin-password">Password</label>
            <input class="full-width has-padding has-border" id="signin-password" name="UserPass" type="text"  placeholder="סיסמה">
            <a href="#0" class="hide-password">Hide</a>
            
            <span class="feed-error-message">Error message here!</span>
          </p>

          <p class="fieldset">
              
            <p class="text-danger">${loginError}</p>
            <input type="checkbox" id="remember-me" checked>
            <label for="remember-me">זכור אותי</label>
          </p>

          <p class="fieldset">
            <input class="full-width" type="submit" value="התחבר">
          </p>
        </form>
        
        <p class="feed-form-bottom-message"><a href="#0">שחכת סיסמה?</a></p>
        <!-- <a href="#0" class="feed-close-form">Close</a> -->
      </div> <!-- feed-login -->

      <div id="feed-signup"> <!-- sign up form -->
        <form class="feed-form" method="POST" action="<%=request.getScheme().toString() %>://<%=request.getServerName().toString() %>:<%=request.getServerPort() %><%=request.getContextPath() %>/CustomerRegistrationServlet">
          <div class="clearfix">
            <p class="fieldset pull-right" style="margin: 0; width: calc(100% / 2 - 5px)">
              <label class="image-replace feed-username" for="signup-firstname">שם פרטי</label>
              <input class="full-width has-padding has-border" id="signup-firstname" type="text" placeholder="שם פרטי">
              <span class="feed-error-message">Error message here!</span>
            </p>

            <p class="fieldset pull-left" style="margin: 0;   width: calc(100% / 2 - 5px)">
              <label class="image-replace feed-username" for="signup-lastname">שם משפחה</label>
              <input class="full-width has-padding has-border" id="signup-lastname" type="text" placeholder="שם משפחה">
              <span class="feed-error-message">Error message here!</span>
            </p>
          </div>
          

          <p class="fieldset">
            <label class="image-replace feed-username" for="signup-username">שם משתמש</label>
            <input class="full-width has-padding has-border" id="signup-username" type="text" placeholder="שם משתמש">
            <span class="feed-error-message">Error message here!</span>
          </p>

          <p class="fieldset">
            <label class="image-replace feed-email" for="signup-email">E-mail</label>
            <input class="full-width has-padding has-border" id="signup-email" type="email" placeholder="כתובת דוא״ל">
            <span class="feed-error-message">Error message here!</span>
          </p>

          <p class="fieldset">
            <label class="image-replace feed-password" for="signup-password">Password</label>
            <input class="full-width has-padding has-border" id="signup-password" type="text"  placeholder="סיסמה">
            <a href="#0" class="hide-password">Hide</a>
            <span class="feed-error-message">Error message here!</span>
          </p>
          
          <div class="clearfix">
            <p class="fieldset pull-right" style="margin: 0; width: calc(55% - 5px)">
              <label class="image-replace feed-phone" for="signup-phone">מספר טלפון</label>
              <input class="full-width has-padding has-border" id="signup-phone" type="text"  placeholder="מספר טלפון">
              <span class="feed-error-message">Error message here!</span>
            </p>

             <p class="fieldset pull-left" style="margin: 0; width: calc(45% - 5px)">
                <label class="image-replace feed-birth" for="signup-birth">תאריך לידה</label>
                <input class="full-width has-padding has-border" id="signup-birth" type="text"  placeholder="תאריך לידה dd/mm/yyyy">
                <span class="feed-error-message">Error message here!</span>
              </p>
          </div>
          

          <p class="fieldset">
            <label class="image-replace feed-address" for="signup-address">כתובת</label>
            <input class="full-width has-padding has-border" id="signup-address" type="text"  placeholder="כתובת">
            <span class="feed-error-message">Error message here!</span>
          </p>

         

          <p class="fieldset hidden">
            <input type="checkbox" id="accept-terms">
            <label for="accept-terms">אני מסכים <a href="#0">לתנאי השימוש</a></label>
          </p>

          <p class="fieldset">
            <input class="full-width has-padding" type="submit" value="צור חשבון">
          </p>
        </form>

        <!-- <a href="#0" class="feed-close-form">Close</a> -->
      </div> <!-- feed-signup -->

      <div id="feed-reset-password"> <!-- reset password form -->
        <p class="feed-form-message">Lost your password? Please enter your email address. You will receive a link to create a new password.</p>

        <form class="feed-form">
          <p class="fieldset">
            <label class="image-replace feed-email" for="reset-email">E-mail</label>
            <input class="full-width has-padding has-border" id="reset-email" type="email" placeholder="E-mail">
            <span class="feed-error-message">Error message here!</span>
          </p>

          <p class="fieldset">
            <input class="full-width has-padding" type="submit" value="Reset password">
          </p>
        </form>

        <p class="feed-form-bottom-message"><a href="#0">Back to log-in</a></p>
      </div> <!-- feed-reset-password -->
      <a href="#0" class="feed-close-form">Close</a>
    </div> <!-- feed-user-modal-container -->
  </div> <!-- feed-user-modal -->

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