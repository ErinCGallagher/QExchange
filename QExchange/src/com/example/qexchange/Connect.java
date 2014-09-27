package com.example.qexchange;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import com.mysql.jdbc.*;

import android.content.ContentValues;
import android.database.SQLException;
import android.os.AsyncTask;
import android.util.Log;


public class Connect  extends AsyncTask<String, Void, ResultSet>{
	String email = "sql353460";
	String password = "lQ5%bI4%";
	String dbms = "mysql";
	String serverName = "sql3.freesqldatabase.com";
	int portNumber = 3306;
	Connection conn;

	@Override
	protected ResultSet doInBackground(String... arg0) {
		ResultSet results = null;
		Connection response = null;
		try {
			response = getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (java.sql.SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		this.conn = response;
		//String st = "(ID, email, password, name) values ('4','fog','test','mac')";
		if (arg0[0].equals("INSERT")){
			sqlInsertExecution(arg0[1]);
		}
		else{
			results = sqlSelectionExecution(arg0[1]);
			System.out.println("after but in try.....rgae");
			return results;
		}
		return results;	
	}


	public Connection getConnection() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException, java.sql.SQLException {

		Connection conn = null;
		Properties connectionProps = new Properties();
		String response = " ";
		connectionProps.put("user", this.email);
		connectionProps.put("password", this.password);

		System.out.println("outside if");
		if (this.dbms.equals("mysql")) {
			System.out.println("in if");

			Class.forName("com.mysql.jdbc.Driver").newInstance();

			conn = DriverManager.getConnection(
					"jdbc:mysql://sql3.freesqldatabase.com:3306/sql353460",
					connectionProps);
			System.out.println("added to database");
		}

		return conn;
	}

	public  void sqlInsertExecution(String arg0){
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(arg0);

			stmt.execute();
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



