<jsp:directive.page contentType="text/html;charset=UTF-8"/>
<%@include file='../manager_templates/head.jsp'%>


        <!-- Main Content -->
        <div class="main-content">
          <section class="white border">
            <div class="container-fluid">
              
              <div class="row">
                  <div class="col-md-1"></div>
                  <div class="col-md-4" >
                      <h4 style="color:#444;">כמות הזמנות יומית</h4>
                      <canvas id="orders_num" width="200" height="200"></canvas>
                  </div>
                  <div class="col-md-2"></div>
                  <div class="col-md-4" >
                      <h4 style="color:#444;">סכום הזמנות יומי</h4>
                      <canvas id="orders_sum" width="200" height="200"></canvas>
                  </div>
                  <div class="col-md-1"></div>
              </div>
            </div>
          </section>
          <section>
            <div class="container-fluid">
              
              
                <div class="row" >
                    <div class="col-md-12">
                    <div class="panel panel-default">
                      <!-- Default panel contents -->
                      <div class="panel-heading">דוח הזמנות</div>
                      <div class="panel-body">
                        <p>לורם איפסום דולור סיט אמט, קונסקטורר אדיפיסינג אלית לפרומי בלוף קינץ תתיח לרעח. לת צשחמי צש בליא, מנסוטו צמלח לביקו ננבי, צמוקו בלוקריה שיצמה ברורק. נולום ארווס סאפיאן - פוסיליס קוויס.</p>
                        <div class="table-responsive">
                            <c:forEach var="data"  items="${requestScope.dateAndNumOfOrders}">
                              <!--<h1>${data.key} ${data.value}</h1>-->
                           </c:forEach>
                              <c:forEach var="data2"  items="${requestScope.dateAndPrice}">
                              <!--<h1>${data2.key} ${data2.value}</h1>-->
                           </c:forEach>
                              <h1>${requestScope.dateAndPrice}</h1>
                              <h1>${requestScope.dateAndNumOfOrders}</h1>
                        </div>
                      </div>
                    </div>
                </div>
                </div>
            </div>
          </section>

         

        </div>

      
  <%@include file='../manager_templates/footer.jsp'%>