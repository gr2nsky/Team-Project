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
		height: 150px;
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
</style>
<title>너의 공간 나의 공간 your space my space</title>
</head>
<body>
<%@ include file="header.jsp" %>
<div class=mainBox>
	<div class="contentBox">
		<div class="textLeft">
			<div class="title" style="font-weight:700;">결제완료 : ${DETAIL.title}</div>
			<div class="titleBottom"></div>
			<div class="share"><img class="sharePhoto" src="${DETAIL.filePath }"/></div>
			<div class="shareIntroduce" style="font-weight:700;">추가 정보</div>
			<div class="underline"></div>
			<table>
				<tr>
					<td class="td_title">수용 인원 : </td>
					<td class="td_content">1명 ~ ${DETAIL.capacity}명</td>
					<td class="td_title">시간당 금액 : </td>
					<td class="td_content">&#8361; <fmt:formatNumber value="${DETAIL.price}" pattern="#,###"/></td>
				</tr>
			</table>
		</div>
	</div>
	
	
</div>
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
<div class=mainBox>
	<div class="contentBox">
		<div class="textLeft">
			<div class="shareIntroduce" style="font-weight:700;">예약 정보</div>
			<div class="underline"></div>
			<table>
				<tr>
					<td class="td_title"> 예약자</td>
					<td class="td_content">${DTO.resName}</td>
					<td class="td_title">전화번호 </td>
					<td class="td_content"> ${DTO.resPhone}  </td>
				</tr>
				<tr>
					<td class="td_title"> 예약일시</td>
					<td class="td_content">${DTO.checkInDate} <br> ${DTO.startTime}:00 ~ ${DTO.endTime}:00</td>
					<td class="td_title">결제금액 </td>
					<td class="td_content">&#8361; <fmt:formatNumber value="${DTO.price }" pattern="#,###"/> </td>
				</tr>
				<tr>
					<td class="td_title">이메일 </td>
					<td class="td_content">${DTO.resEmail}</td>
					<td class="td_title">인원수 </td>
					<td class="td_content">${DTO.resCapacity}</td>
				</tr>
			</table>
		</div>
	</div>
</div>
<%@ include file="footer.jsp" %>

</body>
</html>