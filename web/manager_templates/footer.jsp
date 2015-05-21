<jsp:directive.page contentType="text/html;charset=UTF-8"/> 
</div><!-- End Page Content -->
    </div><!-- End Content Wrapper -->
  </div><!-- End Page Wrapper -->
  
  
  


<!-- Modal -->
<div class="modal fade" id="order_popup_model" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close pull-left" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">הזמנה חדשה שהתקבלה</h4>
      </div>
      <div class="modal-body">
          <ul class="list-unstyled order-summery-list" >
              
          </ul>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
  <script src="http://cdn.jsdelivr.net/jquery.validation/1.13.1/jquery.validate.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
  <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDjy0n0TFNKTZ4S5Hq2w_FVU4E5EglSd6M&language=he"></script>
  <script type="text/javascript" charset="utf8" src="http://cdn.datatables.net/1.10.5/js/jquery.dataTables.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/manager_main.js"></script> 
  <script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/Chart.min.js"></script>
  <script type="text/javascript">
    $('#toggleSidebar').on('click', function(e) {
      e.preventDefault();
      var parent = $('#page-wrapper');
      if(parent.hasClass('open')) {
        parent.removeClass('open');

      } else {
        parent.addClass('open');
      }
    });

    $(document).ready( function () {
        $('.sort-table').DataTable({
          paging: false,
          searching: false,
          "info":     false
        });
    } );
     $('li.sidebar-list a').on('click', function() {
      var parent = $('#page-wrapper');
      if(!parent.hasClass('open')) {
        parent.addClass('open');

      }
    });
  </script>
  
  
  <script type="text/javascript">
      $('#itemMenuCatId').on('change', function() {
          if($(this).val() != '-1') {
              $('.add_item_fileds').removeClass('hidden');
          } else {
              $('.add_item_fileds').addClass('hidden');
          }
      });
  </script>
  
  <script type="text/javascript" >
      
      $('#add_menu_item').validate({
          rules: {
              itemName: {
                            required: true,
                            minlength: 2,
                        },
              itemDescrip:        {
                            required: true,
                            minlength: 2,
                        },
              itemPrice:       {
                            required: true,
                            digits: true
                        },
              logo:    {
                            required: true,
                            minlength: 2,
                        }
          }
      });
      
  </script>
  
  
  
  <script>
      
      if( $('#orders_sum').length > 0 ){
          Chart.defaults.global.responsive = true;
          var ctx = document.getElementById("orders_num").getContext("2d");
            var data = {
                            
                          
                   
                          labels: [
                              <c:forEach var="data"  items="${requestScope.dateAndNumOfOrders}">
                                    "${data.key}",
                                </c:forEach>
                          ],
                          datasets: [
                              {
                                  label: "My First dataset",
                                  fillColor: "rgba(220,220,220,0.5)",
                                  strokeColor: "rgba(220,220,220,0.8)",
                                  highlightFill: "rgba(220,220,220,0.75)",
                                  highlightStroke: "rgba(220,220,220,1)",
                                  data: [
                                            <c:forEach var="data"  items="${requestScope.dateAndNumOfOrders}">
                                                ${data.value},
                                            </c:forEach>
                                        ]
                              },
                          ]
                      };
            var ordersSum = new Chart(ctx).Bar(data);
            
            var ctx = document.getElementById("orders_sum").getContext("2d");
            var data = {
                          labels: [
                              <c:forEach var="data2"  items="${requestScope.dateAndPrice}">
                                    "${data2.key}",
                                </c:forEach>
                          ],
                          datasets: [
                              {
                                  label: "My Second dataset",
                                  fillColor: "rgba(151,187,205,0.5)",
                                  strokeColor: "rgba(151,187,205,0.8)",
                                  highlightFill: "rgba(151,187,205,0.75)",
                                  highlightStroke: "rgba(151,187,205,1)",
                                  data: [
                                            <c:forEach var="data2"  items="${requestScope.dateAndPrice}">
                                                ${data2.value},
                                            </c:forEach>
                                        ]
                              }
                          ]
                      };
            var ordersNum = new Chart(ctx).Bar(data);
      }
      
  </script>
  
  
  
  
  <script>
          
          
          // to keep the session id
        var sessionId = '';

        // name of the client
        var name = ${requestScope.restaurant.getDbid()};


        $(document).ready(function() {

            openSocket();
        });

        var webSocket;

        /**
         * Will open the socket connection
         */
        function openSocket() {
            // Ensures only one connection is open at a time
            if (webSocket !== undefined && webSocket.readyState !== WebSocket.CLOSED) {
                return;
            }

            // Create a new instance of the websocket
            webSocket = new WebSocket("ws://localhost:8084/${pageContext.request.contextPath}/sock?name=" + name);

            /**
             * Binds functions to the listeners for the websocket.
             */
            webSocket.onopen = function(event) {
                if (event.data === undefined)
                    return;

            };

            webSocket.onmessage = function(event) {

                // parsing the json data
                parseMessage(event.data);
            };

            webSocket.onclose = function(event) {
                alert('Error! Connection is closed. Try connecting again.');
            };
        }

        
        /**
         * Closing the socket connection
         */
        function closeSocket() {
            webSocket.close();
        }

        /**
         * Parsing the json message. The type of message is identified by 'flag' node
         * value flag can be self, new, message, exit
         */
        function parseMessage(message) {
            var jObj = $.parseJSON(message);
            
            console.log(jObj);
            
            if( jObj.address != null ) {
                
                
                li_address = '<li><p><b>כתובת</b> '+jObj.address+' </p></li>';
                li_name = '<li><p><b>שם</b> '+jObj.name+' </p></li>';
                li_item = '<li><p><b>מוצר</b> '+jObj.item+' </p></li>';
                
                appendLi = li_address + li_name + li_item;
                $('#order_popup_model').find('.modal-body .order-summery-list').empty();
                $('#order_popup_model').find('.modal-body .order-summery-list').html(appendLi);
                $('#order_popup_model').modal('show');
            }
        }

        

        
          
      </script>
      
      
</body>
</html>