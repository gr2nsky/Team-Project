package com.ysms.command;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import com.ysms.dao.Dao_Announce;

public class AnnounceModifyCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String no = request.getParameter("no");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		System.out.println("AdminModifyCommand : no = " + no + ", title = " + title);
		Dao_Announce dao = new Dao_Announce();
		dao.announceModify(no, title, content);

	}

}
