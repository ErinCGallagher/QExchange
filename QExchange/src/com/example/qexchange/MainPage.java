package com.example.qexchange;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

public class MainPage extends Activity {
	private String emailInput, searchResults, name; 
	private SearchView searchInput;
	private Account obj;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_page);
		obj = getIntent().getParcelableExtra("userAccount");
		emailInput = obj.getEmail();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_page, menu);
		return true;
	}
	
	public void launchAccountPage(View view){
		Intent j = new Intent(
				MainPage.this,
				AccountPage.class);
		j.putExtra("userAccount", obj);
		j.putExtra("flag", "Main");
		startActivity(j);
    }
	
	public void launchSearchPage(View view){
		searchInput = (SearchView)findViewById(R.id.searchView1);
		searchResults = searchInput.getQuery().toString().trim();
		searchResults = searchResults.replaceAll("\\s","");

		Intent j = new Intent(
				MainPage.this,
				SearchResultPage.class);
		j.putExtra("userAccount", obj);
		j.putExtra("search", searchResults);
		j.putExtra("flag", "MainPage");
		startActivity(j);
    }
	
	public void searchTextbooks(View view){
		Toast toast = Toast.makeText(getApplicationContext(), "Searching...", Toast.LENGTH_SHORT);
		toast.show();
    }

}