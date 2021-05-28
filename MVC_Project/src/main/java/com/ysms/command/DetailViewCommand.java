/*
 * 수정일 2021-05-21
 * 수정자 윤재필
 * 내용
 * 		detail.jsp에 글 no를 전송하도록 수정
 * 		reservationInfo에 dto_share 전송하도록 수정
 */
package com.ysms.command;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import com.ysms.common.FilePath;
import com.ysms.common.ReservationInfo;
import com.ysms.dao.Dao_Share;
import com.ysms.dto.Dto_Share;

public class DetailViewCommand implements Command {

	FilePath file = new FilePath();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		int no = Integer.parseInt(request.getParameter("no"));

		Dao_Share dao = new Dao_Share();
		Dto_Share detail = dao.detail(no);

		// 파일이 업로드되있는 상태라면 (이름+경로) - 경로를 해서 파일 이름만 추출해서 request로 전송
		if (detail.getFilePath() != null) {
			String filePath = detail.getFilePath();
			String fileName = filePath.substring(file.Image.length());
			request.setAttribute("IMAGE", fileName);
		}
		ReservationInfo.detail = detail;
		request.setAttribute("DETAIL", detail);
		request.setAttribute("sNo", no);
	}

}
