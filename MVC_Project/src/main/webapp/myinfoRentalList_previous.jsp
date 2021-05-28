<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<head>
<meta charset="UTF-8">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap" rel="stylesheet">
<link rel="stylesheet" href="css/myinfoRentalList.css" type="text/css">
<html>
<title>My Rental List : Previous Reservation </title>
</head>

<style>
	*{
		font-family: 'Nanum Gothic', sans-serif;
		color: #505050;
	}
	.table_outter{
		width: 970px;
		padding: 10px;
/*   		border: 1px solid #f0f0f0;  */
		font-size: 18px;
	}
 	.table_inner{
		width: 950px;
		padding: 15px;
		font-size: 18px;
	} 
	th{
		padding: 0px;
		margin-botton: 10px;
	}
	td{
		font-size:15px;
		padding: 5px;
	}
	.td_title{
		text-align: left;
		font-size:15px;
		padding: 7px;
		font-weight:700;
		width:70px;
	}
	.td_content{
		text-align: left;
		font-size:15px;
		padding: 7px;
		width: 150px;
		
	}
	
	hr{
		border-top: 1px solid #f0f0f0;
	}
	.button_rental{
		margin: 3px;
		padding: 8px;
		display: inline-block;
		width: 65px;
		
		font-size: 12px;
 		color: #fff; 
		
		text-align: center;
		text-decoration: none;
		background-color: #91D8FA;
		
		border: none;
		border-radius: 10px;
	}
	
	.button_cancel{
		margin: 3px;
		padding: 8px;
		display: inline-block;
		width: 65px;
		
		font-size: 12px;
 		color: #fff; 
		
		text-align: center;
		text-decoration: none;
		background-color: #FFCD28;
		
		border: none;
		border-radius: 10px;
	}
	
	.button_myReview{
		margin: 3px;
		padding: 13px;
		display: inline-block;
		width: 135px;
		
		font-size: 13px;
		color: #6e6e6e;
		font-weight:700;
		
		text-align: center;
		text-decoration: none;
		background-color: #dcdcdc;
		
		border: none;
		border-radius: 10px;
	}
	
	.button_writeReview{
		margin: 3px;
		padding: 13px;
		display: inline-block;
		width: 135px;
		
		font-size: 13px;
		color: #fff;
		font-weight:700;
		
		text-align: center;
		text-decoration: none;
		background-color: #ace2f9;
		
		border: none;
		border-radius: 10px;
	}
	
	
	a:link {color: #828282; text-decoration: none; }
	a:visited {color: #828282; text-decoration: none; }
	a:hover {text-decoration: underline; color:#ace2f9;}
</style>
<script type="text/javascript">
	function openReview(window) {
		open(window, "confirm",
		"roolbar=no,location=no,menubar=no,scrollbars=no,resizable=no,width=500,height=700");
	}
</script>
<body>
<%@ include file="myinfoHeader.jsp" %>
<div class="mainBox">
	<div class="contentBox">
		<div class="textLeft" style="margin-bottom:20px;">
			<a href="myinfo_rental_scheduled.four">예정된 예약</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>이전 예약</strong>
		</div>
		<hr>
		<table class="table_outter">
		<c:choose>
			<c:when test="${!empty myinfoRentalPreviousList }">
			<c:forEach items="${myinfoRentalPreviousList }" var="rentalPVDto" varStatus="status">
			<!-- 반복되는 곳  -->
			<tr><td>
			<hr>
				<table class="table_inner">
					<tr>
						<th rowspan="4"><div class="rentalList">
							<img class="rentalListPhoto" src="save/${rentalPVDto.rentalPhoto }"></div></th>
						<th colspan="3" align="left">${rentalPVDto.rentalTitle }</th>
						<th align="right">
							<c:choose>
								<c:when test="${empty rentalPVDto.rentalCancellationDate }">
									<button class="button_rental" disabled="disabled"> 예약완료 </button><button class="button_rental" disabled="disabled"> 결제완료 </button>					
								</c:when>
								<c:otherwise>
									<button class="button_cancel" disabled="disabled"> 예약취소 </button><button class="button_cancel" disabled="disabled"> 환불완료 </button>
								</c:otherwise>
							</c:choose>
						</th>
					</tr>
					<tr>
						<td class="td_title">이용일자 :</td>
						<td class="td_content">${rentalPVDto.checkInDate }</td>
						<td class="td_title">예약일자 :</td>
						<td class="td_content">${rentalPVDto.rentalDate }</td>
					</tr>
					<tr>
						<td class="td_title">이용시간 :</td>
						<td class="td_content">${rentalPVDto.rentalStartTime }시 ~ ${rentalPVDto.rentalEndTime }시</td>
						<td class="td_title">예약번호 :</td>
						<td class="td_content">${rentalPVDto.rentalNo }</td>
					</tr>
					<tr>
						<td class="td_title">총 금액 :</td>
						<td class="td_content">&#8361; <fmt:formatNumber value="${rentalPVDto.rentalPrice }" pattern="#,###"/></td>
			 			<c:choose>
							<c:when test="${!empty rentalPVDto.rentalCancellationDate }">
							<td class="td_title">취소일자 :</td>
							<td class="td_content">${rentalPVDto.rentalCancellationDate }</td>
							</c:when>
							<c:when test="${empty rentalPVDto.reviewSubmitDate }">
							<td colspan="2" align="right">
								<a href="javascript:openReview('write_review.four?rentalNo=${rentalPVDto.rentalNo }')">
								<button class="button_writeReview">리뷰쓰기</button></a>
								<div style="padding-left:100px; padding-top:5px; text-align:center; font-size:9px; color:#6e6e6e">나눔 받은 공간은 어떠셨나요?? <br> 당신의 감상을 들려주세요.</div>
							</td>
							</c:when>				
							<c:otherwise>
							<td colspan="2" align="right"><a href="javascript:openReview('detail_review.four?rentalNo=${rentalPVDto.rentalNo }')">
							<button class="button_myReview">내가 쓴 리뷰 보기</button></a></td>
							</c:otherwise>
						</c:choose>
					</tr>
				</table>
			</td></tr>
			</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="2">이전 예약이 없습니다.</td>
				</tr>
			</c:otherwise>
		</c:choose>
			<tr><td align="center">
			<hr><br><br>
				<c:forEach items="${rentalPreviousPageList }" var="rentalPreviousPage">
				<a href="myinfo_rental_previous.four?rentalPreviousPage=${rentalPreviousPage }">${rentalPreviousPage }</a>
				</c:forEach>
			</td></tr>
		</table>
	</div>
</div>
	<%@ include file="footer.jsp" %>
</body>
</html>