<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<script type="text/javascript">
	function suc(){
		alert("이메일이 성공적으로 전송되었습니다.");
		opener.cancel();
		self.close();
	}
	function fail(){
		alert("이메일이 전송에 실패하였습니다.");
		self.close();
	}
</script>

<c:set var="result" value="${mailSendReuslt}"/>
<c:choose>
	<c:when test="${result eq 'true'}">
		<script type="text/javascript">suc();</script>
	</c:when>
	<c:otherwise>
		<script type="text/javascript">fail();</script>
	</c:otherwise>
</c:choose>



<body>
</body>
</html>