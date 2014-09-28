package com.example.qexchange;

import android.os.Parcel;
import android.os.Parcelable;

public class Account implements Parcelable{

	private String email;
	private String password;
	private String name;

	public Account(String email, String password) {
		this(email, password, "Jon Snow");
	}

	public Account(String email, String password, String name) {
		this.email = email;
		this.password = password;
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	//parcel part
	public Account(Parcel in){
		String[] data = new String[3];

		in.readStringArray(data);
		this.email = data[0];
		this.password = data[1];
		this.name = data[2];
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeStringArray(new String[]{this.email,this.password,this.name});
	}

	public static final Parcelable.Creator<Account> CREATOR= new Parcelable.Creator<Account>() {

		@Override
		public Account createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new Account(source);  //using parcelable constructor
		}

		@Override
		public Account[] newArray(int size) {
			// TODO Auto-generated method stub
			return new Account[size];
		}
	};

}
