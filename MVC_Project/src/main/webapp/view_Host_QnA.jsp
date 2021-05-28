<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<link href="css/view_HostQnA.css" rel="stylesheet" type="text/css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
<meta charset="UTF-8">
<title>${hostQnaShareTitle }의 문의 목록 </title>
<style>

        #hostQna { border-collapse:collapse; width:800px}
        #hostQna th { background:#c0c0c0 url(header_bkg.png) repeat-x scroll center left; color:#fff; padding:7px 7px; text-align:center;}
        #hostQna td { background:#f0f0f0 none repeat-x scroll center left; color:#000; padding:20px 15px; }
        #hostQna tr.odd td { background:#fff url(row_bkg.png) repeat-x scroll center left; cursor:pointer; }
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
		$(document).ready(function(){
	
	    $("#hostQna tr:odd").addClass("odd");
	    $("#hostQna tr:not(.odd)").hide(); 
	    $("#hostQna tr:first-child").show(); //열머리글 보여주기
	
	    $("#hostQna tr.odd").click(function(){
	        $(this).next("tr").toggle();
	        $(this).find(".arrow").toggleClass("up");
	    });
	});
</script>
</head>
<script type="text/javascript">

	function modifyQna(window) {
		open(window, "confirm",
		"roolbar=no,location=no,menubar=no,scrollbars=no,resizable=no,width=450,height=400");
	}

	function openNewWindow(window) {
		open(window, "confirm",
		"roolbar=no,location=no,menubar=no,scrollbars=no,resizable=no,width=300,height=150");
	}
	
</script>
<body>
<%@ include file="header.jsp" %>

	<h3 style="text-align: center;">${hostQnaShareTitle }의 문의 목록</h3>
	<table class="table" id="hostQna">
		<tr>
			<th></th>
			<th>게스트</th>
			<th>문의 내용</th>
			<th>문의 일자</th>
			<th>답변 여부</th>
		</tr>
		
		<c:choose>
		<c:when test="${!empty hostQnaList }">
		<c:forEach items="${hostQnaList }" var="hostQnaDto">
		<tr>
			<td><div class="arrow"></div></td>
			<td>${hostQnaDto.qnaSender }</td>
			<td>${hostQnaDto.qnaContent }</td>
			<td align="center">${hostQnaDto.qnaQ_updateDate }</td>
			<td align="center">${hostQnaDto.qnaYesNo }</td>
		</tr>
		<tr>
			<!--Detail  -->
			<td></td>
			<td colspan = "2">
				문의내용 <br>
				${hostQnaDto.qnaContent }<br><br>
				
				호스트답변<br>
				${hostQnaDto.qnaAnswer }<br>
				${hostQnaDto.qnaA_updateDate}
			</td>
			<td colspan="2" valign="bottom" align="right">
			<c:choose>
			<c:when test="${hostQnaDto.qnaYesNo == 'N' }">
				<a href="javascript:modifyQna('host_write_qna.four?qna_no=${hostQnaDto.qnaNo }')">
				<input type="button" value="답변작성하기"></a>
			</c:when>
			<c:otherwise>
				<a href="javascript:modifyQna('host_modify_qna.four?qna_no=${hostQnaDto.qnaNo }')">
				<input type="button" value="수정하기"></a>
				<a href="javascript:openNewWindow('host_deleteCheck_qna.four?qna_no=${hostQnaDto.qnaNo }')">
				<input type="button" value="삭제하기"></a>	
			</c:otherwise>
			</c:choose>
			</td>
		</tr>
		</c:forEach>
		</c:when>
		<c:otherwise>
			<tr>
				<td colspan="5" align="center">등록된 질문이 없습니다.</td>
			</tr>
		</c:otherwise>
		</c:choose>
		<tr>
			<td colspan="5" align="center">
				<!--Paging  -->
			<c:forEach items="${hostQnaPageList }" var="hostQnaPage">
				<a href="host_qna.four?place_no=${placeNo }&hostQnaPage=${hostQnaPage }">${hostQnaPage }</a>
			</c:forEach></td>
		</tr> 
	</table>

<%@ include file="footer.jsp" %>
</body>
</html>