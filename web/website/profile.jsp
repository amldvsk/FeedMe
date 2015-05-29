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
                <c:forEach var="order"  items="${orders}">
                    <tr>
                        <th scope="row">${order.getOrderId()}</th>
                        <td>${order.getCustomerFullName()}</td>
                        <td>
                        <c:forEach var="item"  items="${order.getRestItemsMap()}">
                            ${item.value.getItemName()}, &nbsp;
                        </c:forEach>
                        </td>
                        <td>${order.calcSum()} &#8362;</td>
                        <td>${order.getOrderDateAndTime()}</td>
                      </tr>
                 </c:forEach>
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
              
        <form class="feed-form" id="user_update" method="POST" accept-charset="UTF-8" action="${pageContext.request.contextPath}/registration">
          <div class="clearfix">
            <p class="fieldset pull-right" style="margin: 0; width: calc(100% / 2 - 5px)">
              <label class=" feed-username" for="firstName">שם פרטי</label>
              <input class="full-width has-padding has-border" name="firstName" id="firstName" type="text" value="${requestScope.customer.getFirstName()}" placeholder="שם פרטי" required>
              <span class="feed-error-message">Error message here!</span>
            </p>

            <p class="fieldset pull-left" style="margin: 0;   width: calc(100% / 2 - 5px)">
              <label class=" feed-username" for="lastName">שם משפחה</label>
              <input class="full-width has-padding has-border" name="lastName" id="lastName" type="text" placeholder="שם משפחה" value="${requestScope.customer.getLastName()}" required>
              <span class="feed-error-message">Error message here!</span>
            </p>
          </div>
          

          <p class="fieldset">
            <label class=" feed-username" for="userName">שם משתמש</label>
            <input class="full-width has-padding has-border" name="userName" id="userName" type="text" placeholder="שם משתמש" value="${requestScope.customer.getUserName()}" required>
            <span class="feed-error-message">Error message here!</span>
          </p>

          <p class="fieldset">
            <label class=" feed-email" for="email">E-mail</label>
            <input class="full-width has-padding has-border" name="email" id="email" type="email" placeholder="כתובת דוא״ל" value="${requestScope.customer.getEmail()}" required>
            <span class="feed-error-message">Error message here!</span>
          </p>
          
          <div class="clearfix">
            <p class="fieldset pull-right" style="  margin: 0; width: calc(55% - 5px)">
              <label class=" feed-phone" for="phone">מספר טלפון</label>
              <input class="full-width has-padding has-border" name="phone" id="phone" type="text"  placeholder="מספר טלפון" value="${requestScope.customer.getPhone()}" required>
              <span class="feed-error-message">Error message here!</span>
            </p>
            
            <c:set var="now" value="<%=new java.util.Date()%>" />
             <p class="fieldset pull-left" style="margin: 0; width: calc(45% - 5px)">
                <label class=" feed-birth" for="bday">תאריך לידה</label>
                <input max='<fmt:formatDate pattern="yyyy-MM-dd" value="${now}" />' style="padding: 8px;" min="1940-01-01" class="full-width has-padding has-border" value="${requestScope.customer.getbDay()}" name="bday" id="bday" type="date"  placeholder="תאריך לידה dd/mm/yyyy" required>
                <span class="feed-error-message">Error message here!</span>
              </p>
          </div>
          

<!--          <p class="fieldset">
            <label class="image-replace feed-address" for="signup-address">כתובת</label>
            <input class="full-width has-padding has-border" name="address" id="signup-address" type="text"  placeholder="כתובת">
            <span class="feed-error-message">Error message here!</span>
          </p>-->

        <p class="fieldset">
            <label class=" feed-phone" for="address">כתובת מגורים</label>
            <input class="full-width has-padding has-border" name="address" id="address" type="text"  placeholder="כתובת מגורים" value="${requestScope.customer.getStreet()}" required>
            <span class="feed-error-message">Error message here!</span>
        </p>

          <div class="clearfix">
            <p class="fieldset pull-right" style="margin: 0; width: calc(60% - 5px)">
              <label class=" feed-phone" for="city">עיר</label>
              <!--<input class="full-width has-padding has-border" name="city" id="city" type="text"  placeholder="עיר" required>-->
                     <select class="full-width has-padding has-border" name="city" id="city" type="text"  placeholder="עיר" data-value="${requestScope.customer.getCity()}" required style="padding: 15px; border: 1px solid #d2d8d8; background-color: transparent; width: 100%; border-radius: 0.25em;">
                      <option value="אבו גוש">אבו גוש</option>
                      <option value="אבו סנאן">אבו סנאן</option>
                      <option value="אבטין">אבטין</option>
                      <option value="אבטליון">אבטליון</option>
                      <option value="אביאל">אביאל</option>
                      <option value="אביבים">אביבים</option>
                      <option value="אביגדור">אביגדור</option>
                      <option value="אביחיל">אביחיל</option>
                      <option value="אביטל">אביטל</option>
                      <option value="אביעזר">אביעזר</option>
                      <option value="אבירים">אבירים</option>
                      <option value="אבן יהודה">אבן יהודה</option>
                      <option value="אבן מנחם">אבן מנחם</option>
                      <option value="אבן ספיר">אבן ספיר</option>
                      <option value="אבן שמואל">אבן שמואל</option>
                      <option value="אבני איתן">אבני איתן</option>
                      <option value="אבני חפץ">אבני חפץ</option>
                      <option value="אבנת">אבנת</option>
                      <option value="אבשלום">אבשלום</option>
                      <option value="אדורה">אדורה</option>
                      <option value="אדירים">אדירים</option>
                      <option value="אדמית">אדמית</option>
                      <option value="אדרת">אדרת</option>
                      <option value="אודים">אודים</option>
                      <option value="אודם">אודם</option>
                      <option value="אוהד">אוהד</option>
                      <option value="אום אל-פחם">אום אל-פחם</option>
                      <option value="אום אל-קוטוף">אום אל-קוטוף</option>
                      <option value="אום בטין">אום בטין</option>
                      <option value="אומן">אומן</option>
                      <option value="אומץ">אומץ</option>
                      <option value="אופקים">אופקים</option>
                      <option value="אור הגנוז">אור הגנוז</option>
                      <option value="אור הנר">אור הנר</option>
                      <option value="אור יהודה">אור יהודה</option>
                      <option value="אור עקיבא">אור עקיבא</option>
                      <option value="אורה">אורה</option>
                      <option value="אורון">אורון</option>
                      <option value="אורות">אורות</option>
                      <option value="אורטל">אורטל</option>
                      <option value="אורים">אורים</option>
                      <option value="אורנים">אורנים</option>
                      <option value="אורנית">אורנית</option>
                      <option value="אושה">אושה</option>
                      <option value="אזור">אזור</option>
                      <option value="אחווה">אחווה</option>
                      <option value="אחוזם">אחוזם</option>
                      <option value="אחוזת ברק">אחוזת ברק</option>
                      <option value="אחיהוד">אחיהוד</option>
                      <option value="אחיטוב">אחיטוב</option>
                      <option value="אחיסמך">אחיסמך</option>
                      <option value="אחיעזר">אחיעזר</option>
                      <option value="אטרש (שבט)">אטרש (שבט)</option>
                      <option value="איבים">איבים</option>
                      <option value="אייל">אייל</option>
                      <option value="איילת השחר">איילת השחר</option>
                      <option value="אילון">אילון</option>
                      <option value="אילון תבור">אילון תבור</option>
                      <option value="אילות">אילות</option>
                      <option value="אילנייה">אילנייה</option>
                      <option value="אילת">אילת</option>
                      <option value="איתמר">איתמר</option>
                      <option value="איתן">איתן</option>
                      <option value="איתנים">איתנים</option>
                      <option value="אכסאל">אכסאל</option>
                      <option value="אל -עזי">אל -עזי</option>
                      <option value="אל -עריאן">אל -עריאן</option>
                      <option value="אל -רום">אל -רום</option>
                      <option value="אלומה">אלומה</option>
                      <option value="אלומות">אלומות</option>
                      <option value="אלון הגליל">אלון הגליל</option>
                      <option value="אלון מורה">אלון מורה</option>
                      <option value="אלון שבות">אלון שבות</option>
                      <option value="אלוני אבא">אלוני אבא</option>
                      <option value="אלוני הבשן">אלוני הבשן</option>
                      <option value="אלוני יצחק">אלוני יצחק</option>
                      <option value="אלונים">אלונים</option>
                      <option value="אלי-עד">אלי-עד</option>
                      <option value="אליכין">אליכין</option>
                      <option value="אליפז">אליפז</option>
                      <option value="אליפלט">אליפלט</option>
                      <option value="אליקים">אליקים</option>
                      <option value="אלישיב">אלישיב</option>
                      <option value="אלישמע">אלישמע</option>
                      <option value="אלמגור">אלמגור</option>
                      <option value="אלמוג">אלמוג</option>
                      <option value="אלעד">אלעד</option>
                      <option value="אלעזר">אלעזר</option>
                      <option value="אלפי מנשה">אלפי מנשה</option>
                      <option value="אלקוש">אלקוש</option>
                      <option value="אלקנה">אלקנה</option>
                      <option value="אמונים">אמונים</option>
                      <option value="אמירים">אמירים</option>
                      <option value="אמנון">אמנון</option>
                      <option value="אמציה">אמציה</option>
                      <option value="אניעם">אניעם</option>
                      <option value="אספר">אספר</option>
                      <option value="אעבלין">אעבלין</option>
                      <option value="אפיק">אפיק</option>
                      <option value="אפיקים">אפיקים</option>
                      <option value="אפק">אפק</option>
                      <option value="אפרתה">אפרתה</option>
                      <option value="ארבל">ארבל</option>
                      <option value="ארגמן">ארגמן</option>
                      <option value="ארז">ארז</option>
                      <option value="אריאל">אריאל</option>
                      <option value="ארסוף">ארסוף</option>
                      <option value="אשבול">אשבול</option>
                      <option value="אשבל">אשבל</option>
                      <option value="אשדוד">אשדוד</option>
                      <option value="אשדות יעקב (איחוד)">אשדות יעקב (איחוד)</option>
                      <option value="אשדות יעקב (מאוחד)">אשדות יעקב (מאוחד)</option>
                      <option value="אשחר">אשחר</option>
                      <option value="אשכולות">אשכולות</option>
                      <option value="אשל הנשיא">אשל הנשיא</option>
                      <option value="אשלים">אשלים</option>
                      <option value="אשקלון">אשקלון</option>
                      <option value="אשרת">אשרת</option>
                      <option value="אשתאול">אשתאול</option>
                      <option value="באקה-ג'ת">באקה-ג'ת</option>
                      <option value="באר אורה">באר אורה</option>
                      <option value="באר טוביה">באר טוביה</option>
                      <option value="באר יעקב">באר יעקב</option>
                      <option value="באר שבע">באר שבע</option>
                      <option value="בארות יצחק">בארות יצחק</option>
                      <option value="בארותיים">בארותיים</option>
                      <option value="בארי">בארי</option>
                      <option value="בוסתן הגליל">בוסתן הגליל</option>
                      <option value="בועיינה-נוג'ידאת">בועיינה-נוג'ידאת</option>
                      <option value="בוקעאתא">בוקעאתא</option>
                      <option value="בורגתה">בורגתה</option>
                      <option value="בחן">בחן</option>
                      <option value="בטחה">בטחה</option>
                      <option value="ביצרון">ביצרון</option>
                      <option value="ביר אל-מכסור">ביר אל-מכסור</option>
                      <option value="ביר הדאג'">ביר הדאג'</option>
                      <option value="בירייה">בירייה</option>
                      <option value="בית אורן">בית אורן</option>
                      <option value="בית אל">בית אל</option>
                      <option value="בית אלעזרי">בית אלעזרי</option>
                      <option value="בית אלפא">בית אלפא</option>
                      <option value="בית אריה">בית אריה</option>
                      <option value="בית ברל">בית ברל</option>
                      <option value="בית ג'ן">בית ג'ן</option>
                      <option value="בית גוברין">בית גוברין</option>
                      <option value="בית גמליאל">בית גמליאל</option>
                      <option value="בית דגן">בית דגן</option>
                      <option value="בית הגדי">בית הגדי</option>
                      <option value="בית הלוי">בית הלוי</option>
                      <option value="בית הלל">בית הלל</option>
                      <option value="בית העמק">בית העמק</option>
                      <option value="בית הערבה">בית הערבה</option>
                      <option value="בית השיטה">בית השיטה</option>
                      <option value="בית זיד">בית זיד</option>
                      <option value="בית זית">בית זית</option>
                      <option value="בית זרע">בית זרע</option>
                      <option value="בית חורון">בית חורון</option>
                      <option value="בית חירות">בית חירות</option>
                      <option value="בית חלקיה">בית חלקיה</option>
                      <option value="בית חנן">בית חנן</option>
                      <option value="בית חנניה">בית חנניה</option>
                      <option value="בית חשמונאי">בית חשמונאי</option>
                      <option value="בית יהושע">בית יהושע</option>
                      <option value="בית יוסף">בית יוסף</option>
                      <option value="בית ינאי">בית ינאי</option>
                      <option value="בית יצחק-שער חפר">בית יצחק-שער חפר</option>
                      <option value="בית לחם הגלילית">בית לחם הגלילית</option>
                      <option value="בית מאיר">בית מאיר</option>
                      <option value="בית נחמיה">בית נחמיה</option>
                      <option value="בית ניר">בית ניר</option>
                      <option value="בית נקופה">בית נקופה</option>
                      <option value="בית עובד">בית עובד</option>
                      <option value="בית עוזיאל">בית עוזיאל</option>
                      <option value="בית עזרא">בית עזרא</option>
                      <option value="בית עריף">בית עריף</option>
                      <option value="בית צבי">בית צבי</option>
                      <option value="בית קמה">בית קמה</option>
                      <option value="בית קשת">בית קשת</option>
                      <option value="בית רבן">בית רבן</option>
                      <option value="בית רימון">בית רימון</option>
                      <option value="בית שאן">בית שאן</option>
                      <option value="בית שמש">בית שמש</option>
                      <option value="בית שערים">בית שערים</option>
                      <option value="בית שקמה">בית שקמה</option>
                      <option value="ביתן אהרן">ביתן אהרן</option>
                      <option value="ביתר עילית">ביתר עילית</option>
                      <option value="בלפוריה">בלפוריה</option>
                      <option value="בן זכאי">בן זכאי</option>
                      <option value="בן עמי">בן עמי</option>
                      <option value="בן שמן">בן שמן</option>
                      <option value="בני ברק">בני ברק</option>
                      <option value="בני דרום">בני דרום</option>
                      <option value="בני דרור">בני דרור</option>
                      <option value="בני יהודה">בני יהודה</option>
                      <option value="בני עטרות">בני עטרות</option>
                      <option value="בני עי" ש"="">בני עי</option>
                      <option value="בני ציון">בני ציון</option>
                      <option value="בני ראם">בני ראם</option>
                      <option value="בניה">בניה</option>
                      <option value="בנימינה-גבעת עדה">בנימינה-גבעת עדה</option>
                      <option value="בסמ" ה"="">בסמ</option>
                      <option value="בסמת טבעון">בסמת טבעון</option>
                      <option value="בצרה">בצרה</option>
                      <option value="בצת">בצת</option>
                      <option value="בקוע">בקוע</option>
                      <option value="בקעות">בקעות</option>
                      <option value="בר גיורא">בר גיורא</option>
                      <option value="בר יוחאי">בר יוחאי</option>
                      <option value="ברור חיל">ברור חיל</option>
                      <option value="ברוש">ברוש</option>
                      <option value="ברכה">ברכה</option>
                      <option value="ברכיה">ברכיה</option>
                      <option value="ברעם">ברעם</option>
                      <option value="ברק">ברק</option>
                      <option value="ברקאי">ברקאי</option>
                      <option value="ברקן">ברקן</option>
                      <option value="ברקת">ברקת</option>
                      <option value="בת הדר">בת הדר</option>
                      <option value="בת חפר">בת חפר</option>
                      <option value="בת ים">בת ים</option>
                      <option value="בת עין">בת עין</option>
                      <option value="בת שלמה">בת שלמה</option>
                      <option value="בתי זיקוק - קישון">בתי זיקוק - קישון</option>
                      <option value="בתי ספר של מרחבים*">בתי ספר של מרחבים*</option>
                      <option value="ג'דיידה-מכר">ג'דיידה-מכר</option>
                      <option value="ג'ולס">ג'ולס</option>
                      <option value="ג'לג'וליה">ג'לג'וליה</option>
                      <option value="ג'סר א-זרקא">ג'סר א-זרקא</option>
                      <option value="ג'ש (גוש חלב)">ג'ש (גוש חלב)</option>
                      <option value="גאולי תימן">גאולי תימן</option>
                      <option value="גאולים">גאולים</option>
                      <option value="גאליה">גאליה</option>
                      <option value="גבולות">גבולות</option>
                      <option value="גבים">גבים</option>
                      <option value="גבע">גבע</option>
                      <option value="גבע בנימין">גבע בנימין</option>
                      <option value="גבע כרמל">גבע כרמל</option>
                      <option value="גבעולים">גבעולים</option>
                      <option value="גבעון החדשה">גבעון החדשה</option>
                      <option value="גבעות בר">גבעות בר</option>
                      <option value="גבעת אבני">גבעת אבני</option>
                      <option value="גבעת אלה">גבעת אלה</option>
                      <option value="גבעת ברנר">גבעת ברנר</option>
                      <option value="גבעת השלושה">גבעת השלושה</option>
                      <option value="גבעת זאב">גבעת זאב</option>
                      <option value="גבעת ח" ן"="">גבעת ח</option>
                      <option value="גבעת חביבה">גבעת חביבה</option>
                      <option value="גבעת חיים (איחוד)">גבעת חיים (איחוד)</option>
                      <option value="גבעת חיים (מאוחד)">גבעת חיים (מאוחד)</option>
                      <option value="גבעת יואב">גבעת יואב</option>
                      <option value="גבעת יערים">גבעת יערים</option>
                      <option value="גבעת ישעיהו">גבעת ישעיהו</option>
                      <option value="גבעת כ" ח"="">גבעת כ</option>
                      <option value="גבעת ניל" י"="">גבעת ניל</option>
                      <option value="גבעת עוז">גבעת עוז</option>
                      <option value="גבעת שמואל">גבעת שמואל</option>
                      <option value="גבעת שמש">גבעת שמש</option>
                      <option value="גבעת שפירא">גבעת שפירא</option>
                      <option value="גבעתי">גבעתי</option>
                      <option value="גבעתיים">גבעתיים</option>
                      <option value="גברעם">גברעם</option>
                      <option value="גבת">גבת</option>
                      <option value="גדות">גדות</option>
                      <option value="גדיש">גדיש</option>
                      <option value="גדעונה">גדעונה</option>
                      <option value="גדרה">גדרה</option>
                      <option value="גונן">גונן</option>
                      <option value="גורן">גורן</option>
                      <option value="גורנות הגליל">גורנות הגליל</option>
                      <option value="גזית">גזית</option>
                      <option value="גזר">גזר</option>
                      <option value="גיאה">גיאה</option>
                      <option value="גיבתון">גיבתון</option>
                      <option value="גיזו">גיזו</option>
                      <option value="גילון">גילון</option>
                      <option value="גילת">גילת</option>
                      <option value="גינוסר">גינוסר</option>
                      <option value="גיניגר">גיניגר</option>
                      <option value="גינתון">גינתון</option>
                      <option value="גיתה">גיתה</option>
                      <option value="גיתית">גיתית</option>
                      <option value="גלאון">גלאון</option>
                      <option value="גלגל">גלגל</option>
                      <option value="גליל ים">גליל ים</option>
                      <option value="גלעד (אבן יצחק)">גלעד (אבן יצחק)</option>
                      <option value="גמזו">גמזו</option>
                      <option value="גן הדרום">גן הדרום</option>
                      <option value="גן השומרון">גן השומרון</option>
                      <option value="גן חיים">גן חיים</option>
                      <option value="גן יאשיה">גן יאשיה</option>
                      <option value="גן יבנה">גן יבנה</option>
                      <option value="גן נר">גן נר</option>
                      <option value="גן שורק">גן שורק</option>
                      <option value="גן שלמה">גן שלמה</option>
                      <option value="גן שמואל">גן שמואל</option>
                      <option value="גנות">גנות</option>
                      <option value="גנות הדר">גנות הדר</option>
                      <option value="גני הדר">גני הדר</option>
                      <option value="גני יוחנן">גני יוחנן</option>
                      <option value="גני עם">גני עם</option>
                      <option value="גני תקווה">גני תקווה</option>
                      <option value="געש">געש</option>
                      <option value="געתון">געתון</option>
                      <option value="גפן">גפן</option>
                      <option value="גרופית">גרופית</option>
                      <option value="גשור">גשור</option>
                      <option value="גשר">גשר</option>
                      <option value="גשר הזיו">גשר הזיו</option>
                      <option value="גת (קיבוץ)">גת (קיבוץ)</option>
                      <option value="גת רימון">גת רימון</option>
                      <option value="דבורה">דבורה</option>
                      <option value="דבורייה">דבורייה</option>
                      <option value="דבירה">דבירה</option>
                      <option value="דברת">דברת</option>
                      <option value="דגניה א'">דגניה א'</option>
                      <option value="דגניה ב'">דגניה ב'</option>
                      <option value="דוב" ב"="">דוב</option>
                      <option value="דולב">דולב</option>
                      <option value="דור">דור</option>
                      <option value="דורות">דורות</option>
                      <option value="דחי">דחי</option>
                      <option value="דייר חנא">דייר חנא</option>
                      <option value="דייר ראפאת">דייר ראפאת</option>
                      <option value="דימונה">דימונה</option>
                      <option value="דישון">דישון</option>
                      <option value="דלייה">דלייה</option>
                      <option value="דלתון">דלתון</option>
                      <option value="דמיידה">דמיידה</option>
                      <option value="דן">דן</option>
                      <option value="דפנה">דפנה</option>
                      <option value="דקל">דקל</option>
                      <option value="דריג'את">דריג'את</option>
                      <option value="האון">האון</option>
                      <option value="הבונים">הבונים</option>
                      <option value="הגושרים">הגושרים</option>
                      <option value="הדר עם">הדר עם</option>
                      <option value="הוד השרון">הוד השרון</option>
                      <option value="הודיות">הודיות</option>
                      <option value="הודייה">הודייה</option>
                      <option value="הושעיה">הושעיה</option>
                      <option value="הזורע">הזורע</option>
                      <option value="הזורעים">הזורעים</option>
                      <option value="החותרים">החותרים</option>
                      <option value="היוגב">היוגב</option>
                      <option value="הילה">הילה</option>
                      <option value="המעפיל">המעפיל</option>
                      <option value="הסוללים">הסוללים</option>
                      <option value="העוגן">העוגן</option>
                      <option value="הר אדר">הר אדר</option>
                      <option value="הר גילה">הר גילה</option>
                      <option value="הר עמשא">הר עמשא</option>
                      <option value="הראל">הראל</option>
                      <option value="הרדוף">הרדוף</option>
                      <option value="הרצלייה">הרצלייה</option>
                      <option value="הררית">הררית</option>
                      <option value="ורד יריחו">ורד יריחו</option>
                      <option value="ורדון">ורדון</option>
                      <option value="זבדיאל">זבדיאל</option>
                      <option value="זוהר">זוהר</option>
                      <option value="זיקים">זיקים</option>
                      <option value="זיתן">זיתן</option>
                      <option value="זכרון יעקב">זכרון יעקב</option>
                      <option value="זכריה">זכריה</option>
                      <option value="זמר">זמר</option>
                      <option value="זמרת">זמרת</option>
                      <option value="זנוח">זנוח</option>
                      <option value="זרועה">זרועה</option>
                      <option value="זרזיר">זרזיר</option>
                      <option value="זרחיה">זרחיה</option>
                      <option value="ח'ואלד">ח'ואלד</option>
                      <option value="חבצלת השרון">חבצלת השרון</option>
                      <option value="חבר">חבר</option>
                      <option value="חגור">חגור</option>
                      <option value="חגי">חגי</option>
                      <option value="חגלה">חגלה</option>
                      <option value="חד-נס">חד-נס</option>
                      <option value="חדיד">חדיד</option>
                      <option value="חדרה">חדרה</option>
                      <option value="חולדה">חולדה</option>
                      <option value="חולון">חולון</option>
                      <option value="חולית">חולית</option>
                      <option value="חולתה">חולתה</option>
                      <option value="חוסן">חוסן</option>
                      <option value="חוסנייה">חוסנייה</option>
                      <option value="חופית">חופית</option>
                      <option value="חוקוק">חוקוק</option>
                      <option value="חורה">חורה</option>
                      <option value="חורפיש">חורפיש</option>
                      <option value="חורשים">חורשים</option>
                      <option value="חזון">חזון</option>
                      <option value="חיבת ציון">חיבת ציון</option>
                      <option value="חיננית">חיננית</option>
                      <option value="חיפה">חיפה</option>
                      <option value="חירות">חירות</option>
                      <option value="חלוץ">חלוץ</option>
                      <option value="חלמיש">חלמיש</option>
                      <option value="חלץ">חלץ</option>
                      <option value="חמאם">חמאם</option>
                      <option value="חמד">חמד</option>
                      <option value="חמדיה">חמדיה</option>
                      <option value="חמדת">חמדת</option>
                      <option value="חמרה">חמרה</option>
                      <option value="חניאל">חניאל</option>
                      <option value="חניתה">חניתה</option>
                      <option value="חנתון">חנתון</option>
                      <option value="חספין">חספין</option>
                      <option value="חפץ חיים">חפץ חיים</option>
                      <option value="חפצי-בה">חפצי-בה</option>
                      <option value="חצב">חצב</option>
                      <option value="חצבה">חצבה</option>
                      <option value="חצור-אשדוד">חצור-אשדוד</option>
                      <option value="חצור הגלילית">חצור הגלילית</option>
                      <option value="חצרים">חצרים</option>
                      <option value="חרב לאת">חרב לאת</option>
                      <option value="חרוצים">חרוצים</option>
                      <option value="חרמש">חרמש</option>
                      <option value="חרשים">חרשים</option>
                      <option value="חשמונאים">חשמונאים</option>
                      <option value="טבריה">טבריה</option>
                      <option value="טובא-זנגרייה">טובא-זנגרייה</option>
                      <option value="טורעאן">טורעאן</option>
                      <option value="טייבה">טייבה</option>
                      <option value="טייבה (בעמק)">טייבה (בעמק)</option>
                      <option value="טירה">טירה</option>
                      <option value="טירת יהודה">טירת יהודה</option>
                      <option value="טירת כרמל">טירת כרמל</option>
                      <option value="טירת צבי">טירת צבי</option>
                      <option value="טל-אל">טל-אל</option>
                      <option value="טל שחר">טל שחר</option>
                      <option value="טללים">טללים</option>
                      <option value="טלמון">טלמון</option>
                      <option value="טמרה">טמרה</option>
                      <option value="טמרה (יזרעאל)">טמרה (יזרעאל)</option>
                      <option value="טנא">טנא</option>
                      <option value="טפחות">טפחות</option>
                      <option value="יאנוח-ג'ת">יאנוח-ג'ת</option>
                      <option value="יבול">יבול</option>
                      <option value="יבנאל">יבנאל</option>
                      <option value="יבנה">יבנה</option>
                      <option value="יגור">יגור</option>
                      <option value="יגל">יגל</option>
                      <option value="יד בנימין">יד בנימין</option>
                      <option value="יד השמונה">יד השמונה</option>
                      <option value="יד חנה">יד חנה</option>
                      <option value="יד מרדכי">יד מרדכי</option>
                      <option value="יד נתן">יד נתן</option>
                      <option value="יד רמב" ם"="">יד רמב</option>
                      <option value="ידידה">ידידה</option>
                      <option value="יהוד">יהוד</option>
                      <option value="יהל">יהל</option>
                      <option value="יובל">יובל</option>
                      <option value="יובלים">יובלים</option>
                      <option value="יודפת">יודפת</option>
                      <option value="יונתן">יונתן</option>
                      <option value="יושיביה">יושיביה</option>
                      <option value="יזרעאל">יזרעאל</option>
                      <option value="יחיעם">יחיעם</option>
                      <option value="יטבתה">יטבתה</option>
                      <option value="ייט" ב"="">ייט</option>
                      <option value="יכיני">יכיני</option>
                      <option value="ים המלח - בתי מלון">ים המלח - בתי מלון</option>
                      <option value="ינוב">ינוב</option>
                      <option value="ינון">ינון</option>
                      <option value="יסוד המעלה">יסוד המעלה</option>
                      <option value="יסודות">יסודות</option>
                      <option value="יסעור">יסעור</option>
                      <option value="יעד">יעד</option>
                      <option value="יעל">יעל</option>
                      <option value="יעף">יעף</option>
                      <option value="יערה">יערה</option>
                      <option value="יפיע">יפיע</option>
                      <option value="יפית">יפית</option>
                      <option value="יפעת">יפעת</option>
                      <option value="יפתח">יפתח</option>
                      <option value="יצהר">יצהר</option>
                      <option value="יציץ">יציץ</option>
                      <option value="יקום">יקום</option>
                      <option value="יקיר">יקיר</option>
                      <option value="יקנעם (מושבה)">יקנעם (מושבה)</option>
                      <option value="יקנעם עילית">יקנעם עילית</option>
                      <option value="יראון">יראון</option>
                      <option value="ירדנה">ירדנה</option>
                      <option value="ירוחם">ירוחם</option>
                      <option value="ירושלים">ירושלים</option>
                      <option value="ירחיב">ירחיב</option>
                      <option value="ירכא">ירכא</option>
                      <option value="ירקונה">ירקונה</option>
                      <option value="ישע">ישע</option>
                      <option value="ישעי">ישעי</option>
                      <option value="ישרש">ישרש</option>
                      <option value="יתד">יתד</option>
                      <option value="כאבול">כאבול</option>
                      <option value="כאוכב אבו אל-היג'א">כאוכב אבו אל-היג'א</option>
                      <option value="כברי">כברי</option>
                      <option value="כדורי">כדורי</option>
                      <option value="כוכב השחר">כוכב השחר</option>
                      <option value="כוכב יאיר">כוכב יאיר</option>
                      <option value="כוכב יעקב">כוכב יעקב</option>
                      <option value="כוכב מיכאל">כוכב מיכאל</option>
                      <option value="כורזים">כורזים</option>
                      <option value="כחל">כחל</option>
                      <option value="כיסופים">כיסופים</option>
                      <option value="כישור">כישור</option>
                      <option value="כליל">כליל</option>
                      <option value="כלנית">כלנית</option>
                      <option value="כמאנה">כמאנה</option>
                      <option value="כמהין">כמהין</option>
                      <option value="כמון">כמון</option>
                      <option value="כנות">כנות</option>
                      <option value="כנף">כנף</option>
                      <option value="כנרת (מושבה)">כנרת (מושבה)</option>
                      <option value="כנרת (קבוצה)">כנרת (קבוצה)</option>
                      <option value="כסיפה">כסיפה</option>
                      <option value="כסלון">כסלון</option>
                      <option value="כסרא-סמיע">כסרא-סמיע</option>
                      <option value="כעביה-טבאש-חג'אג'רה">כעביה-טבאש-חג'אג'רה</option>
                      <option value="כפר אביב">כפר אביב</option>
                      <option value="כפר אדומים">כפר אדומים</option>
                      <option value="כפר אוריה">כפר אוריה</option>
                      <option value="כפר אז" ר"="">כפר אז</option>
                      <option value="כפר אחים">כפר אחים</option>
                      <option value="כפר ביאליק">כפר ביאליק</option>
                      <option value="כפר ביל" ו"="">כפר ביל</option>
                      <option value="כפר בלום">כפר בלום</option>
                      <option value="כפר בן נון">כפר בן נון</option>
                      <option value="כפר ברא">כפר ברא</option>
                      <option value="כפר ברוך">כפר ברוך</option>
                      <option value="כפר גדעון">כפר גדעון</option>
                      <option value="כפר גלים">כפר גלים</option>
                      <option value="כפר גליקסון">כפר גליקסון</option>
                      <option value="כפר גלעדי">כפר גלעדי</option>
                      <option value="כפר דניאל">כפר דניאל</option>
                      <option value="כפר האורנים">כפר האורנים</option>
                      <option value="כפר החורש">כפר החורש</option>
                      <option value="כפר המכבי">כפר המכבי</option>
                      <option value="כפר הנגיד">כפר הנגיד</option>
                      <option value="כפר הנוער הדתי">כפר הנוער הדתי</option>
                      <option value="כפר הנשיא">כפר הנשיא</option>
                      <option value="כפר הס">כפר הס</option>
                      <option value="כפר הרא" ה"="">כפר הרא</option>
                      <option value="כפר הרי" ף"="">כפר הרי</option>
                      <option value="כפר ויתקין">כפר ויתקין</option>
                      <option value="כפר ורבורג">כפר ורבורג</option>
                      <option value="כפר ורדים">כפר ורדים</option>
                      <option value="כפר זוהרים">כפר זוהרים</option>
                      <option value="כפר זיתים">כפר זיתים</option>
                      <option value="כפר חב" ד"="">כפר חב</option>
                      <option value="כפר חושן">כפר חושן</option>
                      <option value="כפר חיטים">כפר חיטים</option>
                      <option value="כפר חיים">כפר חיים</option>
                      <option value="כפר חנניה">כפר חנניה</option>
                      <option value="כפר חסידים א'">כפר חסידים א'</option>
                      <option value="כפר חסידים ב'">כפר חסידים ב'</option>
                      <option value="כפר חרוב">כפר חרוב</option>
                      <option value="כפר טרומן">כפר טרומן</option>
                      <option value="כפר יאסיף">כפר יאסיף</option>
                      <option value="כפר ידידיה">כפר ידידיה</option>
                      <option value="כפר יהושע">כפר יהושע</option>
                      <option value="כפר יונה">כפר יונה</option>
                      <option value="כפר יחזקאל">כפר יחזקאל</option>
                      <option value="כפר יעבץ">כפר יעבץ</option>
                      <option value="כפר כמא">כפר כמא</option>
                      <option value="כפר כנא">כפר כנא</option>
                      <option value="כפר מונש">כפר מונש</option>
                      <option value="כפר מימון">כפר מימון</option>
                      <option value="כפר מל" ל"="">כפר מל</option>
                      <option value="כפר מנדא">כפר מנדא</option>
                      <option value="כפר מנחם">כפר מנחם</option>
                      <option value="כפר מסריק">כפר מסריק</option>
                      <option value="כפר מצר">כפר מצר</option>
                      <option value="כפר מרדכי">כפר מרדכי</option>
                      <option value="כפר נטר">כפר נטר</option>
                      <option value="כפר סאלד">כפר סאלד</option>
                      <option value="כפר סבא">כפר סבא</option>
                      <option value="כפר סילבר">כפר סילבר</option>
                      <option value="כפר סירקין">כפר סירקין</option>
                      <option value="כפר עבודה">כפר עבודה</option>
                      <option value="כפר עזה">כפר עזה</option>
                      <option value="כפר עציון">כפר עציון</option>
                      <option value="כפר פינס">כפר פינס</option>
                      <option value="כפר קאסם">כפר קאסם</option>
                      <option value="כפר קיש">כפר קיש</option>
                      <option value="כפר קרע">כפר קרע</option>
                      <option value="כפר ראש הנקרה">כפר ראש הנקרה</option>
                      <option value="כפר רוזנואלד (זרעית)">כפר רוזנואלד (זרעית)</option>
                      <option value="כפר רופין">כפר רופין</option>
                      <option value="כפר רות">כפר רות</option>
                      <option value="כפר שמאי">כפר שמאי</option>
                      <option value="כפר שמואל">כפר שמואל</option>
                      <option value="כפר שמריהו">כפר שמריהו</option>
                      <option value="כפר תבור">כפר תבור</option>
                      <option value="כפר תפוח">כפר תפוח</option>
                      <option value="כרכום">כרכום</option>
                      <option value="כרם בן זמרה">כרם בן זמרה</option>
                      <option value="כרם בן שמן">כרם בן שמן</option>
                      <option value="כרם מהר" ל"="">כרם מהר</option>
                      <option value="כרם שלום">כרם שלום</option>
                      <option value="כרמי יוסף">כרמי יוסף</option>
                      <option value="כרמי צור">כרמי צור</option>
                      <option value="כרמיאל">כרמיאל</option>
                      <option value="כרמייה">כרמייה</option>
                      <option value="כרמים">כרמים</option>
                      <option value="כרמל">כרמל</option>
                      <option value="לבון">לבון</option>
                      <option value="לביא">לביא</option>
                      <option value="לבנים">לבנים</option>
                      <option value="להב">להב</option>
                      <option value="להבות הבשן">להבות הבשן</option>
                      <option value="להבות חביבה">להבות חביבה</option>
                      <option value="להבים">להבים</option>
                      <option value="לוד">לוד</option>
                      <option value="לוזית">לוזית</option>
                      <option value="לוחמי הגיטאות">לוחמי הגיטאות</option>
                      <option value="לוטם">לוטם</option>
                      <option value="לוטן">לוטן</option>
                      <option value="לימן">לימן</option>
                      <option value="לכיש">לכיש</option>
                      <option value="לפיד">לפיד</option>
                      <option value="לפידות">לפידות</option>
                      <option value="לקיה">לקיה</option>
                      <option value="מאור">מאור</option>
                      <option value="מאיר שפיה">מאיר שפיה</option>
                      <option value="מבוא ביתר">מבוא ביתר</option>
                      <option value="מבוא דותן">מבוא דותן</option>
                      <option value="מבוא חורון">מבוא חורון</option>
                      <option value="מבוא חמה">מבוא חמה</option>
                      <option value="מבוא מודיעים">מבוא מודיעים</option>
                      <option value="מבואות ים">מבואות ים</option>
                      <option value="מבועים">מבועים</option>
                      <option value="מבטחים">מבטחים</option>
                      <option value="מבקיעים">מבקיעים</option>
                      <option value="מבשרת ציון">מבשרת ציון</option>
                      <option value="מג'דל שמס">מג'דל שמס</option>
                      <option value="מגאר">מגאר</option>
                      <option value="מגדים">מגדים</option>
                      <option value="מגדל">מגדל</option>
                      <option value="מגדל העמק">מגדל העמק</option>
                      <option value="מגדל עוז">מגדל עוז</option>
                      <option value="מגדל תפן">מגדל תפן</option>
                      <option value="מגדלים">מגדלים</option>
                      <option value="מגידו">מגידו</option>
                      <option value="מגל">מגל</option>
                      <option value="מגן">מגן</option>
                      <option value="מגן שאול">מגן שאול</option>
                      <option value="מגשימים">מגשימים</option>
                      <option value="מדרך עוז">מדרך עוז</option>
                      <option value="מדרשת בן גוריון">מדרשת בן גוריון</option>
                      <option value="מדרשת רופין">מדרשת רופין</option>
                      <option value="מודיעין-מכבים-רעות">מודיעין-מכבים-רעות</option>
                      <option value="מודיעין עילית">מודיעין עילית</option>
                      <option value="מולדת">מולדת</option>
                      <option value="מוצא עילית">מוצא עילית</option>
                      <option value="מוקייבלה">מוקייבלה</option>
                      <option value="מורן">מורן</option>
                      <option value="מורשת">מורשת</option>
                      <option value="מזור">מזור</option>
                      <option value="מזכרת בתיה">מזכרת בתיה</option>
                      <option value="מזרע">מזרע</option>
                      <option value="מזרעה">מזרעה</option>
                      <option value="מחולה">מחולה</option>
                      <option value="מחניים">מחניים</option>
                      <option value="מחסיה">מחסיה</option>
                      <option value="מטולה">מטולה</option>
                      <option value="מטע">מטע</option>
                      <option value="מי עמי">מי עמי</option>
                      <option value="מיטב">מיטב</option>
                      <option value="מייסר">מייסר</option>
                      <option value="מיצר">מיצר</option>
                      <option value="מירב">מירב</option>
                      <option value="מירון">מירון</option>
                      <option value="מישר">מישר</option>
                      <option value="מיתר">מיתר</option>
                      <option value="מכורה">מכורה</option>
                      <option value="מכחול">מכחול</option>
                      <option value="מכמורת">מכמורת</option>
                      <option value="מכמנים">מכמנים</option>
                      <option value="מלאה">מלאה</option>
                      <option value="מלילות">מלילות</option>
                      <option value="מלכייה">מלכייה</option>
                      <option value="מלכישוע">מלכישוע</option>
                      <option value="מנוחה">מנוחה</option>
                      <option value="מנוף">מנוף</option>
                      <option value="מנות">מנות</option>
                      <option value="מנחמיה">מנחמיה</option>
                      <option value="מנרה">מנרה</option>
                      <option value="מנשית זבדה">מנשית זבדה</option>
                      <option value="מסד">מסד</option>
                      <option value="מסדה">מסדה</option>
                      <option value="מסילות">מסילות</option>
                      <option value="מסילת ציון">מסילת ציון</option>
                      <option value="מסלול">מסלול</option>
                      <option value="מסעדה">מסעדה</option>
                      <option value="מעברות">מעברות</option>
                      <option value="מעגלים">מעגלים</option>
                      <option value="מעגן">מעגן</option>
                      <option value="מעגן מיכאל">מעגן מיכאל</option>
                      <option value="מעוז חיים">מעוז חיים</option>
                      <option value="מעון">מעון</option>
                      <option value="מעונה">מעונה</option>
                      <option value="מעיין ברוך">מעיין ברוך</option>
                      <option value="מעיין צבי">מעיין צבי</option>
                      <option value="מעיליא">מעיליא</option>
                      <option value="מעלה אדומים">מעלה אדומים</option>
                      <option value="מעלה אפרים">מעלה אפרים</option>
                      <option value="מעלה גלבוע">מעלה גלבוע</option>
                      <option value="מעלה גמלא">מעלה גמלא</option>
                      <option value="מעלה החמישה">מעלה החמישה</option>
                      <option value="מעלה לבונה">מעלה לבונה</option>
                      <option value="מעלה מכמש">מעלה מכמש</option>
                      <option value="מעלה עירון">מעלה עירון</option>
                      <option value="מעלה עמוס">מעלה עמוס</option>
                      <option value="מעלה שומרון">מעלה שומרון</option>
                      <option value="מעלות-תרשיחא">מעלות-תרשיחא</option>
                      <option value="מענית">מענית</option>
                      <option value="מעש">מעש</option>
                      <option value="מפלסים">מפלסים</option>
                      <option value="מצדות יהודה">מצדות יהודה</option>
                      <option value="מצובה">מצובה</option>
                      <option value="מצליח">מצליח</option>
                      <option value="מצפה">מצפה</option>
                      <option value="מצפה אבי" ב"="">מצפה אבי</option>
                      <option value="מצפה יריחו">מצפה יריחו</option>
                      <option value="מצפה נטופה">מצפה נטופה</option>
                      <option value="מצפה רמון">מצפה רמון</option>
                      <option value="מצפה שלם">מצפה שלם</option>
                      <option value="מצר">מצר</option>
                      <option value="מקווה ישראל">מקווה ישראל</option>
                      <option value="מרגליות">מרגליות</option>
                      <option value="מרום גולן">מרום גולן</option>
                      <option value="מרחב עם">מרחב עם</option>
                      <option value="מרחביה (מושב)">מרחביה (מושב)</option>
                      <option value="מרחביה (קיבוץ)">מרחביה (קיבוץ)</option>
                      <option value="מרכז שפירא">מרכז שפירא</option>
                      <option value="משאבי שדה">משאבי שדה</option>
                      <option value="משגב דב">משגב דב</option>
                      <option value="משגב עם">משגב עם</option>
                      <option value="משהד">משהד</option>
                      <option value="משואה">משואה</option>
                      <option value="משואות יצחק">משואות יצחק</option>
                      <option value="משכיות">משכיות</option>
                      <option value="משמר איילון">משמר איילון</option>
                      <option value="משמר דוד">משמר דוד</option>
                      <option value="משמר הירדן">משמר הירדן</option>
                      <option value="משמר הנגב">משמר הנגב</option>
                      <option value="משמר העמק">משמר העמק</option>
                      <option value="משמר השבעה">משמר השבעה</option>
                      <option value="משמר השרון">משמר השרון</option>
                      <option value="משמרות">משמרות</option>
                      <option value="משמרת">משמרת</option>
                      <option value="משען">משען</option>
                      <option value="מתן">מתן</option>
                      <option value="מתת">מתת</option>
                      <option value="מתתיהו">מתתיהו</option>
                      <option value="נאות גולן">נאות גולן</option>
                      <option value="נאות הכיכר">נאות הכיכר</option>
                      <option value="נאות מרדכי">נאות מרדכי</option>
                      <option value="נאות סמדר">נאות סמדר</option>
                      <option value="נאעורה">נאעורה</option>
                      <option value="נבטים">נבטים</option>
                      <option value="נגבה">נגבה</option>
                      <option value="נגוהות">נגוהות</option>
                      <option value="נהורה">נהורה</option>
                      <option value="נהלל">נהלל</option>
                      <option value="נהרייה">נהרייה</option>
                      <option value="נוב">נוב</option>
                      <option value="נוגה">נוגה</option>
                      <option value="נווה אבות">נווה אבות</option>
                      <option value="נווה אור">נווה אור</option>
                      <option value="נווה אטי" ב"="">נווה אטי</option>
                      <option value="נווה אילן">נווה אילן</option>
                      <option value="נווה אילן">נווה אילן</option>
                      <option value="נווה איתן">נווה איתן</option>
                      <option value="נווה אפעל">נווה אפעל</option>
                      <option value="נווה דניאל">נווה דניאל</option>
                      <option value="נווה זוהר">נווה זוהר</option>
                      <option value="נווה זיו">נווה זיו</option>
                      <option value="נווה חריף">נווה חריף</option>
                      <option value="נווה ים">נווה ים</option>
                      <option value="נווה ימין">נווה ימין</option>
                      <option value="נווה ירק">נווה ירק</option>
                      <option value="נווה מבטח">נווה מבטח</option>
                      <option value="נווה מיכאל">נווה מיכאל</option>
                      <option value="נווה שלום">נווה שלום</option>
                      <option value="נועם">נועם</option>
                      <option value="נוף איילון">נוף איילון</option>
                      <option value="נופים">נופים</option>
                      <option value="נופית">נופית</option>
                      <option value="נופך">נופך</option>
                      <option value="נוקדים">נוקדים</option>
                      <option value="נורדייה">נורדייה</option>
                      <option value="נורית">נורית</option>
                      <option value="נחושה">נחושה</option>
                      <option value="נחל עוז">נחל עוז</option>
                      <option value="נחלה">נחלה</option>
                      <option value="נחליאל">נחליאל</option>
                      <option value="נחלים">נחלים</option>
                      <option value="נחם">נחם</option>
                      <option value="נחף">נחף</option>
                      <option value="נחשולים">נחשולים</option>
                      <option value="נחשון">נחשון</option>
                      <option value="נחשונים">נחשונים</option>
                      <option value="נטועה">נטועה</option>
                      <option value="נטור">נטור</option>
                      <option value="נטעים">נטעים</option>
                      <option value="נטף">נטף</option>
                      <option value="ניין">ניין</option>
                      <option value="ניל" י"="">ניל</option>
                      <option value="ניצן">ניצן</option>
                      <option value="ניצנה (קהילת חינוך)">ניצנה (קהילת חינוך)</option>
                      <option value="ניצני סיני">ניצני סיני</option>
                      <option value="ניצני עוז">ניצני עוז</option>
                      <option value="ניצנים">ניצנים</option>
                      <option value="ניר אליהו">ניר אליהו</option>
                      <option value="ניר בנים">ניר בנים</option>
                      <option value="ניר גלים">ניר גלים</option>
                      <option value="ניר דוד (תל עמל)">ניר דוד (תל עמל)</option>
                      <option value="ניר ח" ן"="">ניר ח</option>
                      <option value="ניר יפה">ניר יפה</option>
                      <option value="ניר יצחק">ניר יצחק</option>
                      <option value="ניר ישראל">ניר ישראל</option>
                      <option value="ניר משה">ניר משה</option>
                      <option value="ניר עוז">ניר עוז</option>
                      <option value="ניר עם">ניר עם</option>
                      <option value="ניר עציון">ניר עציון</option>
                      <option value="ניר עקיבא">ניר עקיבא</option>
                      <option value="ניר צבי">ניר צבי</option>
                      <option value="נירים">נירים</option>
                      <option value="נירית">נירית</option>
                      <option value="נירן">נירן</option>
                      <option value="נמל תעופה בן-גוריון">נמל תעופה בן-גוריון</option>
                      <option value="נס הרים">נס הרים</option>
                      <option value="נס עמים">נס עמים</option>
                      <option value="נס ציונה">נס ציונה</option>
                      <option value="נעורים">נעורים</option>
                      <option value="נעלה">נעלה</option>
                      <option value="נעמי">נעמי</option>
                      <option value="נען">נען</option>
                      <option value="נצר סרני">נצר סרני</option>
                      <option value="נצרת">נצרת</option>
                      <option value="נצרת עילית">נצרת עילית</option>
                      <option value="נשר">נשר</option>
                      <option value="נתיב הגדוד">נתיב הגדוד</option>
                      <option value="נתיב הל" ה"="">נתיב הל</option>
                      <option value="נתיב העשרה">נתיב העשרה</option>
                      <option value="נתיב השיירה">נתיב השיירה</option>
                      <option value="נתיבות">נתיבות</option>
                      <option value="נתניה">נתניה</option>
                      <option value="סאג'ור">סאג'ור</option>
                      <option value="סאסא">סאסא</option>
                      <option value="סביון">סביון</option>
                      <option value="סגולה">סגולה</option>
                      <option value="סואעד (חמרייה)">סואעד (חמרייה)</option>
                      <option value="סולם">סולם</option>
                      <option value="סוסיה">סוסיה</option>
                      <option value="סופה">סופה</option>
                      <option value="סח'נין">סח'נין</option>
                      <option value="סלמה">סלמה</option>
                      <option value="סלעית">סלעית</option>
                      <option value="סמר">סמר</option>
                      <option value="סנסנה">סנסנה</option>
                      <option value="סעד">סעד</option>
                      <option value="סער">סער</option>
                      <option value="ספיר">ספיר</option>
                      <option value="סתרייה">סתרייה</option>
                      <option value="ע'ג'ר">ע'ג'ר</option>
                      <option value="עבדון">עבדון</option>
                      <option value="עברון">עברון</option>
                      <option value="עגור">עגור</option>
                      <option value="עד הלום">עד הלום</option>
                      <option value="עדי">עדי</option>
                      <option value="עדנים">עדנים</option>
                      <option value="עוזה">עוזה</option>
                      <option value="עוזייר">עוזייר</option>
                      <option value="עולש">עולש</option>
                      <option value="עומר">עומר</option>
                      <option value="עופר">עופר</option>
                      <option value="עוצם">עוצם</option>
                      <option value="עזוז">עזוז</option>
                      <option value="עזר">עזר</option>
                      <option value="עזריאל">עזריאל</option>
                      <option value="עזריה">עזריה</option>
                      <option value="עזריקם">עזריקם</option>
                      <option value="עטרת">עטרת</option>
                      <option value="עידן">עידן</option>
                      <option value="עיילבון">עיילבון</option>
                      <option value="עיינות">עיינות</option>
                      <option value="עילוט">עילוט</option>
                      <option value="עין איילה">עין איילה</option>
                      <option value="עין אל-אסד">עין אל-אסד</option>
                      <option value="עין גב">עין גב</option>
                      <option value="עין גדי">עין גדי</option>
                      <option value="עין דור">עין דור</option>
                      <option value="עין הבשור">עין הבשור</option>
                      <option value="עין הוד">עין הוד</option>
                      <option value="עין החורש">עין החורש</option>
                      <option value="עין המפרץ">עין המפרץ</option>
                      <option value="עין הנצי" ב"="">עין הנצי</option>
                      <option value="עין העמק">עין העמק</option>
                      <option value="עין השופט">עין השופט</option>
                      <option value="עין השלושה">עין השלושה</option>
                      <option value="עין ורד">עין ורד</option>
                      <option value="עין זיוון">עין זיוון</option>
                      <option value="עין חוד">עין חוד</option>
                      <option value="עין חצבה">עין חצבה</option>
                      <option value="עין חרוד (איחוד)">עין חרוד (איחוד)</option>
                      <option value="עין חרוד (מאוחד)">עין חרוד (מאוחד)</option>
                      <option value="עין יהב">עין יהב</option>
                      <option value="עין יעקב">עין יעקב</option>
                      <option value="עין כרמל">עין כרמל</option>
                      <option value="עין מאהל">עין מאהל</option>
                      <option value="עין נקובא">עין נקובא</option>
                      <option value="עין עירון">עין עירון</option>
                      <option value="עין צורים">עין צורים</option>
                      <option value="עין קנייא">עין קנייא</option>
                      <option value="עין ראפה">עין ראפה</option>
                      <option value="עין שמר">עין שמר</option>
                      <option value="עין שריד">עין שריד</option>
                      <option value="עין תמר">עין תמר</option>
                      <option value="עינת">עינת</option>
                      <option value="עיר אובות">עיר אובות</option>
                      <option value="עיר כרמל">עיר כרמל</option>
                      <option value="עכו">עכו</option>
                      <option value="עלומים">עלומים</option>
                      <option value="עלי">עלי</option>
                      <option value="עלי זהב">עלי זהב</option>
                      <option value="עלמה">עלמה</option>
                      <option value="עלמון">עלמון</option>
                      <option value="עמוקה">עמוקה</option>
                      <option value="עמינדב">עמינדב</option>
                      <option value="עמיעד">עמיעד</option>
                      <option value="עמיעוז">עמיעוז</option>
                      <option value="עמיקם">עמיקם</option>
                      <option value="עמיר">עמיר</option>
                      <option value="עמנואל">עמנואל</option>
                      <option value="עמקה">עמקה</option>
                      <option value="ענב">ענב</option>
                      <option value="עפולה">עפולה</option>
                      <option value="עפרה">עפרה</option>
                      <option value="עץ אפרים">עץ אפרים</option>
                      <option value="עראבה">עראבה</option>
                      <option value="עראמשה">עראמשה</option>
                      <option value="ערד">ערד</option>
                      <option value="ערוגות">ערוגות</option>
                      <option value="ערערה">ערערה</option>
                      <option value="ערערה-בנגב">ערערה-בנגב</option>
                      <option value="עשרת">עשרת</option>
                      <option value="עתלית">עתלית</option>
                      <option value="עתניאל">עתניאל</option>
                      <option value="פארן">פארן</option>
                      <option value="פארק תעשיה חבל מודיעים">פארק תעשיה חבל מודיעים</option>
                      <option value="פדואל">פדואל</option>
                      <option value="פדויים">פדויים</option>
                      <option value="פדיה">פדיה</option>
                      <option value="פוריידיס">פוריידיס</option>
                      <option value="פורייה - כפר עבודה">פורייה - כפר עבודה</option>
                      <option value="פורייה - נווה עובד">פורייה - נווה עובד</option>
                      <option value="פורייה עילית">פורייה עילית</option>
                      <option value="פורת">פורת</option>
                      <option value="פטיש">פטיש</option>
                      <option value="פלך">פלך</option>
                      <option value="פלמחים">פלמחים</option>
                      <option value="פני חבר">פני חבר</option>
                      <option value="פסגות">פסגות</option>
                      <option value="פסוטה">פסוטה</option>
                      <option value="פעמי תש" ז"="">פעמי תש</option>
                      <option value="פצאל">פצאל</option>
                      <option value="פקיעין (בוקייעה)">פקיעין (בוקייעה)</option>
                      <option value="פקיעין חדשה">פקיעין חדשה</option>
                      <option value="פרדס חנה-כרכור">פרדס חנה-כרכור</option>
                      <option value="פרדסייה">פרדסייה</option>
                      <option value="פרוד">פרוד</option>
                      <option value="פרזון">פרזון</option>
                      <option value="פרי גן">פרי גן</option>
                      <option value="פתח תקווה">פתח תקווה</option>
                      <option value="פתחיה">פתחיה</option>
                      <option value="צאלים">צאלים</option>
                      <option value="צביה">צביה</option>
                      <option value="צבעון">צבעון</option>
                      <option value="צובה">צובה</option>
                      <option value="צוחר">צוחר</option>
                      <option value="צופייה">צופייה</option>
                      <option value="צופים">צופים</option>
                      <option value="צופית">צופית</option>
                      <option value="צופר">צופר</option>
                      <option value="צוקי ים">צוקי ים</option>
                      <option value="צוקים">צוקים</option>
                      <option value="צור הדסה">צור הדסה</option>
                      <option value="צור משה">צור משה</option>
                      <option value="צור נתן">צור נתן</option>
                      <option value="צוריאל">צוריאל</option>
                      <option value="צורית">צורית</option>
                      <option value="צורן-קדימה">צורן-קדימה</option>
                      <option value="ציפורי">ציפורי</option>
                      <option value="צלפון">צלפון</option>
                      <option value="צנדלה">צנדלה</option>
                      <option value="צפרייה">צפרייה</option>
                      <option value="צפרירים">צפרירים</option>
                      <option value="צפת">צפת</option>
                      <option value="צרופה">צרופה</option>
                      <option value="צרעה">צרעה</option>
                      <option value="קבוצת יבנה">קבוצת יבנה</option>
                      <option value="קדומים">קדומים</option>
                      <option value="קדמה">קדמה</option>
                      <option value="קדמת צבי">קדמת צבי</option>
                      <option value="קדר">קדר</option>
                      <option value="קדרון">קדרון</option>
                      <option value="קדרים">קדרים</option>
                      <option value="קוממיות">קוממיות</option>
                      <option value="קורנית">קורנית</option>
                      <option value="קטורה">קטורה</option>
                      <option value="קיסריה">קיסריה</option>
                      <option value="קלחים">קלחים</option>
                      <option value="קליה">קליה</option>
                      <option value="קלנסווה">קלנסווה</option>
                      <option value="קלע">קלע</option>
                      <option value="קציר-חריש">קציר-חריש</option>
                      <option value="קצר א-סר">קצר א-סר</option>
                      <option value="קצרין">קצרין</option>
                      <option value="קריית אונו">קריית אונו</option>
                      <option value="קריית ארבע">קריית ארבע</option>
                      <option value="קריית אתא">קריית אתא</option>
                      <option value="קריית ביאליק">קריית ביאליק</option>
                      <option value="קריית גת">קריית גת</option>
                      <option value="קריית טבעון">קריית טבעון</option>
                      <option value="קריית ים">קריית ים</option>
                      <option value="קריית יערים">קריית יערים</option>
                      <option value="קריית מוצקין">קריית מוצקין</option>
                      <option value="קריית מלאכי">קריית מלאכי</option>
                      <option value="קריית נטפים">קריית נטפים</option>
                      <option value="קריית ענבים">קריית ענבים</option>
                      <option value="קריית עקרון">קריית עקרון</option>
                      <option value="קריית שלמה">קריית שלמה</option>
                      <option value="קריית שמונה">קריית שמונה</option>
                      <option value="קרית חינוך עזתה">קרית חינוך עזתה</option>
                      <option value="קרית תעופה">קרית תעופה</option>
                      <option value="קרני שומרון">קרני שומרון</option>
                      <option value="קשת">קשת</option>
                      <option value="ראמה">ראמה</option>
                      <option value="ראס אל-עין">ראס אל-עין</option>
                      <option value="ראס עלי">ראס עלי</option>
                      <option value="ראש העין">ראש העין</option>
                      <option value="ראש פינה">ראש פינה</option>
                      <option value="ראש צורים">ראש צורים</option>
                      <option value="ראשון לציון">ראשון לציון</option>
                      <option value="רבבה">רבבה</option>
                      <option value="רבדים">רבדים</option>
                      <option value="רביבים">רביבים</option>
                      <option value="רביד">רביד</option>
                      <option value="רגבה">רגבה</option>
                      <option value="רגבים">רגבים</option>
                      <option value="רהט">רהט</option>
                      <option value="רווחה">רווחה</option>
                      <option value="רוויה">רוויה</option>
                      <option value="רוחמה">רוחמה</option>
                      <option value="רומאנה">רומאנה</option>
                      <option value="רומת הייב">רומת הייב</option>
                      <option value="רועי">רועי</option>
                      <option value="רותם">רותם</option>
                      <option value="רחוב">רחוב</option>
                      <option value="רחובות">רחובות</option>
                      <option value="ריחאנייה">ריחאנייה</option>
                      <option value="ריחן">ריחן</option>
                      <option value="ריינה">ריינה</option>
                      <option value="רימונים">רימונים</option>
                      <option value="רינתיה">רינתיה</option>
                      <option value="רכסים">רכסים</option>
                      <option value="רם-און">רם-און</option>
                      <option value="רמות">רמות</option>
                      <option value="רמות השבים">רמות השבים</option>
                      <option value="רמות מאיר">רמות מאיר</option>
                      <option value="רמות מנשה">רמות מנשה</option>
                      <option value="רמות נפתלי">רמות נפתלי</option>
                      <option value="רמלה">רמלה</option>
                      <option value="רמת אפעל">רמת אפעל</option>
                      <option value="רמת גן">רמת גן</option>
                      <option value="רמת דוד">רמת דוד</option>
                      <option value="רמת הכובש">רמת הכובש</option>
                      <option value="רמת השופט">רמת השופט</option>
                      <option value="רמת השרון">רמת השרון</option>
                      <option value="רמת חובב">רמת חובב</option>
                      <option value="רמת יוחנן">רמת יוחנן</option>
                      <option value="רמת ישי">רמת ישי</option>
                      <option value="רמת מגשימים">רמת מגשימים</option>
                      <option value="רמת פנקס">רמת פנקס</option>
                      <option value="רמת צבי">רמת צבי</option>
                      <option value="רמת רזיאל">רמת רזיאל</option>
                      <option value="רמת רחל">רמת רחל</option>
                      <option value="רנן">רנן</option>
                      <option value="רעים">רעים</option>
                      <option value="רעננה">רעננה</option>
                      <option value="רקפת">רקפת</option>
                      <option value="רשפון">רשפון</option>
                      <option value="רשפים">רשפים</option>
                      <option value="רתמים">רתמים</option>
                      <option value="שאר ישוב">שאר ישוב</option>
                      <option value="שבי ציון">שבי ציון</option>
                      <option value="שבי שומרון">שבי שומרון</option>
                      <option value="שבלי - אום אל-גנם">שבלי - אום אל-גנם</option>
                      <option value="שגב">שגב</option>
                      <option value="שגב-שלום">שגב-שלום</option>
                      <option value="שגור">שגור</option>
                      <option value="שדה אילן">שדה אילן</option>
                      <option value="שדה אליהו">שדה אליהו</option>
                      <option value="שדה אליעזר">שדה אליעזר</option>
                      <option value="שדה בוקר">שדה בוקר</option>
                      <option value="שדה דוד">שדה דוד</option>
                      <option value="שדה ורבורג">שדה ורבורג</option>
                      <option value="שדה יואב">שדה יואב</option>
                      <option value="שדה יעקב">שדה יעקב</option>
                      <option value="שדה יצחק">שדה יצחק</option>
                      <option value="שדה משה">שדה משה</option>
                      <option value="שדה נחום">שדה נחום</option>
                      <option value="שדה נחמיה">שדה נחמיה</option>
                      <option value="שדה ניצן">שדה ניצן</option>
                      <option value="שדה עוזיהו">שדה עוזיהו</option>
                      <option value="שדה צבי">שדה צבי</option>
                      <option value="שדות ים">שדות ים</option>
                      <option value="שדות מיכה">שדות מיכה</option>
                      <option value="שדי אברהם">שדי אברהם</option>
                      <option value="שדי חמד">שדי חמד</option>
                      <option value="שדי תרומות">שדי תרומות</option>
                      <option value="שדמה">שדמה</option>
                      <option value="שדמות דבורה">שדמות דבורה</option>
                      <option value="שדמות מחולה">שדמות מחולה</option>
                      <option value="שדרות">שדרות</option>
                      <option value="שואבה">שואבה</option>
                      <option value="שובה">שובה</option>
                      <option value="שובל">שובל</option>
                      <option value="שוהם">שוהם</option>
                      <option value="שומרה">שומרה</option>
                      <option value="שומרייה">שומרייה</option>
                      <option value="שוקדה">שוקדה</option>
                      <option value="שורש">שורש</option>
                      <option value="שורשים">שורשים</option>
                      <option value="שושנת העמקים">שושנת העמקים</option>
                      <option value="שזור">שזור</option>
                      <option value="שחר">שחר</option>
                      <option value="שחרות">שחרות</option>
                      <option value="שיבולים">שיבולים</option>
                      <option value="שיטים">שיטים</option>
                      <option value="שייח' דנון">שייח' דנון</option>
                      <option value="שילה">שילה</option>
                      <option value="שילת">שילת</option>
                      <option value="שכניה">שכניה</option>
                      <option value="שלווה">שלווה</option>
                      <option value="שלוחות">שלוחות</option>
                      <option value="שלומי">שלומי</option>
                      <option value="שלומציון">שלומציון</option>
                      <option value="שמיר">שמיר</option>
                      <option value="שמעה">שמעה</option>
                      <option value="שמרת">שמרת</option>
                      <option value="שמשית">שמשית</option>
                      <option value="שני">שני</option>
                      <option value="שניר">שניר</option>
                      <option value="שעב">שעב</option>
                      <option value="שעל">שעל</option>
                      <option value="שעלבים">שעלבים</option>
                      <option value="שער אפרים">שער אפרים</option>
                      <option value="שער הגולן">שער הגולן</option>
                      <option value="שער העמקים">שער העמקים</option>
                      <option value="שער מנשה">שער מנשה</option>
                      <option value="שערי תקווה">שערי תקווה</option>
                      <option value="שפיים">שפיים</option>
                      <option value="שפיר">שפיר</option>
                      <option value="שפר">שפר</option>
                      <option value="שפרעם">שפרעם</option>
                      <option value="שקד">שקד</option>
                      <option value="שקף">שקף</option>
                      <option value="שרונה">שרונה</option>
                      <option value="שריגים (לי-און)">שריגים (לי-און)</option>
                      <option value="שריד">שריד</option>
                      <option value="שרשרת">שרשרת</option>
                      <option value="שתולה">שתולה</option>
                      <option value="שתולים">שתולים</option>
                      <option value="תאשור">תאשור</option>
                      <option value="תדהר">תדהר</option>
                      <option value="תובל">תובל</option>
                      <option value="תומר">תומר</option>
                      <option value="תושייה">תושייה</option>
                      <option value="תימורים">תימורים</option>
                      <option value="תירוש">תירוש</option>
                      <option value="תל-חי">תל-חי</option>
                      <option value="תל אביב -יפו">תל אביב -יפו</option>
                      <option value="תל יוסף">תל יוסף</option>
                      <option value="תל יצחק">תל יצחק</option>
                      <option value="תל מונד">תל מונד</option>
                      <option value="תל עדשים">תל עדשים</option>
                      <option value="תל קציר">תל קציר</option>
                      <option value="תל שבע">תל שבע</option>
                      <option value="תל תאומים">תל תאומים</option>
                      <option value="תלם">תלם</option>
                      <option value="תלמי אליהו">תלמי אליהו</option>
                      <option value="תלמי אלעזר">תלמי אלעזר</option>
                      <option value="תלמי ביל" ו"="">תלמי ביל</option>
                      <option value="תלמי יוסף">תלמי יוסף</option>
                      <option value="תלמי יחיאל">תלמי יחיאל</option>
                      <option value="תלמי יפה">תלמי יפה</option>
                      <option value="תלמים">תלמים</option>
                      <option value="תמרת">תמרת</option>
                      <option value="תנובות">תנובות</option>
                      <option value="תעוז">תעוז</option>
                      <option value="תפרח">תפרח</option>
                      <option value="תקומה">תקומה</option>
                      <option value="תרבין א-צאנע ">תרבין א-צאנע </option>
                      <option value="תרדיון">תרדיון</option>
                      <option value="תרום">תרום</option>
                      </select>
              <span class="feed-error-message">Error message here!</span>
            </p>

             <p class="fieldset pull-right" style="margin: 0px 5px; width: calc(20% - 5px)">
                <label class=" feed-birth" for="street_num">מספר בית</label>
                <input class="full-width has-padding has-border" name="street_num" id="street_num" type="text"  value="${requestScope.customer.getHouseNum()}" placeholder="בית" required>
                <span class="feed-error-message">Error message here!</span>
              </p>
              
              <p class="fieldset pull-right" style="margin: 0; width: calc(20% - 5px)">
                <label class=" feed-birth" for="home_num">מספר דירה</label>
                <input class="full-width has-padding has-border" name="home_num" id="home_num" type="text" value="${requestScope.customer.getApartNum()}"  placeholder="דירה" required>
                <span class="feed-error-message">Error message here!</span>
              </p>
          </div>

         

          <p class="fieldset hidden">
            <input type="checkbox" id="accept-terms" required>
            <label for="accept-terms">אני מסכים <a href="#0">לתנאי השימוש</a></label>
          </p>

          <p class="fieldset">
              <input type="hidden" value="0" name="role"/>
            <input class="full-width has-padding" type="submit" value="עדכן משתמש">
          </p>
        </form>

        <!-- <a href="#0" class="feed-close-form">Close</a> -->
      
        </div>
      </div>

  </div>  
</section>

      
<%@include file='../website_templates/login_register.jsp'%>
<%@include file='../website_templates/shopping_cart.jsp'%>
<%@include file='../website_templates/footer.jsp'%>
   