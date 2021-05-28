<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Completed!</title>
</head>
<script type="text/javascript">
	function pclose() {
		opener.location.reload();
		window.close();
	}
</script>
<body>
<h3>질문이 등록되었습니다 !</h3>
<p align="right"><input type="button" value="닫기" onclick="pclose();"></p>
</body>
</html>