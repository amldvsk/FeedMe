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
            <div class="rest_header clearfix">
              <div class="rest-icon pull-right">
                <img src="http://kampaistreetwok.co.il/wp-content/uploads/2014/08/0006_logo_kampai.png" class="img-rounded" alt="placeholder+image">
              </div>
              <div class="rest_name">
                <h1>${requestScope.rest.getName()}</h1>  
                <h5 class="text-center"><i class="fa fa-map-marker"></i> לילינבלום 40, תל אביב יפו</h5>
              </div>
            </div>
          </div>
        </div>
      </div>

    </header>

<div class="bottom-navigation">
  <div class="container">
    <ul id="myTab" class="nav nav-justified" role="tablist">
      <li role="presentation" class="active"><a href="#orders" id="home-tab" role="tab" data-toggle="tab" aria-controls="home" aria-expanded="true">תפריט</a></li>
      <li role="presentation" class=""><a href="#sale" role="tab" id="profile-tab" data-toggle="tab" aria-controls="profile" aria-expanded="false">ביקורות</a></li>
    </ul>
  </div>
</div>

<section>
  <div class="container">

      <div id="myTabContent" class="tab-content">
        <div role="tabpanel" class="rest_cont tab-pane fade active in" id="orders" aria-labelledby="home-tab">
          <!-- Navigation Buttons -->
            

            <!-- Content -->
            <div class="col-md-8 resturen_menu_items">
              <div class="tab-content">
                <div class="tab-pane active" id="home">
                  <h3>ארוחת בוקר</h3>
                    
                    <ul class="list-inline items">
                    <li class="item">
                      <div class="item-logo">
                        <img src="https://www.mishloha.co.il/files/menu_food_pic/3010201218459057.jpg" alt="placeholder+image">
                      </div>
                      <div class="item-caption">
                        <h4>קפה הלל</h4>
                        <p><small><i class="fa fa-map-marker"></i> לילינבלום 40, תל אביב יפו</small></p>
                        <a href="#" class="btn btn-green btn-blcok">הזמן עכשיו</a> 
                      </div>
                    </li>
                    <li class="item">
                      <div class="item-logo">
                        <img src="https://www.mishloha.co.il/files/menu_food_pic/3010201250625087.jpg" alt="placeholder+image">
                      </div>
                      <div class="item-caption">
                        <h4>ערביקה</h4>
                        <p><small><i class="fa fa-map-marker"></i> לילינבלום 40, תל אביב יפו</small></p>
                        <a href="#" class="btn btn-green btn-blcok">הזמן עכשיו</a>
                      </div>
                    </li>
                    <li class="item">
                      <div class="item-logo">
                        <img src="https://www.mishloha.co.il/files/menu_food_pic/3010201220978237.JPG" alt="placeholder+image">
                      </div>
                      <div class="item-caption">
                        <h4>קמפאי</h4>
                        <p><small><i class="fa fa-map-marker"></i> לילינבלום 40, תל אביב יפו</small></p>
                        <a href="#" class="btn btn-green btn-blcok">הזמן עכשיו</a>
                      </div>
                    </li>
                    <li class="item">
                      <div class="item-logo">
                        <img src="https://www.mishloha.co.il/files/menu_food_pic/3010201221245388.JPG" alt="placeholder+image">
                      </div>
                      <div class="item-caption">
                        <h4>Black Bar 'n' Burger</h4>
                        <p><small><i class="fa fa-map-marker"></i> לילינבלום 40, תל אביב יפו</small></p>
                        <a href="#" class="btn btn-green btn-blcok">הזמן עכשיו</a>
                      </div>
                    </li>
                    <li class="item">
                      <div class="item-logo">
                        <img src="https://www.mishloha.co.il/files/menu_food_pic/3010201240694942.jpg" alt="placeholder+image">
                      </div>
                      <div class="item-caption">
                        <h4>ערביקה</h4>
                        <p><small><i class="fa fa-map-marker"></i> לילינבלום 40, תל אביב יפו</small></p>
                        <a href="#" class="btn btn-green btn-blcok">הזמן עכשיו</a>
                      </div>
                    </li>
                    <li class="item">
                      <div class="item-logo">
                        <img src="https://www.mishloha.co.il/files/menu_food_pic/24201349261547.jpg" alt="placeholder+image">
                      </div>
                      <div class="item-caption">
                        <h4>קמפאי</h4>
                        <p><small><i class="fa fa-map-marker"></i> לילינבלום 40, תל אביב יפו</small></p>
                        <a href="#" class="btn btn-green btn-blcok">הזמן עכשיו</a>
                      </div>
                    </li>
                  </ul>

                </div>
                <div class="tab-pane" id="profile">
                  <h3>ארוחת צהריים</h3>
                </div>
                <div class="tab-pane" id="messages">
                  <h3>ארוחת ערב</h3>
                </div>
              </div>
            </div>

            <div class="col-md-3 col-md-offset-1">
              <ul class="nav nav-pills nav-stacked" id="myTabs">
                <li class="active"><a href="#home">ארוחת בוקר</a></li>
                <li><a href="#profile">ארוחת צהריים</a></li>
                <li><a href="#messages">ארוחת ערב</a></li>
              </ul>
            </div>

        </div>
        <div role="tabpanel" class="rest_cont  tab-pane fade" id="sale" aria-labelledby="profile-tab">
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
      </div>

  </div>  
</section>

      
<%@include file='../website_templates/login_register.jsp'%>
<%@include file='../website_templates/shopping_cart.jsp'%>
<%@include file='../website_templates/footer.jsp'%>
   