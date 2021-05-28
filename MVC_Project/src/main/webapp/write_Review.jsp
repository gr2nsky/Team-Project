<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link rel="stylesheet" href="css/spaceDetailView_QnaReview.css" type="text/css">
<title>리뷰 작성 : 너의 공간 나의 공간 Your space My space</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
	
	function checkReviewContent(){
		
		if(document.writeReview.reviewContent.value.trim() == ""){
			alert("내용을 입력해주세요.");
			document.writeReview.reviewContent.focus();
			return false;
		}else if(document.writeReview.reviewScore.value.trim() == ""){
			alert("공간의 점수를 선택해주세요!");
			document.writeReview.reviewContent.focus();
			return false;
		}else{
			/* alert("질문이 등록 되었습니다!"); */
			document.writeReview.submit();
			return true;
		}
	}
</script>
<body>
<%
	int rentalNo = Integer.parseInt(request.getParameter("rentalNo"));
	session.setAttribute("rentalNo", rentalNo);
%>

	<form name="writeReview" action="review_write.four" method="post" enctype="multipart/form-data">
		<input type="hidden" name="rentalNo" value="${rentalNo }">
	<table>
		<tr>		
			<td><h3>리뷰 쓰기</h3></td>
			<td></td>
		</tr>
		<tr>
			<td>공간평점 : </td>
			<td><select name="reviewScore">
				<option value="">공간점수</option>
				<option value="5">5</option>
				<option value="4">4</option>
				<option value="3">3</option>
				<option value="2">2</option>
				<option value="1">1</option>
			</select>
			</td>
		</tr>
		<tr>
			<td>첨부파일</td>
			<td>
			<input type="file" name="reviewImg" id="reviewImg" accept="image/*">
			 </td>
		</tr> 
 		<tr>
   			<td colspan="2">
			    <div id="preview"></div>
   			</td>
   		</tr>
		<tr>
			<td colspan="2">
			<textarea rows="5" cols="50" name="reviewContent" placeholder="나눔 받은 공간은 어떠셨나요? 당신의 감상을 들려주세요."></textarea></td>
		</tr>
		<tr>
			<td clospan="2">
			<input type="button" value="닫기" onclick="window.close();">
			<input type="reset" value="지우기" class="btn-delete">
			<input type="button" value="등록" onclick="checkReviewContent();"></td>
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