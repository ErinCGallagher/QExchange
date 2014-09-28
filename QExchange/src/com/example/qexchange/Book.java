package com.example.qexchange;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable{
	private String name;
	private String author;
	private String comment;
	private int edition;
	private double price;
	private String tag;
	private String email;
	
		
		public Book(){
			name = " ";
			author = " ";
			edition = 1;
			price = 0;
			comment =" ";
			tag = " ";
			
		}
		
		public Book(String name, String author, int edition, double price, String comment, String tag, String email){
			this.name = name;
			this.edition = edition;
			this.price = price;
			this.tag = tag;
			this.author = author;
			this.email = email;
		}

		public String getName(){
			return name;
		}
		public String getAuthor(){
			return author;
		}
		public int getEdition(){
			return edition;
		}
		
		public double getPrice(){
			return price;
		}
		
		public String getComment(){
			return comment;
		}
		
		public String getTag(){
			return tag;
		}
		public String getEmail(){
			return email;
		}
		public void setName(String name){
			this.name = name;
		}
		public void setauthor(String author){
			this.author = author;
		}
		
		public void setEdition(int edition){
			this.edition = edition;
		}
		
		public void setPrice(double price){
			this.price = price;
		}
		
		public void setComment(String comment){
			this.comment = comment;
		}
		
		public void setTag(String tag){
			this.tag = tag;
		}
		
		//parcel part
		public Book(Parcel in){
			String[] data = new String[6];

			in.readStringArray(data);
			this.name = data[0];
			this.author = data[1];
			this.edition = Integer.parseInt(data[2]);
			this.price = Double.parseDouble(data[3]);
			this.comment = data[4];
			this.tag = data[5];
		}
		@Override
		public int describeContents() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void writeToParcel(Parcel dest, int flags) {
			// TODO Auto-generated method stub
			dest.writeStringArray(new String[]{this.name,this.author, String.valueOf(this.edition), String.valueOf(this.price), this.comment, this.tag});
		}

		public static final Parcelable.Creator<Book> CREATOR= new Parcelable.Creator<Book>() {

			@Override
			public Book createFromParcel(Parcel source) {
				// TODO Auto-generated method stub
				return new Book(source);  //using parcelable constructor
			}

			@Override
			public Book[] newArray(int size) {
				// TODO Auto-generated method stub
				return new Book[size];
			}
		};
			
}
	

