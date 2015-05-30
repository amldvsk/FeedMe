jQuery(document).ready(function($){
  //set your google maps parameters
  
  
  $('.sort-table').DataTable({
    paging: false,
    searching: false,
    "info":     false
  });
        
  var latitude = 31.259680,
    longitude =  34.782647,
    map_zoom = 8;

  //google map custom marker icon - .png fallback for IE11
  var is_internetExplorer11= navigator.userAgent.toLowerCase().indexOf('trident') > -1;
  var marker_url = ( is_internetExplorer11 ) ? '/assets/img/cd-icon-location.png' : 'assets/img/cd-icon-location.svg';
    
  //define the basic color of your map, plus a value for saturation and brightness
  var main_color = '#2d313f',
    saturation_value= -20,
    brightness_value= 5;

  
    
  //set google map options
  var map_options = {
        center: new google.maps.LatLng(latitude, longitude),
        zoom: map_zoom,
        panControl: false,
        //zoomControl: false,
        //mapTypeControl: false,
        streetViewControl: false,
        mapTypeId: google.maps.MapTypeId.ROADMAP,
        //scrollwheel: false,
        // styles: style,
    }
    //inizialize the map
  var map = new google.maps.Map(document.getElementById('google-container'), map_options);
  //add a custom marker to the map        
//  var marker = new google.maps.Marker({
//      position: new google.maps.LatLng(latitude, longitude),
//      map: map,
//      visible: true,
//    icon: marker_url,
//  });

  //add custom buttons for the zoom-in/zoom-out on the map
//  function CustomZoomControl(controlDiv, map) {
//    //grap the zoom elements from the DOM and insert them in the map 
//      var controlUIzoomIn= document.getElementById('cd-zoom-in'),
//        controlUIzoomOut= document.getElementById('cd-zoom-out');
//      controlDiv.appendChild(controlUIzoomIn);
//      controlDiv.appendChild(controlUIzoomOut);
//
//    // Setup the click event listeners and zoom-in or out according to the clicked element
//    google.maps.event.addDomListener(controlUIzoomIn, 'click', function() {
//        map.setZoom(map.getZoom()+1)
//    });
//    google.maps.event.addDomListener(controlUIzoomOut, 'click', function() {
//        map.setZoom(map.getZoom()-1)
//    });
//  }
  
 

  //var zoomControlDiv = document.createElement('div');
  //var zoomControl = new CustomZoomControl(zoomControlDiv, map);

    //insert the zoom div on the top left of the map
    //map.controls[google.maps.ControlPosition.LEFT_TOP].push(zoomControlDiv);
    var infowindow = new google.maps.InfoWindow(); 
    for (var x = 0; x < addresses.length; x++) {
        //console.log(addresses[x]);
        $.getJSON('https://maps.googleapis.com/maps/api/geocode/json?address='+addresses[x]+'&sensor=false&language=iw', null, function (data) {
            if( data.results[0].geometry == null )
                return;
            console.log(data.results[0]);
            var p = data.results[0].geometry.location
            var latlng = new google.maps.LatLng(p.lat, p.lng);
            var marker = new google.maps.Marker({
                position: latlng,
                map: map,
                title: data.results[0].formatted_address,
                visible: true,
              icon: marker_url,
            });
            
            google.maps.event.addListener(marker, 'click', function() {
            infowindow.setContent(marker.title);
            infowindow.open(map,marker);
          });

        });
        
         
    }

});



$('#toggleSidebar').on('click', function(e) {
    e.preventDefault();
    var parent = $('#page-wrapper');
    if(parent.hasClass('open')) {
      parent.removeClass('open');

    } else {
      parent.addClass('open');
    }
});


$('li.sidebar-list a').on('click', function() {
 var parent = $('#page-wrapper');
 if(!parent.hasClass('open')) {
   parent.addClass('open');

 }
});


$('#itemMenuCatId').on('change', function() {
    if($(this).val() != '-1') {
        $('.add_item_fileds').removeClass('hidden');
    } else {
        $('.add_item_fileds').addClass('hidden');
    }
});



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
                      number: true
                  },
        logo:    {
                      required: true,
                      minlength: 2,
                  }
    }
});