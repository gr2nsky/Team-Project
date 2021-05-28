<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link href="css/list.css" rel="stylesheet" type="text/css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
<meta charset="UTF-8">
<title>공간 나눔 목록 리스트</title>
</head>
<body>
	<%@ include file="header.jsp" %>
	<div class="mainBox">
	<div class="contentBox">
	<h3>공간 나눔 목록</h3>
	<table  class="table" style="margin-left: auto; margin-right: auto;">
		<tr>
			<th>번호</th>
			<th>이름</th>
			<th>제목</th>
			<th>날짜</th>
			<th colspan="4">상세보기</th>
			<th>질문보기</th>
		</tr>
		<c:forEach items="${list }" var="dto">
			<tr>
				<td align="center">${dto.no }</td>
				
				<td>${dto.user_id }</td>
				<td><a href="content.four?no=${dto.no }">${dto.title }</a></td>
				<td>${dto.date }</td>
				
						<td colspan="4" align="right"><a href="detail.four?no=${dto.no}">자세히보기</a></td>
				<td align="center"><button type="button" class="navyBtn" onClick="location.href='host_qna.four?place_no=${dto.place_no}'">질문보기</button></td>
			</tr>
		</c:forEach>
			<tr>
				<td colspan="10" align="center">
					<!-- 페이징 부분 -->
					<c:forEach items="${pageList }" var="page">
						<a href="list.four?page=${page }">${page}</a>
					</c:forEach>
				</td>
			</tr>
	</table><br>
	<table class="table2" style="margin-left: auto; margin-right: auto;">
		<tr>
			<td  align="right">
			 <!-- <a href="write_space.four">글작성</a> -->
				<c:choose>
					<c:when test="${!empty loginedUserID }">
						 <a href="write_space.four"><button type="button" class="btnWrite" onClick="write_space.four()">공간 작성</button></a>
					</c:when>
					<c:otherwise>
					</c:otherwise>	
				</c:choose>
			</td>
		</tr>
	</table>
	</div>
	</div>
	
	<%@ include file="footer.jsp" %>
</body>
</html>