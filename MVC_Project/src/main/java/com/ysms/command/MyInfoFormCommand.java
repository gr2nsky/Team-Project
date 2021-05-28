package com.ysms.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ysms.common.LoginedUserInfo;
import com.ysms.dao.Dao_MyInfo_UpdateProfile;
import com.ysms.dto.Dto_SignUp;

public class MyInfoFormCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String loginedId = LoginedUserInfo.id;
		if (loginedId.equals("") || loginedId == null) {
			request.setAttribute("error", 1);
		}
		Dao_MyInfo_UpdateProfile dao = new Dao_MyInfo_UpdateProfile();
		Dto_SignUp dto = dao.getUserInfo(loginedId);
		
		if(dto.getFilePath() == null || dto.getFilePath().equals("")) {
			System.out.println("@@@@@@@@@@@@@@2사진이 없는뎁");
		}
		System.out.println(dto.getFilePath());
		
		String[] phone = dto.getPhone().split("-");
		
		request.setAttribute("DTO", dto);
		request.setAttribute("phone1", phone[0]);
		request.setAttribute("phone2", phone[1]);
		request.setAttribute("phone3", phone[2]);
	}

}
