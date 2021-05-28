package com.ysms.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ysms.common.LoginedUserInfo;
import com.ysms.dao.Dao_myinfo_QnA;
import com.ysms.dto.Dto_QnA;

public class MyinfoQnACommand implements Command {

	/* 
	 	-----------------------------
	 	21.05.17 hyokyeong JO
	 	DB table qna_review
	 	
	 	****현재 sender = user01 으로 변수 선언해서 진행중
	 	-> 추후 세션으로 받아와서 바꿀 것.
	 	-----------------------------
	 */
	String user_id = LoginedUserInfo.id;
	
	int numOfTuplesPerPage = 5;
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("Dao_myinfo_QnAReview - execute");;

		// 사용자가 요청한 페이지 번호 초기값은 가장 최신글을 보여주는 1
		int requestPage = 1;
		Dao_myinfo_QnA dao = new Dao_myinfo_QnA();
		HttpSession session = request.getSession();
		
		
		System.out.println("user_id (MyinfoQnACommand) : " + user_id);
		// 최초 목록 진입시 page값을 넘겨주지 않음 -> 초기값인 1페이지 목록을 보여줌
		// 목록에서 page요청 -> 해당 페이지 번호로 requestPage 설정
		if (request.getParameter("myinfoQnaPage") != null) {
			requestPage = Integer.parseInt(request.getParameter("myinfoQnaPage"));
			// content에서 목록보기 요청시 최근 페이지 목록으로 돌아가기 위해 세션에 저장
			session.setAttribute("currentPage", requestPage);
		}
		// 반환되는 총 튜플의 수
		int countedTuple = dao.countTuple(user_id);
		// 페이지 목록 (1...n)
		ArrayList<Integer> myinfoQnaPageList = calcNumOfPage(countedTuple);
		// 페이지 목록을 세션에 담는다. *list에 진입하면 무조건 세션이 갱신되므로 새 글이 생겨도 최신화가 된다.
		session.setAttribute("myinfoQnaPageList", myinfoQnaPageList);

		
		// myInfoQnAList 호출
		ArrayList<Dto_QnA> dtoQnA = dao.myInfoQnAList(user_id, requestPage, numOfTuplesPerPage);
		String empty = "";
		
			if(dtoQnA.isEmpty() == true) {
				request.setAttribute("myInfoQnaList", empty);
			}else {
				request.setAttribute("myInfoQnaList", dtoQnA);
			}
			System.out.println(dtoQnA.isEmpty());
		
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

}
