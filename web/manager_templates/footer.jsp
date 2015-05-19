<jsp:directive.page contentType="text/html;charset=UTF-8"/> 
</div><!-- End Page Content -->
    </div><!-- End Content Wrapper -->
  </div><!-- End Page Wrapper -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
  <script src="http://cdn.jsdelivr.net/jquery.validation/1.13.1/jquery.validate.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
  <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDjy0n0TFNKTZ4S5Hq2w_FVU4E5EglSd6M&language=he"></script>
  <script type="text/javascript" charset="utf8" src="http://cdn.datatables.net/1.10.5/js/jquery.dataTables.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/manager_main.js"></script> 
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
  
</body>
</html>