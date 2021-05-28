<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<link href="css/list.css" rel="stylesheet" type="text/css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
<meta charset="UTF-8">
<title>관리자페이지</title>
</head>

<script type="text/javascript">

<c:if test="${!empty deleteResult}">
<c:choose>
	<c:when test="${deleteResult == true}">
		alert("회원 탈퇴처리가 완료되었습니다.");
	</c:when>
	<c:otherwise>
		alert("회원 탈퇴처리가 진행되지 않았습니다..");
	</c:otherwise>
</c:choose>
</c:if>


var checkedUsers = new Array();

function checkHandler(checkBox){
	if(checkBox.checked){
		checkedUsers.push(checkBox.value);
	} 
	
	if(!checkBox.checked) {
		for(i = 0; i < checkedUsers.length; i++){
			if(checkedUsers[i] == checkBox.value){
				checkedUsers.splice(i, 1);
				i--;
			}
		}
	}
	document.getElementById("result").value = checkedUsers.join();
	console.log(document.getElementById("result").value);
}

function kickBack(){
	if(checkedUsers.length > 0){
		form = document.hiddenForm;
		form.submit();
	} else {
		alert("선택된 회원이 없습니다.");
	}

}

function goToUser(userNo){
	console.log("userNo : " + userNo);

	form = document.hiddenForm2;
	
	hiddenField = document.createElement('input');
	hiddenField.setAttribute('type', 'hidden');
	hiddenField.setAttribute('name', 'no');
	hiddenField.setAttribute('value', userNo);
	form.appendChild(hiddenField);
	
	form.submit();
}


</script>
<body>
<%@ include file="header.jsp" %>
	<br>
	<br>
	<br>
	<br>
	<div class="mainBox">
		<div class="contentBox">
			<h1>회원관리</h1>
			<table id="userList" class="table" style="margin-left: auto; margin-right: auto;">
				<tr>
					<th> no </th>
					<th> id </th>
					<th> name </th>
					<th> email </th>
					<th> status </th>
					<th> select </th>
				</tr>
				
				<c:forEach items="${DTOS}" var="dto" varStatus="status">
					<tr>
						<td> ${dto.no} </td>
						<td><a onclick="goToUser(${dto.no })"> ${dto.id }</a></td>
						<td> ${dto.name } </td>
						<td> ${dto.email } </td>
						<c:choose>
							<c:when test="${empty dto.cancelDate}">
								<td> 일반회원 </td>
								<td align="center"> <input type="checkbox" name="selectedUser" value=" ${dto.no }" onclick="checkHandler(this)" /> </td>
							</c:when>
							<c:otherwise>
								<td> 탈퇴회원 </td>
								<td> </td>
							</c:otherwise>
						</c:choose>
					</tr>
				</c:forEach>
				
				<tr> <td colspan="6" align="center">
					<c:forEach items="${pageList }" var="page">
						<a href="admin_managingUser.four?page=${page }">${page}</a>
					</c:forEach>
				</td> </tr>
			</table>
			
			<table class="table2" style="margin-left: auto; margin-right: auto;">
				<tr>
					<td align="right">
						<a href="write_space.four"><button type="button" class="btnWrite" onclick="kickBack()"> 강제탈퇴 </button></a>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<form action="userDelete.four" method="post" name="hiddenForm">
		<input type="hidden" value="" name="selectedUsers" id="result">
	</form>
	<form action="userDetailView.four" method="post" name="hiddenForm2"></form>
	<br>
	<br>
	<br>
	<br>
	<%@ include file="footer.jsp" %>
</body>
</html>