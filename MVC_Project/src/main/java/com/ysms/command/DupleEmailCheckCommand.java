package com.ysms.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ysms.dao.Dao_Login;

public class DupleEmailCheckCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		//중복체크
		String email = request.getParameter("email");
		Dao_Login dao = new Dao_Login();
		String result = dao.emailDupleCheck(email);
		
		request.setAttribute("emailDupleCheckResult", result);
		request.setAttribute("email", email);
	}

}
