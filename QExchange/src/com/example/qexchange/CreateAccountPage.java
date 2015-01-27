package com.example.qexchange;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreateAccountPage extends Activity {

	private EditText nameField, passwordField, confirmField, emailField;
	private Account userAccount = null;
	private String nameInput, emailInput, passwordInput, confirmPass = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_account);
		if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.HONEYCOMB){
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}

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
		
		Boolean correctPass = false;

		nameField = (EditText)findViewById(R.id.nameField);
		nameInput = isTextValid(nameField);
		
		//name cannot be > 50
		if (nameInput.length() > 50) {
			nameInput = "retry";
			nameField.setError("Name can be no longer than 50 characters");
		}
		
		passwordField = (EditText)findViewById(R.id.newPassField);
		passwordInput = isTextValid(passwordField);
		confirmField = (EditText)findViewById(R.id.confirmPassField);
		confirmPass = isTextValid(confirmField);
		
		//password needs to be greater or = to 5
		if (passwordInput.length() < 5 || passwordInput.length() > 20){
			passwordField.setError("Passwords must be at least 5 characters but less than 20 characters");
		}
		
		correctPass = doPasswordsMatch(passwordInput, confirmPass);

		emailField = (EditText)findViewById(R.id.emailField);
		emailInput = isTextValid(emailField);
		
		//email must be less than 50 characters
		if (emailInput.length() > 50) {
			emailInput = "retry";
			emailField.setError("Emails can be no longer than 50 characters");
		}

		if (!nameInput.equals("retry") && !emailInput.equals("retry") && !passwordInput.equals("retry") && correctPass){
			userAccount = new Account(emailInput, nameInput);
			check(userAccount);
		}

	}
	private void check(Account e1){
			try {
				if (Query.CheckEmailExists(e1.getEmail())) {
					Toast toast = Toast.makeText(getApplicationContext(), "Email already in use!", Toast.LENGTH_LONG);
					toast.show();
					emailField.setError("Email in Use");
				} else {
					String query = "INSERT INTO Accounts (email, password, name) VALUES ('"+e1.getEmail()+"','"+passwordInput+"','"+e1.getName()+"')";
					ResultSet result = Query.query("INSERT", query);
					
					//go to Main Page
					Intent j = new Intent(
							CreateAccountPage.this,
							MainPage.class);
					j.putExtra("userAccount", userAccount);
					startActivity(j);
					
					//close resultSet
					Query.closeResult(result);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}

	//}

	private String isTextValid(EditText text){
		if (text.getText().toString().trim().length() == 0){
			text.setError("Cannot be empty string");
			return "retry";
		}
		else
			return text.getText().toString();
	}

	private Boolean doPasswordsMatch(String one, String two){

		if (one.equals(two)){
			return true;
		}
		else{
			confirmField.setError("not same password");
			passwordField.setError("not same password");
			return false;
		}
			
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		Intent j = new Intent(
				CreateAccountPage.this,
				LoginPage.class);
    	startActivity(j);
		finish();
		return true;
	}

}
