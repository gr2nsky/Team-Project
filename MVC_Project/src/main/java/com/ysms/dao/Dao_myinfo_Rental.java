/*
 * 21.05.22 hyokyoneg JO
 * my info Rental List (scheduled and previous)
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
import com.ysms.dto.Dto_Rental;

public class Dao_myinfo_Rental {
	
	DataSource dataSource;

	// Context - DB연결
	public Dao_myinfo_Rental() {		
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/team4");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	// user의 rental list(예정된 예약)
	public ArrayList<Dto_Rental> myinfoRentalScheduledList(String user_id, int requestPage, int numOfTuplePerPage){
		ArrayList<Dto_Rental> myinfoDtoRentalScheduled = new ArrayList<Dto_Rental>();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();			

			String query1 = "SELECT r.no, r.rentalDate, r.checkInDate, r.startTime, r.endTime, r.cancellationDate, r.price, r.user_id, r.place_no, s.filePath, s.title ";
			String query2 = "FROM rental r, share s ";
			String query3 = "WHERE r.user_id = ? AND r.place_no = s.place_no AND r.checkInDate >= now() AND r.cancellationDate is null  ";
			String query4 = "ORDER BY r.checkInDate LIMIT ?, ?";
			
			
			int offset = requestPage - 1;
			
			preparedStatement = connection.prepareStatement(query1+query2+query3+query4);
			preparedStatement.setString(1, user_id);
			
			
			// 0을 나누면 에러가 발생하므로 예외처리
			if(offset == 0) {
				preparedStatement.setInt(2, offset);
			}else {
				preparedStatement.setInt(2, offset * numOfTuplePerPage);
			}
			preparedStatement.setInt(3, numOfTuplePerPage);

			resultSet = preparedStatement.executeQuery();
			
//			SELECT r.no, r.rentalDate, r.checkInDate, r.startTime, r.endTime, r.cancellationDate, 
//			r.price, r.user_id, r.place_no, s.filePath, s.title
			while(resultSet.next()) {
				int rentalNo = resultSet.getInt("r.no");
				Timestamp tsRentalDate = resultSet.getTimestamp("r.rentalDate");
				Timestamp tscheckInDate = resultSet.getTimestamp("r.checkInDate");
				int rentalStartTime = resultSet.getInt("r.startTime");
				int rentalEndTime = resultSet.getInt("r.endTime");
				Timestamp tsRentalCancellationDate = resultSet.getTimestamp("r.cancellationDate");
				int rentalPrice = resultSet.getInt("r.price");
				String rentalUser_id = resultSet.getString("r.user_id");
				int rentalPlace_no = resultSet.getInt("r.place_no");
				String stRentalPhoto = resultSet.getString("s.filePath");
				String rentalTitle = resultSet.getString("s.title");
				
				
				// updateDate 형식 yyyy-MM-dd로 바꾸기
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String rentalDate = sdf.format(tsRentalDate);
				String checkInDate = sdf.format(tscheckInDate);
				String rentalCancellationDate = "";

				FilePath cv = new FilePath();
				String rentalPhoto = "";
				
				
				// share filepath가 null일때 기본이미지 띄우기
				if(stRentalPhoto == null) {
					rentalPhoto = "basicPhoto.png";
				}else {
					rentalPhoto =  stRentalPhoto.substring(cv.Image.length());
				}
				
				// totalPrice 계산해서 저장
				rentalPrice = (rentalEndTime - rentalStartTime) * rentalPrice;

				Dto_Rental myinfoRentalScheduledDto = new Dto_Rental(rentalNo, rentalDate, checkInDate, rentalStartTime, rentalEndTime, rentalCancellationDate, rentalPrice, rentalUser_id, rentalPlace_no, rentalPhoto, rentalTitle);
				myinfoDtoRentalScheduled.add(myinfoRentalScheduledDto);
			
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
		
		return myinfoDtoRentalScheduled; 
	}
	
	public int scheduledCountTuple(String user_id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		int count = 0;
		String query = "SELECT count(no) FROM rental WHERE user_id = ? AND checkInDate >= now() AND cancellationDate is null";
		
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

	// user의 rental list(이전 예약)
	public ArrayList<Dto_Rental> myinfoRentalPreviousList(String user_id, int requestPage, int numOfTuplePerPage){
		ArrayList<Dto_Rental> myinfoDtoRentalPrevious = new ArrayList<Dto_Rental>();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();			

//			SELECT r.no, r.rentalDate, r.checkInDate, r.startTime, r.endTime, r.cancellationDate, 
//			r.price, r.user_id, r.place_no, s.filePath, s.title 
//			FROM rental r, share s 
//			WHERE r.user_id = ? AND r.place_no = s.place_no AND checkInDate < now() 
//			ORDER BY checkInDate DESC;
			String query1 = "SELECT r.no, r.rentalDate, r.checkInDate, r.startTime, r.endTime, r.cancellationDate, r.price, r.user_id, r.place_no, s.filePath, s.title, r.reviewSubmitDate, r.reviewRemoveDate ";
			String query2 = "FROM rental r, share s ";
			String query3 = "WHERE r.user_id = ? AND r.place_no = s.place_no AND (checkInDate < now() OR cancellationDate is not null) ";
			String query4 = "ORDER BY IF(cancellationDate is null, checkInDate, cancellationDate) DESC LIMIT ?, ?";
			
			
			int offset = requestPage - 1;
			
			preparedStatement = connection.prepareStatement(query1+query2+query3+query4);
			preparedStatement.setString(1, user_id);
			
			
			// 0을 나누면 에러가 발생하므로 예외처리
			if(offset == 0) {
				preparedStatement.setInt(2, offset);
			}else {
				preparedStatement.setInt(2, offset * numOfTuplePerPage);
			}
			preparedStatement.setInt(3, numOfTuplePerPage);

			resultSet = preparedStatement.executeQuery();
			
//				SELECT r.no, r.rentalDate, r.checkInDate, r.startTime, r.endTime, r.cancellationDate, 
//				r.price, r.user_id, r.place_no, s.filePath, s.title
			while(resultSet.next()) {
				int rentalNo = resultSet.getInt("r.no");
				Timestamp tsRentalDate = resultSet.getTimestamp("r.rentalDate");
				Timestamp tscheckInDate = resultSet.getTimestamp("r.checkInDate");
				int rentalStartTime = resultSet.getInt("r.startTime");
				int rentalEndTime = resultSet.getInt("r.endTime");
				Timestamp tsRentalCancellationDate = resultSet.getTimestamp("r.cancellationDate");
				int rentalPrice = resultSet.getInt("r.price");
				String rentalUser_id = resultSet.getString("r.user_id");
				int rentalPlace_no = resultSet.getInt("r.place_no");
				String stRentalPhoto = resultSet.getString("s.filePath");
				String rentalTitle = resultSet.getString("s.title");
				Date reviewSubmitDate = resultSet.getDate("r.reviewSubmitDate");
				Date reviewRemoveDate = resultSet.getDate("r.reviewRemoveDate");
				
				
				// updateDate 형식 yyyy-MM-dd로 바꾸기
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String rentalDate = sdf.format(tsRentalDate);
				String checkInDate = sdf.format(tscheckInDate);
				String rentalCancellationDate = "";
				
				// cancellationDate null 인지 아닌지 확인
				if(tsRentalCancellationDate != null) {
					rentalCancellationDate = sdf.format(tsRentalCancellationDate);
				}
				
				System.out.println(rentalCancellationDate);

				FilePath cv = new FilePath();
				String rentalPhoto = "";
				
				
				// share filepath가 null일때 기본이미지 띄우기
				if(stRentalPhoto == null) {
					rentalPhoto = "basicPhoto.png";
				}else {
					rentalPhoto =  stRentalPhoto.substring(cv.Image.length());
				}
				
				// totalPrice 계산해서 저장
				rentalPrice = (rentalEndTime - rentalStartTime) * rentalPrice;

				Dto_Rental myinfoRentalPreviousDto = new Dto_Rental(rentalNo, rentalDate, checkInDate, rentalStartTime, rentalEndTime, rentalCancellationDate, rentalPrice, rentalUser_id, rentalPlace_no, rentalPhoto, rentalTitle, reviewSubmitDate, reviewRemoveDate);
				myinfoDtoRentalPrevious.add(myinfoRentalPreviousDto);
			
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
		
		return myinfoDtoRentalPrevious; 
	}
	
	public int previousCountTuple(String user_id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		int count = 0;
		String query = "SELECT count(no) FROM rental WHERE user_id = ? AND (checkInDate < now() OR cancellationDate is not null) ";
		
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