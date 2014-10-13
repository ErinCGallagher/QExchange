package com.example.qexchange;

import android.os.Parcel;
import android.os.Parcelable;

public class BookBunch implements Parcelable{

	private Book bookBunch;

	public BookBunch(Book bookBunch){
		this.bookBunch = bookBunch;
	}

	public Book getBookBunch(){
		return bookBunch;
	}

	public int describeContents(){
		return 0;
	}

	public void writeToParcel(Parcel out, int flags){
		out.writeString(bookBunch.getName());
		out.writeString(bookBunch.getAuthor());
		out.writeInt(bookBunch.getEdition());
		out.writeDouble(bookBunch.getPrice());
		out.writeString(bookBunch.getComment());
		out.writeString(bookBunch.getTag());
		out.writeString(bookBunch.getEmail());
		
	}

	public static final Creator<BookBunch> CREATOR = new Parcelable.Creator<BookBunch>() {
	public BookBunch createFromParcel(Parcel in) {
			return new BookBunch(in);
		}

		public BookBunch[] newArray(int size) {
			return new BookBunch[size];
		}
	};
	
	private BookBunch(Parcel in){
		String name = in.readString();
		String author = in.readString();
		int edition = in.readInt();
		double price = in.readDouble();
		String comment = in.readString();
		String tag = in.readString();
		String email = in.readString();

		bookBunch = new Book (name, author,edition,price,comment,tag,email);
			
	}


}
