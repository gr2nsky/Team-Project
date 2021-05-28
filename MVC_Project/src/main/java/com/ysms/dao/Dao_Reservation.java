package com.ysms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.ysms.common.LoginedUserInfo;
import com.ysms.dto.Dto_Payment;
import com.ysms.dto.Dto_Reservation_rental;
import com.ysms.dto.Dto_Reservation_rentalDetail;

public class Dao_Reservation {
	DataSource dataSource;
	
	// DB Connecting
	public Dao_Reservation() {
		System.out.println("Connecting database...");
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/team4");
			System.out.println("Database connection success");
		} catch (NamingException e) {
			System.out.println("Database connection failed");
			e.printStackTrace();
		}
	}
	public ArrayList<Dto_Reservation_rentalDetail> refineSharesDetail(int place_no) {

		String query1 = "SELECT no, resName, resEmail, resPhone, resCapacity, price, checkInDate, startTime, endTime ";
		String query2 = "FROM (SELECT *, TIMESTAMPDIFF(DAY, now(), checkInDate) timeDiff FROM rental WHERE place_no = ? AND cancellationDate IS NULL) r ";
      	String query3 = "WHERE r.timeDiff BETWEEN -32 AND 32 ";
      	String query4 = "ORDER BY checkInDate ASC, startTime ASC";
      	String query = query1 + query2 + query3 + query4;
      	
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		ArrayList<Dto_Reservation_rentalDetail> dtos = new ArrayList<Dto_Reservation_rentalDetail>();

		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(query);
			psmt.setInt(1, place_no);
			rs = psmt.executeQuery();

			while (rs.next()) {
				int no = rs.getInt("no");
				String resName = rs.getString("resName");
				String resEmail = rs.getString("resEmail");
				String resPhone = rs.getString("resPhone");
				int resCapacity = rs.getInt("resCapacity");
				int price = rs.getInt("price");
				Timestamp checkInDate = rs.getTimestamp("checkInDate");
				System.out.println("checkInDate : " + checkInDate);
				int startTime = rs.getInt("startTime");
				int endTime = rs.getInt("endTime");

				Calendar calendar = Calendar.getInstance();
				calendar.setTime(checkInDate);
				
				int month = calendar.get(Calendar.MONTH) + 1;
				int date = calendar.get(Calendar.DATE);
				int usingTime = endTime - startTime;
				
				Dto_Reservation_rentalDetail dto = new Dto_Reservation_rentalDetail(no, resName, resEmail, resPhone, resCapacity, price, month, date, startTime, usingTime);
				System.out.println(month + " / " + date + " : " + startTime + " + " + usingTime);
				dtos.add(dto);
			}
			
			System.out.println("refineShares success");
		} catch (Exception e) {
			System.out.println("refineShares fail");
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
	
	public ArrayList<Dto_Reservation_rental> refineShares(int place_no) {

		String query1 = "SELECT checkInDate, startTime, endTime ";
		String query2 = "FROM (SELECT *, TIMESTAMPDIFF(DAY, now(), checkInDate) timeDiff FROM rental WHERE place_no = ? AND cancellationDate IS NULL) r ";
      	String query3 = "WHERE r.timeDiff BETWEEN -32 AND 32 ";
      	String query4 = "ORDER BY checkInDate ASC, startTime ASC";
      	String query = query1 + query2 + query3 + query4;
      	
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		ArrayList<Dto_Reservation_rental> dtos = new ArrayList<Dto_Reservation_rental>();

		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(query);
			psmt.setInt(1, place_no);
			rs = psmt.executeQuery();

			while (rs.next()) {
				Timestamp checkInDate = rs.getTimestamp("checkInDate");
				System.out.println("checkInDate : " + checkInDate);
				int startTime = rs.getInt("startTime");
				int endTime = rs.getInt("endTime");

				Calendar calendar = Calendar.getInstance();
				calendar.setTime(checkInDate);
				
				int month = calendar.get(Calendar.MONTH) + 1;
				int date = calendar.get(Calendar.DATE);
				int usingTime = endTime - startTime;
				
				Dto_Reservation_rental dto = new Dto_Reservation_rental(month, date, startTime, usingTime);
				System.out.println(month + " / " + date + " : " + startTime + " + " + usingTime);
				dtos.add(dto);
			}
			
			System.out.println("refineShares success");
		} catch (Exception e) {
			System.out.println("refineShares fail");
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
	
	public String payment(String checkInDate, int startTime, int endTime, int price, String user_id, 
			int place_no, String resName, String resPhone, String resEmail, int resCapacity) {
		
		String query1 = "INSERT INTO rental (rentalDate, checkInDate, startTime, endTime, "
				+ "price, user_id, place_no, resName, resPhone, resEmail, resCapacity) ";
		String query2 = "VALUES (now(), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		String query = query1 + query2;
		String result = "false";

		Connection conn = null;
		PreparedStatement psmt = null;

		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(query);

			psmt.setString(1, checkInDate);
			psmt.setInt(2, startTime);
			psmt.setInt(3, endTime);
			psmt.setInt(4, price);
			psmt.setString(5, user_id);
			psmt.setInt(6, place_no);
			psmt.setString(7, resName);
			psmt.setString(8, resPhone);
			psmt.setString(9, resEmail);
			psmt.setInt(10, resCapacity);
			
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

	public Dto_Payment findRental(int no) {
		Dto_Payment dto = null;
		String query = "SELECT place_no, checkInDate, startTime, endTime, resName, resPhone, resEmail, resCapacity, price FROM rental WHERE no = ?";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		String userID = null;

		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(query);
			psmt.setInt(1, no);
			rs = psmt.executeQuery();

			if (rs.next()) {
				int place_no = rs.getInt("place_no");
				String checkInDate = rs.getString("checkInDate");
				int startTime = rs.getInt("startTime");
				int endTime = rs.getInt("endTime");
				String resName = rs.getString("resName");
				String resPhone = rs.getString("resPhone");
				String resEmail = rs.getString("resEmail");
				int resCapacity = rs.getInt("resCapacity");
				int price = rs.getInt("price");
				
				dto = new Dto_Payment(place_no, checkInDate, startTime, endTime, price, resName, resPhone, resEmail, resCapacity);
				System.out.println("place_no : " + place_no +", checkInDate:" + checkInDate+", start-endTime : " + startTime + "-" + startTime + 
						", resName :" + resName + ", resPhone : " + resPhone +", resEmail : " + resEmail + ", resCapacity : " + resCapacity );
				
				System.out.println("findRental success");
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

		return dto;
	}
	
}

