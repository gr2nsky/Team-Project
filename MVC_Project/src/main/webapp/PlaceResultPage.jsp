<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> <!-- 숫자 포맷 라이브러리 -->
<!DOCTYPE html>
<html>
<head>
<link href="css/PlaceResultPage.css" rel="stylesheet" type="text/css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="header.jsp" %>
<%String inputCategory = request.getParameter("categorySpace"); // 입력된 값 불러옴
String printInputLocation = request.getParameter("location") ; //입력된 검색어 값 불러옴
String printInputDate = request.getParameter("date") ; //입력된 검색어 값 불러옴
	// 날짜 값 공백이면 오늘 기준으로 검색하게 하기
	if (printInputDate == null) {
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		printInputDate = date;
	}
%>
<div class="mainBox">
<div class="contentBox">
<form>
<Table>
	<tr>
		<th align="center" width="650">
		<h2>검색 결과 / 
		<c:choose>
			<c:when test="${param.location eq '구'}">
				검색어 (전국)
			</c:when>
			<c:otherwise>
				검색어 (<%=printInputLocation %>)
			</c:otherwise>	
		</c:choose>
			날짜 (<%=printInputDate %>)
		</h2></th>
		<td align="right">
		<select>
				<option>리뷰 많은 순</option>
				<option>사용자 많은 순</option>		
		</select>
		</td>
	</tr>
</Table>
</form>
<table class="table" align="center">
	<c:forEach  items="${SearchLocation }" var="Dto_SearchPlace"> <!-- items 잊지 말 것 -->
	<tr>
		<td rowspan="2" align="right" class="photo">
		<div class="shareList"><a href="detail.four?no=${Dto_SearchPlace.no }"><img class="shareListPhoto" src="${Dto_SearchPlace.filePath }"/></a></div></td>	
		<th width="200">공간유형</th>		
		<td width="200">${Dto_SearchPlace.category }</td>
		<th width="200">이름</th>
		<td width="200"><a href="detail.four?no=${Dto_SearchPlace.no }">${Dto_SearchPlace.title }</a></td>	
	</tr>
			<!-- <tr>
				<td colspan="4"></td>
			</tr> -->
			<tr>
				<th >주소</th>		
				<td >${Dto_SearchPlace.address1 } ${Dto_SearchPlace.address2 }</td> 
				<th>가격<br>(원/시간)</th>
				<td><fmt:formatNumber value="${Dto_SearchPlace.price }" pattern="##,###"/></td>	
			</tr>	
			<tr>
				<td colspan="4"></td>
			</tr>
			</c:forEach>
			<tr>
				<td colspan="5" align="center">
					<!-- 페이징 부분 -->
					<c:forEach items="${pageList }" var="page">
						<a href="SearchPlacePage.four?page=${page }">${page } </a>
					</c:forEach>
				</td>
					</tr>
		</table>
</div>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>