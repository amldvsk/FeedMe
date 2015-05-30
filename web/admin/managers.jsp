<jsp:directive.page contentType="text/html;charset=UTF-8"/>
<%@include file='../admin_templates/head.jsp'%>


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
                    <div class="panel-heading">מנהלי מסעדות</div>
                    <div class="panel-body">
                      <p>לורם איפסום דולור סיט אמט, קונסקטורר אדיפיסינג אלית לפרומי בלוף קינץ תתיח לרעח. לת צשחמי צש בליא, מנסוטו צמלח לביקו ננבי, צמוקו בלוקריה שיצמה ברורק. נולום ארווס סאפיאן - פוסיליס קוויס.</p>
                      <div class="table-responsive">
                      <!-- Table -->
                        <table class="table sort-table">
                          <thead>
                            <tr>
                              <th>#</th>
                              <th>שם</th>
                              <th>שם משתמש</th>
                              <th>טלפון</th>
                              <th>אימייל</th>
                            </tr>
                          </thead>
                          <tbody>
                            <c:forEach var="manager"  items="${managers}">
                                <tr>
                                    <th scope="row">${manager.getDbId()}</th>
                                    <td>${manager.getFullName()}</td>
                                    <td>${manager.getUserName()}</td>
                                    <td>${manager.getPhone()}</td>
                                    <td>${manager.getEmail()}</td>
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

      
  <%@include file='../admin_templates/footer.jsp'%>