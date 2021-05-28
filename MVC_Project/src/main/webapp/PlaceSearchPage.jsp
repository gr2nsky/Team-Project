<!-- 
작성자 : 오성아, 2021-05-22
수정자 2021-05-22 윤재필

수정내용 
 날짜를 선택하는 방식 변경
  - 입력창 선택시 새창으로 달력 출력
  - 출력된 달력에서 날자 클릭시 해당 날자가 form에 입력됨
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
<link href="css/PlaceSearchPage.css" rel="stylesheet" type="text/css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap" rel="stylesheet">
<meta charset="UTF-8">
<meta charset="UTF-8">
<title>공간 검색 하기</title>
</head>
<script type="text/javascript">
	
//날짜 유효성 검사(정규식)
var dateFormCheck = /^(19|20)\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])$/;

//날짜 계산
var nowDate = new Date(); //오늘 날짜
var insertDate = $("#date").val(); // 입력된 날짜
var subDate = insertDate-nowDate; // 입력된 날짜 - 오늘 날짜

//입력 TF 유효성 검사
function checkTF(){
	var form = document.searchForm
	if(form.location.value==""){
		alert("지역을 입력해 주세요!");
		form.location.focus(); // tf 포커스
		return false;
	}
	
	if(form.date.value==""){
		alert("날짜를 입력해 주세요!");
		form.location.focus(); // tf 포커스
		return false;
	}	
	
	form.submit(); // loginForm 전송
	}
	
	//달력 팝업 실행
	function popupCalendar(){
		var url = "placeSearchCalendar.jsp";
		open(url, "calendar",
				"roolbar=no, location=no,menubar=no,scrollbars=no,resizable=no,width=400,height=400");
	}
</script>
<body>
<%@ include file="header.jsp" %>
<div class="mainBox">
	<div class="contentBox">
		<form name="searchForm" action="SearchPlaceCommand.four" method="post">
		<table id="SearchSpace" style="margin-left: auto; margin-right: auto;">
			<tr>
				<td colspan="4" align="center"><h2>장소 검색</h2></td>
			</tr>
			
			<tr>
				<th>공간유형</th>
				<th align="center" >지역</th>
				<th align="center">날짜</th>
			
			
			</tr>
			
			<tr>
				<td><select name="categorySpace" >
						<option value='0'>전체</option>
						<option value="1">휴식</option>
						<option value="2">파티</option>
						<option value="3">공부</option>
						<option value="4">회의</option>
					</select></td>	
				<td><input type="text" name="location" size="40" style="text-align:left;"></td>
				<!-- 날짜 선택 부분 -->
				<td><input type="text" name="date" id="date" onclick="popupCalendar()" readonly="readonly" size="15"></td>
				<td><input type="button" value="검색" onclick="checkTF()" class="btnOK" ></td>
				
			</tr>
			<tr>
				<td></td>
				<td>예) 강남, 왕십리, 송파 등</td>
				<td>예) 2021-05-19</td>
			</tr>
		</table>
	</form>
	</div>
</div>

<%@ include file="PlaceListAll.jsp" %>
<%@ include file="footer.jsp" %>
</body>
</html>