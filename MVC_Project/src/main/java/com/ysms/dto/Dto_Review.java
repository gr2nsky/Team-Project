package com.ysms.dto;

import java.util.Date;

public class Dto_Review {

	
	int rentalNo;
	Date rentalDate;
	Date checkInDate;
	int rentalStartTime;
	int rentalEndTime;
	Date rentalCancellationDate;
	int rentalPrice;
	String rentalUser_id;
	int rentalPlace_no;
	String reviewContent;
	int reviewScore;
	Date reviewSubmitDate;
	Date reviewUpdateDate;
	Date reviewRemoveDate;
	String reviewFilePath;
	String userFilePath;
	String reviewPlaceName ;

	
	// Constructor
	public Dto_Review() {
		// TODO Auto-generated constructor stub
	}
	
	

	// myinfo review list
	public Dto_Review(int rentalNo, String reviewContent, int reviewScore, Date reviewUpdateDate, String reviewFilePath,
			String reviewPlaceName) {
		super();
		this.rentalNo = rentalNo;
		this.reviewContent = reviewContent;
		this.reviewScore = reviewScore;
		this.reviewUpdateDate = reviewUpdateDate;
		this.reviewFilePath = reviewFilePath;
		this.reviewPlaceName = reviewPlaceName;
	}




	public Dto_Review(String rentalUser_id, String reviewContent, int reviewScore, Date reviewUpdateDate,
			Date reviewRemoveDate, String reviewFilePath, String userFilePath) {
		super();
		this.rentalUser_id = rentalUser_id;
		this.reviewContent = reviewContent;
		this.reviewScore = reviewScore;
		this.reviewUpdateDate = reviewUpdateDate;
		this.reviewRemoveDate = reviewRemoveDate;
		this.reviewFilePath = reviewFilePath;
		this.userFilePath = userFilePath;
	}

	// review Detail
	public Dto_Review(String reviewContent, int reviewScore, Date reviewUpdateDate, String reviewFilePath,
			String reviewPlaceName) {
		super();
		this.reviewContent = reviewContent;
		this.reviewScore = reviewScore;
		this.reviewUpdateDate = reviewUpdateDate;
		this.reviewFilePath = reviewFilePath;
		this.reviewPlaceName = reviewPlaceName;
	}


	public int getRentalNo() {
		return rentalNo;
	}


	public void setRentalNo(int rentalNo) {
		this.rentalNo = rentalNo;
	}


	public Date getRentalDate() {
		return rentalDate;
	}


	public void setRentalDate(Date rentalDate) {
		this.rentalDate = rentalDate;
	}


	public Date getCheckInDate() {
		return checkInDate;
	}


	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}


	public int getRentalStartTime() {
		return rentalStartTime;
	}


	public void setRentalStartTime(int rentalStartTime) {
		this.rentalStartTime = rentalStartTime;
	}


	public int getRentalEndTime() {
		return rentalEndTime;
	}


	public void setRentalEndTime(int rentalEndTime) {
		this.rentalEndTime = rentalEndTime;
	}


	public Date getRentalCancellationDate() {
		return rentalCancellationDate;
	}


	public void setRentalCancellationDate(Date rentalCancellationDate) {
		this.rentalCancellationDate = rentalCancellationDate;
	}


	public int getRentalPrice() {
		return rentalPrice;
	}


	public void setRentalPrice(int rentalPrice) {
		this.rentalPrice = rentalPrice;
	}


	public String getRentalUser_id() {
		return rentalUser_id;
	}


	public void setRentalUser_id(String rentalUser_id) {
		this.rentalUser_id = rentalUser_id;
	}


	public int getRentalPlace_no() {
		return rentalPlace_no;
	}


	public void setRentalPlace_no(int rentalPlace_no) {
		this.rentalPlace_no = rentalPlace_no;
	}


	public String getReviewContent() {
		return reviewContent;
	}


	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}


	public int getReviewScore() {
		return reviewScore;
	}


	public void setReviewScore(int reviewScore) {
		this.reviewScore = reviewScore;
	}


	public Date getReviewSubmitDate() {
		return reviewSubmitDate;
	}


	public void setReviewSubmitDate(Date reviewSubmitDate) {
		this.reviewSubmitDate = reviewSubmitDate;
	}


	public Date getReviewUpdateDate() {
		return reviewUpdateDate;
	}


	public void setReviewUpdateDate(Date reviewUpdateDate) {
		this.reviewUpdateDate = reviewUpdateDate;
	}


	public Date getReviewRemoveDate() {
		return reviewRemoveDate;
	}


	public void setReviewRemoveDate(Date reviewRemoveDate) {
		this.reviewRemoveDate = reviewRemoveDate;
	}


	public String getReviewFilePath() {
		return reviewFilePath;
	}


	public void setReviewFilePath(String reviewFilePath) {
		this.reviewFilePath = reviewFilePath;
	}


	public String getUserFilePath() {
		return userFilePath;
	}


	public void setUserFilePath(String userFilePath) {
		this.userFilePath = userFilePath;
	}


	public String getReviewPlaceName() {
		return reviewPlaceName;
	}


	public void setReviewPlaceName(String reviewPlaceName) {
		this.reviewPlaceName = reviewPlaceName;
	}
	
	
}

	
	