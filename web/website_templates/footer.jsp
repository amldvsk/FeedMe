<jsp:directive.page contentType="text/html;charset=UTF-8"/> 
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="http://maps.googleapis.com/maps/api/js?sensor=false&amp;libraries=places"></script>
    <script src="http://cdn.jsdelivr.net/jquery.validation/1.13.1/jquery.validate.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/bootstrap-select.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/jquery.geocomplete.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/modernizr.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/website_main.js"></script>
    
    <c:if  test="${loginError != null }" >
        <script>
            
            $(document).ready(function() {
                confirm("${loginError}");
            });
            
        </script>
    </c:if>
    
    <script type="text/javascript">
        $('#myTabs a').click(function (e) {
          e.preventDefault()
          $(this).tab('show')
        });
      </script>
      
      
      <script type="text/javascript">
          $('#feed-login form.feed-form').validate({
              rules: {
                  UserPass: {
                                required: true,
                                minlength: 2,
			    },
                  Username: {
                                required: true,
                                minlength: 2,
			    },
              }
          });
          
          $('#feed-signup form.feed-form').validate({
              rules: {
                  firstName: {
                      required: true,
                      minlength: 2,
                  },
                  lastName: {
                      required: true,
                      minlength: 2,
                  },
                  userName: {
                      required: true,
                      minlength: 2,
                  },
                  email: {
                      required: true,
                      email: true
                  },
                  pw: {
                      required: true,
                      minlength: 2,
                  },
                  phone: {
                      required: true,
                      minlength: 10,
                      maxlength: 10,
                      digits: true
                  },
                  bday: {
                      required: true,
                      date: true
                  },
                  address: {
                      required: true,
                      minlength: 2,
                  },
                  city: {
                      required: true,
                      minlength: 2,
                  },
                  street_num: {
                      required: true,
                      digits: true
                  },
                  home_num: {
                      required: true,
                      digits:true
                  }
              }
          });
          
      </script>
      
      
      <script type="text/javascript">
          $(document).ready(function(){
            $('#place_order').validate({
                  rules: {
                      address: {
                          required: true,
                          minlength: 2
                      },
                      fname: {
                          required: true,
                          minlength: 2
                      },
                      phone: {
                          required: true,
                          minlength: 2,
                          digits:true
                      },
                      cvv: {
                          required: true,
                          digits :true,
                           minlength: 3,
                           maxlength: 3
                      },
                      craditNum: {
                          required: true,
                          minlength: 2,
                          digits :true
                      },
                  },
              });
          });
          $('#submit_order').on('click', function() {
              if( $('#place_order').valid() ) {
                  var request = $.ajax({
                    url: $('#place_order').attr('action'),
                    type: "POST",
                    contentType: "application/x-www-form-urlencoded;charset=UTF-8",
                    data: $('#place_order').serialize(),
                  });

                  request.done(function(msg) {
                    console.log(msg);
                    if( msg.status == 1 ) {
                        $('.order-summery').addClass('hidden');
                        $('.order-compleated span.order_nubmber').text(msg.order.orderId);
                        $('.order-compleated').removeClass('hidden');
                    }
                  });

                  request.fail(function(jqXHR, textStatus) {
                    console.log( "Request failed: " + textStatus );
                  });

                  return false;
              }
          });
          
          
      </script>
      
  </body>
</html>
