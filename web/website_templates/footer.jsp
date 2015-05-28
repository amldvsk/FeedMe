<jsp:directive.page contentType="text/html;charset=UTF-8"/> 

<c:set var="req" value="${pageContext.request}" />
<c:set var="baseURL" value="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}" />

<div id="loader-wrapper" >
    <div class="table-view">
        <div class="loader" ><img src="${pageContext.request.contextPath}/assets/img/oval.svg" alt=""></div>
    </div>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script> 
    <script src="${req.scheme}://cdn.jsdelivr.net/jquery.validation/1.13.1/jquery.validate.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/bootstrap-select.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/jquery.geocomplete.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/modernizr.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/website_main.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/extand_validator.js"></script>
    
    <c:if  test="${loginError != null }" >
        <script>
            
            $(document).ready(function() {
                alert("${loginError}");
            });
            
        </script>
        <c:remove var="loginError" scope="session" />
    </c:if>
    
            
    <c:if  test="${registerError != null }" >
        <script>
            
            $(document).ready(function() {
                alert("${registerError}");
            });
            
        </script>
        <c:remove var="registerError" scope="session" />
    </c:if>

 
      

      
      <script>
          
          
          // to keep the session id
        var sessionId = '';

        // name of the client
        var name = '';

        

        $(document).ready(function() {
            if($('.place-order-wrapper').length > 0)
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
            
            <c:choose>
                    <c:when test="${req.scheme=='https'}">
                    webSocket = new WebSocket("wss://${req.serverName}:${req.serverPort}/${pageContext.request.contextPath}/sock?name=customer");
                    </c:when>

                    <c:otherwise>
                        webSocket = new WebSocket("ws://${req.serverName}:${req.serverPort}/${pageContext.request.contextPath}/sock?name=customer");
                    </c:otherwise>
              </c:choose>
            /**
             * Binds functions to the listeners for the websocket.
             */
            webSocket.onopen = function(event) {
                if (event.data === undefined)
                    return;

            };

            webSocket.onmessage = function(event) {

                // parsing the json data
                //parseMessage(event.data);
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
         * Sending message to socket server message will be in json format
         */
        function sendMessageToServer(rest_id, item, name, address) {
            var json = '{""}';

            // preparing json object
            var myObject = new Object();
            myObject.order = new Object();
            myObject.order.item = item;
            myObject.order.name = name;
            myObject.order.address = address;
            myObject.order.rest_id = rest_id;

            // converting json object to json string
            json = JSON.stringify(myObject);

            // sending message to server
            webSocket.send(json);
        }
          
      </script>
      
      
  </body>
</html>
