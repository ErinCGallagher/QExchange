package com.example.qexchange;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginPage extends Activity {
	
	EditText usernameField, passwordField;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_page);
		usernameField = (EditText)findViewById(R.id.userField);
		passwordField = (EditText)findViewById(R.id.passField);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void launchCreateAccountPage(View view){
		startActivity(new Intent(LoginPage.this, CreateAccountPage.class));
    }
	
	public void submitLogin(View view){
		
		//validate login (query table
		//if valid, go to main page, else stay here, show error
		String emailInput = usernameField.getText().toString();
		String passwordInput = passwordField.getText().toString();
		if (emailInput.length() <= 0 || passwordInput.length() < 5){
			Toast toast = Toast.makeText(getApplicationContext(), "Please enter valid username and password", Toast.LENGTH_LONG);
			toast.show();
		} else {
			
			//create account: add appropriate info to tables, check that email not already there
			//startActivity(new Intent(LoginPage.this, MainPage.class));
		}
    }

}
