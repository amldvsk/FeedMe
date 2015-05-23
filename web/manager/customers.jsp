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
                              <th>כתובת</th>
                              <th>טלפון</th>
                              <th>אימייל</th>
                              <th>תאריך לידה</th>
                            </tr>
                          </thead>
                          <tbody>
                              <c:forEach var="customer"  items="${customers}">
                                <tr>
                                    <th scope="row">${customer.getDbId()}</th>
                                    <td>${customer.getFullName()}</td>
                                    <td>${customer.getStreet()} ${customer.getHouseNum()} ${customer.getApartNum()}, ${customer.getCity()}</td>
                                    <td>${customer.getPhone()}</td>
                                    <td>${customer.getEmail()}</td>
                                    <td>${customer.getbDay()}</td>
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