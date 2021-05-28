package com.ysms.command;

import java.util.ArrayList;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import com.ysms.dao.Dao_Review;
import com.ysms.dto.Dto_Review;

public class MyinfoReviewCommand implements Command {

	/* 
	 	-----------------------------
	 	21.05.23 hyokyeong JO
	 	DB table rental, share
		my review list 출력
	 	-----------------------------
	 */	
	int numOfTuplesPerPage = 5;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		// 사용자가 요청한 페이지 번호 초기값은 가장 최신글을 보여주는 1
		int requestPage = 1;
		Dao_Review dao = new Dao_Review();
		HttpSession session = request.getSession();
		
		String user_id = (String) session.getAttribute("loginedUserID");
		
		
		// Page 처리
		if (request.getParameter("myinfoReviewPage") != null) {
			requestPage = Integer.parseInt(request.getParameter("myinfoReviewPage"));
			// content에서 목록보기 요청시 최근 페이지 목록으로 돌아가기 위해 세션에 저장
			session.setAttribute("currentPage", requestPage);
		}
		int countedTuple = dao.countTuple(user_id);
		ArrayList<Integer> myinfoReviewPageList = calcNumOfPage(countedTuple);
		session.setAttribute("myinfoReviewPageList", myinfoReviewPageList);

		
		// my review list 출력
		ArrayList<Dto_Review> myinfoReviewList = dao.myinfoReviewList(user_id, requestPage, numOfTuplesPerPage);
		String empty = "";
		
			if(myinfoReviewList.isEmpty() == true) {
				request.setAttribute("myinfoReviewList", empty);
			}else {
				request.setAttribute("myinfoReviewList", myinfoReviewList);
			}
	}

	public ArrayList<Integer> calcNumOfPage(int countedTuple){
		ArrayList<Integer> arr = new ArrayList<Integer>();
		
		int calcPage = 0;
		
		// 튜플의 총 갯수가 딱 맞아떨어지는 경우를 대비해 조건분기
		if (countedTuple % numOfTuplesPerPage == 0) {
			calcPage = countedTuple / numOfTuplesPerPage;
		} else {
			calcPage = countedTuple / numOfTuplesPerPage + 1;
		}
		
		for(int i=1; i<=calcPage; i++) {
			arr.add(i);
		}
		
		return arr;
	}

}// endLine