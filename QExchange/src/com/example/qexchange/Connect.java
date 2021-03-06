package com.example.qexchange;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import android.database.SQLException;
import android.os.AsyncTask;


public class Connect extends AsyncTask<String, Void, ResultSet>{
	String email = "sql363998";
	String password = "vM9!cK1*";
	String dbms = "mysql";
	String serverName = "sql3.freesqldatabase.com";
	int portNumber = 3306;
	Connection conn;

	@Override
	protected ResultSet doInBackground(String... arg0) throws SQLException {
		ResultSet results = null;
		Connection response = null;
		PreparedStatement stmt = null;
		
		try {
			response = getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
		}	
		this.conn = response;
		
		if (arg0[0].equals("INSERT")){
			sqlInsertExecution(arg0[1],stmt);
		}
		else{
			results = sqlSelectionExecution(arg0[1]);
		}
		
		//close connections
		/**
		//cannot close this before 
		if (results !=null){
			try {
				results.close();
			} catch (java.sql.SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		**/
		if (stmt !=null){
			try {
				stmt.close();
			} catch (java.sql.SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (conn !=null){
			try {
				conn.close();
			} catch (java.sql.SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return results;	
	}

	//create connection
	public Connection getConnection() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException, java.sql.SQLException {

		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", this.email);
		connectionProps.put("password", this.password);

		System.out.println("outside if");
		if (this.dbms.equals("mysql")) {
			System.out.println("in if");

			Class.forName("com.mysql.jdbc.Driver").newInstance();

			conn = DriverManager.getConnection(
					"jdbc:mysql://sql3.freesqldatabase.com:3306/sql363998",
					connectionProps);
			System.out.println("added to database");
		}

		return conn;
	}

	public void sqlInsertExecution(String arg0, PreparedStatement stmt){
		try {
			stmt = conn.prepareStatement(arg0);
			stmt.executeUpdate();
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
		}
	}

	public  ResultSet sqlSelectionExecution(String arg0){
		java.sql.Statement stmt;
		ResultSet results = null;
		try {
			stmt = conn.createStatement();
			results = stmt.executeQuery(arg0);

		} catch (java.sql.SQLException e) {
			e.printStackTrace();
		}
		return results;
	}



}



