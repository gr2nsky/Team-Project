package com.ysms.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.ysms.common.FilePath_login;
import com.ysms.dao.Dao_Login;

public class DupleIDCheckCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");

		Dao_Login dao = new Dao_Login();
		String result = dao.IDdupleCheck(id);
		if (result == "useable"){
			request.setAttribute("duplicate_checked_id", id);
		}
	}

}
