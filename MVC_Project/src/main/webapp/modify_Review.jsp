<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap" rel="stylesheet">
<link rel="stylesheet" href="css/reviewCRUD.css" type="text/css">
<title>Modify Review</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
	
	function checkReviewContent(){
		
		if(document.modifyReview.reviewContent.value.trim() == ""){
			alert("내용을 입력해주세요.");
			document.modifyReview.reviewContent.focus();
			return false;
		}else if(document.modifyReview.reviewScore.value.trim() == ""){
			alert("공간의 점수를 선택해주세요!");
			document.modifyReview.reviewContent.focus();
			return false;
		}else{
			/* alert("질문이 등록 되었습니다!"); */
			document.modifyReview.submit();
			return true;
		}
	}
</script>
<body>
<%
	request.setCharacterEncoding("utf-8");
	int rentalNo = Integer.parseInt(request.getParameter("rentalNo"));
	int reviewScore = Integer.parseInt(request.getParameter("reviewScore"));
	String reviewContent = request.getParameter("reviewContent");
	String reviewFilePath = "";
	
	if(reviewFilePath != null){
		reviewFilePath = request.getParameter("reviewFilePath");
	}

	session.setAttribute("rentalNo", rentalNo);
	session.setAttribute("reviewScore", reviewScore);
	session.setAttribute("reviewContent", reviewContent);
	session.setAttribute("reviewOldFilePath", reviewFilePath);	
%>

	<form name="modifyReview" action="review_modify.four" method="post" enctype="multipart/form-data">
		<input type="hidden" name="rentalNo" value="${rentalNo }">
		<input type="hidden" name="reviewOldFilePath" value="${reviewOldFilePath }">
	<table id="modifyReview" align="center">
		<tr>		
			<th align="left">리뷰 수정</th>
			<th align="right">
			<input type="button" value="돌아가기" onclick="history.back();">
			<input type="reset" value="지우기" class="btn-delete">
			<input type="button" value="수정" onclick="checkReviewContent();">
			</th>
		</tr>
		<tr>
			<td class="title" align="left">공간점수</td>
			<td class="title" align="left"><select name="reviewScore">
				<option value="">공간점수</option>
				 <option value="5" <c:if test="${reviewScore == 5 }"> selected </c:if>>5</option>
				<option value="4" <c:if test="${reviewScore == 4 }"> selected </c:if>>4</option>
				<option value="3" <c:if test="${reviewScore == 3 }"> selected </c:if>>3</option>
				<option value="2" <c:if test="${reviewScore == 2 }"> selected </c:if>>2</option>
				<option value="1" <c:if test="${reviewScore == 1 }"> selected </c:if>>1</option>
			</select>
			</td>
		</tr>
		<tr>
			<td class="title">첨부파일</td>
			<td class="title">
			<input type="file" name="reviewImg" id="reviewImg" accept="image/*">
			 </td>
		</tr>
 		<tr>
   			<td colspan="2" align="center">
			<div class="review">
			    <div id="preview">
			    <c:if test="${!empty reviewOldFilePath }">
			    <img class="reviewPhoto" src="reviewPhoto/${reviewOldFilePath }">
			    </c:if>
			    </div>
			</div>
   			</td>
   		</tr>
		<tr>
			<td colspan="2" align="center">
			<textarea name="reviewContent">${reviewContent }</textarea></td>
		</tr>			
	</table>	
	</form>
</body>
<script type="text/javascript">

function readInputFile(input) {
    if(input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $('#preview').html("<img src="+ e.target.result +" width='300'>");
        }
        reader.readAsDataURL(input.files[0]);
    }
}
 
$('#reviewImg').on('change', function(){
    readInputFile(this);
});

function resetInputFile($input, $preview) {
    var agent = navigator.userAgent.toLowerCase();
     if((navigator.appName == 'Netscape' && navigator.userAgent.search('Trident') != -1) || (agent.indexOf("msie") != -1)) {
        // ie 일때
        $input.replaceWith($input.clone(true));
        $preview.empty();
    } else { 
        //other
        $input.val("");
        $preview.empty();
    }       
}
 
$(".btn-delete").click(function(event) {
    var $input = $('#reviewImg');
    var $preview = $('#preview');
    resetInputFile($input, $preview);
});

</script>
</html>