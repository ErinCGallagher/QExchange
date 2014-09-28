package com.example.qexchange;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainPage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_page);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_page, menu);
		return true;
	}
	
	public void launchAccountPage(View view){
    	startActivity(new Intent(MainPage.this, AccountPage.class));
    }
	
	public void launchSearchPage(View view){
    	startActivity(new Intent(MainPage.this, SearchResultPage.class));
    }
	
	public void searchTextbooks(View view){
		Toast toast = Toast.makeText(getApplicationContext(), "Searching...", Toast.LENGTH_SHORT);
		toast.show();
    }

}
