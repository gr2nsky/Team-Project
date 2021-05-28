package com.ysms.command;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import com.ysms.dao.Dao_Announce;

public class AnnounceDeleteCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String no = request.getParameter("no");
		
		Dao_Announce dao = new Dao_Announce();
		dao.announceDelete(no);

	}

}
