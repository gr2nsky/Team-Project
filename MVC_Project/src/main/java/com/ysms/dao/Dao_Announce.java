package com.ysms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.ysms.dto.Dto_Announce;

public class Dao_Announce {
		
		DataSource dataSource;
		
		public Dao_Announce() {
			try {
				Context context = new InitialContext();
				dataSource = (DataSource) context.lookup("java:comp/env/jdbc/team4");
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

		// 공지사항 List 보이기
		public ArrayList<Dto_Announce> announceList(int requestPage, int numOfTuplePerPage, String search_title, String search_content){
			ArrayList<Dto_Announce> dtos = new ArrayList<Dto_Announce>();
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null; // select할 때만 씀
			
			try {
				connection = dataSource.getConnection();
				
			//	String query = "select * from announce WHERE removeDate is null ORDER BY no DESC LIMIT ?, ?";
				String query = "select * from announce where title like '%"+search_title+"%' and content like '%"
								+search_content+"%' and removeDate is null order by no desc LIMIT ?, ?";
				
				// page는 1부터 시작하지만, offset은 0부터 시작.(0~9(10개), 10~19(10개)와같이 offset을 설정해야 하기 때문)
				int offset = requestPage - 1;
				
				preparedStatement = connection.prepareStatement(query);

				//preparedStatement.setString(1, search_title);
				//preparedStatement.setString(2, search_content);
				
				// 0을 나누면 에러가 발생하므로 예외처리
				if(offset == 0) {
					preparedStatement.setInt(1, offset);
				}else {
					preparedStatement.setInt(1, offset * numOfTuplePerPage);
				}
				preparedStatement.setInt(2, numOfTuplePerPage);
				
				resultSet = preparedStatement.executeQuery();
				while(resultSet.next()) {
					int no = resultSet.getInt("no"); 
					String title = resultSet.getString("title");
					String content = resultSet.getString("content");
					String createDate = resultSet.getString("createDate");
					String updateDate = resultSet.getString("updateDate");
					String removeDate = resultSet.getString("removeDate");
					String user_id = resultSet.getString("user_id");
					search_title = resultSet.getString("title");
					search_content = resultSet.getString("content");
					
//					System.out.println(creationDate);
					
					Dto_Announce dto = new Dto_Announce(no, title, content, createDate, updateDate, removeDate, user_id); // BDto - constructor와 순서 맞춰줘야 함
					dtos.add(dto);
					
//					System.out.println("announce");
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(resultSet != null) resultSet.close();
					if(preparedStatement != null) preparedStatement.close();
					if(connection != null) connection.close();
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			return dtos;
		}
			
		// 공지사항 쓰기
		public void announceWrite(String title, String content, String user_id) {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			try {
				connection = dataSource.getConnection();
				
				String query = "insert into announce (title, content, createDate, user_id) values (?,?, now(), ?)";
				preparedStatement = connection.prepareStatement(query);
				
				preparedStatement.setString(1, title);
				preparedStatement.setString(2, content);
				preparedStatement.setString(3, user_id);
				
				preparedStatement.executeUpdate();
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(preparedStatement != null) preparedStatement.close();
					if(connection != null) connection.close();
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}
		
		// 공지사항 내용 보이기
		public Dto_Announce announceContent_view(String str_no) {
			Dto_Announce dto = null;
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null; // select할 때만 씀
			
			try {
				connection = dataSource.getConnection();
				
				String query = "select * from announce where no = ? ";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, Integer.parseInt(str_no));
				resultSet = preparedStatement.executeQuery();
				
				if(resultSet.next()) {
					int no = resultSet.getInt("no"); 
					String title = resultSet.getString("title");
					String content = resultSet.getString("content");
					String createDate = resultSet.getString("createDate");
					String updateDate = resultSet.getString("updateDate");
					String removeDate = resultSet.getString("removeDate");
					String user_id = resultSet.getString("user_id");
					
					dto = new Dto_Announce(no, title, content, createDate, updateDate, removeDate, user_id);
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(resultSet != null) resultSet.close();
					if(preparedStatement != null) preparedStatement.close();
					if(connection != null) connection.close();
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			return dto;
			
		}
		
		// 공지사항 삭제
		public void announceDelete(String str_no) {
			int no = Integer.parseInt(str_no);
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			try {
				connection = dataSource.getConnection();
				
				String query = "UPDATE announce SET removeDate = now() WHERE no = ?";
				preparedStatement = connection.prepareStatement(query);
				
				preparedStatement.setInt(1, no);
				
				preparedStatement.executeUpdate();
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(preparedStatement != null) preparedStatement.close();
					if(connection != null) connection.close();
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}
		
		// 공지사항 수정
		public void announceModify(String str_no, String title, String content) {
			int no = Integer.parseInt(str_no);
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			try {
				connection = dataSource.getConnection();
				
				String query = "UPDATE announce SET title = ?, content = ?, updateDate = now() WHERE no = ?";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, title);
				preparedStatement.setString(2, content);
				preparedStatement.setInt(3, no);
				
				preparedStatement.executeUpdate();
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(preparedStatement != null) preparedStatement.close();
					if(connection != null) connection.close();
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}
		
		// 공지사항 페이지 분할
		public int countTuple(String search_title, String search_content) {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			
			
			
			int count = 0;
			String query = "SELECT COUNT(*) FROM announce WHERE title like '%"+search_title+"%' and content like '%"+search_content+"%' and removeDate is null";
			
			
			try {
				connection = dataSource.getConnection();
				preparedStatement = connection.prepareStatement(query);

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
		
		

}
