package com.ysms.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ysms.dao.Dao_Review;
import com.ysms.dto.Dto_Review;

public class ReviewCommand implements Command {

	/* 
	 	-----------------------------
	 	21.05.18 hyokyeong JO
	 	DB table qna_review
	 	
	 	****현재 place_no = 3으로 변수 선언해서 진행중
	 	-> 추후 세션으로 받아와서 바꿀 것.
	 	-----------------------------
	 */
//	int place_no = 3;
	int reviewNumOfTuplesPerPage = 1;
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("Dao_Review - execute");;
	
		int place_no = Integer.parseInt(request.getParameter("place_no"));	

		// 사용자가 요청한 페이지 번호 초기값은 가장 최신글을 보여주는 1
		int reviewRequestPage = 1;
		Dao_Review dao = new Dao_Review();
		HttpSession session = request.getSession();
		
		session.setAttribute("placeNo", place_no);
		
		// 최초 목록 진입시 page값을 넘겨주지 않음 -> 초기값인 1페이지 목록을 보여줌
		// 목록에서 page요청 -> 해당 페이지 번호로 requestPage 설정
		if (request.getParameter("reviewPage") != null) {
			reviewRequestPage = Integer.parseInt(request.getParameter("reviewPage"));
			// content에서 목록보기 요청시 최근 페이지 목록으로 돌아가기 위해 세션에 저장
			session.setAttribute("currentPage", reviewRequestPage);
		}
		// 반환되는 총 튜플의 수
		int countedTuple = dao.countTuple(place_no);
		// 페이지 목록 (1...n)
		ArrayList<Integer> pageList = calcNumOfPage(countedTuple);
		// 페이지 목록을 세션에 담는다. *list에 진입하면 무조건 세션이 갱신되므로 새 글이 생겨도 최신화가 된다.
		session.setAttribute("reviewPageList", pageList);

		
		// Review List 호출
		ArrayList<Dto_Review> dtoReview = dao.reviewList(place_no, reviewRequestPage, reviewNumOfTuplesPerPage);
		String empty = "";
		
			if(dtoReview.isEmpty() == true) {
				request.setAttribute("reviewList", empty);
			}else {
				request.setAttribute("reviewList", dtoReview);
			}
	}
	
	
	
	public ArrayList<Integer> calcNumOfPage(int countedTuple){
		ArrayList<Integer> reviewArr = new ArrayList<Integer>();
		
		int calcPage = 0;
		
		// 튜플의 총 갯수가 딱 맞아떨어지는 경우를 대비해 조건분기
		if (countedTuple % reviewNumOfTuplesPerPage == 0) {
			calcPage = countedTuple / reviewNumOfTuplesPerPage;
		} else {
			calcPage = countedTuple / reviewNumOfTuplesPerPage + 1;
		}
		
		for(int i=1; i<=calcPage; i++) {
			reviewArr.add(i);
		}
		
		return reviewArr;
	}

}
