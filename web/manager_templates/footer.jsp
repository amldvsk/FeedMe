<jsp:directive.page contentType="text/html;charset=UTF-8"/> 
</div><!-- End Page Content -->
    </div><!-- End Content Wrapper -->
  </div><!-- End Page Wrapper -->
  
  
  

<div class="notifications-overlay">
    <ul class="list-unstyled order-summery-list" >
<!--        <li class="notification active">
            <div class="alert alert-info" role="alert">
                <h4>
                    <b>הזמנה חדשה</b>
                </h4>
                <ul class="list-unstyled text-center">
                    <li class="clearfix"><div class="pull-right"><b>כתובת: </b></div> <span>ASDASD</span></li> 
                    <li class="clearfix"><div class="pull-right"><b>שם: </b></div> <span>sdfsdfdsf</span>  </li>
                    <li class="clearfix"><div class="pull-right"><b>פריט: </b></div> <span>adgfdfsg</span> </li>
                </ul>
            </div>
        </li>-->
    </ul>
</div>


  <c:set var="req" value="${pageContext.request}" />
<c:set var="baseURL" value="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}" />
  
  <script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script> 
  <script src="${req.scheme}://cdn.jsdelivr.net/jquery.validation/1.13.1/jquery.validate.min.js"></script>
  <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDjy0n0TFNKTZ4S5Hq2w_FVU4E5EglSd6M&language=he"></script>
  <script type="text/javascript" charset="utf8" src="${req.scheme}://cdn.datatables.net/1.10.5/js/jquery.dataTables.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/manager_main.js"></script> 
  <script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/Chart.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/extand_validator.js"></script>
 

  
  
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
            $('<audio id="chatAudio"><source src="${pageContext.request.contextPath}/assets/sounds/notify.ogg" type="audio/ogg"><source src="${pageContext.request.contextPath}/assets/sounds/notify.mp3" type="audio/mpeg"><source src="${pageContext.request.contextPath}/assets/sounds/notify.wav" type="audio/wav"></audio>').appendTo('body');
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
            webSocket = new WebSocket("ws://${req.serverName}:${req.serverPort}/${pageContext.request.contextPath}/sock?name=" + name);

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
                console.log('Error! Connection is closed. Try connecting again.');
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
                
                
//                li_address = '<li><p><b>כתובת</b> '+jObj.address+' </p></li>';
//                li_name = '<li><p><b>שם</b> '+jObj.name+' </p></li>';
//                li_item = '<li><p><b>מוצר</b> '+jObj.item+' </p></li>';
//                
//                appendLi = li_address + li_name + li_item;
//                $('#order_popup_model').find('.modal-body .order-summery-list').empty();
//                $('#order_popup_model').find('.modal-body .order-summery-list').html(appendLi);
//                $('#order_popup_model').modal('show');
                  
                  
                  li = '<li class="notification">'+
                            '<div class="alert alert-info" role="alert">'+
                                '<h4>'+
                                    '<b>הזמנה חדשה</b>'+
                                '</h4>'+
                                '<ul class="list-unstyled text-center">'+
                                    '<li class="clearfix"><div class="pull-right"><b>כתובת: </b></div> <span>ASDASD</span></li> '+
                                    '<li class="clearfix"><div class="pull-right"><b>שם: </b></div> <span>sdfsdfdsf</span>  </li>'+
                                    '<li class="clearfix"><div class="pull-right"><b>פריט: </b></div> <span>adgfdfsg</span> </li>'+
                                '</ul>'+
                            '</div>'+
                        '</li>';
                  
                  $('.notifications-overlay ul.order-summery-list').append(li);
                  
                  $('.notifications-overlay ul.order-summery-list li').each(function() {
                      $(this).addClass('active');
                  });
                  
                  $(document).attr("title", $(document).attr("title") + "התקבלה הזמנה חדשה ");
                  
                  $('#chatAudio')[0].play();
            }
        }

        

        $('body').on('click', '.notifications-overlay ul.order-summery-list li', function() {
            $(this).remove();
            $(document).attr("title", "FeedMe - ניהול");
        });
          
      </script>
      
      
</body>
</html>