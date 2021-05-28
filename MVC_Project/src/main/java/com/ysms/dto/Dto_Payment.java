package com.ysms.dto;

public class Dto_Payment {

	String checkInDate;
	int startTime;
	int endTime;
	int price;
	String user_id;
	int place_no;
	String resName;
	String resPhone;
	String resEmail;
	int resCapacity;
	
	public Dto_Payment() {}
	
	
	
	public Dto_Payment(int place_no, String checkInDate, int startTime, int endTime, int price, String resName, String resPhone,
			String resEmail, int resCapacity) {
		this.place_no = place_no;
		this.checkInDate = checkInDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.price = price;
		this.resName = resName;
		this.resPhone = resPhone;
		this.resEmail = resEmail;
		this.resCapacity = resCapacity;
	}

	public Dto_Payment(String checkInDate, int startTime, int endTime, int price, String user_id, int place_no,
			String resName, String resPhone, String resEmail, int resCapacity) {
		super();
		this.checkInDate = checkInDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.price = price;
		this.user_id = user_id;
		this.place_no = place_no;
		this.resName = resName;
		this.resPhone = resPhone;
		this.resEmail = resEmail;
		this.resCapacity = resCapacity;
	}

	public String getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getPlace_no() {
		return place_no;
	}

	public void setPlace_no(int place_no) {
		this.place_no = place_no;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public String getResPhone() {
		return resPhone;
	}

	public void setResPhone(String resPhone) {
		this.resPhone = resPhone;
	}

	public String getResEmail() {
		return resEmail;
	}

	public void setResEmail(String resEmail) {
		this.resEmail = resEmail;
	}

	public int getResCapacity() {
		return resCapacity;
	}

	public void setResCapacity(int resCapacity) {
		this.resCapacity = resCapacity;
	}
	
	
	
	
	
}
