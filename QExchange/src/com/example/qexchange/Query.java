package com.example.qexchange;

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
	
	public static boolean CheckEmailExists(String email) throws SQLException, InterruptedException, ExecutionException {
		Connect database = new Connect();
		ResultSet result =null;
		
		result = database.execute("SELECT","SELECT * FROM Accounts WHERE email = "+email).get();
		if (result.next())
			return false;
		return true;
	}
}
