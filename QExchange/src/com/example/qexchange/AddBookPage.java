package com.example.qexchange;

import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class AddBookPage extends Activity {
	Connect database = new Connect();


	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_book_page);
		if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.HONEYCOMB){
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}

		try {
			query();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	public void query() throws SQLException, InterruptedException, ExecutionException {
		java.sql.ResultSet result =null;
		String st = "SELECT * FROM Accounts";
		result = database.execute("SELECT",st).get();
		System.out.println("after but in try");
		System.out.println("after");
		//int id   = result.findColumn("ID");
		//int email    = result.findColumn("email");
		//int password  = result.findColumn("password");
		int name;
		name = result.findColumn("name");
		while(result.next()) {
			System.out.println(result.getString(name));

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.book_page, menu);
		return true;
	}



}
