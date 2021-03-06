<jsp:directive.page contentType="text/html;charset=UTF-8"/>
<div id="cd-shadow-layer"></div>
  <div id="cd-cart">
    <h2>סל קניות</h2>
    <ul class="cd-cart-items" data-href="${pageContext.request.contextPath}/update-cart" >
        <c:forEach var="cartItem" items="${sessionScope.shoppingCart.getRestItemsMap()}">
            <li class="item" data-item-id="${cartItem.value.getItemID()}" data-item-price="${cartItem.value.getItemPrice()}" data-rest-id="${cartItem.value.getRestId()}" data-item-name="${cartItem.value.getItemName()}">
                <span class="cd-qty">X ${cartItem.value.getQuantity()}</span> ${cartItem.value.getItemName()}
                <div class="cd-price">${cartItem.value.getItemPrice()} &#8362;</div>
                <a href="#0" class="cd-item-remove cd-img-replace"></a>
              </li>
        </c:forEach>
<!--      <li>
        <span class="cd-qty">1x</span> Product Name
        <div class="cd-price">$9.99</div>
        <a href="#0" class="cd-item-remove cd-img-replace"></a>
      </li>

      <li>
        <span class="cd-qty">2x</span> Product Name
        <div class="cd-price">$19.98</div>
        <a href="#0" class="cd-item-remove cd-img-replace"></a>
      </li>

      <li>
        <span class="cd-qty">1x</span> Product Name
        <div class="cd-price">$9.99</div>
        <a href="#0" class="cd-item-remove cd-img-replace"></a>
      </li>-->
    </ul><!-- cd-cart-items -->
   
    <div class="cd-cart-total">
    <p class="clearfix"> <span>סך הכל</span> <span id="calc_sum">${sessionScope.shoppingCart.calcSum()} &#8362;</span></p>
    </div> <!-- cd-cart-total -->
   
    <a href="${pageContext.request.contextPath}/order-complete" class="checkout-btn">השלם הזמנה</a>
    
    <!-- <p class="cd-go-to-cart"><a href="#0">Go to cart page</a></p> -->
  </div> <!-- cd-cart -->