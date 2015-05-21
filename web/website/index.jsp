
    <jsp:directive.page contentType="text/html;charset=UTF-8"/>
     <%@include file='../website_templates/head.jsp'%>
    <%@include file='../website_templates/navigation.jsp'%>
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
                    <c:forEach var="citie" items="${cities}">
                        <option value="${citie}">${citie}</option>
                     </c:forEach>
                  </select>
                </div>
                <div class="form-group">
                  <select class="selectpicker" data-width="100%">
                    <option>מה תרצה לאכול ?</option>
                    <c:forEach var="type" items="${category}">
                        <option value="${type.value}">${type.key}</option>
                     </c:forEach>
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
            <c:forEach var="rest" items="${restaurants}">
                <li class="rest">
                  <div class="rest-logo">
                    <img src="${pageContext.request.contextPath}/assets/Uploads/${rest.getLogo()}" alt="placeholder+image">
                  </div>
                  <div class="rest-caption">
                    <h4>${rest.getName()}</h4>
                    <p><small><i class="fa fa-map-marker"></i> ${rest.getStreet()} ${rest.getStreetNum()}, ${rest.getCity()}</small></p>
                    <div class="review hidden">
                      <ul class="list-inline">
                          <li><i class="fa fa-star"></i></li>
                          <li><i class="fa fa-star"></i></li>
                          <li><i class="fa fa-star"></i></li>
                          <li><i class="fa fa-star"></i></li>
                          <li><i class="fa fa-star"></i></li>
                          <li>24 ביקורות</li>
                        </ul>
                    </div> 
                    <a href="${pageContext.request.contextPath}/resturent?res_id=${rest.getDbid()}" class="btn btn-green btn-blcok">הזמן עכשיו</a> 
                  </div>
                </li>
             </c:forEach>
            
<!--            <li class="rest">
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
            </li>-->
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

      
   <%@include file='../website_templates/login_register.jsp'%>
   <%@include file='../website_templates/shopping_cart.jsp'%>
<%@include file='../website_templates/footer.jsp'%>
   