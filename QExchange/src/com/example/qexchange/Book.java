package com.example.qexchange;

public class Book {
	private String name;
	private String edition;
	private String price;
	private String tag;
	
		
		public Book(){
			name = " ";
			edition = " ";
			price = " ";
			tag = " ";
			
		}
		
		public Book(String name, String edition, String price, String tag){
			this.name = name;
			this.edition = edition;
			this.price = price;
			this.tag = tag;
		}

		public String getName(){
			return name;
		}
		public String getEdition(){
			return edition;
		}
		
		public String getPrice(){
			return price;
		}
		
		public String getTag(){
			return tag;
		}
		
		public void setName(String name){
			this.name = name;
		}
		
		public void setEdition(String edition){
			this.edition = edition;
		}
		
		public void setPrice(String price){
			this.price = price;
		}
		
		public void setTag(String tag){
			this.tag = tag;
		}
			
}
	

