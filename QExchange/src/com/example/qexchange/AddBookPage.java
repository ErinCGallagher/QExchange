package com.example.qexchange;

import java.sql.SQLException;
import java.util.concurrent.ExecutionException;




import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class AddBookPage extends Activity {
	Connect database = new Connect();


	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_book_page);
		if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.HONEYCOMB){
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
		/*
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
		 */

	}

	public void submitBook (View v){
		String title, author, courseCode;
		int edition; 
		double price;
		
		EditText editTitle = (EditText) findViewById(R.id.nameField);
		title = isTextValid(editTitle);
		EditText editAuthor = (EditText) findViewById(R.id.newPassField);
		author = isTextValid(editAuthor);
		EditText editEdition = (EditText) findViewById(R.id.editText5);
		edition = isIntValid(editEdition);
		EditText editCourseCode = (EditText) findViewById(R.id.confirmPassField);
		courseCode = isTextValid(editCourseCode);
		EditText editPrice = (EditText) findViewById(R.id.emailField);
		price = isDoubleValid(editPrice);		

		//all input if correct; add to database
		if (!title.equals("retry") && !author.equals("retry")  && edition!=0 && !courseCode.equals("retry") && price != 0 ){
			Book b1 = new Book(title, author,edition, price, courseCode );
			//String query = "INSERT INTO Books"
			//Query.query("INSERT", query)
		}
	}
	
	private int isIntValid(EditText text){
		String inputText = text.getText().toString().trim();
		if (inputText.length() <= 0){
			text.setError("Cannot be empty");
			System.out.println("empty int");
			return 0;
		}

		else {
			return Integer.parseInt(text.getText().toString());
		}
	}

	private String isTextValid(EditText text){
		if (text.getText().toString().trim().length() == 0){
			text.setError("Cannot be empty string");
			return "retry";
		}
		else
			return text.getText().toString();
	}

	private double isDoubleValid(EditText text){
		if (text.getText().toString().trim().length() == 0){
			text.setError("Cannot be empty");
			System.out.println("empty double");
			return 0;
		}

		else {
			return Double.parseDouble(text.getText().toString());
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

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		startActivity(new Intent(AddBookPage.this, AccountPage.class));
		finish();
		return true;
	}



}
