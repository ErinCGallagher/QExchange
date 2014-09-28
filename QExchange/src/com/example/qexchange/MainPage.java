package com.example.qexchange;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

public class MainPage extends Activity {
	private String emailInput, searchResults, name; 
	SearchView searchInput;
	Account obj;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_page);
		Intent i = getIntent();
		//emailInput = i.getStringExtra("email");
		//System.out.println("email main"+emailInput);
		obj = getIntent().getParcelableExtra("userAcount");
		name = obj.getName();
		emailInput = obj.getEmail();
		System.out.println("email main"+emailInput);
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
		//j.putExtra("email", emailInput);
		j.putExtra("userAcount", obj);
		startActivity(j);
    	//startActivity(new Intent(MainPage.this, AccountPage.class));
    }
	
	public void launchSearchPage(View view){
		searchInput = (SearchView)findViewById(R.id.searchView1);
		searchResults =searchInput.getQuery().toString().trim();
		searchResults = searchResults.replaceAll("\\s","");
		Intent j = new Intent(
				MainPage.this,
				SearchResultPage.class);
		j.putExtra("email", emailInput);
		j.putExtra("search", searchResults);
		startActivity(j);
    }
	
	public void searchTextbooks(View view){
		Toast toast = Toast.makeText(getApplicationContext(), "Searching...", Toast.LENGTH_SHORT);
		toast.show();
    }

}
