package com.ysms.dto;

import java.util.ArrayList;

public class Dto_Refine_rental {

	ArrayList<Integer> startNum;
	ArrayList<Integer> shareTime;
	int totalShareTime;
	
	public Dto_Refine_rental(){
		startNum = new ArrayList<Integer>();
		shareTime = new ArrayList<Integer>();
		totalShareTime = 0;
	}
	
	public void insertData(int shareStartTime, int rentalStartTime, int useTime){
		int sNum = rentalStartTime - shareStartTime;
		startNum.add(sNum);
		this.shareTime.add(useTime);
		totalShareTime += useTime;
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
