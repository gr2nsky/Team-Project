<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="css/contentsView.css" rel="stylesheet" type="text/css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%@ include file="header.jsp" %>
<div class="mainBox">
<div class="contentBox">

<h3 style="text-align: center;">공지사항 작성</h3>
<body>
<form action="announceWrite_admin.four" method="post">
	<table class="table" style="margin-left: auto; margin-right: auto;">
		
			<tr>
				<th>제목</th>
				<td align="left"><input type="text" name="title" size="30"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="10" cols="50" name="content"></textarea></td>
			</tr>
	</table>
	<table class="table2" style="margin-left: auto; margin-right: auto;">
		<tr>
			<td><button type="submit" class="btnModDel" >작성</button>
			&nbsp;&nbsp;&nbsp;
			<td align="right"><a href = "announce_admin.four"><button type="button" class="btnGoList" >목록보기</button></a>
			</td>
		</tr>
	</table>
</form>
	
</div>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>