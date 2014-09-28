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
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class AccountPage extends Activity implements OnItemClickListener{
	String emailInput, inputName;
	Connect database = new Connect();
	TextView nameText, emailText;
	
	String title,author,course, edition,price;
	BookListAdapter BookAdapter;
	String title1, author1, comment1,course1;
	int edition1;
	double price1;
	List<Book> BookList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account_page);
		if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.HONEYCOMB){
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
		Intent i = getIntent();
		emailInput = i.getStringExtra("email");
		System.out.println("email main from account"+emailInput);
		/*
		try {
			queryTitles();
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
		*/
		//setting the title texts
		emailText = (TextView)findViewById(R.id.textView3);
		emailText.setText(emailInput);
		//nameText = (TextView)findViewById(R.id.textView1);
		//nameText.setText(inputName);
		

		Book[] TempList = null; //placeholder

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
		ListView bookList = (ListView)findViewById(R.id.listView1);
		bookList.setOnItemClickListener(this);
		bookList.setAdapter(bookAdapter);
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Book clickedBook = BookList.get(position);
		
		
	}
	/*
		
		if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.HONEYCOMB){
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	 * */
	
	public void queryTitles()throws SQLException, InterruptedException, ExecutionException{
		java.sql.ResultSet result =null;
		String st = "SELECT name FROM Accounts WHERE email = ('"+emailInput+"')";
		result = database.execute("SELECT",st).get();
		//int email = result.findColumn("email");
		//int password  = result.findColumn("password");
		int name;
		name = result.findColumn("name");
		while(result.next()) {
			inputName = result.getString(name);
			System.out.println("getName"+inputName);

		}
		result.close();
	}
	public void queryBooks()throws SQLException, InterruptedException, ExecutionException{
		java.sql.ResultSet result =null;
		String st = "SELECT * FROM Books WHERE userEmail = ('"+emailInput+"')";
		result = database.execute("SELECT",st).get();
		//int email = result.findColumn("email");
		//int password  = result.findColumn("password");
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
	
	/*
	public void queryBooks()throws SQLException, InterruptedException, ExecutionException{
		java.sql.ResultSet result =null;
		String st = "SELECT name FROM Books WHERE userID = ('"+emailInput+"')";
		result = database.execute("SELECT",st).get();
		//int email = result.findColumn("email");
		//int password  = result.findColumn("password");
		int name;
		name = result.findColumn("name");
		while(result.next()) {
			inputName = result.getString(name);
			System.out.println("getName"+inputName);

		}
	}
	*/
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.account_page, menu);
		return true;
	}
	
	
	public class BookListAdapter extends BaseAdapter {

		List<Book> bookList = BookList;
		//List<Book> bookList = getDataForListView();


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
				LayoutInflater inflater = (LayoutInflater) AccountPage.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
		
		public void launchAddBookPage(View view){
			//startActivity(new Intent(AccountPage.this, AddBookPage.class));
			Intent j = new Intent(
					AccountPage.this,
					AddBookPage.class);
			j.putExtra("email", emailInput);
			startActivity(j);
	    }
		public boolean onOptionsItemSelected(MenuItem item) {
			//Bundle b = new Bundle();
			//b.putSerializable(Constants.CUSTOM_LISTING,e1);
			Intent j = new Intent(
					AccountPage.this,
					MainPage.class);
			j.putExtra("email", emailInput);
	    	startActivity(j);
			finish();
			return true;
		}

	}
	//End Class
/*
	public List<Book> getDataForListView(){

		List<Book> BookList = new ArrayList<Book>();


		BookList.add(new Book("Algorithms", "Dawes", 1, 199.99, "good","CISC365"));
		BookList.add(new Book("Python", "Lamb", 1, 132.00, "good","CISC101"));
		BookList.add(new Book("Logic", "Glasgow", 1, 75.00, "good","CISC204"));
		BookList.add(new Book("Evil", "Dove", 1, 250.00, "good","CISC327"));
		return BookList;

	}
*/
	/*
	public void launchMainPage(View view) {
		Intent j = new Intent(
				SearchResultPage.this,
				MainPage.class);
    	startActivity(j);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		//Bundle b = new Bundle();
		//b.putSerializable(Constants.CUSTOM_LISTING,e1);
		Intent j = new Intent(
				SearchResultPage.this,
				MainPage.class);
		j.putExtra("email", emailInput);
    	startActivity(j);
		finish();
		return true;
	}
	*/
}


