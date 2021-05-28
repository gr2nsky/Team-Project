<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>   
<!DOCTYPE html>
<html>
<head>
<link href="css/content.css" rel="stylesheet" type="text/css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
<link rel="stylesheet" href="css/spaceDetailView_QnaReview.css" type="text/css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	int dayLimit = Integer.parseInt((String)request.getAttribute("dayLimit")); 
%>
<script type="text/javascript">
let shareableDateNum = "<%=dayLimit%>";
shareableDateNum *= 1;

function checkBoxSet(){
	checkBoxes = document.getElementsByName("checkBox");
	temp = shareableDateNum*1
	for(i = 0; i < checkBoxes.length; i++){
		if (temp - checkBoxes[i].value*1 >= 0){
			console.log("checkBoxes["+i+"] : temp" + temp + "-" + checkBoxes[i].value + " = " );
			temp -= checkBoxes[i].value*1;
			console.log("계산결과 : "+temp);
		} else {
			checkBoxes[i].checked = false;
		}
	}
}

function dateHandler(checkBox){
	console.log("변경전");
	console.log(shareableDateNum);
	if(checkBox.checked){
		console.log("체크됨");
		shareableDateNum += (checkBox.value*1 );
	}else {
		console.log("체크해제");
		shareableDateNum -= (checkBox.value*1 );
	}
	console.log("변경후");
	console.log(shareableDateNum);
	
	dayLimit.value = shareableDateNum;
}
</script>
<body>
	<%@ include file="header.jsp" %>
	
	<%@ include file="reservationCheck.jsp" %>
	
	<div class="mainBox">
	<div class="contentBox">
	<h3 style="text-align: center;">공간 나눔 목록</h3>
	<form action="modify.four" method="post" enctype="multipart/form-data" accept-charset="UTF-8">
	<table class="table" style="margin-left: auto; margin-right: auto;">
		
			<!-- <input type="hidden" name="bId" value="${content_view.bId }"> -->
			<tr>
				<th>등록번호</th>
				<td><input type="text" name="no" readonly="readonly" value="${DETAIL.no }"></td>
			</tr>
			<tr>
				<th>등록한 사람</th>
				<td><input type="text" name="user_id" size="20" readonly="readonly"  value="${DETAIL.user_id }"></td>
			</tr> 
			<tr>
				<th>타이틀</th>
				<td><input type="text" name="title" size="50" value="${DETAIL.title }"></td>
			</tr> 
			<tr>
				<th>공간소개</th>
				<td><textarea rows="10" cols="50" name="introduce">${DETAIL.introduce }</textarea></td>
			</tr> 
			<tr>
				<th>첨부파일</th>
				<td>
					<c:set var="requestFilePath" value="${DETAIL.filePath }" />
					<c:choose>
					    <c:when test="${!empty requestFilePath}">
					        <a href="${DETAIL.filePath }" download>${fileName }</a>
					    </c:when>
					    <c:otherwise>
					        <a>첨부파일 없음</a>
					    </c:otherwise>
					</c:choose>
					<!-- 이미지 수정을 위해서 기존 파일 경로도 전송해야하므로 히든아이템으로 유지한다. -->
					<input type="hidden" name="oldFilePath" value="${DETAIL.filePath }">
					<input type="file" name="uploadFile" >
				</td>
			</tr>
			<tr>
				<th>첨부파일 미리보기</th>
				<td><img width="300" src="${DETAIL.filePath }"/></td>
			</tr>
			<tr>
				<th>시간당 금액</th>
				<td><input type="text" name="price" size="15" value="${DETAIL.price }"></td>
			</tr>
			<tr>
				<th>예약가능 시작시간</th>
				<td><input type="text" name="startTime" size="15" value="${DETAIL.startTime }"></td>
			</tr>
			<tr>
				<th>예약가능 종료시간</th>
				<td><input type="text" name="endTime" size="15" value="${DETAIL.endTime }"></td>
			</tr>
			<tr>
				<th>예약가능 요일</th>
				<td>
					<input type="hidden" id="dayLimit" name="dayLimit" value="${DETAIL.dayLimit }" size="50">
					<input type="checkbox" name = "checkBox" value="1000000" onclick="dateHandler(this)" checked="checked">일
					<input type="checkbox" name = "checkBox" value="100000" onclick="dateHandler(this)" checked="checked">월
					<input type="checkbox" name = "checkBox" value="10000" onclick="dateHandler(this)" checked="checked">화
					<input type="checkbox" name = "checkBox" value="1000" onclick="dateHandler(this)" checked="checked">수
					<input type="checkbox" name = "checkBox" value="100" onclick="dateHandler(this)" checked="checked">목
					<input type="checkbox" name = "checkBox" value="10" onclick="dateHandler(this)" checked="checked">금
					<input type="checkbox" name = "checkBox" value="1" onclick="dateHandler(this)" checked="checked">토
					<script type="text/javascript">checkBoxSet();</script>
				</td>
			</tr>
		</table>
			
		<table class="table2" style="margin-left: auto; margin-right: auto;">
			<tr>
				<td>
					<button type="submit" class="btnModDel">수정</button>
					<a href="delete.four?no=${DETAIL.no }"><button type="button" class="btnModDel">삭제</button></a>
				</td>
				<td align="right">
					<a href="list.four?page=${currentPage}"><button type="button" class="btnGoList">목록보기</button></a>
				</td>
			</tr>
		
	</table>
	</form>
	</div>
	</div>
	<%@ include file="footer.jsp" %>
</body>
</html>