package com.ysms.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ysms.dao.Dao_Announce;
import com.ysms.dto.Dto_Announce;

public class AnnounceCommand implements Command {

	// 화면에 한 페이지당 나타낼 게시물 수
	int numOfTuplesPerPage = 5;
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		String search_title = "";
		String search_content = "";
		String search = "";
		
		if(request.getParameter("getSearch") != null) {
			search = request.getParameter("getSearch");
		}
		
		String word = request.getParameter("word");
		
		
		
		if(search.equals("title")) {
			search_title = word;
		}else if(search.equals("content")) {
			search_content = word;
		}
		
		
		// 사용자가 요청한 페이지 번호 초기값은 가장 최신글을 보여주는 1
		int requestPage = 1;
		
		Dao_Announce dao = new Dao_Announce();
		
		HttpSession session = request.getSession();
		
		// 최초 목록 진입시 page값을 넘겨주지 않음 -> 초기값인 1페이지 목록을 보여줌
		// 목록에서 page요청 -> 해당 페이지 번호로 requestPage 설정
		if (request.getParameter("page") != null) {
			requestPage = Integer.parseInt(request.getParameter("page"));
			// content에서 목록보기 요청시 최근 페이지 목록으로 돌아가기 위해 세션에 저장
			session.setAttribute("currentPage", requestPage);
		}
		// 반환되는 총 튜플의 수
		int countedTuple = dao.countTuple(search_title, search_content);
		// 페이지 목록 (1...n)
		ArrayList<Integer> pageList = calcNumOfPage(countedTuple);
		// 페이지 목록을 세션에 담는다. *list에 진입하면 무조건 세션이 갱신되므로 새 글이 생겨도 최신화가 된다.
		session.setAttribute("pageList", pageList);
		
		ArrayList<Dto_Announce> dtos = dao.announceList(requestPage, numOfTuplesPerPage, search_title, search_content);
		request.setAttribute("announceList", dtos);

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
