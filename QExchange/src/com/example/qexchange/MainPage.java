package com.example.qexchange;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.example.qexchange.SearchResultPage.BookListAdapter;

import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

public class MainPage extends Activity {
	private String emailInput, searchResults, name; 
	SearchView searchInput;
	Account obj;
	
	Connect database = new Connect();
	String title,author,course, edition,price;
	BookListAdapter BookAdapter;
	String title1, author1, comment1,course1, email1;
	int edition1;
	double price1;
	List<Book> BookList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_page);
		Intent i = getIntent();
		
		obj = getIntent().getParcelableExtra("userAccount");
		name = obj.getName();
		emailInput = obj.getEmail();
		
		if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.HONEYCOMB){
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Book currentBook = BookList.get(position);
		
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
		int email;
		title = result.findColumn("title");
		author = result.findColumn("author");
		edition = result.findColumn("edition");
		price = result.findColumn("price");
		comment = result.findColumn("comment");
		course = result.findColumn("course");
		email = result.findColumn("userEmail");
		BookList = new ArrayList<Book>();
		while(result.next()) {
			title1 = result.getString(title);
			author1 = result.getString(author);
			edition1 = result.getInt(edition);
			price1 = result.getDouble(price);
			comment1 = result.getString(comment);
			course1 = result.getString(course);
			email1 = result.getString(email);
			System.out.println("getName"+title1);
			System.out.println("getEdition"+edition1);
			BookList.add(new Book(title1,author1,edition1,price1,comment1,course1, email1));

		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_page, menu);
		return true;
	}
	
	public void launchAccountPage(View view){
		Intent j = new Intent(
				MainPage.this,
				AccountPage.class);
		j.putExtra("userAccount", obj);
		startActivity(j);
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
				LayoutInflater inflater = (LayoutInflater) MainPage.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
	
	public void launchSearchPage(View view){
		searchInput = (SearchView)findViewById(R.id.searchView1);
		searchResults =searchInput.getQuery().toString();
		
		try {
			queryBooks();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Toast toast = Toast.makeText(getApplicationContext(), "Database connection failed. Please try again!", Toast.LENGTH_SHORT);
			toast.show();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BookListAdapter bookAdapter = new BookListAdapter();
		ListView bookList = (ListView)findViewById(R.id.listView1);
		//bookList.setOnItemClickListener(this);
		bookList.setAdapter(bookAdapter);
    }
	
	public void searchTextbooks(View view){
		Toast toast = Toast.makeText(getApplicationContext(), "Searching...", Toast.LENGTH_SHORT);
		toast.show();
    }

}
