package com.ysms.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ysms.dao.Dao_QnA;
import com.ysms.dao.Dao_myinfo_QnA;

public class DeleteQnACommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		/*
	 	-----------------------------
	 	21.05.20 hyokyeong JO
	 	DB table qna_review	 	
	 	-----------------------------
		*/

	
		int qna_no = Integer.parseInt(request.getParameter("qna_no"));
		System.out.println(qna_no);
		
		Dao_myinfo_QnA dao = new Dao_myinfo_QnA();
		dao.deleteQnA(qna_no);
		
		
		
	}

}
