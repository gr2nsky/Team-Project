package com.ysms.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ysms.dao.Dao_Review;

public class DeleteReviewCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		
		/*
	 	-----------------------------
	 	21.05.23 hyokyeong JO
	 	DB table rental
	 	delete review 	
	 	-----------------------------
		*/

	
		int rentalNo = Integer.parseInt(request.getParameter("rentalNo"));
		
		Dao_Review dao = new Dao_Review();
		dao.deleteReview(rentalNo);
	}

}
