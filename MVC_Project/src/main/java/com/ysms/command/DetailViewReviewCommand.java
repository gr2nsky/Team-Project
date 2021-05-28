package com.ysms.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ysms.dao.Dao_Review;
import com.ysms.dto.Dto_Review;

public class DetailViewReviewCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		int rentalNo = Integer.parseInt(request.getParameter("rentalNo"));
		
		Dao_Review dao = new Dao_Review();
		Dto_Review detailViewReview = dao.reviewDetails(rentalNo);
		
		request.setAttribute("detailViewReview", detailViewReview);
	}

}
