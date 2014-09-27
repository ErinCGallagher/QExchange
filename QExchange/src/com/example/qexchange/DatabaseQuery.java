package com.example.qexchange;

public class DatabaseQuery {
	
	//get/add accounts
	public Account getAccount(String username, String password) {
		return new Account(username, password);
	}
	
	public void addAccount(String username, String password) {
		addAccount(username, password, "Jon Snow", "JonSnow@thewall.net");
	}
	
	public void addAccount(String username, String password, String name) {
		addAccount(username, password, name, "JonSnow@thewall.net");
	}
	
	public void addAccount(String username, String password, String name, String email) {
		//Doesn't do anything until I get the database stuff set up
	}
	
	
	
	//get/add books
	public Book getBook(int id) {
		return new Book("ASOIAF", 5, 4.99, "");
	}
	
	public void addBook() {
		
	}
	
	
	
}
