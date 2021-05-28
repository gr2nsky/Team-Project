<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modify QnA Answer</title>
</head>
<script type="text/javascript">
	
	function checkQnaContent(){
		
		if(document.hostAnswerQna.qnaAnswer.value.trim() == ""){
			alert("답변을 입력해주세요.");
			document.hostAnswerQna.qnaAnswer.focus();
			return false;
		}else{
			/* alert("질문이 등록 되었습니다!"); */
			document.hostAnswerQna.submit();
			return true;
		}
	}
</script>
<body>


	<form name="hostAnswerQna" action="host_qna_modify.four" method="post">
		<input type="hidden" name="qna_no" value="${qnaDetail.qnaNo }">
	<table>
		<tr>		
			<td><h3>문의 내용</h3></td>
			<td></td>
		</tr>
		<tr>
			<td colspan="2"><textarea rows="5" cols="50" name="qnaContent" readonly="readonly">${qnaDetail.qnaContent }</textarea></td>
		</tr>
		<tr>		
			<td><h3>답변 작성하기</h3></td>
			<td></td>
		</tr>
		<tr>
			<td colspan="2"><textarea rows="5" cols="50" name="qnaAnswer">${qnaDetail.qnaAnswer }</textarea></td>
		</tr>
		<tr>
			<td clospan="2" align="right">
			<input type="reset" value="되돌리기">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" value="수정" onclick="checkQnaContent();"></td>
		</tr>				
	</table>
	</form>
</body>
</html>