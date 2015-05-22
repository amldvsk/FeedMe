 <jsp:directive.page contentType="text/html;charset=UTF-8"/>
 </div><!-- End Page Content -->
    </div><!-- End Content Wrapper -->
</div><!-- End Page Wrapper -->
  <script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script> 
  <script src="http://cdn.jsdelivr.net/jquery.validation/1.13.1/jquery.validate.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
  <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDjy0n0TFNKTZ4S5Hq2w_FVU4E5EglSd6M&language=he"></script>
  <script type="text/javascript" charset="utf8" src="http://cdn.datatables.net/1.10.5/js/jquery.dataTables.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/admin_main.js"></script> 
  <script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
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
          $('#select_category').empty();
          $('#select_category').append('<option value="-1">בחר קטגוריה</option>');
          $.each(msg.categories, function(id, value) {
            $('#select_category').append('<option value="'+value.cat_id+'">'+value.cat_name+'</option>');
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
  
  <script type="text/javascript">
      
      $("#make_manager").validate({
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
              pw:        {
                            required: true,
                            minlength: 2,
                        },
              phone:       {
                            required: true,
                            minlength: 2,
                            maxlength : 10,
                            digit : true
                        },
              email:    {
                            required: true,
                            minlength: 2,
                            email:true
                        }
          }
      });
      
  </script>
  
  <script type="text/javascript" >
      $('#create_resturent').validate({
          rules: {
              newName: {
                            required: true,
                            minlength: 2,
                        },
              phone: {
                            required: true,
                            minlength: 2,
                            digit:true
                        },  
              street: {
                            required: true,
                            minlength: 2,
                        },
              streetNum:        {
                            required: true,
                            minlength: 2,
                        },
              deliveryPrice:    {
                            required: true,
                            minlength: 2,
                        },
              minOrder:       {
                            required: true,
                            minlength: 2,                            
                        },
              estimatedTimeDel:       {
                            required: true,
                            minlength: 2,                            
                        },
              logo:       {
                            required: true,
                            minlength: 2,                            
                        },
          }
      });
  </script>
  
</body>
</html>