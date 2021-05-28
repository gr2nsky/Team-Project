package com.ysms.dto;

public class Dto_SearchPlace  {

	String filePath;
	String category;
	String title;
	String address1;
	String address2;
	int price;
	String dayLimit;
	int no;
	
	public Dto_SearchPlace() {
		// TODO Auto-generated constructor stub
	}


	public Dto_SearchPlace(int no, String filePath, String category, String title, String address1, String address2, int price,
			String dayLimit) {
		super();
		this.no = no;
		this.filePath = filePath;
		this.category = category;
		this.title = title;
		this.address1 = address1;
		this.address2 = address2;
		this.price = price;
		this.dayLimit = dayLimit;
	}


	public Dto_SearchPlace(String filePath, String category, String title, String address1, String address2, int price,
			int no) {
		super();
		this.filePath = filePath;
		this.category = category;
		this.title = title;
		this.address1 = address1;
		this.address2 = address2;
		this.price = price;
		this.no = no;
	}


	public String getFilePath() {
		return filePath;
	}


	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
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


	public String getDayLimit() {
		return dayLimit;
	}


	public void setDayLimit(String dayLimit) {
		this.dayLimit = dayLimit;
	}


	public int getNo() {
		return no;
	}


	public void setNo(int no) {
		this.no = no;
	}


	
	
	
	
	

}
