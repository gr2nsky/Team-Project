<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap" rel="stylesheet">
<link rel="stylesheet" href="css/myinfo.css" type="text/css">
<style>

	/* 헤더 CSS */	
	.myinfoHeader {
		text-align: center;
		display: inline-block;
		width: 978px;
		
	}
	
	.myinfoHeader ul {
		background-color: white;
		list-style-type: none;
		overflow: hidden;
		margin-left:5px;
		margin-right:5px;
	}
	
	.myinfoHeader ul li { 
		float: left;
		width : 183px;
		display: inline-block;
	}

	/* 헤더-메뉴바 CSS */		
	.myinfoHeader ul li a {
		display: block;
		background-color: #ACE2F9;
		color: #fff;
		padding: 15px;
		text-decoration: none;
		font-weight: bold;
		font-size: 15px;
	}
	
	.myinfoHeader ul li a:hover {
		background-color: #fff;
		color: #ACE2F9;
	}
</style>
<%@ include file="header.jsp" %>
<!-- <div class="mainBox"> -->
<div class="mainBox">
<div class="myinfoHeader">
	<ul>
		<!-- <li><a href="mainPage.jsp">Logo->클릭시 메인페이지</a></li>	 -->		
		<c:set var="loginedUserID" value="${loginedUserID }" />
		
		<li><a href="myInfoUpdateForm.four">내 정보 확인</a></li>
		<li><a href="myinfo_qna.four">나의 문의 목록 </a></li>
		<li><a href="myinfo_review.four">나의 리뷰 목록</a></li>
		<li><a href="myinfo_rental_scheduled.four">다가오는 예약</a></li>
		<li><a href="myinfo_rental_previous.four">지난 예약</a></li>
	</ul>
</div>
</div>
