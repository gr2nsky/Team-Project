package com.ysms.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ysms.common.PaymentInfo;
import com.ysms.common.ReservationInfo;
import com.ysms.dto.Dto_Payment;
import com.ysms.dto.Dto_Share;

public class PaymentResultCheckCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		Dto_Share detail = ReservationInfo.detail;
		Dto_Payment dto = PaymentInfo.dto_payment;
		
		request.setAttribute("DETAIL", detail);
		request.setAttribute("DTO", dto);
	}

}
