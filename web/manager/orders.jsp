<jsp:directive.page contentType="text/html;charset=UTF-8"/>
<%@include file='../manager_templates/head.jsp'%>
        <!-- Main Content -->
        <div class="main-content">
          <section class="white border hidden">
            
          </section>
          <section>
            <div class="container-fluid">


              <div class="row">
                <div class="col-md-12">
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
                              <c:forEach var="order"  items="${orders}">
                                <tr>
                                    <th scope="row">${order.getOrderId()}</th>
                                    <td>${order.getCustomerFullName()}</td>
                                    <td>${order.getCustomerAdress()}</td>
                                    <td>${order.getOrderDateAndTime()}</td>
                                    <td>${order.getStatus()}</td>
                                  </tr>
                             </c:forEach>
                          </tbody>
                        </table>
                      </div>
                    </div>
                  </div>
                </div>
              </div>

            </div>
          </section>

         

        </div>

     <%@include file='../manager_templates/footer.jsp'%>