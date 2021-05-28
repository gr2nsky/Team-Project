package com.ysms.command;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import com.ysms.dao.Dao_Share;


public class DeleteCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int no = Integer.parseInt(request.getParameter("no"));
		
		Dao_Share dao = new Dao_Share();
		// 일단 share 파트 지우기전에 place_no 찾아서 저장해두기
		int place_no = dao.Find_placeNo_From_share(no);
		
		// place_no 찾았으면 share 부분 지우기 
		System.out.println("place_no (before start deleteShare) : " + place_no);
		dao.deleteShare(no);
	
		// place 부분은 removeDate만 추가해주기! -> 나머지 검색할때 is null 붙여줘야함
		System.out.println("place_no (before start deletePlace) : " + place_no);
		dao.deletePlace(place_no);


	}

}
