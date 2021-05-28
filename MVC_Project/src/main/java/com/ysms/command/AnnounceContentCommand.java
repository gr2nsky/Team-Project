package com.ysms.command;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import com.ysms.dao.Dao_Announce;
import com.ysms.dto.Dto_Announce;

public class AnnounceContentCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String no = request.getParameter("no");
		
		Dao_Announce dao = new Dao_Announce();
		Dto_Announce dto = dao.announceContent_view(no); // 얘는 select 할 때 쓴다!
		
		request.setAttribute("announceContent_view", dto);
	}

}
