package com.ysms.common;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

//싱글톤 디자인 패턴
public class FilePath_login {
	public String signUp_filePath = "/userPhoto";
	int signUp_fileSize = 1024 * 1024 * 3;
	String signUp_charForm = "UTF-8";
	DefaultFileRenamePolicy dfp = new DefaultFileRenamePolicy();
	
	private static FilePath_login fl = new FilePath_login();
	private FilePath_login() {}
	
	public static FilePath_login getInstance() {
		return fl;
	}
	
	public MultipartRequest getMultipartRequest(HttpServletRequest request) {
		String uploadPath = getRealPath(request);
		System.out.println("getMultipartRequest : " + uploadPath);
		MultipartRequest multi = null;
		
		try {
			multi = new MultipartRequest(request, uploadPath, signUp_fileSize, signUp_charForm, dfp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return multi;
	}
	
	public String getRealPath(HttpServletRequest request) {
		ServletContext context = request.getServletContext();
		String uploadPath = context.getRealPath(signUp_filePath);
		System.out.println(uploadPath);
		
		//해당 디렉토리가 없을경우 디렉토리를 생성합니다.
		File folder = new File(uploadPath);
		if (!folder.exists()) {
			try {
				folder.mkdir(); // 폴더 생성합니다.
				System.out.println("/userPhoto 폴더가 생성되었습니다.");
			} catch (Exception e) {
				e.getStackTrace();
			}
		} else {
			System.out.println("/userPhoto 폴더가 이 생성되어 있습니다.");
		}
		return uploadPath;
	}
}
