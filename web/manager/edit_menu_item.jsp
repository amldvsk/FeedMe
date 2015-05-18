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
                    <div class="panel-heading">הוספת מסעדה</div>
                    <div class="panel-body">
                      <p>לורם איפסום דולור סיט אמט, קונסקטורר אדיפיסינג אלית לפרומי בלוף קינץ תתיח לרעח. לת צשחמי צש בליא, מנסוטו צמלח לביקו ננבי, צמוקו בלוקריה שיצמה ברורק. נולום ארווס סאפיאן - פוסיליס קוויס.</p>
                      <div class="table-responsive">
                          <form          enctype="multipart/form-data" method="POST" accept-charset="UTF-8" action="${pageContext.request.contextPath}/menu-item-management">

                            <div class="form-group clearfix">
                                <div class="col-md-8">
                                    <label for="exampleInputEmail1">קטגוריה</label>
                                    <select name="itemMenuCatId" id="select_category" class="form-control">
                                        <option value="-1">בחר קטגוריה</option>
                                        <c:forEach var="category"  items="${categories}">
                                          <option value="${category.key}">${category.value}</option>
                                       </c:forEach>
                                    </select>
                                 </div>
                            </div>


                            <div class="hidden-fileds">
                                <hr>
                                <div class="form-group clearfix">
                                    <div class="col-md-8">
                                        <label for="exampleInputEmail1">שם הפריט</label>
                                        <input type="text" class="form-control" name="itemName" id="exampleInputEmail1" placeholder="שם הפריט">
                                     </div>
                                    <div class="col-md-4">
                                         <label for="exampleInputPassword1">מחיר המוצר</label>
                                  <input type="text" class="form-control" name="itemPrice" id="exampleInputPassword1" placeholder="מחיר המוצר">
                                     </div>

                                </div>
                                 <div class="form-group clearfix">
                                     <div class="col-md-12">
                                        <label for="exampleInputPassword1">תיאור המוצר</label>
                                        <input type="text" class="form-control" name="itemDescrip" id="exampleInputPassword1" placeholder="תיאור המוצר">
                                     </div>
                                </div>
                                <div class="form-group">
                                  <label for="exampleInputFile">תמונת המוצר</label>
                                  <input type="file" name="logo" id="exampleInputFile">
                                </div>
                                <input type="hidden" value="1" name="action">
                                <input type="hidden" value="${requestScope.restaurant.getDbid()}" name="itemRestId">
                                <button type="submit" class="btn btn-success">הוסף מוצר</button>
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

      
  <%@include file='../manager_templates/footer.jsp'%>