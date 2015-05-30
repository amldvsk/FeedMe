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
                    <div class="panel-heading">תפריט</div>
                    <div class="panel-body">
                        <p></p>
                      <div class="table-responsive">
                      <!-- Table -->
                        <table class="table sort-table">
                          <thead>
                            <tr>
                              <th>#</th>
                              <th>לוגו</th>
                              <th>שם</th>
                              <th>רחוב</th>
                            </tr>
                          </thead>
                          <tbody>
                              <c:forEach var="rest"  items="${resturents}">
                                <tr>
                                    <th scope="row">${rest.getDbid()}</th>
                                    <td><img src="${pageContext.request.contextPath}/assets/Uploads/${rest.getLogo()}" class="img-rounded" width="60" height="60"></td>
                                    <td>${rest.getName()}</td>
                                    <td>${rest.getStreet()}</td>
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