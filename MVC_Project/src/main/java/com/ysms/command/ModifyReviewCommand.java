package com.ysms.command;
import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.ysms.common.FilePath;
import com.ysms.dao.Dao_Review;

public class ModifyReviewCommand implements Command {
	
	FilePath file = new FilePath();
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		
		MultipartRequest multi ;
		int rentalNo ;
		String reviewContent ;
		String reviewFilePath ;
		int reviewScore ;
		
		
		// 3mb로 파일 크기를 제한
		int fileSize = 1024 * 1024 * 3;
		//현재 서비스되는 서버가 사용하는 저장공간의 경로를 불러옵니다.
		ServletContext context = request.getServletContext();
		String uploadPath = context.getRealPath("/reviewPhoto");
		
		//해당 디렉토리가 없을경우 디렉토리를 생성합니다.
		// File.io
		File folder = new File(uploadPath);
		if (!folder.exists()) {
			try {
				folder.mkdir(); // 폴더 생성합니다.
				System.out.println("reviewPhoto폴더가 생성되었습니다.");
			} catch (Exception e) {
				e.getStackTrace();
			}
		}

		try {
			//일반 request와는 구분됩니다. request.getParameter로는 값을 가져올 수 없습니다.
			multi = new MultipartRequest(request, uploadPath, fileSize, "UTF-8", new DefaultFileRenamePolicy());
			
			rentalNo = Integer.parseInt(multi.getParameter("rentalNo"));

			Dao_Review dao = new Dao_Review();
			
			String uploadFile = multi.getFilesystemName("reviewImg");
			
			//업로드한 파일이 없을시 save/null로 db에 기록되므로, 이를 방지하기 위해 분기
			if(uploadFile == null) {
				reviewFilePath =  "reviewPhoto/" + multi.getParameter("reviewOldFilePath");
			}else {
				reviewFilePath = "reviewPhoto/" +  uploadFile;
			}	
			reviewContent = multi.getParameter("reviewContent");
			reviewScore = Integer.parseInt(multi.getParameter("reviewScore"));
			
			dao.modifyReview(rentalNo, reviewContent, reviewFilePath, reviewScore);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
