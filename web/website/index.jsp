
    <jsp:directive.page contentType="text/html;charset=UTF-8"/>
     <%@include file='../website_templates/head.jsp'%>
    <%@include file='../website_templates/navigation.jsp'%>
<c:forEach var="i" begin="1" end="5">
   <!--Item <c:out value="${i}"/><p>-->
</c:forEach>
    <header>

      <div class="header-wrapper">
        <div class="container">
          <div class="header-content">
            <h1>מה בא לך לאכול ?</h1>
            <div class="filters clearfix">
              <ul class="list-inline">
                <li>בחר איזור</li>
                <li class="seporator">&gt;</li>
                <li>בחר מסעדה</li>
                <li class="seporator">&gt;</li>
                <li>הוסף פריטים לסל</li>
                <li class="seporator">&gt;</li>
                <li>האוכל בדרך</li>
              </ul>
              <hr>
              <form id="rest_filter" class="form-inline" method="get" action="${pageContext.request.contextPath}/search-rest">
                <div class="form-group">
                  <select name="where" class="selectpicker" data-width="100%" id="where_to_eat" data-href="${pageContext.request.contextPath}/update-category">
                    <option vlaue="-1">איפה תרצה לאכול ?</option>
                    <c:forEach var="citie" items="${cities}">
                        <option value="${citie}">${citie}</option>
                     </c:forEach>
                  </select>
                </div>
                <div class="form-group">
                  <select class="selectpicker" data-width="100%" id="what_category" name="what">
                    <option>נא לבחור אזור</option>
                  </select>
                </div>
                <div class="form-group post">
                  <button type="submit" class="btn send btn-default">Feed Me</button>
                </div>
              </form>
            </div>  
          </div>
        </div>
      </div>

    </header>





     <section class="rest-section">
      <div class="container">
        <h2>מסעדות חדשות שנוספו</h2>
        <div class="sub-section">
          <ul class="list-inline rests">
            <c:forEach var="rest" items="${restaurants}">
                <li class="rest">
                  <div class="rest-logo">
                      <a href="${pageContext.request.contextPath}/resturent?res_id=${rest.getDbid()}"><img src="${pageContext.request.contextPath}/assets/Uploads/${rest.getLogo()}" alt="placeholder+image"></a>
                  </div>
                  <div class="rest-caption">
                    <h4>${rest.getName()}</h4>
                    <p><small><i class="fa fa-map-marker"></i> ${rest.getStreet()} ${rest.getStreetNum()}, ${rest.getCity()}</small></p>
                    <div class="review hidden">
                      <ul class="list-inline">
                          <li><i class="fa fa-star"></i></li>
                          <li><i class="fa fa-star"></i></li>
                          <li><i class="fa fa-star"></i></li>
                          <li><i class="fa fa-star"></i></li>
                          <li><i class="fa fa-star"></i></li>
                          <li>24 ביקורות</li>
                        </ul>
                    </div> 
                    <a href="${pageContext.request.contextPath}/resturent?res_id=${rest.getDbid()}" class="btn btn-green btn-blcok">הזמן עכשיו</a> 
                  </div>
                </li>
             </c:forEach>
            
<!--            <li class="rest">
              <div class="rest-logo">
                <img src="http://kampaistreetwok.co.il/wp-content/uploads/2014/08/0006_logo_kampai.png" alt="placeholder+image">
              </div>
              <div class="rest-caption">
                <h4>קמפאי</h4>
                <p><small><i class="fa fa-map-marker"></i> לילינבלום 40, תל אביב יפו</small></p>
                <div class="review">
                  <ul class="list-inline">
                      <li><i class="fa fa-star"></i></li>
                      <li><i class="fa fa-star"></i></li>
                      <li><i class="fa fa-star"></i></li>
                      <li><i class="fa fa-star"></i></li>
                      <li><i class="fa fa-star"></i></li>
                      <li>24 ביקורות</li>
                    </ul>
                </div>  
                <a href="#" class="btn btn-green btn-blcok">הזמן עכשיו</a>
              </div>
            </li>-->
          </ul>
            <div class="text-center" > <a  class=" pagination-link btn btn-default btn-lg" href="${pageContext.request.contextPath}?page=" data-current-page="${requestScope.currentPage}" data-num-of-pages="${requestScope.noOfPages}"  >טען עוד</a> </div>
        </div>
      </div>
    </section>

                    
     <section class="gray recommendations visible-lg">
       <div class="container">
         <div class="sub-section">
            <div class="container text-center">
              <div class="row">
                <div class=" col-xs-8 col-sm-12">
                    <!-- Tab panes -->
                    <div class="tab-content" id="tabs-collapse">            
                        <c:forEach var="rank" items="${rankings}" varStatus="count">
                            <c:choose>
                            <c:when test="${count.count == 1}">
                               <div role="tabpanel" class="tab-pane fade in active" id="${rank.getRankId()}">
                            </c:when>
                            <c:otherwise>
                                <div role="tabpanel" class="tab-pane fade in" id="${rank.getRankId()}">
                            </c:otherwise>
                        </c:choose>
                        
                            <div class="tab-inner">                    
                                <p class="lead">${rank.getComment()}</p>
                                <hr>
                                <p><strong class="text-uppercase">${rank.getResturent().getName()}</strong></p>
                                <p><em class="text-capitalize"> להזמנה מהמסעדה</em> <a href="${pageContext.request.contextPath}/resturent?res_id=${rank.getResturent().getDbid()}">לחץ כאן</a></p>                 
                            </div>
                        </div>
                        </c:forEach>
                        
                    </div>
                </div>        
              </div>
            </div>
         </div>
       </div>
     </section>

     <div class="avaters clearfix visible-lg">
        <div class="container">
        <div class="people" role="tabpanel">
              <div class="">
                  <!-- Nav tabs -->
                  <ul class="[ nav nav-justified ]" id="nav-tabs" role="tablist">
                      
                        <c:forEach var="rank" items="${rankings}" varStatus="count">
                            <c:choose>
                            <c:when test="${count.count == 1}">
                               <li role="presentation" class="active">
                            </c:when>
                            <c:otherwise>
                                <li role="presentation" class="">
                            </c:otherwise>
                        </c:choose>
                      
                          <a href="#${rank.getRankId()}" aria-controls="dustin" role="tab" data-toggle="tab">
                              <div class="img-wrap"><img class="img-circle" src="${pageContext.request.contextPath}/assets/Uploads/${rank.getResturent().getLogo()}" /></div>
                              <span class="quote"><i class="fa fa-quote-left"></i></span>
                          </a>
                      </li>
                      </c:forEach>
                  </ul>
              </div>
          </div>
        </div>
     </div>

      
   <%@include file='../website_templates/login_register.jsp'%>
   <%@include file='../website_templates/shopping_cart.jsp'%>
<%@include file='../website_templates/footer.jsp'%>
   