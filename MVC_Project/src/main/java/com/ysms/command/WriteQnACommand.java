package com.ysms.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ysms.common.LoginedUserInfo;
import com.ysms.dao.Dao_QnA;

public class WriteQnACommand implements Command {

	String user_id = LoginedUserInfo.id;
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		/* 
		 	-----------------------------
		 	21.05.16 hyokyeong JO
		 	DB table qna_review
		 	category - 0 : qna , 1 : review
		 	
		 	****현재 user_id = user01 으로 변수 선언해서 진행중
		 	-> 추후 세션으로 받아와서 바꿀 것.
		 	-----------------------------
		*/
		
		String st_place_no = request.getParameter("place_no");
		int place_no = Integer.parseInt(st_place_no);
		System.out.println("place_no (WriteQnACommand) : " + place_no);
		
		String content = request.getParameter("qnaContent");
		
		Dao_QnA dao = new Dao_QnA();
		dao.writeQnA(content, user_id, place_no);
		
	}

}
