<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의하기 : 너의 공간 나의 공간 Your space My space</title>
</head>
<script type="text/javascript">
	
	function checkQnaContent(){
		
		if(document.writeQna.qnaContent.value.trim() == ""){
			alert("질문을 입력해주세요.");
			document.writeQna.qnaContent.focus();
			return false;
		}else{
			/* alert("질문이 등록 되었습니다!"); */
			document.writeQna.submit();
			return true;
		}
	}
</script>
<body>
<%
	int place_no = Integer.parseInt(request.getParameter("place_no"));
	session.setAttribute("place_no", place_no);
%>

	<form name="writeQna" action="qna_write.four" method="post">
	
		<input type="hidden" name="place_no" value="${place_no }">
	<table>
		<tr>		
			<td><h3>질문내용</h3></td>
			<td></td>
		</tr>
		<tr>
			<td colspan="2"><textarea rows="5" cols="50" name="qnaContent"></textarea></td>
		</tr>
		<tr>
			<td clospan="2" align="right">
			<input type="reset" value="지우기">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" value="등록" onclick="checkQnaContent();"></td>
		</tr>				
	</table>
	</form>
</body>
</html>