<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap" rel="stylesheet">
<title>예약을 원하시는 날짜를 선택해 주세요</title>
</head>
<style>
	#calendar{margin-bottom:50px; padding: 10px; width:250px; color: #505050; border: 1px solid #dcdcdc;}
	#calendar td{padding:10px;	}
</style>

<script type="text/javascript">

//---------------- calendar --------------------------

//date객체 획득. 가변
var today = new Date();
//today 보조. 고정
var date = new Date();

//오늘에 해당하는 월
var realMonth = date.getMonth()+1;

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
	
	var row = null
	var cnt = 0;
	//달의 첫 날과 마지막날을 구함
	var firstDate = new Date(today.getFullYear(), today.getMonth(), 1);
	var lastDate = new Date(today.getFullYear(), today.getMonth()+1, 0);
	//달력으로 만들 table객체 획득
	var calendarTable = document.getElementById("calendar");
	//달력의 타이틀 객체 획득
	var calendarTableTitle = document.getElementById("calendarTitle");
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
		//예약하지 못하는 조건일경우 +1씩 되므로, noCount가 0일때가 예약가능 일자
		var noCount = 0;
		cell = row.insertCell();
		//cell에 id 부여
		cell.setAttribute('id', i);
		cell.innerHTML = i;
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
		
		//오늘보다 이전이거나 30일 이후면 noCount를 증가시킨다
		if (today.getMonth() == date.getMonth() && i <= date.getDate()) {
			noCount +=1;
        } else if (today.getMonth() > date.getMonth() && i > date.getDate()) {
			noCount +=1;
        }
		//카운트가 0보다 크면 회색배경을 입히고 클릭함수 추가를 하지 않음.
		if (noCount > 0){
			cell.bgColor = "#E0E0E0";
			cell.innerHTML = "<font color='#C6C6C6' >" + i + "</font>";
			
		} else {
			cell.onclick = function(){
				//선택된 날의 연, 월, 일 계산 (일자의 경우 id속성 참조)
				clickedYear = today.getFullYear();
				clickedMonth = ( 1 + today.getMonth() );
				clickedMonth = clickedMonth >= 10 ? clickedMonth : '0' + clickedMonth;
				clickedDate = this.getAttribute('id');
				clickedDate = clickedDate >= 10 ? clickedDate : '0' + clickedDate;
				
				clickedYMD = clickedYear + "-" + clickedMonth + "-" + clickedDate;
				
				//클릭시 부모뷰에 날자를 찍고 본인은 close
				opener.document.getElementById("date").value = clickedYMD;
				self.close();
			}
		}
	}
	//달의 마지막날 뒤 행의 빈 공간을 셀로 채우기
	if(cnt % 7 != 0){
		for(i = 0; i < 7 - (cnt % 7); i++){
			cell = row.insertCell();
		}
	}
}

</script>
<body>
<table id="calendar" align="center">
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
		<script type="text/javascript">buildCalendar();</script>
	</table>
</body>
</html>