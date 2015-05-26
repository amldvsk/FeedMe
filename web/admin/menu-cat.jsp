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
                    <div class="panel-heading">קטגורית תפריט</div>
                    <div class="panel-body">
                      <p>הוספת קטגורית תפריט</p>
                      <form class="form" action="${pageContext.request.contextPath}/admin/menu-category" method="POST" id="addMenuCategoryForm">
                             <div class="form-group">
                                 <div class="col-md-10">
                                    <input type="text" requierd name="MenuCaregoryName"  class="form-control" id="inputEmail3" placeholder="קינוח, מנות ראשונות...">
                                 </div>
                            </div>
                            
                              <button type="submit" class="btn btn-primary">צור קטגוריה</button>
                            
                          </form>
                            <hr/>
                      <div class="table-responsive">
                      <!-- Table -->
                        <table class="table sort-table">
                          <thead>
                            <tr>
                              <th>#</th>
                              <th>קטגוריה</th>
                            </tr>
                          </thead>
                          <tbody>
                            <c:forEach var="categorie"  items="${categories}">
                                <tr>
                                    <th scope="row">${categorie.key}</th>
                                    <td>${categorie.value}</td>
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