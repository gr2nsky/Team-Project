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
public class Dao_QnA {
	DataSource dataSource;
	
	
	// Context - DB연결
	public Dao_QnA() {		
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
	
	// 각 공간에 대한 QnA List 보여주기
	public ArrayList<Dto_QnA> qnaList(int place_no, int requestPage, int numOfTuplePerPage){
		System.out.println("* * Start Method : qnaList * *");
		
		ArrayList<Dto_QnA> dtoQnA = new ArrayList<Dto_QnA>();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			
			// LIMIT {OFFSET}, {LIMIT} -> 쿼리결과중 offset번째부터 limit개의 튜플을 출력
			
			/* select : q.content, q.updateDate, q.sender, q.answer, q.a_updateDate, q.a_removeDate
						q.place_no, u.filePath, s.title
				where : q.place_no    // -> 얘로 수정 ? s.no
			 */
			System.out.println("  -  Query Start : qnaList * *");
			
			String query1 = "SELECT q.content, q.updateDate, q.sender, q.answer, q.a_updateDate, q.a_removeDate, q.place_no, u.filePath, s.title, s.user_id ";
			String query2 = "FROM qna_review q, user u, share s ";
			String query3 = "WHERE q.place_no = ? AND u.id = q.sender AND q.place_no = s.place_no AND q.removeDate is null AND q.score is null ";
			String query4 = "ORDER BY q.no DESC LIMIT ?, ?";
			// page는 1부터 시작하지만, offset은 0부터 시작.(0~9(10개), 10~19(10개)와같이 offset을 설정해야 하기 때문)
			int offset = requestPage - 1;
			
			preparedStatement = connection.prepareStatement(query1+query2+query3+query4);
			
			// place_no -> 추후 세션으로 받아와서 값 집어 넣을 것
			preparedStatement.setInt(1, place_no);
			
			System.out.println("Insert place_no to Query : " + place_no);
			
			// 0을 나누면 에러가 발생하므로 예외처리
			if(offset == 0) {
				preparedStatement.setInt(2, offset);
			}else {
				preparedStatement.setInt(2, offset * numOfTuplePerPage);
			}
			preparedStatement.setInt(3, numOfTuplePerPage);
			
			resultSet = preparedStatement.executeQuery();
			
			/* select : q.content, q.updateDate, q.sender, q.answer, q.a_updateDate, q.a_removeDate
						q.place_no, u.filePath, s.title
			*/
			while(resultSet.next()) {
				String qnaContent = resultSet.getString("q.content");
				Timestamp tsQ_updateDate = resultSet.getTimestamp("q.updateDate");
				String qnaSender = resultSet.getString("q.sender");
				String qnaTarget = resultSet.getString("s.user_id");
				String qnaAnswer = resultSet.getString("q.answer");
				Timestamp tsA_updateDate = resultSet.getTimestamp("q.a_updateDate");
				Timestamp tsA_removeDate = resultSet.getTimestamp("q.a_removeDate");
				int qnaPlace_no = resultSet.getInt("q.place_no");
				String st_qnaUserFilePath = resultSet.getString("u.filePath");
				String qnaPlaceName = resultSet.getString("s.title"); 
				
				// updateDate 형식 yyyy-MM-dd HH:mm:ss로 바꾸기
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String qnaQ_updateDate = sdf.format(tsQ_updateDate);
				
				String qnaA_updateDate = "";
				String qnaA_removeDate = "";
				String qnaYesNo = "";
				
				// host answer != null
				if(qnaAnswer == null) {
					qnaAnswer = "호스트가 아직 답변을 등록하지 않았습니다.";
				}else if(qnaAnswer != null && tsA_removeDate != null) {
					qnaAnswer = "호스트가 아직 답변을 등록하지 않았습니다.";
				}else {
					qnaA_updateDate = sdf.format(tsA_updateDate);
				}
				
				
				// filePath
				FilePath cv = new FilePath();
				String qnaUserFilePath = "";
				
				// u.filepath가 null일때 기본이미지 띄우기
				if(st_qnaUserFilePath == null) {
					qnaUserFilePath = "basicPhoto.png";
				}else {
					qnaUserFilePath =  st_qnaUserFilePath.substring(cv.ysms.length());
				}
				
				
				Dto_QnA qnaDto = new Dto_QnA(qnaContent, qnaQ_updateDate, qnaSender, qnaAnswer, qnaA_updateDate, qnaA_removeDate, qnaPlace_no, qnaUserFilePath, qnaPlaceName, qnaTarget);
				
				dtoQnA.add(qnaDto);
				
				System.out.println(qnaUserFilePath);
			
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
		
		return dtoQnA; 
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
	
	// qna 작성하기
	public void writeQnA(String content, String user_id, int place_no) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "INSERT INTO qna_review (content, submitDate, updateDate, sender, place_no, target) VALUES (?, now(), now(), ?, ?, (SELECT user_id FROM share WHERE place_no = ?))";
			
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, content);
			preparedStatement.setString(2, user_id);
			preparedStatement.setInt(3, place_no);
			preparedStatement.setInt(4, place_no);
			
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
	
	public String shareUserId(int place_no) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		String target =  "";
		String query = "select user_id from share where place_no = ? ";

		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, place_no);

			resultSet = preparedStatement.executeQuery();

			if(resultSet.next()) {
				target = resultSet.getString(1);
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
		return target;
	}

	
	
}//end line
