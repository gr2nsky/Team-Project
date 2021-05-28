<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="css/contentsView.css" rel="stylesheet" type="text/css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
<meta charset="UTF-8">
<title>공지 사항</title>
</head>
<%@ include file="header.jsp" %>

<div class="mainBox">
<div class="contentBox">
<body>
	<h3>공지사항</h3>
	<form action="announceModify_admin.four" method="post">
		<table border="0" class="table">
			<tr>
				<th>No. </th>
				<td align="left"><input type="text" name="no" readonly="readonly" value="${announceContent_view.no }"></td>
			</tr>
			<tr>
				<th>제목</th>
				<td align="left"><input type="text" name="title" size="30" value="${announceContent_view.title}"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="10" cols="50" name="content">${announceContent_view.content}</textarea></td>
			</tr>
		</table>
	
	
		<table class="table2">
			<tr>
				<td >
				<button type="submit" class="btnModDel">수정</button>
				<!-- <input type="submit" value="수정" > --><!-- 
				&nbsp;&nbsp;&nbsp; -->
				<a href="announceDelete_admin.four?no=${announceContent_view.no}"><button type="button" class="btnModDel" >삭제</button></a>
				</td>
				<td align="right">
				<a href = "announce_admin.four"><button type="button" class="btnGoList" >목록보기</button></a>
				</td>
			</tr>
		</table>
	</form>
</div>
</div>
<%@ include file="footer.jsp" %>

</body>
</html>