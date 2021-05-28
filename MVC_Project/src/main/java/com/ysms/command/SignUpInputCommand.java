package com.ysms.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.ysms.common.FilePath_login;
import com.ysms.dao.Dao_Login;

public class SignUpInputCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		FilePath_login fl = FilePath_login.getInstance();
		MultipartRequest multi = null;
		
		String id = null;
		String name = null;
		String pw = null;
		String email = null;
		String phone = null;
		int status = 0;
		String birthday = null;
		String filePath = fl.signUp_filePath;
		
		try {
			//일반 request와는 구분됩니다. request.getParameter로는 값을 가져올 수 없습니다.
			multi = fl.getMultipartRequest(request);
			
			id = multi.getParameter("id");
			name = multi.getParameter("name");
			pw = multi.getParameter("pw1");
			email = multi.getParameter("email");
			phone = multi.getParameter("phone1") + "-" + multi.getParameter("phone2") + "-" + multi.getParameter("phone3");
			status = 1;
			birthday = multi.getParameter("year") + "-" + multi.getParameter("month") + "-" + multi.getParameter("day");
			String uploadPhoto = multi.getFilesystemName("uploadPhoto");
			filePath = filePath + "/" + uploadPhoto;

			Dao_Login dao = new Dao_Login();
			String result = dao.signUp(id, name, pw, email, phone, status, birthday, filePath);

			request.setAttribute("sginUpResult", result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
