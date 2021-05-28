package com.ysms.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ysms.common.PaymentInfo;
import com.ysms.common.ReservationInfo;
import com.ysms.dao.Dao_Reservation;
import com.ysms.dto.Dto_Payment;

public class PaymentSubmitCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int pNo = Integer.parseInt(request.getParameter("pNo"));
		Dto_Payment dto = PaymentInfo.dto_payment;
		
		if(pNo !=dto.getPlace_no()) {
			System.out.println("치명적인 오류. 결제정보가 올바르지 않습니다.");
			return;
		}

		Dao_Reservation dao = new Dao_Reservation();
		String result = dao.payment(dto.getCheckInDate(), dto.getStartTime(), dto.getEndTime(), 
				dto.getPrice(), dto.getUser_id(), dto.getPlace_no(), dto.getResName(), dto.getResPhone(), dto.getResEmail(), dto.getResCapacity());
		
		if(result.equals("true")) {
			System.out.println("결재정보 등록성공");
		}else{
			System.out.println("결재정보 등록실패");
		}
		
		request.setAttribute("paymentResult", result);
		request.setAttribute("paymentInfo", dto);
		request.setAttribute("DETAIL", ReservationInfo.detail);
	}

}
