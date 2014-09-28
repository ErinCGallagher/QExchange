package com.example.qexchange;

import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

import android.os.Build;
import android.os.Bundle;
import android.provider.SyncStateContract.Constants;
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
	Account e1 = null;

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
		String nameInput, emailInput, passwordInput, confirmPass = null;
		Boolean correctPass = false;

		nameField = (EditText)findViewById(R.id.nameField);
		nameInput = isTextValid(nameField);
		passwordField = (EditText)findViewById(R.id.newPassField);
		passwordInput = isTextValid(passwordField);
		confirmField = (EditText)findViewById(R.id.confirmPassField);
		if (passwordInput.length() > 5){
			System.out.println("greater than 5");
			confirmPass = isTextValid(confirmField);
		}
		else{
			confirmField.setError("must be > 5 letters");
			passwordField.setError("must be > 5 letters");
			
		}
		correctPass = doPasswordsMatch(passwordInput, confirmPass);
		emailField = (EditText)findViewById(R.id.emailField);
		emailInput = isTextValid(emailField);
		System.out.println("emailInput"+ emailInput);
		if (!nameInput.equals("retry") && !emailInput.equals("retry") && !passwordInput.equals("retry") && correctPass){
			e1 = new Account(emailInput, passwordInput, nameInput);
			check(e1);
		}

	}
	private void check(Account e1){
			try {
				if (Query.CheckEmailExists(e1.getEmail())) {
					Toast toast = Toast.makeText(getApplicationContext(), "Email already in use!", Toast.LENGTH_LONG);
					toast.show();
					emailField.setError("Email in Use");
				} else {
					String query = "INSERT INTO Accounts (email, password, name) VALUES ('"+e1.getEmail()+"','"+e1.getPassword()+"','"+e1.getName()+"')";
					Query.query("INSERT", query);
					System.out.println("get email"+e1.getEmail());
					Intent j = new Intent(
							CreateAccountPage.this,
							MainPage.class);
					j.putExtra("email", e1.getEmail());
					startActivity(j);
					//startActivity(new Intent(CreateAccountPage.this, MainPage.class));
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
		//Bundle b = new Bundle();
		//b.putSerializable(Constants.CUSTOM_LISTING,e1);
		Intent j = new Intent(
				CreateAccountPage.this,
				LoginPage.class);
    	startActivity(j);
		finish();
		return true;
	}

}
