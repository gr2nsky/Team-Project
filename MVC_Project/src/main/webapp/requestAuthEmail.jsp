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
String authCode = ShareVar_login.authEamilCode;
%>

<script type="text/javascript">

	alert("인증메일 전송 완료");
	self.close();
	opener.close();

</script>
<body>
</body>
</html>