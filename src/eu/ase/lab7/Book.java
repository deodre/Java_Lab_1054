package eu.ase.lab7;

import java.io.Serializable;

public class Book implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4518036732364705845L;
	private String title;
	private Author author;
	private int year;
	public Book(String title, Author author, int year) {
		super();
		this.title = title;
		this.author = author;
		this.year = year;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author.getName() + ", year=" + year + "]";
	}
	
	
}
