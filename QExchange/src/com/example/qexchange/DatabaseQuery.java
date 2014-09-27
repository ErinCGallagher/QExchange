package com.example.qexchange;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import com.mysql.jdbc.*;

import android.database.SQLException;
import android.os.AsyncTask;


public class DatabaseQuery  extends AsyncTask<String, Void, String>{
	String userName = "sql353460";
	String password = "lQ5%bI4%";
	String dbms = "mysql";
	String serverName = "sql3.freesqldatabase.com";
	int portNumber = 3306;
	Connection conn;
	
	@Override
	protected String doInBackground(String... arg0) {
		Connection response;
		response = getConnection();	
		this.conn = response;
		//insert();
		return " ";
	}
	
	
	public Connection getConnection() throws SQLException {

	    Connection conn = null;
	    Properties connectionProps = new Properties();
	    String response = " ";
	    connectionProps.put("user", this.userName);
	    connectionProps.put("password", this.password);
	    
	    System.out.println("outside if");
		    if (this.dbms.equals("mysql")) {
		    	System.out.println("in if");
		    	try{
		    		try {
						Class.forName("com.mysql.jdbc.Driver").newInstance();
						System.out.println("Driver instantiated");
					} catch (InstantiationException e) {
						System.out.println("InstantiationException");
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						System.out.println("IllegalAccessException");
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						System.out.println("ClassNotFoundException");
						e.printStackTrace();
					}
			        conn = DriverManager.getConnection(
			                   "jdbc:mysql://sql3.freesqldatabase.com:3306/sql353460",
			                   connectionProps);
			        //Statement stmt = (Statement) conn.createStatement();
			        //int reset = stmt.executeUpdate("INSERT INTO Accounts (ID, email, password, name) VALUES('1','hello','test','erin')");
			        System.out.println("added to database");
		    	} catch (java.sql.SQLException e) {
					System.out.println("cannot connect to database");
					e.printStackTrace();
				}
		   }
	    	
	   // System.out.println("Connected to database");
	    return conn;
	
	}
	
	public void insert(){
		Statement stmt;
		try {
			stmt = (Statement) conn.createStatement();
			 int reset = stmt.executeUpdate("INSERT INTO Accounts (ID, email, password, name) VALUES('4','fog','test','mac')");
		} catch (java.sql.SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
	}
	/*
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
	
	*/


}
