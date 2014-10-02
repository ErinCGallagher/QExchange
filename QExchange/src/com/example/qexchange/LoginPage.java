package com.example.qexchange;


import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginPage extends Activity {
	
	private EditText usernameField, passwordField;

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
	
	
	//if valid, go to main page, else stay here, show error
	public void submitLogin(View view){
		Account userAccount;
		
		String emailInput = usernameField.getText().toString();
		String passwordInput = passwordField.getText().toString();
		
		boolean validInput = true;
		
		if (emailInput.length() <= 0 || emailInput.length() >= 50 ) {
			usernameField.setError("Enter valid username"); 
			validInput = false;
		} 
		
		if (passwordInput.length() < 5 || passwordInput.length() >= 20){
			passwordField.setError("Enter valid password");
			validInput = false;
		} 
		
		if (validInput) {
			String query = "SELECT name FROM Accounts WHERE email = '"+emailInput+"' AND password = '"+passwordInput+"'";

			try {
				//finished query
				ResultSet results = Query.query("SELECT", query);
				
				//found account with username password validation
				if (results.next()) {
					ResultSetMetaData rsmd=results.getMetaData();

					String userName = results.getString("name");
					userAccount = new Account(emailInput, userName);

					Intent j = new Intent(
							LoginPage.this,
							MainPage.class);
					j.putExtra("userAccount", userAccount);
					startActivity(j);
					
				} else { //if there is no account that exists with the inputted login info
					AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);

		            dlgAlert.setMessage("Invalid username or password.");
		            dlgAlert.setTitle("Login failed");
		            dlgAlert.setPositiveButton("OK", null);
		            dlgAlert.setCancelable(true);
		            dlgAlert.create().show();

		            dlgAlert.setPositiveButton("Ok",
		                    new DialogInterface.OnClickListener() {
		                public void onClick(DialogInterface dialog, int which) {

		                }
		            });
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}

		}
	}

}
