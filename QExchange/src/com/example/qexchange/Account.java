package com.example.qexchange;

public class Account {
	
	private String email;
	private String password;
	private String name;
	
	public Account(String username, String password) {
		this(username, password, "Jon Snow");
	}
	
	public Account(String email, String password, String name) {
		this.email = email;
		this.password = password;
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}

}
