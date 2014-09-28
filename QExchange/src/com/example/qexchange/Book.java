package com.example.qexchange;

public class Book {
	private String name;
	private String author;
	private int edition;
	private double price;
	private String tag;
	
		
		public Book(){
			name = " ";
			author = " ";
			edition = 1;
			price = 0;
			tag = " ";
			
		}
		
		public Book(String name, String author, int edition, double price, String tag){
			this.name = name;
			this.edition = edition;
			this.price = price;
			this.tag = tag;
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
		
		public String getTag(){
			return tag;
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
		
		public void setTag(String tag){
			this.tag = tag;
		}
			
}
	

