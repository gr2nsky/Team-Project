package com.ysms.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ysms.dao.Dao_myinfo_QnA;

public class ModifyQnACommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		/*
	 	-----------------------------
	 	21.05.21 hyokyeong JO
	 	DB table qna_review	 	
	 	-----------------------------
		*/

		int qna_no = Integer.parseInt(request.getParameter("qna_no"));
		String qna_content = request.getParameter("qnaContent");
		
		System.out.println(qna_content);
		
		Dao_myinfo_QnA dao = new Dao_myinfo_QnA();
		dao.modifyQnA(qna_no, qna_content);
		
		
	}

}
