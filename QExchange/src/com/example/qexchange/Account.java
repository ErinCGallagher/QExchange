package com.example.qexchange;

import android.os.Parcel;
import android.os.Parcelable;

public class Account implements Parcelable{

	private String email;
	private String name;


	public Account(String email, String name) {
		this.email = email;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	//parcel part
	public Account(Parcel in){
		String[] data = new String[2];

		in.readStringArray(data);
		this.email = data[0];
		this.name = data[1];
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeStringArray(new String[]{this.email,this.name});
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
