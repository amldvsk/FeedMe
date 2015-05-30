<jsp:directive.page contentType="text/html;charset=UTF-8"/>
<%@include file='../website_templates/head.jsp'%>
<%@include file='../website_templates/navigation.jsp'%>


<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate var="year" value="${now}" pattern="yyyy" />

<c:forEach var="i" begin="1" end="5">
<!--Item <c:out value="${i}"/><p>-->
</c:forEach>

    <header class="not-index">

      <div class="header-wrapper">
        <div class="container">
          <div class="header-content">
              <h1>חיפוש מסעדה  </h1>
          </div>
        </div>
      </div>

    </header>



<section class="rest-section">
      <div class="container">
        <h2></h2>
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
            <c:choose>
                <c:when test="${currentPage < noOfPages}">
                   <div class="text-center" > <a  class=" pagination-link btn btn-default btn-lg" href="${pageContext.request.contextPath}search-rest/?where=${param["where"]}&what=${param["what"]}&page=" data-current-page="${requestScope.currentPage}" data-num-of-pages="${requestScope.noOfPages}"  >טען עוד</a> </div>
                </c:when>
                <c:otherwise>
                    <div class="text-center hidden" > <a  class=" pagination-link btn btn-default btn-lg" href="${pageContext.request.contextPath}/search-rest?where=${param["where"]}&what=${param["what"]}&page=" data-current-page="${requestScope.currentPage}" data-num-of-pages="${requestScope.noOfPages}"  >טען עוד</a> </div>
                </c:otherwise>
            </c:choose>
            
        </div>
      </div>
    </section>

      
<%@include file='../website_templates/login_register.jsp'%>
<%@include file='../website_templates/shopping_cart.jsp'%>
<%@include file='../website_templates/footer.jsp'%>
   