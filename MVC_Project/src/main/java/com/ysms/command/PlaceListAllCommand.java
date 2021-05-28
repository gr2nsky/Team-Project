	/*
	 * 21.05.27 hyokyeong
	 * 공간 목록 클릭시 공간 리스트 전부 띄우기 
	 */
package com.ysms.command;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ysms.dao.Dao_SearchPlace;
import com.ysms.dto.Dto_SearchPlace;

public class PlaceListAllCommand implements Command {
	
	//페이지당 표시할 게시글의 수
	int numOfTuplesPerPage = 5;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)  {

		
		
		//사용자가 요청한 페이지 번호 초기값은 가장 최신글을 보여주는 1
		int requestPage = 1;
		
		Dao_SearchPlace dao = new Dao_SearchPlace(); // BDao와 연결
		HttpSession session = request.getSession();
		
					
		
		//최초 목록 진입시 page값을 넘겨주지 않음 -> 초기값인 1페이지 목록을 보여줌
		//목록에서 page요청 -> 해당 페이지 번호로 requestPage 설정
		if (request.getParameter("shareListpage") != null) {
			requestPage = Integer.parseInt(request.getParameter("shareListpage"));
			System.out.println();
			//content에서 목록보기 요청시 최근 페이지 목록으로 돌아가기 위해 세션에 저
			session.setAttribute("currentPage", requestPage);
			
			System.out.println(requestPage + "페이지 요청");
		}
		
		//반환되는 총 튜플의 수
		int countedTuple = dao.countShareTuple();
		
		//페이지 목록 (1...n)
			ArrayList<Integer> pageList = calcNumOfPage(countedTuple);
			//페이지 목록을 세션에 담는다. *list에 진입하면 무조건 세션이 갱신되므로 새 글이 생겨도 최신화가 된다.
			session.setAttribute("shareListpage", pageList);
			
		
		ArrayList<Dto_SearchPlace> dtos = dao.shareList(requestPage, numOfTuplesPerPage);
		request.setAttribute("shareList", dtos);
		
	}

	
	
	//총 튜플수를 받아 페이지당 표시할 게시글의 수로 나누어서 페이지수를 계산하고 jsp에서 for-each문을 돌리기 위해 배열에 담는다
	public ArrayList<Integer> calcNumOfPage(int countedTuple) {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		int calcPage = 0;
		// 튜플의 총 갯수가 딱 맞아떨어지는 경우를 대비해 조건분기
		if (countedTuple % numOfTuplesPerPage == 0) {
			calcPage = countedTuple / numOfTuplesPerPage;
		} else {
			calcPage = countedTuple / numOfTuplesPerPage + 1;
		}
		
		for (int i = 1; i <= calcPage; i++) {
			System.out.println(i);
			arr.add(i);
		}
		return arr;
	}
	
	
	
}///
