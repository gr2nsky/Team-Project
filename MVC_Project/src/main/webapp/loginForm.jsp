<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>   
<!DOCTYPE html>
<html>
<head>
<link href="css/loginForm.css" rel="stylesheet" type="text/css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
	<meta charset="UTF-8">
	<title>Login</title>
</head>
<script type="text/javascript">
	


	<!-- 입력폼 공백시 알림창으로 알려줌 -->
	function checkNull(){
		var form = document.loginForm;
		if(!form.userID.value){
			alert("아이디를 입력해 주세요.");
			document.loginForm.userID.value.focus();
			return false;
		}
		if(!form.userPW.value){
			alert("비밀번호를 입력해 주세요.");
			document.loginForm.userID.value.focus();
			return false;
		}
		form.submit();
	}
	<!-- 로그인 실행한 결과값을 매개변수로 받아 분기 -->
	function checkLogined(loginedUserId){
		if(loginedUserId == "null"){
			alert("아이디와 비밀번호를 정확히 입력해 주세요.");
			return false;
		} else {
			location.href="mainPage.jsp";
			return false;
		}
	}
	
	//새 창을 띄워서 id, pw 찾기로 이동
	function findID(){
		var url = "findID.jsp";
		open(url, "findID",
				"roolbar=no, location=no,menubar=no,scrollbars=no,resizable=no,width=300,height=200");
	}
	function findPW(){
		var url = "findPW.jsp";
		open(url, "findPW",
				"roolbar=no, location=no,menubar=no,scrollbars=no,resizable=no,width=300,height=200");
	}
</script>
<body>
<%@ include file="header.jsp" %>
	<!-- 
		로그인을 성공하든 실패하든 다시 해당 파이지로 돌아오기 때문에
		한 페이지에서 로그인 작업 수행여부를 판단하기 위해 tryLogin 변수를 토큰으로사용하였음.
		
		이후 checkLogined()를 호출해 
		로그인 성공시 로그인한 아이디를 세션값에 저장하고 실패시 여기에 남고, 성공시 메인페이지로 이동하도록
		로그인 프로세스 실행 결과값인 아이디를 넘겨줌
		
		로그인 수행시 : tryLogin = 1
		로그인 수행 안함 : tryLogin = null
		
		로그인 성공시 : loginedUserId not null
		로그인 실패시 : loginedUserId is null
	 -->
	 <div class="mainBox">
	<div class="contentBox">
	 <div class="href" align="center">
	<c:set var="tryLogin" value="${tryLogin }" />
	<c:if test="${!empty tryLogin}">
		<script type="text/javascript">
			var loginedUserId = '<%=(String)session.getAttribute("loginedUserID")%>';
			checkLogined(loginedUserId)
		</script>
	</c:if>
	
	<br><br><br><br>
	<h1>로그인</h1>
	
	<table border="0" style="margin-left: auto; margin-right: auto;">
		<form action="login_try.four" name="loginForm" method="post">
			<tr>
				<td align="center" ><input type="text" name="userID" size="33" placeholder="아이디" style="height : 30px;"></td>
			</tr>
			<tr>
				<td height="5px"></td>
			</tr>
			<tr>
				<td align="center"  ><input type="password" name="userPW" size="33" placeholder="비밀번호" style="height : 30px;"></td>
			</tr>
			<tr>
				<td height="5px"></td>
			</tr>
			<tr>
				<td colspan="2" align="center" ><input type="button" value="login" onclick="checkNull()" class="button"></td>
			</tr>
			<tr>
				<td height="5px"></td>
			</tr>
			<tr>
				<td align="center">
				<div class="link" style="font-size: 15px; ">
				<a href="signUpForm.jsp" >회원가입</a> |
				<a a href='javascript:void(0);' onclick="findID()" >아이디 찾기</a> |
				<a a href='javascript:void(0);' onclick="findPW()" >비밀번호 찾기</a> 
				</div>				
				</td>
			</tr>
		</form>
	</table>
	
	<br>
	
	</div>
	</div>
	</div>
	<br><br><br><br><br><br><br><br>
	
<%@ include file="footer.jsp" %>
</body>
</html>