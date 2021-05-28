<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>너공나공?</title>

	<!-- 슬라이더 스크립트 -->
	<link rel="stylesheet" href="img_slide.css">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/uikit/3.1.7/js/uikit.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/uikit/3.1.7/js/uikit-icons.min.js"></script>

	<!-- 폰트 적용 -->
	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap" rel="stylesheet">
</head>
<style>

	/* 전체 글씨 속성 적용 */
	*{
		font-family: 'Nanum Gothic', sans-serif;
		color: #505050;
	}
	
	/* 메인 박스 CSS */
	.mainBox {
		display: flex;
		align-items: center;
		justify-content: center;
		margin-bottom: 40px;
 	}
	
	/* 너공나공 소개글-테두리 CSS  */
	.edge { 
		padding-top: 40px;
		width: 978x; 
		word-break: break-all; 
		background-color:rgba(211, 211, 211, 0.2); 
	} 
	
	/* 너공나공 제목 CSS */
	h1 {
		font-weight: 800;
		font-size: 40px;
		letter-spacing: 10px;
		text-align: center;
		margin-bottom: 70px;
	}
	
	/* 슬라이더 CSS */
	.my-slider {
		width: 100%;
		height: 400px;
		overflow: hidden; 
		text-align: center;
		margin: 30px 20px 0 0;
	}
	
	/* 슬라이더-이미지 CSS */
 	.my-slider ul li img {
		width: 800px;
		height: 400px;
	}
	
	/* 너공나공 소개글-부제목 CSS */	
	.introduction h3 {
		font-weight: 800;
		font-size: 23px;
		text-align: center;
		margin-top: 150px;
		margin-bottom: 50px;
	}
	
	/* 너공나공 소개글-내용 CSS */
	.introduction h5 {
		font-size: 15px;
		text-align: center;
		line-height: 200%;
	}

	/* 너공나공 이용 방법-제목 CSS */
	h3 {
		font-weight: 800;
		font-size: 24px;
		text-align: center;
		margin-top: 100px;
	}
	
	/* 너공나공 이용 방법-소제목&내용 테두리 CSS */
	.howToUse {
		border-style: solid;
		border-color: #dcdcdc;
		border-width: 1.5px;
		padding: 40px 70px 30px 70px;
	}
	
	
	/* 너공나공 이용 방법-소제목 CSS */
	.howToUse h4 {
		font-weight: 900; 
		font-size: 15px;
		text-align: left;
	}

	/* 너공나공 이용 방법-내용 CSS */
	.howToUse h5 {
		font-size: 15px;
		text-align: left;
		line-height: 250%;
	}
	
	/* 너공나공 CI CSS */
	.ci {
		margin: 0 20px 70px 20px;
	}
	
	/* 너공나공 CI-제목 CSS */
	.ci h3 {
		font-weight: 800;
		font-size: 24px;
		text-align: center;
		margin-top: 120px;
		margin-bottom: 25px;
	}
	
	/* 너공나공 CI-이미지 배경 CSS */
	.ci table tr td:nth-child(1) {
		float: left;
		width: 300px;
		margin: 15px 20px 0 20px;
		padding: 30px 20px;
		background-color: #FFFFFF;
	}
	
	/* 너공나공 CI-이미지 CSS */
	.ci table tr td:nth-child(1) img {
		float: left;
		width: 300px;
	}

	/* 너공나공 CI-설명 CSS */
	.ci table tr td:nth-child(2) h5 {
		font-size: 15px;
		text-align: left;
		text-indent: 15px;
		line-height: 200%;
		margin: 0 20px 0 20px;
	}
	
</style>

<body>
	<!-- header.jsp 불러오기 -->
	<%@ include file="header.jsp" %>
	
	<!-- 너공나공 소개글 전체 테두리-->
	<div class="mainBox">
	<div class="edge">
	
	<!-- 너공나공? 제목 -->
	<h1>너공나공?</h1>
	
	<!-- 슬라이더 -->
	<div class="my-slider uk-position-relative uk-visible-toggle uk-light" tabindex="-1" uk-slideshow="autoplay:true; autoplay-interval=1000;">
	    <ul class="uk-slideshow-items">
	        <li>
	            <img src="img/ysms_introduction_place_korea.png" alt="">
	        </li>
	        <li>
	            <img src="img/ysms_introduction_place_rooftop.png" alt="">
	        </li>
	        <li>
	            <img src="img/ysms_introduction_place_selfstudy.png" alt="">
	        </li>
	        <li>
	            <img src="img/ysms_introduction_place_meeting.png" alt="">
	        </li>
	        <li>
	            <img src="img/ysms_introduction_place_studyself.png" alt="">
	        </li>
	    </ul>
	    <a class="btn uk-position-center-left uk-position-small uk-hidden-hover" href="#" uk-slidenav-previous uk-slideshow-item="previous"></a>
	    <a class="btn uk-position-center-right uk-position-small uk-hidden-hover" href="#" uk-slidenav-next uk-slideshow-item="next"></a>
	</div>
	
	<!-- 너공나공 소개글 -->
	<div class="introduction">
		<h3>당신의 소중한 공간을 공유하시겠습니까?</h3>
		
		<h5>
				너공나공은 '너의 공간 나의 공간'으로<br> 
		"남는 공간을 어떻게 활용할 수 있을까?"라는 의문점에서 시작되었으며<br>
		숙박 사이트인 '에어비앤비’에서 아이디어에 착안하여 숙박 대신 대실에 중심을 두었습니다.<br><br><br>
		
		사용자가 남는 공간을 타인에게 대여해 줌으로써 수익을 창출 할 수 있고 <br>
		원하는 시간 동안 공간이 필요한 사람은 자신의 상황에 적합한 공간을 빌릴 수 있습니다.<br><br><br>
		
		이처럼 원하는 지역과 대여 목적에 따라 공간을 선택할 수 있다는 것이 주된 의도이며<br>
		또한, 이용자와 호스트 간의 구분이 없어 누구나 자유롭게 공간을 빌릴 수 있고 나눌 수 있습니다.<br><br><br>
		</h5>
	</div>
		
		
	<!-- 너공나공 이용 방법 -->
	<h3>‘너공나공’은 어떻게 이루어져 있나요?</h3><br>
	<div class="mainBox">
		<div class="howToUse">	
			<h4>• 공간 목록</h4>
			<h5>- 사용자가 공간 유형과 지역, 날짜를 입력 후 원하시는 공간을 검색하실 수 있습니다.</h5><br><br>
			
			<h4>• 공간 나눔 목록</h4>
			<h5>
			 - 호스트가 공간을 등록할 수 있으며 등록한 공간의 목록을 보실 수 있습니다.<br>
			 - 공간에 대한 정보를 수정 및 삭제할 수 있습니다.<br>
			 - 이용자가 올린 Q&A를 확인 할 수 있으며 답변 등록이 가능합니다.<br>
			 </h5><br><br>
			
			<h4>• User Info</h4>
			<h5>
			 - 이용자는 지난 예약과 다가올 예약을 확인 할 수 있습니다.<br>
			 - 이용자는 예약한 공간에 대해 Q&A와 리뷰를 올릴 수 있습니다.
			 </h5>
		</div>
	</div>
	
	<!-- CI 소개 -->
	<div class="mainBox">
		<div class="ci">	
			<h3>CI 소개</h3><br>
			<table width = "750px">
			<tr>
				<td><img alt="너공나공 메인 로고" src="img/ysms_main_logo.png"></td>
			<td><h5>
				‘Space’는 공간의 의미와 더불어 ‘띄우다’라는 의미도 있어 구름을 자연스럽게 떠올릴 수 있습니다.
				이처럼 구름 모양의 아이콘과 하늘색은 흔히 볼 수 있는 하늘을 연상시키며 이는 사용자들의 접근성과 친근함을 표현하였습니다.
				더불어 사용자들끼리 공간을 공유하는 것이 주목적이므로 “너, 나, 공간”을 강조하였습니다.
			</h5></td>
			</tr>
			</table>
		</div>
	</div>
	
	</div>
	</div>
	

	<!-- footer.jsp 불러오기 -->
	<%@ include file="footer.jsp" %>

</body>
</html>