package com.example.qexchange;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

public class Query {

	
	public static ResultSet query(String operation, String query) throws SQLException, InterruptedException, ExecutionException {
		
		Connect database = new Connect();
		ResultSet result =null;
		
		result = database.execute(operation,query).get();
		
		return result;
	}
	
	public static boolean CheckEmailExists(String inputEmail) throws SQLException, InterruptedException, ExecutionException {
		Connect database = new Connect();
		ResultSet result =null;
		String query = "SELECT 1 from Accounts WHERE email = '"+inputEmail+"'";
		result = database.execute("Check",query).get();
		if (result.next())
			return true;
		return false;
	}
}
