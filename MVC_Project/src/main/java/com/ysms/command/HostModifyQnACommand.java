package com.ysms.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ysms.dao.Dao_Host_QnA;
import com.ysms.dao.Dao_myinfo_QnA;

public class HostModifyQnACommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		/*
	 	-----------------------------
	 	21.05.21 hyokyeong JO
	 	DB table qna_review
	 	
	 	Host Write and Modify QnA Answer
	 	-----------------------------
		*/

		int qna_no = Integer.parseInt(request.getParameter("qna_no"));
		String qna_answer = request.getParameter("qnaAnswer");
		
		System.out.println(qna_answer);
		
		Dao_Host_QnA dao = new Dao_Host_QnA();
		dao.hostModifyQnA(qna_no, qna_answer);
		
		
	}

}
