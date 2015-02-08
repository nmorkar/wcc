package com.cric.model;

public enum User {

	NIMESH(1l,"nimesh","nimesh7890","NM",true),
	ANIL(2l,"anil","anil123","AM",false),
	NARU(3l,"naru","naru123","NP",false),
	KUMAR(4l,"kumar","kumar123","KM",false),
	SANTOSH(5l,"santosh","santosh123","SK",false),
	CHANDER(6l,"chader","chander123","CB",false),
	HITESH(7l,"hitesh","hitesh123","HJ",false),
	;
	
	Long id;
	String name;
	String password;
	String initial;
	boolean isAdmin;
	String email;
	
	User(Long id,String name, String password, String initial, boolean isAdmin){
		this.id = id;
		this.name = name;
		this.password = password;
		this.initial = initial;
		this.isAdmin = isAdmin;
	}
	
	public Long getId(){
		return id;
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
