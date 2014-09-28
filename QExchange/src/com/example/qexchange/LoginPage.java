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
		Account userAccount;
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
			String query = "SELECT * FROM Accounts WHERE email = '"+emailInput+"' AND password = '"+passwordInput+"'";
			System.out.println(query);
			try {
				ResultSet results = Query.query("SELECT", query);
				System.out.println("Done query");
				if (results.next()) {
					System.out.println("In if");
					ResultSetMetaData rsmd=results.getMetaData();

					String userEmail = results.getString("email");
					String userPassword = results.getString("password");
					String userName = results.getString("name");
					userAccount = new Account(userEmail, userPassword, userName);
					System.out.println("logged in");
					System.out.println("get email"+userAccount.getEmail());
					Intent j = new Intent(
							LoginPage.this,
							MainPage.class);
					//j.putExtra("email", userAccount.getEmail());
					j.putExtra("userAccount", userAccount);
					startActivity(j);
				} else {
					//Toast toast = Toast.makeText(getApplicationContext(), "Incorrect username or password", Toast.LENGTH_LONG);
					//toast.show();
					
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
			//create account: add appropriate info to tables, check that email not already there
			//startActivity(new Intent(LoginPage.this, MainPage.class));
		}
    }

}
