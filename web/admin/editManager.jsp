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
                          <form id="make_manager" method="POST" accept-charset="UTF-8" action="${pageContext.request.contextPath}/registration">
                            <div class="form-group clearfix">
                                <div class="col-md-6">
                                    <label for="firstName">שם פרטי</label>
                                    <input type="text" class="form-control" name="firstName" id="firstName" placeholder="שם פרטי" required>
                                 </div>
                                <div class="col-md-6">
                                     <label for="lastName">שם משפחה</label>
                              <input type="text" class="form-control" name="lastName" id="lastName" placeholder="שם משפחה" required>
                                 </div>
                              
                            </div>
                            <div class="form-group clearfix">
                                <div class="col-md-6">
                                    <label for="userName">שם משתמש</label>
                                    <input type="text" class="form-control" name="userName" id="userName" placeholder="שם משתמש" required>
                                 </div>
                                <div class="col-md-6">
                                     <label for="pw">סיסמה</label>
                              <input type="password" class="form-control" name="pw" id="pw" placeholder="סיסמה" required>
                                 </div>
                              
                            </div>
                            <div class="form-group clearfix">
                                <div class="col-md-6">
                                    <label for="phone">טלפון</label>
                                    <input type="text" class="form-control" name="phone" id="phone" placeholder="טלפון" required>
                                 </div>
                                <div class="col-md-6">
                                     <label for="email">דואר אלקטרוני</label>
                              <input type="email" class="form-control" name="email" id="email" placeholder="דואר אלקטרוני" required>
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