/*
 * create 20210524 - 윤재
 * payment.jsp에 알맞은 정보전달을 위한 command
 * 
 */
package com.ysms.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ysms.common.LoginedUserInfo;
import com.ysms.common.PaymentInfo;
import com.ysms.dto.Dto_Payment;

public class PaymentCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String productName = request.getParameter("productName");
		String userName = request.getParameter("userName");
		String userPhone = request.getParameter("userPhone");
		String userEmail = request.getParameter("userEmail");
		String selectedDate = request.getParameter("selectedDate");
		String selectedTime = request.getParameter("selectedTime");
		int placeNo = Integer.parseInt(request.getParameter("placeNo"));
		int totalPrice = Integer.parseInt(request.getParameter("totalPrice"));
		int capacity = Integer.parseInt(request.getParameter("capacity"));
		
		String[] startAndEndTime = selectedTime.replace(" ", "").split("~");
		String[] startTimes = startAndEndTime[0].split(":");
		String[] endTimes = startAndEndTime[1].split(":");
		int startTime = Integer.parseInt(startTimes[0]);
		int endTime = Integer.parseInt(endTimes[0]);
		System.out.println("start : " + startTime + ", end :  " + endTime);
		System.out.println(selectedDate + ", " + startTime + ", " + endTime + ", " + totalPrice + ", " + LoginedUserInfo.id + ", " + placeNo);

		Dto_Payment dto = new Dto_Payment(selectedDate, startTime, endTime, totalPrice, LoginedUserInfo.id, placeNo, userName, userPhone, userEmail, capacity);
		PaymentInfo.dto_payment = dto;

		
		request.setAttribute("placeNo", placeNo);
		request.setAttribute("productName", productName);
		request.setAttribute("userName", userName);
		request.setAttribute("userPhone", userPhone);
		request.setAttribute("userEmail", userEmail);
		request.setAttribute("totalPrice", totalPrice);
	}

}
