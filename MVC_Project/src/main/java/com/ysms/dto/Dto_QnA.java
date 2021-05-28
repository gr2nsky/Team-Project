package com.ysms.dto;

import java.sql.Timestamp;

public class Dto_QnA {

	
	int qnaNo;
	String qnaContent;
	String qnaSubmitDate;
	String qnaQ_updateDate;
	String qnaQ_removeDate;
	String qnaSender;
	int qnaPlace_no;
	String qnaTarget;
	String qnaAnswer;	
	String qnaA_submitDate;
	String qnaA_updateDate;
	String qnaA_removeDate;
	String qnaPlaceName;
	String qnaUserFilePath;
	String qnaYesNo;
	
	
	
	// Constructor
	public Dto_QnA() {
		// TODO Auto-generated constructor stub
	}
	
	// qna List - qnaDto
	// qnaContent, qnaQ_updateDate, qnaSender, qnaAnswer, qnaA_updateDate, qnaA_removeDate
	public Dto_QnA(String qnaContent, String qnaQ_updateDate, String qnaSender, String qnaAnswer,
			String qnaA_updateDate, String qnaA_removeDate, int qnaPlace_no, 
			String qnaUserFilePath, String qnaPlaceName, String qnaTarget) {
		
		super();
		this.qnaContent = qnaContent;
		this.qnaQ_updateDate = qnaQ_updateDate;
		this.qnaSender = qnaSender;
		this.qnaAnswer = qnaAnswer;
		this.qnaA_updateDate = qnaA_updateDate;
		this.qnaA_removeDate = qnaA_removeDate;
		this.qnaPlace_no = qnaPlace_no;
		this.qnaUserFilePath = qnaUserFilePath;
		this.qnaPlaceName = qnaPlaceName;
		this.qnaTarget = qnaTarget;
	}
	
	
	public Dto_QnA(String qnaTarget) {
		super();
		this.qnaTarget = qnaTarget;
	}
	
	// myinfo_qna List
	// qnaNo, qnaContent, qnaQ_updateDate, qnaAnswer, qnaA_removeDate, 
	// qnaPlaceName, yesNo, qnaA_updateDate
	public Dto_QnA(int qnaNo, String qnaContent, String qnaQ_updateDate, 
			String qnaAnswer, String qnaA_removeDate, String qnaPlaceName,
			String qnaYesNo, String qnaA_updateDate) {
		super();
		this.qnaNo = qnaNo;
		this.qnaContent = qnaContent;
		this.qnaQ_updateDate = qnaQ_updateDate;
		this.qnaAnswer = qnaAnswer;
		this.qnaA_removeDate = qnaA_removeDate;
		this.qnaPlaceName = qnaPlaceName;
		this.qnaYesNo = qnaYesNo;
		this.qnaA_updateDate = qnaA_updateDate;
	}
	
	// Host_qna List
	// qnaNo, qnaContent, qnaQ_updateDate, qnaSender, qnaAnswer, qnaA_removeDate, 
	// qnaPlaceName, yesNo, qnaA_updateDate, 
	public Dto_QnA(int qnaNo, String qnaContent, String qnaQ_updateDate, 
			String qnaSender, String qnaAnswer, String qnaA_removeDate, String qnaPlaceName,
			String qnaYesNo, String qnaA_updateDate, int qnaPlace_no) {
		super();
		this.qnaNo = qnaNo;
		this.qnaContent = qnaContent;
		this.qnaQ_updateDate = qnaQ_updateDate;
		this.qnaSender = qnaSender;
		this.qnaAnswer = qnaAnswer;
		this.qnaA_updateDate = qnaA_updateDate;
		this.qnaA_removeDate = qnaA_removeDate;
		this.qnaPlace_no = qnaPlace_no;
		this.qnaPlaceName = qnaPlaceName;
		this.qnaYesNo = qnaYesNo;
	}
	
	// myinfo and Host - qnaDetailView
	// Dao는 Dao_myinfo
	public Dto_QnA(int qnaNo, String qnaContent, String qnaAnswer) {
		super();
		this.qnaNo = qnaNo;
		this.qnaContent = qnaContent;
		this.qnaAnswer = qnaAnswer;
	}
	

	public int getQnaNo() {
		return qnaNo;
	}



	public void setQnaNo(int qnaNo) {
		this.qnaNo = qnaNo;
	}


	public String getQnaContent() {
		return qnaContent;
	}


	public void setQnaContent(String qnaContent) {
		this.qnaContent = qnaContent;
	}


	public String getQnaSubmitDate() {
		return qnaSubmitDate;
	}


	public void setQnaSubmitDate(String qnaSubmitDate) {
		this.qnaSubmitDate = qnaSubmitDate;
	}


	public String getQnaQ_updateDate() {
		return qnaQ_updateDate;
	}


	public void setQnaQ_updateDate(String qnaQ_updateDate) {
		this.qnaQ_updateDate = qnaQ_updateDate;
	}


	public String getQnaQ_removeDate() {
		return qnaQ_removeDate;
	}


	public void setQnaQ_removeDate(String qnaQ_removeDate) {
		this.qnaQ_removeDate = qnaQ_removeDate;
	}


	public String getQnaSender() {
		return qnaSender;
	}


	public void setQnaSender(String qnaSender) {
		this.qnaSender = qnaSender;
	}


	public int getQnaPlace_no() {
		return qnaPlace_no;
	}


	public void setQnaPlace_no(int qnaPlace_no) {
		this.qnaPlace_no = qnaPlace_no;
	}


	public String getQnaTarget() {
		return qnaTarget;
	}


	public void setQnaTarget(String qnaTarget) {
		this.qnaTarget = qnaTarget;
	}


	public String getQnaAnswer() {
		return qnaAnswer;
	}


	public void setQnaAnswer(String qnaAnswer) {
		this.qnaAnswer = qnaAnswer;
	}


	public String getQnaA_submitDate() {
		return qnaA_submitDate;
	}


	public void setQnaA_submitDate(String qnaA_submitDate) {
		this.qnaA_submitDate = qnaA_submitDate;
	}


	public String getQnaA_updateDate() {
		return qnaA_updateDate;
	}


	public void setQnaA_updateDate(String qnaA_updateDate) {
		this.qnaA_updateDate = qnaA_updateDate;
	}


	public String getQnaA_removeDate() {
		return qnaA_removeDate;
	}


	public void setQnaA_removeDate(String qnaA_removeDate) {
		this.qnaA_removeDate = qnaA_removeDate;
	}

	public String getQnaPlaceName() {
		return qnaPlaceName;
	}

	public void setQnaPlaceName(String qnaPlaceName) {
		this.qnaPlaceName = qnaPlaceName;
	}

	public String getQnaUserFilePath() {
		return qnaUserFilePath;
	}

	public void setQnaUserFilePath(String qnaUserFilePath) {
		this.qnaUserFilePath = qnaUserFilePath;
	}

	public String getQnaYesNo() {
		return qnaYesNo;
	}

	public void setQnaYesNo(String qnaYesNo) {
		this.qnaYesNo = qnaYesNo;
	}
	
}