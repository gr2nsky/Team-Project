package com.ysms.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ysms.dao.Dao_myinfo_Rental;
import com.ysms.dto.Dto_Rental;

public class MyinfoRentalScheduledCommand implements Command {

	/* 
 	-----------------------------
 	21.05.22 hyokyeong JO
 	DB table rental, share

	userId
 	-----------------------------
	 */
	int numOfTuplesPerPage = 5;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		// 사용자가 요청한 페이지 번호 초기값은 가장 최신글을 보여주는 1
		int requestPage = 1;
		Dao_myinfo_Rental dao = new Dao_myinfo_Rental();
		HttpSession session = request.getSession();
		
		String user_id = (String) session.getAttribute("loginedUserID");
		
		
		// Page 처리
		if (request.getParameter("rentalScheduledPage") != null) {
			requestPage = Integer.parseInt(request.getParameter("rentalScheduledPage"));
			// content에서 목록보기 요청시 최근 페이지 목록으로 돌아가기 위해 세션에 저장
			session.setAttribute("currentPage", requestPage);
		}
		int countedTuple = dao.scheduledCountTuple(user_id);
		ArrayList<Integer> rentalScheduledPageList = calcNumOfPage(countedTuple);
		session.setAttribute("rentalScheduledPageList", rentalScheduledPageList);

		
		// rentalList (예정된 예약) 출력
		ArrayList<Dto_Rental> myinfoRentalScheduledList = dao.myinfoRentalScheduledList(user_id, requestPage, numOfTuplesPerPage);
		String empty = "";
		
			if(myinfoRentalScheduledList.isEmpty() == true) {
				request.setAttribute("myinfoRentalScheduledList", empty);
			}else {
				request.setAttribute("myinfoRentalScheduledList", myinfoRentalScheduledList);
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
