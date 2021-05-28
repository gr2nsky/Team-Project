package com.ysms.dto;
import java.sql.Timestamp;
public class Dto_Share {
	int no;
	String title;
	String user_id;
	String introduce;
	String date;
	Timestamp registrationDate;
	Timestamp removeDate;
	
	
	String filePath;
	
	
	
	
	int place_no;
	/* 2021.05.17   Park Jaewon
	 * DB : Place Entity
	 * WriteSpaceCommand 용
	 */
	String address1;
	String address2;

	int capacity;
	int category;
	int price;
	String postCode;
	int startTime;
	int endTime;
	String dayLimit;
	
	
	
	
	

	public Dto_Share(int place_no) {
		super();
		this.place_no = place_no;
	}

	public Dto_Share(int no, String title, String introduce, String filePath, int price, int startTime, int endTime, String dayLimit) {
		super();
		this.no = no;
		this.title = title;
		this.introduce = introduce;
		this.filePath = filePath;
		this.price = price;
		this.startTime = startTime;
		this.endTime = endTime;
		this.dayLimit = dayLimit;
	}

	public Dto_Share(int no, int capacity, int category, int price, int startTime, int endTime, 
			   String title, String user_id, String introduce, 
			   String postCode, String address1, String address2, 
			   Timestamp registrationDate, Timestamp removeDate, 
			   String filePath, String dayLimit, int place_no) {
		super();
		this.no = no;
		this.title = title;
		this.user_id = user_id;
		this.introduce = introduce;
		this.registrationDate = registrationDate;
		this.removeDate = removeDate;
		this.address1 = address1;
		this.address2 = address2;
		this.capacity = capacity;
		this.category = category;
		this.price = price;
		this.postCode = postCode;
		this.startTime = startTime;
		this.endTime = endTime;
		this.filePath = filePath;
		this.dayLimit = dayLimit;
		this.place_no = place_no;
	}

	public Dto_Share(int no, String postCode,String address1, String address2, int capacity, Timestamp registrationDate, Timestamp removeDate,
			int category) {
		super();
		this.no = no;
		this.postCode = postCode;
		this.address1 = address1;
		this.address2 = address2;
		this.registrationDate = registrationDate;
		this.removeDate = removeDate;
		this.capacity = capacity;
		this.category = category;
	}

	public Dto_Share(String title, String user_id, String introduce, int price , String filePath, int place_no, int startTime, int endTime, String dayLimit) {
		super();
		this.title = title;
		this.user_id = user_id;
		this.introduce = introduce;
		this.filePath = filePath;
		this.place_no = place_no;
		this.price = price;
		this.startTime = startTime;
		this.endTime = endTime;
		this.dayLimit = dayLimit;
	}

	public Dto_Share(String address1, String address2, int capacity, int category) {
		super();
		this.address1 = address1;
		this.address2 = address2;
		this.capacity = capacity;
		this.category = category;
	}

	public Dto_Share() {

	}

	public Dto_Share(String title, String user_id, String introduce, String filePath) {
		super();
		this.user_id = user_id;
		this.title = title;
		this.introduce = introduce;
		this.filePath = filePath;
		
	}

	public Dto_Share(int no, String title, String user_id, String date, int place_no) {
		super();
		this.no = no;
		this.user_id = user_id;
		this.title = title;
		this.date = date;
		this.place_no = place_no;
	}

	public Dto_Share(int no, String title, String user_id, String introduce, String date, String filePath) {
		super();
		this.no = no;
		this.user_id = user_id;
		this.title = title;
		this.introduce = introduce;
		this.filePath = filePath;
		this.date = date;
	}
	
	public String getAddress1() {
		return address1;
	}
	
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	
	public String getAddress2() {
		return address2;
	}
	
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getStartTime() {
		return startTime;
	}
	
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	
	public int getEndTime() {
		return endTime;
	}
	
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}
	
	public String getDayLimit() {
		return dayLimit;
	}
	
	public void setDayLimit(String dayLimit) {
		this.dayLimit = dayLimit;
	}
	
	public int getPlace_no() {
		return place_no;
	}
	
	public void setPlace_no(int place_no) {
		this.place_no = place_no;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public int getCategory() {
		return category;
	}
	
	public void setCategory(int category) {
		this.category = category;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}


	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Timestamp getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Timestamp registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Timestamp getRemoveDate() {
		return removeDate;
	}

	public void setRemoveDate(Timestamp removeDate) {
		this.removeDate = removeDate;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public void printAll() {
		System.out.println("share no : " + no + "title : " + title + "intro : " + introduce);
		System.out.println("place no  : " + place_no + "address  : " + address1 + ", " + address2 + "start-end time : "
				+ startTime + " - " + endTime);
	}
	
}

