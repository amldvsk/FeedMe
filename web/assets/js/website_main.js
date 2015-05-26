jQuery(document).ready(function($){

  $("#signup-address").geocomplete();

  var $form_modal = $('.feed-user-modal'),
    $form_login = $form_modal.find('#feed-login'),
    $form_signup = $form_modal.find('#feed-signup'),
    $form_forgot_password = $form_modal.find('#feed-reset-password'),
    $form_modal_tab = $('.feed-switcher'),
    $tab_login = $form_modal_tab.children('li').eq(0).children('a'),
    $tab_signup = $form_modal_tab.children('li').eq(1).children('a'),
    $forgot_password_link = $form_login.find('.feed-form-bottom-message a'),
    $back_to_login_link = $form_forgot_password.find('.feed-form-bottom-message a'),
    $main_nav = $('nav');

  //open modal
  $main_nav.on('click', function(event){

    // if( $(event.target).is($main_nav) ) {
    //   // on mobile open the submenu
    //   $(this).children('ul').toggleClass('is-visible');
    // } else {
    //   // on mobile close submenu
    //   $main_nav.children('ul').removeClass('is-visible');
    //   //show modal layer
    //   $form_modal.addClass('is-visible'); 
    //   //show the selected form
    //   ( $(event.target).is('.feed-signup') ) ? signup_selected() : login_selected();
    // }

    if( $(event.target).is('.feed-signup') ) {
      $form_modal.addClass('is-visible'); 
      signup_selected();
    } else if ( $(event.target).is('.feed-signin') ) {
      $form_modal.addClass('is-visible'); 
      login_selected();
    }

  });

  //close modal
  $('.feed-user-modal').on('click', function(event){
    if( $(event.target).is($form_modal) || $(event.target).is('.feed-close-form') ) {
      $form_modal.removeClass('is-visible');
      $('body').removeClass('removeScroll');
    } 
  });
  //close modal when clicking the esc keyboard button
  $(document).keyup(function(event){
      if(event.which=='27'){
        $form_modal.removeClass('is-visible');
      }
    });

  //switch from a tab to another
  $form_modal_tab.on('click', function(event) {
    event.preventDefault();
    ( $(event.target).is( $tab_login ) ) ? login_selected() : signup_selected();
  });

  //hide or show password
  $('.hide-password').on('click', function(){
    var $this= $(this),
      $password_field = $this.prev('input');
    
    ( 'password' == $password_field.attr('type') ) ? $password_field.attr('type', 'text') : $password_field.attr('type', 'password');
    ( 'Hide' == $this.text() ) ? $this.text('Show') : $this.text('Hide');
    //focus and move cursor to the end of input field
    $password_field.putCursorAtEnd();
  });

  //show forgot-password form 
  $forgot_password_link.on('click', function(event){
    event.preventDefault();
    forgot_password_selected();
  });

  //back to login from the forgot-password form
  $back_to_login_link.on('click', function(event){
    event.preventDefault();
    login_selected();
  });

  function login_selected(){
    $form_login.addClass('is-selected');
    $form_signup.removeClass('is-selected');
    $form_forgot_password.removeClass('is-selected');
    $tab_login.addClass('selected');
    $tab_signup.removeClass('selected');
    $('body').addClass('removeScroll');
  }

  function signup_selected(){
    $form_login.removeClass('is-selected');
    $form_signup.addClass('is-selected');
    $form_forgot_password.removeClass('is-selected');
    $tab_login.removeClass('selected');
    $tab_signup.addClass('selected');
    $('body').addClass('removeScroll');
  }

  function forgot_password_selected(){
    $form_login.removeClass('is-selected');
    $form_signup.removeClass('is-selected');
    $form_forgot_password.addClass('is-selected');
  }

  //REMOVE THIS - it's just to show error messages 
//  $form_login.find('input[type="submit"]').on('click', function(event){
//    event.preventDefault();
//    $form_login.find('input[type="email"]').toggleClass('has-error').next('span').toggleClass('is-visible');
//  });
//  $form_signup.find('input[type="submit"]').on('click', function(event){
//    event.preventDefault();
//    $form_signup.find('input[type="email"]').toggleClass('has-error').next('span').toggleClass('is-visible');
//  });
    
   

  //IE9 placeholder fallback
  //credits http://www.hagenburger.net/BLOG/HTML5-Input-Placeholder-Fix-With-jQuery.html
  if(!Modernizr.input.placeholder){
    $('[placeholder]').focus(function() {
      var input = $(this);
      if (input.val() == input.attr('placeholder')) {
        input.val('');
        }
    }).blur(function() {
      var input = $(this);
        if (input.val() == '' || input.val() == input.attr('placeholder')) {
        input.val(input.attr('placeholder'));
        }
    }).blur();
    $('[placeholder]').parents('form').submit(function() {
        $(this).find('[placeholder]').each(function() {
        var input = $(this);
        if (input.val() == input.attr('placeholder')) {
          input.val('');
        }
        })
    });
  }

});


//credits http://css-tricks.com/snippets/jquery/move-cursor-to-end-of-textarea-or-input/
jQuery.fn.putCursorAtEnd = function() {
  return this.each(function() {
      // If this function exists...
      if (this.setSelectionRange) {
          // ... then use it (Doesn't work in IE)
          // Double the length because Opera is inconsistent about whether a carriage return is one character or two. Sigh.
          var len = $(this).val().length * 2;
          this.setSelectionRange(len, len);
      } else {
        // ... otherwise replace the contents with itself
        // (Doesn't work in Google Chrome)
          $(this).val($(this).val());
      }
  });
};








jQuery(document).ready(function($){
  //if you change this breakpoint in the style.css file (or _layout.scss if you use SASS), don't forget to update this value as well
  var $L = 1200,
    $menu_navigation = $('#main-nav'),
    $cart_trigger = $('#cd-cart-trigger'),
    $hamburger_icon = $('#cd-hamburger-menu'),
    $lateral_cart = $('#cd-cart'),
    $shadow_layer = $('#cd-shadow-layer');

  //open lateral menu on mobile
  $hamburger_icon.on('click', function(event){
    event.preventDefault();
    //close cart panel (if it's open)
    $lateral_cart.removeClass('speed-in');
    toggle_panel_visibility($menu_navigation, $shadow_layer, $('body'));
  });

  //open cart
  $cart_trigger.on('click', function(event){
    event.preventDefault();
    //close lateral menu (if it's open)
    $menu_navigation.removeClass('speed-in');
    toggle_panel_visibility($lateral_cart, $shadow_layer, $('body'));
  });

  //close lateral cart or lateral menu
  $shadow_layer.on('click', function(){
    $shadow_layer.removeClass('is-visible');
    // firefox transitions break when parent overflow is changed, so we need to wait for the end of the trasition to give the body an overflow hidden
    if( $lateral_cart.hasClass('speed-in') ) {
      $lateral_cart.removeClass('speed-in').on('webkitTransitionEnd otransitionend oTransitionEnd msTransitionEnd transitionend', function(){
        $('body').removeClass('overflow-hidden');
      });
      $menu_navigation.removeClass('speed-in');
    } else {
      $menu_navigation.removeClass('speed-in').on('webkitTransitionEnd otransitionend oTransitionEnd msTransitionEnd transitionend', function(){
        $('body').removeClass('overflow-hidden');
      });
      $lateral_cart.removeClass('speed-in');
    }
  });

  //move #main-navigation inside header on laptop
  //insert #main-navigation after header on mobile
  move_navigation( $menu_navigation, $L);
  $(window).on('resize', function(){
    move_navigation( $menu_navigation, $L);
    
    if( $(window).width() >= $L && $menu_navigation.hasClass('speed-in')) {
      $menu_navigation.removeClass('speed-in');
      $shadow_layer.removeClass('is-visible');
      $('body').removeClass('overflow-hidden');
    }

  });
});

function toggle_panel_visibility ($lateral_panel, $background_layer, $body) {
  if( $lateral_panel.hasClass('speed-in') ) {
    // firefox transitions break when parent overflow is changed, so we need to wait for the end of the trasition to give the body an overflow hidden
    $lateral_panel.removeClass('speed-in').one('webkitTransitionEnd otransitionend oTransitionEnd msTransitionEnd transitionend', function(){
      $body.removeClass('overflow-hidden');
    });
    $background_layer.removeClass('is-visible');

  } else {
    $lateral_panel.addClass('speed-in').one('webkitTransitionEnd otransitionend oTransitionEnd msTransitionEnd transitionend', function(){
      $body.addClass('overflow-hidden');
    });
    $background_layer.addClass('is-visible');
  }
}

function move_navigation( $navigation, $MQ) {
  if ( $(window).width() >= $MQ ) {
    $navigation.detach();
    $navigation.appendTo('header');
  } else {
    $navigation.detach();
    $navigation.insertAfter('header');
  }
}



$('.resturen_menu_items li.item a.add-to-cart-btn').on('click', function(e) {
    e.preventDefault();
    var parent = $(this).parents('li.item');
    cart_url = $('ul.resturen_menu_items').data('href');
    //console.log( cart_url +" "+ parent.data('item-name') +" "+ parent.data('item-id') +" "+ parent.data('item-price') +" "+ parent.data('rest-id'));
    
    item_id = parent.data('item-id');
    rest_id = parent.data('rest-id');
    
    var request = $.ajax({
          url: cart_url,
          type: "POST",
          contentType: "application/x-www-form-urlencoded;charset=UTF-8",
          data: {itemid : item_id, restid :rest_id, action : 1 },
        });

        request.done(function(msg) {
//          console.log(msg);
          var cartItems = $('.cd-cart-items');
          appendToCart(msg, cartItems);
          $('#calc_sum').html("&#8362;"+msg.cart_sum);
          $('#cd-cart-trigger').trigger('click');
          
        });

        request.fail(function(jqXHR, textStatus) {
          console.log( "Request failed: " + textStatus );
        });
    
});



$('body').on('click', '.cd-cart-items li.item a.cd-item-remove', function(e) {
    e.preventDefault();
    var parent = $(this).parents('li.item');
    cart_url = $('ul.cd-cart-items').data('href');
    //console.log( cart_url +" "+ parent.data('item-name') +" "+ parent.data('item-id') +" "+ parent.data('item-price') +" "+ parent.data('rest-id'));
    
    item_id = parent.data('item-id');
    rest_id = parent.data('rest-id');
    
    var request = $.ajax({
          url: cart_url,
          type: "POST",
          contentType: "application/x-www-form-urlencoded;charset=UTF-8",
          data: {itemid : item_id, restid :rest_id, action : 2 },
        });

        request.done(function(msg) {
//          console.log(msg);
          var cartItems = $('.cd-cart-items');
          appendToCart(msg, cartItems);
          $('#calc_sum').html("&#8362;"+msg.cart_sum);
          
        });

        request.fail(function(jqXHR, textStatus) {
          console.log( "Request failed: " + textStatus );
        });
    
});


function appendToCart(msg, cart) {
    cart.empty();
    $.each(msg.cart.restItemsMap, function(i, item) {
        li = '<li class="item" data-item-id="'+item.itemID+'" data-item-price="'+item.itemPrice+'" data-rest-id="'+item.rest_id+'" data-item-name="'+item.itemName+'"><span class="cd-qty">X'+item.quantity+'</span> '+item.itemName+'<div class="cd-price">'+item.itemPrice+' &#8362;</div><a href="#0" class="cd-item-remove cd-img-replace"></a></li>';
        cart.append(li);
    });
}




jQuery(document).ready(function($){
  var $timeline_block = $('.cd-timeline-block');

  //hide timeline blocks which are outside the viewport
  $timeline_block.each(function(){
    if($(this).offset().top > $(window).scrollTop()+$(window).height()*0.75) {
      $(this).find('.cd-timeline-img, .cd-timeline-content').addClass('is-hidden');
    }
  });

  //on scolling, show/animate timeline blocks when enter the viewport
  $(window).on('scroll', function(){
    $timeline_block.each(function(){
      if( $(this).offset().top <= $(window).scrollTop()+$(window).height()*0.75 && $(this).find('.cd-timeline-img').hasClass('is-hidden') ) {
        $(this).find('.cd-timeline-img, .cd-timeline-content').removeClass('is-hidden').addClass('bounce-in');
      }
    });
  });
});



$('.order-rating ul li').hover(
        
        function() {
            $(this).prevAll().andSelf().find('p.star').addClass('active');
            $(this).nextAll().find('p.star').removeClass('active');
        }, function() {
            $(this).prevAll().andSelf().find('p.star').removeClass('active');
            setVotes($(this));
        }
        
);


$('.order-rating ul li').on('click', function() {
    $(this).prevAll().andSelf().find('p.star').addClass('active');
    $(this).nextAll().find('p.star').removeClass('active');
});


var rating_text = ['גרוע', 'לא אהבתי', 'היה בסדר', 'אהבתי', 'אהבתי מאוד'];

function setVotes(widget) {
    widget.prevAll().andSelf().find('p.star').addClass('active');
    widget.nextAll().find('p.star').removeClass('active');
    rating = widget.parent().find('li p.star.active').length;
    $('.votes-rating-number').text(rating_text[rating-1]);
    $('#rating-stars').val(rating);
    
}


$('#myTabs a').click(function (e) {
    e.preventDefault()
    $(this).tab('show')
});




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
            minlength: 6,
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



$('#user_update').validate({
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
            minlength: 6,
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
              minlength: 6,
              maxlength : 10,
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
            $('.cd-cart-items').empty();
            $('#calc_sum').html("&#8362; 0.0");
            $.each(msg.order.restItemsMap,function(key, value) {
                console.log(value.rest_id +" "+ value.itemName +" "+msg.order.CustomerFullName + " " + msg.order.CustomerAdress);
                sendMessageToServer(value.rest_id, value.itemName, msg.order.CustomerFullName, msg.order.CustomerAdress);
            });
        }
      });

      request.fail(function(jqXHR, textStatus) {
        console.log( "Request failed: " + textStatus );
      });

      return false;
  }
});




$('#where_to_eat').on('change', function() {
    city_choose = $(this).val();
    if( city_choose == "איפה תרצה לאכול ?" ) {
        $('#what_category').empty();
        $('#what_category').append('<option value="-1">נא לבחור אזור</option>');
        $('#what_category').selectpicker('refresh');
        return;
    }
    url = $(this).data('href');
    var request = $.ajax({
        url: url,
        type: "POST",
        contentType: "application/x-www-form-urlencoded;charset=UTF-8",
        data: { city : city_choose },
      });

    request.done(function(msg) {
      console.log(msg);
      $('#what_category').empty();
      $('#what_category').append('<option value="-1">מה תרצה לאכול ?</option>');
      $.each(msg.categorys, function(key, value) {
        $('#what_category').append('<option value="'+value.cat_id+'">'+value.cat_name+'</option>');
      });
      $('#what_category').selectpicker('refresh');
    });

    request.fail(function(jqXHR, textStatus) {
      console.log( "Request failed: " + textStatus );
    });
      
    
});


$('#rest_filter').on('submit', function() {
    if( $('#what_category').val().trim() == "נא לבחור אזור" ||  $('#what_category').val().trim() == "-1" || $('#where_to_eat').val().trim() == "-1" ) 
        return false;
    else
        return true;
});