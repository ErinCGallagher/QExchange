package com.example.qexchange;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import android.os.Build;
import android.os.Bundle;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class SearchResultPage extends ListActivity implements OnItemClickListener  {
	
	Connect database = new Connect();
	String title,author,course, edition,price;
	BookListAdapter BookAdapter;
	String emailInput, searchInput;
	String title1, author1, comment1,course1;
	int edition1;
	double price1;
	List<Book> BookList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_result_page);
		
		Intent i = getIntent();
		emailInput = i.getStringExtra("email");
		searchInput = i.getStringExtra("search");
		System.out.println("email from search"+emailInput);
		System.out.println("email from search"+searchInput);

		try {
			queryBooks();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		BookListAdapter bookAdapter = new BookListAdapter();
		ListView bookList = (ListView)findViewById(android.R.id.list);
		bookList.setAdapter(bookAdapter);
		
		if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.HONEYCOMB){
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}

	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Book currentBook = BookList.get(position);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search_result_page, menu);
		return true;
	}

	public void queryBooks()throws SQLException, InterruptedException, ExecutionException{
		java.sql.ResultSet result =null;
		String st = "SELECT * FROM Books WHERE course = ('"+searchInput+"')";
		result = database.execute("SELECT",st).get();
		int title;
		int author;
		int edition;
		int price;
		int comment;
		int course;
		title = result.findColumn("title");
		author = result.findColumn("author");
		edition = result.findColumn("edition");
		price = result.findColumn("price");
		comment = result.findColumn("comment");
		course = result.findColumn("course");
		BookList = new ArrayList<Book>();
		while(result.next()) {
			title1 = result.getString(title);
			author1 = result.getString(author);
			edition1 = result.getInt(edition);
			price1 = result.getDouble(price);
			comment1 = result.getString(comment);
			course1 = result.getString(course);
			System.out.println("getName"+title1);
			System.out.println("getEdition"+edition1);
			BookList.add(new Book(title1,author1,edition1,price1,comment1,course1));

		}
	}

	public class BookListAdapter extends BaseAdapter {

		List<Book> bookList = BookList;

		public int getCount(){
			return bookList.size();
		}

		@Override
		public Book getItem(int arg0) {
			return bookList.get(arg0);
		}

		public long getItemId(int arg0) {
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
			bookPrice.setText(String.format("$" + "%1$,.2f", nextBook.getPrice()));

			return arg1;

		}
		public Book getBookAtPosition(int position)
		{
			return bookList.get(position);
		}

	}
	//End Class
	
	public void launchMainPage(View view) {
		Intent j = new Intent(
				SearchResultPage.this,
				MainPage.class);
    	startActivity(j);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent j = new Intent(
				SearchResultPage.this,
				MainPage.class);
		j.putExtra("email", emailInput);
    	startActivity(j);
		finish();
		return true;
	}
}


