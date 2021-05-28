package com.ysms.dto;
import java.util.Date;
public class Dto_Rental {
	int rentalNo;
	String rentalDate;
	String checkInDate;
	int rentalStartTime;
	int rentalEndTime;
	String rentalCancellationDate;
	int rentalPrice;
	String rentalUser_id;
	int rentalPlace_no;
	String rentalPhoto;
	String rentalTitle;
	Date reviewSubmitDate;
	Date reviewRemoveDate;
	
	
	// constructor
	public Dto_Rental() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Dto_Rental(int rentalNo, String rentalDate, String checkInDate, int rentalStartTime, int rentalEndTime,
			String rentalCancellationDate, int rentalPrice, String rentalUser_id, int rentalPlace_no,
			String rentalPhoto, String rentalTitle) {
		super();
		this.rentalNo = rentalNo;
		this.rentalDate = rentalDate;
		this.checkInDate = checkInDate;
		this.rentalStartTime = rentalStartTime;
		this.rentalEndTime = rentalEndTime;
		this.rentalCancellationDate = rentalCancellationDate;
		this.rentalPrice = rentalPrice;
		this.rentalUser_id = rentalUser_id;
		this.rentalPlace_no = rentalPlace_no;
		this.rentalPhoto = rentalPhoto;
		this.rentalTitle = rentalTitle;
	}

	public Dto_Rental(int rentalNo, String rentalDate, String checkInDate, int rentalStartTime, int rentalEndTime,
			String rentalCancellationDate, int rentalPrice, String rentalUser_id, int rentalPlace_no,
			String rentalPhoto, String rentalTitle, 
			Date reviewSubmitDate, Date reviewRemoveDate) {
		super();
		this.rentalNo = rentalNo;
		this.rentalDate = rentalDate;
		this.checkInDate = checkInDate;
		this.rentalStartTime = rentalStartTime;
		this.rentalEndTime = rentalEndTime;
		this.rentalCancellationDate = rentalCancellationDate;
		this.rentalPrice = rentalPrice;
		this.rentalUser_id = rentalUser_id;
		this.rentalPlace_no = rentalPlace_no;
		this.rentalPhoto = rentalPhoto;
		this.rentalTitle = rentalTitle;
		this.reviewSubmitDate = reviewSubmitDate;
		this.reviewRemoveDate = reviewRemoveDate;
	}
	// getter setter
	public int getRentalNo() {
		return rentalNo;
	}
	public void setRentalNo(int rentalNo) {
		this.rentalNo = rentalNo;
	}
	public String getRentalDate() {
		return rentalDate;
	}
	public void setRentalDate(String rentalDate) {
		this.rentalDate = rentalDate;
	}
	public String getCheckInDate() {
		return checkInDate;
	}
	public void setCheckInDate(String checkInDate) {
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
	public String getRentalCancellationDate() {
		return rentalCancellationDate;
	}
	public void setRentalCancellationDate(String rentalCancellationDate) {
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
	public String getRentalPhoto() {
		return rentalPhoto;
	}
	public void setRentalPhoto(String rentalPhoto) {
		this.rentalPhoto = rentalPhoto;
	}
	public String getRentalTitle() {
		return rentalTitle;
	}
	public void setRentalTitle(String rentalTitle) {
		this.rentalTitle = rentalTitle;
	}
	public Date getReviewSubmitDate() {
		return reviewSubmitDate;
	}
	public void setReviewSubmitDate(Date reviewSubmitDate) {
		this.reviewSubmitDate = reviewSubmitDate;
	}
	public Date getReviewRemoveDate() {
		return reviewRemoveDate;
	}
	public void setReviewRemoveDate(Date reviewRemoveDate) {
		this.reviewRemoveDate = reviewRemoveDate;
	}
	
	
	
}// end line