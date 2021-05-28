package com.ysms.command;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.removeAttribute("loginedUserID");
		//로그아웃 작업을 수행했다는 토큰
		request.setAttribute("tryLogout", "1");
	}

}
