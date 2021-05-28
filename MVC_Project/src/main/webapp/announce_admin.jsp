<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="css/announceAdmin.css" rel="stylesheet" type="text/css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
<meta charset="UTF-8">
<title>공지사항</title>
</head>
<body>
<%@ include file="header.jsp" %>
<div class="mainBox">
<div class="contentBox">	
<h3>공지사항</h3>
	<table class="table" style="margin-left: auto; margin-right: auto;">
		
		<tr>
			<th>No.</th>
			<th>제목</th>
			<th>등록 날짜</th>
		</tr>
		<c:forEach items="${announceList }" var="dto"> 
		<tr>
			<td align="center">${dto.no }</td>
			<td><a href="announceContent_view_admin.four?no=${dto.no}">${dto.title }</a></td>
			<td align="center">${dto.createDate }</td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="3" align="center"><c:forEach items="${pageList }" var="page">
				<a href="announce_admin.four?page=${page }">${page }</a>
			</c:forEach></td>
		</tr>
	</table><br>
	
	<DIV class="aside_menu">
		   <FORM name="frm" method="post" action="announce_admin.four">
			    <!-- <ASIDE style="float: right;"> -->
			      <SELECT name="getSearch"> <!-- 검색 컬럼 -->
			        <OPTION name ="search" value="title">제목</OPTION>
			        <OPTION name ="search" value="content">내용</OPTION>
			      </SELECT>
			      <input type="text" name="word" value="" placeholder="특수문자는 사용할수 없습니다." size="30">
			      <button type="submit" class="btnChk">검색</button>    
			    </ASIDE> 
	 		</FORM>
		    <DIV class='menu_line' style='clear: both;'></DIV>
		</DIV>
		
	<table class="table2" style="margin-left: auto; margin-right: auto;">		
	<tr>
		<tr>
			<td  align="right">
			<a href="announceWrite_view_admin.four"><button type="button" class="btnWrite">공지 작성</button></a>
		</td>
		</tr>
	</table>

<Br>
</div>
</div>

<%@ include file="footer.jsp" %>

</body>
</html>