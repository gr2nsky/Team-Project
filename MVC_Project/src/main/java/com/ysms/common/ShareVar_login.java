package com.ysms.common;

public class ShareVar_login {
	
	public static String hostID = "megamind3861";
	public static String hostPW = "shwl*skrjs09";
	public static String authEamilCode = "";
	
	private static ShareVar_login shareVar = new ShareVar_login();
	private ShareVar_login() {}
	public static ShareVar_login getInstance() {
		return shareVar;
	}
	public static String getAuthEamilCode() {
		return authEamilCode;
	}
	
}
