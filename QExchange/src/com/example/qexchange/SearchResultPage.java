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
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class SearchResultPage extends ListActivity  {

	String title,author,course, edition,price;
	BookListAdapter BookAdapter;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_result_page);

		Book[] TempList = null; //placeholder

		BookListAdapter bookAdapter = new BookListAdapter();
		ListView bookList = (ListView)findViewById(R.id.listView1);
		bookList.setAdapter(bookAdapter);

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

	public class BookListAdapter extends BaseAdapter {

		List<Book> bookList = getDataForListView();


		public int getCount(){
			return bookList.size();
		}

		@Override
		public Book getItem(int arg0) {
			return bookList.get(arg0);
		}

		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}



		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {

			if(arg1==null)
			{
				LayoutInflater inflater = (LayoutInflater) SearchResultPage.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				arg1 = inflater.inflate(R.layout.listitem, arg2,false);
			}

			TextView bookTitle = (TextView)arg1.findViewById(R.id.listingName);
			TextView bookAuthor = (TextView)arg1.findViewById(R.id.listingAuthor);
			TextView bookPrice = (TextView)arg1.findViewById(R.id.listingPrice);


			Book nextBook = bookList.get(arg0);

			bookTitle.setText(nextBook.getName());
			bookAuthor.setText(nextBook.getAuthor());
			bookPrice.setText(Double.toString(nextBook.getPrice()));

			return arg1;

		}
		public Book getBookAtPosition(int position)
		{
			return bookList.get(position);
		}

	}
	//End Class

	public List<Book> getDataForListView(){

		List<Book> BookList = new ArrayList<Book>();

		//Query our SQL database
		return BookList;

	}

}


