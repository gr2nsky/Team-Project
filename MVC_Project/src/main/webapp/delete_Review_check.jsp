<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete QnA</title>
</head>
<%
	request.setCharacterEncoding("utf-8");
	int rentalNo = Integer.parseInt(request.getParameter("rentalNo"));
	session.setAttribute("rentalNo", rentalNo);
%>

<body>
<table>
	<form action="review_delete.four" method="post">
	<input type="hidden" name="rentalNo" value="${rentalNo }">
	<tr>
		<td colspan="2">정말 삭제하시겠습니까?</td>
	</tr>
	<tr>
		<td><input type="submit" value="YES"></td></form>
		<td><input type="button" value="NO" onclick="history.back();"></td>
	</tr>
</table>
</body>
<script type="text/javascript">
	sessionStorage.removeItem("rentalNo");
</script>
</html>