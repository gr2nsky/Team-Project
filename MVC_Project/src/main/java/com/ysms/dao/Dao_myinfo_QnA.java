/* 
	 	-----------------------------
	 	21.05.17 hyokyeong JO
	 	DB table qna_review
	 	-----------------------------
 */
package com.ysms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.ysms.dto.Dto_QnA;

public class Dao_myinfo_QnA {

	

	DataSource dataSource;
	
	
	// Context - DB연결
	public Dao_myinfo_QnA() {		
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
	
	// user가 문의한 QnA List 보여주기
	public ArrayList<Dto_QnA> myInfoQnAList(String user_id, int requestPage, int numOfTuplePerPage){
		ArrayList<Dto_QnA> myInfoDtoQnA = new ArrayList<Dto_QnA>();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			
			// LIMIT {OFFSET}, {LIMIT} -> 쿼리결과중 offset번째부터 limit개의 튜플을 출력
			
			/*
			 * select : q.no, s.title, q.content, q.updateDate, q.answer, q.a_updateDate, q.a_removeDate, q.place_no
			 * where = q.sender (user id)
			 */
			String query1 = "SELECT q.no, s.title, q.content, q.updateDate, q.answer, q.a_updateDate, q.a_removeDate, q.place_no ";
			String query2 = "FROM qna_review q, share s ";
			String query3 = "WHERE q.sender = ? AND q.place_no = s.place_no AND q.removeDate is null AND q.score is null  ";
			String query4 = "ORDER BY q.no DESC LIMIT ?, ?";
			
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
			
			// select : q.no, s.title, q.content, q.updateDate, q.answer, q.a_updateDate, q.a_removeDate, q.place_no
			while(resultSet.next()) {
				int qnaNo = resultSet.getInt("q.no");
				String qnaPlaceName = resultSet.getString("s.title");
				String qnaContent = resultSet.getString("q.content");
				Timestamp tsQ_updateDate = resultSet.getTimestamp("q.updateDate");
				String qnaAnswer = resultSet.getString("q.answer");
				Timestamp tsA_updateDate = resultSet.getTimestamp("q.a_updateDate");
				Timestamp tsA_removeDate = resultSet.getTimestamp("q.a_removeDate");
				int qnaPlace_no = resultSet.getInt("q.place_no");
				
				// updateDate 형식 yyyy-MM-dd로 바꾸기
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String qnaQ_updateDate = sdf.format(tsQ_updateDate);
				String qnaA_updateDate = "";
				String qnaA_removeDate = "";
				

				// host answer != null -> yesNo : Y
				String qnaYesNo = "";
				
				if(qnaAnswer == null) {
					qnaAnswer = "호스트가 아직 답변을 등록하지 않았습니다.";
					qnaYesNo = "N";
				}else if(qnaAnswer != null && tsA_removeDate != null) {
					qnaAnswer = "호스트가 아직 답변을 등록하지 않았습니다.";
					qnaYesNo = "N";
				}else {
					qnaA_updateDate = sdf.format(tsA_updateDate);
					qnaYesNo = "Y";
				}

				
				Dto_QnA myInfoQnaDto = new Dto_QnA(qnaNo, qnaContent, qnaQ_updateDate, qnaAnswer, qnaA_removeDate, qnaPlaceName, qnaYesNo, qnaA_updateDate);
				myInfoDtoQnA.add(myInfoQnaDto);
			
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
		
		return myInfoDtoQnA; 
	}
	
	public int countTuple(String user_id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		int count = 0;
		String query = "SELECT COUNT(*) FROM qna_review WHERE sender = ? AND score is null AND removeDate is null";
		
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
	
	
	// delete my qna - user
	public void deleteQnA(int qna_no) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "UPDATE qna_review SET removeDate = now() WHERE no = ? ";
			
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, qna_no);
			
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
		
	}
	
	
	// qna detailView (my info and host)
	public Dto_QnA detailViewQnA(int qna_no) {
		Dto_QnA qnaDto = null;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "SELECT no, content, answer FROM qna_review WHERE no = ? ";
			
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, qna_no);
			
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				int qnaNo = resultSet.getInt("no");
				String qnaContent = resultSet.getString("content");
				String qnaAnswer = resultSet.getString("answer");
				
				qnaDto = new Dto_QnA(qnaNo, qnaContent, qnaAnswer);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return qnaDto;
	}
	
	// modify my qna - user
		public void modifyQnA(int qna_no, String qna_content) {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			try {
				connection = dataSource.getConnection();
				
				String query = "UPDATE qna_review SET content = ?, updateDate = now() WHERE no = ? ";
				
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, qna_content);
				preparedStatement.setInt(2, qna_no);
				
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
			
		}
	

	
	
}// end line
