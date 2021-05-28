<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>너의 공간 나의 공간 : 공간을 나누다. 당신의 공간 이야기를 들려주세요. </title>
	<link rel="stylesheet" href="img_slide.css">
	
	<!-- 슬라이더 스크립트 -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/uikit/3.1.7/js/uikit.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/uikit/3.1.7/js/uikit-icons.min.js"></script>
	
	<!-- 폰트 적용 -->
	<link rel="stylesheet" href="css/spaceDetailView_QnaReview.css" type="text/css">
	<link rel="preconnect" href="https://fonts.gstatic.com">
</head>
<style>

	/* 전체 글씨 속성 적용 */
	*{
		font-family: 'Nanum Gothic', sans-serif;
		color: #505050;
	}
	
	/* 슬라이더 CSS */
	.my-slider {
		width: 100%;
		height: 500px;
		overflow: hidden; 
		text-align: center;
	}
	
	/* 슬라이더-이미지 CSS */
 	.my-slider ul li img {
		width: 1500px;
		height: 500px;
	}
	
	/* 메인 박스 CSS */
	.mainBox {
		display: flex;
		align-items: center;
		justify-content: center;
 	}
	
	/* 인기 지역 CSS */ 
 	.hotPlace {
 		text-align: center;;
		display: inline-block;
		width: 978px; 
		background-color: #fff;
 	}
 	
 	/* 인기 지역-제목 CSS */ 
 	.hotPlace h1 {
 		margin-top: 150px;
 		font-size: 250%;
 		margin-bottom: 40px;
 	}
 	
 	/* 인기 지역-이미지 CSS */ 
  	.hotPlace a img {
 		width: 230px;
 		margin: 30px;
 	}
 	 
 	/* 인기 지역-지역 이름 CSS */ 
 	.hotPlace table tr:nth-child(3) td a {
 		text-decoration: none;
 	 	font-weight: bold;
 	 	font-size: 24px;
 	 	letter-spacing: 50px;
 	 	padding: 10px 10px 10px 60px;
 	 	margin: 0 10px 0 10px;
 	 }

	/* 추천 공간 CSS */
	.recommendationSpace {
		text-align: center;
		display: inline-block;
		width: 978px; 
		background-color: #fff;
		margin-top: 50px;
		margin-bottom: 200px;
	}
	
	/* 추천 공간-제목 CSS */ 
 	.recommendationSpace h1 {
 		margin-top: 150px;
 		font-size: 250%;
 		margin-bottom: 40px;
 	}
 	
 	/* 추천 공간-이미지 CSS */
  	.recommendationSpace a img {
 		width: 200px;
 		margin: 20px;
/*  		border: 1px solid;  */
 
 	}

	/* 추천 공간-공간 이름 CSS */ 
 	.recommendationSpace table tr:nth-child(3) td a {
 		text-decoration: none;
 	 	font-weight: bold;
 	 	font-size: 24px;
 	 	padding: 10px 30px 10px 30px;
 	 	margin: 0 10px 0 10px;
 	 }

	/* 이용 후기 슬라이더 CSS */
	.review-slider {
		width: 900px;
		height: 400px;
		overflow: hidden; 
		text-align: center;
		margin-bottom: 150px;
	}
	
	/* 이용 후기 슬라이더-이미지 CSS */
 	.review-slider ul li img {
		width: 900px;
		height: 400px;
	}
	
</style>

<body>
	<!-- header.jsp 불러오기 -->
	<%@ include file="header.jsp" %>
	
	<!-- 광고 슬라이더 -->
	<div class="my-slider uk-position-relative uk-visible-toggle uk-light" tabindex="-1" uk-slideshow="autoplay:true; autoplay-interval=1000;">
	    <ul class="uk-slideshow-items">
	        <li>
	            <img src="img/ysms_slide_rest.png" alt="">
	        </li>
	        <li>
	            <img src="img/ysms_slide_party.png" alt="">
	        </li>
	        <li>
	            <img src="img/ysms_slide_study.png" alt="">
	        </li>
	        <li>
	            <img src="img/ysms_slide_meeting.png" alt="">
	        </li>
	    </ul>
	    <a class="btn uk-position-center-left uk-position-small uk-hidden-hover" href="#" uk-slidenav-previous uk-slideshow-item="previous"></a>
	    <a class="btn uk-position-center-right uk-position-small uk-hidden-hover" href="#" uk-slidenav-next uk-slideshow-item="next"></a>
	</div>
	
	<!-- 인기 지역 -->
	<div class="mainBox">
		<div class="hotPlace">
			<table align = "center">
				<tr><td colspan="3"><h1>인기 지역</h1></td></tr>
				<tr>
					<td><a href="SearchPlaceCommand.four?categorySpace=0&location=강남"><img alt="Gangnam icon" src="img/ysms_hotPlace_Gangnam.png"></a></td>
					<td><a href="SearchPlaceCommand.four?categorySpace=0&location=종로"><img alt="Jongno icon" src="img/ysms_hotPlace_Jongno.png"></a></td>
					<td><a href="SearchPlaceCommand.four?categorySpace=0&location=마포"><img alt="Hongdae icon" src="img/ysms_hotPlace_Hongdae.png"></a></td>
				</tr>
				<tr>
					<td><a href="SearchPlaceCommand.four?categorySpace=0&location=강남">강남</a></td>
					<td><a href="SearchPlaceCommand.four?categorySpace=0&location=종로">종로</a></td>
					<td><a href="SearchPlaceCommand.four?categorySpace=0&location=마포">홍대</a></td>
				</tr>
			</table>
		</div>
	</div>
	
	<!-- 추천 공간 -->
	<div class="mainBox">
		<div class="recommendationSpace">
			<table align = "center">
				<tr><td colspan="4"><h1>추천 공간</h1></td></tr>
				<tr>
					<td><a href="SearchPlaceCommand.four?categorySpace=1&location=구"><img alt="rest icon" src="img/ysms_reco_icon_rest.png"></a></td>
					<td><a href="SearchPlaceCommand.four?categorySpace=2&location=구"><img alt="party icon" src="img/ysms_reco_icon_party.png"></a></td>
					<td><a href="SearchPlaceCommand.four?categorySpace=3&location=구"><img alt="study icon" src="img/ysms_reco_icon_study.png"></a></td>
					<td><a href="SearchPlaceCommand.four?categorySpace=4&location=구"><img alt="meeting icon" src="img/ysms_reco_icon_meeting.png"></a></td>
				</tr>
				<tr>
					<td><a href="SearchPlaceCommand.four?categorySpace=1&location=구">Rest</a></td>
					<td><a href="SearchPlaceCommand.four?categorySpace=2&location=구">Party</a></td>
					<td><a href="SearchPlaceCommand.four?categorySpace=3&location=구">Study</a></td>
					<td><a href="SearchPlaceCommand.four?categorySpace=4&location=구">Meeting</a></td>
				</tr>
			</table>
		</div>
	</div>
	
	<!-- 이용 후기 슬라이더 -->
	<div class="mainBox">
		<div class="review-slider uk-position-relative uk-visible-toggle uk-light" tabindex="-1" uk-slideshow="autoplay:true; autoplay-interval=1000;">
		    <ul class="uk-slideshow-items">
		        <li>
		        	<img alt="이용 후기 1" src="img/ysms_review_slide_one.jpg">
		        </li>
		        <li>
		        	<img alt="이용 후기 2" src="img/ysms_review_slide_two.jpg">
		        </li>
		        <li>
		        	<img alt="이용 후기 3" src="img/ysms_review_slide_three.jpg">
		        </li>
		    </ul>
		    <a class="btn uk-position-center-left uk-position-small uk-hidden-hover" href="#" uk-slidenav-previous uk-slideshow-item="previous"></a>
		    <a class="btn uk-position-center-right uk-position-small uk-hidden-hover" href="#" uk-slidenav-next uk-slideshow-item="next"></a>
		</div>
	</div>
	
	<!-- footer.jsp 불러오기 -->
	<%@ include file="footer.jsp" %>
</body>
</html>