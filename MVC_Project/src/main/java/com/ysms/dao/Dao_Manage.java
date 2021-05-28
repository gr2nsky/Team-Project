package com.ysms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.ysms.dto.Dto_SignUp;

public class Dao_Manage {
	DataSource dataSource;

	public Dao_Manage() {
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
	
	// list에서 사용하는 릴레이션이 가진 튜플의 총 갯수를 리턴한다.
		public int countTuple() {
			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			int count = 0;
			String query = "SELECT COUNT(*) FROM user";

			try {
				conn = dataSource.getConnection();
				psmt = conn.prepareStatement(query);
				rs = psmt.executeQuery();

				if (rs.next()) {
					count = rs.getInt(1);
					System.out.println("list-count success");
				}
			} catch (Exception e) {
				System.out.println("list-count fail");
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

			return count;
		}
		
		public ArrayList<Dto_SignUp> getAllUserList(int requestPage, int numOfTuplePerPage) {
			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			ArrayList<Dto_SignUp> dtos = new ArrayList<Dto_SignUp>();
			// LIMIT {OFFSET}, {LIMIT} -> 쿼리결과중 offset번째부터 limit개의 튜플을 출력
			String query = "SELECT no, id, name, email, phone, cancelDate FROM user ORDER BY no DESC LIMIT ?, ?";
			// page는 1부터 시작하지만, offset은 0부터 시작.(0~9(10개), 10~19(10개)와같이 offset을 설정해야 하기 때문)
			int offset = requestPage - 1;

			try {
				conn = dataSource.getConnection();
				psmt = conn.prepareStatement(query);
				// 0을 나누면 에러가 발생하므로 예외처
				if (offset != 0) {
					psmt.setInt(1, offset * numOfTuplePerPage);
				} else {
					psmt.setInt(1, offset);
				}
				psmt.setInt(2, numOfTuplePerPage);
				rs = psmt.executeQuery();

				while (rs.next()) {
					int no = rs.getInt("no");
					String id = rs.getString("id");
					String name = rs.getString("name");
					String email = rs.getString("email");
					String phone = rs.getString("phone");
					String cancelDate = rs.getString("cancelDate");
					
					Dto_SignUp dto = new Dto_SignUp(no, id, name, email, phone, cancelDate);
					dtos.add(dto);
					System.out.println("list-data load success");
				}
			} catch (Exception e) {
				System.out.println("list-data load fail");
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

			return dtos;
		}
		
		public String deleteUsers(String selectedUsers) {
			
			System.out.println(" deleteUsers : ㅎㅇ");
			String result = "false";
			String query = "UPDATE user SET cancelDate = now() WHERE no in (" + selectedUsers + ")";
			
			Connection conn = null;
			PreparedStatement psmt = null;
			
			try {
				conn = dataSource.getConnection();
				psmt = conn.prepareStatement(query);
				psmt.executeUpdate();
				result = "true";
			} catch (Exception e) {
				System.out.println("<data delete Fail>");
				e.printStackTrace();
			} finally {
				try {
					if(psmt != null)
						psmt.close();
					if(conn != null)
						conn.close();
				} catch (Exception e) {
					System.out.println("< psmt, conn close Fail>");
					e.printStackTrace();
				}
			}
			return result;
		}
		
		public Dto_SignUp getUserInfo(int no) {
			Dto_SignUp dto = null;
			String query = "SELECT * FROM user WHERE no = ?";

			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;

			try {
				conn = dataSource.getConnection();
				psmt = conn.prepareStatement(query);
				psmt.setInt(1, no);
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
}
