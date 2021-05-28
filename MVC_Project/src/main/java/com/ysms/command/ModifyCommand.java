package com.ysms.command;

import java.io.File;

import javax.servlet.ServletContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.ysms.common.FilePath;
import com.ysms.dao.Dao_Share;

public class ModifyCommand implements Command {


	FilePath file = new FilePath();
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MultipartRequest multi = null;
		int no = 0;
		String title = null;
		String introduce = null;
		String filePath = null;
		String price = null;
		String startTime = null;
		String endTime = null;
		String dayLimit = null;

		// 3mb로 파일 크기를 제한
		int fileSize = 1024 * 1024 * 3;
		ServletContext context = request.getServletContext();
		String uploadPath = context.getRealPath("/save");

		File folder = new File(uploadPath);
		// 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
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

		try {
			//일반 request와는 구분됩니다. request.getParameter로는 값을 가져올 수 없습니다.
			multi = new MultipartRequest(request, uploadPath, fileSize, "UTF-8", new DefaultFileRenamePolicy());
			
			System.out.println(no);
			
			no = Integer.parseInt(multi.getParameter("no"));

			System.out.println(no);
			Dao_Share dao = new Dao_Share();
			
			String uploadFile = multi.getFilesystemName("uploadFile");
			
			//업로드한 파일이 없을시 save/null로 db에 기록되므로, 이를 방지하기 위해 분기
			if(uploadFile == null) {
				filePath = multi.getParameter("oldFilePath");
				title = multi.getParameter("title");
				introduce = multi.getParameter("introduce");
				price = multi.getParameter("price");
				startTime = multi.getParameter("startTime");
				endTime = multi.getParameter("endTime");
				dayLimit = multi.getParameter("dayLimit");
				dao.update(no, title, introduce, filePath, price, startTime, endTime, dayLimit);
				System.out.println("update success : uploadFile was null");
			} else {
				filePath = "save/" + uploadFile;
				
				title = multi.getParameter("title");
				introduce = multi.getParameter("introduce");
				price = multi.getParameter("price");
				startTime = multi.getParameter("startTime");
				endTime = multi.getParameter("endTime");
				dayLimit = multi.getParameter("dayLimit");
				
				dao.update(no, title, introduce, filePath, price, startTime, endTime, dayLimit);
				System.out.println("update success : uploadFile was not null");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("update fail");
		}
	}
}
