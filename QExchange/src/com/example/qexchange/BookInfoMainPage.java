package com.example.qexchange;

import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class BookInfoMainPage extends Activity {
	private String name, author, comment, course, email;
	private int edition;
	private double price;
	private Book obj;
	private Account account;
	private String search;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book_info_main_page);
		if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.HONEYCOMB){
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
		
		obj = getIntent().getParcelableExtra("newBook");
		name = obj.getName();
		author = obj.getAuthor();
		edition = obj.getEdition();
		price = obj.getPrice();
		comment = obj.getComment();
		course = obj.getTag();
		email = obj.getEmail();
		
		search = getIntent().getStringExtra("search");
		System.out.println("search"+search);
		
		TextView emailText = (TextView)findViewById(R.id.emailText);
		emailText.setText(email);
		TextView titleText = (TextView)findViewById(R.id.titleText);
		titleText.setText(name);
		TextView authorText = (TextView)findViewById(R.id.authorText);
		authorText.setText(author);
		TextView editionText = (TextView)findViewById(R.id.editionText);
		editionText.setText(Integer.toString(edition));
		TextView priceText = (TextView)findViewById(R.id.priceText);
		priceText.setText(Double.toString(price));
		TextView courseText = (TextView)findViewById(R.id.courseText);
	    courseText.setText(course);
		
		
		
		//for going "back" to the account page
		account = getIntent().getParcelableExtra("userAccount");
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.book_info_page, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent j = new Intent(
				BookInfoMainPage.this,
				SearchResultPage.class);
		j.putExtra("userAccount", account);
		j.putExtra("search",  search);
		startActivity(j);
		finish();
		return true;
	}

}