<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap" rel="stylesheet">
<link rel="stylesheet" href="css/spaceDetailView_QnaReview.css" type="text/css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style>
	*{
		font-family: 'Nanum Gothic', sans-serif;
		color: #505050;
	}
	iframe{
		overflow-x:hidden;
		overflow:auto;
		width:100%;
		min-height:150px;
		margin-bottom: 30px;
	}
	textarea{
		width: 100%;
		height: 300px;
		padding: 10px;
		box-sizing: border-box;
		border: none;
		font-size: 20px;
		resize: none;
		background-color: #fff;
	}
	table{
		width: 750px;
		padding: 10px;
/* 		border: 1px solid #f0f0f0; */
		font-size: 18px;
	}
	.td_content{
		width:200px;
		margin: 0;
		padding: 5px;
	}
	.td_title{
		width:170px;
		margin: 0;
		padding: 5px;
		font-weight: 700;
	}
	hr{
		border-top: 1px solid #F9FFFF;
	}
	.shareUnderline{
		background-color: #ace2f9;
		width:120px;
		height:5px;
		margin-bottom:30px;
	}
</style>
<title>${DETAIL.title} : 너의 공간 나의 공간 your space my space </title>
</head>
<script type="text/javascript"> 
	function calcQnaHeight(){
	 //find the height of the internal page
	 var the_height=
	 document.getElementById('qna_iframe').contentWindow.
	 document.body.scrollHeight;
	 //change the height of the iframe
	 document.getElementById('qna_iframe').height=
	the_height;
	 //document.getElementById('the_iframe').scrolling = "no";
	 document.getElementById('qna_iframe').style.overflow = "hidden";
	}
	function calcReviewHeight(){
		 //find the height of the internal page
		 var the_height=
		 document.getElementById('review_iframe').contentWindow.
		 document.body.scrollHeight;
		 //change the height of the iframe
		 document.getElementById('review_iframe').height=
		the_height;
		 //document.getElementById('the_iframe').scrolling = "no";
		 document.getElementById('review_iframe').style.overflow = "hidden";
		}
</script>
<body>
<%@ include file="header.jsp" %>
<div class=mainBox>
	<div class="contentBox">
		<div class="textLeft">
		<div class="shareIntroduce" style="font-weight:700;">공간 정보</div>
		<div class="shareUnderline"></div>
			<div style="margin-bottom:50px; font-weight:700; font-size:40px;">${DETAIL.title}</div>
			<div class="share"><img class="sharePhoto" src="${DETAIL.filePath }"/></div>
			<div class="shareIntroduce" style="font-weight:700;">공간 소개</div>
			<div class="shareUnderline"></div>
			<textarea name="introduce" readonly="readonly" disabled="disabled">${DETAIL.introduce}</textarea>
			<div class="shareIntroduce" style="font-weight:700;">추가 정보</div>
			<div class="shareUnderline"></div>
			<table>
				<tr>
					<td class="td_title">수용 인원 : </td>
					<td class="td_content">1명 ~ ${DETAIL.capacity}명</td>
					<td class="td_title">시간당 금액 : </td>
					<td class="td_content">&#8361; <fmt:formatNumber value="${DETAIL.price}" pattern="#,###"/></td>
				</tr>
				<tr>
				<td style="padding-left:5px;"colspan="4"><hr></td>
				</tr>
				<tr>
					<td class="td_title">시작 가능 시간 : </td>
					<td class="td_content">${DETAIL.startTime }시</td>
					<td class="td_title">종료 시간 : </td>
					<td class="td_content">${DETAIL.endTime }시</td>
				</tr>
			</table>
					<!-- 예약하기 페이지로 이동 -->
			<form action="reservation.four" method="post">
			<input type="hidden" name="sNo" value="${sNo}">
			<!-- <input type="submit" value="예약하기"> -->
			</form>
		</div>
	</div>
</div>
<br><br>
<div class=mainBox>
	<div class="map">
		<div class="textLeft">
			<div style="margin-left:20px; margin-top:20px; font-size:25px; font-weight:700;">${DETAIL.title}<div class="underline" style="margin-top:10px;"></div></div>
			<div style="margin-left:20px; margin-bottom:20px; font-size:17px; "> - ${DETAIL.address1}${DETAIL.address2}</div>
		</div>
		<div id="map" style="width:100%;height:350px; text-align:center;"></div>
		<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=4c1cf1fe1c2b704ea638ef5d32b31c60&libraries=services"></script>
		<script>
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		    mapOption = {
		        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
		        level: 3 // 지도의 확대 레벨
		    };  
		// 지도를 생성합니다    
		var map = new kakao.maps.Map(mapContainer, mapOption); 
		// 주소-좌표 변환 객체를 생성합니다
		var geocoder = new kakao.maps.services.Geocoder();
		// 주소로 좌표를 검색합니다
		geocoder.addressSearch('${DETAIL.address1}', function(result, status) {
		    // 정상적으로 검색이 완료됐으면 
		     if (status === kakao.maps.services.Status.OK) {
		        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
		        // 결과값으로 받은 위치를 마커로 표시합니다
		        var marker = new kakao.maps.Marker({
		            map: map,
		            position: coords
		        });
		        // 인포윈도우로 장소에 대한 설명을 표시합니다
		        var infowindow = new kakao.maps.InfoWindow({
		            content: '<div style="width:150px;text-align:center;padding:6px 0;">${DETAIL.title}</div>'
		        });
		        infowindow.open(map, marker);
		        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
		        map.setCenter(coords);
		    } 
		});    
		</script>
	</div>	
</div>
<%// 리뷰와 큐앤에이 %>
	<div style="text-align: center; margin-bottom:50px;">
	<iframe src="qna.four?place_no=${DETAIL.place_no }" id="qna_iframe" onload="calcQnaHeight();" name="" title="" frameborder="0" scrolling="no"></iframe>
	<iframe src="review.four?place_no=${DETAIL.place_no }" id="review_iframe" onload="calcReviewHeight();" frameborder="0" scrolling="no"></iframe>
	</div>
		<!-- 예약하기 페이지로 이동 -->
	<form action="reservation.four" method="post">
	<input type="hidden" name="sNo" value="${sNo}">
	<script type="text/javascript">
		/* $(function() {
			var offset = $("#sidebar").offset();
			var topPadding = 300;
			$(window).scroll(function() {
				if ($(window).scrollTop() > offset.top) {
					$("#sidebar").stop().animate({
						marginTop: $(window).scrollTop() - offset.top + topPadding
					});
				} else {
					$("#sidebar").stop().animate({
						marginTop: 0
					});
				};
			});
		}); */
		$(document).ready(function() {
			// 기존 css에서 플로팅 배너 위치(top)값을 가져와 저장한다.
			var floatPosition = parseInt($("#floatMenu").css('top'));
			// 250px 이런식으로 가져오므로 여기서 숫자만 가져온다. parseInt( 값 );
			$(window).scroll(function() {
				// 현재 스크롤 위치를 가져온다.
				var scrollTop = $(window).scrollTop();
				var newPosition = scrollTop + floatPosition + "px";
				/* 애니메이션 없이 바로 따라감
				 $("#floatMenu").css('top', newPosition);
				 */
				$("#floatMenu").stop().animate({
					"top" : newPosition
				}, 200);
			}).scroll();
		});
		</script>
	<div id="floatMenu" style="position: absolute; " >	
      <button type="submit" class="button" >예약하기</button>  
  </div>
	</form> 
	<%@ include file="footer.jsp" %>
</body>
</html>