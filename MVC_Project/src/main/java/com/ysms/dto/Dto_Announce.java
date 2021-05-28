package com.ysms.dto;

public class Dto_Announce {
		
		int no;
		String title;
		String content;
		String createDate;
		String updateDate;
		String removeDate;
		String user_id;
		
		public Dto_Announce() {
			
		}

		public Dto_Announce(int no, String title, String content, String createDate, String updateDate, String removeDate,
				String user_id) {
			super();
			this.no = no;
			this.title = title;
			this.content = content;
			this.createDate = createDate;
			this.updateDate = updateDate;
			this.removeDate = removeDate;
			this.user_id = user_id;
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

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getCreateDate() {
			return createDate;
		}

		public void setCreateDate(String createDate) {
			this.createDate = createDate;
		}

		public String getUpdateDate() {
			return updateDate;
		}

		public void setUpdateDate(String updateDate) {
			this.updateDate = updateDate;
		}

		public String getRemoveDate() {
			return removeDate;
		}

		public void setRemoveDate(String removeDate) {
			this.removeDate = removeDate;
		}

		public String getUser_id() {
			return user_id;
		}

		public void setUser_id(String user_id) {
			this.user_id = user_id;
		}


}
