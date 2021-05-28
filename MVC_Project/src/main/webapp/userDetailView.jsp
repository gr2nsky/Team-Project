<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap" rel="stylesheet">
<link rel="stylesheet" href="css/myinfo.css" type="text/css">
<meta charset="UTF-8">
<title>내 정보 수정</title>
<style>
	*{
		font-family: 'Nanum Gothic', sans-serif;
		color: #505050;
	}
	table {
		width: 978px;
		padding: 50px;
/* 		border: 1px solid #f0f0f0;  */
		font-size: 18px;
	}
	.td_myinfo{
		height:70px;
		padding:10px;
		font-size: 30px;
		font-weight: 700;
	}
	.td_title{
		width: 170px;
		padding:10px;
		font-size: 20px;
		font-weight: 700;
	}
	.td_content{
		padding:10px;
		font-size: 20px;	
	}
	td{
		padding:10px;
		font-size: 20px;
	}
	input[type="text"],
	input[type="password"]{
		height: 25px;
		padding: 5px;
		width: 300px;
		font-size:18px;
		background-color: #fff;
		
		border: none;
 /*		border-radius: 7px; */
	}
	button{
		margin: 0;
		padding: 10px;
		font-size: 15px;
		
		text-align: center;
		text-decoration: none;
		background-color: #dcdcdc;
		
		border: none;
		border-radius: 10px;
		
		display: inline-block;
		width: 130px;
	}
	input[type="button"]{
		margin: 0;
		padding: 13px;
		font-size: 17px;
		color: #fff;
		font-weight:700;
		
		text-align: center;
		text-decoration: none;
		background-color: #ace2f9;
		
		border: none;
		border-radius: 10px;
		
		display: inline-block;
		width: 130px;
	}
	input[type="button"]:hover{
		padding: 12px;
		color: #ace2f9;
		background-color: #fff;
		border: 1px solid #ace2f9;
	}
	#noBorder{width: 200px; height: 25px;	padding: 10px; 
		color: #505050; font-size:17px;	background-color: #fff;	border: 1px solid #dcdcdc;}
</style>
</head>
<script type="text/javascript">
	function back(){
		window.history.back();
	}
</script>
<body>
<%@ include file="header.jsp" %>
<div class="mainBox">
	<div class="contentBox">
		<form name="signUpForm" action="signUpInput.four" method="post" enctype="multipart/form-data" accept-charset="UTF-8">
		<table align="center">
			<tr>
				<td colspan="3" align="left" valign="top" class="td_myinfo"><div style="margin-bottom:10px;">회원정보</div>
				<div class="underline" style="width:150px"></div>
				</td>
			</tr>
			<tr>
				<td align="left" class="td_title">아이디</td>
				<td colspan="2" align="left" class="td_content">
				<input type="text" name="id" id="inputIdForm" value="${DTO.id}"  maxlength="12" disabled="disabled">
				</td>
			</tr>

			<tr>
				<td align="left" class="td_title">이름</td>
				<td colspan="2" align="left" class="td_content">
				<input type="text" name="name" value="${DTO.name }" disabled="disabled">
				</td>
			</tr>
			<tr>
				<td valign="top" align="left" class="td_title">사진 등록</td>
				<td align="left" class="td_content" width="300px">
				<div class="user">
				<img class="userProfile" src="${DTO.filePath }"/></div></td>
			</tr>
			<tr>
				<td align="left" class="td_title">연락처</td>
				<td colspan="2" align="left" class="td_content">
				<input type="text" name="phone1" value="${phone1 } - ${phone2 } - ${phone3 }" size="9" maxlength="4" disabled="disabled"> 
				</td>
			</tr>
			<tr>
				<td align="left" class="td_title">이메일</td>
				<td colspan="2" align="left" class="td_content"><input type="text" name="email" id="inputEmailForm" value="${DTO.email }" disabled="disabled">
			</tr>
			<tr>
				<td align="left" class="td_title">생년월일</td>
				<td colspan="2" align="left" class="td_content">
				<input type="text" name="birthDay" value="${DTO.birthday }"  disabled="disabled"><a class="aBirth"></a> 
				</td>
			</tr>
			<tr>
				<td colspan="3" align="right"><input type="button" value="뒤로가기" onclick="back()" class="button">
				</td>
			</tr>
		</table>
		</form>
	</div>
</div>
<br>
<br>
<br>
<br>
	<%@ include file="footer.jsp" %>
</body>
</html>