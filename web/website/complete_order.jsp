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
              <h1>השלמת הזמנה <span><i class="fa fa-shopping-cart"></i></span>   </h1>
          </div>
        </div>
      </div>

    </header>



<section class="place-order-wrapper">
  <div class="container">
      <div class="order-summery" >
      <div class="order-customer-details" >
        <h3>פרטי המשלוח</h3>
        <form    id="place_order" method="POST" accept-charset="UTF-8" action="${pageContext.request.contextPath}/order-complete">
            <h4>פרטים אישיים</h4>
            <div class="add_item_fileds hidden-fileds">
                <div class="form-group clearfix">
                    <div class="col-md-6">
                        <label for="address">כתובת למשלוח</label>
                        <input type="text" class="form-control" name="address" value="${requestScope.customer.getStreet()} ${requestScope.customer.getHouseNum()} ${requestScope.customer.getApartNum()}, ${requestScope.customer.getCity()}" id="address" placeholder="כתובת למשלוח">
                     </div>
                    <div class="col-md-6">
                         <label for="fname">שם פרטי ושם משפחה</label>
                  <input type="text" class="form-control" name="fname" id="fname" value="${requestScope.customer.getFullName()}" placeholder="שם פרטי ושם משפחה">
                     </div>

                </div>
                 <div class="form-group clearfix">
                     <div class="col-md-12">
                        <label for="phone">טלפון לברורים</label>
                        <input type="text" class="form-control" name="phone" id="phone" value="${requestScope.customer.getPhone()}" placeholder="טלפון לברורים">
                     </div>
                </div>
            </div>
            <h4>פרטי כרטיס אשראי</h4>
            <div class="add_item_cradit_card hidden-fileds">
                <div class="form-group clearfix">
                    
                    <div class="col-md-4">
                         <label for="cvv">CVV</label>
                        <input type="text" class="form-control" name="cvv" id="cvv" placeholder="3 ספרות בגב הכרטיס">
                     </div>
                    
                    <div class="col-md-8">
                        <label for="craditNum">מספר כרטיס</label>
                        <input type="text" class="form-control" name="craditNum" id="craditNum" placeholder="מספר כרטיס">
                     </div>

                </div>
                <div class="form-group clearfix">
                    
                    <div class="col-md-12">
                        <label for="">תוקף</label>
                     </div>
                    <div class="col-md-6">
                        <label for="card_mounth">חודש</label>
                        <select class="form-control" name="card_mounth" id="card_mounth">
                            <c:forEach begin="1" end="12" varStatus="loop">
                                <option value="${loop.index}">${loop.index}</option>
                            </c:forEach>
                        </select>
                     </div>
                    <div class="col-md-6">
                         <label for="card_year">שנה</label>
                         <select class="form-control" name="card_year" id="card_year">
                             <c:forEach begin="0" end="4" varStatus="loop">
                                <c:set var="currentYear" value="${year + loop.index}" />
                                <option value="${currentYear}" ${form.workloadYear == currentYear ? 'selected="selected"' : ''}>${currentYear}</option>
                            </c:forEach>
                         </select>
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
          <div class="text-center"><button type="submit" id="submit_order" class="btn btn-success btn-lg">שלח הזמנה</button></div>
      </div>
    </div>
    <div class="order-compleated hidden" >
        <div class="alert alert-success" role="alert">
            הזמנתך התקבלה בהצלחה
             <b>מספר הזמנה <span class="order_nubmber" ></span></b>
        </div>
        
        <div class="order-rating">
            <h4>דירוג חווית הזמנה</h4>
            <hr>
            <div class="text-center">
                <form action="${pageContext.request.contextPath}/rank-order" method="POST"  >
                <ul class="list-inline text-center">
                    <li><p class="star"></p></li>
                    <li><p class="star"></p></li>
                    <li><p class="star"></p></li>
                    <li><p class="star"></p></li>
                    <li><p class="star"></p></li>
                </ul>
                <p class="votes-rating-number"></p>
                <textarea class="form-control" name="comment" placeholder="הוסף ביקורת"></textarea>
                <c:forEach var="cartItem" items="${sessionScope.shoppingCart.getRestItemsMap()}">
                    <input type="hidden" name="restid[]" value="${cartItem.key.getFirstNumber()}"  />
                </c:forEach>
                <input type="hidden" value="1" name="rank" id="rating-stars" />
                 <div class="text-center"><button type="submit" id="submit_rating" class="btn btn-success">שלח דירוג</button></div>
                </form>
            </div>
        </div>
        
    </div>
  </div>  
</section>

      
<%@include file='../website_templates/login_register.jsp'%>
<%@include file='../website_templates/shopping_cart.jsp'%>
<%@include file='../website_templates/footer.jsp'%>
   