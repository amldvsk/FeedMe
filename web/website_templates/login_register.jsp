<jsp:directive.page contentType="text/html;charset=UTF-8"/>
 <section></section>

     <div class="feed-user-modal"> <!-- this is the entire modal form, including the background -->
    <div class="feed-user-modal-container"> <!-- this is the container wrapper -->
      <ul class="feed-switcher">
        <li><a href="#0">התחבר</a></li>
        <li><a href="#0">הרשמה</a></li>
      </ul>

      <div id="feed-login"> <!-- log in form -->
        <form class="feed-form" accept-charset="UTF-8" method="POST" action="${pageContext.request.contextPath}/Login">
          <p class="fieldset">
            <label class="image-replace feed-email" for="Username">E-mail</label>
            <input class="full-width has-padding has-border" id="Username" type="text" name="Username" placeholder="שם משתמש" required>
            <span class="feed-error-message">Error message here!</span>
          </p>

          <p class="fieldset">
            <label class="image-replace feed-password" for="UserPass">Password</label>
            <input class="full-width has-padding has-border" id="UserPass" name="UserPass" type="text"  placeholder="סיסמה" required>
            <a href="#0" class="hide-password">Hide</a>
            
            <span class="feed-error-message">Error message here!</span>
          </p>

          <p class="fieldset">
              
            <p class="text-danger">${loginError}</p>
            <input type="checkbox" id="remember-me" checked>
            <label for="remember-me">זכור אותי</label>
          </p>

          <p class="fieldset">
            <input class="full-width" type="submit" value="התחבר">
          </p>
        </form>
        
        <p class="feed-form-bottom-message"><a href="#0">שחכת סיסמה?</a></p>
        <!-- <a href="#0" class="feed-close-form">Close</a> -->
      </div> <!-- feed-login -->

      <div id="feed-signup"> <!-- sign up form -->
        <form class="feed-form" method="POST" accept-charset="UTF-8" action="${pageContext.request.contextPath}/registration">
          <div class="clearfix">
            <p class="fieldset pull-right" style="margin: 0; width: calc(100% / 2 - 5px)">
              <label class="image-replace feed-username" for="firstName">שם פרטי</label>
              <input class="full-width has-padding has-border" name="firstName" id="firstName" type="text" placeholder="שם פרטי" required>
              <span class="feed-error-message">Error message here!</span>
            </p>

            <p class="fieldset pull-left" style="margin: 0;   width: calc(100% / 2 - 5px)">
              <label class="image-replace feed-username" for="lastName">שם משפחה</label>
              <input class="full-width has-padding has-border" name="lastName" id="lastName" type="text" placeholder="שם משפחה" required>
              <span class="feed-error-message">Error message here!</span>
            </p>
          </div>
          

          <p class="fieldset">
            <label class="image-replace feed-username" for="userName">שם משתמש</label>
            <input class="full-width has-padding has-border" name="userName" id="userName" type="text" placeholder="שם משתמש" required>
            <span class="feed-error-message">Error message here!</span>
          </p>

          <p class="fieldset">
            <label class="image-replace feed-email" for="email">E-mail</label>
            <input class="full-width has-padding has-border" name="email" id="email" type="email" placeholder="כתובת דוא״ל" required>
            <span class="feed-error-message">Error message here!</span>
          </p>

          <p class="fieldset">
            <label class="image-replace feed-password" for="pw">Password</label>
            <input class="full-width has-padding has-border" name="pw" id="pw" type="text"  placeholder="סיסמה" required>
            <a href="#0" class="hide-password">Hide</a>
            <span class="feed-error-message">Error message here!</span>
          </p>
          
          <div class="clearfix">
            <p class="fieldset pull-right" style="  margin: 0; width: calc(55% - 5px)">
              <label class="image-replace feed-phone" for="phone">מספר טלפון</label>
              <input class="full-width has-padding has-border" name="phone" id="phone" type="text"  placeholder="מספר טלפון" required>
              <span class="feed-error-message">Error message here!</span>
            </p>

             <p class="fieldset pull-left" style="margin: 0; width: calc(45% - 5px)">
                <label class="image-replace feed-birth" for="bday">תאריך לידה</label>
                <input style="padding: 8px;" class="full-width has-padding has-border" name="bday" id="bday" type="date"  placeholder="תאריך לידה dd/mm/yyyy" required>
                <span class="feed-error-message">Error message here!</span>
              </p>
          </div>
          

<!--          <p class="fieldset">
            <label class="image-replace feed-address" for="signup-address">כתובת</label>
            <input class="full-width has-padding has-border" name="address" id="signup-address" type="text"  placeholder="כתובת">
            <span class="feed-error-message">Error message here!</span>
          </p>-->

        <p class="fieldset">
            <label class="image-replace feed-phone" for="address">כתובת מגורים</label>
            <input class="full-width has-padding has-border" name="address" id="address" type="text"  placeholder="כתובת מגורים" required>
            <span class="feed-error-message">Error message here!</span>
        </p>

          <div class="clearfix">
            <p class="fieldset pull-right" style="margin: 0; width: calc(60% - 5px)">
              <label class="image-replace feed-phone" for="city">עיר</label>
              <input class="full-width has-padding has-border" name="city" id="city" type="text"  placeholder="עיר" required>
              <span class="feed-error-message">Error message here!</span>
            </p>

             <p class="fieldset pull-right" style="margin: 0px 5px; width: calc(20% - 5px)">
                <label class="image-replace feed-birth" for="street_num">מספר בית</label>
                <input class="full-width has-padding has-border" name="street_num" id="street_num" type="text"  placeholder="בית" required>
                <span class="feed-error-message">Error message here!</span>
              </p>
              
              <p class="fieldset pull-right" style="margin: 0; width: calc(20% - 5px)">
                <label class="image-replace feed-birth" for="home_num">מספר דירה</label>
                <input class="full-width has-padding has-border" name="home_num" id="home_num" type="text"  placeholder="דירה" required>
                <span class="feed-error-message">Error message here!</span>
              </p>
          </div>

         

          <p class="fieldset hidden">
            <input type="checkbox" id="accept-terms" required>
            <label for="accept-terms">אני מסכים <a href="#0">לתנאי השימוש</a></label>
          </p>

          <p class="fieldset">
              <input type="hidden" value="0" name="role"/>
            <input class="full-width has-padding" type="submit" value="צור חשבון">
          </p>
        </form>

        <!-- <a href="#0" class="feed-close-form">Close</a> -->
      </div> <!-- feed-signup -->

      <div id="feed-reset-password"> <!-- reset password form -->
        <p class="feed-form-message">Lost your password? Please enter your email address. You will receive a link to create a new password.</p>

        <form class="feed-form">
          <p class="fieldset">
            <label class="image-replace feed-email" for="reset-email">E-mail</label>
            <input class="full-width has-padding has-border" id="reset-email" type="email" placeholder="E-mail">
            <span class="feed-error-message">Error message here!</span>
          </p>

          <p class="fieldset">
            <input class="full-width has-padding" type="submit" value="Reset password">
          </p>
        </form>

        <p class="feed-form-bottom-message"><a href="#0">Back to log-in</a></p>
      </div> <!-- feed-reset-password -->
      <a href="#0" class="feed-close-form">Close</a>
    </div> <!-- feed-user-modal-container -->
  </div> <!-- feed-user-modal -->