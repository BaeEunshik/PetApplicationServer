<%@page import="kr.co.petApplication.dto.StoreData"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kr.co.petApplication.utils.Global"%>
<%@page import="kr.co.petApplication.dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%	
	String fail = (String)request.getAttribute("fail");
	System.out.println(fail);
	Member login_member = (Member)session.getAttribute("login_member");
	//검색 시 새로 세팅
	ArrayList<StoreData> items = (ArrayList<StoreData>)request.getAttribute("items");
	Integer isSearched = (Integer)request.getAttribute("search");
	//Bookmark
	ArrayList<StoreData> bookmarkItems = (ArrayList<StoreData>)request.getAttribute("bookmarkItems");
	if(bookmarkItems != null){
		System.out.println("널아님");
	}else{
		System.out.println("널");
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

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
	<script type="text/javascript" src="resources/js/jquery.sticky.js"></script>
	<script type="text/javascript" src="resources/js/jquery.easing.min.js"></script>
	<script src="resources/js/script.js"></script>
	<script>
	$(function(){
		
		//검색한 경우
		if(<%= isSearched %> == 1){
			var headerH = $('.navigation').outerHeight();
			$("html, body").animate({
            scrollTop: $("#search").offset().top - headerH + 'px'
       	 },{
       	 	duration : 100
       	 });
		}
		
		//로그인
		$("#submit_login").click(function(){
			$("#login_form").submit();
		});
		
		//검색
		$(".btn_search").click(function(){
			$("#search_form").submit();
		});
		
	});
	</script>
</head>
<body class="main_body">
	<!-- NAVIGATION -->
	<header class="media">
		<nav id="navigation" class="main transparent">
			<!-- NAVIGATION - ITEM -->
				<div class="member">
				
				<%if(login_member != null){%>
					<!-- 로그인 상태 -->
					<a class="nav_member" href="#none"></a>
				<%} else {%>
					<!-- 로그아웃 상태 -->
					<a class="nav_login" href="#none"></a>
				<%}%>
				</div>

				<ul class="menu">

					<li><a href="home.do">HOME</a></li>
					<li><a id="nav_search"href="#search">SEARCH</a></li>

					<!-- 로그아웃 상태의 경우 생성 x -->
					<%if(login_member != null){%>
					<li><a href="register.do">REGISTER</a></li>
					<%}%>
					<!-- 로그아웃 상태의 경우 생성 x -->

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
	<div class="wrap">
		<!-- HOME -->
		<div id="home">
			<!-- video -->
			<div class="video">
        <div style="z-index: -99; width: 100%; height: 100%">
          <!-- FOR YOUTUBE -->
          <!-- chrome 에서는 mute 해야만 autoplay 가능 -->
          <iframe class="full_screen_video" src="//www.youtube.com/embed/UCJcELEeS9k?autoplay=1&amp;loop=1&amp;playlist=UCJcELEeS9k&amp;mute=1&amp;controls=0&amp;showinfo=0&amp;autohide=1&amp;rel=0&amp;vq=hd1080">
          </iframe>

        </div>
      </div>
			<!-- video -->

			<!-- WELCOME TEXT -->
			<div class="welcome-text-box">
				<div class="welcome">
					<a href="#search">WELCOME</a>
				</div>
			</div>
			<!-- HOME TEXT -->
		</div>
		<!-- HOME -->

		<!-- SEARCH -->
		<div id="search" style="margin-top:10px">
			<!-- TITLE -->
			<div class="titleBox" style="position: relative; height:100px">
				<h1 style="font-size: 35px;text-align: center;font-weight: medium;">
					반려견과 함께 할 곳을 찾아보세요 !
				</h1>
			</div>
			<!-- TITLE -->
			<!-- container -->
			<div class="container">
				<form action="search.do" method="get" id="search_form">
					<!-- searchBox -->
					<div class="searchBox">
						<div class="input_box clearfix">
							<input type="text" id="searchText" name="searchText" style="float:left;">
							<button type="button" class="btn_search" style="">
								검색
							</button>
						</div>

						<!-- filter_box -->
						<div class="filter_box clearfix">
							<div class="selectBox location">
								<select name="selectLocation" id="selectLocation" style="height:38px;">
									<option hidden value="0">위치(구)</option>
									<%for(int i = 0; i < Global.LOCATION_GU_STR_ARR.length; i ++){ %>
										<option value="<%=i %>"><%=Global.LOCATION_GU_STR_ARR[i] %></option>
									<%} %>
								</select>
							</div>

							<div class="selectBox size">
								<select name="selectSize" id="selectSize" style="height:38px;">
									<option hidden value="0">크기</option>
									<option value="1">소형견</option>
									<option value="2">중형견</option>
									<option value="3">대형견</option>
								</select>
							</div>

							<div class="selectBox place">
								<select name="selectPlace" id="selectPlace" style="height:38px;">
									<option hidden value="0">장소</option>
									<%for (int i = 0 ; i < Global.CATEGORY_GENERAL_STR_ARR.length ; i++ ) { %>
										<option value=<%=i+1 %>><%= Global.CATEGORY_GENERAL_STR_ARR[i]%></option>
									<%} %>
									<%for (int i = 0 ; i < Global.CATEGORY_SPECIAL_STR_ARR.length ; i++ ) { %>
										<option value=<%=Global.CATEGORY_GENERAL_STR_ARR.length + i+1 %>><%= Global.CATEGORY_SPECIAL_STR_ARR[i]%></option>
									<%} %>							
								</select>
							</div>

						</div>
						<!-- filter_box -->

					</div>
					<!-- searchBox -->
				</form>
			</div>
			<!-- container -->
		</div>
		<!-- SEARCH -->


		<!-- ITEMS -->
		<div class="searched_items">
			<ul class="clearfix">

				<!-- START ITEM SECTION-->
				<%if(items != null){ %>
					<%for(int i = 0; i < items.size(); i++){ %>
					<li>
						<a href="shop.do?id=<%=items.get(i).getStore().getId()%>">
							<div class="item_box">
								<div class="imgBox" >
									<img class="item_img" src="resources/upload/<%= items.get(i).getImages().get(0).getSavedName() %>" alt="">
								</div>
								<div class="infobox clearfix">
									<div class="left">
										<p class="item_name"><%= items.get(i).getStore().getName() %></p>
										<p class="item_location"><%= Global.LOCATION_GU_STR_ARR[items.get(i).getStore().getSigungu()] %></p>
										<div class="item_counts clearfix">
											<div class="viewContainer">
												<p><%= items.get(i).getStore().getHit() %></p>
											</div>
											<div class="reviewContainer">
												<p><%= items.get(i).getReviews().size() %></p>
											</div>
										</div>
									</div>
									<div class="right">
										<div class="point">
											<p>5.0</p>
										</div>
									</div>
								</div>
							</div>
						</a>
					</li>
					<%}%>
				<%}%>
				<!-- END ITEM SECTION-->

			</ul>
		</div>
	</div>


		<!-- Footer -->
		<div class="footer">
		</div>
		<!-- Footer -->
	</div>
	<!-- PAGE WRAP -->

	<!-- LOGIN CONTAINER LAYER -->
	<div class="login_container layer hidden">
		<div class="login_box">
			<h2>LOGIN</h2>
			<button class="login_close">  </button>

			<form action="login_ok.do" method="post" id="login_form">

				<div class="form-group">
					<label for="login_id">
						<input id="login_id" name="login_id" type="text" placeholder="id">
					</label>
				</div>
				<div class="id_alert alert hidden">아이디를 입력해주세요</div>
				<div class="form-group ">
					<label for="login_pwd">
						<input id="login_pwd" name="login_pwd" type="password" placeholder="Password">
					</label>
				</div>
				<div class="pwd_alert alert hidden">비밀번호를 입력해주세요</div>
				<div class="login_alert alert hidden">아이디와 비밀번호를 확인해주세요</div>

				<a id="submit_login" class="btn_login" href="#none">LOGIN</a>
				<a class="btn_sign_up" href="#none">SIGNUP</a>

			</form>

		</div>
	</div>
	<!-- LOGIN CONTAINER LAYER -->

	<!-- SIGNUP CONTAINER LAYER -->
	<div class="sign_up_container layer hidden">
		<div class="sign_up_box">
			<h2>SIGNUP</h2>
			<button class="sign_up_close"></button>

			<form action="signup_ok.do" method="post" id="sign_up_form">

				<div class="form-group">
					<label for="sign_up_id">
						<input id="sign_up_id" name="sign_up_id" type="text" placeholder="id">
					</label>
				</div>
				<div class="id_alert alert hidden">아이디를 입력해주세요</div>

				<div class="form-group">
					<label for="sign_up_name">
						<input id="sign_up_name" name="sign_up_name" type="text" placeholder="name">
					</label>
				</div>
				<div class="name_alert alert hidden">닉네임을 입력해주세요</div>

				<div class="form-group">
					<label for="sign_up_pwd">
						<input id="sign_up_pwd" name="sign_up_pwd" type="password" placeholder="Password">
					</label>
				</div>
				<div class="pwd_alert alert hidden">비밀번호를 입력해주세요</div>

				<div class="form-group">
					<label for="sign_up_pwd_re">
						<input id="sign_up_pwd_re" name="sign_up_pwd_re" type="password" placeholder="Re:Password">
					</label>
				</div>
				<div class="pwd_re_alert alert hidden">비밀번호를 입력해주세요</div>

				<a class="btn_sign_up" href="#none" style="margin-top: 35px;">SIGNUP</a>

			</form>

		</div>
	</div>
	<!-- SIGNUP CONTAINER LAYER -->


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
			<ul class="bookmark_list">
			<%if(bookmarkItems != null){ %>
				<%for(int i = 0; i < bookmarkItems.size(); i++){ %>
				<li class="menu_item clearfix">
					<a class="btn_bookmark_info" href="shop.do?id=<%=bookmarkItems.get(i).getStore().getId()%>">
						<div class="bookmark_picture" 
							style="background-image: url('resources/upload/<%= bookmarkItems.get(i).getImages().get(0).getSavedName() %>');">
						</div>
						<div class="bookmark_contents clearfix">
							<p class="bookmark_name"><%= bookmarkItems.get(i).getStore().getName()%></p>
							<p class="bookmark_point">
								<% 
									double result = ((double)bookmarkItems.get(i).getStore().getScore_sum()) / ((double)bookmarkItems.get(i).getStore().getScore_count());
									result = Math.ceil(result*10d) / 10d;
									if (Double.isNaN(result)) {
										result = 0.0;
									}
								%>
								<%= result %>
							</p>
						</div>
					</a>
				</li>
				<%} %>
			<%} %>
			</ul>
			
			<ul class="registerd_list">
				
			</ul>
		</div>
		<div class="logout_box">
			<a href="logout.do" class="btn_logout">logout</a>
		</div>
	</div>
	<!-- MEMBER CONTAINER LAYER -->

	<!-- OVERLAY -->
	<!-- <div class="login_overlay hidden"></div>
	<div class="member_overlay hidden"></div> -->
	<div class="overlay hidden"></div>
	<!-- OVERLAY -->

</body>
</html>
