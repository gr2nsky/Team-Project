<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>   
<style>
 <!-- 폰트 적용 -->
<link rel="stylesheet" href="css/spaceDetailView_QnaReview.css" type="text/css">
<link rel="preconnect" href="https://fonts.gstatic.com">



	/* 전체 글씨 속성 적용 */
	*{
		font-family: 'Nanum Gothic', sans-serif;
		color: #505050;
	}

	/*  우측 상단 로그인 버튼 CSS */
	.login{
		text-align: right;
		margin-right: 30px;
		font-weight: bold;
		
	}
	
	/*  우측 상단 로그인 버튼-글씨 CSS */
	.login a {
		text-decoration: none;
		color: #000;
		margin: 10px;
	}

	/* 메인 로고 CSS */
	.mainLogo {
		text-align: center;
	}

	/* 메인 로고-이미지 CSS */
	.mainLogo img {
		width: 275px;
	}
	
	/* 메인 박스 CSS */	
	.mainBox {
		display: flex;
		align-items: center;
		justify-content: center;
 	}

	/* 헤더 CSS */	
	.header {
		text-align: center;
		display: inline-block;
		width: 978px; 
	}
	
	.header ul {
		background-color: white;
		list-style-type: none;
		overflow: hidden;
		margin-left: 50px;
		margin-right: 50px;
	}
	
	.header ul li { 
		float: left;
		width : 200px;
		display: inline-block;
	}

	/* 헤더-메뉴바 CSS */		
	.header ul li a {
		display: block;
		background-color: white;
		color: #7F7F7F;
		padding: 10px;
		text-decoration: none;
		font-weight: bold;
		font-size: 20px;
	}
	
	.header ul li a:hover {
		background-color: #ACE2F9;
		color: white;
	}
	
</style>
<div class="login">
		<c:set var="sginUpResult" value="${param.sginUpResult }"/>
	<c:if test="${sginUpResult == 'false' }">
		<script type="text/javascript"> alert("회원가입에 오류가 발생했습니다")</script>
	</c:if>
	<!-- 회원가입시 중복아이디 체크한 세션값이 남아있다면, 메인페이지에서 제거 -->
	<c:set var="duplicate_checked_id" value="${duplicate_checked_id }"/>
	<c:if test="${!empty tryLogout }">
		<script type="text/javascript">sessionStorage.removeItem("duplicate_checked_id");</script>
	</c:if>
	<!-- 회원가입시 pw체크할떄 사용한 세션값이 남아있다면, 메인페이지에서 제거 -->
	<c:set var="pwEqualCheck" value="${pwEqualCheck }"/>
	<c:if test="${!empty pwEqualCheck }">
		<script type="text/javascript">sessionStorage.removeItem("pwEqualCheck");</script>
	</c:if>
	<!-- 로그아웃시 모든 세션 삭제 tryLogout은 로그아웃 작업을 마쳐야 1로 세팅, 수행하지 않으면 null-->
	<c:set var="tryLogout" value="${tryLogout }" />
	<c:if test="${!empty tryLogout }">
		<script type="text/javascript"> 
			alert("로그아웃 되었습니다.")
			sessionStorage.clear();
		</script>
	</c:if>
	<!-- 로그인 성공시 메인페이지에 추가적으로 정보 표시 -->
	<c:set var="loginedUserID" value="${loginedUserID }" />
	<c:if test="${empty loginedUserID }">
			<a href="loginForm.jsp">로그인</a>
	</c:if>
	<c:if test="${!empty loginedUserID }">
		<c:choose>
			<c:when test="${loginedUserID eq 'admin' }">
				<a href="admin_managingUser.four">회원관리</a>
				<a href="mypage.four">마이페이지</a>
				<a href="logout.four">로그아웃</a>
			</c:when>
			<c:otherwise>			
					${loginedUserID}님, 환영합니다.	
					<a href="mypage.four">마이페이지</a>
					<a href="logout.four">로그아웃</a>
			</c:otherwise>	
		</c:choose>
	</c:if>
	<!-- <a href="#">로그인</a> -->
</div>

<div class="mainLogo">
	<a href="mainPage.jsp"><img alt="너공나공 메인 로고" src="img/ysms_main_logo.png"></a> <!-- Logo->클릭시 메인페이지 -->
</div>
<div class="mainBox">
<div class="header">
	<ul>
		<!-- <li><a href="mainPage.jsp">Logo->클릭시 메인페이지</a></li>	 -->
		<li><a href="introduction.jsp">너공나공?</a></li>
		<li><a href="SearchPlacePage.four">공간 목록</a></li>
		<li><a href="list.four">공간 나눔 목록</a></li>
		
		<c:set var="loginedUserID" value="${loginedUserID }" />
		<c:choose>
			<c:when test="${loginedUserID eq 'admin'}">
				<li><a href="announce_admin.four">공지사항</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="announce_user.four">공지사항</a></li>
			</c:otherwise>	
		</c:choose>
	</ul>
</div>
</div>