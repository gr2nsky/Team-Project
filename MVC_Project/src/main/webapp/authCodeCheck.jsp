<%@page import="com.ysms.common.ShareVar_login"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<%
String result = "false";
	String authCode = ShareVar_login.authEamilCode;
	String inputedCode = request.getParameter("inputedCode");
	
	if(authCode.equals(inputedCode)){
		result = "true";
	}
%>

<script type="text/javascript">
	var result = "true";
	var inputedCode = "<%=inputedCode%>";
	
	if (inputedCode == ""){
		alert("인증번호가 올바르지 않습니다.");
		opener.document.getElementById("authPass").value = "false";
		self.close();
	}
	if(<%=result%> == true){
		alert("인증이 완료되었습니다.");
		opener.document.getElementById("authPass").value = "true";
		console.log("<%=result%>");
		opener.document.signUpForm.inputEmailForm.readOnly = true; 
		opener.document.signUpForm.eamilAuthBtn.readOnly = true;
		opener.document.signUpForm.authCodeCheckBtn.disabled = true;
		opener.document.signUpForm.inputAuthCode.disabled = true;
		self.close();
	} else {
		alert("인증번호가 올바르지 않습니다.");
		opener.document.getElementById("authPass").value = "false";
		self.close();
	}

</script>
<body>

</body>
</html>