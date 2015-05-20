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
              <h1>קצת עלינו  </h1>
          </div>
        </div>
      </div>

    </header>



<section id="cd-timeline" class="cd-container">
        <div class="cd-timeline-block">
          <div class="cd-timeline-img cd-picture">
            <img src="${pageContext.request.contextPath}/assets/img/profiles/adi.jpg" alt="Picture" class="img-circle">
          </div> <!-- cd-timeline-img -->

          <div class="cd-timeline-content">
            <h2>Adi Moldavski</h2>
            <h4>Team Leader & Client Side</h4>
            <ul class="list-unstyled">
              <li><p><i class="fa fa-envelope"></i> &nbsp; amldvsk@gmail.com</p></li>
            </ul> 
          </div> <!-- cd-timeline-content -->
        </div> <!-- cd-timeline-block -->

        <div class="cd-timeline-block">
          <div class="cd-timeline-img cd-movie is-hidden">
            <img src="${pageContext.request.contextPath}/assets/img/profiles/ofir.jpg" alt="Movie" class="img-circle">
          </div> <!-- cd-timeline-img -->

          <div class="cd-timeline-content is-hidden">
            <h2>Ofir Vaknin</h2>
            <h4>Project Architect</h4>
            <ul class="list-unstyled">
              <li><p><i class="fa fa-envelope"></i> &nbsp; asdas@asdasdc.om</p></li>
            </ul> 
          </div> <!-- cd-timeline-content -->
        </div> <!-- cd-timeline-block -->

        <div class="cd-timeline-block">
          <div class="cd-timeline-img cd-picture is-hidden">
            <img src="${pageContext.request.contextPath}/assets/img/profiles/nadav.jpg" alt="Picture" class="img-circle">
          </div> <!-- cd-timeline-img -->

          <div class="cd-timeline-content is-hidden">
            <h2>Nadav Bismuth</h2>
            <h4>Server Side & Database</h4>
            <ul class="list-unstyled">
              <li><p><i class="fa fa-envelope"></i> &nbsp; asdas@asdasdc.om</p></li>
            </ul> 
          </div> <!-- cd-timeline-content -->
        </div> <!-- cd-timeline-block -->

        <div class="cd-timeline-block">
          <div class="cd-timeline-img cd-location is-hidden">
            <img src="${pageContext.request.contextPath}/assets/img/profiles/idan.jpg" alt="Location"  class="img-circle">
          </div> <!-- cd-timeline-img -->

          <div class="cd-timeline-content is-hidden">
            <h2>Idan Benyamin</h2>
            <h4>Server Side & Database</h4>
            <ul class="list-unstyled">
              <li><p><i class="fa fa-envelope"></i> &nbsp; asdas@asdasdc.om</p></li>
            </ul> 
          </div> <!-- cd-timeline-content -->
        </div> <!-- cd-timeline-block -->

        <div class="cd-timeline-block">
          <div class="cd-timeline-img cd-location is-hidden">
            <img src="${pageContext.request.contextPath}/assets/img/profiles/david.jpg" alt="Location"  class="img-circle">
          </div> <!-- cd-timeline-img -->

          <div class="cd-timeline-content is-hidden">
            <h2>David Lazarev</h2>
            <h4>Server Side</h4>
            <ul class="list-unstyled">
              <li><p><i class="fa fa-envelope"></i> &nbsp; asdas@asdasdc.om</p></li>
            </ul> 
          </div> <!-- cd-timeline-content -->
        </div> <!-- cd-timeline-block -->

        <div class="cd-timeline-block">
          <div class="cd-timeline-img cd-movie" is-hidden>
            <img src="${pageContext.request.contextPath}/assets/img/profiles/tamir.jpg" alt="Movie" class="img-circle">
          </div> <!-- cd-timeline-img -->

          <div class="cd-timeline-content is-hidden">
            <h2>Tamir Kratz</h2>
            <h4>Server Side</h4>
            <ul class="list-unstyled">
              <li><p><i class="fa fa-envelope"></i> &nbsp; asdas@asdasdc.om</p></li>
            </ul> 
          </div> <!-- cd-timeline-content -->
        </div> <!-- cd-timeline-block -->
      </section> <!-- cd-timeline -->

      
<%@include file='../website_templates/login_register.jsp'%>
<%@include file='../website_templates/shopping_cart.jsp'%>
<%@include file='../website_templates/footer.jsp'%>
   