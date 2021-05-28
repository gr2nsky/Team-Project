<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="css/write.css" rel="stylesheet" type="text/css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
	<script type="text/javascript">
		let shareableDateNum = 1111111*1;
		
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
	<div class="mainBox">
	<div class="contentBox">
	<h3 style="text-align: center;">공간 나눔(호스트) 신청 : 영업정보 입력</h3>
	<table class="table" style="margin-left: auto; margin-right: auto;">
		<form action="write.four" method="post" enctype="multipart/form-data">
			<tr>
				<th>공간이름</th>
				<td><input type="text" name="title" size="50"></td>
			</tr> 
			<tr>
				<th>시간당 금액</th>
				<td><input type="text" name="price" size="50"></td>
			</tr> 
			<tr>
				<th>시작시간</th>
				<td><input type="text" name="startTime" size="50" placeholder="영업시작 시간을 입력해주세요(예:오전 8시이면 08을 입력)"></td>
			</tr> 
			<tr>
				<th>종료시간</th>
				<td><input type="text" name="endTime" size="50" placeholder="영업종료 시간을 입력해주세요(예:오후 8시이면 20을 입력)"></td>
			</tr> 
			<tr>
				<th>예약가능요일</th>
				<td>
					<input type="hidden" id="dayLimit" name="dayLimit" value="" size="50">
					<input type="checkbox" value="1000000" onclick="dateHandler(this)" checked="checked">일
					<input type="checkbox" value="100000" onclick="dateHandler(this)" checked="checked">월
					<input type="checkbox" value="10000" onclick="dateHandler(this)" checked="checked">화
					<input type="checkbox" value="1000" onclick="dateHandler(this)" checked="checked">수
					<input type="checkbox" value="100" onclick="dateHandler(this)" checked="checked">목
					<input type="checkbox" value="10" onclick="dateHandler(this)" checked="checked">금
					<input type="checkbox" value="1" onclick="dateHandler(this)" checked="checked">토
				</td>
			</tr> 
			<tr>
				<th>내용</th>
				<td><textarea rows="10" cols="50" name="introduce"></textarea></td>
			</tr> 
			<tr>
				<th>첨부파일</th>
				<td><input type="file" name="uploadFile" ></td>
			</tr> 
			
		</table>
		
		<table class="table2" style="margin-left: auto; margin-right: auto;">
			<tr><td>
			<input type="hidden" name="user_id" value="${loginedUserID }">
			<input type="hidden" name="postCode" value="${POSTCODE }">
			<input type="hidden" name="capacity" value="${CAPACITY }">
			<input type="hidden" name="category" value="${CATEGORY }">
			</td></tr>
			<tr><td><button type="submit" class="btnChk" >신청하기</button></td></tr>
			<!-- <tr><td><input type="submit" value="신청하기" class="btnChk"></td></tr> -->
		</table>
		<br><br><br><br>
		<br><br><br><br>
		</form>
	
	</div>
	</div>
	<%@ include file="footer.jsp" %>
</body>
</html>