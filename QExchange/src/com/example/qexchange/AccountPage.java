package com.example.qexchange;

import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class AccountPage extends Activity {
	String emailInput, inputName;
	Connect database = new Connect();
	TextView nameText, emailText;
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
		//setting the title texts
		emailText = (TextView)findViewById(R.id.textView3);
		emailText.setText(emailInput);
		nameText = (TextView)findViewById(R.id.textView1);
		nameText.setText(inputName);
		
	}
	
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
	}
	
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.account_page, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		startActivity(new Intent(AccountPage.this, MainPage.class));
		finish();
		return true;
	}
	
	public void launchAddBookPage(View view){
		//startActivity(new Intent(AccountPage.this, AddBookPage.class));
		Intent j = new Intent(
				AccountPage.this,
				AddBookPage.class);
		j.putExtra("email", emailInput);
		startActivity(j);
    }

}
