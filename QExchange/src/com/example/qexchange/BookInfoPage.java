package com.example.qexchange;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class BookInfoPage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book_info_page);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.book_info_page, menu);
		return true;
	}

}
