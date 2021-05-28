package com.ysms.command;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import com.ysms.dao.Dao_Announce;

public class AnnounceWriteCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String user_id = "admin";
		
		Dao_Announce dao = new Dao_Announce();
		dao.announceWrite(title, content, user_id);
	}

}
