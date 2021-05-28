<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap" rel="stylesheet">
<link rel="stylesheet" href="css/spaceDetailView_QnaReview.css" type="text/css">
<style>
	*{
		font-family: 'Nanum Gothic', sans-serif;
		color: #505050;
	}
	table{
		width: 700px;
		padding: 15px;
/* 		table-layout: fixed; */
		border: 1px solid #f0f0f0;
	}
	th{
		padding: 10px;
	}	
	td{
		margin: 0;
		padding: 15px;
/* 		border: 1px solid #f0f0f0;  */
	}
	.photo{
		width: 170px;
	}
	textarea{
		width: 100%;
		height: 80px;
		padding: 10px;
		box-sizing: border-box;
		border: none;
		font-size: 15px;
		resize: none;
		background-color: #fff;
	}
	button{
		margin: 0;
		padding: 12px;
		font-size: 13px;
		color: #fff;
		text-align: center;
		text-decoration: none;
		background-color: #ace2f9;
		border: none;
		border-radius: 10px;
		display: inline-block;
		width: 100px;
	}
	button:hover{
		padding: 11px;
		color: #ace2f9;
		background-color: #fff;
		border: 1px solid #ace2f9;
	}
	a:link {color: #828282; text-decoration: none; }
	a:visited {color: #828282; text-decoration: none; }
	a:hover {text-decoration: underline; color:#ace2f9;}
</style>
</head>
<script type="text/javascript">
	function writeQna(){
 		var place_no = document.getElementById("place_no").value;
 		  var url = "write_qna.four?place_no=" + place_no;
		open(url, "confirm",
				"roolbar=no, location=no,menubar=no,scrollbars=no,resizable=no,width=450,height=230");
 	}
	function hiddenbtn() {
	var target = document.getElementById("target").value;
	var loginedId = document.getElementById("loginedId").value;
	var btn = document.getElementById("write");
	if(target == loginedId){
		btn.style.display = "none";
	}	
}
</script>
<body>
<div class=mainBox>
	<div class="contentBox">
	<table align="center">
		<tr>
			<th colspan="2" align="left">
			<span style="padding:5px; font-size:15pt; font-weight:700;">QnA</span>
			<div class="underline" style="margin-top:10px; margin-left:5px;"></div>
			</th>
			<th align="right">
				<button id="write" type="button" style="font-weight:800;" onclick="writeQna();">작성하기</button>
				<input type="hidden" id="loginedId" value="${loginedUserID }">
				<input type="hidden" id="place_no" value="${placeNo }">
			</th>
		</tr>
		<c:choose>
		<c:when test="${!empty qnaList }">
		<c:forEach items="${qnaList }" var="qnaDto">
		<tr>
			<td  class="photo" rowspan="4" valign="top" align="center">
 			<div class="user">
			<img class="userProfile" src="userPhoto/${qnaDto.qnaUserFilePath }">
			</div>
			<input type="hidden" id = "target" value="${qnaDto.qnaTarget }">
			</td>
		</tr>
		<tr>
			<td align="left" style="font-weight:700;">${qnaDto.qnaSender }</td>
			<td align="right"><span style="font-size:13px;">${qnaDto.qnaQ_updateDate }</span></td>
		</tr>
		<tr>
			<td colspan="2"><textarea readonly="readonly" disabled="disabled">${qnaDto.qnaContent }</textarea></td>
		</tr>
		<tr>
		</tr>
		<!--호스트의 답변  -->
<!-- 		<tr>
			<td colspan="3"><hr></td>
		</tr> -->
		<tr>
			<td rowspan="3"></td>
		</tr>
		<tr>
			<td align="left" style="font-weight:700;">호스트의 답변</td>
			<td align="right"><span style="font-size:13px;">${qnaDto.qnaA_updateDate }</span></td>
		</tr>
		<tr>
			<td colspan="2"><textarea readonly="readonly" disabled="disabled">${qnaDto.qnaAnswer }</textarea></td>
		</tr>
		<tr>
		</tr>
		</c:forEach>
		</c:when>
		<c:otherwise>
			<tr>
				<td colspan="2">
<!-- 				<textarea rows="5" cols="50" readonly="readonly" style="resize:none;">
				등록된 질문이 없습니다.</textarea> -->
				<p align="center" style="font-weight:700;">등록된 질문이 없습니다.</p>
				</td>
			</tr>
		</c:otherwise>
		</c:choose>
		<tr>
			<td colspan="3" align="center">
				<!--Paging  -->
			<c:forEach items="${qnaPageList }" var="qnaPage">
				<a href="qna.four?place_no=${placeNo }&qnaPage=${qnaPage }">${qnaPage }</a>
			</c:forEach></td>
		</tr>
	</table>
	</div>
</div>
<script type="text/javascript">
hiddenbtn();
</script>
</body>
</html>