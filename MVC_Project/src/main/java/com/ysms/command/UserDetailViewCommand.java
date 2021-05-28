package com.ysms.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ysms.dao.Dao_Manage;
import com.ysms.dto.Dto_SignUp;

public class UserDetailViewCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int no = Integer.parseInt(request.getParameter("no"));

		Dao_Manage dao = new Dao_Manage();
		Dto_SignUp dto = dao.getUserInfo(no);
		
		String[] phone = dto.getPhone().split("-");
		
		request.setAttribute("DTO", dto);
		request.setAttribute("phone1", phone[0]);
		request.setAttribute("phone2", phone[1]);
		request.setAttribute("phone3", phone[2]);
		
		
	}

}
