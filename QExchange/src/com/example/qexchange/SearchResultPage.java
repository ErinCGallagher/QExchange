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

	private Connect database = new Connect();
	private String title,author,course, edition,price;
	private BookListAdapter bookAdapter;
	private String emailInput, searchInput;
	private String title1, author1, comment1,course1, email1;
	private int edition1;
	private double price1;
	private ArrayList<Book> searchedBooks = new ArrayList<Book>();
	private ArrayList<BookBunch> passedBack = new ArrayList<BookBunch>();
	private Account useraccount;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_result_page);

		if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.HONEYCOMB){
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
		if (getIntent().getStringExtra("flag").equals("bookInfoMain")){
			useraccount = getIntent().getParcelableExtra("userAccount");
			ArrayList<BookBunch> bookSaved = getIntent().getParcelableArrayListExtra("bookBunch");
			for(final BookBunch point: bookSaved){
				searchedBooks.add(point.getBookBunch());

			}

		}
		else{
			useraccount = getIntent().getParcelableExtra("userAccount");
			searchInput = getIntent().getStringExtra("search");

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
		}

		BookListAdapter bookAdapter = new BookListAdapter();
		ListView bookList = (ListView)findViewById(android.R.id.list);
		bookList.setAdapter(bookAdapter);
		bookList.setOnItemClickListener(this);


	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Book currentBook = searchedBooks.get(position);
		for(Book point: searchedBooks){
			passedBack.add(new BookBunch(point));
		}
		Intent m = new Intent(
				SearchResultPage.this,
				BookInfoMainPage.class);
		m.putExtra("newBook", currentBook);
		m.putExtra("userAccount", useraccount);
		m.putExtra("bookBunch", passedBack);
		startActivity(m);
		finish();

	}


	public void queryBooks()throws SQLException, InterruptedException, ExecutionException{
		java.sql.ResultSet result =null;
		String st = "SELECT * FROM Books WHERE course = '"+searchInput+"'";
		result = database.execute("SELECT",st).get();
		int title, author, edition, price, comment, course, email;


		title = result.findColumn("title");
		author = result.findColumn("author");
		edition = result.findColumn("edition");
		price = result.findColumn("price");
		comment = result.findColumn("comment");
		course = result.findColumn("course");
		email = result.findColumn("userEmail");
		searchedBooks = new ArrayList<Book>();

		while(result.next()) {
			title1 = result.getString(title);
			author1 = result.getString(author);
			edition1 = result.getInt(edition);
			price1 = result.getDouble(price);
			comment1 = result.getString(comment);
			course1 = result.getString(course);
			email1 = result.getString(email);
			searchedBooks.add(new Book(title1,author1,edition1,price1,comment1,course1, email1));

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search_result_page, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent j = new Intent(
				SearchResultPage.this,
				MainPage.class);
		j.putExtra("userAccount", useraccount);
		startActivity(j);
		finish();
		return true;
	}

	public class BookListAdapter extends BaseAdapter {

		List<Book> bookList = searchedBooks;


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

}


