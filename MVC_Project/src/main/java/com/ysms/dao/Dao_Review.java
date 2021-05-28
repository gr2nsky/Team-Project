	/* 
	 	-----------------------------
	 	21.05.23 hyokyeong JO
	 	DB table rental
	 	-----------------------------
	 */

package com.ysms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.ysms.common.FilePath;
import com.ysms.dto.Dto_Review;
	
public class Dao_Review {
	DataSource dataSource;
	
	
	// Context - DB연결
	public Dao_Review() {		
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/team4");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* 
		사용자가 요청한 페이지번째(requestPage : offset)와
		페이지당 표시할 게시글의 수(numOfTuplePerPage : limit)을 매개변수로 받는다.
	*/
	
	// 각 공간에 대한 Review List 보여주기
	public ArrayList<Dto_Review> reviewList(int place_no, int requestPage, int numOfTuplePerPage){
		ArrayList<Dto_Review> dtoReview = new ArrayList<Dto_Review>();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			
			// LIMIT {OFFSET}, {LIMIT} -> 쿼리결과중 offset번째부터 limit개의 튜플을 출력
			
			/* 
				SELECT u.filePath, r.user_id, r.reviewContent, r.reviewScore, r.reviewUpdateDate, r.filePath
				FROM rental r, user u
				WHERE r.place_no = ? AND u.id = r.user_id AND r.reviewSubmitDate is not null AND r.reviewRemoveDate is null;
			 */
			String query1 = "SELECT u.filePath, r.user_id, r.reviewContent, r.reviewScore, r.reviewUpdateDate, r.reviewRemoveDate, r.filePath ";
			String query2 = "FROM rental r, user u ";
			String query3 = "WHERE r.place_no = ? AND u.id = r.user_id AND r.reviewSubmitDate is not null AND r.reviewRemoveDate is null ";
			String query4 = "ORDER BY r.reviewSubmitDate DESC LIMIT ?, ?";
			
			// page는 1부터 시작하지만, offset은 0부터 시작.(0~9(10개), 10~19(10개)와같이 offset을 설정해야 하기 때문)
			int offset = requestPage - 1;
			
			preparedStatement = connection.prepareStatement(query1+query2+query3+query4);
			
			// place_no -> 추후 세션으로 받아와서 값 집어 넣을 것
			preparedStatement.setInt(1, place_no);
			
			
			// 0을 나누면 에러가 발생하므로 예외처리
			if(offset == 0) {
				preparedStatement.setInt(2, offset);
			}else {
				preparedStatement.setInt(2, offset * numOfTuplePerPage);
			}
			preparedStatement.setInt(3, numOfTuplePerPage);
			
			resultSet = preparedStatement.executeQuery();
			
//			select : u.filePath, r.user_id, r.reviewContent, r.reviewScore, r.reviewUpdateDate, r.reviewRemoveDate, r.filePath
			while(resultSet.next()) {
				String userFilePath = resultSet.getString("u.filePath");
				String rentalUser_id = resultSet.getString("r.user_id");
				String reviewContent = resultSet.getString("r.reviewContent");
				int reviewScore = resultSet.getInt("r.reviewScore");
				Date reviewUpdateDate = resultSet.getDate("r.reviewUpdateDate");
				Date reviewRemoveDate = resultSet.getDate("r.reviewRemoveDate");
				String reviewFilePath = resultSet.getString("r.filePath");
				
//				// updateDate 형식 yyyy-MM-dd HH:mm:ss로 바꾸기
//				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//				String reviewUpdateDate = sdf.format(ts_reviewUpdateDate);
				
				// filePath
				FilePath cv = new FilePath();
				
				// u.filepath가 null일때 기본이미지 띄우기
				if(userFilePath == null) {
					userFilePath = "basicPhoto.png";
				}else {
					userFilePath =  userFilePath.substring(cv.ysms.length());
				}
				
				// reviewFilePath가 null이 아닐때
				if(reviewFilePath != null) {
					reviewFilePath =  reviewFilePath.substring(cv.reviewPhoto.length());
				}
				//u.filePath, r.user_id, r.reviewContent, r.reviewScore, r.reviewUpdateDate, r.reviewRemoveDate, r.filePath
				Dto_Review reviewDto = new Dto_Review(rentalUser_id, reviewContent, reviewScore, reviewUpdateDate, reviewRemoveDate,reviewFilePath, userFilePath);
				dtoReview.add(reviewDto);
			
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return dtoReview; 
	}
	
	public int countTuple(int place_no) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		int count = 0;
		String query = "SELECT count(reviewContent) FROM rental WHERE place_no = ? AND reviewSubmitDate is not null AND reviewRemoveDate is null;";
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, place_no);
			resultSet = preparedStatement.executeQuery();

			if(resultSet.next()) {
				count = resultSet.getInt(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	
	
	/*
	 * 리뷰쓰기
	 * DB update : rental table
	 * 21.05.23 hyokyeong
	 */
	
	// query
//	UPDATE rental 
//	SET reviewContent = ?, reviewScore = ?, reviewSubmitDate = now(), reviewUpdateDate = now(), filePath = ?
//	WHERE no = ?;
	public void writeReview(int rentalNo, String reviewContent, String reviewFilePath, int reviewScore) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			
			String query1 = "UPDATE rental SET reviewContent = ?, reviewScore = ?, filePath = ?, ";
			String query2 = "reviewSubmitDate = now(), reviewUpdateDate = now(), reviewRemoveDate = null WHERE no = ? ";
			
			preparedStatement = connection.prepareStatement(query1+query2);
			preparedStatement.setString(1, reviewContent);
			preparedStatement.setInt(2, reviewScore);
			preparedStatement.setString(3, reviewFilePath);
			preparedStatement.setInt(4, rentalNo);
			
			preparedStatement.executeUpdate();
			
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	} // End writeReview **********
	
	
	// Review Detail View
	public Dto_Review reviewDetails(int rentalNo) {
		Dto_Review dtoReviewDetail = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			
			String query1 = "SELECT s.title, r.reviewContent, r.reviewScore, r.reviewUpdateDate, r.filePath ";
			String query2 = "FROM rental r, share s WHERE r.no = ? AND r.place_no = s.place_no ";
			
			preparedStatement = connection.prepareStatement(query1+query2);
			preparedStatement.setInt(1, rentalNo);

			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				
				String reviewPlaceName = resultSet.getString("s.title");
				String reviewContent = resultSet.getString("r.reviewContent");
				int reviewScore = resultSet.getInt("r.reviewScore");
				Date reviewUpdateDate = resultSet.getDate("r.reviewUpdateDate");
				String reviewFilePath = resultSet.getString("r.filePath");
				
				// reviewFilePath가 null이 아닐때
				FilePath cv = new FilePath();
				if(reviewFilePath != null) {
					reviewFilePath =  reviewFilePath.substring(cv.reviewPhoto.length());
				}
				
				dtoReviewDetail = new Dto_Review(reviewContent, reviewScore, reviewUpdateDate, reviewFilePath, reviewPlaceName);

			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dtoReviewDetail;
	}
	
	
	// review modify
	public void modifyReview(int rentalNo, String reviewContent, String reviewFilePath, int reviewScore) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			
			String query1 = "UPDATE rental SET reviewContent = ?, reviewScore = ?, filePath = ?, ";
			String query2 = "reviewUpdateDate = now() WHERE no = ? ";
			
			preparedStatement = connection.prepareStatement(query1+query2);
			preparedStatement.setString(1, reviewContent);
			preparedStatement.setInt(2, reviewScore);
			preparedStatement.setString(3, reviewFilePath);
			preparedStatement.setInt(4, rentalNo);
			
			preparedStatement.executeUpdate();
			
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	} // End modify Review **********
	
	
	// delete review
	public void deleteReview(int rentalNo) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "UPDATE rental SET reviewSubmitDate = null, reviewRemoveDate = now() WHERE no = ? ";
			
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, rentalNo);
			
			preparedStatement.executeUpdate();
			
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	} // End delete Review **********
	
	
	// myinfo - 내가 작성한 Review List 보여주기
	public ArrayList<Dto_Review> myinfoReviewList(String user_id, int requestPage, int numOfTuplePerPage){
		ArrayList<Dto_Review> dtoMyinfoReview = new ArrayList<Dto_Review>();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			
			// LIMIT {OFFSET}, {LIMIT} -> 쿼리결과중 offset번째부터 limit개의 튜플을 출력
			

			String query1 = "SELECT r.no, s.title, r.reviewContent, r.reviewScore, r.reviewUpdateDate, r.filePath ";
			String query2 = "FROM rental r, share s ";
			String query3 = "WHERE r.user_id = ? AND r.place_no = s.place_no AND reviewSubmitDate is not null ";
			String query4 = "ORDER BY r.reviewSubmitDate DESC LIMIT ?, ?";
			
			// page는 1부터 시작하지만, offset은 0부터 시작.(0~9(10개), 10~19(10개)와같이 offset을 설정해야 하기 때문)
			int offset = requestPage - 1;
			
			preparedStatement = connection.prepareStatement(query1+query2+query3+query4);
			
			// place_no -> 추후 세션으로 받아와서 값 집어 넣을 것
			preparedStatement.setString(1, user_id);
			
			
			// 0을 나누면 에러가 발생하므로 예외처리
			if(offset == 0) {
				preparedStatement.setInt(2, offset);
			}else {
				preparedStatement.setInt(2, offset * numOfTuplePerPage);
			}
			preparedStatement.setInt(3, numOfTuplePerPage);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				int rentalNo = resultSet.getInt("r.no");
				String reviewPlaceName = resultSet.getString("s.title");
				String reviewContent = resultSet.getString("r.reviewContent");
				int reviewScore = resultSet.getInt("r.reviewScore");
				Date reviewUpdateDate = resultSet.getDate("r.reviewUpdateDate");
				String reviewFilePath = resultSet.getString("r.filePath");
				
				
				// filePath
				FilePath cv = new FilePath();
				
				// reviewFilePath가 null이 아닐때
				if(reviewFilePath != null) {
					reviewFilePath =  reviewFilePath.substring(cv.reviewPhoto.length());
				}
				
				Dto_Review myinfoReviewDto = new Dto_Review(rentalNo, reviewContent, reviewScore, reviewUpdateDate, reviewFilePath, reviewPlaceName);
				dtoMyinfoReview.add(myinfoReviewDto);
			
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return dtoMyinfoReview; 
	}// end myinfoReviewList *******
	
	
	public int countTuple(String user_id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		int count = 0;
		String query = "SELECT count(reviewContent) FROM rental WHERE user_id = ? AND reviewSubmitDate is not null";
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, user_id);
			resultSet = preparedStatement.executeQuery();

			if(resultSet.next()) {
				count = resultSet.getInt(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	
}//end line