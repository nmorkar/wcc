package com.cric.util;

import java.util.HashMap;
import java.util.Map;

import com.cric.model.User;

public class ValidateUtil {

	private static Map<String, String> m = new HashMap<>();

	static {

		m.put("nimesh", "nimesh183");
		m.put("anil", "anil123");
		m.put("kumar", "kumar123");
		m.put("naru", "naru123");
		m.put("hitesh", "hitesh123");
		m.put("santosh", "santosh123");
		m.put("chander", "chander");

	}

	public static boolean isValid(String username, String password) {
		/*
		 * String u = m.get(username); if(u == null || !u.equals(password))
		 * return false; return true;
		 */
		try {
			User u = User.valueOf(username.toUpperCase());
			if (!u.getPassword().equals(password))
				return false;
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public static boolean isAdmin(String username){
		try {
			User u = User.valueOf(username.toUpperCase());
			return u.isAdmin();
		} catch (Exception e) {
			return false;
		}
	}
}
