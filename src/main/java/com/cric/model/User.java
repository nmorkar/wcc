package com.cric.model;

public enum User {

	NIMESH("nimesh","nimesh123","NM",true),
	ANIL("anil","anil123","AM",false),
	KUMAR("kumar","kumar123","KM",false),
	NARU("naru","naru123","NP",false),
	SANTOSH("santosh","santosh123","SK",false),
	HITESH("hitesh","hitesh123","HJ",false),
	CHANDER("chader","chander123","CB",false)
	;
	
	String name;
	String password;
	String initial;
	boolean isAdmin;
	
	User(String name, String password, String initial, boolean isAdmin){
		this.name = name;
		this.password = password;
		this.initial = initial;
		this.isAdmin = isAdmin;
	}
	
	public String getName(){
		return name;
	}
	public String getPassword(){
		return password;
	}
	public String getInitial(){
		return initial;
	}
	public boolean isAdmin(){
		return isAdmin;
	}
	
	
}
