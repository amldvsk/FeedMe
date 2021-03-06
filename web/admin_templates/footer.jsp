 <jsp:directive.page contentType="text/html;charset=UTF-8"/>
 </div><!-- End Page Content -->
    </div><!-- End Content Wrapper -->
</div><!-- End Page Wrapper -->


<c:set var="req" value="${pageContext.request}" />
<c:set var="baseURL" value="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}" />

  <script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script> 
  <script src="${req.scheme}://cdn.jsdelivr.net/jquery.validation/1.13.1/jquery.validate.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
  <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDjy0n0TFNKTZ4S5Hq2w_FVU4E5EglSd6M&language=he"></script>
  <script type="text/javascript" charset="utf8" src="${req.scheme}://cdn.datatables.net/1.10.5/js/jquery.dataTables.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/admin_main.js"></script> 
  <script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/extand_validator.js"></script>
  
  
  
            
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
  
  <script>
      
      $('#addCategoryForm').on('submit', function() {
        if( $('#inputEmail3').val().trim().length == 0 ) {
            alert("נא להכניס שם");
            return false;
        }
            
        var url = $(this).attr('action');
        var dataCat = $(this).serialize();
        var request = $.ajax({
          url: url,
          type: "POST",
          contentType: "application/x-www-form-urlencoded;charset=UTF-8",
          data: dataCat,
        });

        request.done(function(msg) {
          console.log(msg);
          $('#addCategoryForm')[0].reset();
          $('#category').empty();
          $('#category').append('<option value="-1">בחר קטגוריה</option>');
          $.each(msg.categories, function(id, value) {
            $('#category').append('<option value="'+value.cat_id+'">'+value.cat_name+'</option>');
          });

          if(msg.status == true) {
            $('.add-category-model').modal('hide');
          }
        });

        request.fail(function(jqXHR, textStatus) {
          console.log( "Request failed: " + textStatus );
        });

        return false;
      });
      
  </script>

  
</body>
</html>