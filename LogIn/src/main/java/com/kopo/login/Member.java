package com.kopo.login;

public class Member {

	int idx;
	String id;
	String pwd;
	String name;
	String birthday;
	String address;
	String created;
	String updated;

	public Member() {

	}

	public Member(String id, String pwd, String name, String birthday, String address, String created,String updated ) {

		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.birthday = birthday;
		this.address = address;
		this.created = created;
		this.updated = updated;
	}

}
