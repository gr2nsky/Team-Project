package com.ysms.command;

import javax.servlet.http.HttpServletRequest;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ysms.dao.Dao_Login;

public class LoginCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String userID = request.getParameter("userID");
		String userPW = request.getParameter("userPW");
		
		Dao_Login dao = new Dao_Login();
		String loginedUserID = dao.tryToLogin(userID, userPW);
		
		HttpSession session = request.getSession();
		session.setAttribute("loginedUserID", loginedUserID);
		//로그인을 실행했다는 일종의 토큰
		request.setAttribute("tryLogin", "1");
	}

}
