<jsp:directive.page contentType="text/html;charset=UTF-8"/>
<%@include file='../website_templates/head.jsp'%>
<%@include file='../website_templates/navigation.jsp'%>
<c:forEach var="i" begin="1" end="5">
<!--Item <c:out value="${i}"/><p>-->
</c:forEach>

    <header class="not-index">

      <div class="header-wrapper">
        <div class="container">
          <div class="header-content">
            <h1>שלום, <span>${requestScope.customer.getFullName()}</span></h1>
          </div>
        </div>
      </div>

    </header>

<div class="bottom-navigation">
  <div class="container">
    <ul id="myTab" class="nav nav-justified" role="tablist">
      <li role="presentation" class="active"><a href="#orders" id="home-tab" role="tab" data-toggle="tab" aria-controls="home" aria-expanded="true">הזמנות</a></li>
      <li role="presentation" class=""><a href="#sale" role="tab" id="profile-tab" data-toggle="tab" aria-controls="profile" aria-expanded="false">הטבות</a></li>
      <li role="presentation" class=""><a href="#profile_edit" role="tab" id="profile-tab" data-toggle="tab" aria-controls="profile" aria-expanded="false">פרופיל</a></li>
    </ul>
  </div>
</div>

<section>
  <div class="container">

      <div id="myTabContent" class="tab-content">
        <div role="tabpanel" class="tab-pane fade active in" id="orders" aria-labelledby="home-tab">
          <table class="table table-striped">
            <thead>
              <tr>
                <th>מספר הזמנה</th>
                <th>המסעדה</th>
                <th>פירוט</th>
                <th>סכום</th>
                <th>תאריך</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <th scope="row">1</th>
                <td>קמפאי</td>
                <td>פאד קפאו, קוקה קולה</td>
                <td>80 &#8362;</td>
                <td>22/04/15</td>
              </tr>
            </tbody>
          </table>
        </div>
        <div role="tabpanel" class="tab-pane fade" id="sale" aria-labelledby="profile-tab">
          <table class="table table-striped">
            <thead>
              <tr>
                <th>מספר הטבה</th>
                <th>המסעדה</th>
                <th>ההטבה</th>
                <th>תוקף</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <th scope="row">1</th>
                <td>קמפאי</td>
                <td>1+1 על כל התפריט</td>
                <td>22/04/15</td>
              </tr>
            </tbody>
          </table>
        </div>
        <div role="tabpanel" class="tab-pane fade" id="profile_edit" aria-labelledby="dropdown1-tab">
              <form class="feed-form">
            <div class="clearfix">
              <p class="fieldset pull-right" style="margin: 0; width: calc(100% / 2 - 5px)">
                <label class="image-replace feed-username" for="signup-firstname">שם פרטי</label>
                <input class="full-width has-padding has-border" id="signup-firstname" type="text" placeholder="שם פרטי" value="${requestScope.customer.getFirstName()}">
                <span class="feed-error-message">Error message here!</span>
              </p>

              <p class="fieldset pull-left" style="margin: 0;   width: calc(100% / 2 - 5px)">
                <label class="image-replace feed-username" for="signup-lastname">שם משפחה</label>
                <input class="full-width has-padding has-border" id="signup-lastname" type="text" placeholder="שם משפחה" value="${requestScope.customer.getLastName()}">
                <span class="feed-error-message">Error message here!</span>
              </p>
            </div>
            

            <p class="fieldset">
              <label class="image-replace feed-username" for="signup-username">שם משתמש</label>
              <input class="full-width has-padding has-border" id="signup-username" type="text" placeholder="שם משתמש" value="${requestScope.customer.getUserName()}">
              <span class="feed-error-message">Error message here!</span>
            </p>

            <p class="fieldset">
              <label class="image-replace feed-email" for="signup-email">E-mail</label>
              <input class="full-width has-padding has-border" id="signup-email" type="email" placeholder="כתובת דוא״ל" value="${requestScope.customer.getEmail()}">
              <span class="feed-error-message">Error message here!</span>
            </p>
            
            <div class="clearfix">
              <p class="fieldset pull-right" style="margin: 0; width: calc(55% - 5px)">
                <label class="image-replace feed-phone" for="signup-phone">מספר טלפון</label>
                <input class="full-width has-padding has-border" id="signup-phone" type="text"  placeholder="מספר טלפון" value="${requestScope.customer.getPhone()}">
                <span class="feed-error-message">Error message here!</span>
              </p>

               <p class="fieldset pull-left" style="margin: 0; width: calc(45% - 5px)">
                  <label class="image-replace feed-birth" for="signup-birth">תאריך לידה</label>
                  <input class="full-width has-padding has-border" id="signup-birth" type="text"  placeholder="תאריך לידה dd/mm/yyyy" value="${requestScope.customer.getbDay()}">
                  <span class="feed-error-message">Error message here!</span>
                </p>
            </div>
            

            <p class="fieldset">
              <label class="image-replace feed-address" for="signup-address">כתובת</label>
              <input class="full-width has-padding has-border" id="signup-address" type="text"  placeholder="כתובת" value="${requestScope.customer.getStreet()}, ${requestScope.customer.getHouseNum()}, ${requestScope.customer.getApartNum()}">
              <span class="feed-error-message">Error message here!</span>
            </p>

           

            <p class="fieldset hidden">
              <input type="checkbox" id="accept-terms">
              <label for="accept-terms">אני מסכים <a href="#0">לתנאי השימוש</a></label>
            </p>

            <p class="fieldset">
              <input class="full-width has-padding" type="submit" value="עדכן פרופיל">
            </p>
          </form>
        </div>
      </div>

  </div>  
</section>

      
<%@include file='../website_templates/login_register.jsp'%>
<%@include file='../website_templates/shopping_cart.jsp'%>
<%@include file='../website_templates/footer.jsp'%>
   