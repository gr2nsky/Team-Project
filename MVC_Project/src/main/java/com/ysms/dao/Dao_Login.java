/*
 * 2021 5 24
 * login_try 에서 추가적인 유저정보 주입하도록 수정
 * 
 */

package com.ysms.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.ysms.common.*;

public class Dao_Login {

	DataSource dataSource;

	public Dao_Login() {
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

	public String tryToLogin(String inputID, String inputPW) {
		String query = "SELECT id, status, name, phone, email FROM user WHERE id = ? AND pw = ?";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		String userID = null;

		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(query);
			psmt.setString(1, inputID);
			psmt.setString(2, inputPW);
			rs = psmt.executeQuery();

			if (rs.next()) {
				userID = rs.getString("id");
				LoginedUserInfo.id = rs.getString("id");
				LoginedUserInfo.name = rs.getString("name");
				LoginedUserInfo.email = rs.getString("email");
				LoginedUserInfo.phone = rs.getString("phone");
				LoginedUserInfo.status = Integer.parseInt(rs.getString("status"));
				System.out.println("loginProcess success");
			}
		} catch (Exception e) {
			System.out.println("loginProcess fail");
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

		return userID;
	}

	public String IDdupleCheck(String inputID) {
		String result = "unuseable";
		String query = "SELECT id FROM user WHERE id = ?";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(query);
			psmt.setString(1, inputID);
			rs = psmt.executeQuery();

			if (!rs.next()) {
				result = "useable";
				System.out.println("this id possible to use.");
			} else {
				result = "unuseable";
				System.out.println("this id is already in use.");
			}
		} catch (Exception e) {
			System.out.println("duplecheck fail");
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

		return result;
	}

	public String emailDupleCheck(String inputEmail) {
		String result = "unuseable";
		String query = "SELECT email FROM user WHERE email = ?";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(query);
			psmt.setString(1, inputEmail);
			rs = psmt.executeQuery();

			if (!rs.next()) {
				result = "useable";
				System.out.println("this email possible to use.");
			} else {
				result = "unuseable";
				System.out.println("this email is already in use.");
			}
		} catch (Exception e) {
			System.out.println("email duplecation check fail");
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

		return result;
	}

	public String signUp(String id, String name, String pw, String email, String phone, int status, String birthday,
			String filePath) {

		String query1 = "INSERT INTO user (id, name, pw, email, phone, status, birthday, filePath, signDate) ";
		String query2 = "VALUES (?, ?, ?, ?, ?, ?, ?, ?, now())";
		String query = query1 + query2;
		String result = "false";

		Connection conn = null;
		PreparedStatement psmt = null;

		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(query);

			psmt.setString(1, id);
			psmt.setString(2, name);
			psmt.setString(3, pw);
			psmt.setString(4, email);
			psmt.setString(5, phone);
			psmt.setInt(6, status);
			psmt.setString(7, birthday);
			psmt.setString(8, filePath);
			psmt.executeUpdate();
			result = "true";
		} catch (Exception e) {
			System.out.println("<data insert Fail>");
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null)
					psmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println("< psmt, conn close Fail>");
				e.printStackTrace();
			}
		}
		return result;
	}

	public String findAccount(String inputedEmail) {
		String query = "SELECT id FROM user WHERE email = ?";
		String userID = null;

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(query);
			psmt.setString(1, inputedEmail);
			rs = psmt.executeQuery();

			if (rs.next()) {
				userID = rs.getString("id");
				System.out.println("findAccount success");
			}
		} catch (Exception e) {
			System.out.println("findAccount fail");
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

		return userID;
	}

	public String findAccount(String inputedEmail, String inputedID) {
		String query = "SELECT pw FROM user WHERE email = ? AND id = ?";
		String userPW = null;

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(query);
			psmt.setString(1, inputedEmail);
			psmt.setString(2, inputedID);
			rs = psmt.executeQuery();

			if (rs.next()) {
				userPW = rs.getString("pw");
				System.out.println("findAccount success");
			}
		} catch (Exception e) {
			System.out.println("findAccount fail");
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

		return userPW;
	}

}