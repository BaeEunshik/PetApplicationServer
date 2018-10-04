<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
	
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

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
	<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
	<script type="text/javascript" src="resources/js/jquery.sticky.js"></script>
	<script type="text/javascript" src="resources/js/jquery.easing.min.js"></script>
	<script type="text/javascript" src="resources/js/jquery.timepicker.js"></script>
	<script type="text/javascript" src="resources/js/script.js"></script>
	<script>
		$(document).ready(function(){
			//timepicker
			$('input.timepicker').timepicker({
				 timeFormat: 'h:mm p',
				 interval: 60,
				 minTime: '10',
				 maxTime: '6:00pm',
				 defaultTime: '12',
				 startTime: '10:00',
				 dynamic: false,
				 dropdown: true,
				 scrollbar: true
			});
			//daum address
			$('.address').click(function(){
				new daum.Postcode({
					popupName: 'postcodePopup',
			        oncomplete: function(data) {
			        	zonecode = data.zonecode;
			        	$('.address').val(data.zonecode);
						//data.zonecode, data.jibunAddress, data.buildingName
			        }
	    		}).open();
			});

		});
	</script>
</head>
<body>
	<!-- NAVIGATION -->
	<header class="media">
		<nav id="navigation" class="register transparent"><!-- not transparent -->

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
	<div class="wrap register_section" >
		<div class="container table_container">
			<div class="table_box">

				<table class="register_table">
					<tbody>
						<tr class="clearfix">
							<th>가게명</th>
							<td>
								<div class="td_box">
									<input type="text">
								</div>
							</td>
						</tr>
						<tr class="clearfix">
							<th>주소</th>
							<td>
								<div class="td_box">
									<input type="text" class="address" readonly>
								</div>
							</td>
						</tr>
						<tr class="clearfix">
							<th>매장성격</th>
							<td>
								<div class="td_box">
									<div class="purpose_container clearfix">
										<div class="purpose_box purpose_left">
											<select name="" id="purpose_1">
												<option value="1">애견동반</option>
												<option value="2">애견전용</option>
											</select>
										</div>
										<div class="purpose_box purpose_right">
											<select name="" id="purpose_2">
												<option value="1"> 일반카페</option>
											</select>
										</div>
									</div>
								</div>
							</td>
						</tr>
						<tr class="clearfix">
							<th>입장 가능한 반려견 크기</th>
							<td>
								<div class="td_box">
									<div class="size_container clearfix">
										<div class="size_item">
											<input type="radio" name="size" value="1">
											<span>소형견</span>
										</div>
										<div class="size_item">
											<input type="radio" name="size" value="2">
											<span>중형견</span>
										</div>
										<div class="size_item">
											<input type="radio" name="size" value="3">
											<span>대형견</span>
										</div>
									</div>
								</div>
							</td>
						</tr>
						<tr class="clearfix">
							<th>영업일 / 영업시간</th>
							<td>
								<div class="td_box">
									<div class="open_day_container clearfix">
										<div class="open_day_item">
											<input type="checkbox">
											<span>월</span>
										</div>
										<div class="open_day_item">
											<input type="checkbox">
											<span>화</span>
										</div>
										<div class="open_day_item">
											<input type="checkbox">
											<span>수</span>
										</div>
										<div class="open_day_item">
											<input type="checkbox">
											<span>목</span>
										</div>
										<div class="open_day_item">
											<input type="checkbox">
											<span>금</span>
										</div>
										<div class="open_day_item">
											<input type="checkbox">
											<span>토</span>
										</div>
										<div class="open_day_item">
											<input type="checkbox">
											<span>일</span>
										</div>
									</div>
									<!-- open_day_container -->

									<!-- open_time_container -->
									<div>
										<div class="open_time_container clearfix">

											<div class="open_time_item">
												<input class="timepicker"type="text">
											</div>
											<span class="table_flow_icon"> ~ </span>
											<div  class="open_time_item">
												<input class="timepicker"type="text">
											</div>

										</div>
									</div>
									<!-- open_time_container -->
								</div>
								<!-- td_box -->
							</td>
						</tr>
						<tr class="clearfix">
							<th>예약여부</th>
							<td>
								<div class="td_box">
									<div class="reservation_container clearfix">
										<div class="reservation_item">
											<input type="radio" name="size" value="1">
											<span>예약가능</span>
										</div>
										<div class="reservation_item">
											<input type="radio" name="size" value="2">
											<span>예약불가</span>
										</div>
									</div>
								</div>
							</td>
						</tr>
						<tr class="clearfix">
							<th>전화번호</th>
							<td>
								<style>

								</style>
								<div class="td_box">
									<div class="phone_container clearfix">
										<div class="phone_box">
											<select name="" id="">
												<option value="02">02</option>
												<option value="010">010</option>
											</select>
										</div>
										<span class="table_flow_icon">-</span>
										<div class="phone_box">
											<input type="text">
										</div>
											<span class="table_flow_icon">-</span>
										<div class="phone_box">
											<input type="text">
										</div>
									</div>
								</div>
							</td>
						</tr>
						<tr class="clearfix">
							<th>주차여부</th>
							<td>
								<div class="td_box">
									<div class="parking_container clearfix">
										<div class="parking_item">
											<input type="radio" name="parking" value="1">
											<span>주차가능</span>
										</div>
										<div class="parking_item">
											<input type="radio" name="parking" value="2">
											<span>주차불가</span>
										</div>
										<div class="parking_item">
											<input type="radio" name="parking" value="3">
											<span>발렛주차</span>
										</div>
									</div>
								</div>
							</td>
						</tr>
						<tr class="clearfix">
							<th>매장 상세설명</th>
							<td>
								<div class="td_box">
									<textarea name="name" maxlength="50"></textarea>
									<!-- <input type="text"> -->
								</div>
							</td>
						</tr>
						<tr class="clearfix">
							<th>매장사진</th>
							<td>
								<div class="td_box">



								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<!-- table_box -->





			<!-- <a href="">등록</a> -->


		</div>
		<!-- container table_container -->


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
