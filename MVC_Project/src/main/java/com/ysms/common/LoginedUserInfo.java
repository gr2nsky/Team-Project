package com.ysms.common;

public class LoginedUserInfo {

	public static String id;
	public static int status;
	public static String name;
	public static String phone;
	public static String email;

	public static boolean isUse() {
		if (id == null || id.equals("")) {
			System.out.println("<<<<is unUse");
			return false;
		}
		System.out.println("<<<is Use");
		return true;
	}

}