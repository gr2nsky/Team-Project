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
	#resInfoViewTable td{
		padding: 15px;
		border-bottom: 1px solid #dcdcdc;
		
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
%>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">	
	console.log(<%=thisMonthResData%>);
	//예약이 가득찬 날들의 배열
	var thisMonthResDateList = new Array();
	<c:forEach items="${thisMonthResDateList}" var = "date">
		thisMonthResDateList.push(${date});
	</c:forEach>
	var nextMonthResDateList = new Array();
	<c:forEach items="${nextMonthResDateList}" var = "date">
		nextMonthResDateList.push(${date});
	</c:forEach>

	//---------------- calendar --------------------------
	
	//date객체 획득. 가변
	var today = new Date();
	//today 보조. 고정
	var date = new Date();

	//선택되있던 셀 객체 저장
	var selectedCell;
	var selectedCellBackgroundColor;
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
			alert("예약확인은 이번달과 다음달만 가능합니다.");	
			return false;
		}
		today = new Date(today.getFullYear(), today.getMonth()-1, today.getDate());
		buildCalendar();
	}
	//다음달 달력
	function nextCalendar(){
		if(today.getMonth()+1 == (realMonth + 1)){
			alert("예약확인은 이번달과 다음달만 가능합니다.");	
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
             	cell.innerHTML = "<span color=#F79DC2>" + i + "</span>";
            	console.log(cell.innerHTML);
         	 }  
            
			//일주일 입력 완료시 개행
			if (cnt % 7 == 0){
				//cnt % 7 == 0이면 토요일이므로 파랗게
				cell.innerHTML = "<span color=skyblue>" + i + "</span>";
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
			cell.onclick = function(){					
				//선택된 셀 색 변화
				if(selectedCell != null){
					selectedCell.style.backgroundColor = selectedCellBackgroundColor;
				}
				selectedCell = this;
				selectedCellBackgroundColor = this.style.backgroundColor;
				this.style.backgroundColor = "#fbedaa";
				//time table 생성
				nonResTable(nowMonth, this.getAttribute('id'));
			}
			
			if (noCount > 0){
				cell.style.backgroundColor = "#E0E0E0";
				cell.innerHTML = "<span color='#C6C6C6' >" + i + "</span>";
			}
			//오늘색 변경
			if (nowMonth == realMonth && i == realToDay) {
				cell.style.backgroundColor = "#D5C2EE";
			}
		}
		//예약이 가득찬 날인 경우 cell 색상변경
		checkMonth = thisMonth(nowMonth, realMonth);
		resDate = [];
		if(checkMonth == 0){
			resDate = thisMonthResDateList;
		}
		if(checkMonth == 1){
			resDate = nextMonthResDateList;;
		}
		for (var i = 0; i < resDate.length; i++){
			console.log("예약 있는 날: " + resDate[i]);
			cell = document.getElementById(resDate[i]);
			console.log("예약 있는 날: " + cell.innerHTML);
			cell.style.backgroundColor = "#FFDC3C";
			
			//오늘색 변경
			if (nowMonth == realMonth && resDate[i] == realToDay) {
				cell.style.backgroundColor = "#DB003E";
			}
			
			cell.onclick = function(){					
				//선택된 셀 색 변화
				if(selectedCell != null){
					selectedCell.style.backgroundColor = selectedCellBackgroundColor;
				}
				selectedCell = this;
				selectedCellBackgroundColor = this.style.backgroundColor;
				this.style.backgroundColor = "#fbedaa";
				//time table 생성
				timeTableMaker(today.getMonth() + 1,this.getAttribute('id'));
			}
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

	var startTime = "<%=startTime%>";
	var endTime = "<%=endTime%>";
	
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
					resName = jsonObject.resName[j];
					resEmail = jsonObject.resEmail[j];
					resPhone = jsonObject.resPhone[j];
					resCapacity = jsonObject.resCapacity[j];
					resPrice = jsonObject.resPrice[j];
					shareNo = jsonObject.no[j];
					startNum = jsonObject.startNum[j];
					shareTime = jsonObject.shareTime[j];
					
					//예약이 있는 칸 색칠 및 클릭시 이벤트 발생
					for(k = startNum; k < startNum*1 + shareTime; k++){
						cell = timeTable.rows[k].cells[0];
						cell.style.backgroundColor = "#FFDC3C";
						cell.innerHTML = resName + " : "+cell.innerHTML;
						//cell.style.color = '#C6C6C6';
						cell.onclick = function(){
							viewResInfo(this, resName, resEmail, resPhone, resCapacity, resPrice, shareNo, startNum, shareTime);
						};
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
	
	//클릭한 날자의 예약이 없을 경우
	function nonResTable(selectedMonth, selectedDate){
		row = null
		month = selectedMonth;
		date = selectedDate;
		var timeTable = document.getElementById("timeTable");
		
		//테이블 초기화
		while(timeTable.rows.length > 0){
			timeTable.deleteRow(timeTable.rows.length-1);
		}
			
		//셀 입력을 위해 테이블 개행
		row = timeTable.insertRow();
		//해당 row의 셀 생성
		cell = row.insertCell();
		//셀에 입력
		cell.innerHTML = selectedMonth + "월 " + selectedDate + "일은 예약이 <br> 존재하지 않습니다.";
	}
	
	function viewResInfo(prtCell, resName, resEmail, resPhone, resCapacity, resPrice, shareNo, startNum, shareTime){
		console.log("prtCell : " + prtCell + ", resName : " + resName + ", resEmail: " + resEmail + 
				", resPhone : " + resPhone + ", resCapacity: " + resCapacity + ", resPrice : " + resPrice +
				", shareNo : " + shareNo + ", startNum: " + startNum + ", shareTime : " + shareTime );
		var resInfoViewTable = document.getElementById("resInfoViewTable");
		
		while(resInfoViewTable.rows.length > 0){
			resInfoViewTable.deleteRow(resInfoViewTable.rows.length-1);
		}
		//셀 입력을 위해 테이블 개행
		row = resInfoViewTable.insertRow();
		cell = row.insertCell();
		cell.innerHTML = "예약자명";
		cell.style.backgroundColor="#FFDC3C";
		
		cell = row.insertCell();
		cell.innerHTML = resName;
		
		cell = row.insertCell();
		cell.innerHTML = "전화번호";
		cell.style.backgroundColor="#FFDC3C";
		
		cell = row.insertCell();
		cell.innerHTML = resPhone;

		row = resInfoViewTable.insertRow();
		
		cell = row.insertCell();
		cell.innerHTML = "결제금액";
		cell.style.backgroundColor="#FFDC3C";
		
		
		cell = row.insertCell(); 
		cell.innerHTML = resPrice;
		

		cell = row.insertCell();
		cell.innerHTML = "이메일";
		cell.style.backgroundColor="#FFDC3C";
		
		cell = row.insertCell();
		cell.innerHTML = resEmail;
		
		row = resInfoViewTable.insertRow();
		
		cell = row.insertCell();
		cell.innerHTML = "인원수";
		cell.style.backgroundColor="#FFDC3C";
		
		cell = row.insertCell();
		cell.innerHTML = resCapacity; 
		
		cell = row.insertCell();
		cell.innerHTML = "총 예약시간";
		cell.style.backgroundColor="#FFDC3C";
		
		cell = row.insertCell();
		cell.innerHTML = startTime*1 + startNum*1 + ":00" + " ~ " + (startTime*1 + startNum*1 + shareTime*1) + ":00"; 
	}
</script>
<body>
<div class="mainBox">
	<div class="contentBox2">
		<div class="textLeft"><span style="color: #505050; font-size:30px; font-weight:700">예약확인</span>
		<div class="underline"></div></div>
		<form action="payment.four" method="post" name="paymentForm">
	
		<table id="reservation_time">
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
		</table>
		</form>
	</div>
</div>

<div class="mainBox">
	<div class="contentBox2">
		<div class="textLeft"><span style="color: #505050; font-size:30px; font-weight:700">예약 정보</span>
		<div class="underline"></div>
			<table id="resInfoViewTable">
				<tr><td>시간을 클릭해 주세요.</td></tr>
			</table>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	buildCalendar();
	todayCell = document.getElementById(realToDay);
	todayCell.onclick();
</script>
</body>
</html>