package com.example.qexchange;

public class Account {
	
	private String username;
	private String password;
	private String name;
	private String email;
	
	public Account(String username, String password) {
		this(username, password, "Jon Snow");
	}
	
	public Account(String username, String password, String name) {
		this.username = username;
		this.password = password;
		this.name = name;
	}
	
	public String getUsername() {
		return username;
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
