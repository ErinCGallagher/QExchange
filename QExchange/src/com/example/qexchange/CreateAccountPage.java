package com.example.qexchange;

import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreateAccountPage extends Activity {

	EditText nameField, passwordField, confirmField, emailField;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_account);
		if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.HONEYCOMB){
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
		nameField = (EditText)findViewById(R.id.nameField);
		passwordField = (EditText)findViewById(R.id.newPassField);
		confirmField = (EditText)findViewById(R.id.confirmPassField);
		emailField = (EditText)findViewById(R.id.emailField);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_account, menu);
		return true;
	}

	public void launchLoginPage(View view){
		startActivity(new Intent(CreateAccountPage.this, LoginPage.class));
	}

	public void createNewAccount(View view){
		String email = emailField.getText().toString();
		String password = passwordField.getText().toString();
		String name = nameField.getText().toString();
		if (!password.equals(confirmField.getText().toString())){
			Toast toast = Toast.makeText(getApplicationContext(), "Passwords don't match", Toast.LENGTH_LONG);
			toast.show();
		} else if (password.length() < 5) {
			Toast toast = Toast.makeText(getApplicationContext(), "Passwords must be at least 5 characters", Toast.LENGTH_LONG);
			toast.show();
		} else if (name.length() <= 0) {
			Toast toast = Toast.makeText(getApplicationContext(), "Please enter a valid name", Toast.LENGTH_LONG);
			toast.show();
		} else {
			//create account: add appropriate info to tables, check that email not already there
			try {
				if (Query.CheckEmailExists(emailField.getText().toString())) {
					Toast toast = Toast.makeText(getApplicationContext(), "Email already in use!", Toast.LENGTH_LONG);
				} else {
					String query = "INSERT INTO Accounts (email, password, name) VALUES ("+email+","+password+","+name+")";
					Query.query("INSERT", query);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			startActivity(new Intent(CreateAccountPage.this, MainPage.class));
		}

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		startActivity(new Intent(CreateAccountPage.this, LoginPage.class));
		finish();
		return true;
	}

}
