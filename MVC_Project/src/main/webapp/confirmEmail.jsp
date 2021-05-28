<%@page import="java.io.PrintWriter"%>
<%@page import="com.ysms.command.AuthEmailRequestCommand"%>
<%@page import="com.ysms.common.ShareVar_login"%>
<%@page import="com.ysms.dao.Dao_Login"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>   
<!DOCTYPE html>
<html>
<head>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap" rel="stylesheet">
<link rel="stylesheet" href="css/confirm.css" type="text/css">
<meta charset="UTF-8">
<title>Email 확인</title>
</head>

<%! String email = ""; %>
<% email = request.getParameter("email"); %>

<script type="text/javascript">

	function cancel() {
		self.close();
	}

	function requestAuthEamil(){
		var url = "requestAuthEmail.four?email=" + "<%=email%>";
		open(url, "authEmailSend",
				"toolbar=no, location=no,menubar=no,scrollbars=no,resizable=no,width=300,height=200");
	}
	
</script>
<body>


	<div class="mainBox">
	<div class="contentBox">
	
	<c:set var="emailDupleCheckResult" value="${emailDupleCheckResult }" />
	<c:choose>
		<c:when test="${emailDupleCheckResult == 'useable' }">
			<a>인증메일을 전송중입니다. 팝업창을 허용해주세요.</a>
			<script type="text/javascript">requestAuthEamil();</script>
		</c:when>
		<c:otherwise>
			<h4><!-- ${inputedEmail } :  -->사용 불가능한 email입니다.</h4>
		</c:otherwise>
	</c:choose>
	<br><br>
	<button type="button" onclick="cancel()" class="btnCancel">취소</button>
	
	</div>
	</div>

</body>
</html>