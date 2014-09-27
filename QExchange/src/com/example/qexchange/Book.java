package com.example.qexchange;

public class Book {
	private String name;
	private int edition;
	private double price;
	private String tag;
	private int id;
	
		
		public Book(){
			name = " ";
			edition = 1;
			price = 0;
			tag = " ";
			
		}
		
		public Book(String name, int edition, double price, String tag){
			this.name = name;
			this.edition = edition;
			this.price = price;
			this.tag = tag;
		}

		public String getName(){
			return name;
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
	

