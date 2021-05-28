package com.ysms.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ysms.dao.Dao_Manage;

public class AdminUserDeleteCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("AdminUserDeleteCommand : ㅎㅇ");
		String selectedUsers = request.getParameter("selectedUsers");
		
		System.out.println("selectedUsers : " + selectedUsers);
		Dao_Manage dao = new Dao_Manage();
		String result = dao.deleteUsers(selectedUsers);
		
		request.setAttribute("deleteResult", result);
		request.setAttribute("tryDelete", 1);
	}

}
