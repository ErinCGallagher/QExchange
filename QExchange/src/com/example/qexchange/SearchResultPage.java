package com.example.qexchange;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


public class SearchResultPage extends ListActivity  {
	
	String title,author,course, edition,price;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_result_page);

			Intent intent = getIntent();
			if (Intent.ACTION_SEARCH.equals(intent.getAction())){
				String query = intent.getStringExtra(SearchManager.QUERY);
	//			searchDatabase(query); TODO
//				displayResults(ResultSet) TODO
			}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search_result_page, menu);
		return true;
	}
	
	public void searchDatabase(String query){
		//TODO
	}
	
	/*public void displayResults(String[] ResultSet){
		title= ResultSet[0];
		author= ResultSet[1];
		edition= (String)ResultSet[2];
		course = ResultSet[3];
		price = (String)ResultSet[4];
	}*/
	
	public void displayResults(ArrayList<Book> list){
		
	}
	
	

}
