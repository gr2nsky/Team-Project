package com.ysms.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ysms.dao.Dao_Share;

public class WriteSpaceCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		String postCode = null;
		String address1 = null;
		String address2 = null;
		String capacity = null;
		String category = null;
		
		
		
		try {
			postCode = request.getParameter("postCode");
			address1 = request.getParameter("address1");
			address2 = request.getParameter("address2");
			capacity = request.getParameter("capacity");
			category = request.getParameter("category");
			
			
			Dao_Share dao = new Dao_Share();
			dao.write_Space(postCode, address1, address2, capacity, category);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("POSTCODE", postCode);
		request.setAttribute("ADDRESS1", address1);
		request.setAttribute("ADDRESS2", address2);
		request.setAttribute("CAPACITY", capacity);
		request.setAttribute("CATEGORY", category);
		
	}

}
