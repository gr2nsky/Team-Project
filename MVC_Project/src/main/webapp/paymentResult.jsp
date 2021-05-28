<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%

String paymentResult = (String)request.getAttribute("paymentResult");

%>
<script type="text/javascript">
paymentResult = "<%=paymentResult%>"



if (paymentResult == "true"){
	window.opener.location.href="paymentResultCheck.four";
	alert("결제정보가 성공적으로 등록되었습니다.");
	self.close();
} else {
	alert("결제정보 등록에 실패하였습니다.");
	self.close();	
}

</script>
<body>

</body>
</html>