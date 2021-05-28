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
	int qna_no = Integer.parseInt(request.getParameter("qna_no"));
	session.setAttribute("qnaNo", qna_no);
%>

<body>
<table>
	<form action="qna_delete.four" method="post">
	<input type="hidden" name="qna_no" value="${qnaNo }">
	<tr>
		<td colspan="2">정말 삭제하시겠습니까?</td>
	</tr>
	<tr>
		<td><input type="submit" value="YES"></td></form>
		<td><input type="button" value="NO" onclick="window.close();"></td>
	</tr>
</table>
</body>
<script type="text/javascript">
	sessionStorage.removeItem("qnaNo");
</script>
</html>