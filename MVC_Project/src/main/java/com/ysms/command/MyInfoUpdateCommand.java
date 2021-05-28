package com.ysms.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.ysms.common.FilePath_login;
import com.ysms.common.LoginedUserInfo;
import com.ysms.dao.Dao_Login;
import com.ysms.dao.Dao_MyInfo_UpdateProfile;

public class MyInfoUpdateCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		FilePath_login fl = FilePath_login.getInstance();
		MultipartRequest multi = null;
		String updateResult = null;
		String pw = null;
		String phone = null;
		String filePath = fl.signUp_filePath;
		
		try {
			//일반 request와는 구분됩니다. request.getParameter로는 값을 가져올 수 없습니다.
			multi = fl.getMultipartRequest(request);

			pw = multi.getParameter("pw1");
			phone = multi.getParameter("phone1") + "-" + multi.getParameter("phone2") + "-" + multi.getParameter("phone3");
			String uploadPhoto = multi.getFilesystemName("uploadPhoto");
			if(uploadPhoto == null || uploadPhoto.equals("")) {
				filePath = null;
			} else {
				filePath = filePath + "/" + uploadPhoto;
			}

			Dao_MyInfo_UpdateProfile dao = new Dao_MyInfo_UpdateProfile();
			if(pw == null || pw.equals("")) {
				updateResult = dao.updateUserInfo(LoginedUserInfo.id, phone, filePath);
			}else {
				updateResult = dao.updateUserInfo(LoginedUserInfo.id, pw, phone, filePath);
			}
			

			request.setAttribute("updateTty", 1);
			request.setAttribute("updateResult", updateResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
