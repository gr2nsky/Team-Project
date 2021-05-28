<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 폰트 적용 -->
<link rel="stylesheet" href="css/spaceDetailView_QnaReview.css" type="text/css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<style>
	/* 전체 글씨 속성 적용 */
	*{
		font-family: 'Nanum Gothic', sans-serif;
		color: #000000;
	}
	/* footer 배경색 적용 CSS */
	.container { 
		background-color: #ACE2F9;
		
		/* margin-top : 150px; */
	}
	/* footer 로고 이미지 CSS */
	.footer_logo img {
		width: 250px;
		padding: 20px 0 0 20px;
	}
	/* footer 하단 설명 CSS */
	.container ul {
		list-style-type: none;
		margin: auto;
		padding: 0 20px 30px 50px; /* 위부터 시계방향 여백 */
		overflow: hidden;
	}
</style>
<footer class="container">
	<div class = "footer_logo">
		<img alt="너공나공 푸토 로고" src="img/ysms_logo_footer.png">
	</div>
	<ul>
		<li># YOURSPACE_MYSPACE made by TEAM4 for JSP project</li>
		<li>E-mail : ysms.team4@gmail.com   Tel : 02-0000-0000</li>
		<li>* 너공나공에 대한 문의사항은 해당 너공나공 호스트에게 직접 문의 바랍니다.</li>
		<li>* 너공나공은 통신판매중개자이며 통신판매의 당사자가 아닙니다. 따라서 너공나공은 거래정보 및 거래에 대해 책임지지 않습니다.</li>
	</ul>
</footer>