<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  
	int placeNoCheck = (int)request.getAttribute("placeNo");
	String productName = (String)request.getAttribute("productName");
	String userName = (String)request.getAttribute("userName");
    String email = (String)request.getAttribute("userEmail");
    String phone = (String)request.getAttribute("userPhone");
    int totalPrice = (int)request.getAttribute("totalPrice");    
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script> 
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
</head>
<body>
<script type="text/javascript">
IMP.init('imp27536534');

IMP.request_pay({
    pg : 'inicis', // version 1.1.0부터 지원.
    pay_method : 'card',
    merchant_uid : 'YSMS 주식회사_' + new Date().getTime(),
    name : '<%=productName%>',
    amount : '<%=totalPrice%>',
    buyer_email : 'i<%=email%>',
    buyer_name : '<%=userName%>',
    buyer_tel : '<%=phone%>',
    buyer_addr : '서울특별시 강남구 삼성동',
    buyer_postcode : '123-456'
}, function(rsp) {
    if ( rsp.success ) {
        var msg = '결제가 완료되었습니다.';
        msg += '고유ID : ' + rsp.imp_uid;
        msg += '상점 거래ID : ' + rsp.merchant_uid;
        msg += '결제 금액 : ' + rsp.paid_amount;
        msg += '카드 승인번호 : ' + rsp.apply_num;
        
        location.href="successPayment.four?pNo=" + "<%=placeNoCheck%>";
    } else {
        var msg = '결제에 실패하였습니다.';
        msg += '에러내용 : ' + rsp.error_msg;
    }
    alert(msg);
});

</script>
</body>
</html>
