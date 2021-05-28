package com.ysms.command;

import java.io.File;

import javax.servlet.ServletContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.ysms.dao.Dao_Share;


public class WriteCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		MultipartRequest multi = null;
		String filePath = null;
		String user_id = null;
		String title = null;
		String introduce = null;
		String price = null;
		String startTime = null;
		String endTime = null;
		String dayLimit = null;
		
		
		// 3mb로 파일 크기를 제한
		int fileSize = 1024 * 1024 * 3;
		//현재 서비스되는 서버가 사용하는 저장공간의 경로를 불러옵니다.
		ServletContext context = request.getServletContext();
		String uploadPath = context.getRealPath("/save");
		
		//해당 디렉토리가 없을경우 디렉토리를 생성합니다.
		// File.io
		File folder = new File(uploadPath);
		if (!folder.exists()) {
			try {
				folder.mkdir(); // 폴더 생성합니다.
				System.out.println("폴더가 생성되었습니다.");
			} catch (Exception e) {
				e.getStackTrace();
			}
		} else {
			System.out.println("이미 폴더가 생성되어 있습니다.");
		}
			
			System.out.println(user_id);
		try {
			//일반 request와는 구분됩니다. request.getParameter로는 값을 가져올 수 없습니다.
			multi = new MultipartRequest(request, uploadPath, fileSize, "UTF-8", new DefaultFileRenamePolicy());
			user_id = multi.getParameter("user_id");
			title = multi.getParameter("title");
			introduce = multi.getParameter("introduce");
			price = multi.getParameter("price");
			startTime = multi.getParameter("startTime");
			endTime = multi.getParameter("endTime");
			dayLimit = multi.getParameter("dayLimit");
			dayLimit = dateLimitForm(dayLimit);
			
			String postCode = multi.getParameter("postCode");
			int capacity = Integer.parseInt(multi.getParameter("capacity"));
			int category = Integer.parseInt(multi.getParameter("category"));
			
			Dao_Share dao = new Dao_Share();
			int place_no = dao.Find_placeNo(postCode, capacity, category);

			
			String uploadFile = multi.getFilesystemName("uploadFile");
			Dao_Share dao02 = new Dao_Share();
			filePath = "save/" + uploadFile;
			dao02.write(title, user_id, introduce, price, filePath, place_no, startTime, endTime, dayLimit);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public String dateLimitForm(String dayLimit) {
		int strLen = dayLimit.length();
		String lessStr = "";
		
		if (strLen < 7) {
			int less = 7 - strLen;
			for(int i = 0; i < less; i++) {
				lessStr += "0";
			}
		}
		
		return lessStr + dayLimit;
	}

}
