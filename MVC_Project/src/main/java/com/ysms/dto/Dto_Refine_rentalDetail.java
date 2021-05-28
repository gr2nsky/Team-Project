package com.ysms.dto;

import java.util.ArrayList;

public class Dto_Refine_rentalDetail {
	
	ArrayList<Integer> no;
	ArrayList<String> resName;
	ArrayList<String> resEmail;
	ArrayList<String> resPhone;
	ArrayList<Integer> resCapacity;
	ArrayList<Integer> resPrice;
	ArrayList<Integer> startNum;
	ArrayList<Integer> shareTime;
	int totalShareTime;
	
	public Dto_Refine_rentalDetail(){
		no = new ArrayList<Integer>();
		resName = new ArrayList<String>();
		resEmail = new ArrayList<String>();
		resPhone = new ArrayList<String>();
		resCapacity = new ArrayList<Integer>();
		resPrice = new ArrayList<Integer>();
		startNum = new ArrayList<Integer>();
		shareTime = new ArrayList<Integer>();
		totalShareTime = 0;
	}
	
	public void insertData(int no, String resName, String resEmail, String resPhone, int resCapacity, int resPrice,
			int shareStartTime, int rentalStartTime, int useTime){
		this.no.add(no);
		this.resName.add(resName);
		this.resEmail.add(resEmail);
		this.resPhone.add(resPhone);
		this.resCapacity.add(resCapacity);
		this.resPrice.add(resPrice);
		int sNum = rentalStartTime - shareStartTime;
		startNum.add(sNum);
		this.shareTime.add(useTime);
		totalShareTime += useTime;
	}
	
	
	
	public ArrayList<Integer> getNo() {
		return no;
	}

	public void setNo(ArrayList<Integer> no) {
		this.no = no;
	}

	public ArrayList<String> getResName() {
		return resName;
	}

	public void setResName(ArrayList<String> resName) {
		this.resName = resName;
	}
	
	
	public ArrayList<String> getResEmail() {
		return resEmail;
	}

	public void setResEmail(ArrayList<String> resEmail) {
		this.resEmail = resEmail;
	}

	public ArrayList<String> getResPhone() {
		return resPhone;
	}

	public void setResPhone(ArrayList<String> resPhone) {
		this.resPhone = resPhone;
	}

	public ArrayList<Integer> getResCapacity() {
		return resCapacity;
	}

	public void setResCapacity(ArrayList<Integer> resCapacity) {
		this.resCapacity = resCapacity;
	}

	public ArrayList<Integer> getResPrice() {
		return resPrice;
	}

	public void setResPrice(ArrayList<Integer> resPrice) {
		this.resPrice = resPrice;
	}

	public String printTotalShareTime() {
		return Integer.toString(totalShareTime);
	}

	public ArrayList<Integer> getStartNum() {
		return startNum;
	}

	public void setStartNum(ArrayList<Integer> startNum) {
		this.startNum = startNum;
	}

	public ArrayList<Integer> getShareTime() {
		return shareTime;
	}

	public void setShareTime(ArrayList<Integer> shareTime) {
		this.shareTime = shareTime;
	}

	public int getTotalShareTime() {
		return totalShareTime;
	}

	public void setTotalShareTime(int totalShareTime) {
		this.totalShareTime = totalShareTime;
	}
	
}