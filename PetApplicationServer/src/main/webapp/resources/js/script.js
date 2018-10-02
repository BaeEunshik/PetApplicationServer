
jQuery(document).ready(function($){

		var height_video = $(window).width();
		var height_responsive = (height_video / 1.78011) + 1;

		$(window).resize(function() {
			var height_video = $(window).width();
			var height_responsive = (height_video / 1.78011) + 1;
			$('.video').css("height",height_responsive);
		});

		//$('nav').animate({'opacity': '1'}, 400);
		$('.video').css("height",height_responsive);

});

var window_height = $(window).height();


$(function(){

	 /* ------------------------------------------------------------------------ */
	 // NAVIGATION
	 /* ------------------------------------------------------------------------ */

			 	if ($(window).scrollTop() > $(window).height()){
			 		$('nav.main.transparent').addClass('scroll');
			 	} else {
			 		$('nav.main.transparent').removeClass('scroll');
			 	}

			 	$(window).on("scroll", function(){
			 		var winHeight = $(window).height();
			 		var windowWidth = $(window).width();
			 		var windowScroll = $(window).scrollTop();
			 		var home_height =  $('#home').outerHeight();

		 			if ($(window).scrollTop() > home_height){
		 				$('nav.main.transparent').addClass('scroll');
		 			} else {
		 				$('nav.main.transparent').removeClass('scroll');
		 			}

		 	  });
		/*----------------------------------------------------*/
		// MEDIA QUERY NAVIGATION
		/*----------------------------------------------------*/
			$(".btn_menu").bind('click',function(event){
				$("#navigation").toggleClass("show");
				event.preventDefault();
			});
		/*----------------------------------------------------*/
		// MENU SMOOTH SCROLLING
		/*----------------------------------------------------*/
		   $(".menu a, .scroll-to, .welcome-text-box a").bind('click',function(event){

				$("#navigation").removeClass("show");	// media query

				var headerH = $('.navigation').outerHeight();

		        $("html, body").animate({
		            scrollTop: $($(this).attr("href")).offset().top - headerH + 'px'
		        }, {
		            duration: 1200,
					easing: "easeOutExpo"
		        });

				event.preventDefault();
		    });


		/*----------------------------------------------------*/
		// FULLSCREEN HOME HEIGHT
		/*----------------------------------------------------*/
				fullscreenHomeHeight();

			  function fullscreenHomeHeight(){
				  $('#home').css({"height":window_height});
			  }

			  $(window).bind('resize',function() {
				  fullscreenHomeHeight();
			  });

	/*----------------------------------------------------*/
	/* LAYER - LOGIN & MEMBER & SIGNUP
	/*----------------------------------------------------*/

	// DARK OVERLAY CLICK
		var isLayerOpen = false;
		$(".overlay").click(function(){
			$(".overlay").addClass("hidden");
			$(".layer").addClass("hidden");
			isLayerOpen = !isLayerOpen;
			scrollResume();
		});
	// OPEN LOGIN LAYER & SCROLL PAUSE
		// NAV_ICON CLICK
		$(".nav_login").click(function(){

			$("#navigation").removeClass("show");	// media query

			isLayerOpen = !isLayerOpen;
			if(isLayerOpen){
				$(".login_container").removeClass("hidden");
				$(".overlay").removeClass("hidden");
				scrollPause();
			}else{
				$(".layer").addClass("hidden");
				$(".overlay").addClass("hidden");
				scrollResume();
			}
		});
		// SIGN_UP ELEMENTS CLICK
		$(".login_close").bind('click',function(){
			$(".login_container").toggleClass("hidden");
			$(".overlay").toggleClass("hidden");

			isLayerOpen = !isLayerOpen;
			if(isLayerOpen){
				scrollPause();
			}else{
				scrollResume();
			}
		});

	// OPEN SIGN_UP LAYER & SCROLL PAUSE
		// OPEN SIGN_UP
		$(".login_container .btn_sign_up").click(function(){
			$(".login_container").addClass("hidden");
			$(".sign_up_container").removeClass("hidden");
		});
		// SIGN_UP ELEMENT CLICK
		$(".sign_up_close").bind('click',function(){
			$(".sign_up_container").toggleClass("hidden");
			$(".overlay").toggleClass("hidden");

			isLayerOpen = !isLayerOpen;
			if(isLayerOpen){
				scrollPause();
			}else{
				scrollResume();
			}
		});
	// OPEN MEMBER LAYER & SCROLL PAUSE
		$(".nav_member").bind('click',function(){

			$("#navigation").removeClass("show");	// media query

			$(".member_container").toggleClass("hidden");
			$(".overlay").toggleClass("hidden");

			isLayerOpen = !isLayerOpen;
			if(isLayerOpen){
				scrollPause();
			}else{
				scrollResume();
			}
		});
		function scrollPause(){
			$('html').on('scroll touchmove mousewheel', function(event) {
			  event.preventDefault();
			  event.stopPropagation();
			  return false;
			});
			document.documentElement.style.overflowX = 'hidden';	// horizontal scrollbar will be hidden
			document.documentElement.style.overflowY = 'hidden';	// vertical scrollbar will be hidden
		}
		function scrollResume(){
			$('html').off('scroll touchmove mousewheel');
			document.documentElement.style.overflowY = '';
			document.documentElement.style.overflowX = '';
		}
		// MENU CLICK
		$(".member_menu ol.my_menu a").bind('click',function(event){
				$(".member_menu a").removeClass("active");
				$(this).addClass("active");
				event.preventDefault
		});
		// CLOSE BUTTON
		$(".member_menu .close > button").bind("click",function(){
				$(".overlay").trigger("click");
		})

});
