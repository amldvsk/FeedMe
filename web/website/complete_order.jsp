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
              <h1>השלמת הזמנה <span><i class="fa fa-shopping-cart"></i></span>   </h1>
          </div>
        </div>
      </div>

    </header>



<section class="place-order-wrapper">
  <div class="container">

      <div class="order-customer-details" >
        <h3>פרטי המשלוח</h3>
        <form    id="place_order"      enctype="multipart/form-data" method="POST" accept-charset="UTF-8" action="${pageContext.request.contextPath}/menu-item-management">
            <h4>פרטים אישיים</h4>
            <div class="add_item_fileds hidden-fileds">
                <div class="form-group clearfix">
                    <div class="col-md-6">
                        <label for="itemName">כתובת למשלוח</label>
                        <input type="text" class="form-control" name="itemName" id="itemName" placeholder="כתובת למשלוח">
                     </div>
                    <div class="col-md-6">
                         <label for="itemPrice">שם פרטי ושם משפחה</label>
                  <input type="text" class="form-control" name="itemPrice" id="itemPrice" placeholder="שם פרטי ושם משפחה">
                     </div>

                </div>
                 <div class="form-group clearfix">
                     <div class="col-md-12">
                        <label for="itemDescrip">טלפון לברורים</label>
                        <input type="text" class="form-control" name="itemDescrip" id="itemDescrip" placeholder="טלפון לברורים">
                     </div>
                </div>
            </div>
            <h4>פרטי כרטיס אשראי</h4>
            <div class="add_item_fileds hidden-fileds">
                <div class="form-group clearfix">
                    <div class="col-md-6">
                        <label for="itemName">כתובת למשלוח</label>
                        <input type="text" class="form-control" name="itemName" id="itemName" placeholder="כתובת למשלוח">
                     </div>
                    <div class="col-md-6">
                         <label for="itemPrice">שם פרטי ושם משפחה</label>
                  <input type="text" class="form-control" name="itemPrice" id="itemPrice" placeholder="שם פרטי ושם משפחה">
                     </div>

                </div>
                 <div class="form-group clearfix">
                     <div class="col-md-12">
                        <label for="itemDescrip">טלפון לברורים</label>
                        <input type="text" class="form-control" name="itemDescrip" id="itemDescrip" placeholder="טלפון לברורים">
                     </div>
                </div>
            </div>
        </form>
      </div>
      
      <hr>
      <div class="order-cart" >
        <h3>סל הקניות</h3>
        
        
        <ul class="cart-items list-unstyled clearfix" >
            <li class="clearfix cart-header">
                <div class="col-md-2" ><h5>מחיר</h5></div>
                <div class="col-md-4" ><h5>פירוט</h5></div>
                <div class="col-md-4" ><h5>תמונה</h5></div>
                <div class="col-md-2" ><h5>כמות</h5></div>
            </li>
            <li class="clearfix cart-item hidden">
                <div class="col-md-2" >
                    <p><small>39 &#8362;</small></p>
                </div>
                <div class="col-md-4" >
                    <h4><b>נאגטס עוף</b></h4>
                    <p class="muted" ><small>בקרמל תפוזי דם פיקנט </small></p>
                </div>
                <div class="col-md-4" >
                    <img height="100" weight="100"  src="https://www.mosesrest.co.il/filestock/img/img_1412763050812-0.jpg" class="img-rounded" />
                </div>
                <div class="col-md-2" >
                    <p><small>3</small></p>
                </div>
            </li>
            
            <c:forEach var="cartItem" items="${sessionScope.shoppingCart.getRestItemsMap()}">
                <li class="cart-item item clearfix" data-item-id="${cartItem.value.getItemID()}" data-item-price="${cartItem.value.getItemPrice()}" data-rest-id="${cartItem.value.getRestId()}" data-item-name="${cartItem.value.getItemName()}">
                    <div class="col-md-2" >
                    <p><small>${cartItem.value.getItemPrice()} &#8362;</small></p>
                    </div>
                    <div class="col-md-4" >
                        <h4><b>${cartItem.value.getItemName()}</b></h4>
                        <p class="muted" ><small>${cartItem.value.getItemDescription()}</small></p>
                    </div>
                    <div class="col-md-4" >
                        <img height="100" weight="100"  src="${pageContext.request.contextPath}/assets/Uploads/${cartItem.value.getItemImagePath()}" class="img-rounded" />
                    </div>
                    <div class="col-md-2" >
                        <p><small>${cartItem.value.getQuantity()}</small></p>
                    </div>
                  </li>
            </c:forEach>
            <li class="clearfix cart-footer">
                <div class="col-md-2" >
                    <h4><b><span>סך הכל</span> <span>${sessionScope.shoppingCart.calcSum()} &#8362;</span></b></h4>
                </div>
                <div class="col-md-4" ></div>
                <div class="col-md-4" ></div>
                <div class="col-md-2" ></div>
            </li>
        </ul>
        
      </div>
      <div class="cart-btn clearfix" >
          <div class="text-center"><button type="submit" class="btn btn-success btn-lg">שלח הזמנה</button></div>
      </div>
  </div>  
</section>

      
<%@include file='../website_templates/login_register.jsp'%>
<%@include file='../website_templates/shopping_cart.jsp'%>
<%@include file='../website_templates/footer.jsp'%>
   