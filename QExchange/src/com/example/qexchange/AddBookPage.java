package com.example.qexchange;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import com.mysql.jdbc.ResultSet;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class AddBookPage extends Activity {
	private Connect database = new Connect();
	private String emailInput, inputName;
	private Account obj;
	private ArrayList<BookBunch> bookSaved;
	private ArrayList<Book> searchedBook = new ArrayList<Book>();
	private ArrayList<BookBunch> bookSavedUpdated = new ArrayList<BookBunch>();
	

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_book_page);
		if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.HONEYCOMB){
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
		obj = getIntent().getParcelableExtra("userAccount");
		emailInput = obj.getEmail();
		
		bookSaved = getIntent().getParcelableArrayListExtra("bookBunch");
		for(BookBunch point: bookSaved){
			searchedBook.add(point.getBookBunch());
		}	

	}

	public void submitBook (View v){
		String title, author, courseCode, comment;
		int edition; 
		double price;

		EditText editTitle = (EditText) findViewById(R.id.nameField);
		title = isTextValid(editTitle);
		//book title cannot be greater than 50 characters
		if (title.length() > 50) {
			title = "retry";
			editTitle.setError("Book titles must be no longer than 50 characters");
		}

		EditText editAuthor = (EditText) findViewById(R.id.newPassField);
		author = isTextValid(editAuthor);
		//author name cannot be greater than 50 characters
		if (author.length() > 50) {
			author = "retry";
			editAuthor.setError("Author names must be no longer than 50 characters");
		}

		EditText editEdition = (EditText) findViewById(R.id.editText5);
		edition = isIntValid(editEdition);
		//edition check
		if (edition > 1000 || edition <= 0) {
			edition = 0;
			editEdition.setError("Not a valid edition number");
		}

		EditText editCourseCode = (EditText) findViewById(R.id.confirmPassField);
		courseCode = isTextValid(editCourseCode);
		//course code check and trim
		if (courseCode.length() > 11 || courseCode.length() < 7) {
			courseCode = "retry";
			editCourseCode.setError("Not a valid course code");
		} else {
			//removes all spaces from course code
			courseCode = courseCode.replaceAll("\\s","");
		}

		EditText editPrice = (EditText) findViewById(R.id.emailField);
		price = isDoubleValid(editPrice);	
		//price check
		if (price > 1000 || price <= 0) {
			price = 0;
			editPrice.setError("Please enter a price between 0 and 1000");
		}

		EditText editComment = (EditText) (findViewById(R.id.editText1));
		comment = editComment.getText().toString();
		//comment check
		if (comment.length() > 200) {
			comment = "retry";
			editComment.setError("Comments can be no longer than 200 characters");
		}
		//all input if correct; add to database
		if (!title.equals("retry") && !author.equals("retry")  && edition!=0 && !courseCode.equals("retry") && price != 0 && !comment.equals("retry")){
			Book b1 = new Book(title, author,edition, price, comment, courseCode, emailInput);
			searchedBook.add(b1);
			addToDatabase(title, author, courseCode, comment, edition, price);
			
		}

		
	}
	private void addToDatabase(String title, String author, String courseCode, String comment, int edition, double price){
		java.sql.ResultSet result =null;
		String query = "INSERT INTO Books (title, author, edition, course, price, userEmail, comment) VALUES ('"+title+"','"+author+"','"+edition+"','"+courseCode+"','"+price+"','"+emailInput+"','"+comment+"')";
		try {
			result = Query.query("INSERT", query);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		//update list of Books
		for(Book point: searchedBook){
			bookSavedUpdated.add(new BookBunch(point));
			System.out.println("name: "+point.getName());
		}

		Intent j = new Intent(
				AddBookPage.this,
				AccountPage.class);
		j.putExtra("flag","bookInfoPage");
		j.putExtra("userAccount", obj);
		j.putExtra("bookBunch", bookSavedUpdated);
		startActivity(j);
		startActivity(j);
		
		//close resultSet
		Query.closeResult(result);
	}


private int isIntValid(EditText text){
	String inputText = text.getText().toString().trim();
	if (inputText.length() <= 0){
		text.setError("Cannot be empty");
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
		return 0;
	}

	else {
		return Double.parseDouble(text.getText().toString());
	}
}


public void query() throws SQLException, InterruptedException, ExecutionException {
	java.sql.ResultSet result =null;
	String st = "SELECT name FROM Accounts WHERE email = ('"+emailInput+"')";
	result = database.execute("SELECT",st).get();
	int name;
	name = result.findColumn("name");
	while(result.next()) {
		inputName = result.getString(name);
		System.out.println("getName"+inputName);

	}
	//close resultSet
	Query.closeResult(result);
}

@Override
public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.book_page, menu);
	return true;
}

@Override
public boolean onOptionsItemSelected(MenuItem item) {
	Intent j = new Intent(
			AddBookPage.this,
			AccountPage.class);
	j.putExtra("flag","bookInfoPage");
	j.putExtra("userAccount", obj);
	j.putExtra("bookBunch", bookSaved);
	startActivity(j);
	finish();
	return true;
}



}
