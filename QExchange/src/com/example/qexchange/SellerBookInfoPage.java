package com.example.qexchange;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class SellerBookInfoPage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_seller_book_info_page);
		if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.HONEYCOMB){
			getActionBar().setDisplayHomeAsUpEnabled(true);
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
		startActivity(new Intent(SellerBookInfoPage.this, AccountPage.class));
		finish();
		return true;
	}
	
	public void deleteBookListing(View view) {
		//warning: are you sure?
		//if yes, remove from database and return to account
		//else, stay on bookInfo
		Toast toast = Toast.makeText(getApplicationContext(), "Book deleted", Toast.LENGTH_SHORT);
		toast.show();
		startActivity(new Intent(SellerBookInfoPage.this, AccountPage.class));
	}
}
