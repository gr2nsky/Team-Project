<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="css/writeSpace.css" rel="stylesheet" type="text/css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
//daum객체는 위에서 설정한 라이브러리 안쪽에 들어있다.
	function daumZipCode() {
		new daum.Postcode({
			oncomplete: function(data) {
	// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
	// 각 주소의 노출 규칙에 따라 주소를 조합한다.
	// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로 이를 참고하여 분기 한다.
				var fullAddr = ''; // 최종 주소 변수
				var extraAddr = ''; // 조합형 주소 변수
	        // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
	       		// 사용자가 도로명 주소를 선택했을 경우         
				if (data.userSelectedType === 'R') {  //R은 도로명 주소이다.
					fullAddr = data.roadAddress;
				} else { // 사용자가 지번 주소를 선택했을 경우(J)
					fullAddr = data.jibunAddress; //도로명 주소가 아니라면.. 지번주소.
				}
	         	// 사용자가 선택한 주소가 도로명 타입일때 조합한다.
				if(data.userSelectedType === 'R'){
	            //법정동명이 있을 경우 추가한다.
					if(data.bname !== ''){
						extraAddr += data.bname;
					} //도로명 주소일때는 법에 맞춰서 '동' 이름을 추가해야 한다.
	             // 건물명이 있을 경우 추가한다.
					if(data.buildingName !== ''){
						extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
					}
	             // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
					fullAddr += (extraAddr !== '' ? 
	                ' ('+ extraAddr +')' : '');
				}
	         // 우편번호와 주소 정보를 해당 필드에 넣는다.
	         // 5자리 새우편번호 사용
				document.getElementById('zipcode').value = data.zonecode; 
				document.getElementById('address1').value = fullAddr; //address1에 확정된 주소값의 풀네임이 들어간다.
	         // 커서를 상세주소 필드로 이동한다. 
	         // 커서를 이동시켜서 깜빡이게끔 한다.
				document.getElementById('address2').focus();
			}
		}).open();
	}
</script>
</head>
<body>
	<%@ include file="header.jsp" %>
	<div class="mainBox">
	<div class="contentBox">
	<h3 style="text-align: center;">공간 나눔(호스트) 신청 : 공간 정보 입력</h3>
<form action="write_detail.four" method="post">
	<table class="table" style="margin-left: auto; margin-right: auto;">
		<tr>
			<th align="right">우편번호</th>
			<td><input name="postCode" id="zipcode" readonly size="10"> <input type="button" onclick="daumZipCode()" value="우편번호 찾기"></td>
		</tr>
		<tr>
			<th align="right">주소 </th>
			<td><input name="address1" id="address1" size="60"></td>
		</tr>
		<tr>
			<th align="right">상세주소</th>
			<td><input name="address2" id="address2"></td>
		</tr>
		<tr>
			<th>이용최대인원</th>
			<td><input type="text" name="capacity" size="50"></td>
		</tr> 
		<tr>
			<th>공간 유형</th>
			<td><select name="category">
				<option value="1">휴식</option>
				<option value="2">파티</option>
				<option value="3">공부</option>
				<option value="4">회의</option>
			</select>
			</td>
		</tr> 
	</table>
		
		
		<table class="table2" style="margin-left: auto; margin-right: auto;">
			<!-- <tr><td><input type="submit" value="입력완료" class="btnChk"></td></tr> -->
			<tr><td><button class="btnChk" type="submit">입력완료</button></td></tr>
		</table>
		</form>
			 
	<br><br><br><br>
	<br><br><br><br>
	</div>
	</div>
	<%@ include file="footer.jsp" %>
</body>
</html>