package com.ysms.dao;

import java.sql.Connection;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.ysms.dto.Dto_Share;



public class Dao_Share {
	DataSource dataSource;

	// DB Connecting
	public Dao_Share() {
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
	
	// [Paging 01]
	// 사용자가 요청한 페이지번째(requestPage : offset)와 페이지당 표시할 게시글의 수(numOfTuplePerPage : limit)을 매개변수로 받는다.
	public ArrayList<Dto_Share> list(String id, int requestPage, int numOfTuplePerPage) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		ArrayList<Dto_Share> dtos = new ArrayList<Dto_Share>();
		// LIMIT {OFFSET}, {LIMIT} -> 쿼리결과중 offset번째부터 limit개의 튜플을 출력
		String query = "SELECT no, user_id, title, updateDate, place_no FROM share WHERE user_id = ? ORDER BY no DESC LIMIT ?, ?";
		// page는 1부터 시작하지만, offset은 0부터 시작.(0~9(10개), 10~19(10개)와같이 offset을 설정해야 하기 때문)
		int offset = requestPage - 1;

		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(query);
			psmt.setString(1, id);
				// 0을 나누면 에러가 발생하므로 예외처
				if (offset == 0) {
					psmt.setInt(2, offset);
				} else {
					psmt.setInt(2, offset * numOfTuplePerPage);
				}
			psmt.setInt(3, numOfTuplePerPage);
			rs = psmt.executeQuery();

			while (rs.next()) {
				int no = rs.getInt("no");
				String title = rs.getString("title");
				String user_id = rs.getString("user_id");
				String date = rs.getString("updateDate");
				int place_no = rs.getInt("place_no");

				Dto_Share dto = new Dto_Share(no, title, user_id, date, place_no);
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
				System.out.println("< rs, psmt, conn close Success : list >");
			} catch (Exception e) {
				System.out.println("< rs, psmt, conn close Fail : list >");
			}
		}
		return dtos;
	}
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  
	
	// [Paging 02]
	// list에서 사용하는 릴레이션이 가진 튜플의 총 갯수를 리턴한다.
	public int countTuple() {
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int count = 0;
		String query = "SELECT COUNT(*) FROM share";

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
				System.out.println("< rs, psmt, conn close Success : countTuple >");
			} catch (Exception e) {
				System.out.println("< rs, psmt, conn close Fail : countTuple >");
			}
		}

		return count;
	}
	
	// [Paging 03]
	//detail view를 위해 튜플의 모든 정보를 가져온다.
	public Dto_Share content(int contentNo) {
		Dto_Share dto = null;
		String query = "SELECT * FROM share where no = ?";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(query);
			psmt.setInt(1, contentNo);
			rs = psmt.executeQuery();

			if (rs.next()) {
				int no = rs.getInt("no");
				String title = rs.getString("title");
				String user_id = rs.getString("user_id");
				String introduce = rs.getString("introduce");
				String updateDate = rs.getString("updateDate");
				String filePath = rs.getString("filePath");

				dto = new Dto_Share(no, title, user_id, introduce, updateDate, filePath);
				System.out.println(" Data load success : content ");
			}

		} catch (Exception e) { 
			System.out.println(" Data load Fail : content ");
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (psmt != null)
					psmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println("< rs, psmt, conn close Fail>");
				e.printStackTrace();
			}
		}
		return dto;
	}
	
	// place와 share 에 있는 모든 정보 가져오기
	public Dto_Share detail(int placeNo) {
		System.out.println("* * Start Method : detail * *");
		
		Dto_Share dto = null;
		String query01 = "SELECT * FROM place, share WHERE place.no = share.place_no ";
		String query02 = "AND share.no = ?";
		
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(query01+query02);
			psmt.setInt(1, placeNo);
			rs = psmt.executeQuery();
			
			if (rs.next()) {
				int no = rs.getInt("share.no");
				int capacity = rs.getInt("capacity");
				int category = rs.getInt("category");
				int price = rs.getInt("price");
				int startTime = rs.getInt("startTime");
				int endTime = rs.getInt("endTime");
				String title = rs.getString("title");
				String user_id = rs.getString("user_id");
				String introduce = rs.getString("introduce");
				String postCode = rs.getString("postCode");
				String address1 = rs.getString("address1");
				String address2 = rs.getString("address2");
				Timestamp registrationDate = rs.getTimestamp("registrationDate");
				Timestamp removeDate = rs.getTimestamp("removeDate");
				String filePath = rs.getString("filePath");
				String dayLimit = rs.getString("dayLimit");
				int place_no = rs.getInt("place_no");
				
				dto = new Dto_Share(no, capacity, category, price, startTime, endTime, 
							  title, user_id, introduce, 
							  postCode, address1, address2, 
							  registrationDate, removeDate, 
							  filePath, dayLimit, place_no);
				
				System.out.println("  - share no : " + no);
				System.out.println("  - place no : " + place_no);
				System.out.println("  - user_id : " + user_id);
				System.out.println(" Data load success : detail ");
			}
			
		} catch (Exception e) { 
			System.out.println("  - Data load Fail : detail ");
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (psmt != null)
					psmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println("< rs, psmt, conn close Fail>");
				e.printStackTrace();
			}
		}
		return dto;
	}
	/*
	 * 추가 : 2021-05-27 윤재필
	 * placeNo으로 찾도록 추가작
	 */
	public Dto_Share rDetail(int placeNo) {
		System.out.println("* * Start Method : detail * *");
		
		Dto_Share dto = null;
		String query = "SELECT * FROM place, share WHERE place_no = ? AND place.no = share.place_no ";
		
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(query);
			psmt.setInt(1, placeNo);
			rs = psmt.executeQuery();
			
			if (rs.next()) {
				int no = rs.getInt("share.no");
				int capacity = rs.getInt("capacity");
				int category = rs.getInt("category");
				int price = rs.getInt("price");
				int startTime = rs.getInt("startTime");
				int endTime = rs.getInt("endTime");
				String title = rs.getString("title");
				String user_id = rs.getString("user_id");
				String introduce = rs.getString("introduce");
				String postCode = rs.getString("postCode");
				String address1 = rs.getString("address1");
				String address2 = rs.getString("address2");
				Timestamp registrationDate = rs.getTimestamp("registrationDate");
				Timestamp removeDate = rs.getTimestamp("removeDate");
				String filePath = rs.getString("filePath");
				String dayLimit = rs.getString("dayLimit");
				int place_no = rs.getInt("place_no");
				
				dto = new Dto_Share(no, capacity, category, price, startTime, endTime, 
						title, user_id, introduce, 
						postCode, address1, address2, 
						registrationDate, removeDate, 
						filePath, dayLimit, place_no);
				
				System.out.println("  - share no : " + no);
				System.out.println("  - place no : " + place_no);
				System.out.println("  - user_id : " + user_id);
				System.out.println(" Data load success : detail ");
			}
			
		} catch (Exception e) { 
			System.out.println("  - Data load Fail : detail ");
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (psmt != null)
					psmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println("< rs, psmt, conn close Fail>");
				e.printStackTrace();
			}
		}
		return dto;
	}
	
	/* DB Insert : share Relation
	 *  - 2021.05.17 18:30
	 *  - Park Jaewon
	 */
	
	public void write(String title, String user_id, String introduce, String price, String filePath, int place_no, String startTime, String endTime, String dayLimit) {
		System.out.println("* * Start Method : write * *");
		
		// 시간당 가격
		int temp00 = Integer.parseInt(price);
		// 시작시간 : 등록자가 지정
		int temp01 = Integer.parseInt(startTime);
		// 종료시간 : 등록자가 지정 
		int temp02 = Integer.parseInt(endTime);
		
		// SQL Query : share에 정보 입력하기
		String query01 = "INSERT INTO share (title, user_id, introduce, updateDate, price, filePath, place_no, startTime, endTime, dayLimit) ";
		String query02 = "VALUES (?, ?, ?,now(), ?, ?, ?,?,?,?)";
		Connection conn = null;
		PreparedStatement psmt = null;

		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(query01 + query02);
			
			psmt.setString(1, title);
			psmt.setString(2, user_id);
			psmt.setString(3, introduce);
			psmt.setInt(4, temp00);
			psmt.setString(5, filePath);
			psmt.setInt(6, place_no);
			psmt.setInt(7, temp01);
			psmt.setInt(8, temp02);
			psmt.setString(9, dayLimit);
			
			psmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("< Data insert Fail to 'share'>");
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null)
					psmt.close();
				if (conn != null)
					conn.close();
				System.out.println("< Data insert Success to 'share' >");
				System.out.println("< psmt, conn close Success >");
			} catch (Exception e) {
				System.out.println("< psmt, conn close Fail >");
				e.printStackTrace();
			}
		}
	}
	
	/* DB Insert : Place Entity
	 *  - 2021.05.17 18:00
	 *  - Park Jaewon
	 */
	
	// 공간 등록하기 1단계 (DB : place Entity에 정보 입력시키기)
	public void write_Space(String postCode, String address1, String address2, String capacity, String category) {
		System.out.println("* * Start Method : write_Space * *");
		
		//SQL Query
		String query = "INSERT INTO place (postCode, address1, address2, capacity, registrationDate, category) VALUES (?, ?, ?, ?, now(), ?)";
		// address1 : 주소 API에서 입력되는 값
		// address2 : 사용자가 직접 입력하는 값
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		// 공간 수용인원
		int temp1 = Integer.parseInt(capacity);
		// 공간이 어떤 유형인지 카테고리 선택
		int temp2 = Integer.parseInt(category);
				
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(query);

			psmt.setString(1, postCode);
			psmt.setString(2, address1);
			psmt.setString(3, address2);
			psmt.setInt(4, temp1);
			psmt.setInt(5, temp2);

			psmt.executeUpdate();
			System.out.println("< Data insert success : write_Space ( to place entity ) >");
			
		} catch (Exception e) {
			System.out.println("< Data insert Fail : write_Space  ( to place entity ) >");
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null)
					psmt.close();
				if (conn != null)
					conn.close();
				System.out.println("< psmt, conn close Success : write_Space >");
			} catch (Exception e) {
				System.out.println("< psmt, conn close Fail : write_Space >");
				e.printStackTrace();
			}
		}
	}
	
	/* DB Search : SEARCH no FROM place
	 *  - 2021.05.17 17:00
	 *  - Park Jaewon
	 */
	
	// DB 1단계 입력 -> "방금 입력한 튜플의 no를 찾기 위함임" -> Share에 입력하는 값 입력
	public int Find_placeNo(String postCode, int capacity, int category) {
		System.out.println("* * Start Method : Find_placeNo * *");
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		// 찾고자 하는 값
		int place_no = 0;
		
		// 전 페이지에서 입력한 우편번호 + 인원 + 카테고리 조건이 맞는 값 찾기
		System.out.println("  - Data search : postCode = " + postCode);
		String query = "SELECT no FROM place WHERE postCode = '" + postCode +"'";
		String query2 = " and capacity = " + capacity;
		String query3 = " and category = " + category;

		try {
			conn = dataSource.getConnection();
			
			psmt = conn.prepareStatement(query + query2 + query3);
			rs = psmt.executeQuery();
			// 0을 나누면 에러가 발생하므로 예외처
			while (rs.next()) {
				place_no = rs.getInt("no");

				System.out.println("  - no : " + place_no);
				System.out.println("< Data load success >");
			}
		} catch (Exception e) {
			e.printStackTrace();
	
		}
		return place_no;
	}
	
	/* Update Method : use detail dto
	 *  - 2021.05.18 12:00
	 *  - Park Jaewon
	 */
	
	public void update(int no, String title, String introduce, String filePath, String price, String startTime, String endTime, String dayLimit) {
		System.out.println("* * Start Method : update * *");
		
		System.out.println(no);
		
		// 시간당 가격
		int temp00 = Integer.parseInt(price);
		// 시작시간 : 등록자가 수정
		int temp01 = Integer.parseInt(startTime);
		// 종료시간 : 등록자가 수정 
		int temp02 = Integer.parseInt(endTime);
		
		
		System.out.println("  - share no : " + no);
		System.out.println("  - Query start");
		
		String query01 = "UPDATE share SET ";
		String query02 = "title = ?, introduce = ?, filePath = ? ,";
		String query03 = "price = ?, startTime = ?, endTime = ?, dayLimit = ? ";
		String query04 = "WHERE share.no = ?";

		Connection conn = null;
		PreparedStatement psmt = null;

		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(query01 + query02 + query03 + query04);

			psmt.setString(1, title);
			psmt.setString(2, introduce);
			psmt.setString(3, filePath);
			psmt.setInt(4, temp00);
			psmt.setInt(5, temp01);
			psmt.setInt(6, temp02);
			psmt.setString(7, dayLimit);
			psmt.setInt(8, no);

			psmt.executeUpdate();
			
			System.out.println("  - changed price : " + temp00);
			System.out.println("  - Data update Success");
		} catch (Exception e) {
			System.out.println("  - Data update Fail");
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null)
					psmt.close();
				if (conn != null)
					conn.close();
				System.out.println("< psmt, conn close Success : update >");
			} catch (Exception e) {
				System.out.println("< psmt, conn close Fail : update >");
				e.printStackTrace();
			}
		}
	}	
	
	/* Delete command : ready to delete ( find place_no from share )
	 *  - 2021.05.18 14:50
	 *  - Park Jaewon
	 */
	
	
	public int Find_placeNo_From_share(int no) {
		System.out.println("* * Start Method : Find_placeNo_From_share * *");
		int place_no = 0;
		
		System.out.println("  - place_no :  " + place_no);
		System.out.println("  - Query Start");
			
		String query01 = "SELECT place_no FROM share WHERE no = ? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
			
			try {
				conn = dataSource.getConnection();
				psmt = conn.prepareStatement(query01);
				psmt.setInt(1, no);
				rs = psmt.executeQuery();
				
				while (rs.next()) {
					place_no =  rs.getInt("place_no");
					
					System.out.println("   - place_no (Query Comp) :  " + place_no);
					System.out.println(" Data load success : detail ");
				}
				
			} catch (Exception e) { 
				System.out.println("  - Data load Fail : detail ");
				e.printStackTrace();
			} finally {
				try {
					if (rs != null)
						rs.close();
					if (psmt != null)
						psmt.close();
					if (conn != null)
						conn.close();
				} catch (Exception e) {
					System.out.println("< rs, psmt, conn close Fail>");
					e.printStackTrace();
				}
			}
			return place_no;
	}
	
	public void deleteShare(int no) {
		System.out.println("* * Start Method : deleteShare * *");
		String query = "DELETE FROM share WHERE no = ?";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(query);
			psmt.setInt(1, no);
			psmt.executeUpdate();
			System.out.println("  - Data delete from Share : Success >");
		} catch (Exception e) {
			System.out.println("  - Data delete from Share : Fail >");
			e.printStackTrace();
		} finally {
			try {
				if(psmt != null)
					psmt.close();
				if(conn != null)
					conn.close();
				System.out.println("< psmt, conn close Success : deleteShare >");
			} catch (Exception e) {
				System.out.println("< psmt, conn close Fail : deleteShare >");
				e.printStackTrace();
			}
		}
	}
	
	public void deletePlace(int place_no) {
		System.out.println("* * Start Method : deletePlace * *");
		
		System.out.println("  - place_no : " + place_no);
		
		
		System.out.println("  - Query start");
		
		String query01 = "UPDATE place SET ";
		String query02 = "removeDate = now() ";
		String query03 = "WHERE no = ?";

		Connection conn = null;
		PreparedStatement psmt = null;

		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(query01 + query02 + query03);

			psmt.setInt(1, place_no);

			psmt.executeUpdate();
			
			System.out.println("  - Data update Success : deletePlace // add removeDate ");
		} catch (Exception e) {
			System.out.println("  - Data update Fail : deletePlace // add removeDate ");
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null)
					psmt.close();
				if (conn != null)
					conn.close();
				System.out.println("< psmt, conn close Success : update >");
			} catch (Exception e) {
				System.out.println("< psmt, conn close Fail : update >");
				e.printStackTrace();
			}
		}
	}	
	
	
	
	
}
