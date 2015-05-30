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
                              <th>תמונת המוצר</th>
                              <th>שם המוצר</th>
                              <th>תיאור המוצר</th>
                              <th>מחיר</th>
                            </tr>
                          </thead>
                          <tbody>
                              <c:forEach var="menu"  items="${menus}">
                                  <c:forEach var="item"  items="${menu.value}">
                                    <tr>
                                        <th scope="row">${item.getItemID()}</th>
                                        <td><img src="${pageContext.request.contextPath}/assets/Uploads/${item.getItemImagePath()}" width="80" height="80" class="img-rounded" /></td>
                                        <td>${item.getItemName()}</td>
                                        <td>${item.getItemDescription()}</td>
                                        <td>${item.getItemPrice()}</td>
                                      </tr>
                                  </c:forEach>
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