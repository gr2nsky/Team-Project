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
		font-size: 25px;
		font-weight: 700;
	}
	.td_title{
		width: 170px;
		padding:10px;
		font-size: 17px;
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
		height: 23px;
		padding: 7px;
		font-size:18px;
		background-color: #fff;
		border: 1px solid #dcdcdc;		
	}
	button{
		margin: 0;
		padding: 10px;
		font-size: 13px;
		text-align: center;
		text-decoration: none;
		background-color: #dcdcdc;
		border: none;
		border-radius: 10px;
		display: inline-block;
		width: 100px;
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
		height: 50px;
	}
	input[type="button"]:hover{
		padding: 12px;
		color: #ace2f9;
		background-color: #fff;
		border: 1px solid #ace2f9;
		height: 50px;
	}
</style>
</head>
<script type="text/javascript">
	<c:if test="${!empty updateTty}">
		<c:if test="${!empty updateResult}">
			if(updateResult == true){
				alert("정보가 성공적으로 수정되었습니다.");
			}else {
				alert("정보 수정에 실패하였습니다..");
			}
		</c:if>
	</c:if>
	function validationCheck(){
		var form = document.signUpForm;
		var pw1 = form.pw1.value;
		var pw2 = form.pw2.value;
		//phoneNo check
 		var phonePattern = /^\d{3}-\d{3,4}-\d{4}$/;
		var phone = form.phone1.value + "-" + form.phone2.value + "-" + form.phone3.value;
		if (pw1 != "" || pw2 != ""){
			if (document.signUpForm.hp.value = "false"){
				alert("비밀번호 일치확인을 해 주세요.");
				return false;
			}
		}
		if(!check(phonePattern, phone, "유효하지 않은 핸드폰 번호입니다.")) {
			return false;
		} 
		alert("회원정보 수정이 성공적으로 완료되었습니다.");
		form.submit(); 
	}
	function check(pattern, taget, message) {
		if(pattern.test(taget)) {
	    	return true;
	    }
	    alert(message);
	    taget.focus();
	}
	function pwEqualCheck(){
		var form = document.signUpForm;
		var passwordPattern = /^(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
		var pw1 = form.pw1.value;
		var pw2 = form.pw2.value;
		if(!check(passwordPattern, pw1, "비밀번호는 8~15자리의 영문, 숫자, 특수문자로 조합해야 합니다.")) {
			return false;
		}
		if (pw1 != pw2) {
			alert("비밀번호가 일치하지 않습니다.");
			sessionStorage.removeItem("pwEqualCheck");
			document.getElementById('inputPwForm1').readOnly = false;
			document.getElementById('inputPwForm2').readOnly = false;
			return false;
		}
		document.getElementById('inputPwForm1').readOnly = true;
		document.getElementById('inputPwForm2').readOnly = true;
		form.hp.value = "true";
		alert("사용가능한 비밀번호입니다.");
	}
</script>
<body>
	<%@ include file="myinfoHeader.jsp" %>
<div class="mainBox">
	<div class="contentBox">
		<form name="signUpForm" action="myInfoUpdate.four" method="post" enctype="multipart/form-data" accept-charset="UTF-8">
		<table align="center">
			<tr>
				<td colspan="3" align="left" valign="top" class="td_myinfo"><div style="margin-bottom:10px;">내 정보 수정</div>
				<div class="underline" style="width:130px"></div>
				</td>
			</tr>
			<tr>
				<td align="left" class="td_title">아이디</td>
				<td colspan="2" align="left" class="td_content">
				<input type="text" name="id" id="inputIdForm" value="${DTO.id}"  maxlength="12" disabled="disabled">
				</td>
			</tr>
			<tr>
				<td align="left" class="td_title">
					<input type="hidden" id="hp" value="false"> 
					비밀번호 변경
				</td>
				<td colspan="2" align="left" class="td_content"><input type="password" name="pw1" id="inputPwForm1" maxlength="15"></td>
			</tr>
			<tr>
				<td></td>
				<td colspan="2" valign="top" align="left" class="td_content"><input type="password" name="pw2" id="inputPwForm2"  maxlength="15">
				<button onclick="pwEqualCheck()" type="button" class="btnChk">비밀번호 확인</button>
				<input type="hidden" name="pwCheck" id="pwCheck" value="false">
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
				<div class="user"><img class="userProfile" src="userPhoto/${DTO.filePath }"/></div></td>
				<td align="left" valign="bottom">
				<input type="file" name="uploadPhoto" class="file-input" >
				</td>
			</tr>
			<tr>
				<td align="left" class="td_title">연락처</td>
				<td colspan="2" align="left" class="td_content">
				<input type="text" name="phone1" value="${phone1 }" size="9" maxlength="4">
				<input type="text" name="phone2" value="${phone2 }" size="9" maxlength="4">
				<input type="text" name="phone3" value="${phone3 }" size="9" maxlength="4">
				</td>
			</tr>
			<tr>
				<td align="left" class="td_title">이메일</td>
				<td colspan="2" align="left" class="td_content"><input type="text" name="email" id="inputEmailForm" value="${DTO.email }" size="30" maxlength="30" disabled="disabled">
			</tr>
			<tr>
				<td align="left" class="td_title">생년월일</td>
				<td colspan="2" align="left" class="td_content">
				<input type="text" name="birthDay" value="${DTO.birthday }"  disabled="disabled"><a class="aBirth"></a> 
				</td>
			</tr>
			<tr>
				<td colspan="3" align="right"><input type="button" value="제출" onclick="validationCheck()" class="button">
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