package com.example.qexchange;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

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
	private String emailInput, inputName;
	private Connect database = new Connect();
	private TextView nameText, emailText;

	private String title1, author1, comment1,course1, email1;
	private int edition1;
	private double price1;
	private ArrayList<Book> BookList = new ArrayList<Book>();
	private ArrayList<BookBunch> passedBack = new ArrayList<BookBunch>();
	private Account obj;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account_page);
		if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.HONEYCOMB){
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
		
		obj = getIntent().getParcelableExtra("userAccount");
		inputName = obj.getName();
		emailInput = obj.getEmail();
		
		//setting the title texts
		emailText = (TextView)findViewById(R.id.textView3);
		emailText.setText(emailInput);
		nameText = (TextView)findViewById(R.id.textView1);
		nameText.setText(inputName);
		
		//from BookInfoPage
		if (getIntent().getStringExtra("flag").equals("bookInfoPage")){		
			ArrayList<BookBunch> bookSaved = getIntent().getParcelableArrayListExtra("bookBunch");
			//do not query database
			for(final BookBunch point: bookSaved){
				BookList.add(point.getBookBunch());

			}

		}
		//from Main Search Page
		else{
			//query the database
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
		ListView bookList = (ListView)findViewById(R.id.listView1);
		bookList.setOnItemClickListener(this);
		bookList.setAdapter(bookAdapter);

		}

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Book clickedBook = BookList.get(position);
			for(Book point: BookList){
				passedBack.add(new BookBunch(point));
			}
			Intent j = new Intent(
					AccountPage.this,
					BookInfoPage.class);
			j.putExtra("newBook", clickedBook);
			j.putExtra("userAccount", obj);
			j.putExtra("bookBunch", passedBack);
			startActivity(j);


		}

		public void queryBooks()throws SQLException, InterruptedException, ExecutionException{
			java.sql.ResultSet result =null;
			String st = "SELECT * FROM Books WHERE userEmail = ('"+emailInput+"')";
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

				BookList.add(new Book(title1,author1,edition1,price1,comment1,course1, email1));

			}
		}

		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.account_page, menu);
			return true;
		}

		public boolean onOptionsItemSelected(MenuItem item) {
			Intent j = new Intent(
					AccountPage.this,
					MainPage.class);
			j.putExtra("userAccount", obj);
			startActivity(j);
			finish();
			return true;
		}

		public void launchAddBookPage(View view){		
			for(Book point: BookList){
				passedBack.add(new BookBunch(point));
			}
			Intent j = new Intent(
					AccountPage.this,
					AddBookPage.class);
			j.putExtra("userAccount", obj);
			j.putExtra("bookBunch", passedBack);
			startActivity(j);
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
				bookPrice.setText(String.format("$" + "%1$,.2f", nextBook.getPrice()));
				return arg1;

			}
			public Book getBookAtPosition(int position)
			{
				return bookList.get(position);
			}



		}

	}


