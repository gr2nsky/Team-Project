package com.ysms.dto;

public class Dto_Reservation_rentalDetail {
	// 누가 예약했는지도 중요하니까 가져온다
		// 필요한것 : 예약넘버, 예약자이름, 예약날자, 시작, 이용시간(종료시간 - 시작시간 : 이건 dao가 계산),
		int no;
		String resName;
		String resEmail;
		String resPhone;
		int resCapacity;
		int resPrice;
		int month;
		int date;
		int startTime;
		int usingTime;
		
		public Dto_Reservation_rentalDetail(){ }

		public Dto_Reservation_rentalDetail(int no, String resName, String resEmail, String resPhone, int resCapacity,
				int resPrice, int month, int date, int startTime, int usingTime) {
			super();
			this.no = no;
			this.resName = resName;
			this.resEmail = resEmail;
			this.resPhone = resPhone;
			this.resCapacity = resCapacity;
			this.resPrice = resPrice;
			this.month = month;
			this.date = date;
			this.startTime = startTime;
			this.usingTime = usingTime;
		}
		
		

		public String getResEmail() {
			return resEmail;
		}

		public void setResEmail(String resEmail) {
			this.resEmail = resEmail;
		}

		public String getResPhone() {
			return resPhone;
		}

		public void setResPhone(String resPhone) {
			this.resPhone = resPhone;
		}

		public int getResCapacity() {
			return resCapacity;
		}

		public void setResCapacity(int resCapacity) {
			this.resCapacity = resCapacity;
		}

		public int getResPrice() {
			return resPrice;
		}

		public void setPrice(int resPrice) {
			this.resPrice = resPrice;
		}

		public int getNo() {
			return no;
		}

		public void setNo(int no) {
			this.no = no;
		}

		public String getResName() {
			return resName;
		}

		public void setResName(String resName) {
			this.resName = resName;
		}


		public int getMonth() {
			return month;
		}

		public void setMonth(int month) {
			this.month = month;
		}

		public int getDate() {
			return date;
		}

		public void setDate(int date) {
			this.date = date;
		}

		public int getStartTime() {
			return startTime;
		}

		public void setStartTime(int startTime) {
			this.startTime = startTime;
		}

		public int getUsingTime() {
			return usingTime;
		}

		public void setUsingTime(int usingTime) {
			this.usingTime = usingTime;
		}
		
		public String getInfo() {
			return month + "/" + date + " : " + startTime + " 부터 " + usingTime + "시간";
		}
}
