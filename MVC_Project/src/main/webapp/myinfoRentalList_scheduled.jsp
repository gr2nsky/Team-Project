<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap" rel="stylesheet">
<link rel="stylesheet" href="css/myinfoRentalList.css" type="text/css">
<title>My Rental List : Scheduled Reservation </title>
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
/* 		font-weight:700; */
		
		text-align: center;
		text-decoration: none;
		background-color: #91D8FA;
/* 		background-color: #ace2f9; */
		
		border: none;
		border-radius: 10px;
	}
	
	.button_detail{
		margin: 3px;
		padding: 13px;
		display: inline-block;
		width: 135px;
		
		font-size: 13px;
		/* color: #fff; */
		font-weight:700;
		
		text-align: center;
		text-decoration: none;
		background-color: #dcdcdc;
		
		border: none;
		border-radius: 10px;
	}
	
	
	a:link {color: #828282; text-decoration: none; }
	a:visited {color: #828282; text-decoration: none; }
	a:hover {text-decoration: underline; color:#ace2f9;}
</style>
<script type="text/javascript">
function moveToDetail(btn){
	hiddenForm = document.hf;
	document.getElementById("hn").value = btn.value;
	hiddenForm.submit();
}
</script>
<body>
<%@ include file="myinfoHeader.jsp" %>
<div class="mainBox">
	<div class="contentBox">
		<div class="textLeft" style="margin-bottom:20px;">
			<strong>예정된 예약</strong> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="myinfo_rental_previous.four">이전 예약</a>
		</div>
		<hr>
		<table class="table_outter">
		<c:choose>
			<c:when test="${!empty myinfoRentalScheduledList }">
			<c:forEach items="${myinfoRentalScheduledList }" var="rentalSCDto">
			<!-- 반복되는 곳  -->
			<tr><td>
			<hr>
				<table class="table_inner">
					<tr>
						<th rowspan="4">
							<div class="rentalList">
								<img class="rentalListPhoto" src="save/${rentalSCDto.rentalPhoto }">
							</div>
						</th>
						<th colspan="3" align="left">${rentalSCDto.rentalTitle }</th>
						<th align="right"><button class="button_rental" disabled="disabled">예약완료</button><button class="button_rental" disabled="disabled">결제완료</button></th>
					</tr>
					<tr>
						<td class="td_title">이용일자 :</td>
						<td class="td_content">${rentalSCDto.checkInDate }</td>
						<td class="td_title">예약일자 :</td>
						<td class="td_content">${rentalSCDto.rentalDate }</td>
					</tr>
					<tr>
						<td class="td_title">이용시간 :</td>
						<td class="td_content">${rentalSCDto.rentalStartTime }시 ~ ${rentalSCDto.rentalEndTime }시</td>
						<td class="td_title">예약번호 :</td>
						<td class="td_content">${rentalSCDto.rentalNo }</td>
					</tr>
					<tr>
						<td class="td_title">총 금액 :</td>
						<td class="td_content" colspan="2">&#8361; <fmt:formatNumber value="${rentalSCDto.rentalPrice }" pattern="#,###"/></td>
						<td align="right"><button class="button_detail" value="${rentalSCDto.rentalNo }" onclick="moveToDetail(this)">예약 상세 보기</button></td>
					</tr>
				</table>
			</td></tr>
			</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td class="td_title" colspan="2">예정된 예약이 없습니다.</td>
				</tr>
			</c:otherwise>
		</c:choose>
		<tr><td align="center">
			<hr><br><br>
			<c:forEach items="${rentalScheduledPageList }" var="rentalScheduledPage">
			<a href="myinfo_rental_scheduled.four?rentalScheduledPage=${rentalScheduledPage }">${rentalScheduledPage }</a>
			</c:forEach>
		</td></tr>
		</table>
	</div>
</div>
<form action="paymentResultDetailView.four" method="post" name="hf" >
<input type="hidden" name="hn" value="" id="hn">
</form>
<%@ include file="footer.jsp" %>

</body>
</html>