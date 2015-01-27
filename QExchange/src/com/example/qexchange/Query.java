package com.example.qexchange;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

public class Query {

	
	public static ResultSet query(String operation, String query) throws SQLException, InterruptedException, ExecutionException {
		
		Connect database = new Connect();
		ResultSet result = null;
		
		result = database.execute(operation,query).get();
		
		return result;
	}
	
	public static void closeResult(ResultSet result){
		
		if (result !=null){
			try {
				result.close();
			} catch (java.sql.SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
