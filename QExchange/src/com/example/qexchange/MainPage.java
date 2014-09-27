package com.example.qexchange;

import android.os.Bundle;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.view.Menu;
import android.widget.SearchView;

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
		
//		 SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//		    SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
//		    // Assumes current activity is the searchable activity
//		    searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
//		    searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default
		    
		return true;
	}

}
