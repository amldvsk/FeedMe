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
                    <div class="panel-heading">הוספת מסעדה</div>
                    <div class="panel-body">
                      <p>לורם איפסום דולור סיט אמט, קונסקטורר אדיפיסינג אלית לפרומי בלוף קינץ תתיח לרעח. לת צשחמי צש בליא, מנסוטו צמלח לביקו ננבי, צמוקו בלוקריה שיצמה ברורק. נולום ארווס סאפיאן - פוסיליס קוויס.</p>
                      <div class="table-responsive">
                          <form          enctype="multipart/form-data" method="POST" accept-charset="UTF-8" action="${pageContext.request.contextPath}/add-resturent">
                            <div class="form-group clearfix">
                                <div class="col-md-8">
                                    <label for="exampleInputEmail1">בעל המסעדה</label>
                                    <select name="managerId" id="select_admin" class="form-control">
                                        <option value="-1">בחר את הבעלים של המסעדה</option>
                                        <c:forEach var="type"  items="${managers}">
                                          <option value="${type.getDbId()}">${type.getFullName()}</option>
                                       </c:forEach>
                                    </select>
                                 </div>
                                <div class="col-md-4">
                                    <label for="exampleInputEmail1">יצירת בעלים חדשים</label>
                                    <a href="${pageContext.request.contextPath}/admin/editManager.jsp" class="btn btn-info form-control ">צור בעלים חדשים</a>
                                 </div>
                            </div>
                            <hr>

                            <div class="form-group clearfix">
                                <div class="col-md-8">
                                    <label for="exampleInputEmail1">קטגוריה</label>
                                    <select name="category" id="select_category" class="form-control">
                                        <option value="-1">בחר קטגוריה</option>
                                        <c:forEach var="type"  items="${categories}">
                                          <option value="${type.value}">${type.key}</option>
                                       </c:forEach>
                                    </select>
                                 </div>
                                <div class="col-md-4">
                                    <label for="exampleInputEmail1">יצירת קטגוריה</label>
                                    <a href="#" data-toggle="modal" data-target=".add-category-model" class="btn btn-warning form-control ">יצירת קטגוריה חדשה</a>
                                 </div>
                            </div>


                            <div class="hidden-fileds">
                                <hr>
                                <div class="form-group clearfix">
                                    <div class="col-md-8">
                                        <label for="exampleInputEmail1">שם המסעדה</label>
                                        <input type="text" class="form-control" name="newName" id="exampleInputEmail1" placeholder="שם המסעדה">
                                     </div>
                                    <div class="col-md-4">
                                         <label for="exampleInputPassword1">טלפון</label>
                                  <input type="text" class="form-control" name="phone" id="exampleInputPassword1" placeholder="טלפון">
                                     </div>

                                </div>
                                 <div class="form-group clearfix">
                                     <div class="col-md-4">
                                        <label for="exampleInputPassword1">כתובת</label>
                                        <input type="text" class="form-control" name="street" id="exampleInputPassword1" placeholder="כתובת">
                                     </div>
                                     <div class="col-md-4">
                                        <label for="exampleInputPassword1">מספר</label>
                                        <input type="text" class="form-control" name="streetNum" id="exampleInputPassword1" placeholder="מספר">
                                     </div>
                                     <div class="col-md-4">
                                        <label for="exampleInputPassword1">עיר</label>
                                        <input type="text" class="form-control" name="city" id="exampleInputPassword1" placeholder="עיר">
                                     </div>
                                </div>
                                  <div class="form-group clearfix">
                                      <div class="col-md-4">
                                          <label for="exampleInputPassword1">מחיר משלוח</label>
                                          <input type="text" class="form-control" name="deliveryPrice" id="exampleInputPassword1" placeholder="מחיר משלוח">
                                      </div>
                                      <div class="col-md-4">
                                          <label for="exampleInputPassword1">מינימום הזמנה</label>
                                          <input type="text" class="form-control" name="minOrder" id="exampleInputPassword1" placeholder="מינימום הזמנה">
                                      </div>
                                      <div class="col-md-4">
                                          <label for="exampleInputPassword1">זמן משלוח משוער</label>
                                          <input type="text" class="form-control" name="estimatedTimeDel" id="exampleInputPassword1" placeholder="זמן משלוח משוער">
                                      </div>
                                  </div>
                                <div class="form-group">
                                  <label for="exampleInputFile">לוגו</label>
                                  <input type="file" name="logo" id="exampleInputFile">
                                </div>
                                <input type="hidden" value="1" name="action">
                                <button type="submit" class="btn btn-success">הוסף מסעדה</button>
                            </div>
                          </form>
                      
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </section>

         

        </div>
        
    <div class="modal fade add-category-model" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
       <div class="modal-dialog">
         <div class="modal-content">

            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal" aria-label="Close" style="float:left" ><span aria-hidden="true">×</span></button>
              <h4 class="modal-title" id="mySmallModalLabel">הוספת קטגוריה<a class="anchorjs-link" href="#mySmallModalLabel"><span class="anchorjs-icon"></span></a></h4>
            </div>
            <form class="form" action="${pageContext.request.contextPath}/add-resturent-category" method="POST" id="addCategoryForm">
              <div class="modal-body">
                  <div class="form-group">
                      <input type="text" name="categoryName"  class="form-control" id="inputEmail3" placeholder="פיצה, סושי, סטקייה">
                  </div>
              </div>
              <div class="modal-footer">
                <button type="submit" class="btn btn-primary">צור קטגוריה</button>
              </div>
            </form>
          </div>
       </div>
     </div>
      
  <%@include file='../admin_templates/footer.jsp'%>