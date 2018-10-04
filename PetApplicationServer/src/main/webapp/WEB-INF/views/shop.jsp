<%@page import="kr.co.petApplication.dto.Bookmark"%>
<%@page import="kr.co.petApplication.dto.Member"%>
<%@page import="kr.co.petApplication.dto.StoreData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%
	//멤버
	Member login_member = (Member)session.getAttribute("login_member");
	//북마크
	Bookmark bookmark = (Bookmark)request.getAttribute("bookmark");
	//장소 객체
	StoreData item = (StoreData)request.getAttribute("item");
	//점수
	double result = ((double)item.getStore().getScore_sum())/((double)item.getStore().getScore_count());
	result = Math.ceil(result*10d) / 10d;
	if (Double.isNaN(result)) {
		result = 0.0;
	}
%>
<!DOCTYPE html>
<html lang="ko">

<!-- .container 는 박스모델 - 미디어쿼리를 위한 박스  -->
<!-- nav, search 자식요소로 있다. -->
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
	<title>PET</title>

	<!-- GOOGLE WEB FONTS INCLUDE -->
  	<link href='http://fonts.googleapis.com/css?family=Oswald|Open+Sans:400,600' rel='stylesheet' type='text/css'>

	<link rel="stylesheet" href="resources/css/reset.css">	<!--[if IE]><script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
	<link rel="stylesheet" href="resources/css/skeleton.css">
	<link rel="stylesheet" href="resources/css/style.css">
	<link rel="stylesheet" href="resources/css/media.css">
	<link rel="stylesheet" href="resources/css/jquery.timepicker.css">

	<link rel="stylesheet" type="text/css" href="resources/plugin/slick/slick.css"/>
  	<link rel="stylesheet" type="text/css" href="resources/plugin/slick/slick-theme.css"/>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
	<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
	<script type="text/javascript" src="resources/js/jquery.sticky.js"></script>
	<script type="text/javascript" src="resources/js/jquery.easing.min.js"></script>
	<script type="text/javascript" src="resources/js/jquery.timepicker.js"></script>
	<script type="text/javascript" src="resources/js/script.js"></script>

	<script type="text/javascript" src="resources/plugin/slick/slick.min.js"></script>
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDA6LRonlMWbg5selHc5ayH6UQUKNnKoVg&sensor=false"></script>
	<script type="text/javascript">
		function setGoogleMap() {
		 
			var Y_point = <%=item.getStore().getLatitude()%>; // Y 좌표
			var X_point = <%=item.getStore().getLongitude()%>;  // X 좌표
			var zoomLevel = 17; // 첫 로딩시 보일 지도의 확대 레벨
			var markerTitle = "<%=item.getStore().getName()%>"; 
			// 현재 위치 마커에 마우스를 올렸을때 나타나는 이름
			var markerMaxWidth = 300; // 마커를 클릭했을때 나타나는 말풍선의 최대 크기
			
			var myLatlng = new google.maps.LatLng(Y_point, X_point);
			var mapOptions = {
				zoom: zoomLevel,
				center: myLatlng,
				mapTypeId: google.maps.MapTypeId.ROADMAP
			}
			var map = new google.maps.Map(document.getElementById('map_view'), mapOptions);
			var marker = new google.maps.Marker({
				position: myLatlng,
				map: map,
				title: markerTitle
			});
			/* var infowindow = new google.maps.InfoWindow({
				maxWidth: markerMaxWidth
			});
			google.maps.event.addListener(marker, 'click', function() {
				infowindow.open(map, marker);
			}); */
		}
		$(document).ready(function() {
			setGoogleMap();
		});
		
	  $(document).ready(function(){
	    $('.gallery_container').slick({
	
	       	slidesToShow: 6,
			slidesToScroll: 1,
			infinite: false,
			speed: 100,
			edgeFriction: 0.1,
			centerMode: false,
			responsive: [{
	       		breakpoint: 1500,
	       		settings: { slidesToShow: 5 }
	  		 	},
				 	{
	       		breakpoint: 1024,
	       		settings: { slidesToShow: 4 }
	  		 	},
					{
	      		breakpoint: 960,
	      		settings: { slidesToShow: 3 }
			}]
	
	    });
	  });
	</script>

</head>
<body>
	<!-- NAVIGATION -->
	<header class="media">
		<nav id="navigation" class="shop transparent"><!-- not transparent -->

				<!-- NAVIGATION - ITEM -->

					<div class="member">
					<!-- 로그인 상태 -->
						<a class="nav_member" href="#none"></a>
					</div>

					<ul class="menu">
						<li><a href="home.do">HOME</a></li>
						<li><a href="home.do#search">SEARCH</a></li>
						<li><a href="register.do">REGISTER</a></li>
					</ul>

					<!-- MOBILE HEADER MENU BAR -->
					<div class="mobile_header" href="#">
						<a class="btn_menu" href=""></a>
					</div>
					<!-- MOBILE HEADER MENU BAR -->

				<!-- NAVIGATION - ITEM -->
		</nav>
	</header>
	<!-- NAVIGATION -->

	<!-- PAGE WRAP -->
	<div class="wrap shop_section" >
	
		<div class="gallery_container clearfix" style="margin-left: 0;">
			<%for(int i = 0 ; i < item.getImages().size(); i ++){ %>
			<div class="img_box">
				<img class="item_img" src="resources/upload/<%= item.getImages().get(i).getSavedName() %>" alt="">
			</div>
			<%} %>
		</div>

		<div class="shop_info">
			<div class="shop_info_header">
				<div class="info_header_left">
					<div>
						<h3>
							<%= item.getStore().getName() %>
							<span class="point">
							<%= result %>
							</span>
						</h3>
					</div>
	
					<div class="item_counts clearfix">
						<div class="viewContainer">
							<p>10</p>
						</div>
						<div class="reviewContainer">
							<p>10</p>
						</div>
	
					</div>
				</div>
				<div class="info_header_right">
				 	<div class="btn_add_bookmark
			 		<% if (bookmark != null) { %> 
			 			on
			 		<% } else { %> 
			 			off
			 		<% } %>
				 	">
				 	</div>
					<p>북마크</p>
				</div>
			</div>
			<div class="shop_info_content clearfix">
				<div class="content_left">
					<div class="clearfix">
						<div class="title">
							<p>주소</p>
							<p>영업 시간</p>
							<p>영업 요일</p>
							<p>반려견 크기</p>
							<p>주차</p>
							<p>예약</p>
						</div>
						<div class="content">
							<p><%= item.getStore().getAddress() %></p>
							<p><%= item.getStore().getOperation_time()%></p>
							<p style="word-spacing:5px"><%= item.getStore().getOperation_day()%></p>
							<p>
								<%if (item.getStore().getDog_size() == 1){%>
									~ 소형견
								<%} else if (item.getStore().getDog_size() == 2){%>
									~ 중형견
								<%} else if (item.getStore().getDog_size() == 3){ %>
									~ 대형견
								<%} %>
							</p>
							<p>
								<% if (item.getStore().getParking() == 1){%>
									주차 가능
								<%} else if (item.getStore().getParking() == 2){ %>
									주차 불가
								<%} else if (item.getStore().getParking() == 3){ %>
									발렛 주차
								<%} %>
							</p>
							<p>
								<% if (item.getStore().getReservation() == 0){%>
									예약 가능
								<%} else if (item.getStore().getReservation() == 1){ %>
									예약 불가
								<%} %>
							</p>
						</div>
					</div>
					<div class="shop_info_introduce">
						<p>
							※ <%= item.getStore().getStore_information() %>
						</p>

					</div>
				</div>
				<div class="content_right clearfix">
					<div id="map_view" class="google_map_box">

					</div>
				</div>
			</div>
			<style>
				
			</style>
			<div class="reviews">
				<div class="review_header clearfix">
					<div class="review_counts">
						<h5>
							리뷰 (<%= item.getReviews().size() %>)
						</h5>
					</div>
					<div class="add_review">
						<a class="btn_add_review" href="review.do">
							리뷰 작성
						</a>
					</div>
				</div>

				
				<%for(int i = 0; i < item.getReviews().size(); i ++){ %>
				<!-- START REVIEW ITEM -->
				<div class="review_item clearfix">
					<div class="user">
						<div class="picture">
							<!-- 리뷰어 이미지 가져와야 함 
							<img src="resources/upload/" alt="" /> 
							-->
						</div>
						<div class="name" style="padding-top:10px">
							<p>
								<%=item.getMembers().get(i).getName() %>
							</p>
						</div>
					</div>
					<div class="review">
						<p>
							<%=item.getReviews().get(i).getContent() %>
						</p>
					</div>
					<!-- 
					<div class="point">
						<p>
						리뷰어가 매긴 점수 가져와야 함 - DB 에 없음
						</p>
					</div>
					-->	
				</div>
				<!-- START REVIEW ITEM -->
				<%} %>


			</div>
		</div>
		<!-- shop_info -->

		<!-- Footer -->
		<div class="footer">
		</div>
		<!-- Footer -->
	</div>
	<!-- PAGE WRAP -->


	<!-- MEMBER CONTAINER LAYER -->
	<div class="member_container layer hidden">
		<div class="member_menu">
			<span class="arrow"></span>
			<span class="close"><button></button></span>
			<ol class="my_menu clearfix">
				<li class="bookmark">
					<a class="active" href="#none">북마크</a>
				</li>
				<li class="myList">
					<a href="#none">내가 등록한 곳</a>
				</li>
			</ol>
		</div>
		<div class="menu_items">
		
		</div>
		<div class="logout_box">
			<a href="logout.do">logout</a>
		</div>
	</div>
	<!-- MEMBER CONTAINER LAYER -->

	<!-- OVERLAY -->
	<div class="overlay hidden"></div>
	<!-- OVERLAY -->
</body>
</html>
