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
                    <div class="panel-heading">הוספת מנהל מסעדה</div>
                    <div class="panel-body">
                      <p>לורם איפסום דולור סיט אמט, קונסקטורר אדיפיסינג אלית לפרומי בלוף קינץ תתיח לרעח. לת צשחמי צש בליא, מנסוטו צמלח לביקו ננבי, צמוקו בלוקריה שיצמה ברורק. נולום ארווס סאפיאן - פוסיליס קוויס.</p>
                      <div class="table-responsive">
                          <form method="POST" accept-charset="UTF-8" action="${pageContext.request.contextPath}/registration">
                            <div class="form-group clearfix">
                                <div class="col-md-6">
                                    <label for="exampleInputEmail1">שם פרטי</label>
                                    <input type="text" class="form-control" name="firstName" id="exampleInputEmail1" placeholder="שם פרטי">
                                 </div>
                                <div class="col-md-6">
                                     <label for="exampleInputPassword1">שם משפחה</label>
                              <input type="text" class="form-control" name="lastName" id="exampleInputPassword1" placeholder="שם משפחה">
                                 </div>
                              
                            </div>
                            <div class="form-group clearfix">
                                <div class="col-md-6">
                                    <label for="exampleInputEmail1">שם משתמש</label>
                                    <input type="text" class="form-control" name="userName" id="exampleInputEmail1" placeholder="שם משתמש">
                                 </div>
                                <div class="col-md-6">
                                     <label for="exampleInputPassword1">סיסמה</label>
                              <input type="password" class="form-control" name="pw" id="exampleInputPassword1" placeholder="סיסמה">
                                 </div>
                              
                            </div>
                            <div class="form-group clearfix">
                                <div class="col-md-6">
                                    <label for="exampleInputEmail1">טלפון</label>
                                    <input type="text" class="form-control" name="phone" id="exampleInputEmail1" placeholder="טלפון">
                                 </div>
                                <div class="col-md-6">
                                     <label for="exampleInputPassword1">דואר אלקטרוני</label>
                              <input type="email" class="form-control" name="email" id="exampleInputPassword1" placeholder="דואר אלקטרוני">
                                 </div>
                              
                            </div>
                              <input type="hidden" name="role" value="1">
                            <button type="submit" class="btn btn-success">הוסף מנהל</button>
                          </form>
                      
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </section>

         

        </div>

      
  <%@include file='../admin_templates/footer.jsp'%>