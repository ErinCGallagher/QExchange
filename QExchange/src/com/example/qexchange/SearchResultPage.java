package com.example.qexchange;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;

public class SearchResultPage extends ListActivity 
		implements LoaderManager.LoaderCallback<Cursor>{
	
	SimpleCursorAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_result_page);
		
		
		String[] fromColumns = {};
	    int[] toViews = {android.R.id.text1};
	        
	        
		mAdapter = new SimpleCursorAdapter(this, 
                android.R.layout.simple_list_item_1, null,
                fromColumns, toViews, 0);
        setListAdapter(mAdapter);
        
       
	
			Intent intent = getIntent();
			if (Intent.ACTION_SEARCH.equals(intent.getAction())){
				String query = intent.getStringExtra(SearchManager.QUERY);
				displayResults(query);
			}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search_result_page, menu);
		return true;
	}
	
	public void displayResults(String query){
		
	}
	
	

}
