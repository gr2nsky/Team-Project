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

import com.ysms.common.FilePath;
import com.ysms.dto.Dto_QnA;
	
	/* 
	 	-----------------------------
	 	21.05.16 hyokyeong JO
	 	DB table qna_review
	 	-----------------------------
	 */
public class Dao_Host_QnA {
	DataSource dataSource;
	
	
	// Context - DB연결
	public Dao_Host_QnA() {		
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/team4");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 각 공간에 대한 QnA List 보여주기
	public ArrayList<Dto_QnA> hostQnaList(int place_no, int requestPage, int numOfTuplePerPage){
		ArrayList<Dto_QnA> hostDtoQnA = new ArrayList<Dto_QnA>();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			
			// LIMIT {OFFSET}, {LIMIT} -> 쿼리결과중 offset번째부터 limit개의 튜플을 출력
			
			/* select : q.no, q.content, q.updateDate, q.sender,
			 * q.answer, q.a_updateDate, q.a_removeDate, q.place_no, s.title
				where : q.place_no
			 */
			String query1 = "SELECT q.no, q.content, q.updateDate, q.sender, q.answer, q.a_updateDate, q.a_removeDate, q.place_no, s.title ";
			String query2 = "FROM qna_review q, user u, share s ";
			String query3 = "WHERE q.place_no = ? AND u.id = q.sender AND q.place_no = s.place_no AND q.removeDate is null AND q.score is null ";
			String query4 = "ORDER BY q.no DESC LIMIT ?, ?";
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
			
			/* select : q.no, q.content, q.updateDate, q.sender,
			 * q.answer, q.a_updateDate, q.a_removeDate, q.place_no, s.title
				where : q.place_no
			 */
			while(resultSet.next()) {
				
				int qnaNo = resultSet.getInt("q.no");
				String qnaContent = resultSet.getString("q.content");
				Timestamp tsQ_updateDate = resultSet.getTimestamp("q.updateDate");
				String qnaSender = resultSet.getString("q.sender");
				String qnaAnswer = resultSet.getString("q.answer");
				Timestamp tsA_updateDate = resultSet.getTimestamp("q.a_updateDate");
				Timestamp tsA_removeDate = resultSet.getTimestamp("q.a_removeDate");
				int qnaPlace_no = resultSet.getInt("q.place_no");
				String qnaPlaceName = resultSet.getString("s.title"); 
				
				
				// updateDate 형식 yyyy-MM-dd로 바꾸기
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String qnaQ_updateDate = sdf.format(tsQ_updateDate);
				
				String qnaA_updateDate = "";
				String qnaA_removeDate = "";
				String qnaYesNo = "";
				
				System.out.println("update time(if before) : " + tsA_updateDate);
				// host answer != null
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
				
				System.out.println("update time (else after) : " + tsA_updateDate);
				Dto_QnA hostQnADto = new Dto_QnA(qnaNo, qnaContent, qnaQ_updateDate, qnaSender, qnaAnswer, qnaA_removeDate, qnaPlaceName, qnaYesNo, qnaA_updateDate, qnaPlace_no);
				hostDtoQnA.add(hostQnADto);
			
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
		
		return hostDtoQnA; 
	}
	
	public int countTuple(int place_no) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		int count = 0;
		String query = "SELECT COUNT(*) FROM qna_review WHERE place_no = ? AND score is null AND removeDate is null";
		
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
	
	
	// modify qna answer - host
	public void hostModifyQnA(int qna_no, String qna_answer) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "UPDATE qna_review SET answer = ?, a_updateDate = now(), a_removeDate = null WHERE no = ? ";
			
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, qna_answer);
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
	
	
	// delete host answer
	public void hostDeleteQnA(int qna_no) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = dataSource.getConnection();

			String query = "UPDATE qna_review SET a_submitDate = null, a_removeDate = now() WHERE no = ? ";

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
		
		
		// modify qna answer - host
		public void hostWriteQnA(int qna_no, String qna_answer) {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			try {
				connection = dataSource.getConnection();
				
				String query = "UPDATE qna_review SET answer = ?, a_submitDate = now(), a_updateDate = now(), a_removeDate = null WHERE no = ? ";
	
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, qna_answer);
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
	
		// 공간 이름 불러오기
		public String shareTitle(int place_no) {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;

			String qnaPlaceName =  "";
			String query = "select title from share where place_no = ? ";

			try {
				connection = dataSource.getConnection();
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, place_no);

				resultSet = preparedStatement.executeQuery();



				if(resultSet.next()) {
					qnaPlaceName = resultSet.getString(1);
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
			return qnaPlaceName;
		}
	
	
}//end line

