<%@page import="com.ysms.common.LoginedUserInfo"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="com.ysms.dto.Dto_Refine_rental"%>
<%@page import="com.ysms.dto.Dto_Share"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap" rel="stylesheet">
<link rel="stylesheet" href="css/reservation.css" type="text/css">
<title>Reservation : ${DETAIL.title}</title>
<style>
	#reservation_share{
		margin:50px;
		width:970px;
		border-collapse:collapse;
		color: #505050;
/* 		border: 1px solid #f0f0f0; */
	}
	#reservation_share td.top{	
		padding-bottom: 10px;
		font-weight:700; font-size:25px;
	}
	
	#reservation_share td.title{
		padding-left: 15px;
		width:110px;
		height: 90px;
		font-weight:700;
		font-size:20px;
		border-bottom: 1px solid #dcdcdc;
		background-color: #fbedaa;
	}
	#reservation_share td.content{
		font-size:19px;
		width:320px;
		padding-left:20px;
		border-bottom: 1px solid #dcdcdc;
	}
	
	#reservation_user{
		margin:50px;
		width:600px;
		border-collapse:collapse;
		color: #505050;
/* 		border: 1px solid #dcdcdc; */
	}
	#reservation_user td.top{	
		padding-bottom: 20px;
		font-weight:700; font-size:25px;
	}
	
	#reservation_user td.title{
		padding : 15px;
		font-weight:700;
		font-size:20px;
	}
	#reservation_user td.content{
		font-size:19px;
		width:450px;
		padding-left:20px;
	}
	
	#reservation_time{
		margin:50px;
		width:600px;
		border-collapse:collapse;
		color: #505050;
/* 		border: 1px solid #dcdcdc; */
	}
	#reservation_time td.top{
		padding-bottom: 20px;
		font-weight:700; font-size:25px;
	}
	#reservation_time td.content{
		padding-bottom: 50px;
	}
	
	#calendar{margin-bottom:50px; padding: 10px; width:250px; color: #505050; border: 1px solid #dcdcdc;}
	#calendar td{padding:10px;	}
	
	#timeTable{margin-bottom:50px; padding: 5px; width:200px; color: #505050;}
	#timeTable td{padding:8px;	}
	
	#selectedDate{width: 200px; height: 25px;	padding: 10px; 
		color: #505050; font-size:17px;	background-color: #fff;	border: 1px solid #dcdcdc;	}
	#selectedTime{width: 200px; height: 25px;	padding: 10px; 
		color: #505050; font-size:17px;	background-color: #fff;	border: 1px solid #dcdcdc;	}
	#totalPrice{width: 200px; height: 25px;	padding: 10px; 
		color: #505050; font-size:17px;	background-color: #fff;	border: 1px solid #dcdcdc;	}
	
	#btn_submit{
		margin: 0;
		padding: 20px;
		text-align: center;
		text-decoration: none;
		font-size: 20px; color:#fff;
		background-color: #ace2f9;
		font-weight:700;
	
		border: none;
/* 		border-radius: 10px; */
		
		display: inline-block;
		width: 300px;
	}
	#btn_submit:hover{
		margin: 0;
		padding: 20px;
		text-align: center;
		text-decoration: none;
		font-size: 20px; color:#ace2f9;
		background-color: #fff;
		font-weight:700;
	
		border: 1px solid #ace2f9;
/* 		border-radius: 10px; */
		
		display: inline-block;
		width: 300px;
	}
	
</style>
</head>
<%
	if ((Integer)request.getAttribute("error") == 1){
		out.println("<script>alert('오류발생1!!');history.back();</script>");
	}
	
	//유저정보 획득
	String userName = LoginedUserInfo.name;
	String userPhone = LoginedUserInfo.phone;
	String userEmail = LoginedUserInfo.email;
	
	
	//share detail data
	Dto_Share share = (Dto_Share)request.getAttribute("DETAIL");
	//JSON 형식으로 달의 날자별 예약현황을 전송받음
	JSONArray thisMonthResData = (JSONArray)request.getAttribute("thisMonthResData");
	JSONArray nextMonthResData = (JSONArray)request.getAttribute("nextMonthResData");
	
	//예약가능 요일 (일~월, 가능0 불가능1)
	char[] possibleDay = (share.getDayLimit()).toCharArray();
	//예약가능 시간 (start time~end time) end - start = 이용가능시
	int startTime = share.getStartTime();
	int endTime = share.getEndTime();
	//총 이용 가능 시간
	int totalUsingTime = endTime - startTime;
	//시간당 가격
	int price = share.getPrice();
%>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">	
	
	//예약이 가득찬 날들의 배열
	var thisMonthFullDateList = new Array();
	<c:forEach items="${thisMonthFullDateList}" var = "date">
		thisMonthFullDateList.push(${date});
	</c:forEach>
	var nextMonthFullDateList = new Array();
	<c:forEach items="${nextMonthFullDateList}" var = "date">
		nextMonthFullDateList.push(${date});
	</c:forEach>

	//---------------- calendar --------------------------
	
	//date객체 획득. 가변
	var today = new Date();
	//today 보조. 고정
	var date = new Date();

	//선택되있던 셀 객체 저장
	var selectedCell;
	//오늘에 해당하는 월
	var realMonth = date.getMonth()+1;
	var realToDay = date.getDate()
	//선택된 월, 일
	var selectedMonth = null;
	var selectedDate = null;
	
	//예약가능 요일 계산해 배열 (일~월, 가능0 불가능1)
	const possibleDay = "<%=possibleDay%>";
	
	//전달 달력
	function prevCalendar(){
		if (today.getMonth() < realMonth){
			alert("예약은 금일기준 다음날부터 30일 이후까지만 가능합니다.");	
			return false;
		}
		today = new Date(today.getFullYear(), today.getMonth()-1, today.getDate());
		buildCalendar();
	}
	//다음달 달력
	function nextCalendar(){
		if(today.getMonth()+1 == (realMonth + 1)){
			alert("예약은 금일기준 다음날부터 30일 이후까지만 가능합니다.");
			return false;
		}
		today = new Date(today.getFullYear(), today.getMonth()+1, today.getDate());
		buildCalendar();
	}
	//달력 제작 (이번달 기준)
	function buildCalendar(){
		row = null;
		cnt = 0;
		firstDate = new Date(today.getFullYear(), today.getMonth(), 1);
		lastDate = new Date(today.getFullYear(), today.getMonth()+1, 0);
		//현재 참조중인 월 
		nowMonth = today.getMonth()+1;
		//이번달이면 0, 다음달이면 1 리턴
		monthEquals = thisMonth(nowMonth, realMonth);
		//달력 객체
		var calendarTable = document.getElementById("calendar");
		//달력의 타이틀 객체 획득
		var calendarTableTitle = document.getElementById("calendarTitle");
		//타이틀 수정
		calendarTableTitle.innerHTML = today.getFullYear()+"년"+(today.getMonth()+1)+"월";
		
		//테이블 초기화
		while(calendarTable.rows.length > 2){
			calendarTable.deleteRow(calendarTable.rows.length -1);
		}
		
		//셀 입력을 위해 테이블 개행
		row = calendarTable.insertRow();
		
		//달의 첫 날 전까지 빈 셀 생성
		for(i = 0; i < firstDate.getDay(); i++){
			cell = row.insertCell();
			cnt += 1;
		}
		
		//요일 입력 (셀 생성)
		for(i = 1; i <= lastDate.getDate(); i++){
			//예약하지 못하는 조건일경우 +1씩 되므로, noCount가 0일 시에만 클릭함수를 적용
			noCount = 0;
			cell = row.insertCell();
			//cell에 id 부여
			cell.setAttribute('id', i);
			cell.innerHTML = i;
			//cell.innerHTML = '<label onclick="prevCalendar()">' + i + '</label>';
			cell.align = "center";
			
			//셀 생성 후 count 증가
			cnt += 1;
			
			//cnt % 7 == 1이면 일요일이므로 빨갛게
			if (cnt % 7 == 1) {
              cell.innerHTML = "<font color=#F79DC2>" + i + "</font>";
         	 }  
            
			//일주일 입력 완료시 개행
			if (cnt % 7 == 0){
				//cnt % 7 == 0이면 토요일이므로 파랗게
				cell.innerHTML = "<font color=skyblue>" + i + "</font>";
				row = calendar.insertRow();
			}
			//예약불가일 색상변경 (오늘 이전 또는 30일 이후) 및 사용자가 직접 지정한 경우
			etp = exchangeToPosibleDay(cnt)*1;
			
			if (nowMonth == realMonth && i <= realToDay) {
				noCount +=1;
	        } else if (nowMonth > realMonth && i > realToDay) {
				noCount +=1;
	        } else if (possibleDay[etp] == 0){
				noCount +=1;
	        }

			if (noCount > 0){
				cell.style.backgroundColor = "#E0E0E0";
				cell.innerHTML = "<font color='#C6C6C6' >" + i + "</font>";
			} else {
				cell.onclick = function(){
					selectedTimeAndTotalPriceInit();
					//선택된 날의 연, 월, 일 계산 (일자의 경우 id속성 참조)
					clickedYear = today.getFullYear();
					clickedMonth = (1 + today.getMonth());
					clickedMonth = clickedMonth >= 10 ? clickedMonth : '0' + clickedMonth;
					clickedDate = this.getAttribute('id');
					clickedDate = clickedDate >= 10 ? clickedDate : '0' + clickedDate;
					
					clickedYMD = clickedYear + "-" + clickedMonth + "-" + clickedDate;
					
					//하단에 예약일시 표시
					inputField = document.getElementById("selectedDate");
					inputField.value = clickedYMD;
					//선택된 월, 일 변수 저장
					selectedMonth = today.getMonth() + 1;
					selectedDate = this.getAttribute('id');
					
					//선택된 셀 색 변화
					if(selectedCell != null){
						selectedCell.bgColor = "#FFFFFF";
					}
					selectedCell = this;
					this.bgColor = "#fbedaa";
					//time table 생성
					timeTableMaker(today.getMonth() + 1,this.getAttribute('id'));
				}
			}
			
		}
		//예약이 가득찬 날인 경우 cell 비활성화 및 색상 변rud
		checkMonth = thisMonth(nowMonth, realMonth);
		fullDate = [];
		if(checkMonth == 0){
			fullDate = thisMonthFullDateList;
		}
		if(checkMonth == 1){
			fullDate = nextMonthFullDateList;;
		}
		for (var i = 0; i < fullDate.length; i++){
			console.log("꽉 찬날 : " + fullDate[i]);
			cell = document.getElementById(fullDate[i]);
			console.log("꽉 찬날 : " + cell.innerHTML);
			cell.style.backgroundColor = "#E0E0E0";
			cell.style.color = '#C6C6C6';
			cell.onclick = function(){};
		}
		
		//달의 마지막날 뒤 행의 빈 공간을 셀로 채우기
		if(cnt % 7 != 0){
			for(i = 0; i < 7 - (cnt % 7); i++){
				cell = row.insertCell();
			}
		}
	}
	//사용자가 입력한 예약불가능 일자와 대조하기 위해 0~7의 환형 계산구조
	function exchangeToPosibleDay(num){
		result = num % 7;
		result -= 1;
		if (result == -1) {
			result = 6;
		}
		return result; 
	}
	//이번달이면 0 리턴, 다음달이면 1 리턴
	function thisMonth(todayMonth, dateMonth){
		console.log("todayMonth : " + todayMonth + ", dateMonth : " + dateMonth);
		if (todayMonth*1 == dateMonth*1){
			console.log("이번달 이구요")
			return 0;
		} 
		console.log("다음달 이구요")
		return 1;
	}

	// ---------------- time table --------------------------

	var price = "<%=price%>";
	var startTime = "<%=startTime%>";
	var endTime = "<%=endTime%>";
	//선택된 시간중 가장 빠른/늦은 시간;
	var selectedFirstTime = 24*1;
	var selectedFinalTime = 0*1;
	//예약시간표를 만들 table객체 획득

	
	function timeTableMaker(selectedMonth, selectedDate){
		row = null
		month = selectedMonth;
		date = selectedDate;
		var timeTable = document.getElementById("timeTable");
		
		//테이블 초기화
		while(timeTable.rows.length > 0){
			timeTable.deleteRow(timeTable.rows.length-1);
		}
		
		for (i = 0; i < endTime - startTime; i++){
			//곱해서 숫자타입으로 변환
			cellTime = startTime*1 + i;
			
			cellStartTimeText = cellTime + ":00";
			cellEndTimeText = (cellTime + 1) + ":00";
			inputCellText = cellStartTimeText + " ~ " +  cellEndTimeText;
			
			//셀 입력을 위해 테이블 개행
			row = timeTable.insertRow();
			//해당 row의 셀 생성
			cell = row.insertCell();
			//cell에 id 부여
			cell.setAttribute('id', cellTime);
			//셀에 입력
			cell.innerHTML = inputCellText;
			//selectedCell.bgColor = "#FFFFFF";
			//cell.innerHTML = "<font color='#C6C6C6' >" + inputCellText + "</font>";
			//클릭이벤
			cell.onclick = function(){
				cellTime = this.getAttribute('id');
				cellTime = cellTime*1;
				console.log("first : " + selectedFirstTime + ", selectedFinalTime : " + selectedFinalTime + ", selected : " + cellTime);
				//예약일시 입력처리
				if (selectedFirstTime != 24 && selectedFinalTime != 0){
					if(cellTime < selectedFirstTime - 1){
						alert("연속한 시간만 예약가능합니다.");
						return false;
					}
					if (cellTime > selectedFinalTime + 1){
						alert("연속한 시간만 예약가능합니다.");
						console.log(cellTime + ">" + selectedFinalTime + 1)
						return false;
					}
				}
				this.bgColor = "#fbedaa";
				if (cellTime < selectedFirstTime) {
					selectedFirstTime = cellTime
				}
				if (cellTime > selectedFinalTime) {
					selectedFinalTime = cellTime
				}
				
				//하단의 예약일시에 시간 표시
				resTime  = selectedFirstTime + ":00 ~ " + (selectedFinalTime + 1) + ":00";
			
				resTimeForm = document.getElementById("selectedTime");
				resTimeForm.value = resTime;
				
				//하단의 결제정보에 가격정보 표시
				useTime = (selectedFinalTime + 1) - selectedFirstTime;
				
				useTimeForm = document.getElementById("totalPrice");
				useTimeForm.value = useTime * price;
				
			}
		}
		//JSON으로 테이블 td 핸들링
		//이번달 0 다음달 1
		nowMonth = today.getMonth()+1;
		checkMonth = thisMonth(nowMonth, realMonth);
		var json = [];
		if(checkMonth == 0){
			json = <%=thisMonthResData%>;
		} else {
			json = <%=nextMonthResData%>;
		}
		for(i = 0; i < Object.keys(json).length; i++){
			if (date == json[i].date){
				jsonObject = json[i];
				for(j = 0; j < jsonObject.startNum.length; j++){
					startNum = jsonObject.startNum[j];
					shareTime = jsonObject.shareTime[j];
					console.log("startNum: " + startNum + ", shareTime : " + shareTime);
					for(k = startNum; k < startNum*1 + shareTime; k++){
						cell = timeTable.rows[k].cells[0];
						cell.style.backgroundColor = "#E0E0E0";
						cell.style.color = '#C6C6C6';
						cell.onclick = function(){};
					}
				}
			}
		}

	}
	//시간효 초기화
	function tableinit(){
		timeTableMaker(selectedMonth, selectedDate);
		selectedTimeAndTotalPriceInit();
		buildCalendar();
	}
	
	//날자 클릭시 예약시간 및 결제정보 초기화
	function selectedTimeAndTotalPriceInit(){
		resDateForm = document.getElementById("selectedDate");
		resTimeForm = document.getElementById("selectedTime");
		resTimeForm.value = "";
		resDateForm.value = "";
		
		useTimeForm = document.getElementById("totalPrice");
		useTimeForm.value = "";
		
		selectedFirstTime = 24*1;
		selectedFinalTime = 0*1;
	}
	
	//체크박스 이벤트
	function checkboxEvent(checkbox){
		nameForm = document.getElementById("userName");
		phoneForm = document.getElementById("userPhone");
		emailForm = document.getElementById("userEmail");
		
		userName = "<%=userName%>";
		userPhone = "<%=userPhone%>";
		userEmail = "<%=userEmail%>";
		
		if(checkbox.checked == true){
			nameForm.value = userName;
			phoneForm.value = userPhone;
			emailForm.value = userEmail;

		} else {
			nameForm.value = "";
			phoneForm.value = "";
			emailForm.value = "";
		}
	}
	
	function submitRes(){
		arr = new Array();
		
		nameForm = document.getElementById("userName");
		phoneForm = document.getElementById("userPhone");
		emailForm = document.getElementById("userEmail");
		capacityForm = document.getElementById("capacity");
		resTimeForm = document.getElementById("selectedTime");
		selectedDateFrom = document.getElementById("selectedDate");
		selectedTimeForm = document.getElementById("selectedTime");
		totalPriceForm = document.getElementById("totalPrice");
		
		arr.push(nameForm);
		arr.push(phoneForm);
		arr.push(emailForm);
		arr.push(resTimeForm);
		arr.push(selectedDateFrom);
		arr.push(selectedTimeForm);
		arr.push(totalPriceForm);
		
		for (i = 0; i < arr.length; i++){
			item = arr[i];
			if(item.value == "" ){
				alert("미기입된 정보가 있습니다.");
				item.focus();
				return false;
			}
		}
		
		if ( ${DETAIL.capacity} < capacityForm.value){
			alert("인원수가 초과되었습니다.");
			capacityForm.focus();
			return false;
		}
		
		popUp = window.open("payment", "payment");
		form = document.paymentForm
		form.action = "payment.four";
		form.target = "payment";
		form.submit();
	}
	
</script>
<body>
<%@ include file="header.jsp" %>
<div class="mainBox">
	<div class="contentBox">
		<div class="textLeft"><span style="color: #505050; font-size:30px; font-weight:700">예약하기</span>
		<div class="underline"></div></div>
		<form action="payment.four" method="post" name="paymentForm">
		<table id="reservation_share" align="center">
			<tr>
				<td class="top" colspan="3" align="left">공간명 ${DETAIL.title}</td>
			</tr>
			<tr>
				<td rowspan="3">
				<div class="share"><img class="sharePhoto" src="${DETAIL.filePath }"/></div></td>
				<td class="title" align="left" style="border-top:1px solid #dcdcdc;"> 공간 유형 </td>
				<c:if test="${DETAIL.category == 1 }"><td class="content" align="left" style="border-top:1px solid #dcdcdc;">휴식</td></c:if>
				<c:if test="${DETAIL.category == 2 }"><td class="content" align="left" style="border-top:1px solid #dcdcdc;">파티</td></c:if>
				<c:if test="${DETAIL.category == 3 }"><td class="content" align="left" style="border-top:1px solid #dcdcdc;">공부</td></c:if>
				<c:if test="${DETAIL.category == 4 }"><td class="content" align="left" style="border-top:1px solid #dcdcdc;">회의</td></c:if>
			</tr>
			<tr>
				<td class="title" align="left">최대 인원</td>
				<td class="content" align="left">1 ~ ${DETAIL.capacity}명</td>
			</tr>
			<tr>
				<td class="title" align="left">주소</td>
				<td class="content" align="left">${DETAIL.address1}<br>${DETAIL.address2}</td>
			</tr>
		</table>
		<table id="reservation_user">
				<tr>
					<input type="hidden" name="productName" value="${DETAIL.title}"></input>
					<input type="hidden" name="placeNo" value="${DETAIL.place_no}"></input>
					<td class="top" align="left">예약자 정보</td>
					<td class="content" align="right">
					<input type="checkbox" onclick="checkboxEvent(this)">계정과 동일</td>
				</tr>
				<tr>
					<td class="title" align="right">예약자</td>
					<td class="content" align="left"> <input type="text" id="userName" name="userName" size="20"> </td>
				</tr>
				<tr>
					<td class="title" align="right">전화번호</td>
					<td class="content" align="left"> <input type="text" id="userPhone" name="userPhone" size="20"> </td>
				</tr>
				<tr>
					<td class="title" align="right">이메일</td>
					<td class="content" align="left"> <input type="text" id="userEmail" name="userEmail" size="20"> </td>
				</tr>
				<tr>
					<td class="title" align="right">인원수</td>
					<td class="content" align="left"> <input type="text" id="capacity" name="capacity" size="20"> </td>
				</tr>
		</table>
	
		<table id="reservation_time">
			<tr>
				<td class="top" align="left">시간선택</td>
				<td class="top" align="right"><button class="btnTime" type="button" onclick="tableinit()">초기화</button></td>
			</tr>
			<tr>
				<td>
					<table id="calendar">
						<tr>
							<td align="center"><label onclick="prevCalendar()"> ◀ </label></td>
							<td colspan="5" align="center" id="calendarTitle">yyyy년 m월</td>
							<td align="center"><label onclick="nextCalendar()"> ▶ </label></td>
						</tr>
						<tr>
							<td align="center"><font color ="#F79DC2">일</td>
							<td align="center">월</td>
							<td align="center">화</td>
							<td align="center">수</td>
							<td align="center">목</td>
							<td align="center">금</td>
							<td align="center"><font color ="skyblue">토</td>
						</tr>
					</table>
				</td>
				<td>
					<table id = "timeTable">	</table>
				</td>
			</tr>
			<tr> 
				<td class="top" align="left" colspan="2">예약일시</td>
			</tr>
			<tr>
				<td class="content" colspan="2" align="left"><input id="selectedDate" name="selectedDate" value="" readonly="readonly"></input>
				<input id="selectedTime" name="selectedTime" value="" readonly="readonly"></input></td>
			</tr>
			<tr>
				<td class="top" align="left">결제정보</td>
			</tr>
			<tr>
				<td class="content" align="left" colspan="2"><input id="totalPrice" name="totalPrice" value="" readonly="readonly"></input></td>
			</tr>
			<tr>
				<td class="content" align="left" colspan="2">
				<input id="btn_submit" type="button" value="결제하기" onclick="submitRes()"></td>
			<tr>
		</table>
		</form>
	</div>
</div>
<script type="text/javascript">buildCalendar();</script>
<%@ include file="footer.jsp" %>
</body>
</html>