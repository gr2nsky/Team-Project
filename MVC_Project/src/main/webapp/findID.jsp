<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="css/find.css" rel="stylesheet" type="text/css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
<meta charset="UTF-8">
<title>아이디 찾기</title>
</head>
<script type="text/javascript">

function cancel(){
	self.close();
}

function emailValCheck(){
	var emailPattern= /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	var email = document.getElementById("inputEmailForm").value;
	
	if(!emailPattern.test(email)) {
	    alert("유효한 이메일을 입력해 주세요.");
    	return false;
    }
}

function findID(){
	if (emailValCheck == false){
		return false;
	}
	var url = "findAccount.four?email=" + document.getElementById("inputEmailForm").value;
	open(url, "findID",
			"roolbar=no, location=no,menubar=no,scrollbars=no,resizable=no,width=300,height=200");
}

</script>
<body>
	<table border="0">
		<tr>
			<td colspan="2" align="center"><h3>아이디 찾기</h3></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				가입시 입력한 이메일로 
				<br>
				아이디를 전송합니다.
			</td>
		</tr>
		<tr>
			<td align="center">이메일 입력</td>
			<td align="right"><input type="text" id="inputEmailForm"></td>
		</tr>
		<tr>
			<td colspan="2" align="center"> 
				<input type="button" onclick="cancel()" value="Cancel" class="btnCancel"> 
				<input type="button" onclick="findID()" value="OK" class="btnOK">
			</td>
		<tr>
		</tr>
	</table>
	
</body>
</html>