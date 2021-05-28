package com.ysms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.ysms.common.LoginedUserInfo;
import com.ysms.dto.Dto_SignUp;

public class Dao_MyInfo_UpdateProfile {
	DataSource dataSource;

	public Dao_MyInfo_UpdateProfile() {
		System.out.println("Connectiong database...");
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/team4");
			System.out.println("Database connection success");
		} catch (NamingException e) {
			System.out.println("Database connection failed");
			e.printStackTrace();
		}
	}
	
	public Dto_SignUp getUserInfo(String inputId) {
		Dto_SignUp dto = null;
		String query = "SELECT * FROM user WHERE id = ?";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(query);
			psmt.setString(1, inputId);
			rs = psmt.executeQuery();

			if (rs.next()) {
				
				String id = rs.getString("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				int status = Integer.parseInt(rs.getString("status"));
				String birthday = rs.getString("birthday");
				String filePath = rs.getString("filePath");
				
				dto = new Dto_SignUp(id, name, email, phone, status, birthday, filePath);
				System.out.println("getUserInfo success");
			}
		} catch (Exception e) {
			System.out.println("getUserInfo fail");
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (psmt != null)
					psmt.close();
				if (conn != null)
					conn.close();
				System.out.println("< rs, psmt, conn close success>");
			} catch (Exception e) {
				System.out.println("< rs, psmt, conn close Fail>");
			}
		}

		return dto;
	}
	
	public String updateUserInfo(String id, String phone, String filePath) {
		String result = "false";
		String query = "UPDATE user SET phone = ?, filePath = ? WHERE id = ?";

		Connection conn = null;
		PreparedStatement psmt = null;

		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(query);
			psmt.setString(1, phone);
			psmt.setString(2, filePath);
			psmt.setString(3, id);
			psmt.executeUpdate();
			System.out.println("updateUserInfo success");
			result = "true";
		} catch (Exception e) {
			result = "false";
			System.out.println("updateUserInfo fail");
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null)
					psmt.close();
				if (conn != null)
					conn.close();
				System.out.println("< rs, psmt, conn close success>");
			} catch (Exception e) {
				System.out.println("< rs, psmt, conn close Fail>");
			}
		}

		return result;
	}
	public String updateUserInfo(String id, String pw, String phone, String filePath) {
		String result = "false";
		String query = "UPDATE user SET pw = ?, phone = ?, filePath = ? WHERE id = ?";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(query);
			psmt.setString(1, pw);
			psmt.setString(2, phone);
			psmt.setString(3, filePath);
			psmt.setString(4, id);
			psmt.executeUpdate();
			System.out.println("updateUserInfo success");
			result = "true";
		} catch (Exception e) {
			result = "false";
			System.out.println("updateUserInfo fail");
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null)
					psmt.close();
				if (conn != null)
					conn.close();
				System.out.println("< rs, psmt, conn close success>");
			} catch (Exception e) {
				System.out.println("< rs, psmt, conn close Fail>");
			}
		}
		
		return result;
	}
}
