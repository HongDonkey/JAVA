package com.com.com;

public class Member {
	
	int idx;
	String id;
	String pwd;
	String name;
	String address;
	String birthday;	
	String now;
	String updated;

	Member() {

	}
	
	Member(String id, String pwd, String name, String address, String birthday) {
		
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.address = address;
		this.birthday = birthday;
		

	}
	Member(String id, String pwd, String name, String address, String birthday, String now, String updated) {
		
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.address = address;
		this.birthday = birthday;
		this.now = now;
		this.updated = updated;
	}
	
	Member(int idx,String id, String pwd, String name, String address, String part, String updated){
		
		this.idx = idx;
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.address = address;
		this.birthday = birthday;
		this.now = now;
		this.updated = updated;

	}

	
	
	

}