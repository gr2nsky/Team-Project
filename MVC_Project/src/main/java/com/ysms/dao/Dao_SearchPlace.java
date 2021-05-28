package com.ysms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.ysms.dto.Dto_SearchPlace;



public class Dao_SearchPlace {
	
	DataSource dataSource; //javax.sql
//	int count = 0; //검색된 행 갯수

	
	public Dao_SearchPlace() {
		try {
			Context context = new InitialContext();// javax.naming , context.xml과 연결
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/team4"); // context.xml의 내용을 불러옴
			
		}catch(Exception e){
			e.printStackTrace(); // error code 출력
		}
	}
	
	
	//검색 결과 목록 출력
	public ArrayList<Dto_SearchPlace> SearchLocation(String inputCategory, String inputLocation, String inputDate, int requestPage, int numOfTuplesPerPage){ // 검색
		ArrayList<Dto_SearchPlace> dtos = new ArrayList<Dto_SearchPlace>();
		Connection connection = null;// java.sql
		PreparedStatement preparedStatement = null; // java.sql
		ResultSet resultSet = null; // java.sql	
		
		String useCategory = inputCategory;
		String useDate = inputDate;
		
		System.out.println("<<<SearchLocation>>>");
		System.out.println("inputed data : " + inputCategory + ", " + inputLocation + ", " + requestPage + ", " + numOfTuplesPerPage);
		
		try {
			connection = dataSource.getConnection(); // 연결
			
			
			String search1 = "SELECT DISTINCT s.no, s.filePath, p.category, s.title, p.address1, p.address2, s.price, s.daylimit ";
			String search2 = "FROM share as s, place as p, qna_review as q ";
			String search3 = "WHERE p.no = s.place_no and p.removeDate is null ";
			String search4 = "";
			if(useCategory.equals("0")) {
				useCategory = "is not null"; // null값이 아닌 값을 전부
				search4 = "and p.address1 like '%" + inputLocation + "%' and p.category " + useCategory +" limit ?, ?";
						
			} else {
				search4 = "and p.address1 like '%" + inputLocation + "%' and p.category = '" + useCategory +"' limit ?, ?";
			}
			System.out.println("useCategory : " + useCategory);
			String search = search1 + search2 + search3 + search4;
			
			int offset = requestPage-1;
			System.out.println(requestPage + "페이지 출력(dao)");
			
			preparedStatement = connection.prepareStatement(search); // 쿼리문 연결
			
			// 0을 나누면 에러가 발생하므로 예외처리
			if(offset == 0) {
				preparedStatement.setInt(1, offset);
			}else {
				preparedStatement.setInt(1, offset * numOfTuplesPerPage);
			}
			preparedStatement.setInt(2, numOfTuplesPerPage);
			
			resultSet = preparedStatement.executeQuery(); // 쿼리문 실행
			
			while(resultSet.next()) {
				
				String filePath = resultSet.getString("s.filePath"); //이미지파일 불러오기
				String category = resultSet.getString("p.category");//category 숫자값 -> 문자로 변환
				
				switch(category) {
				case "1" :
					category = "휴식";
					break;
				case "2" :
					category = "파티";
					break;
				case "3" : 
					category = "공부";
					break;
				case "4" :
					category = "회의";
					break;
				default : 
					break;
				
				}
				int no = resultSet.getInt("s.no");
				String title = resultSet.getString("s.title");
				String address1 = resultSet.getString("p.address1");
				String address2 = resultSet.getString("p.address2");
				int price = resultSet.getInt("s.price");
				String dayLimit = resultSet.getString("s.dayLimit");
				
				
				
				Dto_SearchPlace dto = new Dto_SearchPlace(no, filePath, category, title, address1,address2, price, dayLimit); // ArrayList 생성
				System.out.println("dto : "  + filePath  + ", "+ category + ", " +title + ", " + address1 + ", " + address2 + "," + price + "," + dayLimit);
				dtos.add(dto); // ArrayList에 저장(메모리)
				
			}
			System.out.println("success search");
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(resultSet!=null) resultSet.close(); // 실행이 잘되었다면, 실행종료
				if(preparedStatement!=null) preparedStatement.close(); // 실행이 잘되었다면, 실행종료
				if(connection != null) connection.close(); //실행이 잘되었다면, 실행종료
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return dtos;
		
	}
	
	//검색된 행 갯수 세기
	public int countTuple(String inputCategory, String inputLocation, String inputDate) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String useCategory = inputCategory;
		String useDate = inputDate;
		int count = 0;
		
		String query = "SELECT DISTINCT count(s.title) FROM share as s, place as p "
				+ "	WHERE p.no = s.place_no and p.removeDate is null and p.address1 LIKE '%" + inputLocation + "%' AND p.category ";
		
		//카테고리가 숫자이면 category = 가 되야하지만
		//카테고리가 is not null 이면 category is not null이 되므로 분기가 되야 
		if(useCategory.equals("0")) {
			useCategory = "is not null";
			query = "SELECT DISTINCT count(s.title) FROM share as s, place as p "
					+ "	WHERE p.no = s.place_no and p.removeDate is null and p.address1 LIKE '%" + inputLocation + "%' AND p.category " + useCategory;
		} else {
			query = "SELECT DISTINCT count(s.title) FROM share as s, place as p "
					+ "	WHERE p.no = s.place_no and p.removeDate is null and p.address1 LIKE '%" + inputLocation + "%' AND p.category = " + useCategory;
		}
		
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				count = resultSet.getInt(1);
			}
			
			System.out.println("dao.countTuple 실행");
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("dao.countTuple 실행실패");
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
	 * 21.05.27 hyokyeong
	 * 공간 목록 클릭시 공간 리스트 전부 띄우기 
	 */
	public ArrayList<Dto_SearchPlace> shareList(int requestPage, int numOfTuplesPerPage){ // 검색
		ArrayList<Dto_SearchPlace> dtos = new ArrayList<Dto_SearchPlace>();
		Connection connection = null;// java.sql
		PreparedStatement preparedStatement = null; // java.sql
		ResultSet resultSet = null; // java.sql	
		
		try {
			connection = dataSource.getConnection(); // 연결
			
			String query1 = "SELECT DISTINCT s.no, s.filePath, p.category, s.title, p.address1, p.address2, s.price ";
			String query2 = "FROM share s, place  p ";
			String query3 = "WHERE p.no = s.place_no and p.removeDate is null ";
			String query4 = "ORDER BY s.no DESC LIMIT ?, ? ";
			
			int offset = requestPage-1;
			
			preparedStatement = connection.prepareStatement(query1+query2+query3+query4); // 쿼리문 연결
			
			// 0을 나누면 에러가 발생하므로 예외처리
			if(offset == 0) {
				preparedStatement.setInt(1, offset);
			}else {
				preparedStatement.setInt(1, offset * numOfTuplesPerPage);
			}
			preparedStatement.setInt(2, numOfTuplesPerPage);
			
			resultSet = preparedStatement.executeQuery(); // 쿼리문 실행
			
			while(resultSet.next()) {
				
				String filePath = resultSet.getString("s.filePath"); //이미지파일 불러오기
				String category = resultSet.getString("p.category");//category 숫자값 -> 문자로 변환
				int no = resultSet.getInt("s.no");
				String title = resultSet.getString("s.title");
				String address1 = resultSet.getString("p.address1");
				String address2 = resultSet.getString("p.address2");
				int price = resultSet.getInt("s.price");
				
				// category -----
				switch(category) {
				case "1" :
					category = "휴식";
					break;
				case "2" :
					category = "파티";
					break;
				case "3" : 
					category = "공부";
					break;
				case "4" :
					category = "회의";
					break;
				default : 
					break;
					
				}
				Dto_SearchPlace dto = new Dto_SearchPlace(filePath, category, title, address1, address2, price, no); // ArrayList 생성
				dtos.add(dto); // ArrayList에 저장(메모리)
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(resultSet!=null) resultSet.close(); // 실행이 잘되었다면, 실행종료
				if(preparedStatement!=null) preparedStatement.close(); // 실행이 잘되었다면, 실행종료
				if(connection != null) connection.close(); //실행이 잘되었다면, 실행종료
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return dtos;
	}
	
	//share list tuple 수 세기
	public int countShareTuple() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		int count = 0;
		
		String query = "SELECT DISTINCT count(s.title) FROM share s, place  p WHERE p.no = s.place_no and p.removeDate is null" ;

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
	
		
	
	
	
}///
