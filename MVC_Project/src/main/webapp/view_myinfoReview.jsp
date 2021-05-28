<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap" rel="stylesheet">
<link rel="stylesheet" href="css/myinfo.css" type="text/css">
<title>${loginedUserID}님의 리뷰 목록 </title>
</head>
<style>
	*{
		font-family: 'Nanum Gothic', sans-serif;
		color: #505050;
	}
	.text_line {
		width:150px;
	    overflow:hidden;
	    text-overflow:ellipsis;
	    white-space:nowrap;
	}
	.text_contentline {
		width:330px;
	    overflow:hidden;
	    text-overflow:ellipsis;
	    white-space:nowrap;
	}
	
	#myReview{
		margin-left:50px;
		border-collapse:collapse;
		width:900px;
/* 		border: 1px solid #f0f0f0;  */
	}
	#myReview th{
		padding: 10px;
		font-size:16px;
		background-color: #fbedaa;
	}
	#myReview td{
		padding: 10px;
		border-left:none;
		border-top: 1px solid #f0f0f0; 
	}
	
	button{
		margin: 5px;
		padding: 12px;
		display: inline-block;
		
		font-size: 12px;
/* 		font-weight:700; */
		
		text-align: center;
		text-decoration: none;
		background-color: #f0f0f0;
/* 		background-color: #ace2f9; */
		
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
		<div class="textLeft" style="margin:20px 0px 40px 50px; font-size:25px; font-weight:700;">나의 리뷰 목록</div>
		<table id="myReview" align="center">
			<tr>
				<th>예약<br>번호</th>
				<th style="width:150px;">공간이름</th>
				<th style="width:330px;">리뷰내용</th>
				<th>공간<br>점수</th>
				<th>게시일</th>
				<th></th>
			</tr>
		<c:choose>
		<c:when test="${!empty myinfoReviewList }">
		<c:forEach items="${myinfoReviewList }" var="myReviewDto"> 
		<!-- 반복되는 곳  -->
			<tr>
				<td>${myReviewDto.rentalNo }</td>
				<td><div class="text_line">${myReviewDto.reviewPlaceName }</div></td>
				<td><div class="text_contentline">${myReviewDto.reviewContent }</div></td>
				<td>${myReviewDto.reviewScore }</td>
				<td>${myReviewDto.reviewUpdateDate }</td>
				<td><a href="javascript:openReview('detail_review.four?rentalNo=${myReviewDto.rentalNo }')">
				<button>자세히보기</button></a></td>
			</tr>
		</c:forEach>
		</c:when>
		<c:otherwise>
			<tr>
				<td colspan="2">작성하신 리뷰가 없습니다.</td>
			</tr>
		</c:otherwise>
		</c:choose>
		<tr><td colspan="6" align="center">
		 	<c:forEach items="${myinfoReviewPageList }" var="myinfoReviewPage">
			<a href="myinfo_review.four?myinfoReviewPage=${myinfoReviewPage }">${myinfoReviewPage }</a>
			</c:forEach>
		</td></tr>
		</table>
	</div>
</div>

</body>
</html>